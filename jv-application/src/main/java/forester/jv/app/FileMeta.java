package forester.jv.app;

/**
 * Created by FORESTER on 29.10.17.
 */
public class FileMeta {
    private String fileName;
    private String fileSize;
    private String fileType;

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileSize() {
        return fileSize;
    }

    public void setFileSize(String fileSize) {
        this.fileSize = fileSize;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FileMeta fileMeta = (FileMeta) o;

        if (fileName != null ? !fileName.equals(fileMeta.fileName) : fileMeta.fileName != null) return false;
        if (fileSize != null ? !fileSize.equals(fileMeta.fileSize) : fileMeta.fileSize != null) return false;
        return fileType != null ? fileType.equals(fileMeta.fileType) : fileMeta.fileType == null;

    }

    @Override
    public int hashCode() {
        int result = fileName != null ? fileName.hashCode() : 0;
        result = 31 * result + (fileSize != null ? fileSize.hashCode() : 0);
        result = 31 * result + (fileType != null ? fileType.hashCode() : 0);
        return result;
    }
}
