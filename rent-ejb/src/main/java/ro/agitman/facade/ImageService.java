package ro.agitman.facade;

import ro.agitman.dto.UploadedImage;
import ro.agitman.model.Advert;
import ro.agitman.model.User;

import javax.ejb.Local;
import java.util.List;

/**
 * Created by edi on 4/18/2015.
 */
@Local
public interface ImageService {

    void uploadImages(Advert advert, List<UploadedImage> images);

}
