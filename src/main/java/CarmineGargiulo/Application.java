package CarmineGargiulo;

import CarmineGargiulo.dao.AuthorDao;
import CarmineGargiulo.dao.LoanDao;
import CarmineGargiulo.dao.UserDao;
import CarmineGargiulo.dao.VolumeDao;
import CarmineGargiulo.entities.*;
import CarmineGargiulo.enums.Genre;
import CarmineGargiulo.enums.Periodicity;
import com.github.javafaker.Faker;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public class Application {
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("progetto_settimana-15");
    private static final Faker faker = new Faker(Locale.US);
    public static void main(String[] args) {
        EntityManager em = emf.createEntityManager();
        AuthorDao authorDao = new AuthorDao(em);
        UserDao userDao = new UserDao(em);
        VolumeDao volumeDao = new VolumeDao(em);
        LoanDao loanDao = new LoanDao(em);
        /*for (int i = 0; i < 10; i++) {
            Author author = new Author(faker.name().firstName(), faker.name().lastName());
            authorDao.saveAuthor(author);
        }*/
      /*  for (int i = 0; i < 10; i++) {
            User user = new User(faker.name().firstName(), faker.name().lastName(), LocalDate.of(faker.random().nextInt(1950, 2006), faker.random().nextInt(1, 12), faker.random().nextInt(1, 27)));
            userDao.saveUser(user);
        }*/
        /*for (int i = 0; i < 10; i++) {
            List<Periodicity> periodicities = Arrays.stream(Periodicity.values()).toList();
            Magazine magazine = new Magazine(faker.funnyName().name(), faker.random().nextInt(1970, 2025), faker.random().nextInt(20, 200), periodicities.get(faker.random().nextInt(0, periodicities.size()-1)));
            volumeDao.saveVolume(magazine);
        }*/
        List<Author> authorListFromDb = authorDao.getAllAuthors();
       /* for (int i = 0; i < 10; i++) {
            List<Genre> genres = Arrays.stream(Genre.values()).toList();
            Book book = new Book(faker.book().title(), faker.random().nextInt(1970, 2025), faker.random().nextInt(50, 1000),
                     authorListFromDb.get(faker.random().nextInt(0, authorListFromDb.size()-1)), genres.get(faker.random().nextInt(0, genres.size()-1)));
            volumeDao.saveVolume(book);
        }*/
        List<Volume> volumeListFromDb = volumeDao.getAllVolums();
        List<User> userListFromDb = userDao.getAllUsers();
        for (int i = 0; i < 20; i++) {
            Loan loan = new Loan(LocalDate.of(faker.random().nextInt(2000, 2024), faker.random().nextInt(1, 12), faker.random().nextInt(1, 27)),
                    userListFromDb.get(faker.random().nextInt(0, userListFromDb.size()-1)), volumeListFromDb.get(faker.random().nextInt(0, volumeListFromDb.size()-1)));
            loanDao.saveLoan(loan);
        }

    }
}
