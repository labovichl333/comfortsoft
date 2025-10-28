package com.example.comfortsoft.exeption.handler;

import com.example.comfortsoft.exeption.FileFormatException;
import com.example.comfortsoft.exeption.FileProcessingException;
import com.example.comfortsoft.exeption.InvalidInputException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({
            FileFormatException.class,
            FileProcessingException.class,
            InvalidInputException.class
    })
    public ResponseEntity<String> handleExceptionForBadRequestHttpStatus(Exception ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }
}
