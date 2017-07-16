/**
 * ConfirmAction.java
 * Created: Mar 29, 2007 9:57:58 PM
 */
package lt.igdo.webapp.shop.actions;

import java.rmi.RemoteException;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import lt.igdo.ejb.services.interfaces.IRegistrationService;

/**
 * Component to check if user confirmation code is valid.
 * 
 * @author Donatas
 * 
 */
@ManagedBean(name = "confirmAction")
@RequestScoped
public class ConfirmAction {

    /** Indicates if confirmation was successful. */
    private boolean confirmationSuccessful = false;

    /** Confirmation code request parameter. */
    private String code;
    
    @ManagedProperty(value="#{registrationService}")
    private IRegistrationService registrationService;
    

    public void setRegistrationService(IRegistrationService registrationService) {
		this.registrationService = registrationService;
	}

	/**
     * Confirms registration. If registration successful user can start use her
     * account.
     */
    public void confirmRegistration() {
        try {
            registrationService.confirm(code);
            confirmationSuccessful = true;
        } catch (RemoteException e) {
            // TODO log.
        }
    }

    /**
     * Returns if regitration confirmation was successful.
     * 
     * @return true if confirmation successful and false otherwise.
     */
    public boolean isConfirmationSuccessful() {
        return confirmationSuccessful;
    }
}
