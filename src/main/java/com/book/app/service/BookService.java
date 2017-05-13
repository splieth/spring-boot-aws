package com.book.app.service;

import com.book.app.domain.Book;
import com.book.app.repository.BookRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/books")
public class BookService {
    private final BookRepository bookRepo;

    private static final Logger logger = LoggerFactory.getLogger(BookService.class);

    @Autowired
    public BookService(BookRepository bookRepo) {
        this.bookRepo = bookRepo;
    }

    @PostMapping(produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Book> addBook(@RequestBody Book b) {
        bookRepo.insert(b);
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
        Optional<Book> b = bookRepo.findOne(id);
        HttpStatus status = b.isPresent() ? HttpStatus.OK : HttpStatus.NOT_FOUND;

        logger.debug("Looking for %s; Got %s", id, status.toString());

        return new ResponseEntity(b.orElse(null), status);
    }
}
