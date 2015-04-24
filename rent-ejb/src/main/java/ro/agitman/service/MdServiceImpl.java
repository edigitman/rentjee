package ro.agitman.service;

import ro.agitman.dba.DataAccessService;
import ro.agitman.facade.MdService;
import ro.agitman.model.MdBuildingType;
import ro.agitman.model.MdCity;
import ro.agitman.model.MdHeatSource;
import ro.agitman.model.MdNeighborhood;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by edi on 2/10/2015.
 */
@Stateless
public class MdServiceImpl implements MdService {

    @EJB
    private DataAccessService service;

    @Override
    public List<MdNeighborhood> findAllNeighborhood(MdCity city) {
        Map<String, Object> map = new HashMap<>();
        map.put("city", city);
        return service.findWithNamedQuery(MdNeighborhood.FIND_BY_CITY, map);
    }

    @Override
    public List<MdCity> findAllCities() {
        return service.findWithNamedQuery(MdCity.FIND_ALL);
    }

    @Override
    public List<MdBuildingType> findAllBuildingType() {
        return service.findWithNamedQuery(MdBuildingType.FIND_ALL);
    }

    @Override
    public List<MdHeatSource> findAllHeatSource() {
        return service.findWithNamedQuery(MdHeatSource.FIND_ALL);
    }


}
