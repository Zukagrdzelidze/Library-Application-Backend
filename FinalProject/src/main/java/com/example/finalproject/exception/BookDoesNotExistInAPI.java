package com.example.finalproject.exception;

import com.example.finalproject.constants.ErrorCodes;

public class BookDoesNotExistInAPI extends LibraryException{
    private static final String message = "Book Is not in API";
    public BookDoesNotExistInAPI() {
        super(message, ErrorCodes.BOOK_NOT_IN_API);
    }
}
