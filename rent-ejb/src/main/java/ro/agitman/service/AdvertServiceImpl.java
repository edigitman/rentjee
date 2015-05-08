package ro.agitman.service;

import ro.agitman.dba.DataAccessService;
import ro.agitman.dto.AdvertStatusEnum;
import ro.agitman.dto.UploadedImage;
import ro.agitman.facade.AdvertService;
import ro.agitman.facade.GMapService;
import ro.agitman.facade.ImageService;
import ro.agitman.facade.MailService;
import ro.agitman.model.*;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.math.BigDecimal;
import java.util.*;

/**
 * Created by edi on 2/11/2015.
 */
@Stateless
public class AdvertServiceImpl implements AdvertService {

    @EJB
    private DataAccessService service;
    @EJB
    private ImageService imageService;
    @EJB
    private MailService mailService;
    @EJB
    private GMapService mapService;

    @Override
    public Advert init() {
        Advert advert = new Advert();
        advert.setAddress(new Address());
        advert.setInfo(new RentInfo());
        advert.setInterval(new RentInterval());
        return advert;
    }

    @Override
    public void save(Advert advert, List<UploadedImage> images) {

        mapService.findForAddress(advert.getAddress());
        if (advert.getId() == null) {
            advert.setCreateDate(new Date());
            advert.setStatusUpdate(new Date());
            advert.setStatus(AdvertStatusEnum.ACTIVE);
            advert.setWithPictures(images != null && !images.isEmpty());
            advert = service.create(advert);
            imageService.uploadImages(advert, images);
            mailService.sendAdvertAdded(advert);
            service.reload(advert);
        } else {
            service.update(advert);
        }
    }

    public List<Advert> findAll() {
        List<AdvertStatusEnum> list = new ArrayList<>();
        list.add(AdvertStatusEnum.ACTIVE);
        list.add(AdvertStatusEnum.EXPIRED);
        Map<String, Object> map = new HashMap<>();
        map.put("stats", list);
        return service.findWithNamedQuery(Advert.FIND_ALL, map);
    }

    public List<Advert> load(int first, int pageSize, Object sortField, Object sortOrder, Map<String, Object> filters){
        EntityManager em = service.getEntityManager();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Advert> cq = cb.createQuery(Advert.class);
        Root<Advert> advert = cq.from(Advert.class);

        if (filters != null && !filters.isEmpty()) {
            List<Predicate> predicates = buildPredicateFromFilters(advert, cb, filters);
            cq.where(cb.and(predicates.toArray(new Predicate[predicates.size()])));
        }

        cq.select(advert);
        TypedQuery<Advert> q = em.createQuery(cq);
        //pagination
        if (pageSize >= 0){
            q.setMaxResults(pageSize);
        }
        if (first >= 0){
            q.setFirstResult(first);
        }

        return q.getResultList();
    }

    public int count(Map<String, Object> filters){
        EntityManager em = service.getEntityManager();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Advert> advert = cq.from(Advert.class);

        if (filters != null && !filters.isEmpty()) {
            List<Predicate> predicates = buildPredicateFromFilters(advert, cb, filters);
            cq.where(cb.and(predicates.toArray(new Predicate[predicates.size()])));
        }

        cq.select(cb.count(advert));
        TypedQuery<Long> q = em.createQuery(cq);
        return q.getSingleResult().intValue();
    }

    @Override
    public Advert findForId(Long id) {
        return service.find(Advert.class, id);
    }

    public List<Advert> findForUser(User user) {
        Map<String, Object> map = new HashMap<>();
        map.put("user", user);
        return service.findWithNamedQuery(Advert.FIND_FOR_USER, map);
    }

    public List<Advert> findFavoritesForUser(User user) {
        Map<String, Object> map = new HashMap<>();
        map.put("user", user);
        return service.findWithNamedQuery(Advert.FIND_FAV_BY_USER, map);
    }

    public List<Advert> findDezByUser(User user) {
        return new ArrayList<>();
    }

    public List<Advert> findSearch(Long cityId, Integer minPrice, Integer maxPrice, Boolean onlyImages) {

        EntityManager em = service.getEntityManager();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Advert> cq = cb.createQuery(Advert.class);
        Root<Advert> advert = cq.from(Advert.class);
        List<Predicate> predicates = new ArrayList<Predicate>();

        // filter price
        predicates.add(cb.between(advert.<BigDecimal>get("value"), new BigDecimal(minPrice), new BigDecimal(maxPrice)));

        //filter images
        if (onlyImages)
            predicates.add(cb.isTrue(advert.<Boolean>get("withPictures")));

        //filter status
        List<AdvertStatusEnum> list = new ArrayList<>();
        list.add(AdvertStatusEnum.ACTIVE);
        list.add(AdvertStatusEnum.EXPIRED);
        predicates.add(advert.<AdvertStatusEnum>get("status").in(list));

        //filter city if required
        if (cityId != 0) {
            Join<Advert, MdCity> city = advert.join("address").join("city");
            predicates.add(cb.equal(city.<Long>get("id"), cityId));
        }

        cq.where(cb.and(predicates.toArray(new Predicate[predicates.size()])));
        cq.select(advert);

        TypedQuery<Advert> q = em.createQuery(cq);
        return q.getResultList();
    }

    public void markFav(User user, Advert advert, boolean makeActive) {
        if (makeActive) {
            RentFavorite favorite = new RentFavorite();
            favorite.setAdvert(advert);
            favorite.setUser(user);
            favorite.setDateCreated(new Date());
            service.create(favorite);
        } else {
            Map<String, Object> map = new HashMap<>();
            map.put("advert", advert);
            map.put("user", user);
            service.executeUpdateWithQuery(RentFavorite.DELETE_ONE, map);
        }
    }

    public int isFav(User user, Advert selected) {
        Map<String, Object> map = new HashMap<>();
        map.put("user", user);
        map.put("advert", selected);

        List<RentFavorite> list = service.findWithNamedQuery(RentFavorite.FIND_ONE, map);
        return list.size();
    }

    //============== UTILITY METHODS

    private List<Predicate> buildPredicateFromFilters(Root<Advert> advert, CriteriaBuilder cb, Map<String, Object> filters){
        List<Predicate> predicates = new ArrayList<Predicate>();

        // filter price
        BigDecimal minPrice = (BigDecimal) filters.get("minPrice");
        BigDecimal maxPrice = (BigDecimal) filters.get("maxPrice");
        predicates.add(cb.between(advert.<BigDecimal>get("value"), minPrice, maxPrice));

        //filter images
        Boolean onlyImages = (Boolean) filters.get("onlyImages");
        if (onlyImages)
            predicates.add(cb.isTrue(advert.<Boolean>get("withPictures")));

        //filter status
        List<AdvertStatusEnum> list = new ArrayList<>();
        list.add(AdvertStatusEnum.ACTIVE);
        list.add(AdvertStatusEnum.EXPIRED);
        predicates.add(advert.<AdvertStatusEnum>get("status").in(list));

        //filter city if required
        Long cityId = (Long) filters.get("cityId");
        if (cityId != 0) {
            Join<Advert, MdCity> city = advert.join("address").join("city");
            predicates.add(cb.equal(city.<Long>get("id"), cityId));
        }

        return predicates;
    }
}
