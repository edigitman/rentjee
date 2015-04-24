package ro.agitman.pub;

import com.ocpsoft.pretty.faces.annotation.URLAction;
import com.ocpsoft.pretty.faces.annotation.URLMapping;
import com.ocpsoft.pretty.faces.annotation.URLMappings;
import ro.agitman.AbstractMB;
import ro.agitman.account.UserMB;
import ro.agitman.dto.DotariEnum;
import ro.agitman.dto.DotariEnumCmp;
import ro.agitman.facade.AdvertService;
import ro.agitman.model.Advert;
import ro.agitman.model.User;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.LinkedList;
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

    private Advert selected;
    private Long id;
    private List<DotariEnum> dotari;
    private int favNr = 0;
    private User user;

    @URLAction(onPostback = false)
    public void load() {
        selected = advertService.findForId(id);
        user = userMB.getUser();
        favNr = advertService.isFav(user, selected);
        if (selected != null) {
            buildDotari();
        }
    }

    public void makeFavorite() {
        if (favNr == 0) {
            advertService.markFav(user, selected, true);
        } else {
            advertService.markFav(user, selected, false);
        }
    }

    private void buildDotari() {
        dotari = new LinkedList<>();
        long dot = selected.getDotari();
        System.out.println(Long.toBinaryString(dot));
        if (dot != 0) {
            for (DotariEnum d : DotariEnum.values()) {
                if ((dot & d.getVal()) != 0) {
                    dotari.add(d);
                }
            }
        }
        Collections.sort(dotari, new DotariEnumCmp());
    }

    public List<DotariEnum> dotari() {
        return dotari;
    }

    public boolean hasDeposit() {
        return selected != null && !new BigDecimal("0.00").equals(selected.getValue().getDeposit());
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

}
