package forester.jv.web.controller;

import forester.jv.app.CryptoUtils;
import forester.jv.data.entity.PhotoMeta;
import forester.jv.data.repository.PhotosMetaRepository;
import forester.jv.data.repository.PhotosRepository;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
    CryptoUtils cryptoUtils;

    @GetMapping
    public String album(Model model,
                        @RequestParam(value="id") Long albumId) throws Exception{
        List<PhotoMeta> photosMetas = photosMetaRepository.getMetaByAlbumId(albumId);
        model.addAttribute("photos_meta",photosMetas);
        model.addAttribute("album",albumId);
        //TODO change hardcode
        byte[] cookie = cryptoUtils.generateCookie("zcbm000011112222".getBytes(),albumId);
        model.addAttribute("cookie", Base64.encodeBase64URLSafeString(cookie));
        return "album";
    }

    @GetMapping("/photo")
    public ResponseEntity<byte[]> getImage(@RequestParam(value = "id") Long photoId,
                                           @RequestParam(value = "album") Long albumId,
                                           @CookieValue("forester") String cookie) throws Exception{
        //TODO change hardcoded albumId && check cookie
        byte[] media = cryptoUtils.decryptPhoto(photoId,Base64.decodeBase64(cookie),albumId);
        HttpHeaders headers = new HttpHeaders();
        headers.setCacheControl(CacheControl.noCache().getHeaderValue());
        ResponseEntity<byte[]> responseEntity = new ResponseEntity(media, headers, HttpStatus.OK);
        return responseEntity;
    }
}
