package com.example.weatherinformationservice.exceptions;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.example.weatherinformationservice.utils.ErrorDto;
import com.example.weatherinformationservice.utils.ErrorResponseDto;

@ControllerAdvice
public class ErrorHandler extends ResponseEntityExceptionHandler{
    
    @ExceptionHandler(value = {CustomException.class})
    public ResponseEntity<ErrorResponseDto> handleBadRequestExceptions(CustomException e) {
        ErrorResponseDto errorResponseDto = new ErrorResponseDto();
        ErrorDto errorDto = new ErrorDto();
        errorDto.setCode(e.getCode());
        errorDto.setMessage(e.getMessage());
        errorDto.setStatus(e.getStatus());
        errorResponseDto.setSuccess(false);
        errorResponseDto.setError(errorDto);
        return new ResponseEntity<ErrorResponseDto>(errorResponseDto,HttpStatusCode.valueOf(e.getHttpCode()));
    }

    @ExceptionHandler(value = {InvalidInputException.class})
    public ResponseEntity<ErrorResponseDto> handleInvalidInputExceptions(InvalidInputException e) {
        ErrorResponseDto errorResponseDto = new ErrorResponseDto();
        ErrorDto errorDto = new ErrorDto();
        errorDto.setCode(e.getCode());
        errorDto.setMessage(e.getMessage());
        errorDto.setStatus(e.getStatus());
        errorResponseDto.setSuccess(false);
        errorResponseDto.setError(errorDto);
        return new ResponseEntity<ErrorResponseDto>(errorResponseDto,HttpStatusCode.valueOf(e.getHttpCode()));
    }

    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity<ErrorResponseDto> handleGenericExceptions(Exception e, WebRequest request) {
        ErrorResponseDto errorResponseDto = new ErrorResponseDto();
        ErrorDto errorDto = new ErrorDto();
        errorDto.setMessage(e.getMessage());
        errorResponseDto.setSuccess(false);
        errorResponseDto.setError(errorDto);
        return new ResponseEntity<ErrorResponseDto>(errorResponseDto,HttpStatusCode.valueOf(500));
    }
}
