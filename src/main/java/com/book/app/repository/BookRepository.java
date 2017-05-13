package com.book.app.repository;

import com.book.app.domain.Book;

import java.util.List;
import java.util.Optional;

public interface BookRepository {
    Book insert(Book b);

    List<Book> list();

    Optional<Book> findOne(String id);
}
