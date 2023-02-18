package models;

public class Book {

    private String title;

    private String author;

    private String description;

    private String genre;

    private String date;

    public Book() {
    }

    public Book(String title, String author, String description, String genre, String date) {
        this.title = title;
        this.author = author;
        this.description = description;
        this.genre = genre;
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Book{" +
                "title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", description='" + description + '\'' +
                ", genre='" + genre + '\'' +
                ", date='" + date + '\'' +
                '}';
    }
}
