package bookstoread;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

public class BookShelfSpec {

    private BookShelf shelf;
    @BeforeEach
    public void init() throws Exception{
        shelf = new BookShelf();
    }

    @Test
    public void shelfEmptyWhenNoBookAdded(){
        /*
        * Test case is broken into three parts :
        * We set up data that our test case needs.
        * We call the unit being tested.
        * We perform assertions to verify if expected behavior is correct.
        * */
        List<String> books = shelf.getAllBooksInShelf();
        assertTrue(books.isEmpty(),()->"BookShelf should be empty");

    }

    @Test
    public void bookShelfContainsThreeBooksWhenThreeBooksAdded(){
        shelf.addBooksToShelf("JAVA the complete refrence");
        shelf.addBooksToShelf("JAVA unit testing with Junit5","MYSQL Fundementals");
        List<String> books = shelf.getAllBooksInShelf();
        assertEquals(3,books.size(),()->"There should be three books in the shelf");

    }

    @Test
    public void bookShelfEmptyWhenNoBokksAdded(){
        List<String> books = shelf.getAllBooksInShelf();
        assertTrue(books.isEmpty(),()-> "Bookshelf should be empty");

    }

    @Test
    public void booksListunmodifiable(){
        List<String> books = shelf.getAllBooksInShelf();
        try{
        books.add("12 Rules for life");
        fail(()->"Adding books to the list directly should be illegal");
        }
        catch (Exception e){
            assertTrue(e instanceof UnsupportedOperationException, () -> "Should throw UnsupportedOperationException.");
        }
    }

    @Test
    public void booksShelfArrangedByTitle(){
        shelf.addBooksToShelf("JAVA unit testing with Junit5","MYSQL Fundementals","JAVA the complete refrence");
        List<String> books = shelf.arrangeBooksByTitle();
        assertEquals(Arrays.asList("JAVA the complete refrence","JAVA unit testing with Junit5","MYSQL Fundementals"),books,
                ()->"Books should be arranged lexicographically by book title");
    }

    @Test
    public void booksShelfIsInInsertionOrderAfterCallingArrange(){
        shelf.addBooksToShelf("JAVA unit testing with Junit5","MYSQL Fundementals","JAVA the complete refrence");
        List<String> books = shelf.arrangeBooksByTitle();
        books = shelf.getAllBooksInShelf();
        assertEquals(Arrays.asList("JAVA unit testing with Junit5","MYSQL Fundementals","JAVA the complete refrence"),books,
                ()->"Books should be in insertion order even after arrange when getAllBooksInShelf is called");
    }


}
