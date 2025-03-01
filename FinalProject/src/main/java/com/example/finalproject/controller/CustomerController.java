package com.example.finalproject.controller;

import com.example.finalproject.model.request.BookRateRequest;
import com.example.finalproject.model.request.BookRequest;
import com.example.finalproject.model.request.CustomerRequest;
import com.example.finalproject.model.response.BookIdResponse;
import com.example.finalproject.model.response.RefreashedBookResponse;
import com.example.finalproject.model.response.VoidRegisterResponse;
import com.example.finalproject.service.BookService;
import com.example.finalproject.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;
    private final BookService bookService;

    /**
        Gets All Refreshed Books. It calls CustomerService were logic is implemented
     */
    @GetMapping("books")
    public ResponseEntity<List<RefreashedBookResponse>> getBooks(){
        return new ResponseEntity<>(bookService.getRefreashedBooks(), HttpStatus.OK);
    }

    /**
        Reserves Book. It calls CustomerService were logic is implemented
     */
    @PostMapping("{customerId}/reserve")
    public ResponseEntity<BookIdResponse> reserveBook(@PathVariable String customerId, @RequestBody BookRequest bookRequest){
        customerService.reserveBook(Long.valueOf(customerId), bookRequest);
        return new ResponseEntity<>(new BookIdResponse(Long.valueOf(bookRequest.getBookId())), HttpStatus.OK);
    }

    /**
        Returns Book. It calls CustomerService were logic is implemented
     */
    @PutMapping("{customerId}/return")
    public ResponseEntity<BookIdResponse> returnBook(@PathVariable String customerId, @RequestBody BookRequest bookRequest){
        customerService.returnBook(customerId, bookRequest);
        return new ResponseEntity<>(new BookIdResponse(Long.valueOf(bookRequest.getBookId())), HttpStatus.OK);

    }

    /**
        Rates Book. It calls CustomerService were logic is implemented
     */
    @PutMapping("{customerId}/rating")
    public ResponseEntity<BookIdResponse> rateBook(@PathVariable String customerId, @RequestBody BookRateRequest bookRateRequest){
        customerService.rateBook(customerId, bookRateRequest);
        return new ResponseEntity<>(new BookIdResponse(Long.valueOf(bookRateRequest.getBookId())), HttpStatus.OK);
    }

    /**
        Registers Customer. It calls CustomerService were logic is implemented
     */
    @PostMapping("registration")
    public ResponseEntity<VoidRegisterResponse> registerCustomer(@RequestBody CustomerRequest customerRequest){
        customerService.registerCustomer(customerRequest);
        return new ResponseEntity<>(new VoidRegisterResponse(customerRequest.getFirstName()), HttpStatus.OK);

    }
}
