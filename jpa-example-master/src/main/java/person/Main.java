package person;

import com.github.javafaker.Faker;
import lombok.extern.java.Log;
import lombok.extern.log4j.Log4j2;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.ZoneId;
import java.util.List;
import java.util.Locale;

@Log4j2
public class Main {

    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa-example");
    private static Faker faker = new Faker(new Locale("en"));

    public static Person randomPerson() {

        Address address = Address.builder()

               .zip(faker.address().zipCode())
               .city(faker.address().city())
               .streetAddress(faker.address().streetAddress())
               .state(faker.address().state())
               .country(faker.address().country())
               .build();

        Person person = Person.builder()
                .name(faker.name().name())
                .profession(faker.company().profession())
                .email(faker.internet().emailAddress())
                .address(address)
                .dob(faker.date().birthday().toInstant().atZone(ZoneId.systemDefault()).toLocalDate())
                .gender(faker.options().option(Person.Gender.M, Person.Gender.F))
                .build();

        return person;
    }

        public static void addPpl2DB(int noOfPpl) {

            EntityManager em = emf.createEntityManager();

            em.getTransaction().begin();
            try {
                for (int i = 0; i <= noOfPpl; i++) {
                    em.persist(randomPerson());
                }
                em.getTransaction().commit();
            } finally {
                em.close();
                emf.close();
            }

        }

        public static List<Person> getPplFromDB() {
            EntityManager em = emf.createEntityManager();
            try {
                return em.createQuery("SELECT p FROM Person p ORDER BY p.id", Person.class).getResultList();
            } finally {
                em.close();
            }
        }

        public static void main (String[]args){

            addPpl2DB(10);
            getPplFromDB().forEach(log::info);
            emf.close();


    }
}