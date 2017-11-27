package forester.jv.data.entity;

import javax.persistence.*;
import java.math.BigInteger;
import java.util.Arrays;

/**
 * Created by FORESTER on 26.11.17.
 */
@Entity
@Table(name = "photos", schema = "public", catalog = "JV")
public class Photo {
    private Long photoId;
    private byte[] photo;

    @Id
    @Column(name = "photo_id")
    public Long getPhotoId() {
        return photoId;
    }

    public void setPhotoId(Long photoId) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Photo photo = (Photo) o;

        if (photoId != null ? !photoId.equals(photo.photoId) : photo.photoId != null) return false;
        if (!Arrays.equals(this.photo, photo.photo)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = photoId != null ? photoId.hashCode() : 0;
        result = 31 * result + Arrays.hashCode(photo);
        return result;
    }
}
