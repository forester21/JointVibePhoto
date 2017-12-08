package forester.jv.web;

import org.apache.commons.codec.binary.Base64;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Created by FORESTER on 24.09.17.
 */
public class Test {
    public static void main(String[] args) {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode("hello");
        System.out.println(encodedPassword);
        System.out.println(passwordEncoder.matches("hello",encodedPassword));
    }
}
