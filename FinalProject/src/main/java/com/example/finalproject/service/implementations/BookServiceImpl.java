package com.example.finalproject.service.implementations;


import com.example.finalproject.domain.Book;
import com.example.finalproject.domain.LoanBook;
import com.example.finalproject.exception.BookAlreadyExists;
import com.example.finalproject.exception.BookIdNotValidException;
import com.example.finalproject.exception.CustomerNotInLoanBooksException;
import com.example.finalproject.model.request.BookRegisterRequest;
import com.example.finalproject.model.response.BookResponse;
import com.example.finalproject.model.response.RefreashedBookResponse;
import com.example.finalproject.repository.BookRepository;
import com.example.finalproject.repository.LoanBookRepository;
import com.example.finalproject.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final LoanBookRepository loanBookRepository;

    /**
        This Functions takes Book Title and Amount and registers book in database.
        This book will be unrefreshed but after refresh it will be ready to use
     */
    @Override
    public void registerBook(BookRegisterRequest bookRegisterRequest) {
        if (bookRepository.existsByTitle(bookRegisterRequest.getTitle())) {
            throw new BookAlreadyExists();
        }
        bookRepository.save(Book.builder().title(bookRegisterRequest.getTitle()).amount(bookRegisterRequest.getAmount()).build());
    }

    /**
        This functions takes ID and activates book.
     */
    @Override
    public void activateBook(Long bookId) {
        Book book = bookRepository.findById(bookId).orElseThrow(BookIdNotValidException::new);
        book.setActivated(true);
        bookRepository.save(book);
    }

    /**
        This functions takes ID and deactivates book.
     */
    @Override
    public void deactivateBook(Long bookId) {
        Book book = bookRepository.findById(bookId).orElseThrow(BookIdNotValidException::new);
        book.setActivated(false);
        bookRepository.save(book);
    }

    /**
        This functions returns ID all books in database.
     */
    @Override
    public List<BookResponse> getAllBooks() {
        return bookRepository.findAll().stream().map(s -> BookResponse.builder().title(s.getTitle()).Id(s.getId()).numberOfPages(s.getNumberOfPages()).author(s.getAuthor()).build()).toList();
    }

    /**
        This function rakes customer ID and returns customer's loaned books.
        It may throw exception if customer id is invalid
     */
    @Override
    public List<BookResponse> getBooksByCustomerId(Long customerId) {
        List<LoanBook> list = loanBookRepository.findLoanBookByCustomerId(customerId);
        if (list.isEmpty()) {
            throw new CustomerNotInLoanBooksException();
        }
        return list.stream().map(this::converToBookResponse).toList();
    }

    private BookResponse converToBookResponse(LoanBook loanBook) {
        Book book = bookRepository.findById(loanBook.getBookId()).orElseThrow(BookIdNotValidException::new);
        return BookResponse.builder().title(book.getTitle()).author(book.getAuthor()).Id(book.getId()).numberOfPages(book.getNumberOfPages()).build();
    }


    /**
        This function returns all unrefreshed books
     */
    @Override
    public List<RefreashedBookResponse> getRefreashedBooks() {
        return bookRepository.findRefreshedBooks().stream().map(b -> RefreashedBookResponse.builder().id(b.getId()).title(b.getTitle()).amount(b.getAmount()).build()).toList();
    }
}
