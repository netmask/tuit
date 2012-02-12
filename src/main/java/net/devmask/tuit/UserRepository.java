package net.devmask.tuit;

import net.devmask.tuit.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

/**
 * @author <a href="mailto:jonathan@devmask.net"> Jonathan Garay </a>
 *         11/02/12 Creado
 */
@Repository
public class UserRepository {
    
    @Autowired
    private EntityManager entityManager;
    
    public User findLogin(User user){
        return (User)entityManager.createNamedQuery("user.find.to_login")
                .setParameter("username", user.getUsername())
                .setParameter("password", user.getPassword())
                .getSingleResult();
    }
}
