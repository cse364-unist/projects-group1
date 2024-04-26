package com.example.exception;

public class QuizNotFoundException extends RuntimeException{
    public QuizNotFoundException(int id){
        super("Could not found quiz" + id);
    }
}
