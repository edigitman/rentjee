package ro.agitman.account;

import com.ocpsoft.pretty.faces.annotation.URLMapping;
import com.ocpsoft.pretty.faces.annotation.URLMappings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ro.agitman.AbstractMB;
import ro.agitman.dto.DotariEnum;
import ro.agitman.facade.AdvertService;
import ro.agitman.model.Advert;
import ro.agitman.pub.FileUploadBean;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by edi on 2/11/2015.
 */
@ManagedBean
@ViewScoped
@URLMappings(mappings = {
        @URLMapping(id = "add", pattern = "/u/add", viewId = "/pages/user/add.xhtml?faces-redirect=true"),
        @URLMapping(id = "home", pattern = "/u/home", viewId = "/pages/user/home.xhtml?faces-redirect=true")
})
public class AddMB extends AbstractMB implements Serializable {

    private static Logger log = LoggerFactory.getLogger(AddMB.class);

    @EJB
    private AdvertService advertService;

    @ManagedProperty(value = "#{userMB}")
    private UserMB userMB;

    @ManagedProperty(value = "#{fileUploadBean}")
    private FileUploadBean fileUploadBean;

    private List<DotariEnum> dotariSelected = new ArrayList<>();
    private Advert advert;

    private List<Advert> myAds = null;
    private List<Advert> favAds = null;
    private List<Advert> dezAds = null;

    @PostConstruct
    public void init() {
        advert = advertService.init();
    }

    public void save() {
        advert.setUser(userMB.getUser());
        advert.setDotari(mapDotari());
        advertService.save(advert, fileUploadBean.getImages());
        fileUploadBean.clearAll();
        redirectPretty("home");
    }

    private long mapDotari() {
        long result = 1 << 30;
        for (DotariEnum d : DotariEnum.values()) {
            result = (result << 1) | (dotariSelected.contains(d) ? 1 : 0);
        }
        return result;
    }

    private void loadAds(int type) {
        switch (type) {
            case 1:
                myAds = advertService.findForUser(userMB.getUser());
                break;
            case 2:
                favAds = advertService.findFavoritesForUser(userMB.getUser());
                break;
            case 3:
                dezAds = advertService.findDezByUser(userMB.getUser());
                break;
        }
    }

    public List<Advert> getMyAds() {
        if (myAds == null) {
            loadAds(1);
        }
        return myAds;
    }

    public void setMyAds(List<Advert> myAds) {
        this.myAds = myAds;
    }

    public List<Advert> getFavAds() {
        if (favAds == null) {
            loadAds(2);
        }
        return favAds;
    }

    public void setFavAds(List<Advert> favAds) {
        this.favAds = favAds;
    }

    public List<Advert> getDezAds() {
        if (dezAds == null) {
            loadAds(3);
        }
        return dezAds;
    }

    public void setDezAds(List<Advert> dezAds) {
        this.dezAds = dezAds;
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

    public List<SelectItem> getDotari(int cat) {
        List<SelectItem> result = new ArrayList<>();
        for (DotariEnum dotare : DotariEnum.values()) {
            if(dotare.getCat() == cat)
                result.add(new SelectItem(dotare, dotare.gethName()));
        }
        return result;
    }

    public List<DotariEnum> getDotariSelected() {
        return dotariSelected;
    }

    public void setDotariSelected(List<DotariEnum> dotariSelected) {
        this.dotariSelected = dotariSelected;
    }

    public FileUploadBean getFileUploadBean() {
        return fileUploadBean;
    }

    public void setFileUploadBean(FileUploadBean fileUploadBean) {
        this.fileUploadBean = fileUploadBean;
    }
}
