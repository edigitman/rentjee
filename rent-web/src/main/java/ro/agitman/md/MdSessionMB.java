package ro.agitman.md;

import ro.agitman.dto.AdTypeEnum;
import ro.agitman.dto.CurrencyEnum;
import ro.agitman.facade.MdService;
import ro.agitman.model.MdBuildingType;
import ro.agitman.model.MdCity;
import ro.agitman.model.MdNeighborhood;

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
    private List<SelectItem> cityNames;
    private List<SelectItem> neighborhoods;
    private List<MdCity> citiesList;
    private List<SelectItem> types;
    private List<SelectItem> accomodations;
    private List<SelectItem> currencies;

    public List<SelectItem> getCities() {
        if (cities == null) {
            cities = new ArrayList<>();
            for (MdCity city : mdService.findAllCities()) {
                cities.add(new SelectItem(city, city.getName()));
            }
        }
        return cities;
    }

    public List<MdCity> getCitiesList() {
        if (citiesList == null) {
            citiesList = mdService.findAllCities();
        }
        return citiesList;
    }

    public List<SelectItem> getCityNames() {
        if (cityNames == null) {
            cityNames = new ArrayList<>();
            for (MdCity city : getCitiesList()) {
                cityNames.add(new SelectItem(city.getName(), city.getName()));
            }
        }
        return cityNames;
    }

    public List<SelectItem> getNeighborhoods(MdCity city) {

        neighborhoods = new ArrayList<>();
        for (MdNeighborhood neighborhood : mdService.findAllNeighborhood(city)) {
            neighborhoods.add(new SelectItem(neighborhood, neighborhood.getName()));
        }

        return neighborhoods;
    }

    public List<SelectItem> getTypes() {
        if (types == null) {
            types = new ArrayList<>();
            for (AdTypeEnum adType : AdTypeEnum.values()) {
                types.add(new SelectItem(adType, adType.name()));
            }
        }
        return types;
    }

    public List<SelectItem> getAccomodations() {
        if (accomodations == null) {
            accomodations = new ArrayList<>();
            for (MdBuildingType buildingType : mdService.findAllBuildingType()) {
                accomodations.add(new SelectItem(buildingType, buildingType.getName()));
            }
        }
        return accomodations;
    }

    public List<SelectItem> getCurrencies() {
        if (currencies == null) {
            currencies = new ArrayList<>();
            for (CurrencyEnum currency : CurrencyEnum.values()) {
                currencies.add(new SelectItem(currency, currency.name()));
            }
        }
        return currencies;
    }
}
