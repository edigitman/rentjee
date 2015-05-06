package ro.agitman.pub;

import com.ocpsoft.pretty.PrettyContext;
import com.ocpsoft.pretty.faces.annotation.URLAction;
import com.ocpsoft.pretty.faces.annotation.URLMapping;
import com.ocpsoft.pretty.faces.annotation.URLMappings;
import ro.agitman.facade.AdvertService;
import ro.agitman.md.MdSessionMB;
import ro.agitman.model.Advert;
import ro.agitman.model.MdCity;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;
import java.util.List;

/**
 * Created by edi on 2/10/2015.
 */
@ManagedBean
@SessionScoped
@URLMappings(mappings = {
        @URLMapping(id = "index", pattern = "/index", viewId = "/pages/index.jsf?faces-redirect=true"),
        @URLMapping(id = "contact", pattern = "/contact", viewId = "/pages/contact.jsf?faces-redirect=true"),
        @URLMapping(id = "help", pattern = "/help", viewId = "/pages/help.jsf?faces-redirect=true"),
        @URLMapping(id = "search", pattern = "/index/#{indexMB.cityName}/#{indexMB.minPrice}/#{indexMB.maxPrice}/#{indexMB.onlyImages}", viewId = "/pages/index.jsf?faces-redirect=true")
})
public class IndexMB implements Serializable{

    private List<Advert> adverts;
    private MdCity city = new MdCity(0L);
    private Integer cityId = 0;
    private String cityName = "-";
    private Integer minPrice = 100;
    private Integer maxPrice = 400;
    private Boolean onlyImages;

    @EJB
    private AdvertService advertService;

    @ManagedProperty(value = "#{mdSessionMB}")
    private MdSessionMB mdSession;

    @URLAction(onPostback = false)
    public void init() {
        String viewId = PrettyContext.getCurrentInstance().getCurrentMapping().getId();

        if ("search".equals(viewId)) {
//            long id = cityId == 0 ? (city == null || city.getId() == 0) ? 0 : city.getId() : cityId;
            long id = findByName(cityName);
            adverts = advertService.findSearch(id, minPrice, maxPrice, onlyImages);
        }

        if ("index".equals(viewId)) {
            adverts = advertService.findAll();
        }

    }

    @PostConstruct
    public void load() {
        adverts = advertService.findAll();
    }

    private long findByName(String name){
        if(name == null || name.isEmpty() || "-".equals(name)){
            return 0L;
        }
        for(MdCity city : mdSession.getCitiesList()){
            if(city.getName().equals(name))
                return city.getId();
        }
        return 0L;
    }

    public List<Advert> getAdverts() {
        return adverts;
    }

    public void setAdverts(List<Advert> adverts) {
        this.adverts = adverts;
    }

    public MdCity getCity() {
        return city;
    }

    public void setCity(MdCity city) {
        this.city = city;
    }

    public Integer getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(Integer minPrice) {
        this.minPrice = minPrice;
    }

    public Integer getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(Integer maxPrice) {
        this.maxPrice = maxPrice;
    }

    public Boolean getOnlyImages() {
        return onlyImages;
    }

    public void setOnlyImages(Boolean onlyImages) {
        this.onlyImages = onlyImages;
    }

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public MdSessionMB getMdSession() {
        return mdSession;
    }

    public void setMdSession(MdSessionMB mdSession) {
        this.mdSession = mdSession;
    }
}
