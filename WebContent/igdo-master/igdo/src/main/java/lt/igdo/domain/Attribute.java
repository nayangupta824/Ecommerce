/**
 * Attribute.java
 * Created: 9:45:43 PM Aug 5, 2008
 */
package lt.igdo.domain;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lt.igdo.domain.base.BaseEntity;

import org.compass.annotations.Searchable;
import org.compass.annotations.SearchableId;
import org.dom4j.tree.AbstractEntity;

/**
 * Item attribute domain object. Represents various attributes for items, like
 * color size etc.
 * 
 * @author Ignas
 * 
 */
@Entity
@Table(name = "ATTRIBUTES")
@Searchable
public class Attribute extends BaseEntity {

    /** Generated serialVersionUID. */
    private static final long serialVersionUID = 2167244060649192257L;

    /** */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ATTRIBUTE_NAME_ID", nullable = false)
    private AttributeName attributeName;

    /** */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ATTRIBUTE_VALUE_ID", nullable = false)
    private AttributeValue attributeValue;

    /**
     * Compass search is used for full text search. By design Compass search
     * needs to store an id in the index. <i>@SearchableId</i> annotation marks
     * the property to use for this purpose. Since our item id is inherited from
     * {@link AbstractEntity} we need transient method to get that id and
     * annotate it for Compass search.
     * 
     * @return Attribute ID
     */
    @Override
    @SearchableId
    public Long getId() {
        return super.getId();
    }

    /**
     * @return attributeName.
     */
    public AttributeName getAttributeName() {
        return attributeName;
    }

    /**
     * @param attributeName
     *            attributeName to set.
     */
    public void setAttributeName(AttributeName attributeName) {
        this.attributeName = attributeName;
    }

    /**
     * @return attributeValue.
     */
    public AttributeValue getAttributeValue() {
        return attributeValue;
    }

    /**
     * @param attributeValue
     *            attributeValue to set.
     */
    public void setAttributeValue(AttributeValue attributeValue) {
        this.attributeValue = attributeValue;
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result
                + ((attributeName == null) ? 0 : attributeName.hashCode());
        result = prime * result
                + ((attributeValue == null) ? 0 : attributeValue.hashCode());
        return result;
    }

    /**
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!super.equals(obj))
            return false;
        if (getClass() != obj.getClass())
            return false;
        Attribute other = (Attribute) obj;
        if (attributeName == null) {
            if (other.attributeName != null)
                return false;
        } else if (!attributeName.equals(other.attributeName))
            return false;
        if (attributeValue == null) {
            if (other.attributeValue != null)
                return false;
        } else if (!attributeValue.equals(other.attributeValue))
            return false;
        return true;
    }
}
