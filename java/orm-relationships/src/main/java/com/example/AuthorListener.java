package com.example;

import jakarta.persistence.PostLoad;

import jakarta.persistence.PostPersist;
import jakarta.persistence.PrePersist;

public class AuthorListener {
    @PrePersist
    public void prePersist(Author author){
        System.out.println("AuthorListener.prePersist for author: " + author.getName());
    }
    @PostLoad
    public void postLoad(Author author){
        System.out.println("AuthorListener.postLoad for author: " + author.getName());
    }
    @PostPersist
    public void postPersist(Author author){
        System.out.println("AuthorListener.postPersist for author: " + author.getName());
    }
}
