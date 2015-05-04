package ro.agitman.service;

import org.imgscalr.Scalr;
import ro.agitman.dba.DataAccessService;
import ro.agitman.dto.UploadedImage;
import ro.agitman.facade.ImageService;
import ro.agitman.model.Advert;
import ro.agitman.model.Image;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;


/**
 * Created by edi on 4/18/2015.
 */
@Stateless
public class ImageServiceImpl implements ImageService {

    @EJB
    private DataAccessService service;
    @EJB
    private ClaudinaryManager claudinaryManager;

    public ImageServiceImpl() {
    }

    public Boolean delete(List<UploadedImage> image) {
        //List<String> ids = new ArrayList<>();
//        try {
//            Api.ApiResponse response = claudinary.api().deleteResources(ids, Cloudinary.emptyMap());
//            return true;
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

        return false;
    }

    private Image buildImage(Map<String, Object> map) {
        Image image = new Image();

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        if (map.containsKey("public_id"))
            image.setPublicId(map.get("public_id").toString());
        if (map.containsKey("version"))
            image.setVersion(new BigDecimal(map.get("version").toString()).longValue());
        if (map.containsKey("signature"))
            image.setSignature(map.get("signature").toString());
        if (map.containsKey("width"))
            image.setWidth(new BigDecimal(map.get("width").toString()).longValue());
        if (map.containsKey("height"))
            image.setHeight(new BigDecimal(map.get("height").toString()).longValue());
        if (map.containsKey("format"))
            image.setFormat(map.get("format").toString());
        if (map.containsKey("resource_type"))
            image.setResourceType(map.get("resource_type").toString());
        try {
            if (map.containsKey("created_at")) {
                String dateStr = map.get("created_at").toString();
                dateStr = dateStr.replace("T", " ");
                dateStr = dateStr.replace("Z", "");
                image.setCreatedAt(dateFormat.parse(dateStr));
            }
        } catch (ParseException e) {
        }
        if (map.containsKey("bytes"))
            image.setBytes(new BigDecimal(map.get("bytes").toString()).longValue());
        if (map.containsKey("type"))
            image.setType(map.get("type").toString());
        if (map.containsKey("url"))
            image.setUrl(map.get("url").toString());
        if (map.containsKey("url"))
            image.setUrlSmall(map.get("url").toString().replace("upload/", "upload/w_100/"));
        if (map.containsKey("secure_url"))
            image.setSecureUrl(map.get("secure_url").toString());
        if (map.containsKey("etag"))
            image.setEtag(map.get("etag").toString());

        return image;
    }

    @Override
    public void uploadImages(Advert advert, List<UploadedImage> images) {
        for (UploadedImage image : images) {
            try(InputStream is = new ByteArrayInputStream(image.getContents())) {
                Map<String, Objects> map = new HashMap<>();
                InputStream is = new ByteArrayInputStream(image.getContents());
                BufferedImage img = ImageIO.read(is);
                BufferedImage thumbnail = Scalr.resize(img, 640);
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                ImageIO.write(thumbnail, "jpg", baos);
                baos.flush();
                Image imageResult = buildImage(claudinaryManager.getClaudinary().uploader().upload(baos.toByteArray(), map));
                baos.close();
                imageResult.setAdvert(advert);
                service.create(imageResult);
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
