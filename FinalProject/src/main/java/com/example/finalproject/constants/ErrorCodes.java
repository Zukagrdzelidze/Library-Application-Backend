package com.example.finalproject.constants;

public interface ErrorCodes {
    int CUSTOMER_EXISTS = -9999;
    int NO_MORE_BOOK = -9998;
    int NOT_VALID_BOOK = -9997;
    int NOT_LOAN_BOOK = -9996;
    int NOT_RETURNED_BOOK = -9995;
    int BOOK_ALREADY_EXISTS = -9994;
    int BOOK_NOT_IN_API = -9993;
    int WRONG_JSON_OBJECT_NAME = -9992;
    int WRONG_API_LINK = -9991;
    int CUSTOMER_NOT_IN_RESERVATIONS = -9990;
    int BOOK_ALREADY_REFRESHED = -9989;
    int CUSTOMER_NOT_EXIST = -9988;
    int BOOK_ALREADY_ACTIVATED = -9987;
    int RATING_INVALID = -9986;
    int BOOK_NOT_REFRESHED = -9985;
    int BOOK_NOT_LOANED = -9984;

    int INVALID_ARGUMENT = 1001;
    int NULL_POINTER = 1002;
    int ILLEGAL_STATE = 1003;
    int IO_ERROR = 2001;
    int DATABASE_ERROR = 3001;
    int INVALID_REQUEST_BODY = 4001;
    int VALIDATION_ERROR = 4002;
    int UNKNOWN_ERROR = 9999;
}
