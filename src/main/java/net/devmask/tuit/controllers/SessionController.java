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
    public String login(User user, Model model, RedirectAttributes redirectAttributes) {
        User loginUser = repository.findLogin(user);
        return "redirect:/user/dashboard";
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(Model model,RedirectAttributes redirectAttributes) {
        tuitSession.logout();
        redirectAttributes.addFlashAttribute("success","Loged in. ");
        return "redirect:/user/dashboard";
    }

    @ExceptionHandler
    public String handle(NoResultException e, Model model) {
        model.addAttribute("error","Email or password incorrect try again");
        return "redirect:user:home";
    }
}
