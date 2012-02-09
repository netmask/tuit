package net.devmask.tuit;

import net.devmask.tuit.models.Tuit;
import net.devmask.tuit.models.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.Locale;

@Controller
public class HomeController extends BaseController{

    @RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Model model) {
        model.addAttribute(new User());
        model.addAttribute("tuits", (List<Tuit>)getEntityManager()
                                            .createNamedQuery("all.tuits")
                                            .getResultList());
		return "home";
	}
	
}
