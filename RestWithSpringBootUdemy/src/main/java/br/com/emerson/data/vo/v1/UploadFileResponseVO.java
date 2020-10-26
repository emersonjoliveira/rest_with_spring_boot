package br.com.emerson.data.vo.v1;

import java.io.Serializable;
import java.util.Objects;

public class UploadFileResponseVO implements Serializable {

    private String fileName;
    private String fileDownloadURI;
    private String fileType;
    private Long size;

    public UploadFileResponseVO(String fileName, String fileDownloadURI, String fileType, Long size) {
        this.fileName = fileName;
        this.fileDownloadURI = fileDownloadURI;
        this.fileType = fileType;
        this.size = size;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileDownloadURI() {
        return fileDownloadURI;
    }

    public void setFileDownloadURI(String fileDownloadURI) {
        this.fileDownloadURI = fileDownloadURI;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        UploadFileResponseVO that = (UploadFileResponseVO) o;
        return Objects.equals(fileName, that.fileName) && Objects
                .equals(fileDownloadURI, that.fileDownloadURI) && Objects.equals(fileType, that.fileType) && Objects
                .equals(size, that.size);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fileName, fileDownloadURI, fileType, size);
    }
}
