package ro.agitman.pub;

import ro.agitman.facade.AdvertService;
import ro.agitman.model.Advert;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by edi on 2/10/2015.
 */
@ManagedBean
@ViewScoped
public class IndexMB implements Serializable{

    private List<Advert> adverts;

    @EJB
    private AdvertService advertService;

    @PostConstruct
    public void init(){
        adverts = advertService.findAll();
    }

    public List<Advert> getAdverts() {
        return adverts;
    }

    public void setAdverts(List<Advert> adverts) {
        this.adverts = adverts;
    }
}
