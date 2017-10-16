package forester.jv.data.entity;

import javax.persistence.*;
import java.math.BigInteger;
import java.util.Arrays;

/**
 * Created by FORESTER on 24.09.17.
 */
@Entity
@Table(name = "avatars")
public class Avatar {
    private BigInteger avatarId;
    private byte[] avatar;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "avatar_id")
    public BigInteger getAvatarId() {
        return avatarId;
    }

    public void setAvatarId(BigInteger avatarId) {
        this.avatarId = avatarId;
    }

    @Basic
    @Column(name = "avatar")
    public byte[] getAvatar() {
        return avatar;
    }

    public void setAvatar(byte[] avatar) {
        this.avatar = avatar;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Avatar avatar = (Avatar) o;

        if (avatarId != null ? !avatarId.equals(avatar.avatarId) : avatar.avatarId != null) return false;
        if (!Arrays.equals(this.avatar, avatar.avatar)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = avatarId != null ? avatarId.hashCode() : 0;
        result = 31 * result + Arrays.hashCode(avatar);
        return result;
    }
}
