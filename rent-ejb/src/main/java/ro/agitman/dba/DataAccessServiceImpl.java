package ro.agitman.dba;

import ro.agitman.model.AbstractModel;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.OptimisticLockException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by edi on 2/10/2015.
 */
@Stateless
public class DataAccessServiceImpl implements DataAccessService {

    protected EntityManager em;

    public EntityManager getEntityManager() {
        return em;
    }

    // Method used to inject the Entity Manager
    @PersistenceContext(unitName = "CrudPU")
    public void setEntityManager(EntityManager em) {
        this.em = em;
    }

    /**
     * {@inheritDoc}
     */
    public <T extends AbstractModel> T create(T t) {
        this.em.persist(t);
        this.em.flush();
        this.em.refresh(t);
        return t;
    }

    /**
     * {@inheritDoc}
     */
    public <T extends AbstractModel> T find(Class<T> type, Object id) {
        return this.em.find(type, id);
    }

    /**
     * {@inheritDoc}
     */
    public <T extends AbstractModel> void delete(Class<T> type, Object id) {
        Object ref = this.em.getReference(type, id);
        this.em.remove(ref);
        this.em.flush();
    }

    /**
     * {@inheritDoc}
     */
    public <T extends AbstractModel> void delete(T t) {
        this.em.remove(t);
        this.em.flush();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void executeUpdateWithQuery(String namedQueryName, Map<String, Object> parameters) {
        Set<Map.Entry<String, Object>> rawParameters = parameters.entrySet();
        Query query = this.em.createNamedQuery(namedQueryName);

        for (Map.Entry<String, Object> entry : rawParameters) {
            query.setParameter(entry.getKey(), entry.getValue());
        }
        query.executeUpdate();
    }

    /**
     * {@inheritDoc}
     */
    public <T extends AbstractModel> T update(T t) {
        return this.em.merge(t);
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings({"unchecked"})
    public <T extends AbstractModel> List<T> findAll(Class<T> type) {
        return this.em.createNamedQuery(type.getSimpleName() + ".findAll").getResultList();
    }


    /**
     * {@inheritDoc}
     */
    @SuppressWarnings({"unchecked"})
    public <T extends AbstractModel> List<T> findWithNamedQuery(String namedQueryName) {
        return this.em.createNamedQuery(namedQueryName).getResultList();
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings({"unchecked"})
    public <T extends AbstractModel> T getWithNamedQuery(String namedQueryName) {
        List<T> result = findWithNamedQuery(namedQueryName);
        if (!result.isEmpty()) {
            return result.get(0);
        }
        return null;
    }


    /**
     * {@inheritDoc}
     */
    public <T extends AbstractModel> List<T> findWithNamedQuery(String namedQueryName, Map<String, Object> parameters) {
        return findWithNamedQuery(namedQueryName, parameters, 0);
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings({"unchecked"})
    public <T extends AbstractModel> T getWithNamedQuery(String namedQueryName, Map<String, Object> parameters) {
        List<T> result = findWithNamedQuery(namedQueryName, parameters, 0);
        if (!result.isEmpty()) {
            return result.get(0);
        }
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings({"unchecked"})
    public <T extends AbstractModel> List<T> findWithNamedQuery(String queryName, int resultLimit) {
        return this.em.createNamedQuery(queryName).
                setMaxResults(resultLimit).
                getResultList();
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings({"unchecked"})
    public <T extends AbstractModel> List<T> findByNativeQuery(String sql, Class<T> type) {
        return this.em.createNativeQuery(sql, type).getResultList();
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings({"unchecked"})
    public <T extends AbstractModel> List<T> findWithNamedQuery(String namedQueryName, Map<String, Object> parameters, int resultLimit) {
        Set<Map.Entry<String, Object>> rawParameters = parameters.entrySet();
        Query query = this.em.createNamedQuery(namedQueryName);
        if (resultLimit > 0)
            query.setMaxResults(resultLimit);
        for (Map.Entry<String, Object> entry : rawParameters) {
            query.setParameter(entry.getKey(), entry.getValue());
        }
        return query.getResultList();
    }

    /**
     * {@inheritDoc}
     */
    public Long countWithNamedQuery(String namedQueryName) {
        return countWithNamedQuery(namedQueryName, null);
    }

    /**
     * {@inheritDoc}
     */
    public Long countWithNamedQuery(String namedQueryName, Map<String, Object> parameters) {
        Query query = this.em.createNamedQuery(namedQueryName);
        if (parameters != null) {
            Set<Map.Entry<String, Object>> rawParameters = parameters.entrySet();
            for (Map.Entry<String, Object> entry : rawParameters) {
                query.setParameter(entry.getKey(), entry.getValue());
            }
        }
        return (Long) query.getSingleResult();
    }

    /**
     * {@inheritDoc}
     */
    public <T extends AbstractModel> void refresh(T entity) {
        this.em.refresh(entity);
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings({"unchecked"})
    public <T extends AbstractModel> T reload(T entity) {
        if (entity == null) {
            return null;
        }
        T reloaded = (T) em.find(entity.getClass(), entity.getId());
        if (reloaded == null) {
            throw new OptimisticLockException("Object has been deleted");
        }
        this.refresh(reloaded);
        return reloaded;
    }

    /**
     * {@inheritDoc}
     */
    public void evictCache() {
        em.getEntityManagerFactory().getCache().evictAll();
    }

    /**
     * {@inheritDoc}
     */
    public <T extends AbstractModel> void evictCacheFor(Class<T> entityClass) {
        this.em.getEntityManagerFactory().getCache().evict(entityClass);
    }

    /**
     * {@inheritDoc}
     */
    public <T extends AbstractModel> void evictCacheFor(T entity) {
        if (entity != null) {
            this.em.getEntityManagerFactory().getCache().evict(entity.getClass(), entity.getId());
        }
    }

}
