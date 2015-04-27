package ro.agitman.service;

import org.joda.time.MutableDateTime;
import ro.agitman.dba.DataAccessService;
import ro.agitman.model.Advert;

import javax.ejb.EJB;
import javax.ejb.Schedule;
import javax.ejb.Singleton;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by AlexandruG on 4/24/2015.
 */
@Singleton
public class UpdateAdvertStatusScheduler {

    @EJB
    private DataAccessService service;

    @Schedule(second = "*", minute = "*", hour = "5", persistent = false)
    public void executeUpdate() {
        MutableDateTime date = new MutableDateTime();
        System.out.println("Execute batch... at " + date.toString("dd/MM/yy hh:mm:ss"));

        date.addDays(-20);
        Date dateExp = date.toDate();
        System.out.println("dateEXP param: " + date.toString("dd/MM/yy hh:mm:ss"));

        Map<String, Object> map1 = new HashMap<>();
        map1.put("date", dateExp);

        List<Advert> expiredAdverts = service.findWithNamedQuery(Advert.FIND_STATUS_TO_EXPIRED, map1);
        for (Advert advert : expiredAdverts) {
            System.out.println("Expired advert id: " + advert.getId());
        }

        MutableDateTime dateRet = new MutableDateTime();
        date.addDays(-10);
        Map<String, Object> map2 = new HashMap<>();
        map2.put("dateExp", dateExp);
        map2.put("dateRet", dateRet.toDate());
        System.out.println("dateRM param: " + dateRet.toString("dd/MM/yy hh:mm:ss"));

        List<Advert> removedAdverts = service.findWithNamedQuery(Advert.FIND_STATUS_TO_REMOVED, map2);
        for (Advert advert : removedAdverts) {
            System.out.println("Removed advert id: " + advert.getId());
        }
    }
}
