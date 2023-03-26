package com.spblue4422.divers.common.errors;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

//인터넷보고 따라 만들어봄.
@RestControllerAdvice
public class EntireExceptionHandler {
    @ExceptionHandler(Exception.class)
    protected ResponseEntity<ErrorResponse> handleException(Exception ex) {
        ErrorType error = ErrorType.INTERNAL_SERVER_ERROR;

        return new ResponseEntity<>(HttpStatusCode.valueOf(error.getStatus()));
    }
}
