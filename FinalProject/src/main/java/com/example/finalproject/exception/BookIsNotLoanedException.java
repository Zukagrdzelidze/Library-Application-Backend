package com.example.finalproject.exception;

import com.example.finalproject.constants.ErrorCodes;

public class BookIsNotLoanedException extends LibraryException{
    private final static String message = "Book is not loaned";
    public BookIsNotLoanedException() {
        super(message, ErrorCodes.BOOK_NOT_LOANED);
    }
}
