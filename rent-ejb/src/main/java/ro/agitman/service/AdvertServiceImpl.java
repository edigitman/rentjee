package ro.agitman.service;

import ro.agitman.dba.DataAccessService;
import ro.agitman.dto.UploadedImage;
import ro.agitman.facade.AdvertService;
import ro.agitman.facade.ImageService;
import ro.agitman.facade.MailService;
import ro.agitman.model.*;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by edi on 2/11/2015.
 */
@Stateless
public class AdvertServiceImpl implements AdvertService {

    @EJB
    private DataAccessService service;
    @EJB
    private ImageService imageService;
    @EJB
    private MailService mailService;

    @Override
    public Advert init() {
        Advert advert = new Advert();
        advert.setAddress(new Address());
        advert.setInfo(new RentInfo());
        advert.setInterval(new RentInterval());
        advert.setValue(new RentValue());
        return advert;
    }

    @Override
    public void save(Advert advert, List<UploadedImage> images) {

        if (advert.getId() == null) {
            service.create(advert);
            imageService.uploadImages(advert, images);
            mailService.sendAdvertAdded(advert);
        } else {
            service.update(advert);
        }
    }

    public List<Advert> findAll() {
        return service.findAll(Advert.class);
    }

    public List<Advert> findForUser(User user) {
        Map<String, Object> map = new HashMap<>();
        map.put("user", user);
        return service.findWithNamedQuery(Advert.FIND_FOR_USER, map);
    }

    public List<Advert> findFavoritesForUser(User user) {
        return new ArrayList<>();
    }

    public List<Advert> findDezByUser(User user) {
        return new ArrayList<>();
    }


}
