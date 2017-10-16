package forester.jv.web.config;

import org.hibernate.service.spi.InjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import javax.sql.DataSource;

/**
 * Created by FORESTER on 15.10.17.
 */
@Configuration
@EnableWebSecurity
@ComponentScan(basePackages = "forester.jv")
public class SecurityConfig extends WebSecurityConfigurerAdapter{


    @Bean
    public UserDetailsService userDetailsService(){
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        manager.createUser(User.withUsername("user").password("password").roles("USER").build());
        return manager;
    }

//    @Autowired
//    DataSource dataSource;

//    @Autowired
//    public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
//
//        auth.jdbcAuthentication().dataSource(dataSource)
//                .usersByUsernameQuery(
//                        "select login,password,'true' from users where login=?")
//                .authoritiesByUsernameQuery(
//                        "select login, 'ROLE_ADMIN' from users where login=?");
//    }

    protected void configure(HttpSecurity http) throws Exception{
        http
                .authorizeRequests()
                    .antMatchers("/lib/**","/start").permitAll()
                    .anyRequest().authenticated()
                    .and()
                .formLogin()
                    .loginPage("/start")
                    .permitAll()
                    .successForwardUrl("/start")
                    .failureUrl("/start")
                    .and()
                .logout()
                    .logoutUrl("/logout")
                    .logoutSuccessUrl("/start")
                    .invalidateHttpSession(true)
                    .and()
                .csrf().disable();
    }
}
