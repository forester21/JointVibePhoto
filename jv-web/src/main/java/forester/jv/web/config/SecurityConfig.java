package forester.jv.web.config;

import forester.jv.data.config.AppConfig;
import org.hibernate.service.spi.InjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import javax.sql.DataSource;

/**
 * Created by FORESTER on 15.10.17.
 */
@Configuration
@EnableWebSecurity
@ComponentScan(basePackages = "forester.jv.web")
@Import({AppConfig.class})
public class SecurityConfig extends WebSecurityConfigurerAdapter{


    @Autowired
    DataSource dataSource;

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Autowired
    public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {

        auth
                .jdbcAuthentication()
                    .passwordEncoder(passwordEncoder())
                    .dataSource(dataSource)
                .usersByUsernameQuery(
                        "select login,password,'true' from users where login=?")
                .authoritiesByUsernameQuery(
                        "select login, 'ROLE_ADMIN' from users where login=?");
    }

    protected void configure(HttpSecurity http) throws Exception{
        http
                .authorizeRequests()
                    .antMatchers("/lib/**","/start","/registration","/photos/upload").permitAll()
                    .anyRequest().authenticated()
                    .and()
                .formLogin()
                    .loginPage("/start")
                    .permitAll()
                    .successForwardUrl("/albums")
                    .failureUrl("/lol")
                    .and()
                .logout()
                    .logoutUrl("/logout")
                    .logoutSuccessUrl("/start")
                    .invalidateHttpSession(true)
                    .and()
                .csrf().disable();
    }
}
