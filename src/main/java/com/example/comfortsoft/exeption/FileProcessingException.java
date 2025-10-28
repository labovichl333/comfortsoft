package com.example.comfortsoft.exeption;

public class FileProcessingException extends RuntimeException{
    public FileProcessingException(String message) {
        super(message);
    }
}
