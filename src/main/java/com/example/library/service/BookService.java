package com.example.library.service;

import com.example.library.data.dto.mapper.BookMapper;
import com.example.library.data.dto.request.BookRequestDTO;
import com.example.library.data.dto.response.BookResponseDTO;
import com.example.library.data.entity.Book;
import com.example.library.exception.resource.book.NoBookFoundException;
import com.example.library.exception.resource.book.NoBooksFoundException;
import com.example.library.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepository bookRepository;

    public List<BookResponseDTO> findAll() {
        return Optional.of(bookRepository.findAll())
                .filter(list -> !list.isEmpty())
                .map(books -> convertToDTOList(books,BookMapper::toDTO))
                .orElseThrow(NoBooksFoundException::new);
    }

    public List<BookResponseDTO> findByAuthor(String author) {
        return Optional.of(bookRepository.findByAuthor(author))
                .filter(list -> !list.isEmpty())
                .map(books -> convertToDTOList(books, BookMapper::toDTO))
                .orElseThrow(NoBookFoundException::new);
    }

    public BookResponseDTO findById(Long id) {
        return bookRepository.findById(id)
                .map(BookMapper::toDTO)
                .orElseThrow(NoBooksFoundException::new);
    }

    public BookResponseDTO save(BookRequestDTO book) {
        return Optional.of(book)
                .map(BookMapper::toEntity)
                .map(bookRepository::save)
                .map(BookMapper::toDTO)
                .orElseThrow(IllegalArgumentException::new);
    }

    public BookResponseDTO update(Long id, BookRequestDTO bookDetails) {
        return bookRepository.findById(id)
                .map(book -> {
                    book.setName(bookDetails.name());
                    book.setIsbn(bookDetails.isbn());
                    book.setLanguage(bookDetails.language());
                    book.setAuthor(bookDetails.author());
                    book.setReleaseDate(bookDetails.releaseDate());
                    return bookRepository.save(book);
                })
                .map(BookMapper::toDTO)
                .orElseThrow(NoBookFoundException::new);
    }

    public void delete(Long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(NoBookFoundException::new);
        bookRepository.delete(book);
    }

    private <T, R> List<R> convertToDTOList(List<T> entity, Function<T, R> mapper) {
        return entity.stream()
                .map(mapper)
                .collect(Collectors.toList());
    }
}
