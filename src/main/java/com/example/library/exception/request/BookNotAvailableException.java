package com.example.library.exception.request;

public class BookNotAvailableException extends BadRequestException {
    public BookNotAvailableException() {
        super("O Livro solicitado não está disponível para empréstimo !");
    }
}
