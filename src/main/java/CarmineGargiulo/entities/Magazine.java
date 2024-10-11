package CarmineGargiulo.entities;

import CarmineGargiulo.enums.Periodicity;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

@DiscriminatorValue("Magazine")
public class Magazine extends Volume{
    @Enumerated(EnumType.STRING)
    private Periodicity periodicity;

    public Magazine(){

    }

    public Magazine(String title, int publicationYear, int nrPages, Periodicity periodicity) {
        super(title, publicationYear, nrPages);
        this.periodicity = periodicity;
    }

    public Periodicity getPeriodicity() {
        return periodicity;
    }

    public void setPeriodicity(Periodicity periodicity) {
        this.periodicity = periodicity;
    }

    @Override
    public String toString() {
        return "Magazine = " + super.toString() +
                ", periodicity: " + periodicity;
    }
}
