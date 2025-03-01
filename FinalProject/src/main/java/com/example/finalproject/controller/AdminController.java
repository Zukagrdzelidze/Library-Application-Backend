package com.example.finalproject.controller;

import com.example.finalproject.model.request.BookRegisterRequest;
import com.example.finalproject.model.response.BookIdResponse;
import com.example.finalproject.model.response.BookResponse;
import com.example.finalproject.model.response.VoidRegisterResponse;
import com.example.finalproject.service.BookService;
import com.example.finalproject.service.BookRefreshService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {
    private final BookService bookService;
    private final BookRefreshService bookRefreshService;

    /**
        BookRegister Function. It calls bookService where function is implemented
     */
    @PostMapping("/bookRegister")
    public ResponseEntity<VoidRegisterResponse> registerBook(@RequestBody BookRegisterRequest bookRegisterRequest) {
        bookService.registerBook(bookRegisterRequest);
        return new ResponseEntity<>(new VoidRegisterResponse(bookRegisterRequest.getTitle()), HttpStatus.CREATED);
    }

    /**
        Activates Book. It calls bookService where function is implemented
     */
    @PutMapping("/activate/{bookId}")
    public ResponseEntity<BookIdResponse> activateBook(@PathVariable String bookId) {
        bookService.activateBook(Long.valueOf(bookId));
        return new ResponseEntity<>(new BookIdResponse(Long.valueOf(bookId)), HttpStatus.OK);

    }

    /**
        Deactivates Book. It calls bookService where function is implemented
     */
    @PutMapping("/deactivate/{bookId}")
    public ResponseEntity<BookIdResponse> deactivateBook(@PathVariable String bookId) {
        bookService.deactivateBook(Long.valueOf(bookId));
        return new ResponseEntity<>(new BookIdResponse(Long.valueOf(bookId)), HttpStatus.OK);
    }

    /**
        Gets all Books. It calls bookService where function is implemented
     */
    @GetMapping("/books")
    public ResponseEntity<List<BookResponse>> getAllBooks(){
        return new ResponseEntity<>(bookService.getAllBooks(), HttpStatus.OK);
    }

    /**
        Takes Customer ID and lists books for each id. It calls bookService where function is implemented
     */
    @GetMapping("/books/{customerId}")
    public ResponseEntity<List<BookResponse>> getBooksByCustomerId(@PathVariable String customerId){
        return new ResponseEntity<>(bookService.getBooksByCustomerId(Long.valueOf(customerId)), HttpStatus.OK);
    }

    /**
        Refreshes Book. It calls bookService where function is implemented
     */
    @PutMapping("refresh/{id}")
    public ResponseEntity<BookIdResponse> refreshBook(@PathVariable String id) {
        bookRefreshService.refreshBook(Long.valueOf(id));
        return new ResponseEntity<>(new BookIdResponse(Long.valueOf(id)), HttpStatus.OK);
    }
}
