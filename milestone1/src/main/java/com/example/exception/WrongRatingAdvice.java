package com.example.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class WrongRatingAdvice {
    @ResponseBody
    @ExceptionHandler(WrongRatingException.class)
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    public String WrongRatingHandler(WrongRatingException ex){
        return ex.getMessage();
    }
}
