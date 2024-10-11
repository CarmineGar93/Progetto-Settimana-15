package CarmineGargiulo;

import CarmineGargiulo.dao.AuthorDao;
import CarmineGargiulo.dao.LoanDao;
import CarmineGargiulo.dao.UserDao;
import CarmineGargiulo.dao.VolumeDao;
import CarmineGargiulo.entities.*;
import CarmineGargiulo.enums.Genre;
import CarmineGargiulo.enums.Periodicity;
import CarmineGargiulo.exceptions.EmptyListException;
import CarmineGargiulo.exceptions.NotFoundException;
import com.github.javafaker.Faker;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.RollbackException;

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
        initializeDbWithData(authorDao, userDao, volumeDao, loanDao);

        List<Author> authorListFromDb = authorDao.getAllAuthors();
        System.out.println("-----------------Exercise 1-------------------");
        System.out.println("Adding an element to the collection");
        volumeDao.saveVolume(new Book("Bello Java", 2024, 3000, authorListFromDb.get(5), Genre.ADVENTURE));
        System.out.println("-----------------Exercise 2-------------------");
        System.out.println("Removing an element given the isbn code");
        try{
            volumeDao.deleteVolume("d6ba5b5a-29fa-4887-88c4-875e636b273d");
        } catch (NotFoundException | IllegalArgumentException e) {
            System.out.println(e.getMessage());
        } catch (RollbackException e) {
            System.out.println("Sorry, the volume is currently on loan. Remove denied");
        }
        System.out.println("-----------------Exercise 3-------------------");
        System.out.println("Searching a volume given the ISBN");
        try{
            System.out.println(volumeDao.findByIsbn("60f2e102-94f3-498a-ad1d-f62dd5915815"));
        } catch (NotFoundException | IllegalArgumentException e){
            System.out.println(e.getMessage());
        }
        System.out.println("-----------------Exercise 4-------------------");
        System.out.println("Searching a volume given the publication year");
        try{
            volumeDao.searchByYear(2006).forEach(System.out::println);
        } catch (EmptyListException e){
            System.out.println(e.getMessage());
        }
        System.out.println("-----------------Exercise 5-------------------");
        System.out.println("Searching a book given the author");
        try{
            volumeDao.searchByAuthor(authorListFromDb.get(1)).forEach(System.out::println);
        } catch (EmptyListException e){
            System.out.println(e.getMessage());
        }
        System.out.println("-----------------Exercise 6-------------------");
        System.out.println("Searching a volume given the title");
        try{
            volumeDao.searchByTitle("the").forEach(System.out::println);
        } catch (EmptyListException e){
            System.out.println(e.getMessage());
        }
        System.out.println("-----------------Exercise 7-------------------");
        System.out.println("Searching all volume not returned given an user badge");
        try{
            loanDao.getAllLoansFromBadgeNr(2).forEach(System.out::println);
        } catch (EmptyListException e){
            System.out.println(e.getMessage());
        }
        System.out.println("-----------------Exercise 8-------------------");
        System.out.println("Searching all expired loans not yet returned");
        try{
            loanDao.updateLoanReturnDateByUserBadgeNr(LocalDate.now(), 2);
            loanDao.getAllExpiredAndNotYetReturnedLoans().forEach(System.out::println);
            System.out.println(loanDao.getAllExpiredAndNotYetReturnedLoans().size() + " loans expired");
        } catch (EmptyListException e){
            System.out.println(e.getMessage());
        }
    }

    public static void initializeDbWithData(AuthorDao authorDao, UserDao userDao, VolumeDao volumeDao, LoanDao loanDao){
        for (int i = 0; i < 10; i++) {
            Author author = new Author(faker.name().firstName(), faker.name().lastName());
            authorDao.saveAuthor(author);
        }
        for (int i = 0; i < 10; i++) {
            User user = new User(faker.name().firstName(), faker.name().lastName(), LocalDate.of(faker.random().nextInt(1950, 2006), faker.random().nextInt(1, 12), faker.random().nextInt(1, 27)));
            userDao.saveUser(user);
        }
        for (int i = 0; i < 10; i++) {
            List<Periodicity> periodicities = Arrays.stream(Periodicity.values()).toList();
            Magazine magazine = new Magazine(faker.funnyName().name(), faker.random().nextInt(1970, 2025), faker.random().nextInt(20, 200), periodicities.get(faker.random().nextInt(0, periodicities.size()-1)));
            volumeDao.saveVolume(magazine);
        }
        List<Author> authorListFromDb = authorDao.getAllAuthors();
        for (int i = 0; i < 10; i++) {
            List<Genre> genres = Arrays.stream(Genre.values()).toList();
            Book book = new Book(faker.book().title(), faker.random().nextInt(1970, 2025), faker.random().nextInt(50, 1000),
                     authorListFromDb.get(faker.random().nextInt(0, authorListFromDb.size()-1)), genres.get(faker.random().nextInt(0, genres.size()-1)));
            volumeDao.saveVolume(book);
        }
        List<Volume> volumeListFromDb = volumeDao.getAllVolums();
        List<User> userListFromDb = userDao.getAllUsers();
        for (int i = 0; i < 20; i++) {
            Loan loan = new Loan(LocalDate.of(faker.random().nextInt(2000, 2024), faker.random().nextInt(1, 12), faker.random().nextInt(1, 27)),
                    userListFromDb.get(faker.random().nextInt(0, userListFromDb.size()-1)), volumeListFromDb.get(faker.random().nextInt(0, volumeListFromDb.size()-1)));
            loanDao.saveLoan(loan);
        }
    }
}
