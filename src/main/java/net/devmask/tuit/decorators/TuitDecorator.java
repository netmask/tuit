package net.devmask.tuit.decorators;

import net.devmask.tuit.models.Tuit;
import net.devmask.tuit.models.User;

/**
 * @author <a href="mailto:jonathan@devmask.net"> Jonathan Garay </a>
 *         11/02/12 Creado
 */
public class TuitDecorator{     /// :(
    
    private String username;
    private String stamp;
    private String content;

    public static TuitDecorator apply(Tuit tuit){
        return  new TuitDecorator(tuit.getUser().getUsername(), tuit.getStamp().toString(), tuit.getContent());
    }
    
    // MMM .... 
    public static TuitDecorator apply(Tuit tuit, User userSession){
        TuitDecorator td = apply(tuit);
        td.setUsername(userSession.getUsername().equals(td.username) ? "by You" : td.username);
        return td;
    }

    
    public static Tuit toTuit(TuitDecorator decorator){
        Tuit tuit = new Tuit();
        tuit.setContent(decorator.getContent());
        return tuit;
    }
    
    public TuitDecorator(String username, String stamp, String content) {
        this.username = username;
        this.stamp = stamp;
        this.content = content;
    }

    public TuitDecorator() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getStamp() {
        return stamp;
    }

    public void setStamp(String stamp) {
        this.stamp = stamp;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }


}
