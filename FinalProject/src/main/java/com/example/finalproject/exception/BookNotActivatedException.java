package com.example.finalproject.exception;

import com.example.finalproject.constants.ErrorCodes;

public class BookNotActivatedException extends LibraryException{
    private final static String message = "Book is not activated";
    public BookNotActivatedException(){
        super(message, ErrorCodes.BOOK_ALREADY_ACTIVATED);
    }
}
