package com.example.finalproject.exception;

import com.example.finalproject.constants.ErrorCodes;

public class ReservationNotExistsException extends LibraryException{
    private static final String message = "Reservation does not exists";
    public ReservationNotExistsException(){
        super(message, ErrorCodes.NOT_LOAN_BOOK);
    }
}
