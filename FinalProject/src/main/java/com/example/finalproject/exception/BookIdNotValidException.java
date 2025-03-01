package com.example.finalproject.exception;

import com.example.finalproject.constants.ErrorCodes;

public class BookIdNotValidException extends LibraryException{
    private static final String message = "Book id is not valid";
    public BookIdNotValidException(){
        super(message, ErrorCodes.NOT_VALID_BOOK);
    }
}
