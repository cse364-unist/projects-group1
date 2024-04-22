package com.example.exception;

public class MovieNotFoundException extends RuntimeException{
    public MovieNotFoundException(int id){
        super("Could not found movie" + id);
    }
}
