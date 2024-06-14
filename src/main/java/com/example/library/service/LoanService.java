package com.example.library.service;

import com.example.library.data.dto.mapper.LoanMapper;
import com.example.library.data.dto.response.LoanResponseDTO;
import com.example.library.data.entity.Book;
import com.example.library.data.entity.Loan;
import com.example.library.exception.argument.IllegalDateException;
import com.example.library.exception.argument.IllegalStatusException;
import com.example.library.exception.request.BookNotAvailableException;
import com.example.library.exception.resource.book.NoBookFoundException;
import com.example.library.exception.resource.loan.NoLoanFoundException;
import com.example.library.exception.resource.loan.NoLoansFoundException;
import com.example.library.repository.BookRepository;
import com.example.library.repository.LoanRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LoanService {
    private final LoanRepository loanRepository;
    private final BookRepository bookRepository;

    public List<LoanResponseDTO> findAll() {
        return Optional.of(loanRepository.findAll())
                .filter(loans -> !loans.isEmpty())
                .map(loans -> convertToDTOList(loans,LoanMapper::toDTO))
                .orElseThrow(NoLoansFoundException::new);
    }

    public LoanResponseDTO findById(Long id) {
        return loanRepository.findById(id)
                .map(LoanMapper::toDTO)
                .orElseThrow(NoLoanFoundException::new);
    }

    public List<LoanResponseDTO> findByStatus(String status) {
        checkArgumentStatus(status);
        return Optional.of(loanRepository.findByStatus(status))
                .filter(loans -> !loans.isEmpty())
                .map(loans -> convertToDTOList(loans,LoanMapper::toDTO))
                .orElseThrow(NoLoansFoundException::new);
    }

    public List<LoanResponseDTO> findByDate (Date dateRequest) {
        checkArgumentDate(dateRequest);
        return Optional.of(loanRepository.findByDate(dateRequest))
                .filter(loans -> !loans.isEmpty())
                .map(loans -> convertToDTOList(loans,LoanMapper::toDTO))
                .orElseThrow(NoLoansFoundException::new);
    }

    public List<LoanResponseDTO> findByPerson (String personCpf) {
        return Optional.of(loanRepository.findByPerson(personCpf))
                .filter(loans -> !loans.isEmpty())
                .map(loans -> convertToDTOList(loans, LoanMapper::toDTO))
                .orElseThrow(IllegalArgumentException::new);
    }

    public List<LoanResponseDTO> findByBook (Long bookId) {
        return Optional.of(loanRepository.findByBook(bookId))
                .filter(loans -> !loans.isEmpty())
                .map(loans -> convertToDTOList(loans, LoanMapper::toDTO))
                .orElseThrow(IllegalArgumentException::new);
    }

    public LoanResponseDTO save(Loan loan) {
        loan = loanRepository.findById(loan.getId()).get();
        Loan finalLoan = loan;
        Loan savedLoan = Optional.of(loan.getBook())
                .filter(Book::getAvailability)
                .map(book -> loanRepository.save(finalLoan))
                .orElseThrow(BookNotAvailableException::new);
        return bookRepository.findById(savedLoan.getBook().getId())
                .map(book -> {
                    book.setLoanCount(book.getLoanCount() + 1);
                    book.setAvailability(false);
                    bookRepository.save(book);
                    return savedLoan;
                })
                .map(LoanMapper::toDTO)
                .orElseThrow(NoBookFoundException::new);
    }

    public LoanResponseDTO update (Long id, Loan loanDetails) {
        return loanRepository.findById(id)
                .map(loan -> {
                    loan.setStartDate(loanDetails.getStartDate());
                    loan.setEndDate(loanDetails.getEndDate());
                    loan.setStatus(loanDetails.getStatus());
                    return loanRepository.save(loan);
                })
                .map(LoanMapper::toDTO)
                .orElseThrow(NoLoanFoundException::new);
    }

    public LoanResponseDTO returning (Long id) {
        return loanRepository.findById(id)
                .map(loan -> {
                    loan.setStatus("devolvido");
                    loan.getBook().setAvailability(true);
                    Loan updatedLoan = loanRepository.save(loan);
                    bookRepository.save(updatedLoan.getBook());
                    return LoanMapper.toDTO(updatedLoan);
                })
                .orElseThrow(NoLoanFoundException::new);
    }

    public void delete (Long id) {
        Loan loan = loanRepository.findById(id)
                .orElseThrow(NoLoanFoundException::new);
        loanRepository.delete(loan);
    }

    private void checkArgumentStatus (String status) {
        if (!Arrays.asList("emprestado", "atrasado", "devolvido").contains(status)) {
            throw new IllegalStatusException();
        }
    }

    private void checkArgumentDate(Date date) {
        LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate today = LocalDate.now();
        if (localDate.isAfter(today)) {
            throw new IllegalDateException();
        }
    }

    private <T, R> List<R> convertToDTOList(List<T> entity, Function<T, R> mapper) {
        return entity.stream()
                .map(mapper)
                .collect(Collectors.toList());
    }
}
