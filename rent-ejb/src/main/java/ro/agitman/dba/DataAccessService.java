package ro.agitman.dba;

import ro.agitman.model.AbstractModel;

import javax.ejb.Local;
import javax.persistence.EntityManager;
import java.util.List;
import java.util.Map;

/**
 * Created by edi on 2/10/2015.
 */
@Local
public interface DataAccessService {

    EntityManager getEntityManager();

    void setEntityManager(EntityManager em);

    /**
     * This method will create the given object into the persistence system.
     *
     * @param t   The object that must be created
     * @param <T> Any object extending AgcEntity
     * @return The created object
     */
    <T extends AbstractModel> T create(T t);

    /**
     * This method will fetch the object corresponding to the given id from the persistence system.
     *
     * @param type The class of the object that must be fetched
     * @param id   The id (key) of the object that must be fetched
     * @param <T>  Any object extending AgcEntity
     * @return The fetched object
     */
    <T extends AbstractModel> T find(Class<T> type, Object id);

    /**
     * This method will delete the given object from the persistence system.
     *
     * @param type The class of the object that must be deleted
     * @param id   The id (key) of the object that must be fetched
     * @param <T>  Any object extending AgcEntity
     */
    <T extends AbstractModel> void delete(Class<T> type, Object id);

    /**
     * This method will delete the given object from the persistence system.
     *
     * @param t   The object that must be deleted
     * @param <T> Any object extending AgcEntity
     */
    <T extends AbstractModel> void delete(T t);

    /**
     * This method will execute an update query in order to delete
     *
     * @param namedQueryName named query
     * @param parameters     - parameters map
     */
    void deleteWithQuery(String namedQueryName, Map<String, Object> parameters);

    /**
     * This method will update the given object into the persistence system.
     *
     * @param t   The object that must be updated
     * @param <T> Any object extending AgcEntity
     * @return The updated object
     */
    <T extends AbstractModel> T update(T t);

    /**
     * This method will return the results of the findAll query for the given class.
     *
     * @param type The class of the object that must be used to create the query
     * @param <T>  Any object extending AgcEntity
     * @return The results of the findAll query
     */
    <T extends AbstractModel> List<T> findAll(Class<T> type);

    <T extends AbstractModel> List<T> findWithNamedQuery(String namedQueryName);

    <T extends AbstractModel> T getWithNamedQuery(String namedQueryName);

    <T extends AbstractModel> List<T> findWithNamedQuery(String namedQueryName, Map<String, Object> parameters);

    <T extends AbstractModel> T getWithNamedQuery(String namedQueryName, Map<String, Object> parameters);

    <T extends AbstractModel> List<T> findWithNamedQuery(String queryName, int resultLimit);

    <T extends AbstractModel> List<T> findByNativeQuery(String sql, Class<T> type);

    <T extends AbstractModel> List<T> findWithNamedQuery(String namedQueryName, Map<String, Object> parameters, int resultLimit);

    Long countWithNamedQuery(String namedQueryName);

    Long countWithNamedQuery(String namedQueryName, Map<String, Object> parameters);

    <T extends AbstractModel> void refresh(T entity);

    <T extends AbstractModel> T reload(T entity);

    /**
     * This method evicts all data from the cache. The next time a query is made,
     * the results are garantueed to be 'fresh' from the database. After calling
     * this method queries might be slow for a short time as the cache is rebuild.
     */
    public void evictCache();

    /**
     * This method evicts all instances of the entity class from the cache. The
     * next time a query returns one or more instances of this class, they are
     * garantueed to be 'fresh' from the database. After calling this method
     * queries might be slow for a short time as the cache is rebuild.
     */
    public <T extends AbstractModel> void evictCacheFor(Class<T> entityClass);

    /**
     * This method evicts the entity from the cache. The next time a query returns
     * this instance, it is garantueed to be 'fresh' from the database.
     */
    public <T extends AbstractModel> void evictCacheFor(T entity);

}
