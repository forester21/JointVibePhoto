package forester.jv.data.entity;

import javax.persistence.*;
import java.math.BigInteger;
import java.util.Arrays;

/**
 * Created by FORESTER on 24.09.17.
 */
@Entity
@Table(name = "photos")
public class Photo {
    private BigInteger photoId;
    private byte[] photo;
    private BigInteger groupId;
    private BigInteger userId;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "photo_id")
    public BigInteger getPhotoId() {
        return photoId;
    }

    public void setPhotoId(BigInteger photoId) {
        this.photoId = photoId;
    }

    @Basic
    @Column(name = "photo")
    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }

    @Column(name = "group_id")
    public BigInteger getGroupId() {
        return groupId;
    }

    public void setGroupId(BigInteger groupId) {
        this.groupId = groupId;
    }

    @Column(name = "user_id")
    public BigInteger getUserId() {
        return userId;
    }

    public void setUserId(BigInteger userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Photo photo1 = (Photo) o;

        if (photoId != null ? !photoId.equals(photo1.photoId) : photo1.photoId != null) return false;
        if (!Arrays.equals(photo, photo1.photo)) return false;
        if (groupId != null ? !groupId.equals(photo1.groupId) : photo1.groupId != null) return false;
        return userId != null ? userId.equals(photo1.userId) : photo1.userId == null;

    }

    @Override
    public int hashCode() {
        int result = photoId != null ? photoId.hashCode() : 0;
        result = 31 * result + Arrays.hashCode(photo);
        result = 31 * result + (groupId != null ? groupId.hashCode() : 0);
        result = 31 * result + (userId != null ? userId.hashCode() : 0);
        return result;
    }
}
