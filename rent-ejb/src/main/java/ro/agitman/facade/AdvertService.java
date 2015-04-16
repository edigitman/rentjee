package ro.agitman.facade;

import ro.agitman.model.Advert;

import javax.ejb.Local;

/**
 * Created by edi on 2/11/2015.
 */
@Local
public interface AdvertService {

    Advert init();

    void save(Advert advert);
}
