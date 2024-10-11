package CarmineGargiulo.dao;

import CarmineGargiulo.entities.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class UserDao {
    private final EntityManager entityManager;

    public UserDao(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void saveUser(User user){
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.persist(user);
        transaction.commit();
        System.out.println("User " + user.getSurname() + " has been successfully saved");
    }

    public List<User> getAllUsers(){
        TypedQuery<User> query = entityManager.createNamedQuery("allUsers", User.class);
        return query.getResultList();
    }

}
