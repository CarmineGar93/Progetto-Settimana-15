package CarmineGargiulo.entities;

import jakarta.persistence.*;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "authors")
public class Author {
    @Id
    @GeneratedValue
    @Column(name = "author_id")
    private UUID authorId;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String surname;
    @OneToMany(mappedBy = "author")
    private List<Book> bookList;

    public Author(){

    }

    public List<Book> getBookList() {
        return bookList;
    }

    public Author(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    public UUID getAuthorId() {
        return authorId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    @Override
    public String toString() {
        return "Author = authorId: " + authorId +
                ", name: " + name  +
                ", surname: " + surname;
    }
}
