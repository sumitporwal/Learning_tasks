package com.example.SpringBoot_React_Authentication.Exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ValidationException extends RuntimeException {

    public ValidationException(String message){
        super(message);
    }

    public ValidationException(String message,Throwable cause){
        super(message,cause);
    }
}
