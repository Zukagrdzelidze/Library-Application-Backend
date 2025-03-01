package com.example.finalproject.service;

import com.example.finalproject.model.request.BookRegisterRequest;
import com.example.finalproject.model.response.BookIdResponse;
import com.example.finalproject.model.response.BookResponse;
import com.example.finalproject.model.response.RefreashedBookResponse;

import java.util.List;

public interface BookService {

    void registerBook(BookRegisterRequest bookRegisterRequest);

    void activateBook(Long bookId);

    void deactivateBook(Long bookId);

    List<BookResponse> getAllBooks();

    List<BookResponse> getBooksByCustomerId(Long customerId);


    List<RefreashedBookResponse> getRefreashedBooks();
}
