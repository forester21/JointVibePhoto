package forester.jv.app;

import java.math.BigInteger;

/**
 * Created by FORESTER on 29.10.17.
 */
public class FileMeta {
    private String name;
    private String size;
    private String type;

    public FileMeta(String name, String size, String type) {
        this.name = name;
        this.size = size;
        this.type = type;
    }

    public FileMeta() {
    }

    public FileMeta(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FileMeta fileMeta = (FileMeta) o;

        if (name != null ? !name.equals(fileMeta.name) : fileMeta.name != null) return false;
        if (size != null ? !size.equals(fileMeta.size) : fileMeta.size != null) return false;
        return type != null ? type.equals(fileMeta.type) : fileMeta.type == null;

    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (size != null ? size.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        return result;
    }
}
