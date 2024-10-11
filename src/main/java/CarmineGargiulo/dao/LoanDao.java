package CarmineGargiulo.dao;

import CarmineGargiulo.entities.Loan;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class LoanDao {
    private final EntityManager entityManager;

    public LoanDao(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void saveLoan(Loan loan){
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.persist(loan);
        transaction.commit();
        System.out.println("Loan of " + loan.getUser().getSurname() + " has been successfully saved");
    }

    public List<Loan> getAllLoans(){
        TypedQuery<Loan> query = entityManager.createNamedQuery("allLoans", Loan.class);
        return query.getResultList();
    }
}
