package ro.agitman.pages.pub;

import com.ocpsoft.pretty.PrettyContext;
import com.ocpsoft.pretty.faces.annotation.URLAction;
import com.ocpsoft.pretty.faces.annotation.URLMapping;
import com.ocpsoft.pretty.faces.annotation.URLMappings;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;
import ro.agitman.AbstractMB;
import ro.agitman.facade.AdvertService;
import ro.agitman.facade.MailService;
import ro.agitman.md.MdSessionMB;
import ro.agitman.model.Advert;
import ro.agitman.model.MdCity;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import java.math.BigDecimal;
import java.util.*;

/**
 * Created by edi on 2/10/2015.
 */
@ManagedBean
@SessionScoped
@URLMappings(mappings = {
        @URLMapping(id = "index", pattern = "/index", viewId = "/pages/index.jsf?faces-redirect=true"),
        @URLMapping(id = "contact", pattern = "/contact", viewId = "/pages/contact.jsf?faces-redirect=true"),
        @URLMapping(id = "help", pattern = "/help", viewId = "/pages/help.jsf?faces-redirect=true"),
        @URLMapping(id = "about", pattern = "/about", viewId = "/pages/about.jsf?faces-redirect=true"),
        @URLMapping(id = "search", pattern = "/index/#{indexMB.cityName}/#{indexMB.minPrice}/#{indexMB.maxPrice}/#{indexMB.onlyImages}", viewId = "/pages/index.jsf?faces-redirect=true")
})
public class IndexMB extends AbstractMB {

    @EJB
    private MailService mailService;

    //contact parameters
    private String contactEmail;
    private String contactSubject;
    private String contactMessage;

    //search parameters
    private LazyDataModel<Advert> lazyAdverts;
    private String cityName = "-";
    private Integer minPrice = 100;
    private Integer maxPrice = 400;
    private Boolean onlyImages;
    private String title = "Anunturi rencente";

    // language
    private Locale locale;
    private static Map<String, Locale> countries;

    static {
        countries = new LinkedHashMap<String, Locale>();
        countries.put("Engleza", Locale.ENGLISH); //label, value
        countries.put("Romana", new Locale("ro", "RO"));
    }


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
        FacesContext.getCurrentInstance().getViewRoot().setLocale(new Locale("ro", "RO"));
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

    private long findByName(String name) {
        if (name == null || name.isEmpty() || "-".equals(name)) {
            return 0L;
        }
        for (MdCity city : mdSession.getCitiesList()) {
            if (city.getName().equals(name))
                return city.getId();
        }
        return 0L;
    }

    public void sendContactEmail() {
        if (validateCapthca()) {
            mailService.sendContactEmail(contactEmail, contactSubject, contactMessage);
            contactMessage = null;
            contactSubject = null;
            contactEmail = null;
            info("Am primit mailul tau. Multumim.");
        }
    }

    //value change event listener
    public void countryLocaleCodeChanged(ValueChangeEvent e) {

        String newLocaleValue = e.getNewValue().toString();

        //loop country map to compare the locale code
        for (Map.Entry<String, Locale> entry : countries.entrySet()) {
            if (entry.getValue().toString().equals(newLocaleValue)) {
                locale = entry.getValue();
                FacesContext.getCurrentInstance().getViewRoot().setLocale(locale);
            }
        }
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

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    public String getContactSubject() {
        return contactSubject;
    }

    public void setContactSubject(String contactSubject) {
        this.contactSubject = contactSubject;
    }

    public String getContactMessage() {
        return contactMessage;
    }

    public void setContactMessage(String contactMessage) {
        this.contactMessage = contactMessage;
    }

    public Map<String, Locale> getCountriesInMap() {
        return countries;
    }

    public Locale getLocale() {
        return locale;
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }
}
