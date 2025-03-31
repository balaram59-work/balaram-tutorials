package com.example;
import jakarta.persistence.*;
import org.hibernate.annotations.Fetch;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@EntityListeners(AuthorListener.class)
@Table(name = "authors")
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public List<Book> getBooks() {
        return books;
    }

    public void addBook (Book book){
        book.setAuthor(this); // without this... when we persist authors and books, the author_id in the book table will be null
        // responsibility of setting the author_id in the book table is on the author side.. what if it on book side ? it cna be on book side also
        // but the client program will be knowing the internal details... better to handle the responsibility as this is a composistion
        books.add(book);
    }

    private String name;

    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Book> books = new ArrayList<>();

}
