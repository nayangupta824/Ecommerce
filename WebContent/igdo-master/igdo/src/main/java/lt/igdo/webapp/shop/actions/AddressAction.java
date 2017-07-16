/**
 * AddressAction.java
 * Created: 1:12:34 AM Aug 31, 2008
 */
package lt.igdo.webapp.shop.actions;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import lt.igdo.domain.Address;
import lt.igdo.ejb.services.interfaces.IUserService;
import lt.igdo.webapp.shop.beans.UserBean;

/**
 * User address book actions.
 * 
 * @author Ignas
 * 
 */
@ManagedBean(name = "addressAction")
@ViewScoped
public class AddressAction {

    /** User service remote ejb. */
	@ManagedProperty(value="#{userService}")
    private IUserService userService;

    /** New address to add. */
    private Address address;

    /** */
    private Address selectedAddress;

    /**
     * Injected user bean used for getting current logged in user.
     */
    @ManagedProperty(value="#{userBean}")
    private UserBean userBean;

    /**
     * Is invoked on creation of this component.
     */
    @PostConstruct
    public void create() {
        address = new Address();
    }
    
    public void setUserService(IUserService userService) {
		this.userService = userService;
	}
    
	public void setUserBean(UserBean userBean) {
		this.userBean = userBean;
	}

	/**
     * Adds new address to user address book.
     * 
     * @return View of user address book management.
     */
    public String addNewAddress() {
        userService.saveUserAddress(userBean.getUser(), address);
        userBean.reloadUser();
        address = new Address();
        return "/pages/user/preferences/addresses.xhtml";
    }

    /**
     * New address getter.
     * 
     * @return Address.
     */
    public Address getAddress() {
        return address;
    }

    /**
     * @return Addresses
     */
    public List<Address> getUserAddresses() {
        return userBean.getUser().getUserAddresses();
    }

    /**
     * @return selectedAddress.
     */
    public Address getSelectedAddress() {
        return selectedAddress != null ? selectedAddress : userBean.getUser()
                .getShippingAddress();
    }

    /**
     * @param selectedAddress
     *            selectedAddress to set.
     */
    public void setSelectedAddress(Address selectedAddress) {
        userBean.changeCurrentUserAddress(selectedAddress);
        this.selectedAddress = selectedAddress;
    }

}
