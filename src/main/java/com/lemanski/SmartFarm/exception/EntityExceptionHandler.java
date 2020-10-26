package com.lemanski.SmartFarm.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestController
@ControllerAdvice
public class EntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler
    public final ResponseEntity<CustomExceptionMessageResponse> handleExceptionMessage(CustomExceptionMessage ex, WebRequest request) {
        CustomExceptionMessageResponse exception = new CustomExceptionMessageResponse(ex.getMessage());
        return new ResponseEntity<>(exception, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public final ResponseEntity<CustomExceptionMessageResponse> handleNotFoundAnimalException(NotFoundAnimalException ex, WebRequest request) {
        CustomExceptionMessageResponse exception = new CustomExceptionMessageResponse(ex.getMessage());
        return new ResponseEntity<>(exception, HttpStatus.NOT_FOUND);
    }
}
