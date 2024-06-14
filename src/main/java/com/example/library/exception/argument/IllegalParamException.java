package com.example.library.exception.argument;

public class IllegalParamException extends IllegalArgumentException {
    public IllegalParamException() {
        super("O Parametro fornecido é inválido");
    }
}
