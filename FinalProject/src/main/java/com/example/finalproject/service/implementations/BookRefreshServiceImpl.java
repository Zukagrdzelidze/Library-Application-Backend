package com.example.finalproject.service.implementations;

import com.example.finalproject.clients.OpenLibrary;
import com.example.finalproject.constants.OpenLibraryConstants;
import com.example.finalproject.domain.Book;
import com.example.finalproject.exception.*;
import com.example.finalproject.repository.BookRepository;
import com.example.finalproject.service.BookRefreshService;
import lombok.RequiredArgsConstructor;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


@Service
@RequiredArgsConstructor
public class BookRefreshServiceImpl implements BookRefreshService {
    private final OpenLibrary openLibrary;
    private final BookRepository bookRepository;

    /**
        Refreshes Book By its Id. It takes id and gets Json object by it's book title
        This Function turns JSON Object to Book which we save in Database.
        This Function may throw several exceptions if book is already refreshed or we weren't
        able to find book or etc.
     */
    @Override
    public void refreshBook(Long id) {

        Book book = bookRepository.findById(id).orElseThrow(BookIdNotValidException::new);
        if(book.isRefreshed()){
            throw new AlreadyRefreshedException();
        }
        JSONObject jsonResponse  = openLibrary.getJsonFromLibrary(book.getTitle());

        if (jsonResponse != null) {
            if (jsonResponse.has(OpenLibraryConstants.JSON_OBJECT_NAME)) {
                JSONArray docs = jsonResponse.getJSONArray(OpenLibraryConstants.JSON_OBJECT_NAME);
                if (!docs.isEmpty()) {
                    JSONObject firstBook = docs.getJSONObject(0);
                    String title = firstBook.optString(OpenLibraryConstants.TITLE, OpenLibraryConstants.UNKNOWN_TITLE);
                    String author = firstBook.optJSONArray(OpenLibraryConstants.AUTHOR)
                            .optString(0, OpenLibraryConstants.UNKNOWN_AUTHOR);
                    int numberOfPages = firstBook.optInt(OpenLibraryConstants.NUM_PAGES, 0);

                    book.setAuthor(author);
                    book.setTitle(title);
                    book.setNumberOfPages(numberOfPages);
                    book.setRefreshed(true);
                    bookRepository.save(book);
                }else{
                    throw new BookDoesNotExistInAPI();
                }
            }else{
                throw new WrongJSONObjectNames(OpenLibraryConstants.JSON_OBJECT_NAME);
            }
        }else{
            throw new WrongApiLink();
        }
    }


    /**
        This Function refreshes all books. It takes Unrefreshed book's list and refreshes it.
        We use ThreadPoolExecutor and we call refreshBook for every ID.
     */
    @Override
    public void refreshAllBooks() {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        List<Book> unrefreshedBook = bookRepository.findUnrefreshedBooks();
        for (int i = 0; i < unrefreshedBook.size(); i++) {
            int finalI = i;
            executorService.submit(new Runnable() {
                @Override
                public void run() {
                    Book book = unrefreshedBook.get(finalI);
                    refreshBook(book.getId());
                }
            });
        }
    }
}
