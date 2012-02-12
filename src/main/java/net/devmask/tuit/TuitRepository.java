package net.devmask.tuit;

import net.devmask.tuit.models.Tuit;
import net.devmask.tuit.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext ;
import java.util.Date;
import java.util.List;

/**
 * @author <a href="mailto:jonathan@devmask.net"> Jonathan Garay </a>
 *         11/02/12 Creado
 */
@Repository
@SuppressWarnings("unchecked")
public class TuitRepository {

    @Autowired
    private EntityManager entityManager;

    @Transactional
    public List<Tuit> findAll(){
        return (List<Tuit>)entityManager.createNamedQuery("all.tuits")
                .setMaxResults(10)
                .getResultList();
    }

    public List<Tuit> findUserTimeline(User user){
     return entityManager.createNamedQuery("user.timeline")
                     .setParameter("user",user)
                     .getResultList();   
    }

    @Transactional
    public Tuit createUserTuit(User user, Tuit tuit){
        entityManager.getTransaction().begin();
        tuit.setUser(user);
        entityManager.persist(tuit);
        entityManager.getTransaction().commit();
        return tuit;
    }


    public List<Tuit> findTimelineSicen(User user, Date since){
        return (List<Tuit>) entityManager.createNamedQuery("user.timeline.since")
                .setParameter("user",user)
                .setParameter("date",since)
                .getResultList();
    }
}
