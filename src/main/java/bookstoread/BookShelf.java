package bookstoread;

import java.time.Year;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;

public class BookShelf {

    private ArrayList<Book> books = null;

    public BookShelf(){
        books = new ArrayList<Book>();
    }
    List<Book> getAllBooksInShelf(){
        return Collections.unmodifiableList(books);
    }
    public void addBooksToShelf(Book... bookNames){

        Arrays.stream(bookNames).forEach(books::add);
        /*
        Replace lambda with method refrence
        Arrays.stream(bookNames).forEach(book -> books.add(book));
        */
        /*
        //replaced by lambda expression and streams
        for(String bookName:bookNames){
            books.add(bookName);
        }
        */
    }

    public List<Book> arrange(){
       return books.stream().sorted().collect(Collectors.toList());
    }
    public List<Book> arrange(Comparator<Book> criteria){
        return books.stream().sorted(criteria).collect(Collectors.toList());
    }
    @Deprecated
    public List<Book> arrangeBooksByTitle(Boolean revrseOrder) {
        List<Book> booksArrangerdByTitle = new ArrayList<>(books);
        if(!revrseOrder)
        booksArrangerdByTitle.sort(Comparator.naturalOrder());
        else
            booksArrangerdByTitle.sort(Comparator.<Book>naturalOrder().reversed());
        return Collections.unmodifiableList(booksArrangerdByTitle);
    }

    public Map<Year,List<Book>> groupByPublicationYear() {
        return books.stream().collect(groupingBy(book->Year.of(book.getPublishedOn().getYear())));
    }

    public <K> Map<K, List<Book>> groupBy(Function<Book, K> fx) {
    return books.stream().collect(groupingBy(fx));
    }

    public Progress progress() {
        if(books.size()==0) return new Progress(0,0,0);
        int booksRead = Long.valueOf(books.stream().filter(Book::isRead).count()).intValue();
        int booksInProgress = Long.valueOf(books.stream().filter(Book::isProgress).count()).intValue();
        int booksToRead = books.size()-booksRead-booksInProgress;
        int percentageCompleted = booksRead*100/books.size();
        int percentageToRead = booksToRead*100/books.size();
        int percentageInProgress = booksInProgress*100/books.size();
        return new Progress(percentageCompleted, percentageToRead, percentageInProgress);
    }
}
