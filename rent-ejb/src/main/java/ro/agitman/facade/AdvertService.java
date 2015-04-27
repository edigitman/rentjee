package ro.agitman.facade;

import ro.agitman.dto.UploadedImage;
import ro.agitman.model.Advert;
import ro.agitman.model.MdCity;
import ro.agitman.model.User;

import javax.ejb.Local;
import java.util.List;

/**
 * Created by edi on 2/11/2015.
 */
@Local
public interface AdvertService {

    Advert init();

    void save(Advert advert, List<UploadedImage> images);

    Advert findForId(Long id);

    List<Advert> findAll();

    List<Advert> findForUser(User user);

    List<Advert> findFavoritesForUser(User user);

    List<Advert> findDezByUser(User user);

    List<Advert> findSearch(Long cityId, Integer minPrice, Integer maxPrice, Boolean onlyImages);

    void markFav(User user, Advert advert, boolean active);

    int isFav(User user, Advert selected);
}
