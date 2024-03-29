package net.devmask.tuit.controllers;

import net.devmask.tuit.TuitSession;
import net.devmask.tuit.UserRepository;
import net.devmask.tuit.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.persistence.NoResultException;

/**
 * @author <a href="mailto:jonathan@devmask.net"> Jonathan Garay </a>
 *         07/02/12 Creado
 */
@Controller
@RequestMapping("/session")
public class SessionController {

    @Autowired
    private TuitSession tuitSession;
    
    @Autowired
    private UserRepository repository;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(User user, RedirectAttributes redirectAttributes) {
        try{
            tuitSession.login(repository.findLogin(user));
        }catch (NoResultException nre){
            redirectAttributes.addFlashAttribute("error","Email or password incorrect try again");
            return "redirect:/";
        }
        return "redirect:/user/dashboard";
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(Model model,RedirectAttributes redirectAttributes) {
        tuitSession.logout();
        redirectAttributes.addFlashAttribute("success","Success Logged out. ");
        return "redirect:/";
    }


}
