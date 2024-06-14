package com.example.library.exception.resource.loan;

import com.example.library.exception.resource.ResourceNotFoundException;

public class NoLoanFoundException extends ResourceNotFoundException {
    public NoLoanFoundException() {
        super("Nenhum empréstimo encontrado com o parâmetro fornecido");
    }
}
