package com.example.library.controller;

import com.example.library.data.dto.request.BookRequestDTO;
import com.example.library.data.dto.response.BookResponseDTO;
import com.example.library.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/book")
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;

    @GetMapping
    public ResponseEntity<List<BookResponseDTO>> getBook () {
        List<BookResponseDTO> books = bookService.findAll();
        return ResponseEntity.ok(books);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookResponseDTO> getBookById (@PathVariable Long id) {
        BookResponseDTO book = bookService.findById(id);
        return ResponseEntity.ok(book);
    }

    @GetMapping("/by-author")
    public ResponseEntity<List<BookResponseDTO>> getBooksByAuthor(@RequestParam String author) {
        List<BookResponseDTO> books = bookService.findByAuthor(author);
        return ResponseEntity.ok(books);
    }

    @PostMapping
    public ResponseEntity<BookResponseDTO> postBook (@RequestBody BookRequestDTO book) {
        BookResponseDTO savedBook = bookService.save(book);
        return ResponseEntity.ok(savedBook);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookResponseDTO> putBook (@PathVariable Long id, @RequestBody BookRequestDTO book) {
        BookResponseDTO updatedBook = bookService.update(id, book);
        return ResponseEntity.ok(updatedBook);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBook (@PathVariable Long id) {
        bookService.delete(id);
        return ResponseEntity.ok("Livro excluído com sucesso");
    }
}
