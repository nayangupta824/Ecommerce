/**
 * ShopManager.java
 * Created: Nov 29, 2007 2:51:15 PM
 */
package lt.igdo.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lt.igdo.domain.base.BaseEntity;

/**
 * Property domain object.
 * 
 * @author Ignas
 * 
 */
@Entity(name = "Property")
@Table(name = "PROPERTIES")
public class Property extends BaseEntity {

    /** Generated UID for serializable classes. */
    private static final long serialVersionUID = -5479234925161659758L;

    /**
     * Property key field. This field is unique and value lookup is done by this
     * field.
     */
    @Column(name = "PROPERTY_KEY", nullable = false)
    private String key;

    /** Property value. */
    @Column(name = "PROPERTY_VALUE", nullable = false)
    private String value;

    /** Explanation what is property responsible for. */
    @Column(name = "EXPLANATION", nullable = false)
    private String explanation;

    /**
     * Key getter.
     * 
     * @return Key.
     */
    public String getKey() {
        return key;
    }

    /**
     * Key setter.
     * 
     * @param key
     *            Key to set.
     */
    public void setKey(String key) {
        this.key = key;
    }

    /**
     * Value getter.
     * 
     * @return Value.
     */
    public String getValue() {
        return value;
    }

    /**
     * Value setter.
     * 
     * @param value
     *            Value to set.
     */
    public void setValue(String value) {
        this.value = value;
    }

    /**
     * Explanation getter.
     * 
     * @return Explanation.
     */
    public String getExplanation() {
        return explanation;
    }

    /**
     * Explanation setter.
     * 
     * @param explanation
     *            Explanation to set.
     */
    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }

}
