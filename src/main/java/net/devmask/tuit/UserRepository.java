package net.devmask.tuit;

import net.devmask.tuit.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.transaction.TransactionManager;
import java.util.List;

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

    @Transactional
    public void create(User user){
        entityManager.getTransaction().begin();
        entityManager.persist(user);
        entityManager.getTransaction().commit(); //i don
    }

    public List<User> findUsersLike(String username){
        return (List<User>)entityManager.createNamedQuery("user.by_username.like")
                .setParameter("username","%"+username+"%")
                .getResultList();
    }
    
    public User findByUsername(String username){
        return (User)entityManager.createNamedQuery("user.by_username.like")
                .setParameter("username",username) //hacky
                .getSingleResult();
    }

    @Transactional
    public User followUser(User follower, String tofollow ){
        User user = findByUsername(tofollow);
        System.out.println(user.getUsername());
        System.out.println(follower.getUsername());
        entityManager.getTransaction().begin();
        user.getFollowers().add(follower);
        follower.getFollowing().add(user);
        entityManager.getTransaction().commit();
        return user;
    }
    
    @Transactional
    public User unfollowUser(User follower, String toUnfollow){
        entityManager.getTransaction().begin();
        follower.getFollowing().remove(findByUsername(toUnfollow));
        entityManager.getTransaction().commit();
        return follower;
    }

}
