package com.example.library.data.dto.request;

import com.example.library.data.entity.Book;
import java.util.Date;

public record BookRequestDTO(
        Long id,
        String isbn,
        String name,
        String language,
        String author,
        Date releaseDate,
        Long loanCount,
        Boolean availability
) {
    public BookRequestDTO(Book book) {
        this(book.getId(), book.getIsbn(),book.getName(), book.getLanguage(), book.getAuthor(), book.getReleaseDate(), book.getLoanCount(), book.getAvailability());
    }
}
