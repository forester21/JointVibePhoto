package forester.jv.web.controller;

import forester.jv.data.entity.User;
import forester.jv.data.repository.UsersRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by FORESTER on 01.10.17.
 */
@Controller
public class JVController {

    final static Logger log = Logger.getLogger(JVController.class);

    @Autowired
    UsersRepository usersRepository;

    @Autowired
    BCryptPasswordEncoder passwordEncoder;

    @GetMapping(value="/start")
    public String startPage(Model model){
        model.addAttribute("user",new User());
        return "start_page";
    }

    @PostMapping(value = "/start")
    public String loginPost(){
        return "redirect:/albums";
    }

    @PostMapping(value="/registration")
    public String registartionPost(@ModelAttribute User user) throws NoSuchAlgorithmException {
        log.info("Saving user with id= "+user.getUserId());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        usersRepository.save(user);
        return "redirect:/start";
    }
}
