package ro.agitman.md;

import ro.agitman.facade.MdService;
import ro.agitman.model.MdAdType;
import ro.agitman.model.MdBuildingType;
import ro.agitman.model.MdCity;
import ro.agitman.model.MdCurrency;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.SelectItem;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by edi on 2/10/2015.
 */
@ManagedBean
@SessionScoped
public class MdSessionMB implements Serializable {

    @EJB
    private MdService mdService;

    private List<SelectItem> cities;
    private List<SelectItem> types;
    private List<SelectItem> accomodations;
    private List<SelectItem> currencies;

    public List<SelectItem> getCities() {
        if (cities == null) {
            cities = new ArrayList<>();
            for(MdCity city: mdService.findAllCities()){
                cities.add(new SelectItem(city, city.getName()));
            }
        }
        return cities;
    }

    public List<SelectItem> getTypes() {
        if (types == null) {
            types = new ArrayList<>();
            for(MdAdType adType: mdService.findAllAdType()){
                types.add(new SelectItem(adType, adType.getName()));
            }
        }
        return types;
    }

    public List<SelectItem> getAccomodations() {
        if (accomodations == null) {
            accomodations = new ArrayList<>();
            for(MdBuildingType buildingType: mdService.findAllBuildingType()){
                accomodations.add(new SelectItem(buildingType, buildingType.getName()));
            }
        }
        return accomodations;
    }

    public List<SelectItem> getCurrencies() {
        if (currencies == null) {
            currencies = new ArrayList<>();
            for(MdCurrency currency: mdService.findAllCurrency()){
                currencies.add(new SelectItem(currency, currency.getName()));
            }
        }
        return currencies;
    }
}
