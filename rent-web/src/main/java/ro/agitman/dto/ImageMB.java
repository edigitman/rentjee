package ro.agitman.dto;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseId;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.*;

/**
 * Created by edi on 4/18/2015.
 */
@ManagedBean(name = "imageMB")
@SessionScoped
public class ImageMB implements Serializable {

    private Map<Integer, UploadedFile> fileMap = new HashMap<>();

    public void clear() {
        fileMap = new HashMap<>();
    }

    public void addFile(UploadedFile file) {
        fileMap.put(fileMap.keySet().size(), file);
    }

   public void setFiles(List<UploadedFile> files) {
       this.clear();
       for (UploadedFile file : files) {
           fileMap.put(files.indexOf(file), file);
       }
   }

    public Set<Integer> indexes(){
        return fileMap.keySet();
    }

    public StreamedContent getStreamedImageById(Integer id) throws IOException {
        FacesContext context = FacesContext.getCurrentInstance();

        if (context.getCurrentPhaseId() == PhaseId.RENDER_RESPONSE) {
            // So, we're rendering the view. Return a stub StreamedContent so that it will generate right URL.
            return new DefaultStreamedContent();
        } else {
            // So, browser is requesting the image. Get ID value from actual request param.
            return new DefaultStreamedContent(new ByteArrayInputStream(fileMap.get(id).getContents()) , "image/jpeg");
        }
    }

    public boolean canRender(){
        return !fileMap.keySet().isEmpty();
    }
}
