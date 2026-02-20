package org.example.posbackend.exceptions;

public class CustomException extends RuntimeException{
    public CustomException(String message){
        super(message);
    }
}
