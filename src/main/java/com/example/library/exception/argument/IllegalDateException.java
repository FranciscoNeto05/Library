package com.example.library.exception.argument;

public class IllegalDateException extends IllegalArgumentException {
    public IllegalDateException() {
        super("A data informada é inválida ou não faz sentido");
    }
}
