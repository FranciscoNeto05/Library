package com.example.library.exception.resource.book;

import com.example.library.exception.resource.ResourceNotFoundException;

public class NoBooksFoundException extends ResourceNotFoundException {
    public NoBooksFoundException() {
        super("Nenhum livro encontrado no banco de dados");
    }
}
