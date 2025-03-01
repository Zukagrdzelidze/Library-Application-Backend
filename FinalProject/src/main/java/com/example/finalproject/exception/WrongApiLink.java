package com.example.finalproject.exception;

import com.example.finalproject.constants.ErrorCodes;

public class WrongApiLink extends LibraryException{
    private static final String message ="URL You are trying to get data from is not valid";
    public WrongApiLink(){
        super(message, ErrorCodes.WRONG_API_LINK);
    }
}
