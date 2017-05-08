package com.book.app.controller;

import com.book.app.domain.Book;
import com.book.app.repository.BookRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/books")
public class BookController {
    private final BookRepository bookRepo;

    private static final Logger logger = LoggerFactory.getLogger(BookController.class);

    @Autowired
    public BookController(BookRepository bookRepo) {
        this.bookRepo = bookRepo;
    }

    @PostMapping(produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Book> addBook(@RequestBody Book b) {
        bookRepo.add(b);
        return new ResponseEntity<Book>(b, HttpStatus.CREATED);
    }

    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Book>> listBooks() {
        List<Book> list = bookRepo.list();
        HttpStatus status = bookRepo.list().isEmpty() ? HttpStatus.NO_CONTENT : HttpStatus.OK;
        return new ResponseEntity<>(list, status);
    }

    @GetMapping(value = "/{id}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Optional<Book>> getBook(@PathVariable String id) {
        Optional<Book> b = bookRepo.get(id);
        HttpStatus status = b.isPresent() ? HttpStatus.OK : HttpStatus.NOT_FOUND;
        Book book;

        try {
            book = b.get();
        } catch (NoSuchElementException e) {
            book = null;
        }

        return new ResponseEntity(book, status);
    }
}
