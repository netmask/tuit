package net.devmask.tuit;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;

/**
 * @author <a href="mailto:jonathan@devmask.net"> Jonathan Garay </a>
 *         07/02/12 Creado
 */
@Controller
@RequestMapping("/login")
public class SessionController {

    @RequestMapping(method = RequestMethod.GET)
    public String showform(Map params){
        System.out.println(params.toString());
        return "";
    }
}
