package ro.agitman.account;

import com.ocpsoft.pretty.PrettyContext;
import com.ocpsoft.pretty.faces.annotation.URLAction;
import com.ocpsoft.pretty.faces.annotation.URLMapping;
import com.ocpsoft.pretty.faces.annotation.URLMappings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ro.agitman.AbstractMB;
import ro.agitman.dto.AdvertStatusEnum;
import ro.agitman.dto.DotariEnum;
import ro.agitman.facade.AdvertService;
import ro.agitman.model.Advert;
import ro.agitman.model.User;
import ro.agitman.pub.FileUploadBean;
import ro.agitman.util.RentUtils;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

/**
 * Created by edi on 2/11/2015.
 */
@ManagedBean
@ViewScoped
@URLMappings(mappings = {
        @URLMapping(id = "add", pattern = "/u/add", viewId = "/pages/user/add.xhtml?faces-redirect=true"),
        @URLMapping(id = "edit", pattern = "/u/edit/#{addMB.id}", viewId = "/pages/user/add.xhtml?faces-redirect=true"),
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

    private EnumSet<DotariEnum> dotariSelected = EnumSet.noneOf(DotariEnum.class);
    private boolean firstAdd = true;
    private Advert advert;
    private Long id;
    private User user;

    private List<Advert> myAds = null;
    private List<Advert> favAds = null;

    @PostConstruct
    public void init() {
        user = userMB.getUser();
    }

    @URLAction(onPostback = false)
    public void load() {
        String viewId = PrettyContext.getCurrentInstance().getCurrentMapping().getId();

        if ("add".equals(viewId)){
            advert = advertService.init();
            if (user.getPhone() == null || "".equals(user.getPhone())) {
                errorPersistRedirect("Trebuie sa iti adaugi numarul de telefon pentru a adauga un anunt");
                redirectPretty("settings");
            }
        }

        if ("edit".equals(viewId)) {
            advert = advertService.findForId(id);
            if (advert == null ||
                    !advert.getUser().getId().equals(user.getId()) ||
                    AdvertStatusEnum.REMOVED.equals(advert.getStatus()) ||
                    AdvertStatusEnum.EXPIRED.equals(advert.getStatus())) {
                advert = null;
                errorPersistRedirect("Anunt invalid");
                redirectPretty("add");
            } else {
                dotariSelected = RentUtils.loadDotari(advert);
                firstAdd = true;
            }
        }
    }

    public void save() {
        advert.setUser(user);
        advert.setDotari(RentUtils.criptDotari(dotariSelected));
        advertService.save(advert, fileUploadBean.getImages());
        fileUploadBean.clearAll();
        redirectPretty("home");
    }

    private void loadAds(int type) {
        switch (type) {
            case 1:
                myAds = advertService.findForUser(user);
                break;
            case 2:
                favAds = advertService.findFavoritesForUser(user);
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

    public EnumSet<DotariEnum> getDotariEnum(int cat){
        EnumSet<DotariEnum> result = EnumSet.noneOf(DotariEnum.class);
        for (DotariEnum dotare : DotariEnum.values()) {
            if(dotare.getCat() == cat)
                result.add(dotare);
        }
        return result;
    }

    public EnumSet<DotariEnum> getDotariSelected() {
        return dotariSelected;
    }

    public void setDotariSelected(EnumSet<DotariEnum> dotariSelected) {
        if (firstAdd) {
            this.dotariSelected.clear();
            firstAdd = false;
        }
        this.dotariSelected.addAll(dotariSelected);
    }

    public FileUploadBean getFileUploadBean() {
        return fileUploadBean;
    }

    public void setFileUploadBean(FileUploadBean fileUploadBean) {
        this.fileUploadBean = fileUploadBean;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
