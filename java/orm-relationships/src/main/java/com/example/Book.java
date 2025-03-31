package com.example;

import jakarta.persistence.*;

@Entity

public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    private String title;

    @ManyToOne
    @JoinColumn(name = "author_id") // Foreign key in com.example.Book table
    private Author author;


    // Getters and Setters
}