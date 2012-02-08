package net.devmask.tuit;

import net.devmask.tuit.models.Tuit;
import net.devmask.tuit.models.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

/**
 * @author <a href="mailto:jonathan@devmask.net"> Jonathan Garay </a>
 *         07/02/12 Creado
 */

@Controller
@RequestMapping("/user")
@SessionAttributes("user")
public class UsersController extends BaseController{

    @RequestMapping(method = RequestMethod.GET)
    public String newUser(Model model){
        model.addAttribute(new User());
        return "accounts/new";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String create(@Valid User user, BindingResult result,RedirectAttributes redirectAttributes){
        if(result.hasErrors()){ return "accounts/new"; }

        persist(user);
        redirectAttributes.addAttribute("username",user.getUsername());
        return "redirect:/user/{username}/dashboard";
    }

    @RequestMapping(value = "{username}/dashboard", method = RequestMethod.GET)
    public String dashboard(Model model){
        model.addAttribute(new Tuit());
        return "users/dashboard";
    }




}
