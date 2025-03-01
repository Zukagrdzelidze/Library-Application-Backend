package com.example.finalproject.exception;

import com.example.finalproject.constants.ErrorCodes;

public class AlreadyRefreshedException extends LibraryException{
    private static final String message = "Book is already refreshed";
    public AlreadyRefreshedException(){
        super(message, ErrorCodes.BOOK_ALREADY_REFRESHED);
    }
}
