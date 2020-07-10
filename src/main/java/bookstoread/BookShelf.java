package bookstoread;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BookShelf {

    private ArrayList<String> books = null;

    public BookShelf(){
        books = new ArrayList<String>();
    }
    List<String> getAllBooksInShelf(){
        return books;
    }
    public void addBookToShelf(String... bookNames){
        for(String bookName:bookNames){
            books.add(bookName);
        }
    }

}
