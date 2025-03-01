package com.example.finalproject.exception;

import com.example.finalproject.constants.ErrorCodes;

public class BookAlreadyExists extends LibraryException{
    private static final String message = "Book Already Exists in Library";
    public BookAlreadyExists() {
        super(message, ErrorCodes.BOOK_ALREADY_EXISTS);
    }
}
