package forester.jv.data.entity;

import javax.persistence.*;
import java.math.BigInteger;
import java.util.Arrays;

/**
 * Created by FORESTER on 24.09.17.
 */
@Entity
public class Photos {
    private BigInteger photoId;
    private byte[] photo;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Photos photos = (Photos) o;

        if (photoId != null ? !photoId.equals(photos.photoId) : photos.photoId != null) return false;
        if (!Arrays.equals(photo, photos.photo)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = photoId != null ? photoId.hashCode() : 0;
        result = 31 * result + Arrays.hashCode(photo);
        return result;
    }
}
