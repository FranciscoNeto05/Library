package com.example.library.exception.resource.person;

import com.example.library.exception.resource.ResourceNotFoundException;

public class NoPersonFoundException extends ResourceNotFoundException {
    public NoPersonFoundException() {
        super("Nenhuma pessoa econtrada com o parâmetro fornecidos");
    }
}
