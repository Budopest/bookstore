package bookstoread;

import org.junit.jupiter.api.*;

import static java.util.Collections.singletonList;
import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

public class BookShelfSpec {

    private BookShelf shelf;
    private Book effectiveJava;
    private Book codeComplete;
    private Book mythicalManMonth;
    private Book cleanCode;

    @BeforeEach
    public void init() throws Exception{
        shelf = new BookShelf();
        effectiveJava = new Book("Effective Java", "Joshua Bloch", LocalDate.of(2008, Month.MAY, 8));
        codeComplete = new Book("Code Complete", "Steve McConnel", LocalDate.of(2004, Month.JUNE, 9));
        mythicalManMonth = new Book("The Mythical Man-Month", "Frederick Phillips Brooks", LocalDate.of(1975, Month.JANUARY, 1));
        cleanCode = new Book("Clean Code", "Robert C. Martin", LocalDate.of(2008, Month.AUGUST, 1));
    }

    @Nested
    @DisplayName("is Empty")
    class IsEmpty{

        @Test
        public void shelfEmptyWhenNoBookAdded(){
            /*
             * Test case is broken into three parts :
             * We set up data that our test case needs.
             * We call the unit being tested.
             * We perform assertions to verify if expected behavior is correct.
             * */
            List<Book> books = shelf.getAllBooksInShelf();
            assertTrue(books.isEmpty(),()->"BookShelf should be empty");

        }
        @Test
        public void bookShelfEmptyWhenNoBokksAdded(){
            List<Book> books = shelf.getAllBooksInShelf();
            assertTrue(books.isEmpty(),()-> "Bookshelf should be empty");

        }
    }

    @Test
    public void bookShelfContainsThreeBooksWhenThreeBooksAdded(){
        shelf.addBooksToShelf(effectiveJava,codeComplete,mythicalManMonth);
        List<Book> books = shelf.getAllBooksInShelf();
        assertEquals(3,books.size(),()->"There should be three books in the shelf");

    }


    @Test
    public void booksListunmodifiable(){
        List<Book> books = shelf.getAllBooksInShelf();
        try{
        books.add(effectiveJava);
        fail(()->"Adding books to the list directly should be illegal");
        }
        catch (Exception e){
            assertTrue(e instanceof UnsupportedOperationException, () -> "Should throw UnsupportedOperationException.");
        }
    }

    @Test
    public void booksShelfArrangedByTitle(){
        shelf.addBooksToShelf(effectiveJava,mythicalManMonth,codeComplete);
        List<Book> books = shelf.arrange();
        assertEquals(Arrays.asList(codeComplete,effectiveJava,mythicalManMonth),books,
                ()->"Books should be arranged lexicographically by book title");
    }

    @Test
    public void booksShelfIsInInsertionOrderAfterCallingArrange(){
        shelf.addBooksToShelf(effectiveJava,mythicalManMonth,codeComplete);
        List<Book> books = shelf.arrange();
        books = shelf.getAllBooksInShelf();
        assertEquals(Arrays.asList(effectiveJava,mythicalManMonth,codeComplete),books,
                ()->"Books should be in insertion order even after arrange when getAllBooksInShelf is called");
    }

    @Disabled
    public void bokShelfArrangedByUserCriteriaOld(){
        shelf.addBooksToShelf(effectiveJava,mythicalManMonth,codeComplete);
        List<Book> books = shelf.arrange(Comparator.<Book>naturalOrder().reversed());
        assertEquals(Arrays.asList(mythicalManMonth,effectiveJava,codeComplete),books,
                ()->"Books should be arranged in reverse order according to the criteria");

    }

    @Test
    public void bokShelfArrangedByUserCriteria(){
        shelf.addBooksToShelf(effectiveJava,mythicalManMonth,codeComplete);
        Comparator<Book> reverseOrder = Comparator.<Book>naturalOrder().reversed();
        List<Book> books = shelf.arrange(reverseOrder);
        assertThat(books).isSortedAccordingTo(reverseOrder);
    }

    @Test
    @DisplayName("books inside bookshelf are grouped by publication year")
    void groupBooksInsideBookShelfByPublicationYear() {
        shelf.addBooksToShelf(effectiveJava, codeComplete, mythicalManMonth, cleanCode);
        Map<Year, List<Book>> booksByPublicationYear = shelf.groupByPublicationYear();
        assertThat(booksByPublicationYear)
                .containsKey(Year.of(2008))
                .containsValues(Arrays.asList(effectiveJava, cleanCode));
        assertThat(booksByPublicationYear)
                .containsKey(Year.of(2004))
                .containsValues(singletonList(codeComplete));
        assertThat(booksByPublicationYear)
                .containsKey(Year.of(1975))
                .containsValues(singletonList(mythicalManMonth));
    }
    @Test
    @DisplayName("books inside bookshelf are grouped according to user provided criteria(group by author name)")
    void groupBooksByUserProvidedCriteria() {
        shelf.addBooksToShelf(effectiveJava, codeComplete, mythicalManMonth, cleanCode);
        Map<String,List<Book>> booksByAuthor = shelf.groupBy(Book::getAuthor);
        assertThat(booksByAuthor)
                .containsKey("Joshua Bloch")
                .containsValues(singletonList(effectiveJava));

    }


}
