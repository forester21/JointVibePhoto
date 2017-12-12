package forester.jv.app.config;

import forester.jv.app.crypto.CryptoUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Created by FORESTER on 07.12.17.
 */
@Configuration
@ComponentScan(basePackages = "forester.jv.app")
public class SpringCryptoConfig {

    @Bean
    public CryptoUtils cryptoUtils() throws Exception{
        return new CryptoUtils();
    }
}
