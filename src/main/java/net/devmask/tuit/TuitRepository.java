package net.devmask.tuit;

import net.devmask.tuit.models.Tuit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext ;
import java.util.List;

/**
 * @author <a href="mailto:jonathan@devmask.net"> Jonathan Garay </a>
 *         11/02/12 Creado
 */
@Repository
public class TuitRepository {

    @Autowired
    private EntityManager entityManager;

    public List<Tuit> findAll(){
        return (List<Tuit>)entityManager.createNamedQuery("all.tuits")
                .setMaxResults(10)
                .getResultList();
    }


}
