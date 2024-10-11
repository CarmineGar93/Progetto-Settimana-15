package CarmineGargiulo.dao;

import CarmineGargiulo.entities.Author;
import CarmineGargiulo.entities.Book;
import CarmineGargiulo.entities.Volume;
import CarmineGargiulo.exceptions.EmptyListException;
import CarmineGargiulo.exceptions.NotFoundException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;

import java.util.List;
import java.util.UUID;

public class VolumeDao {
    private final EntityManager entityManager;

    public VolumeDao(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void saveVolume(Volume volume){
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.persist(volume);
        transaction.commit();
        System.out.println("Volume " + volume.getTitle() + " has been successfully saved");
    }

    public List<Volume> getAllVolums(){
        TypedQuery<Volume> query = entityManager.createNamedQuery("allVolums", Volume.class);
        return query.getResultList();
    }

    public Volume findByIsbn(String isbn){
        Volume found = entityManager.find(Volume.class, UUID.fromString(isbn));
        if(found == null) throw new NotFoundException(isbn);
        return found;
    }

    public void deleteVolume(String isbn){
        Volume found = findByIsbn(isbn);
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.remove(found);
        transaction.commit();
        System.out.println("Volume " + found.getTitle() + " has been succesfully removed");
    }

    public List<Volume> searchByYear(int year){
        TypedQuery<Volume> query = entityManager.createQuery("SELECT v FROM Volume v WHERE v.publicationYear = :year", Volume.class);
        query.setParameter("year", year);
        List<Volume> result = query.getResultList();
        if(result.isEmpty()) throw new EmptyListException();
        return result;
    }

    public List<Book> searchByAuthor(Author author){
        TypedQuery<Book> query = entityManager.createQuery("SELECT b FROM Book b WHERE b.author = :author", Book.class);
        query.setParameter("author", author);
        List<Book> result = query.getResultList();
        if(result.isEmpty()) throw new EmptyListException();
        return result;
    }

    public List<Volume> searchByTitle(String title){
        TypedQuery<Volume> query = entityManager.createQuery("SELECT v FROM Volume v WHERE LOWER(v.title) LIKE :title", Volume.class);
        query.setParameter("title", "%" + title + "%");
        List<Volume> result = query.getResultList();
        if(result.isEmpty()) throw new EmptyListException();
        return result;
    }
}
