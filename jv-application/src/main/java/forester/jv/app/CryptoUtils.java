package forester.jv.app;

import forester.jv.data.repository.GroupsRepository;
import forester.jv.data.repository.PhotosRepository;
import forester.jv.data.repository.UsersRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.StreamUtils;
import org.springframework.util.StringUtils;

import javax.crypto.*;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.security.NoSuchAlgorithmException;
import java.security.spec.KeySpec;
import java.util.HashMap;

/**
 * Created by FORESTER on 06.12.17.
 */
public class CryptoUtils {

    private static int buffSize = 1024;

    final static Logger log = Logger.getLogger(CryptoUtils.class);

    private HashMap<Long,SecretKey> cookieKeys;

    @Autowired
    private PhotosRepository photoRepository;

    @Autowired
    private GroupsRepository groupsRepository;

    public CryptoUtils() throws NoSuchPaddingException, NoSuchAlgorithmException {
        cookieKeys = new HashMap<Long, SecretKey>();
    }

    public byte[] encryptPhoto(byte[] photo, byte[] cookie, Long albumId) throws Exception{
        byte[] photoKeyBytes = decryptCookie(cookie,albumId);
        SecretKey photoKey = generateKeyByPassword(photoKeyBytes,albumId);
        return encrypt(photoKey,photo);
    }

    public byte[] decryptPhoto(Long photoId, byte[] cookie, Long albumId) throws Exception{
        byte[] photoKeyBytes = decryptCookie(cookie,albumId);
        SecretKey photoKey = generateKeyByPassword(photoKeyBytes,albumId);
        byte[] photo = photoRepository.findOne(photoId).getPhoto();
        return decrypt(photoKey,photo);
    }

    public byte[] generateCookie(byte[] albumKey, Long albumId) throws Exception {
        SecretKey key = KeyGenerator.getInstance("AES").generateKey();
        cookieKeys.put(albumId,key);
        return encrypt(key, albumKey);
    }

    private byte[] decryptCookie(byte[] cookie, Long albumId) throws Exception{
        SecretKey cookieKey = cookieKeys.get(albumId);
        if (StringUtils.isEmpty(cookie)){
            log.error("cookie string is empty!");
            return null;
        }
        if (cookieKey != null){
            return decrypt(cookieKey,cookie);
        } else {
            //TODO check session timeout and notify user
            log.error("Session timeout, reenter key");
            return null;
        }
    }

    public boolean checkCookie(Long albumId){
        if (cookieKeys.containsKey(albumId))
            return true;
        return false;
    }

    public boolean isEncrypted(Long albumId){
        return groupsRepository.findOne(albumId).getEncrypted();
    }

    private byte[] crypt(SecretKey key, byte[] inputBytes, int cryptMode) throws Exception{
        Cipher encryptCipher = Cipher.getInstance("AES");
        encryptCipher.init(cryptMode, key);
        ByteArrayInputStream in = new ByteArrayInputStream(inputBytes);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        CipherOutputStream cos = null;
        try {
            cos = new CipherOutputStream(out, encryptCipher);
            byte buff[] = new byte[buffSize];
            int tmpRead;

            while ((tmpRead = in.read(buff,0,buffSize))!= -1){
                cos.write(buff,0,tmpRead);
            }
        } finally {
            if (cos != null)
                cos.close();
        }
        return out.toByteArray();
    }

    private byte[] encrypt(SecretKey key, byte[] inputBytes) throws Exception{
        return crypt(key,inputBytes,Cipher.ENCRYPT_MODE);
    }

    private byte[] decrypt(SecretKey key, byte[] inputBytes) throws Exception{
        return crypt(key,inputBytes,Cipher.DECRYPT_MODE);
    }

    private SecretKey generateKeyByPassword(byte[] keyBytes, Long albumId) throws Exception{
        //TODO maybe modify this part (salt or smth else)
        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
        String salt = "testSalt";
        KeySpec spec = new PBEKeySpec(new String(keyBytes).toCharArray(), salt.getBytes(), 65536, 128);
        SecretKey tmp = factory.generateSecret(spec);
        return new SecretKeySpec(tmp.getEncoded(), "AES");
    }
}
