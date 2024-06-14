package com.example.library.data.dto.mapper;

import com.example.library.data.dto.response.LoanResponseDTO;
import com.example.library.data.entity.Loan;

public class LoanMapper {
    public static LoanResponseDTO toDTO(Loan loan) {
        if (loan == null) {
            return null;
        }
        return new LoanResponseDTO(
                loan.getId(),
                loan.getBook().getId(),
                loan.getPerson().getCpf(),
                loan.getStartDate(),
                loan.getEndDate(),
                loan.getStatus()
        );
    }
}
