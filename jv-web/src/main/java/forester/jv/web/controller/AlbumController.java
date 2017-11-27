package forester.jv.web.controller;

import forester.jv.app.FileMeta;
import forester.jv.data.entity.PhotoMeta;
import forester.jv.data.repository.PhotosMetaRepository;
import forester.jv.data.repository.PhotosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigInteger;
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

    @GetMapping
    public String album(Model model,
                        @RequestParam(value="id") Long albumId){
        List<PhotoMeta> photoMeta = photosMetaRepository.getMetaByAlbumId(albumId);
        model.addAttribute("photos_meta",photoMeta);
        return "album";
    }

    @GetMapping("/photo")
    public ResponseEntity<byte[]> getImage(@RequestParam(value = "id") Long photoId){
        byte[] media = photosRepository.findOne(photoId).getPhoto();
        HttpHeaders headers = new HttpHeaders();
        headers.setCacheControl(CacheControl.noCache().getHeaderValue());
        ResponseEntity<byte[]> responseEntity = new ResponseEntity(media, headers, HttpStatus.OK);
        return responseEntity;
    }
}
