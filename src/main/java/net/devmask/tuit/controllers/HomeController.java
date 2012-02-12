package net.devmask.tuit.controllers;

import net.devmask.tuit.TuitRepository;
import net.devmask.tuit.models.Tuit;
import net.devmask.tuit.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class HomeController{

    @Autowired
    private TuitRepository repository;

    @RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Model model) {
        model.addAttribute(new User());
        model.addAttribute("tuits", repository.findAll());
		return "home";
	}
	
}
