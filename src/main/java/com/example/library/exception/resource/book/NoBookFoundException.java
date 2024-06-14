package com.example.library.exception.resource.book;

import com.example.library.exception.resource.ResourceNotFoundException;

public class NoBookFoundException extends ResourceNotFoundException {
    public NoBookFoundException() {
        super("Nenhum livro encontrado com o parâmetro forncecido");
    }
}
