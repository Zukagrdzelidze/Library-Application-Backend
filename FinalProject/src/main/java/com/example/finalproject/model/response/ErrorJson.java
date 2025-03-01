package com.example.finalproject.model.response;

import lombok.Data;

@Data
public class ErrorJson {
    private int errorCode;
    private String errorDescription;
}
