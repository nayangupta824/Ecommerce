/**
 * RegisterAction.java
 * Created: 12:52:11 PM Jul 25, 2008
 */
package lt.igdo.webapp.shop.actions;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import lt.igdo.commons.utils.FacesUtils;
import lt.igdo.commons.utils.PasswordUtils;
import lt.igdo.commons.utils.UniqueIdGenerator;
import lt.igdo.domain.User;
import lt.igdo.domain.UserConfirmation;
import lt.igdo.ejb.services.interfaces.IUserService;
import lt.igdo.webapp.shop.beans.UserBean;

/**
 * New user registration component. This class validates new user's data, save
 * it and sends confirmation email.
 * 
 * @author Ignas
 * 
 */
// Was flow scoped in older seam version, just making simlest solution with session scope when updating project
@ManagedBean(name = "registerAction")
@SessionScoped
public class RegisterAction {

    /** User service remote ejb. */
    private IUserService userService;

    /**
     * Injected user bean used for getting user who wants to register.
     */
    @ManagedProperty(value="#{userBean}")
    private UserBean userBean;

    /**
     * User who is going to register. Everyone using system has created user
     * account with user id in it even if they are not registered. They are
     * identified by cookie, and when they register their account status only
     * change to registered.
     */
    private User user;

    /** User's provided password. */
    private String password;

    /** User's provided password for verification. */
    private String passwordVerification;

    
    public void setUserBean(UserBean userBean) {
		this.userBean = userBean;
	}

	/**
     * Is invoked on creation of this component.
     */
    @PostConstruct
    public void create() {
        user = userBean.getUser();
    }

    /**
     * Checks if user with such email already exists.
     * 
     * @return true if email is not duplicated and false otherwise.
     */
    public boolean isNotDuplicate() {
        User userWithSameEmail = userService.findUserByEmail(user.getEmail());
        boolean duplicate = (userWithSameEmail != null);
        if (duplicate) {
            FacesUtils.error("user.registration.error.emailAlreadyExists");
        }
        return !duplicate;
    }

    /**
     * Check if provided password and password verification are equal and valid.
     * 
     * @return true if password is valid and false otherwise.
     */
    public boolean isValid() {
        boolean valid = true;
        if (password == null || password.trim().length() == 0
                || !password.equals(passwordVerification)) {
            FacesUtils.error("user.registration.error.passwordsDoNotMatch");
            valid = false;
        }
        if (user.getEmail() == null || user.getEmail().trim().length() == 0) {
        	FacesUtils.error("user.registration.error.emailEmpty");
            valid = false;
        }
        // TODO: validate password strength
        return valid;
    }

    /**
     * Update registered user's information and save her as registered.
     */
    public void saveUser() {
        // Check if confirmation is not sent to the user
        user.setPassword(PasswordUtils.hashPassword(password));
        user.setUserName(user.getEmail());
        user.setRegistered(Boolean.TRUE);
        userService.updateUser(user);
        UserConfirmation userConfirmation = userService
                .findConfirmationByUser(user);
        if (userConfirmation == null) {
            // Create new confirmation
            userConfirmation = new UserConfirmation();
            userConfirmation.setCode(UniqueIdGenerator.getUniqueId());
            userConfirmation.setUser(user);
            userService.saveUserConfirmation(userConfirmation);
        }
        
        System.out.println("Send email with confirmation code: " + userConfirmation);
    }

    /**
     * Password verification getter.
     * 
     * @return Password verification.
     */
    public String getPasswordVerification() {
        return passwordVerification;
    }

    /**
     * Password verification setter.
     * 
     * @param passwordVerification
     *            Verification to set.
     */
    public void setPasswordVerification(String passwordVerification) {
        this.passwordVerification = passwordVerification;
    }

    /**
     * Password getter.
     * 
     * @return User password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Password verification setter.
     * 
     * @param password
     *            Password to set.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * User getter.
     * 
     * @return Current user.
     */
    public User getUser() {
        return user;
    }

    /**
     * Clear user filled form.
     */
    public void clear() {
        user.setFirstName(null);
        user.setLastName(null);
        user.setEmail(null);
        user.setPassword(null);
    }

}
