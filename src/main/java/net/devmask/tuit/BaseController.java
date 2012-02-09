package net.devmask.tuit;

import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import java.io.Serializable;
import java.util.HashMap;

/**
 * @author <a href="mailto:jonathan@devmask.net"> Jonathan Garay </a>
 *         07/02/12 Creado
 */
public class BaseController {

    private EntityManager entityManager = null;

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    public EntityManagerFactory getEntityManagerFactory() {
        return entityManagerFactory;
    }

    public EntityManager getEntityManager(){
        if(entityManager==null){
            entityManager = entityManagerFactory.createEntityManager();
        }
        return entityManager;
    }
    
    
    public void persist(Serializable object) throws Exception {
        EntityManager entityManager = getEntityManager();
        try{
            entityManager.getTransaction().begin();
            entityManager.persist(object);
            entityManager.getTransaction().commit();
        }catch(Exception ex){
            entityManager.getTransaction().rollback();
            throw new Exception(ex);
        }
    }
    
    public void persist(Serializable... objects){
        EntityManager entityManager = getEntityManager();
        entityManager.getTransaction().begin();
        for(Serializable s: objects){ entityManager.persist(s); }
        entityManager.getTransaction().commit();
    }

    public Object callNamedQuery(String name){
        return getEntityManager().createNamedQuery(name).getResultList();
    }
}
