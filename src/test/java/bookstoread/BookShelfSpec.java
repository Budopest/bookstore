package bookstoread;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

public class BookShelfSpec {

    @Test
    public void shelfEmptyWhenNoBookAdded() throws Exception{
        /*
        * Test case is broken into three parts :
        * We set up data that our test case needs.
        * We call the unit being tested.
        * We perform assertions to verify if expected behavior is correct.
        * */
        BookShelf shelf = new BookShelf();
        List<String> books = shelf.getAllBooksInShelf();
        assertTrue(books.isEmpty(),()->"BookShelf should be empty");

    }

    @Test
    public void bookShelfContainsThreeBooksWhenThreeBooksAdded(){

        BookShelf shelf = new BookShelf();
        shelf.addBookToShelf("JAVA the complete refrence");
        shelf.addBookToShelf("JAVA unit testing with Junit5","MYSQL Fundementals");
        List<String> books = shelf.getAllBooksInShelf();
        assertEquals(3,books.size(),()->"There should be three books in the shelf");

    }
}
