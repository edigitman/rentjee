package ro.agitman.dto;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.io.Serializable;

/**
 * Created by edi on 4/18/2015.
 */
public class UploadedImage implements Serializable{

    private static Logger log = LoggerFactory.getLogger(UploadedImage.class);

    private byte[] contents;
    private String contentType;
    private String fileName;
    private Long size;

    public UploadedImage(byte[] contents, String contentType, String fileName, Long size) {
        this.contents = contents;
        this.contentType = contentType;
        this.fileName = fileName;
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

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }
}
