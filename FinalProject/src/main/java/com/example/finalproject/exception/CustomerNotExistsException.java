package com.example.finalproject.exception;

import com.example.finalproject.constants.ErrorCodes;

public class CustomerNotExistsException extends LibraryException{
    private static final String message = "Customer does not exist";
    public CustomerNotExistsException(){
        super(message, ErrorCodes.CUSTOMER_NOT_EXIST);
    }
}
