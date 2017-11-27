package forester.jv.data.repository;

import forester.jv.data.entity.PhotoMeta;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.sql.ResultSet;
import java.util.List;

/**
 * Created by FORESTER on 26.11.17.
 */
@Repository
public interface PhotosMetaRepository extends CrudRepository<PhotoMeta, Long> {

    @Query("select pm from PhotoMeta pm where pm.groupId = :album_id")
    List<PhotoMeta> getMetaByAlbumId(@Param("album_id") Long albumId);
}
