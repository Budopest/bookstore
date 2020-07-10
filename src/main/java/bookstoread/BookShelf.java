package bookstoread;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class BookShelf {

    private ArrayList<String> books = null;

    public BookShelf(){
        books = new ArrayList<String>();
    }
    List<String> getAllBooksInShelf(){
        return Collections.unmodifiableList(books);
    }
    public void addBooksToShelf(String... bookNames){

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

}
