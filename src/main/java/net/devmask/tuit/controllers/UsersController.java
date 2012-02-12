package net.devmask.tuit.controllers;

import net.devmask.tuit.TuitSession;
import net.devmask.tuit.controllers.BaseController;
import net.devmask.tuit.models.Tuit;
import net.devmask.tuit.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

/**
 * @author <a href="mailto:jonathan@devmask.net"> Jonathan Garay </a>
 *         07/02/12 Creado
 */

@Controller
@RequestMapping("/user")
public class UsersController extends BaseController {
    
    @Autowired
    private TuitSession tuitSession;
    
    @RequestMapping(method = RequestMethod.GET)
    public String newUser(Model model){
        model.addAttribute(new User());
        return "accounts/new";
    }


    @RequestMapping(method = RequestMethod.POST)
    public String create(@Valid User user, BindingResult result,RedirectAttributes redirectAttributes){

        if(result.hasErrors()){
            return "accounts/new";
        }

        try {
            persist(user);
        } catch (Exception e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("error","email already taked");
            return "accounts/new";
        }

        tuitSession.login(user);

        return "redirect:/user/dashboard";
    }

    @RequestMapping(value = "/dashboard", method = RequestMethod.GET)
    public String dashboard(Model model){
        model.addAttribute(new Tuit());
        model.addAttribute("timeline",getTuits());
        return "users/dashboard";
    }



    @RequestMapping(value = "/following",method = RequestMethod.GET)
    public @ResponseBody List<User> following(){
        return tuitSession.getCurrentUser().getFollowing();
    }


    @RequestMapping(value = "{username}/follow",method = RequestMethod.GET)
    public @ResponseBody User toFollow(@PathVariable("username") String username){
        User user = (User) getEntityManager().createNamedQuery("user.by_username.like")
                .setParameter("username",username)
                .getSingleResult();

        // :/ i don't like this
        getEntityManager().getTransaction().begin();
        tuitSession.getCurrentUser().getFollowing().add(user);
        user.getFollowers().add(tuitSession.getCurrentUser());
        getEntityManager().persist(user);
        getEntityManager().persist(tuitSession.getCurrentUser());
        getEntityManager().getTransaction().commit();

        return user;
    }



    @RequestMapping(value = "/find",method = RequestMethod.GET)
    public @ResponseBody List<User> find(@RequestParam("username") String username){
        return (List<User>) getEntityManager()
                .createNamedQuery("user.by_username.like")
                .setParameter("username","%"+username+"%") //hacky
                .getResultList();
    }




    @RequestMapping(value = "/tuits", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public @ResponseBody Tuit newTuit(@RequestBody Tuit tuit){
        tuit.setUser(tuitSession.getCurrentUser());
        try {
            persist(tuit);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tuit;
    }

    @RequestMapping(value = "/tuits", method = RequestMethod.GET)
    public @ResponseBody List<Tuit> tuits(Model model){
        return getTuits();
    }

    protected List<Tuit> getTuits(){
        return getEntityManager().createNamedQuery("user.timeline")
                .setParameter("user",tuitSession.getCurrentUser())
                .getResultList();
    }
}
