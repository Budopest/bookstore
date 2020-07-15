package bookstoread;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.time.LocalDate;
import java.time.Month;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@DisplayName("Book Shelf Progress specifications")
@ExtendWith(BookParameterResolver.class)
public class BookShelfProgressSpecs {

    private BookShelf shelf;
    private Book effectiveJava;
    private Book codeComplete;
    private Book mythicalManMonth;
    private Book cleanCode;

    @BeforeEach
    public void init(Map<String,Book> books) throws Exception{
        shelf = new BookShelf();
        this.effectiveJava = books.get("Effective Java");
        this.codeComplete = books.get("Code Complete");
        this.mythicalManMonth = books.get("The Mythical Man-Month");
        this.cleanCode = books.get("Clean Code");
        shelf.addBooksToShelf(effectiveJava, codeComplete, mythicalManMonth, cleanCode);
    }


    @Test
    @DisplayName("progress 0% and 100% to read when no book are read")
    void progress100PercentUnread(){
        Progress progress = shelf.progress();
        assertAll(()->assertThat(progress.completed()).isEqualTo(0),
                ()->assertThat(progress.toRead()).isEqualTo(100));

    }

    @Test
    void progress50to50whenTwoBooksRead() {
        effectiveJava.startedReadingOn(LocalDate.of(2016, Month.JULY, 1));
        effectiveJava.finishedReadingOn(LocalDate.of(2016, Month.JULY, 31));
        cleanCode.startedReadingOn(LocalDate.of(2016, Month.AUGUST, 1));
        cleanCode.finishedReadingOn(LocalDate.of(2016, Month.AUGUST, 31));
        codeComplete.startedReadingOn(LocalDate.of(2020,Month.JULY,13));
        Progress progress = shelf.progress();
        assertAll(() -> assertThat(progress.completed()).isEqualTo(50),
                () -> assertThat(progress.toRead()).isEqualTo(25),
                ()-> assertThat(progress.inProgress()).isEqualTo(25)
        );

    }
}
