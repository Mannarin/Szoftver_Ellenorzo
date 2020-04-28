package legoset;

import legoset.model.LegoSet;
import lombok.extern.log4j.Log4j2;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.Year;
import java.util.List;
import java.util.Optional;


@Log4j2
public class LegoSetService {

    private EntityManager em;

    public LegoSetService(EntityManager em){
        this.em=em;
    }

    public LegoSet create(String number, String name, Year year, int pieces){
        LegoSet legoSet=new LegoSet(number,name,year,pieces);
        em.persist(legoSet);
        return legoSet;
    }

    public List<LegoSet> findAll(){
        return em.createQuery("select l from LegoSet l order by l.number",LegoSet.class).getResultList();
    }

    public Optional<LegoSet> find(String number){
        return Optional.ofNullable(em.find(LegoSet.class,number));
    }

    public void delete(String number){
        find(number).ifPresent(legoSet -> {
            em.remove(legoSet);
            log.info("Deleted LegoSet {}", number );
        });
    }

public void deleteAll(){
        long count=em.createQuery("delete from LegoSet").executeUpdate();
        log.info("Deleted {} LegoSET(s)",count);
}


    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa-example");
        EntityManager em=emf.createEntityManager();
        LegoSetService service=new LegoSetService(em);

        em.getTransaction().begin();
        service.create("60073", "Service Truck", Year.of(2015), 233);
        service.create("75211", "Imperial TIE Fighter", Year.of(2018), 519);
        service.create("21034", "London", Year.of(2017), 468);
        em.getTransaction().commit();


        log.info("All LegoSets: ");
        service.findAll().forEach(log::info);

        log.info("LegoSet 75211: {}",service.find("75211"));
        log.info("LegoSet 13012", service.find("13012"));


        em.getTransaction().begin();
        service.delete("75211");
        em.getTransaction().commit();

        em.getTransaction().begin();
        service.deleteAll();
        em.getTransaction().commit();

        em.close();
        emf.close();




    }

}
