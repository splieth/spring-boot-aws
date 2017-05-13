package com.book.app.repository;

import com.book.app.domain.Book;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.testng.Assert.*;


public class BookRepositoryListTest {

    private BookRepositoryList bookRepo;

    @Test
    public void shouldInsert() throws Exception {
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
}
