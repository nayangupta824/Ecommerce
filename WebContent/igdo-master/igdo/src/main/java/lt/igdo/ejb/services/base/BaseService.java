package lt.igdo.ejb.services.base;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.TypeVariable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import lt.igdo.domain.base.IEntity;

import org.springframework.transaction.annotation.Transactional;

/**
 * Base service that other persistence services can extend. It provides all
 * common crud operations. Also provide default search capabilities which work
 * nicely with composite jsf search components.
 * 
 * @author Ignas
 * 
 * @param <T>
 *            Type of entity.
 * 
 */
@Transactional(readOnly = true)
public abstract class BaseService<T extends IEntity> implements IService<T>, Serializable {

    /**
     * Class version id for serialization. After a change to serialized field
     * this number should be changed so it would be clear its different class
     * version.
     */
    private static final long serialVersionUID = 1L;

    // CHECKSTYLE:OFF
    /** Entity class of service. */
    protected final Class<? extends IEntity> entityClass;

    /** JPA entity manager. */
    @PersistenceContext
    protected EntityManager em;

    // CHECKSTYLE:ON

    /**
     * Default constructor. Loads entity class from super service information.
     * It is used
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public BaseService() {
        Class clazz = getClass();
        while (!(clazz.getGenericSuperclass() instanceof ParameterizedType)) {
            clazz = clazz.getSuperclass();
        }
        Object o = ((ParameterizedType) clazz.getGenericSuperclass()).getActualTypeArguments()[0];

        if (o instanceof TypeVariable) {
            this.entityClass = (Class<T>) ((TypeVariable) o).getBounds()[0];
        } else {
            this.entityClass = (Class<T>) o;
        }
    }

    @Override
    @Transactional(readOnly = false)
    public void add(T entity) {
        em.persist(entity);
    }

    @Override
    @Transactional(readOnly = false)
    public void update(T entity) {
    	em.merge(entity);
    }
    
    @Override
    @Transactional(readOnly = false)
    public void delete(T entity) {
        em.remove(entity);
    }
    
    @Override
    @Transactional(readOnly = false)
    public void delete(Long id) {
        Query query = em.createQuery("delete from " + entityClass.getName() + " where id = :id)");
        query.setParameter("id", id);
        query.executeUpdate();
    }
        

    @Override
    @Transactional(readOnly = false)
    public void deleteMany(Iterable<Long> ids) {
        Query query = em.createQuery("delete from " + entityClass.getName() + " where id in (:ids)");
        query.setParameter("ids", ids);
        query.executeUpdate();
    }


    @Override
    public T findById(Long id) {
        @SuppressWarnings("unchecked")
        List<T> list = em.createQuery("from " + entityClass.getName() + " where id=:id").setParameter("id", id).getResultList();
        return list.size() > 0 ? (T) list.get(0) : null;
    }

    @Override
    public T findById(Long id, List<String> fetchFields) {
        StringBuilder queryString = new StringBuilder("from " + entityClass.getName() + " a");
        if (fetchFields != null && !fetchFields.isEmpty()) {
            for (String fetchField : fetchFields) {
                if (fetchField.contains(".")) {
            		String[] fields = fetchField.split("\\.");
            		queryString.append(" left join fetch a." + fields[0] + " as " + fields[0]);
            		queryString.append(" left join fetch " + fields[0] + "." + fields[1] + " as " + fields[1]);
            	} else {
            		queryString.append(" left join fetch a." + fetchField + " as " + fetchField);
            	}
            }
        }
        queryString.append(" where a.id = :id");
        Query query = em.createQuery(queryString.toString());
        query.setParameter("id", id);

        @SuppressWarnings("unchecked")
        List<T> list = query.getResultList();

        return list.size() > 0 ? (T) list.get(0) : null;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<T> list() {
    	return em.createQuery("from " + entityClass.getName()).getResultList();
    }

    @Override
    public long count() {
    	return (long)em.createQuery("count (*) from " + entityClass.getName()).getSingleResult();
    }

}
