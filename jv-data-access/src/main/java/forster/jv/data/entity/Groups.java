package forster.jv.data.entity;

import javax.persistence.*;
import java.math.BigInteger;

/**
 * Created by FORESTER on 24.09.17.
 */
@Entity
public class Groups {
    private BigInteger groupId;
    private String joinKey;
    private Boolean encrypted;
    private String password;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "group_id")
    public BigInteger getGroupId() {
        return groupId;
    }

    public void setGroupId(BigInteger groupId) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Groups groups = (Groups) o;

        if (groupId != null ? !groupId.equals(groups.groupId) : groups.groupId != null) return false;
        if (joinKey != null ? !joinKey.equals(groups.joinKey) : groups.joinKey != null) return false;
        if (encrypted != null ? !encrypted.equals(groups.encrypted) : groups.encrypted != null) return false;
        if (password != null ? !password.equals(groups.password) : groups.password != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = groupId != null ? groupId.hashCode() : 0;
        result = 31 * result + (joinKey != null ? joinKey.hashCode() : 0);
        result = 31 * result + (encrypted != null ? encrypted.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        return result;
    }
}
