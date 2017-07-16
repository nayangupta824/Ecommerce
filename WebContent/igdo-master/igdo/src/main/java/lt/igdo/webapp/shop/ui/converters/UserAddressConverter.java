/**
 * UserAddressConverter.java
 * Created: 1:45:15 PM Aug 31, 2008
 */
package lt.igdo.webapp.shop.ui.converters;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import lt.igdo.domain.Address;
import lt.igdo.webapp.shop.beans.UserBean;

/**
 * User address converter using current logged in user.
 * 
 * @author Ignas
 * 
 */
@RequestScoped
@ManagedBean(name = "userAddressConverter")
public class UserAddressConverter implements Converter {

    /**
     * Injected user bean used for getting current logged in user.
     */
	@ManagedProperty(value = "#{userBean}")
    private UserBean userBean;;

    public void setUserBean(UserBean userBean) {
		this.userBean = userBean;
	}

	/**
     * @see javax.faces.convert.Converter#getAsObject(javax.faces.context.FacesContext,
     *      javax.faces.component.UIComponent, java.lang.String)
     */
    public Object getAsObject(FacesContext context, UIComponent component,
            String value) {

        if (value == null || value.trim().length() == 0)
            return null;

        Long id = Long.valueOf(value);

        return getAddressById(id);
    }

    /**
     * @see javax.faces.convert.Converter#getAsString(javax.faces.context.FacesContext,
     *      javax.faces.component.UIComponent, java.lang.Object)
     */
    public String getAsString(FacesContext context, UIComponent component,
            Object object) {

        Address address = (Address) object;

        return address.getId().toString();
    }

    /**
     * @param id
     * @return
     */
    private Address getAddressById(Long id) {
        for (Address address : userBean.getUser().getUserAddresses()) {
            if (id.equals(address.getId())) {
                return address;
            }
        }
        return null;
    }

}
