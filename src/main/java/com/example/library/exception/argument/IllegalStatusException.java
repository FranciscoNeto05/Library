package com.example.library.exception.argument;

public class IllegalStatusException extends IllegalArgumentException {
    public IllegalStatusException() {
        super("O Status informado é inválido");
    }
}
