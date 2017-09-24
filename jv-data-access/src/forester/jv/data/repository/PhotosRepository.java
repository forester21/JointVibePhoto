package forester.jv.data.repository;

import forester.jv.data.entity.Photos;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by FORESTER on 24.09.17.
 */
@Repository
public interface PhotosRepository extends CrudRepository<Photos,Long> {
}
