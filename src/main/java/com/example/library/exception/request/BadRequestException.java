package com.example.library.exception.request;

public abstract class BadRequestException extends RuntimeException {
    BadRequestException(String message) {
        super(message);
    }
}
