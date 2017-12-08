package forester.jv.data;

import forester.jv.data.config.AppConfig;
import forester.jv.data.repository.PhotosRepository;
import org.apache.commons.codec.binary.Base64;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.math.BigInteger;

/**
 * Created by FORESTER on 24.09.17.
 */
public class Test {
    public static void main(String[] args) {
//        ApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
//        PhotosRepository repo = ctx.getBean(PhotosRepository.class);
//        System.out.println(repo.getMetaByAlbumId(new BigInteger("4")));
        byte[] bytes = new byte[]{(byte)0x01, (byte)0xFA};
        byte[] encodedBytes = Base64.encodeBase64(bytes);
        System.out.println("encodedBytes " + new String(encodedBytes));
        byte[] decodedBytes = Base64.decodeBase64(encodedBytes);
        System.out.println("decodedBytes " + new String(decodedBytes));
    }
}
