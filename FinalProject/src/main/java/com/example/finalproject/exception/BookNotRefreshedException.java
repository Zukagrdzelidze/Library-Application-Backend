package com.example.finalproject.exception;

import com.example.finalproject.constants.ErrorCodes;

public class BookNotRefreshedException extends LibraryException{
    private static final String message = "Book is not refreshed";
    public BookNotRefreshedException(){
        super(message, ErrorCodes.BOOK_NOT_REFRESHED);
    }
}
