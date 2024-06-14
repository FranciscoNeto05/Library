package com.example.library.repository;

import com.example.library.data.entity.Loan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface LoanRepository extends JpaRepository<Loan,Long> {

    @Query("SELECT l FROM Loan l WHERE l.status = :status")
    List<Loan> findByStatus(@Param("status") String status);

    @Query("SELECT l FROM Loan l WHERE l.startDate = :requestDate")
    List<Loan> findByDate(@Param("requestDate") Date requestDate);

    @Query("SELECT l FROM Loan l WHERE l.book.id = :bookId")
    List<Loan> findByBook(@Param("bookId") Long bookId);

    @Query("SELECT l FROM Loan l WHERE l.person.cpf = :personCpf")
    List<Loan> findByPerson(@Param("personCpf") String personCpf);
}
