package person;

import com.github.javafaker.Faker;
import legoset.model.LegoSet;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.Year;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa-example");
    static EntityManager em = emf.createEntityManager();

    private static List<Person> getPersons() {
            return em.createQuery("SELECT l FROM Person l", Person.class).getResultList();
    }
    public static Person randomPerson() {
        Faker faker = new Faker();
        Person person = new Person();
        Address address = new Address();
        address.setCity(faker.address().city());
        address.setCountry(faker.address().country());
        address.setState(faker.address().state());
        address.setStreetAddress(faker.address().streetAddress());
        address.setZip(faker.address().zipCode());
        person.setAddress(address);
        person.setProfession(faker.company().profession());
        person.setDob(faker.date().birthday().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
        person.setEmail(faker.internet().emailAddress());
        person.setName(faker.name().name());
        person.setGender(faker.options().option(Person.Gender.FEMALE, Person.Gender.MALE));
        em.persist(person);
        return person;
    }

    public static void main(String[] args) {
        System.out.println("Hany szemelyt akar berakni az adatbazisba? \nKerem adjon meg egy szamot");
        int n = new Scanner(System.in).nextInt();
        em.getTransaction().begin();
        for(int i = 0; i < n; i++) {
            randomPerson();
        }
        em.getTransaction().commit();
        System.out.println("Osszes szemely:");
        getPersons().forEach(System.out::println);
        em.close();


    }
}
