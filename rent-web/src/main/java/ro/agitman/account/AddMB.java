package ro.agitman.account;

import ro.agitman.facade.AdvertService;
import ro.agitman.model.Advert;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;

/**
 * Created by edi on 2/11/2015.
 */
@ManagedBean
@ViewScoped
public class AddMB implements Serializable{

    @EJB
    private AdvertService advertService;

    @ManagedProperty(value="#{userMB}")
    private UserMB userMB;

    private Advert advert;

    @PostConstruct
    public void init(){
        advert = advertService.init();
    }


    public String save(){
        advert.setUser(userMB.getUser());

        advertService.save(advert);

        return "user/home";
    }


    public Advert getAdvert() {
        return advert;
    }

    public void setAdvert(Advert advert) {
        this.advert = advert;
    }

    public UserMB getUserMB() {
        return userMB;
    }

    public void setUserMB(UserMB userMB) {
        this.userMB = userMB;
    }
}
