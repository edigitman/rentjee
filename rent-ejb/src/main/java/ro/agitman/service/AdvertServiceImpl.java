package ro.agitman.service;

import ro.agitman.dba.DataAccessService;
import ro.agitman.facade.AdvertService;
import ro.agitman.model.*;

import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 * Created by edi on 2/11/2015.
 */
@Stateless
public class AdvertServiceImpl implements AdvertService {

    @EJB
    private DataAccessService service;

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
    public void save(Advert advert) {
        if (advert.getId() == null) {
            service.create(advert);
        } else {
            service.update(advert);
        }
    }
}
