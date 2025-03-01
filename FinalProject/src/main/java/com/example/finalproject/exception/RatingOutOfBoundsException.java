package com.example.finalproject.exception;

import com.example.finalproject.constants.ErrorCodes;

public class RatingOutOfBoundsException extends LibraryException{
    private final static String message = "Rating is Out of bounds";
    public RatingOutOfBoundsException(){
        super(message, ErrorCodes.RATING_INVALID);
    }
}
