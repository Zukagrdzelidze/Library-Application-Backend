package com.example.finalproject.exception;

import com.example.finalproject.constants.ErrorCodes;

public class CustomerAlreadyExists extends LibraryException {

    public CustomerAlreadyExists(String id){
        super("Customer with identification " + id  + " already exists", ErrorCodes.CUSTOMER_EXISTS);
    }

}


