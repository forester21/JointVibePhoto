package forester.jv.data.repository;

import forester.jv.data.entity.Users;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by FORESTER on 24.09.17.
 */
@Repository
public interface UsersRepository extends CrudRepository<Users,Long> {
}
