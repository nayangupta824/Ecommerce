/**
 * Contact.java
 * Created: Jan 17, 2008 5:41:13 PM
 */
package lt.igdo.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * Contact embedable domain object.
 * 
 * @author Ignas
 * 
 */
@Embeddable
public class Contact implements Serializable {

    /** Generated UID for serializable classes. */
    private static final long serialVersionUID = 630991591740483185L;

    /** Person name. */
    @Column(name = "PERSON_NAME")
    private String personName;

    /** Person surname. */
    @Column(name = "PERSON_SURNAME")
    private String personSurname;

    /** Phone number. */
    @Column(name = "PHONE")
    private String phone;

    /** Email. */
    @Column(name = "EMAIL")
    private String email;

    /**
     * Person name getter.
     * 
     * @return Person name.
     */
    public String getPersonName() {
        return personName;
    }

    /**
     * Person name setter.
     * 
     * @param personName
     *            Person name to set.
     */
    public void setPersonName(String personName) {
        this.personName = personName;
    }

    /**
     * Person surname getter.
     * 
     * @return Person surname.
     */
    public String getPersonSurname() {
        return personSurname;
    }

    /**
     * Person surname setter.
     * 
     * @param personSurname
     *            Person surname to set.
     */
    public void setPersonSurname(String personSurname) {
        this.personSurname = personSurname;
    }

    /**
     * Phone getter.
     * 
     * @return Phone number.
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Phone setter.
     * 
     * @param phone
     *            Phone number to set.
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * Email getter.
     * 
     * @return Email.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Email setter.
     * 
     * @param email
     *            Email to set.
     */
    public void setEmail(String email) {
        this.email = email;
    }

}
