package com.example.library.data.dto.response;

import com.example.library.data.entity.Loan;
import java.util.Date;

public record LoanResponseDTO(
        Long id,
        Long bookId,
        String personCpf,
        Date startDate,
        Date endDate,
        String status
) {
    public LoanResponseDTO (Loan loan){
        this(loan.getId(), loan.getBook().getId(), loan.getPerson().getCpf(), loan.getStartDate(), loan.getEndDate(), loan.getStatus());
    }
}
