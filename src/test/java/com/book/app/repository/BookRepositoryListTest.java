package com.book.app.repository;

import com.book.app.domain.Book;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Optional;

import static org.testng.Assert.*;


public class BookRepositoryListTest {

    private BookRepositoryList bookRepo;
    private Book someBook;
    private Book someOtherBook;

    // Meta
    @BeforeClass
    public void setUp() {
        bookRepo = new BookRepositoryList();
        someBook = new Book("Some Author", "Some Title", 199.99);
        someOtherBook= new Book("Some Other Author", "Some Other Title", 199.99);
    }

    @AfterTest
    public void cleanUp() {
        bookRepo.deleteAll();
    }

    // insert
    @Test
    public void insertShouldInsert() throws Exception {
        Book someResponse = bookRepo.insert(someBook);

        assertEquals(someResponse.getAuthor(), someBook.getAuthor());
        assertEquals(someResponse.getTitle(), someBook.getTitle());
        assertEquals(someResponse.getPrice(), someBook.getPrice());
    }

    // findOne
    @Test
    public void findOnefindsObject() throws Exception {
        Optional<Book> result = bookRepo.findOne(bookRepo.insert(someBook).getId());

        assertTrue(result.isPresent());
        assertEquals(result.get(), someBook);
    }

    @Test
    public void findOnefindsNothing() throws Exception {
        Optional<Book> result = bookRepo.findOne("some-id");

        assertFalse(result.isPresent());
    }

    // list
    @Test
    public void listIsEmpty() {
        assertTrue(bookRepo.list().isEmpty());
    }

    @Test
    public void listContainsElements() {
        bookRepo.insert(someBook);
        bookRepo.insert(someOtherBook);

        assertFalse(bookRepo.list().isEmpty());
        assertTrue(bookRepo.list().size() == 2);
        assertEquals(bookRepo.list().get(0).getTitle(), "Some Title");
        assertEquals(bookRepo.list().get(1).getTitle(), "Some Strange Title");
    }

    // delete
    @Test
    public void deleteRemovesElementFromList() {
        bookRepo.insert(someBook);
        bookRepo.insert(someOtherBook);

        bookRepo.delete(someOtherBook.getId());

        assertEquals(bookRepo.list().size(), 1);
        assertEquals(someBook.getId(), bookRepo.list().get(0).getId());
    }

    // deleteAll
    public void deleteAllFlushesList() {
        bookRepo.insert(someBook);
        bookRepo.insert(someOtherBook);

        bookRepo.deleteAll();
        assertEquals(bookRepo.list().size(), 0);
    }
}
