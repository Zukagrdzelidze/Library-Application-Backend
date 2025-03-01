package com.example.finalproject.service.implementations;

import com.example.finalproject.constants.Rating;
import com.example.finalproject.constants.Status;
import com.example.finalproject.domain.Book;
import com.example.finalproject.domain.Customer;
import com.example.finalproject.domain.LoanBook;
import com.example.finalproject.exception.*;
import com.example.finalproject.model.request.BookRateRequest;
import com.example.finalproject.model.request.BookRequest;
import com.example.finalproject.model.request.CustomerRequest;
import com.example.finalproject.repository.BookRepository;
import com.example.finalproject.repository.CustomerRepository;
import com.example.finalproject.repository.LoanBookRepository;
import com.example.finalproject.service.CustomerService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final BookRepository bookRepository;
    private final LoanBookRepository loanBookRepository;

    /**
        Takes Request(name, userIdentification and etc.) and registers customer
     */
    @Override
    public void registerCustomer(CustomerRequest customerRequest) {
        if(!customerRepository.existsByUserIdentification(customerRequest.getUserIdentification())){
            customerRepository.save(Customer.builder()
                    .firstName(customerRequest.getFirstName())
                    .lastName(customerRequest.getLastName())
                    .email(customerRequest.getEmail())
                    .phone(customerRequest.getPhone()).userIdentification(customerRequest.getUserIdentification())
                    .build());
        }else{
            throw new CustomerAlreadyExists(customerRequest.getUserIdentification());
        }
    }

    /**
        Reserves Book. We need transactional annotation because we change 2 things in database
        Firstly We Create LoanBook Object in Database =, also we need to change amount of
        books. It may throw several exceptions
     */
    @Transactional
    @Override
    public void reserveBook(Long customerId, BookRequest bookRequest) {
        if(!customerRepository.existsById(customerId)){
            throw new CustomerNotExistsException();
        }
        Long bookId = Long.valueOf(bookRequest.getBookId());
        Book book = bookRepository.findById(bookId).orElseThrow(BookIdNotValidException::new);
        if(!book.isRefreshed()){
            throw new BookNotRefreshedException();
        }
        if(book.getAmount() <= 0){
            throw new NoMoreBooksAvailableException();
        }else if(!book.isActivated()){
            throw new BookNotActivatedException();
        }
        book.setAmount(book.getAmount() - 1);
        bookRepository.save(book);
        loanBookRepository.save(LoanBook.builder().bookId(bookId).customerId(customerId).status(Status.LOANED).build());
    }

    /**
        Returns Book and It is also is transactional because we change 2 things in database
        Firstly We need to change LoanBook object to Returned also we need to change amount
        of books. It can throw several exceptions
     */
    @Transactional
    @Override
    public void returnBook(String customerId, BookRequest bookRequest) {
        Long bookId = Long.valueOf(bookRequest.getBookId());
        Book book = bookRepository.findById(bookId).orElseThrow(BookIdNotValidException::new);
        book.setAmount(book.getAmount() + 1);
        bookRepository.save(book);
        LoanBook loanBook = loanBookRepository.findByBookIdAndCustomerId(bookId, Long.valueOf(customerId)).orElseThrow(ReservationNotExistsException::new);
        if(!Status.LOANED.equals(loanBook.getStatus())){
            throw new BookIsNotLoanedException();
        }
        loanBook.setStatus(Status.RETURNED);
        loanBookRepository.save(loanBook);
    }

    /**
        Adds Rating in the LoanBook database
     */
    @Override
    public void rateBook(String customerId, BookRateRequest bookRateRequest) {
        if(!customerRepository.existsById(Long.valueOf(customerId))){
            throw new CustomerNotExistsException();
        }
        if(bookRateRequest.getRating() > Rating.MAX || bookRateRequest.getRating() < Rating.MIN){
            throw new RatingOutOfBoundsException();
        }
        LoanBook loanBook = loanBookRepository.findByBookIdAndCustomerId(Long.valueOf(bookRateRequest.getBookId()), Long.valueOf(customerId)).orElseThrow(ReservationNotExistsException::new);
        if(loanBook.getStatus().equals(Status.RETURNED)){
            loanBook.setRating(bookRateRequest.getRating());
            loanBookRepository.save(loanBook);
        }else{
            throw new BookNotReturnedException();
        }
    }
}
