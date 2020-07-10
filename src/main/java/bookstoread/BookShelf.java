package bookstoread;

import java.util.*;

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

    public List<String> arrangeBooksByTitle() {
        List<String> booksArrangerdByTitle = new ArrayList<>(books);
        booksArrangerdByTitle.sort(Comparator.naturalOrder());
        return Collections.unmodifiableList(booksArrangerdByTitle);
    }
}
