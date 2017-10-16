package forester.jv.data;

import forester.jv.data.config.AppConfig;
import forester.jv.data.entity.User;
import forester.jv.data.repository.UsersRepository;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

/**
 * Created by FORESTER on 24.09.17.
 */
public class Test {
    public static void main(String[] args) {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
        UsersRepository repo = ctx.getBean(UsersRepository.class);
        User user = new User();
        user.setLogin("forester22");
        user.setPassword("zcbm");
        user.setEmail("mail1");
        System.out.println(repo.save(user));
        System.out.println(repo.findAll().iterator().next().getName());
    }
}
