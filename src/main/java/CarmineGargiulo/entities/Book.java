package CarmineGargiulo.entities;

import CarmineGargiulo.enums.Genre;
import jakarta.persistence.*;

@DiscriminatorValue("Book")
public class Book extends Volume{
    @ManyToOne
    @JoinColumn(name = "author_id", nullable = false)
    private Author author;
    @Enumerated(EnumType.STRING)
    private Genre genre;

    public Book(){

    }

    public Book(String title, int publicationYear, int nrPages, Author author, Genre genre) {
        super(title, publicationYear, nrPages);
        this.author = author;
        this.genre = genre;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    @Override
    public String toString() {
        return "Book = " + super.toString() +
                ", author: " + author +
                ", genre: " + genre;
    }
}
