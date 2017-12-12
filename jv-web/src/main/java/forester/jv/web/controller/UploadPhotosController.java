package forester.jv.web.controller;

/**
 * Created by FORESTER on 24.10.17.
 */
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import forester.jv.app.crypto.CryptoUtils;
import forester.jv.data.FileMeta;
import forester.jv.data.entity.Photo;
import forester.jv.data.entity.PhotoMeta;
import forester.jv.data.repository.GroupsRepository;
import forester.jv.data.repository.PhotosMetaRepository;
import forester.jv.data.repository.PhotosRepository;
import org.apache.commons.codec.binary.Base64;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.HttpServletRequest;

import static forester.jv.web.controller.AlbumController.COOKIE_NAME;

@Controller
@RequestMapping("/upload")
public class UploadPhotosController {

    final static Logger log = Logger.getLogger(UploadPhotosController.class);

    @Autowired
    private PhotosRepository photosRepository;

    @Autowired
    private PhotosMetaRepository metaRepository;

    @Autowired
    private GroupsRepository groupsRepository;

    @Autowired
    private CryptoUtils cryptoUtils;

    /***************************************************
     * URL: /rest/controller/upload
     * upload(): receives files
//     * @param request : MultipartHttpServletRequest auto passed
     * @return LinkedList<FileMeta> as json format
     ****************************************************/

    @PostMapping
    @ResponseBody
    public ResponseEntity<List<FileMeta>> uploadPOST(@RequestParam("fileupload") List<MultipartFile> mpf,
                                                     @RequestParam("id") Long albumId,
                                                     HttpServletRequest request) throws Exception{

        log.info("Started handling photos");

        String cookie = "";
        if (cryptoUtils.isEncrypted(albumId)){
            cookie = WebUtils.getCookie(request,COOKIE_NAME+albumId).getValue();
        }

        ArrayList<FileMeta> files = new ArrayList<FileMeta>(mpf.size());

        for(MultipartFile file : mpf){
            FileMeta photoMeta = new FileMeta();
            photoMeta.setName(file.getOriginalFilename());
            photoMeta.setSize(file.getSize()/1024+" Kb");
            photoMeta.setType(file.getContentType());
            files.add(photoMeta);
            try {
                PhotoMeta photoMetaEntity = new PhotoMeta();
                photoMetaEntity.setName(file.getOriginalFilename());
                photoMetaEntity.setGroupId(albumId);
                Photo photo = new Photo();
                byte[] photoBytes = file.getBytes();
                if (cryptoUtils.isEncrypted(albumId)){
                    photoBytes = cryptoUtils.encryptPhoto(photoBytes, Base64.decodeBase64(cookie),albumId);
                }
                Long photoId = metaRepository.save(photoMetaEntity).getPhotoId();
                photo.setPhoto(photoBytes);
                photo.setPhotoId(photoId);
                photosRepository.save(photo);
            } catch (IOException e) {
                log.error("Unable to create new Photo entity");
            }
        }

        // result will be like this
        // [{"fileName":"app_engine-85x77.png","fileSize":"8 Kb","fileType":"image/png"},...]
        return new ResponseEntity<List<FileMeta>>(files, HttpStatus.OK);
    }

    @GetMapping
    public String uploadGET(@RequestParam("id") Long albumId,
                            Model model){
        model.addAttribute("album",albumId);
        return "upload_photos";
    }
}
