package com.lemanski.SmartFarm.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class NotFoundAnimalException extends RuntimeException {
    public NotFoundAnimalException(String message) {
        super(message);
    }
}
