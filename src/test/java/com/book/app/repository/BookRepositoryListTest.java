package com.book.app.repository;

import com.book.app.domain.Book;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Optional;

import static org.testng.Assert.*;


public class BookRepositoryListTest {

    private BookRepositoryList bookRepo;

    // insert
    @Test
    public void insertShouldInsert() throws Exception {
        bookRepo = new BookRepositoryList();
        String someAuthor = "Some Author";
        String someTitle = "Some Title";
        Double somePrice = 199.99;
        Book someInsert = new Book(someAuthor, someTitle, somePrice);
        Book someResponse = bookRepo.insert(someInsert);

        assertEquals(someResponse.getAuthor(), someAuthor);
        assertEquals(someResponse.getTitle(), someTitle);
        assertEquals(someResponse.getPrice(), somePrice);
    }

    // findOne
    @Test
    public void findOnefindsObject() throws Exception {
        bookRepo = new BookRepositoryList();
        String someAuthor = "Some Author";
        String someTitle = "Some Title";
        Double somePrice = 199.99;
        Book someBook = new Book(someAuthor, someTitle, somePrice);
        Optional<Book> result = bookRepo.findOne(bookRepo.insert(someBook).getId());

        assertTrue(result.isPresent());
        assertEquals(result.get(), someBook);
    }

    @Test
    public void findOnefindsNothing() throws Exception {
        bookRepo = new BookRepositoryList();
        Optional<Book> result = bookRepo.findOne("some-id");

        assertFalse(result.isPresent());
    }

    // list
    @Test
    public void listIsEmpty() {
        bookRepo = new BookRepositoryList();

        assertTrue(bookRepo.list().isEmpty());
    }

    @Test
    public void listContainsElements() {
        bookRepo = new BookRepositoryList();
        Book someBook = new Book("Some Author", "Some Title", 19.99);
        Book someOtherBook = new Book("Some Other Author", "Some Strange Title", 99.99);
        bookRepo.insert(someBook);
        bookRepo.insert(someOtherBook);

        assertFalse(bookRepo.list().isEmpty());
        assertTrue(bookRepo.list().size() == 2);
        assertEquals(bookRepo.list().get(0).getTitle(), "Some Title");
        assertEquals(bookRepo.list().get(1).getTitle(), "Some Strange Title");
    }
}
