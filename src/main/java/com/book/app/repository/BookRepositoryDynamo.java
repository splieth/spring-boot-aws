package com.book.app.repository;

import com.book.app.domain.Book;

import java.util.List;
import java.util.Optional;

public class BookRepositoryDynamo implements BookRepository {
    @Override
    public Book insert(Book b) {
        return null;
    }

    @Override
    public List<Book> list() {
        return null;
    }

    @Override
    public Optional<Book> findOne(String id) {
        return null;
    }

    @Override
    public void delete(String id) {

    }

    @Override
    public void deleteAll() {

    }
}
