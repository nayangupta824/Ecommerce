/**
 * 
 */
package lt.igdo.webapp.shop.actions;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import lt.igdo.commons.utils.FacesUtils;
import lt.igdo.domain.User;
import lt.igdo.ejb.services.interfaces.IUserService;
import lt.igdo.webapp.shop.beans.UserBean;

@ManagedBean(name = "authenticateAction")
@ViewScoped
public class AuthenticateAction {
	
	private String userEmail;
	
	private String password;

    /**
     * Injected user bean used for getting current logged in user. Needed when
     * user post comment about item.
     */
	@ManagedProperty(value="#{userBean}")
    private UserBean userBean;

    /** User service remote ejb. */
    @ManagedProperty(value="#{userService}")
    private IUserService userService;

    public void setUserBean(UserBean userBean) {
		this.userBean = userBean;
	}

	public void setUserService(IUserService userService) {
		this.userService = userService;
	}

	/**
     * @return true if user exists and password is correct and false otherwise.
     */
    public boolean authenticate() {
        User user = userService.findUserByEmail(userEmail);
        if (user == null) {
            FacesUtils.error("user.login.message.notExists");
            return false;
        } else {
            if (user.getPassword().equals(password)) {
                FacesUtils.info("user.login.message.success");
                userBean.loginUser(user);
                return true;
            } else {
            	FacesUtils.error("user.login.message.wrongPassword");
                return false;
            }
        }
    }

	

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
    
}
