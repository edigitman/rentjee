package ro.agitman.service;

import ro.agitman.dba.DataAccessService;
import ro.agitman.facade.MdService;
import ro.agitman.model.*;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.List;

/**
 * Created by edi on 2/10/2015.
 */
@Stateless
public class MdServiceImpl implements MdService {

    @EJB
    private DataAccessService service;

    @Override
    public List<MdCity> findAllCities(){
        return service.findWithNamedQuery(MdCity.FIND_ALL);
    }

    @Override
    public List<MdBuildingType> findAllBuildingType(){
        return service.findWithNamedQuery(MdBuildingType.FIND_ALL);
    }

    @Override
    public List<MdHeatSource> findAllHeatSource(){
        return service.findWithNamedQuery(MdHeatSource.FIND_ALL);
    }


}
