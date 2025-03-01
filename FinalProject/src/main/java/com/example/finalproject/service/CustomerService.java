package com.example.finalproject.service;

import com.example.finalproject.model.request.BookRateRequest;
import com.example.finalproject.model.request.BookRequest;
import com.example.finalproject.model.request.CustomerRequest;

public interface CustomerService {
    void registerCustomer(CustomerRequest customerRequest);

    void reserveBook(Long aLong, BookRequest bookRequest);


    void returnBook(String customerId, BookRequest bookRequest);

    void rateBook(String customerId, BookRateRequest bookRateRequest);
}
