package forester.jv.web.controller;

/**
 * Created by FORESTER on 24.10.17.
 */
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import forester.jv.app.FileMeta;
import forester.jv.data.entity.Photo;
import forester.jv.data.entity.PhotoMeta;
import forester.jv.data.repository.PhotosMetaRepository;
import forester.jv.data.repository.PhotosRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/upload")
public class UploadPhotosController {

    final static Logger log = Logger.getLogger(UploadPhotosController.class);

    LinkedList<FileMeta> files = new LinkedList<FileMeta>();

    @Autowired
    private PhotosRepository photosRepository;

    @Autowired
    private PhotosMetaRepository metaRepository;

    /***************************************************
     * URL: /rest/controller/upload
     * upload(): receives files
//     * @param request : MultipartHttpServletRequest auto passed
     * @return LinkedList<FileMeta> as json format
     ****************************************************/

    @PostMapping
    @ResponseBody
    public ResponseEntity<List<FileMeta>> uploadPOST(@RequestParam("fileupload") List<MultipartFile> mpf){

        //1. build an iterator
//        Iterator<String> itr =  request.getFileNames();
//        MultipartFile mpf = null;

        //2. get each file
//        while(itr.hasNext()){

            //2.1 get next MultipartFile
//            mpf = request.getFile(itr.next());

            log.info("Started handling photos");
            log.info("files list size:"+files.size());

            //2.2 if files > 10 remove the first from the list
            if(files.size() >= 10)
                files.pop();

            //2.3 create new photoMeta
            for(MultipartFile file : mpf){
                FileMeta photoMeta = new FileMeta();
                photoMeta.setName(file.getOriginalFilename());
                photoMeta.setSize(file.getSize()/1024+" Kb");
                photoMeta.setType(file.getContentType());
                files.add(photoMeta);
                try {
                    PhotoMeta photoMetaEntity = new PhotoMeta();
                    photoMetaEntity.setName(file.getOriginalFilename());
                    photoMetaEntity.setGroupId(new Long("4"));
                    Photo photo = new Photo();
                    Long photoId = metaRepository.save(photoMetaEntity).getPhotoId();
                    photo.setPhoto(file.getBytes());
                    photo.setPhotoId(photoId);
                    photosRepository.save(photo);
                } catch (IOException e) {
                    log.error("Unable to create new Photo entity");
                }
            }

//        }
        // result will be like this
        // [{"fileName":"app_engine-85x77.png","fileSize":"8 Kb","fileType":"image/png"},...]
        return new ResponseEntity<List<FileMeta>>(files, HttpStatus.OK);
    }

    @GetMapping
    public String uploadGET(@RequestParam("id") Long albumId){
        return "upload_photos";
    }

//    /***************************************************
//     * URL: /rest/controller/get/{value}
//     * get(): get file as an attachment
//     * @param response : passed by the server
//     * @param value : value from the URL
//     * @return void
//     ****************************************************/
//    @RequestMapping(value = "/get/{value}", method = RequestMethod.GET)
//    public void get(HttpServletResponse response,@PathVariable String value){
//        FileMeta getFile = files.get(Integer.parseInt(value));
//        try {
//            response.setContentType(getFile.getType());
//            response.setHeader("Content-disposition", "attachment; filename=\""+getFile.getName()+"\"");
//            FileCopyUtils.copy(getFile.getBytes(), response.getOutputStream());
//        }catch (IOException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//    }
}
