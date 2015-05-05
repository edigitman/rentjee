package ro.agitman.pub;

import com.ocpsoft.pretty.faces.annotation.URLMapping;
import com.ocpsoft.pretty.faces.annotation.URLMappings;
import org.apache.commons.io.IOUtils;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;
import ro.agitman.dto.UploadedImage;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@ManagedBean
@SessionScoped
@URLMappings(mappings = {
        @URLMapping(id = "upload", pattern = "/upload", viewId = "/pages/upload.xhtml?faces-redirect=true")
})
public class FileUploadBean implements Serializable {

    private List<UploadedImage> images = new ArrayList<>();

    public void handleFileUpload(FileUploadEvent event) {
        UploadedFile file = event.getFile();
        System.out.println("handleFileUpload - " + file.getFileName());

        byte[] bytes;
        try {
            InputStream is = file.getInputstream();
            if (is != null) {
                bytes = IOUtils.toByteArray(is);
                is.close();
            } else {
                bytes = new byte[0];
            }
        } catch (IOException e) {
            e.printStackTrace();
            bytes = new byte[0];
        }
        images.add(new UploadedImage(bytes, file.getContentType(), file.getFileName(), file.getSize()));
    }

    public void remove(UploadedImage image) {
        images.remove(image);
    }


    public List<UploadedImage> getImages() {
        return images;
    }

    public void setImages(List<UploadedImage> images) {
        this.images = images;
    }
}
