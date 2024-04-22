package com.example.exception;

public class WrongRatingException extends RuntimeException{
    public WrongRatingException(double avg){
        super("this" + avg + " avg is wrong Range");
    }

}
