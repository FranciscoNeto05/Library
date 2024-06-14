package com.example.library.exception.resource.person;

import com.example.library.exception.resource.ResourceNotFoundException;

public class NoPersonsFoundException extends ResourceNotFoundException {
    public NoPersonsFoundException() {
        super("Nenhuma pessoa econtrada no banco de dados");
    }
}
