package forester.jv.data;

import forester.jv.data.config.AppConfig;
import forester.jv.data.repository.PhotosRepository;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.math.BigInteger;

/**
 * Created by FORESTER on 24.09.17.
 */
public class Test {
    public static void main(String[] args) {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
        PhotosRepository repo = ctx.getBean(PhotosRepository.class);
//        System.out.println(repo.getMetaByAlbumId(new BigInteger("4")));
    }
}
