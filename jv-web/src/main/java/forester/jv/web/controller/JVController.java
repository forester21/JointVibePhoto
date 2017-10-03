package forester.jv.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by FORESTER on 01.10.17.
 */
@Controller
public class JVController {

    @RequestMapping(value="/", method = RequestMethod.GET)
    public String welocomePage(){
        return "test";
    }

}
