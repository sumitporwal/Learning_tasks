package com.example.SpringBoot_Testing.Exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class NotFoundException extends RuntimeException{

    public NotFoundException(String message){
        super(message);
    }
}
