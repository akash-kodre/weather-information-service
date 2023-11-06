package com.example.weatherinformationservice.utils;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorDto {
    private int code;
    private String message;
    private String status;
}
