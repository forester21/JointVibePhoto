package forester.jv.data.repository;

import forester.jv.data.entity.Avatar;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by FORESTER on 24.09.17.
 */
@Repository
public interface AvatarsRepository extends CrudRepository<Avatar,Long> {
}
