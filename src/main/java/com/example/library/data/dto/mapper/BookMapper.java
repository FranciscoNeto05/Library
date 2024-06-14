package com.example.library.data.dto.mapper;

import com.example.library.data.dto.request.BookRequestDTO;
import com.example.library.data.dto.response.BookResponseDTO;
import com.example.library.data.entity.Book;
import com.example.library.exception.argument.IllegalParamException;

public class BookMapper {
    public static BookResponseDTO toDTO (final Book book) {
        if (book == null) {
            return null;
        }
        return new BookResponseDTO(
                book.getIsbn(),
                book.getName(),
                book.getLanguage(),
                book.getAuthor(),
                book.getReleaseDate(),
                book.getLoanCount(),
                book.getAvailability()
        );
    }

    public static Book toEntity (final BookRequestDTO bookRequestDTO) {
        if (bookRequestDTO == null) {
            throw new IllegalParamException();
        }
        return new Book(
                bookRequestDTO.id(),
                bookRequestDTO.isbn(),
                bookRequestDTO.name(),
                bookRequestDTO.language(),
                bookRequestDTO.author(),
                bookRequestDTO.releaseDate(),
                bookRequestDTO.loanCount(),
                bookRequestDTO.availability()
        );
    }
}