package ro.agitman.account;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;
import ro.agitman.AbstractMB;
import ro.agitman.dto.DotariEnum;
import ro.agitman.dto.UploadedImage;
import ro.agitman.facade.AdvertService;
import ro.agitman.model.Advert;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by edi on 2/11/2015.
 */
@ManagedBean
@ViewScoped
public class AddMB extends AbstractMB implements Serializable {

    @EJB
    private AdvertService advertService;

    @ManagedProperty(value = "#{userMB}")
    private UserMB userMB;

    private List<DotariEnum> dotariSelected = new ArrayList<>();
    private List<UploadedImage> files = new ArrayList<>();
    private Boolean filesUploaded = false;
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
        advertService.save(advert, files);

        redirect("/pages/user/home");
    }

    private long mapDotari() {
        long result = 1 << 30;
        for (DotariEnum d : DotariEnum.values()) {
            result = (result << 1) | (dotariSelected.contains(d)? 1 : 0);
        }
        return result;
    }

    public void handleFileUpload(FileUploadEvent event) throws IOException {
        UploadedFile f = event.getFile();
        files.add(new UploadedImage(f.getContents(), f.getContentType(), f.getFileName(), f.getInputstream(), f.getSize()));
        filesUploaded = true;
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

    public List<SelectItem> getDotari() {
        List<SelectItem> result = new ArrayList<>();
        for (DotariEnum dotare : DotariEnum.values()) {
            result.add(new SelectItem(dotare, dotare.name()));
        }
        return result;
    }

    public Boolean getFilesUploaded() {
        return filesUploaded;
    }

    public void setFilesUploaded(Boolean filesUploaded) {
        this.filesUploaded = filesUploaded;
    }

    public List<DotariEnum> getDotariSelected() {
        return dotariSelected;
    }

    public void setDotariSelected(List<DotariEnum> dotariSelected) {
        this.dotariSelected = dotariSelected;
    }
}
