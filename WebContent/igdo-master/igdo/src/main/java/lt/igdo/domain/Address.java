/**
 * Address.java
 * Created: Dec 19, 2007 7:15:11 PM
 */
package lt.igdo.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lt.igdo.domain.base.BaseEntity;

/**
 * Address class.
 * 
 * @author Ignas
 * 
 */
@Entity
@Table(name = "ADDRESSES")
public class Address extends BaseEntity implements Serializable {

    /** Generated UID for serializable classes. */
    private static final long serialVersionUID = -9134841794533072417L;

    /** Name. */
    @Column(name = "ADDRESS_NAME")
    private String name;

    /** Country. */
    @Column(name = "COUNTRY")
    private String country;

    /** City. */
    @Column(name = "CITY")
    private String city;

    /** Street. */
    @Column(name = "STREET")
    private String street;

    /** House or appartament number. */
    @Column(name = "ADDRESS_NUMBER")
    private String addressNumber;

    /**
     * Name getter.
     * 
     * @return Name.
     */
    public String getName() {
        return name;
    }

    /**
     * Name setter.
     * 
     * @param name
     *            Name to set.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Country getter.
     * 
     * @return Country.
     */
    public String getCountry() {
        return country;
    }

    /**
     * Country setter.
     * 
     * @param country
     *            Country to set.
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * City getter.
     * 
     * @return City
     */
    public String getCity() {
        return city;
    }

    /**
     * City getter.
     * 
     * @param city
     *            City to set.
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * Street getter.
     * 
     * @return Street.
     */
    public String getStreet() {
        return street;
    }

    /**
     * Street setter.
     * 
     * @param street
     *            Street to set.
     */
    public void setStreet(String street) {
        this.street = street;
    }

    /**
     * Address number getter.
     * 
     * @return Address number.
     */
    public String getAddressNumber() {
        return addressNumber;
    }

    /**
     * Address number setter.
     * 
     * @param addressNumber
     *            Address number to set.
     */
    public void setAddressNumber(String addressNumber) {
        this.addressNumber = addressNumber;
    }

}
