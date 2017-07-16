package lt.igdo.ejb.services.base;

import lt.igdo.domain.base.BaseEntity;
import lt.igdo.domain.base.IEntity;

/**
 * Service interface for service with no concrete Entity behind.
 * 
 * @author Ignas
 * 
 */
public interface IVariableTypeService {
    
    /**
     * Loads entity by its id.
     * 
     * @param entityClass
     *            Concrete Entity class.
     * @param id
     *            Entity ID
     * @return Loaded Entity.
     */
    BaseEntity findById(Class<? extends IEntity> entityClass, Long id);

}
