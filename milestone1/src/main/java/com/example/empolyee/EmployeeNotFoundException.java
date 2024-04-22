package com.example.empolyee;

public class EmployeeNotFoundException extends RuntimeException{
    public EmployeeNotFoundException(Long id){
        super("Could not found empolyee" + id );
    }
}