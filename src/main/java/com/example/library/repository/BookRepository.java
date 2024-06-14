package com.example.library.repository;

import com.example.library.data.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BookRepository extends JpaRepository<Book,Long> {
    @Query("SELECT l FROM Book l WHERE l.author = :author")
    List<Book> findByAuthor(@Param("author") String author);
}
