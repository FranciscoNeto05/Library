package com.example.library.data.dto.response;

import com.example.library.data.entity.Book;
import java.util.Date;

public record BookResponseDTO(
        String isbn,
        String name,
        String language,
        String author,
        Date releaseDate,
        Long loanCount,
        Boolean availability
) {
    public BookResponseDTO(Book book) {
        this(book.getIsbn(),book.getName(), book.getLanguage(), book.getAuthor(), book.getReleaseDate(), book.getLoanCount(), book.getAvailability());
    }
}
