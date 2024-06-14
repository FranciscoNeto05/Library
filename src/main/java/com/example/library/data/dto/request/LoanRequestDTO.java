package com.example.library.data.dto.request;

import com.example.library.data.entity.Loan;
import java.util.Date;

public record LoanRequestDTO (
        Long id,
        Long bookId,
        String personCpf,
        Date startDate,
        Date endDate,
        String status
) {
    public LoanRequestDTO (Loan loan) {
        this(loan.getId(), loan.getBook().getId(), loan.getPerson().getCpf(), loan.getStartDate(), loan.getEndDate(), loan.getStatus());
    }
}
