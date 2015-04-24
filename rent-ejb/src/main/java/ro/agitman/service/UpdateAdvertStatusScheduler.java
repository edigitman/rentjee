package ro.agitman.service;

import org.joda.time.MutableDateTime;
import ro.agitman.dba.DataAccessService;
import ro.agitman.model.Advert;

import javax.ejb.EJB;
import javax.ejb.Schedule;
import javax.ejb.Singleton;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by AlexandruG on 4/24/2015.
 */
@Singleton
public class UpdateAdvertStatusScheduler {

    @EJB
    private DataAccessService service;

    @Schedule(second = "*/1", minute = "*", hour = "*", persistent = false)
    public void executeUpdate() {
        MutableDateTime date = new MutableDateTime();
        date.addDays(-20);
        Date dateExp = date.toDate();

        Map<String, Object> map1 = new HashMap<>();
        map1.put("date", dateExp);

        service.executeUpdateWithQuery(Advert.UPDATE_STATUS_TO_EXPIRED, map1);

        MutableDateTime dateRet = new MutableDateTime();
        date.addDays(-10);
        Map<String, Object> map2 = new HashMap<>();
        map2.put("dateExp", dateExp);
        map2.put("dateRet", dateRet.toDate());

        service.executeUpdateWithQuery(Advert.UPDATE_STATUS_TO_REMOVED, map2);
    }

}
