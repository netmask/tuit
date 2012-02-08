package net.devmask.tuit;

import net.devmask.tuit.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.validation.Valid;

/**
 * @author <a href="mailto:jonathan@devmask.net"> Jonathan Garay </a>
 *         07/02/12 Creado
 */

@Controller
@RequestMapping("/user")
public class UsersController extends BaseController{

    @RequestMapping(method = RequestMethod.GET)
    public String newUser(Model model){
        model.addAttribute(new User());
        return "accounts/new";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String create(@Valid User user, BindingResult result){
        if(result.hasErrors()){
            return "accounts/new";
        }

        EntityManager entityManager = getEntityManager();
        entityManager.persist(user);

        return "users/dashboard";
    }




}
