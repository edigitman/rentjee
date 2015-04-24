package ro.agitman.service;

import ro.agitman.dba.DataAccessService;
import ro.agitman.dto.UploadedImage;
import ro.agitman.facade.AdvertService;
import ro.agitman.facade.ImageService;
import ro.agitman.facade.MailService;
import ro.agitman.model.*;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.math.BigDecimal;
import java.util.*;

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
            advert.setDateCreated(new Date());
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

    @Override
    public Advert findForId(Long id) {
        return service.find(Advert.class, id);
    }

    public List<Advert> findForUser(User user) {
        Map<String, Object> map = new HashMap<>();
        map.put("user", user);
        return service.findWithNamedQuery(Advert.FIND_FOR_USER, map);
    }

    public List<Advert> findFavoritesForUser(User user) {
        Map<String, Object> map = new HashMap<>();
        map.put("user", user);
        return service.findWithNamedQuery(Advert.FIND_FAV_BY_USER, map);
    }

    public List<Advert> findDezByUser(User user) {
        return new ArrayList<>();
    }

    public List<Advert> findSearch(MdCity city, Integer minPrice, Integer maxPrice, Boolean onlyImages) {
        Map<String, Object> map = new HashMap<>();
        if (minPrice == null) {
            minPrice = 10;
        }
        if (maxPrice == null) {
            maxPrice = 1000;
        }
        map.put("city", city);
        map.put("minPrice", new BigDecimal(minPrice));
        map.put("maxPrice", new BigDecimal(maxPrice));
        map.put("img", onlyImages ? 1 : 0);

        return service.findWithNamedQuery(Advert.FIND_SEARCH, map);
    }

    public void markFav(User user, Advert advert, boolean active) {
        if (active) {
            Map<String, Object> map = new HashMap<>();
            map.put("advert", advert);
            map.put("user", user);
            service.deleteWithQuery(RentFavorite.DELETE_ONE, map);
        } else {
            RentFavorite favorite = new RentFavorite();
            favorite.setAdvert(advert);
            favorite.setUser(user);
            favorite.setDateCreated(new Date());
            service.create(favorite);
        }
    }

    public int isFav(User user, Advert selected) {
        Map<String, Object> map = new HashMap<>();
        map.put("user", user);
        map.put("advert", selected);

        List<RentFavorite> list = service.findWithNamedQuery(RentFavorite.FIND_ONE, map);
        return list.size();
    }
}
