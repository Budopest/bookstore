package bookstoread;

import java.time.LocalDate;

public class Book implements Comparable<Book>{
    private final String title;
    private final String author;
    private final LocalDate publishedOn;
    private LocalDate startingDate;
    private LocalDate finishingDate;

    public Book(String title, String author, LocalDate publishedOn) {
        this.title = title;
        this.author = author;
        this.publishedOn = publishedOn;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public LocalDate getPublishedOn() {
        return publishedOn;
    }

    @Override
    public String toString() {
        // ctrl + alt + l for auto indention
        return "Book{" +
                "title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", publishedOn=" + publishedOn +
                ", start date=" + startingDate+
                ", finish date="+ finishingDate+
                '}';
    }

    @Override
    public int compareTo(Book that) {
        return this.title.compareTo(that.title);
    }

    public void finishedReadingOn(LocalDate finishingDate) {
        this.finishingDate = finishingDate;
    }

    public void startedReadingOn(LocalDate startingDate) {
        this.startingDate = startingDate;
    }

    public boolean isRead() {return startingDate != null && finishingDate != null; }

    public boolean isProgress() {return startingDate != null && finishingDate == null; }



}
