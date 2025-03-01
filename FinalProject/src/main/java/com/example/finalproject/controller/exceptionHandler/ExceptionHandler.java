package com.example.finalproject.controller.exceptionHandler;


import com.example.finalproject.constants.ErrorCodes;
import com.example.finalproject.exception.LibraryException;
import com.example.finalproject.model.response.ErrorJson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;

import java.io.IOException;
import java.sql.SQLException;

@Slf4j
@ControllerAdvice
public class ExceptionHandler {
    /**
        Handles LibraryException sub-class exceptions. It shows us what error was
     */
    @org.springframework.web.bind.annotation.ExceptionHandler(LibraryException.class)
    public ResponseEntity<ErrorJson> handle(LibraryException ex) {
        log.error("Error during processing request", ex);
        ErrorJson errorJson = new ErrorJson();
        errorJson.setErrorCode(ex.getErrorCode());
        errorJson.setErrorDescription(ex.getMessage());
        return ResponseEntity.badRequest().body(errorJson);
    }

    /**
        Handles non-LibraryException exceptions. It shows us what error was
     */
    @org.springframework.web.bind.annotation.ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorJson> handle(Exception ex) {
        log.error("Error during processing request", ex);
        ErrorJson errorJson = new ErrorJson();
        errorJson.setErrorCode(getErrorCodeForException(ex));
        errorJson.setErrorDescription(ex.getMessage());
        return ResponseEntity.badRequest().body(errorJson);
    }

    /**
        Gives us ErrorCodes
     */
    private int getErrorCodeForException(Exception ex) {
        if (ex instanceof IllegalArgumentException) {
            return ErrorCodes.INVALID_ARGUMENT;
        } else if (ex instanceof NullPointerException) {
            return ErrorCodes.NULL_POINTER;
        } else if (ex instanceof IllegalStateException) {
            return ErrorCodes.ILLEGAL_STATE;
        } else if (ex instanceof IOException) {
            return ErrorCodes.IO_ERROR;
        } else if (ex instanceof SQLException) {
            return ErrorCodes.DATABASE_ERROR;
        } else if (ex instanceof HttpMessageNotReadableException) {
            return ErrorCodes.INVALID_REQUEST_BODY;
        } else if (ex instanceof MethodArgumentNotValidException) {
            return ErrorCodes.VALIDATION_ERROR;
        }
        return ErrorCodes.UNKNOWN_ERROR;
    }
}
