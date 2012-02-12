package net.devmask.tuit.controllers;

import net.devmask.tuit.FileUtils;
import net.devmask.tuit.TuitSession;
import net.devmask.tuit.UserRepository;
import net.devmask.tuit.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;

/**
 * @author <a href="mailto:jonathan@devmask.net"> Jonathan Garay </a>
 *         07/02/12 Creado
 */

@Controller
@RequestMapping("/user")
public class UsersController {
    
    @Autowired
    private TuitSession tuitSession;

    @Autowired
    private UserRepository userRepository;

    @RequestMapping(method = RequestMethod.GET)
    public String newUser(Model model){
        model.addAttribute(new User());
        return "accounts/new";
    }


    @RequestMapping(method = RequestMethod.POST)
    public String create(@Valid User user, BindingResult result, @RequestParam MultipartFile file){
        if(result.hasErrors()){ return "accounts/new"; }
        user.setAvatarName(FileUtils.storeImage(file) ? file.getOriginalFilename() : "no_avatar_perfil.jpg" );
        userRepository.create(user);
        tuitSession.login(user);

        return "redirect:/user/dashboard";
    }


    @RequestMapping(value = "/find",method = RequestMethod.GET)
    public @ResponseBody List<User> find(@RequestParam("username") String username){
        return userRepository.findUsersLike(username);
    }

}
