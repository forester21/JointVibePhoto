package forester.jv.web.controller;

import forester.jv.app.CryptoUtils;
import forester.jv.data.entity.PhotoMeta;
import forester.jv.data.repository.GroupsRepository;
import forester.jv.data.repository.PhotosMetaRepository;
import forester.jv.data.repository.PhotosRepository;
import forester.jv.web.FormString;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import java.util.List;

/**
 * Created by FORESTER on 26.11.17.
 */
@Controller
@RequestMapping("/album")
public class AlbumController {

    @Autowired
    PhotosRepository photosRepository;
    @Autowired
    PhotosMetaRepository photosMetaRepository;
    @Autowired
    GroupsRepository groupsRepository;
    @Autowired
    CryptoUtils cryptoUtils;
    @Autowired
    PasswordEncoder passwordEncoder;

    @GetMapping
    public String album(Model model,
                        @RequestParam(value="id") Long albumId) throws Exception{

        if (cryptoUtils.isEncrypted(albumId)&&!cryptoUtils.checkCookie(albumId)){
                return "redirect:/album/passwd?album="+albumId;
        }
        return getAlbumPage(model,albumId);
    }

    @GetMapping("/photo")
    public ResponseEntity<byte[]> getImage(@RequestParam(value = "id") Long photoId,
                                           @RequestParam(value = "album") Long albumId,
                                           @CookieValue("forester") String cookie,
                                           Model model) throws Exception{
        byte[] media;
        if (cryptoUtils.isEncrypted(albumId)){
            media = cryptoUtils.decryptPhoto(photoId,Base64.decodeBase64(cookie),albumId);
        } else {
            media = photosRepository.findOne(photoId).getPhoto();
        }
        model.addAttribute("album",albumId);
        HttpHeaders headers = new HttpHeaders();
        headers.setCacheControl(CacheControl.noCache().getHeaderValue());
        ResponseEntity<byte[]> responseEntity = new ResponseEntity(media, headers, HttpStatus.OK);
        return responseEntity;
    }

    @GetMapping("/passwd")
    public String albumPasswordGET(Model model,
                                   @RequestParam(value = "album") Long albumId,
                                   @RequestParam(value = "action", required = false) String action){
        String text = "";
        if ("ses".equals(action))
            text = "Сессия истекла, введите пароль повторно";
        if ("wrong".equals(action))
            text = "Неверный пароль";
        model.addAttribute("text",text);
        model.addAttribute("album",albumId);
        model.addAttribute("password",new FormString());
        return "album_password";
    }

    @PostMapping("/passwd")
    public String albumPasswordPOST(Model model,
                                    @ModelAttribute FormString password,
                                    @RequestParam(value = "album") Long albumId) throws Exception{
        String hashedPassword = groupsRepository.findOne(albumId).getPassword();
        if (password==null || !passwordEncoder.matches(password.getStr(),hashedPassword))
            return "redirect:/album/passwd?album="+albumId+"&action=wrong";
        byte[] cookie = cryptoUtils.generateCookie(password.getStr().getBytes(),albumId);
        model.addAttribute("cookie", Base64.encodeBase64URLSafeString(cookie));
        return getAlbumPage(model,albumId);
    }

    private String getAlbumPage(Model model, Long albumId){
        List<PhotoMeta> photosMetas = photosMetaRepository.getMetaByAlbumId(albumId);
        model.addAttribute("photos_meta",photosMetas);
        model.addAttribute("album",albumId);
        return "album";
    }
}
