/**
 * EntityLogger.java
 * Created: Jan 22, 2008 9:32:45 PM
 */
package lt.igdo.domain.listeners;

import javax.persistence.PostLoad;
import javax.persistence.PostPersist;
import javax.persistence.PostRemove;
import javax.persistence.PostUpdate;
import javax.persistence.PrePersist;
import javax.persistence.PreRemove;
import javax.persistence.PreUpdate;

import org.apache.log4j.Logger;

/**
 * Entity listener for logging purposes.
 * 
 * @author Donatas
 * 
 */
public class EntityLogger {

    /** Logger. */
    private static Logger logger = Logger.getLogger(EntityLogger.class);

    /**
     * Pure constructor.
     */
    public EntityLogger() {

    }

    @PrePersist
    public void prePersist(Object entity) {
        EntityLogger.logger.debug("@PrePersist: " + entity);
    }

    @PostPersist
    public void postPersist(Object entity) {
        EntityLogger.logger.debug("@PostPersist: " + entity);
    }

    @PostLoad
    public void postLoad(Object entity) {
        EntityLogger.logger.debug("@PostLoad: " + entity);
    }

    @PreUpdate
    public void preUpdate(Object entity) {
        EntityLogger.logger.debug("@PreUpdate: " + entity);
    }

    @PostUpdate
    public void postUpdate(Object entity) {
        EntityLogger.logger.debug("@PostUpdate: " + entity);
    }

    @PreRemove
    public void preRemove(Object entity) {
        EntityLogger.logger.debug("@PreRemove: " + entity);
    }

    @PostRemove
    public void postRemove(Object entity) {
        EntityLogger.logger.debug("@PostRemove: " + entity);
    }
}
