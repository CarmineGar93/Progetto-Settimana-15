package CarmineGargiulo.dao;

import CarmineGargiulo.entities.Loan;
import CarmineGargiulo.entities.Volume;
import CarmineGargiulo.exceptions.EmptyListException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;

import java.time.LocalDate;
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

    public List<Volume> getAllLoansFromBadgeNr(long badgeNr){
        TypedQuery<Volume> query = entityManager.createQuery("SELECT l.volume FROM Loan l WHERE l.returnDate IS NULL AND l.user.badgeNr = :badgeNr", Volume.class);
        query.setParameter("badgeNr", badgeNr);
        List<Volume> result = query.getResultList();
        if(result.isEmpty()) throw new EmptyListException();
        return result;
    }

    public List<Loan> getAllExpiredAndNotYetReturnedLoans(){
        TypedQuery<Loan> query = entityManager.createQuery("SELECT l FROM Loan l WHERE l.returnDate IS NULL AND l.expectedReturnDate < :date", Loan.class);
        query.setParameter("date", LocalDate.now());
        List<Loan> result = query.getResultList();
        if(result.isEmpty()) throw new EmptyListException();
        return result;

    }
}
