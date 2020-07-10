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
        shelf.addBooksToShelf("JAVA the complete refrence");
        shelf.addBooksToShelf("JAVA unit testing with Junit5","MYSQL Fundementals");
        List<String> books = shelf.getAllBooksInShelf();
        assertEquals(3,books.size(),()->"There should be three books in the shelf");

    }

    @Test
    public void bookShelfEmptyWhenNoBokksAdded(){

        BookShelf shelf = new BookShelf();
        List<String> books = shelf.getAllBooksInShelf();
        assertTrue(books.isEmpty(),()-> "Bookshelf should be empty");

    }

    @Test
    public void booksListunmodifiable(){
        BookShelf shelf = new BookShelf();
        List<String> books = shelf.getAllBooksInShelf();
        try{
        books.add("12 Rules for life");
        fail(()->"Adding books to the list directly should be illegal");
        }
        catch (Exception e){
            assertTrue(e instanceof UnsupportedOperationException, () -> "Should throw UnsupportedOperationException.");
        }

    }
}
