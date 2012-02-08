package net.devmask.tuit;

import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.io.Serializable;

/**
 * @author <a href="mailto:jonathan@devmask.net"> Jonathan Garay </a>
 *         07/02/12 Creado
 */
public class BaseController {

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    public EntityManagerFactory getEntityManagerFactory() {
        return entityManagerFactory;
    }

    public void setEntityManagerFactory(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    public EntityManager getEntityManager(){
        return entityManagerFactory.createEntityManager();
    }
    
    
    public void persist(Serializable object){
        EntityManager entityManager = getEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(object);
        entityManager.getTransaction().commit();
    }
    
    public void persist(Serializable... objects){
        EntityManager entityManager = getEntityManager();
        entityManager.getTransaction().begin();
        for(Serializable s: objects){ entityManager.persist(s); }
        entityManager.getTransaction().commit();
    }

}
