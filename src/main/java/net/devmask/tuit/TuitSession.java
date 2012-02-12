package net.devmask.tuit;

import com.sun.org.apache.bcel.internal.generic.NEW;
import net.devmask.tuit.models.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author <a href="mailto:jonathan@devmask.net"> Jonathan Garay </a>
 *         08/02/12 Creado
 */

@Component
@Scope("session")
public class TuitSession {

    private User currentUser = null;
    private Boolean logedIn = false;
    private Date lastQuery = new Date();

    
    public void login(User user){
        currentUser = user;
        logedIn = true;
        lastQuery = new Date();
    }
    
    public void logout(){
        currentUser = new User();
        logedIn = false;        
    }
    
    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }

    public Boolean isLogedIn() {
        return logedIn;
    }

    public void setLogedIn(Boolean logedIn) {
        this.logedIn = logedIn;
    }

    public Date getLastQuery() {
        Date last = lastQuery;
        lastQuery = new Date(); // ugly
        return last;
    }

    public void setLastQuery(Date lastQuery) {
        this.lastQuery = lastQuery;
    }
}
