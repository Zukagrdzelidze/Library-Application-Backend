package com.example.finalproject.exception;

import com.example.finalproject.constants.ErrorCodes;

public class BookNotReturnedException extends LibraryException{
    private final static String message = "Book is not returned";
    public BookNotReturnedException() {
        super(message, ErrorCodes.NOT_RETURNED_BOOK);
    }
}
