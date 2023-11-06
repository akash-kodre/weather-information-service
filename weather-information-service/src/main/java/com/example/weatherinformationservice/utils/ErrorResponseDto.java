package com.example.weatherinformationservice.utils;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorResponseDto {
    private boolean success;
    private ErrorDto error;
}
