package com.book.app.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;
import java.util.UUID;

public class Book {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private final String id;

    private String author;

    private String title;

    private double price;

    public Book() {
        this.id = UUID.randomUUID().toString();
    }

    public Book(String author, String title, double price) {
        this.id = UUID.randomUUID().toString();
        this.author = author;
        this.title = title;
        this.price = price;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        return String.format("%s by %s now for %n", author, title, price);
    }

    @Override
    public boolean equals(Object o) {
        if(this == o) return true;
        if(!(o instanceof Book)) return false;

        Book b = (Book) o;

        if(!id.equals(b.getId())) return false;
        if(author != null ? !author.equals(b.author) : b.author != null) return false;
        if(title != null ? !title.equals(b.title) : b.title != null) return false;
        if(price != b.price) return false;

        return true;
    }
}
