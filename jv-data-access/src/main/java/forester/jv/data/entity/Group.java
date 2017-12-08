package forester.jv.data.entity;

import javax.persistence.*;
import java.math.BigInteger;

/**
 * Created by FORESTER on 24.09.17.
 */
@Entity
@Table(name = "groups")
public class Group {
    private String name;
    private Long groupId;
    private String joinKey;
    private Boolean encrypted;
    private String password;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "group_id")
    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    @Basic
    @Column(name = "join_key")
    public String getJoinKey() {
        return joinKey;
    }

    public void setJoinKey(String joinKey) {
        this.joinKey = joinKey;
    }

    @Basic
    @Column(name = "encrypted")
    public Boolean getEncrypted() {
        return encrypted;
    }

    public void setEncrypted(Boolean encrypted) {
        this.encrypted = encrypted;
    }

    @Basic
    @Column(name = "password")
    public String getPassword() {
        return password;
    }


    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Group group = (Group) o;

        if (name != null ? !name.equals(group.name) : group.name != null) return false;
        if (groupId != null ? !groupId.equals(group.groupId) : group.groupId != null) return false;
        if (joinKey != null ? !joinKey.equals(group.joinKey) : group.joinKey != null) return false;
        if (encrypted != null ? !encrypted.equals(group.encrypted) : group.encrypted != null) return false;
        return password != null ? password.equals(group.password) : group.password == null;

    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (groupId != null ? groupId.hashCode() : 0);
        result = 31 * result + (joinKey != null ? joinKey.hashCode() : 0);
        result = 31 * result + (encrypted != null ? encrypted.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        return result;
    }
}
