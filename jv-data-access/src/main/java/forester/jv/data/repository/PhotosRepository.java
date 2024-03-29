package forester.jv.data.repository;

import forester.jv.data.entity.Photo;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.sql.ResultSet;
import java.util.List;

/**
 * Created by FORESTER on 24.09.17.
 */
@Repository
public interface PhotosRepository extends CrudRepository<Photo,Long> {

}
