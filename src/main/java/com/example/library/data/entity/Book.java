package com.example.library.data.entity;

import java.util.Date;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "book")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Book {
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Column(name = "isbn", nullable = false, unique = true)
    private String isbn;

    @Column(name = "name")
    private String name;

    @Column(name = "language")
    private String language;

    @Column(name = "author")
    private String author;

    @Column(name = "release_date")
    private Date releaseDate;

    @Column(name = "loan_count", columnDefinition = "BIGINT DEFAULT 0")
    private Long loanCount;

    @Column(name = "availability")
    private Boolean availability;

    @PrePersist
    protected void onCreate() {
        if (loanCount == null) {
            loanCount = 0L;
        }
    }
}
