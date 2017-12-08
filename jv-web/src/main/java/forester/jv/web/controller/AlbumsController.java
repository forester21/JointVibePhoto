package forester.jv.web.controller;

import forester.jv.data.entity.Group;
import forester.jv.data.entity.User;
import forester.jv.data.entity.UsersGroupsAccess;
import forester.jv.data.repository.GroupsRepository;
import forester.jv.data.repository.UsersGroupsAccessRepository;
import forester.jv.data.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.math.BigInteger;
import java.util.List;

/**
 * Created by FORESTER on 26.11.17.
 */
@Controller
@RequestMapping("/albums")
public class AlbumsController {

    @Autowired
    UsersRepository usersRepository;
    @Autowired
    GroupsRepository groupsRepository;
    @Autowired
    UsersGroupsAccessRepository usersGroupsAccessRepository;
    @Autowired
    PasswordEncoder passwordEncoder;

    //needed after logging in
    @PostMapping
    public String redirectToAlbumsAfterAuth(){
        return "redirect:/albums";
    }

    @GetMapping
    public String albums(Model model){
//        User user = usersRepository.findByLogin(auth.getName());
        List<Group> albums = groupsRepository.findAlbumsByLogin(SecurityContextHolder.getContext().getAuthentication().getName());
        model.addAttribute("albums",albums);
        model.addAttribute("new_album",new Group());
//        model.addAttribute("user",user);
        return "albums";
    }

    @PostMapping(value = "/new_album")
    public String newAlbum(@ModelAttribute Group album){
        String login = SecurityContextHolder.getContext().getAuthentication().getName();
        album.setEncrypted(false);
        if (!StringUtils.isEmpty(album.getPassword())){
            album.setEncrypted(true);
            album.setPassword(passwordEncoder.encode(album.getPassword()));
        }
        Group savedAlbum = groupsRepository.save(album);
        Long userId = usersRepository.findByLogin(login).getUserId();
        usersGroupsAccessRepository.save(new UsersGroupsAccess(userId,savedAlbum.getGroupId()));
        return "redirect:/albums";
    }

}
