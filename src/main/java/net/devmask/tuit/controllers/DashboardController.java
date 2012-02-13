package net.devmask.tuit.controllers;

import net.devmask.tuit.TuitRepository;
import net.devmask.tuit.TuitSession;
import net.devmask.tuit.models.Tuit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreFilter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author <a href="mailto:jonathan@devmask.net"> Jonathan Garay </a>
 *         11/02/12 Creado
 */
@Controller
@RequestMapping("/user/dashboard")
public class DashboardController {

    @Autowired
    private TuitSession tuitSession;

    @Autowired
    private TuitRepository tuitRepository;

    @RequestMapping(method = RequestMethod.GET)
    public String dashboard(Model model){
        model.addAttribute(new Tuit());
        model.addAttribute("user",tuitSession.getCurrentUser());
        model.addAttribute("timeline",tuitRepository.findUserTimeline(tuitSession.getCurrentUser()));
        return "users/dashboard";
    }

}
