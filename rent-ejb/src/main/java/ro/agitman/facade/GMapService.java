package ro.agitman.facade;

import ro.agitman.model.Address;

import javax.ejb.Local;

/**
 * Created by AlexandruG on 5/4/2015.
 */
@Local
public interface GMapService {

    void findForAddress(Address address);

}
