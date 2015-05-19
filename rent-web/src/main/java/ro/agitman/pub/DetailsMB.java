package ro.agitman.pub;

import com.ocpsoft.pretty.faces.annotation.URLAction;
import com.ocpsoft.pretty.faces.annotation.URLMapping;
import com.ocpsoft.pretty.faces.annotation.URLMappings;
import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.MapModel;
import ro.agitman.AbstractMB;
import ro.agitman.account.UserMB;
import ro.agitman.dto.AdvertStatusEnum;
import ro.agitman.dto.DotariEnum;
import ro.agitman.facade.AdvertService;
import ro.agitman.model.Advert;
import ro.agitman.model.User;
import ro.agitman.util.RentUtils;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

/**
 * Created by edi on 4/20/2015.
 */
@ManagedBean
@ViewScoped
@URLMappings(mappings = {
        @URLMapping(id = "details", pattern = "/details/#{detailsMB.id}", viewId = "/pages/details.jsf?faces-redirect=true")
})
public class DetailsMB extends AbstractMB {

    @EJB
    private AdvertService advertService;

    @ManagedProperty(value = "#{userMB}")
    private UserMB userMB;
    private boolean confirmHumanReq = false;
    private boolean active;

    private MapModel geoModel = new DefaultMapModel();
    private String centerGeoMap = "41.850033, -87.6500523";

    private Advert selected;
    private Long id;
    private EnumSet<DotariEnum> dotari = EnumSet.noneOf(DotariEnum.class);
    private int favNr = 0;
    private int oldFav = 0;
    private User user;

    @URLAction(onPostback = false)
    public void load() {
        selected = advertService.findForId(id);
        user = userMB.getUser();
        oldFav = advertService.isFav(user, selected);
        favNr = oldFav;
        if (selected != null) {
            dotari = RentUtils.loadDotari(selected);
            active = AdvertStatusEnum.ACTIVE.equals(selected.getStatus());
        }
    }

    public void makeFavorite() {
        if (oldFav == 0) {
            advertService.markFav(user, selected, true);
            favNr = oldFav = 1;
        } else {
            advertService.markFav(user, selected, false);
            favNr = oldFav = 0;
        }
    }

    public List<SelectItem> getDotari(Integer cat) {
        List<SelectItem> result = new ArrayList<>();
        for (DotariEnum dotare : dotari) {
            if(dotare.getCat() == cat)
                result.add(new SelectItem(dotare, dotare.gethName()));
        }
        return result;
    }

    public void updateActive() {
        if(active){
            selected.setStatus(AdvertStatusEnum.ACTIVE);
        }else {
            selected.setStatus(AdvertStatusEnum.RETIRED);
        }
        advertService.save(selected, null);
    }

    public boolean isMyAdvert() {
        return selected != null && selected.getUser().getId().equals(user.getId());
    }

    public void confirmHuman(ActionEvent actionEvent){
        confirmHumanReq = true;
    }

    public boolean hasDeposit() {
        return selected != null && !new BigDecimal("0.00").equals(selected.getDeposit());
    }

    public boolean isMine() {
        return user != null && selected.getUser().getId().equals(user.getId());
    }

    public Advert getSelected() {
        return selected;
    }

    public void setSelected(Advert selected) {
        this.selected = selected;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserMB getUserMB() {
        return userMB;
    }

    public void setUserMB(UserMB userMB) {
        this.userMB = userMB;
    }

    public int getFavNr() {
        return favNr;
    }

    public void setFavNr(int favNr) {
        this.favNr = favNr;
    }

    public boolean isConfirmHumanReq() {
        return confirmHumanReq;
    }

    public void setConfirmHumanReq(boolean confirmHumanReq) {
        this.confirmHumanReq = confirmHumanReq;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
