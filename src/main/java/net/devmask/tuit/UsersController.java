package net.devmask.tuit;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author <a href="mailto:jonathan@devmask.net"> Jonathan Garay </a>
 *         07/02/12 Creado
 */

@Controller
@RequestMapping("/users")
public class UsersController {

    @RequestMapping(method = RequestMethod.POST)
    public void create(ModelMap m){

    }
}
