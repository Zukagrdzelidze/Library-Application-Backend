package com.example.finalproject.exception;

import com.example.finalproject.constants.ErrorCodes;

public class WrongJSONObjectNames extends LibraryException{

    public WrongJSONObjectNames(String name){
        super("Object with name " +  name + " does not exist", ErrorCodes.WRONG_JSON_OBJECT_NAME);
    }
}
