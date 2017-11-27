package forester.jv.data.entity;

import javax.persistence.*;
import java.math.BigInteger;

/**
 * Created by FORESTER on 26.11.17.
 */
@Entity
@Table(name = "photo_meta", schema = "public", catalog = "JV")
public class PhotoMeta {
    private Long photoId;
    private String name;
    private Long userId;
    private Long groupId;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "photo_id")
    public Long getPhotoId() {
        return photoId;
    }

    public void setPhotoId(Long photoId) {
        this.photoId = photoId;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "user_id")
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

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

        PhotoMeta photoMeta = (PhotoMeta) o;

        if (photoId != null ? !photoId.equals(photoMeta.photoId) : photoMeta.photoId != null) return false;
        if (name != null ? !name.equals(photoMeta.name) : photoMeta.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = photoId != null ? photoId.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }
}
