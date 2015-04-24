package ro.agitman.facade;

import ro.agitman.model.MdBuildingType;
import ro.agitman.model.MdCity;
import ro.agitman.model.MdHeatSource;
import ro.agitman.model.MdNeighborhood;

import javax.ejb.Local;
import java.util.List;

/**
 * Created by edi on 2/10/2015.
 */
@Local
public interface MdService {

    List<MdNeighborhood> findAllNeighborhood(MdCity city);

    List<MdCity> findAllCities();

    List<MdBuildingType> findAllBuildingType();

    List<MdHeatSource> findAllHeatSource();
}
