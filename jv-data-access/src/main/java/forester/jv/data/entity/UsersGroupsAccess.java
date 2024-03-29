package forester.jv.data.entity;

import javax.persistence.*;
import java.math.BigInteger;

/**
 * Created by FORESTER on 24.09.17.
 */
@Entity
@Table(name = "users_groups_access", schema = "public", catalog = "JV")
@IdClass(UsersGroupsAccessPK.class)
public class UsersGroupsAccess {

    public UsersGroupsAccess(Long userId, Long groupId) {
        this.userId = userId;
        this.groupId = groupId;
    }

    public UsersGroupsAccess(){}

    private Long userId;
    private Long groupId;

    @Id
    @Column(name = "user_id")
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Id
    @Column(name = "group_id")
    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UsersGroupsAccess that = (UsersGroupsAccess) o;

        if (userId != null ? !userId.equals(that.userId) : that.userId != null) return false;
        if (groupId != null ? !groupId.equals(that.groupId) : that.groupId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = userId != null ? userId.hashCode() : 0;
        result = 31 * result + (groupId != null ? groupId.hashCode() : 0);
        return result;
    }
}
