package com.book.app.repository;

import com.book.app.domain.Book;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class BookRepositoryList implements BookRepository {
    private final List<Book> bookList;
    private static final Logger logger = LoggerFactory.getLogger(BookRepositoryList.class);


    public BookRepositoryList() {
        bookList = new ArrayList<>();
    }

    @Override
    public Book add(Book b) {
        bookList.add(b);
        return b;
    }

    @Override
    public List<Book> list() {
        return bookList;
    }

    @Override
    public Optional<Book> get(String id) {
        Optional<Book> book = Optional.empty();
        for (Book b : bookList) {
            if (b.getId().equals(id)) {
                book = Optional.ofNullable(b);
            }
        }
        return book;
    }
}
