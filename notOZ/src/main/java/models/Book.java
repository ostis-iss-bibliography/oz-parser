package models;

public class Book {

    private String title;

    private String author;

    private String description;

    private String genre;

    private String released_on;

    public Book() {
    }

    public Book(String title, String author, String description, String genre, String released_on) {
        this.title = title;
        this.author = author;
        this.description = description;
        this.genre = genre;
        this.released_on = released_on;
    }

    @Override
    public String toString() {
        return "Book{" +
                "title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", description='" + description + '\'' +
                ", genre='" + genre + '\'' +
                ", released_on='" + released_on + '\'' +
                '}';
    }
}
