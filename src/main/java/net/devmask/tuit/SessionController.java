package net.devmask.tuit;

import net.devmask.tuit.models.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;

/**
 * @author <a href="mailto:jonathan@devmask.net"> Jonathan Garay </a>
 *         07/02/12 Creado
 */
@Controller
@RequestMapping("/session")
public class SessionController extends BaseController{

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public String login(User user, Model model){

        model.addAttribute(getEntityManager().find(user.getClass(),user.getEmail()));

        return "redirect:user/dashboard";
    }
}
