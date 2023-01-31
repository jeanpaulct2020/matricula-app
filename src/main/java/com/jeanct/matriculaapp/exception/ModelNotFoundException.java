package com.jeanct.matriculaapp.exception;

import lombok.Data;

@Data
public class ModelNotFoundException extends RuntimeException{

    public ModelNotFoundException(String message){
        super(message);
    }
}
