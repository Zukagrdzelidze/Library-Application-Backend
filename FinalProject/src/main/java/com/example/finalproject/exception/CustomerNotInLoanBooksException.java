package com.example.finalproject.exception;

import com.example.finalproject.constants.ErrorCodes;

public class CustomerNotInLoanBooksException extends LibraryException{
    private final static String message = "Customer with your ID does not exist in Reservations";
    public CustomerNotInLoanBooksException(){
        super(message, ErrorCodes.CUSTOMER_NOT_IN_RESERVATIONS);
    }
}
