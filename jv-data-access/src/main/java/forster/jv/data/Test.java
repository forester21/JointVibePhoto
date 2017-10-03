package forster.jv.data;

import forster.jv.data.repository.UsersRepository;
import forster.jv.data.entity.Users;
import org.springframework.context.support.GenericXmlApplicationContext;

/**
 * Created by FORESTER on 24.09.17.
 */
public class Test {
    public static void main(String[] args) {
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.load("classpath:META-INF/data-context.xml");
        ctx.refresh();
        UsersRepository repo = ctx.getBean(UsersRepository.class);
        Users user = new Users();
        user.setLogin("forester22");
        user.setPassword("zcbm");
        user.setEmail("mail1");
        System.out.println(repo.save(user));
//        System.out.println(repo.findAll().iterator().next().getName());
    }
}
