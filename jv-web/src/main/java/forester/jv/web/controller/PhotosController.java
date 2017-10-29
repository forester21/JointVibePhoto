package forester.jv.web.controller;

/**
 * Created by FORESTER on 24.10.17.
 */
import java.io.IOException;
import java.math.BigInteger;
import java.util.Iterator;
import java.util.LinkedList;

import forester.jv.app.FileMeta;
import forester.jv.data.entity.Photo;
import forester.jv.data.repository.PhotosRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

@Controller
@RequestMapping("/photos")
public class PhotosController {

    final static Logger log = Logger.getLogger(PhotosController.class);

    LinkedList<FileMeta> files = new LinkedList<FileMeta>();
    FileMeta photoMeta = null;

    @Autowired
    private PhotosRepository photosRepository;
    /***************************************************
     * URL: /rest/controller/upload
     * upload(): receives files
//     * @param request : MultipartHttpServletRequest auto passed
     * @return LinkedList<FileMeta> as json format
     ****************************************************/
    @PostMapping(value="/upload")
    public @ResponseBody FileMeta uploadPOST(@RequestParam("fileupload") MultipartFile mpf) {

        //1. build an iterator
//        Iterator<String> itr =  request.getFileNames();
//        MultipartFile mpf = null;

        //2. get each file
//        while(itr.hasNext()){

            //2.1 get next MultipartFile
//            mpf = request.getFile(itr.next());

            log.info("Started handling photo:"+mpf.getName());

            //2.2 if files > 10 remove the first from the list
            if(files.size() >= 10)
                files.pop();

            //2.3 create new photoMeta
            photoMeta = new FileMeta();
            photoMeta.setFileName(mpf.getOriginalFilename());
            photoMeta.setFileSize(mpf.getSize()/1024+" Kb");
            photoMeta.setFileType(mpf.getContentType());

            Photo photo = new Photo();
            photo.setGroupId(new BigInteger("1"));
            photo.setUserId(new BigInteger("8"));
            try {
                photo.setPhoto(mpf.getBytes());
            } catch (IOException e) {
                log.error("Unable to create new Photo entity");
            }

            photosRepository.save(photo);

            //2.4 add to files
            files.add(photoMeta);
//        }
        // result will be like this
        // [{"fileName":"app_engine-85x77.png","fileSize":"8 Kb","fileType":"image/png"},...]
        return files.get(0);
    }

    @GetMapping(value = "/upload")
    public String uploadGET(){
        return "upload_photos.html";
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
//            response.setContentType(getFile.getFileType());
//            response.setHeader("Content-disposition", "attachment; filename=\""+getFile.getFileName()+"\"");
//            FileCopyUtils.copy(getFile.getBytes(), response.getOutputStream());
//        }catch (IOException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//    }
}
