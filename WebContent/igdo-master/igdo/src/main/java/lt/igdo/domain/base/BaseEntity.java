package lt.igdo.domain.base;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

/**
 * Base entity class that can be extended for concrete domain models. It already
 * contains implementation of {@link IEntity} interface and Version field for
 * JPAs optimistic locking.
 * 
 * @author Ignas
 * 
 */
@MappedSuperclass
public class BaseEntity implements IEntity, Serializable {

    /** */
    private static final long serialVersionUID = 1L;

    /**
     * Entity ID in database.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Long id;

    /**
     * @see lt.igdo.domain.base.IEntity#getId()
     */
    @Override
    public Long getId() {
        return id;
    }

    /**
     * ID setter.
     */
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public boolean isTransient() {
        return id == null;
    }

    /**
     * Better to override this method in concrete domain class, because for
     * transient entities ID is null.
     * 
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    /**
     * Better to override this method in concrete domain class, because for
     * transient entities ID is null.
     * 
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        BaseEntity other = (BaseEntity) obj;
        if (id == null) {
            if (other.id != null) {
                return false;
            }
        } else if (!id.equals(other.id)) {
            return false;
        }
        return true;
    }

}