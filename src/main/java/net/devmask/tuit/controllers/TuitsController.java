package net.devmask.tuit.controllers;

import net.devmask.tuit.TuitRepository;
import net.devmask.tuit.TuitSession;
import net.devmask.tuit.UserRepository;
import net.devmask.tuit.decorators.TuitDecorator;
import net.devmask.tuit.models.Tuit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author <a href="mailto:jonathan@devmask.net"> Jonathan Garay </a>
 *         11/02/12 Creado
 */
@Controller
@RequestMapping("/user/tuits")
public class TuitsController {
    @Autowired
    private TuitSession tuitSession;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TuitRepository tuitRepository;

    @RequestMapping(method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public @ResponseBody TuitDecorator newTuit(@RequestBody TuitDecorator tuit){
        return TuitDecorator.apply(tuitRepository.createUserTuit(tuitSession.getCurrentUser(), TuitDecorator.toTuit(tuit)),
                tuitSession.getCurrentUser());
    }

    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody List<TuitDecorator> tuits(Model model){
        List<TuitDecorator> decorated = new ArrayList<TuitDecorator>(); // pew
        for(Tuit tuti: tuitRepository.findUserTimeline(tuitSession.getCurrentUser())){
            decorated.add(TuitDecorator.apply(tuti,tuitSession.getCurrentUser()));
        }
        return decorated;
    }

    @RequestMapping(value="/last", method = RequestMethod.GET)
    public @ResponseBody List<TuitDecorator> lastTuits(Model model){
        List<TuitDecorator> decorated = new ArrayList<TuitDecorator>(); // pew
        for(Tuit tuti: tuitRepository.findTimelineSicen(tuitSession.getCurrentUser(), tuitSession.getLastQuery())){
            decorated.add(TuitDecorator.apply(tuti,tuitSession.getCurrentUser()));
        }
        return decorated;
    }
}
