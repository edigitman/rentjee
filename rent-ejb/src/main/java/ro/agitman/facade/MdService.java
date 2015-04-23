package ro.agitman.facade;

import ro.agitman.model.*;

import javax.ejb.Local;
import java.util.List;

/**
 * Created by edi on 2/10/2015.
 */
@Local
public interface MdService {

    List<MdCity> findAllCities();

    List<MdBuildingType> findAllBuildingType();

    List<MdHeatSource> findAllHeatSource();
}