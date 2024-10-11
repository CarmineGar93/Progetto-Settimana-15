package CarmineGargiulo.entities;

import jakarta.persistence.*;

import java.util.List;
import java.util.UUID;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "volume_type")
@Table(name = "volums")
@NamedQuery(name = "allVolums", query = "SELECT v FROM Volume v")
public class Volume {
    @Id
    @GeneratedValue
    @Column(name = "isbn_code")
    protected UUID isbnCode;
    @Column(nullable = false)
    protected String title;
    @Column(name = "publication_year", nullable = false)
    protected int publicationYear;
    @Column(name = "nr_of_pages")
    protected int nrPages;
    @OneToMany(mappedBy = "volume")
    protected List<Loan> loanList;

    public Volume(){

    }

    public Volume(String title, int publicationYear, int nrPages) {
        this.title = title;
        this.publicationYear = publicationYear;
        this.nrPages = nrPages;
    }

    public List<Loan> getLoanList() {
        return loanList;
    }

    public UUID getIsbnCode() {
        return isbnCode;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPublicationYear() {
        return publicationYear;
    }

    public void setPublicationYear(int publicationYear) {
        this.publicationYear = publicationYear;
    }

    public int getNrPages() {
        return nrPages;
    }

    public void setNrPages(int nrPages) {
        this.nrPages = nrPages;
    }

    @Override
    public String toString() {
        return "isbnCode: " + isbnCode +
                ", title: " + title +
                ", publicationYear: " + publicationYear +
                ", nrPages: " + nrPages;
    }
}
