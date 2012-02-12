package net.devmask.tuit.controllers;

import net.devmask.tuit.TuitSession;
import net.devmask.tuit.UserRepository;
import net.devmask.tuit.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author <a href="mailto:jonathan@devmask.net"> Jonathan Garay </a>
 *         11/02/12 Creado
 */
@Controller
@RequestMapping("/user/follow")
public class FollowController {

    @Autowired
    private TuitSession tuitSession;

    @Autowired
    private UserRepository userRepository;

    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody
    List<User> following(){
        return tuitSession.getCurrentUser().getFollowing();
    }

    @RequestMapping(value = "{username}", method = RequestMethod.POST)
    public @ResponseBody User toFollow(@PathVariable("username") String username){
        return userRepository.followUser(tuitSession.getCurrentUser(),username);
    }

    @RequestMapping(value = "{username}", method = RequestMethod.DELETE)
    public @ResponseBody User delete(@PathVariable("username") String username){
        return userRepository.unfollowUser(tuitSession.getCurrentUser(),username);
    }


}
