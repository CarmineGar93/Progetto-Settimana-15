package CarmineGargiulo.dao;

import CarmineGargiulo.entities.Author;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class AuthorDao {
    private final EntityManager entityManager;

    public AuthorDao(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void saveAuthor(Author author){
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.persist(author);
        transaction.commit();
        System.out.println("Author " + author.getSurname() + " has been successfully saved");
    }

    public List<Author> getAllAuthors(){
        TypedQuery<Author> query = entityManager.createNamedQuery("allAuthors", Author.class);
        return query.getResultList();
    }
}
