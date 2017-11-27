package forester.jv.data.repository;

import forester.jv.data.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Created by FORESTER on 24.09.17.
 */
@Repository
public interface UsersRepository extends CrudRepository<User,Long> {
    @Query("select u from User u where u.login = :login")
    User findByLogin(@Param("login") String login);
}
