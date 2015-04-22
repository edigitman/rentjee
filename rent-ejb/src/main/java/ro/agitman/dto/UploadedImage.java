package ro.agitman.dto;

import java.io.InputStream;

/**
 * Created by edi on 4/18/2015.
 */
public class UploadedImage {

    private byte[] contents;
    private String contentType;
    private String fileName;
    private InputStream inputStream;
    private Long size;

    public UploadedImage(byte[] contents, String contentType, String fileName, InputStream inputStream, Long size) {
        this.contents = contents;
        this.contentType = contentType;
        this.fileName = fileName;
        this.inputStream = inputStream;
        this.size = size;
    }

    public byte[] getContents() {
        return contents;
    }

    public void setContents(byte[] contents) {
        this.contents = contents;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public InputStream getInputStream() {
        return inputStream;
    }

    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }
}
