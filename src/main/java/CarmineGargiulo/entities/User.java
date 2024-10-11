package CarmineGargiulo.entities;

import com.github.javafaker.Faker;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "users")
@NamedQuery(name = "allUsers", query = "SELECT u FROM User u")
public class User {
    @Id
    @GeneratedValue
    @Column(name = "user_id")
    private UUID userId;
    @Column(name = "badge_nr", unique = true)
    private long badgeNr;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String surname;
    @Column(nullable = false)
    private LocalDate birthday;
    @OneToMany(mappedBy = "user")
    private List<Loan> loanList;
    private static long count = 1;

    public User(){

    }

    public User(String name, String surname, LocalDate birthday) {
        this.badgeNr = count;
        this.name = name;
        this.surname = surname;
        this.birthday = birthday;
        count ++;
    }

    public UUID getUserId() {
        return userId;
    }

    public long getBadgeNr() {
        return badgeNr;
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

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public List<Loan> getLoanList() {
        return loanList;
    }

    @Override
    public String toString() {
        return "User = userId: " + userId +
                ", badgeNr: " + badgeNr +
                ", name: " + name +
                ", surname: " + surname +
                ", birthday: " + birthday;
    }
}
