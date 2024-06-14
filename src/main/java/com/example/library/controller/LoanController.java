package com.example.library.controller;

import com.example.library.data.dto.response.LoanResponseDTO;
import com.example.library.data.entity.Loan;
import com.example.library.service.LoanService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.TimeZone;

@RestController
@RequestMapping("/loan")
@RequiredArgsConstructor
public class LoanController {
    private final LoanService loanService;

    @GetMapping
    public ResponseEntity<List<LoanResponseDTO>> getAll () {
        List<LoanResponseDTO> loans = loanService.findAll();
        return ResponseEntity.ok(loans);
    }

    @GetMapping("/{id}")
    public ResponseEntity<LoanResponseDTO> getLoanById(@PathVariable Long id) {
        LoanResponseDTO loan = loanService.findById(id);
        return ResponseEntity.ok(loan);
    }

    @GetMapping("/date")
    public ResponseEntity<List<LoanResponseDTO>> getLoansByDate(@RequestParam("date") String requestDate) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
        System.out.println(sdf.parse(requestDate).toString());
        List<LoanResponseDTO> loans = loanService.findByDate(sdf.parse(requestDate));
        return ResponseEntity.ok(loans);
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<LoanResponseDTO>> getLoansByStatus(@PathVariable String status) {
        List<LoanResponseDTO> loans = loanService.findByStatus(status);
        return ResponseEntity.ok(loans);
    }

    @GetMapping("/person/{personCpf}")
    public ResponseEntity<List<LoanResponseDTO>> getLoansByPerson(@PathVariable String personCpf) {
        List<LoanResponseDTO> loans = loanService.findByPerson(personCpf);
        return ResponseEntity.ok(loans);
    }

    @GetMapping("/book/{bookId}")
    public ResponseEntity<List<LoanResponseDTO>> getLoansByBook(@PathVariable Long bookId) {
        List<LoanResponseDTO> loans = loanService.findByBook(bookId);
        return ResponseEntity.ok(loans);
    }

    @PostMapping
    public ResponseEntity<LoanResponseDTO> save(@RequestBody Loan loan) {
        LoanResponseDTO savedLoan = loanService.save(loan);
        return ResponseEntity.ok(savedLoan);
    }

    @PostMapping("/{id}/return")
    public ResponseEntity<LoanResponseDTO> returning(@PathVariable Long id) {
        LoanResponseDTO loan = loanService.returning(id);
        return ResponseEntity.ok(loan);
    }

    @PutMapping("/{id}")
    public ResponseEntity<LoanResponseDTO> put(@PathVariable Long id, @RequestBody Loan loan) {
        LoanResponseDTO updatedLoan = loanService.update(id,loan);
        return ResponseEntity.ok(updatedLoan);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        loanService.delete(id);
        return ResponseEntity.ok("Emprestimo excluído com sucesso!");
    }
}
