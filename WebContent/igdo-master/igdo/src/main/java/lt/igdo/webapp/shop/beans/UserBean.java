/**
 * UserBean.java
 * Created: Oct 25, 2007 12:25:22 AM
 */
package lt.igdo.webapp.shop.beans;

import java.util.Date;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import lt.igdo.commons.constants.CommonConstants;
import lt.igdo.commons.utils.ServletUtils;
import lt.igdo.commons.utils.UniqueIdGenerator;
import lt.igdo.domain.Address;
import lt.igdo.domain.User;
import lt.igdo.ejb.services.interfaces.IUserService;

/**
 * User related services component. Basically every session has its own user,
 * either it is registered or not registered. If user is not registered it still
 * has {@link User} entity atached to her. She is identified by unique id set in
 * browser cookie. When she registers the same {@link User} entity is updated
 * with information provided (phone, address etc.) and entity status changed to
 * registered.
 * 
 * @author Ignas
 * 
 */
@ManagedBean(name = "userBean")
@SessionScoped
public class UserBean {

    /** User service remote ejb. */
	@ManagedProperty(value="#{userService}")
    private IUserService userService;

    /** Current user. */
    private User user;

    public void setUserService(IUserService userService) {
		this.userService = userService;
	}

	/**
     * Gets current user. If user is not yet presented in session, it tries to
     * get cookie set in browser, identify user and set it to session. If no
     * cookie found it creates new user and sets cookie to its browser.
     */
    @SuppressWarnings("rawtypes")
	public User getUser() {
        if (user == null) {
            FacesContext facesContext = FacesContext.getCurrentInstance();
            Map cookieMap = facesContext.getExternalContext()
                    .getRequestCookieMap();

            // Get cookie containing user unique id.
            Cookie cookie = (Cookie) cookieMap
                    .get(CommonConstants.COOKIE_PARAM_USER_ID);
            if (cookie == null
                    || cookie.getValue() == null
                    || ServletUtils.getRequest().getAttribute(
                            CommonConstants.REQUEST_PARAM_LOGOUT) == Boolean.TRUE) {
                // if cookie does not exist.
                cookie = UserBean.createNewUserCookie(null);
            }

            String uniqueId = cookie.getValue();
            User user = userService.findUserByUniqueId(uniqueId);
            if (user == null) {
                // Create new user.
                user = new User();
                user.setUniqueId(uniqueId);
                user.setVisitedOn(new Date());
                user.setRegistered(false);
                user = userService.saveUser(user);

            } else {
                // Mark last visited date if user already exists.
                user.setVisitedOn(new Date());
                user = userService.updateUser(user);
            }
            // Update cookie value.
            ServletUtils.getResponse().addCookie(cookie);
            this.user = user;
        }
        return user;
    }

    /**
     * Reloads newest data of user from database.
     */
    public void reloadUser() {
        user = userService.findUserByUniqueId(user.getUniqueId());
    }

    public void changeCurrentUserAddress(Address address) {
        user.setShippingAddress(address);
        userService.updateUser(user);
    }

    // /**
    // * Resets user.
    // */
    // public void resetUser() {
    // Cookie cookie = UserBean.createNewUserCookie(null);
    // String uniqueId = cookie.getValue();
    //
    // User user = new User();
    // user.setUniqueId(uniqueId);
    // user.setVisitedOn(new Date());
    // user.setRegistered(false);
    // user = userService.saveUser(user);
    //
    // log.debug("User #0 created", user);
    // // Update cookie value.
    // ServletUtils.getResponse().addCookie(cookie);
    // this.user = user;
    // }

    /**
     * 
     * Login user.
     * 
     * @param user
     *            User to login
     */
    public void loginUser(User user) {
        // TODO: Do something with user which was anonymous before this login
        // Create cookie containing user unique id.
        Cookie cookie = UserBean.createNewUserCookie(user.getUniqueId());
        user.setVisitedOn(new Date());
        userService.updateUser(user);
        ServletUtils.getResponse().addCookie(cookie);
        this.user = user;
    }

    /**
     * Logout current user.
     */
    @SuppressWarnings("rawtypes")
	public void logoutUser() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        Map cookieMap = facesContext.getExternalContext().getRequestCookieMap();

        // Get cookie containing user unique id.
        Cookie cookie = (Cookie) cookieMap
                .get(CommonConstants.COOKIE_PARAM_USER_ID);
        if (cookie != null) {
            // TODO set logoff parameter
            cookie.setMaxAge(-CommonConstants.COOKIE_MAX_AGE);
            ServletUtils.getResponse().addCookie(cookie);
        }
    }

    /**
     * Create new cookie for a new user.
     * 
     * @return Newly created Cookie object.
     */
    private static Cookie createNewUserCookie(String userId) {
        Cookie cookie = new Cookie(CommonConstants.COOKIE_PARAM_USER_ID,
                userId != null ? userId : UniqueIdGenerator.getUniqueId());
        HttpServletRequest request = ServletUtils.getRequest();
        cookie.setPath(request.getContextPath());
        cookie.setMaxAge(CommonConstants.COOKIE_MAX_AGE);
        return cookie;
    }

}
