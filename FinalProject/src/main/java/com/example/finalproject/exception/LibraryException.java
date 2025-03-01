package com.example.finalproject.exception;

public class LibraryException extends RuntimeException {
    private int errorCode;
    public LibraryException(String message, int errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public int getErrorCode() {
        return errorCode;
    }
}
