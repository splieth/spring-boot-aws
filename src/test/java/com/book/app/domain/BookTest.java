package com.book.app.domain;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import org.junit.Test;

public class BookTest {
    public void equalsContract() throws Exception {
        Book someBook = new Book("someTitle", "someAuthor", 9.99);
        Book someOtherBook = new Book("someDifferentTitle", "someDifferentAuthor", 9.99);
        Book sameBook = someBook;

        assertTrue(someBook.equals(sameBook));
        assertFalse(someBook.equals(someOtherBook));
    }
}
