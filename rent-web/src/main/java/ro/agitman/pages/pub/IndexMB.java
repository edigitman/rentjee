package ro.agitman.pages.pub;

import com.ocpsoft.pretty.PrettyContext;
import com.ocpsoft.pretty.faces.annotation.URLAction;
import com.ocpsoft.pretty.faces.annotation.URLMapping;
import com.ocpsoft.pretty.faces.annotation.URLMappings;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;
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
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by edi on 2/10/2015.
 */
@ManagedBean
@SessionScoped
@URLMappings(mappings = {
        @URLMapping(id = "index", pattern = "/index", viewId = "/pages/index.jsf?faces-redirect=true"),
        @URLMapping(id = "contact", pattern = "/contact", viewId = "/pages/contact.jsf?faces-redirect=true"),
        @URLMapping(id = "help", pattern = "/help", viewId = "/pages/help.jsf?faces-redirect=true"),
        @URLMapping(id = "abour", pattern = "/about", viewId = "/pages/help.jsf?faces-redirect=true"),
        @URLMapping(id = "search", pattern = "/index/#{indexMB.cityName}/#{indexMB.minPrice}/#{indexMB.maxPrice}/#{indexMB.onlyImages}", viewId = "/pages/index.jsf?faces-redirect=true")
})
public class IndexMB implements Serializable{

    private LazyDataModel<Advert> lazyAdverts;
    private String cityName = "-";
    private Integer minPrice = 100;
    private Integer maxPrice = 400;
    private Boolean onlyImages;
    private String title = "Anunturi rencente";

    @EJB
    private AdvertService advertService;

    @ManagedProperty(value = "#{mdSessionMB}")
    private MdSessionMB mdSession;

    @URLAction(onPostback = false)
    public void init() {
        String viewId = PrettyContext.getCurrentInstance().getCurrentMapping().getId();

        if ("search".equals(viewId)) {
            title = "Rezultatele cautarii";
            lazyAdverts = new LazyDataModel<Advert>() {
                @Override
                public List<Advert> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> mapFilters) {

                    Map<String, Object> filters = new HashMap<>();
                    filters.put("minPrice", new BigDecimal(minPrice));
                    filters.put("maxPrice", new BigDecimal(maxPrice));
                    filters.put("onlyImages", onlyImages);
                    filters.put("cityId", findByName(cityName));


                    List<Advert> result = advertService.load(first, pageSize, null, null, filters);
                    lazyAdverts.setRowCount(advertService.count(filters));

                    return result;
                }
            };
        }

        if ("index".equals(viewId)) {
            title = "Anunturi rencente";
            lazyAdverts = getLazyModel();
        }
    }

    @PostConstruct
    public void load() {
        lazyAdverts = getLazyModel();
    }

    //============== UTILITY METHODS

    private LazyDataModel<Advert> getLazyModel() {
        return new LazyDataModel<Advert>() {
            @Override
            public List<Advert> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {

                List<Advert> result = advertService.load(first, pageSize, null, null, null);
                lazyAdverts.setRowCount(advertService.count(null));

                return result;
            }
        };
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

    //============== GETTERS AND SETTERS

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

    public LazyDataModel<Advert> getLazyAdverts() {
        return lazyAdverts;
    }

    public void setLazyAdverts(LazyDataModel<Advert> lazyAdverts) {
        this.lazyAdverts = lazyAdverts;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
