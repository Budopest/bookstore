package bookstoread;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
        List<String> books = shelf.books();
        assertTrue(books.isEmpty(),()->"BookShelf should be empty");

    }
}
