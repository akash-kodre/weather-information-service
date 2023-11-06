package com.example.weatherinformationservice.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NoRecordFoundException  extends RuntimeException{
    
    private String status;    

    private int code;

    private String message;

    private int httpCode;

    public NoRecordFoundException(String status, int code, String message, int httpCode) {
        this.status = status;
        this.code = code;
        this.message = message;
        this.httpCode = httpCode;
    } 
}
