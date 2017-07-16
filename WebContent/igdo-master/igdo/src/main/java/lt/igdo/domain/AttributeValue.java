/**
 * AttributeValue.java
 * Created: 11:34:58 PM Aug 15, 2008
 */
package lt.igdo.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lt.igdo.domain.base.BaseEntity;

/**
 * @author Ignas
 * 
 */
@Entity
@Table(name = "ATTRIBUTE_VALUES")
public class AttributeValue extends BaseEntity {

    /** */
    private static final long serialVersionUID = 2463151120149763616L;

    /** */
    @Column(name = "CODE")
    private String code;

    /** */
    @Column(name = "ATTRIBUTE_VALUE")
    private String value;

    /**
     * @return code.
     */
    public String getCode() {
        return code;
    }

    /**
     * @param code
     *            code to set.
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * @return value.
     */
    public String getValue() {
        return value;
    }

    /**
     * @param value
     *            value to set.
     */
    public void setValue(String value) {
        this.value = value;
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((code == null) ? 0 : code.hashCode());
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
        AttributeValue other = (AttributeValue) obj;
        if (code == null) {
            if (other.code != null)
                return false;
        } else if (!code.equals(other.code))
            return false;
        return true;
    }

}
