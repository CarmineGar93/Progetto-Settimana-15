package CarmineGargiulo.entities;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "loans")
@NamedQuery(name = "allLoans", query = "SELECT l FROM Loan l")
public class Loan {
    @Id
    @GeneratedValue
    private UUID loan_id;
    @Column(name = "loan_date", nullable = false)
    private LocalDate loanDate;
    @Column(name = "expected_return_date", nullable = false)
    private LocalDate expectedReturnDate;
    @Column(name = "return_date")
    private LocalDate returnDate;
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    @ManyToOne
    @JoinColumn(name = "volume_id", nullable = false)
    private Volume volume;

    public Loan(){

    }
    public Loan(LocalDate loanDate, User user, Volume volume) {
        this.loanDate = loanDate;
        this.expectedReturnDate = loanDate.plusDays(30);
        this.user = user;
        this.volume = volume;
    }

    public UUID getLoan_id() {
        return loan_id;
    }

    public LocalDate getLoanDate() {
        return loanDate;
    }

    public LocalDate getExpectedReturnDate() {
        return expectedReturnDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    public User getUser() {
        return user;
    }

    public Volume getVolume() {
        return volume;
    }

    @Override
    public String toString() {
        return "Loan_id: " + loan_id +
                ", loanDate: " + loanDate +
                ", expectedReturnDate: " + expectedReturnDate +
                ", returnDate: " + returnDate +
                ", " + user +
                ", " + volume;
    }
}
