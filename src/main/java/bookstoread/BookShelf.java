package bookstoread;

import java.util.*;
import java.util.stream.Collectors;

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
}
