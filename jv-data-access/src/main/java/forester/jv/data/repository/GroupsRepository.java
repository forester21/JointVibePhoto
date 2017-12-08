package forester.jv.data.repository;

import forester.jv.data.entity.Group;
import forester.jv.data.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by FORESTER on 24.09.17.
 */
@Repository
public interface GroupsRepository extends CrudRepository<Group,Long> {
    @Query("select g from UsersGroupsAccess ga, User u, Group g where ga.userId = u.userId " +
            "and u.login = :login and ga.groupId = g.groupId")
    List<Group> findAlbumsByLogin(@Param("login") String login);

    @Query("select g from UsersGroupsAccess g, User u where g.userId = u.userId " +
            "and u.login = :login")
    void addNewAlbum(@Param("login") String login);
}
