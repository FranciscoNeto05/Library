package com.example.library.exception.resource.loan;

import com.example.library.exception.resource.ResourceNotFoundException;

public class NoLoansFoundException extends ResourceNotFoundException {
    public NoLoansFoundException() {
        super("Nenhum empréstimo encontrado no banco de dados");
    }
}
