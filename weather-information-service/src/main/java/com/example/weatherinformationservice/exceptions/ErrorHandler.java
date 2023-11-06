package com.example.weatherinformationservice.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import com.example.weatherinformationservice.utils.ErrorDto;
import com.example.weatherinformationservice.utils.ErrorResponseDto;
import jakarta.validation.ConstraintViolationException;

@ControllerAdvice
public class ErrorHandler extends ResponseEntityExceptionHandler{
    private static final Logger logger = LoggerFactory.getLogger(ErrorHandler.class);
    
    @ExceptionHandler(value = {CustomException.class})
    public ResponseEntity<ErrorResponseDto> handleBadRequestExceptions(CustomException e) {
        ErrorResponseDto errorResponseDto = new ErrorResponseDto();
        ErrorDto errorDto = new ErrorDto();
        errorDto.setCode(e.getCode());
        errorDto.setMessage(e.getMessage());
        errorDto.setStatus(e.getStatus());
        errorResponseDto.setSuccess(false);
        errorResponseDto.setError(errorDto);
        logger.error(errorDto.getMessage());
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
        logger.error(errorDto.getMessage());
        return new ResponseEntity<ErrorResponseDto>(errorResponseDto,HttpStatusCode.valueOf(e.getHttpCode()));
    }

    @ExceptionHandler(value = {NoRecordFoundException.class})
    public ResponseEntity<ErrorResponseDto> handleNoRecordFoundExceptions(NoRecordFoundException e) {
        ErrorResponseDto errorResponseDto = new ErrorResponseDto();
        ErrorDto errorDto = new ErrorDto();
        errorDto.setCode(e.getCode());
        errorDto.setMessage(e.getMessage());
        errorDto.setStatus(e.getStatus());
        errorResponseDto.setSuccess(false);
        errorResponseDto.setError(errorDto);
        logger.error(errorDto.getMessage());
        return new ResponseEntity<ErrorResponseDto>(errorResponseDto,HttpStatusCode.valueOf(e.getHttpCode()));
    }

    @ExceptionHandler(value = {ConstraintViolationException.class})
    public ResponseEntity<ErrorResponseDto> handlConstraintVoilationExceptions(ConstraintViolationException e, WebRequest request) {
        ErrorResponseDto errorResponseDto = new ErrorResponseDto();
        ErrorDto errorDto = new ErrorDto();
        errorDto.setCode(400003);
        errorDto.setStatus("BAD REQUEST");
        errorDto.setMessage("Parameter 'city' must be present");
        errorResponseDto.setSuccess(false);
        errorResponseDto.setError(errorDto);
        logger.error(errorDto.getMessage());
        return new ResponseEntity<ErrorResponseDto>(errorResponseDto,HttpStatusCode.valueOf(400));
    }

    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity<ErrorResponseDto> handleGenericExceptions(Exception e, WebRequest request) {
        ErrorResponseDto errorResponseDto = new ErrorResponseDto();
        ErrorDto errorDto = new ErrorDto();
        errorDto.setCode(500001);
        errorDto.setStatus("INTERNAL SERVER ERROR");
        errorDto.setMessage(e.getMessage());
        errorResponseDto.setSuccess(false);
        errorResponseDto.setError(errorDto);
        logger.error(errorDto.getMessage());
        return new ResponseEntity<ErrorResponseDto>(errorResponseDto,HttpStatusCode.valueOf(500));
    }
}
