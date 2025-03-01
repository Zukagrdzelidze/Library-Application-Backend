package com.example.finalproject.exception;

import com.example.finalproject.constants.ErrorCodes;

public class NoMoreBooksAvailableException extends LibraryException{
    private final static String message = "Book is not available for reservation";
    public NoMoreBooksAvailableException() {
        super(message, ErrorCodes.NO_MORE_BOOK);
    }
}
