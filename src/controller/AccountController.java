package controller;

import java.lang.Object;
import javax.faces.application.FacesMessage;
import java.util.concurrent.TimeUnit;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.primefaces.context.RequestContext;
import entities.Account;
import model.*;
import java.util.*;  
import javax.servlet.http.Part;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.security.Key;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
@ManagedBean(name = "AccountController")
@SessionScoped
public class AccountController {
	private Account account = new Account();
	private String errorMessage = "";
	private boolean remember = false;
	private Map<String, Object> map;
	private String email;
	private String repassword;
	public String quantity = "0";
	private ArrayList<ArrayList<String>> items;
	public boolean redirected = true;
	public String snackbarmessage;
	public String name = "Test";
	public int cartquantity = 0;
	public int errorcode = -1;
	private ArrayList<ArrayList<Integer>> cart;
	private String[] imgarr;
	private String[] itemnamearr;
	private String[] pricearr;
	private int carttotal;
	private double carttax;
	private double cartdis;
	private double cartgrandtotal;
	public int removequan;
	public int removeid;
	public double dis = 0;
	public String[] couponcode = {"NEW10","NEW20","NEW50"};
	public int [] couponcodedis = {10,20,50};
	public String currcouponcode = null;
	public String cartgrandtotals;
	public String search_input;
	private Part userimage;
	private String checkoutaddress;
	public String selectedState;
	public String selectedCity;
	public ArrayList<String> states;
	public ArrayList<String> cities;
	public ArrayList<ArrayList<Integer>> orders;
	public int ordertotal;
	public int orderquantity;
	public String orderstatus;
	public String mobile;
	public String gallerycategory="";
	public ArrayList<ArrayList<String>> sortedlist;
	int sort = 0;
	public boolean success = false;
	public boolean loggedin;
	public String none = "none";
	public String resetuser;
	public String errorMessage2;
	public String errorMessage3;
	public String newpass;
	
	
	
	public String getNewpass() {
		return newpass;
	}

	public void setNewpass(String newpass) {
		this.newpass = newpass;
	}

	public String getErrorMessage3() {
		return errorMessage3;
	}

	public void setErrorMessage3(String errorMessage3) {
		this.errorMessage3 = errorMessage3;
	}

	public String getErrorMessage2() {
		return errorMessage2;
	}

	public void setErrorMessage2(String errorMessage2) {
		this.errorMessage2 = errorMessage2;
	}

	public String getResetuser() {
		return resetuser;
	}

	public void setResetuser(String resetuser) {
		this.resetuser = resetuser;
	}

	public String getNone() {
		return none;
	}

	public void setNone(String none) {
		this.none = none;
	}

	public boolean isLoggedin() {
		return loggedin;
	}

	public void setLoggedin(boolean loggedin) {
		this.loggedin = loggedin;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public int getSort() {
		return sort;
	}

	public void setSort(int sort) {
		this.sort = sort;
	}

	public ArrayList<ArrayList<String>> getSortedlist() {
		return sortedlist;
	}

	public void setSortedlist(ArrayList<ArrayList<String>> sortedlist) {
		this.sortedlist = sortedlist;
	}

	public String getGallerycategory() {
		return gallerycategory;
	}

	public void setGallerycategory(String gallerycategory) {
		this.gallerycategory = gallerycategory;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getOrderstatus() {
		return orderstatus;
	}

	public void setOrderstatus(String orderstatus) {
		this.orderstatus = orderstatus;
	}

	public int getOrderquantity() {
		return orderquantity;
	}

	public void setOrderquantity(int orderquantity) {
		this.orderquantity = orderquantity;
	}

	public int getOrdertotal() {
		return ordertotal;
	}

	public void setOrdertotal(int ordertotal) {
		this.ordertotal = ordertotal;
	}

	public ArrayList<ArrayList<Integer>> getOrders() {
		return orders;
	}

	public void setOrders(ArrayList<ArrayList<Integer>> orders) {
		this.orders = orders;
	}

	public ArrayList<String> getStates() {
		return states;
	}

	public void setStates(ArrayList<String> states) {
		this.states = states;
	}

	public ArrayList<String> getCities() {
		return cities;
	}

	public void setCities(ArrayList<String> cities) {
		this.cities = cities;
	}

	public String getSelectedState() {
		return selectedState;
	}

	public void setSelectedState(String selectedState) {
		this.selectedState = selectedState;
	}

	public String getSelectedCity() {
		return selectedCity;
	}

	public void setSelectedCity(String selectedCity) {
		this.selectedCity = selectedCity;
	}

	public String getCheckoutaddress() {
		return checkoutaddress;
	}

	public void setCheckoutaddress(String checkoutaddress) {
		this.checkoutaddress = checkoutaddress;
	}

	public Part getUserimage() {
		return userimage;
	}

	public void setUserimage(Part userimage) {
		this.userimage = userimage;
	}

	public String getSearch_input() {
		return search_input;
	}

	public void setSearch_input(String search_input) {
		this.search_input = search_input;
	}

	public String getCartgrandtotals() {
		return cartgrandtotals;
	}

	public void setCartgrandtotals(String cartgrandtotals) {
		this.cartgrandtotals = cartgrandtotals;
	}

	public String getCurrcouponcode() {
		return currcouponcode;
	}

	public void setCurrcouponcode(String currcouponcode) {
		this.currcouponcode = currcouponcode;
	}

	public String[] getCouponcode() {
		return couponcode;
	}

	public void setCouponcode(String[] couponcode) {
		this.couponcode = couponcode;
	}

	public int[] getCouponcodedis() {
		return couponcodedis;
	}

	public void setCouponcodedis(int[] couponcodedis) {
		this.couponcodedis = couponcodedis;
	}

	public double getDis() {
		return dis;
	}

	public void setDis(double dis) {
		this.dis = dis;
	}

	

	public double getCarttax() {
		return carttax;
	}

	public void setCarttax(double carttax) {
		this.carttax = carttax;
	}

	public double getCartdis() {
		return cartdis;
	}

	public void setCartdis(double cartdis) {
		this.cartdis = cartdis;
	}

	public double getCartgrandtotal() {
		return cartgrandtotal;
	}

	public void setCartgrandtotal(double cartgrandtotal) {
		this.cartgrandtotal = cartgrandtotal;
	}

	public int getRemoveid() {
		return removeid;
	}

	public void setRemoveid(int removeid) {
		this.removeid = removeid;
	}

	public int getRemovequan() {
		return removequan;
	}

	public void setRemovequan(int removequan) {
		this.removequan = removequan;
	}

	public int getCarttotal() {
		return carttotal;
	}

	public void setCarttotal(int carttotal) {
		this.carttotal = carttotal;
	}

	public String[] getPricearr() {
		return pricearr;
	}

	public void setPricearr(String[] pricearr) {
		this.pricearr = pricearr;
	}

	public String[] getItemnamearr() {
		return itemnamearr;
	}

	public void setItemnamearr(String[] itemnamearr) {
		this.itemnamearr = itemnamearr;
	}

	public String[] getImgarr() {
		return imgarr;
	}

	public void setImgarr(String[] imgarr) {
		this.imgarr = imgarr;
	}

	public ArrayList<ArrayList<Integer>> getCart() {
		return cart;
	}

	public void setCart(ArrayList<ArrayList<Integer>> cart) {
		this.cart = cart;
	}

	public int getErrorcode() {
		return errorcode;
	}

	public void setErrorcode(int errorcode) {
		this.errorcode = errorcode;
	}

	public int getCartquantity() {
		return cartquantity;
	}

	public void setCartquantity(int cartquantity) {
		this.cartquantity = cartquantity;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	public String getSnackbarmessage() {
		return snackbarmessage;
	}

	public void setSnackbarmessage(String snackbarmessage) {
		this.snackbarmessage = snackbarmessage;
	}

	public boolean isRedirected() {
		return redirected;
	}

	public void setRedirected(boolean redirected) {
		this.redirected = redirected;
	}

	public ArrayList<ArrayList<String>> getItems() {
		return items;
	}

	public void setItems(ArrayList<ArrayList<String>> items) {
		this.items = items;
	}

	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRepassword() {
		return repassword;
	}

	public void setRepassword(String repassword) {
		this.repassword = repassword;
	}

	public boolean isRemember() {
		return remember;
	}

	public void setRemember(boolean remember) {
		this.remember = remember;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}
	
	public void login()
	{
		
		AccountModel accountModel = new AccountModel();
		//check if username of password is empty
		 if(this.account.getUsername() == "")
		 {
			 this.errorMessage = "Username is required !";
			 return;
		 }
		 if(this.account.getPassword() == "")
		 {
			 this.errorMessage = "Password is required !";
			 return;
		 }
		
		
		FacesContext facesContext = FacesContext.getCurrentInstance();
		HttpServletResponse response = (HttpServletResponse) facesContext.getExternalContext().getResponse();
		if(accountModel.login(this.account.getUsername(), this.account.getPassword()))
		{
			if(this.remember)
			{
				Cookie ckUsername = new Cookie("username",this.account.getUsername());
				ckUsername.setMaxAge(3600);
				response.addCookie(ckUsername);
				Cookie ckPassword = new Cookie("password",this.account.getPassword());
				ckUsername.setMaxAge(3600);
				response.addCookie(ckPassword);
			}
			//check if account has been verified
			if(!accountModel.checkverified(this.account.getUsername()))
			{
				this.errorMessage = "Verify your account first! Verification Link sent to your email";
				this.redirected = true;
				redirect("index.xhtml");
			}
			else
			{
				//create session
				FacesContext context = FacesContext.getCurrentInstance();
				context.getExternalContext().getSessionMap().put("username",this.account.getUsername());
				System.out.println(context.getExternalContext().getSessionMap().get("username"));
				
				redirect("itemsx.xhtml");
			}
			
		}
		else
		{
			this.errorMessage = "Account's Invalid";
			this.redirected = true;
			redirect("index.xhtml");
		}
	}
	
	public void logout()
	{
		FacesContext facesContext = FacesContext.getCurrentInstance();
		HttpServletRequest request = (HttpServletRequest) facesContext.getExternalContext().getRequest();
		HttpServletResponse response = (HttpServletResponse) facesContext.getExternalContext().getResponse();
		//Remove cookie
		for(Cookie ck : request.getCookies())
		{
			if(ck.getName().equalsIgnoreCase("username"))
			{
				ck.setMaxAge(0);
				response.addCookie(ck);
			}
			if(ck.getName().equalsIgnoreCase("password"))
			{
				ck.setMaxAge(0);
				response.addCookie(ck);
			}
		}
		//delete session
		FacesContext context = FacesContext.getCurrentInstance();
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		System.out.println(context.getExternalContext().getSessionMap().get("username"));
		try{
		TimeUnit.SECONDS.sleep(2);
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		this.redirected = false;
		redirect("index.xhtml");
	}
	
	public void verifyLogin()
	{
		Account acc = checkCookie();
		if(acc != null)
		{
			System.out.println("Cookie exists");
			AccountModel accountModel = new AccountModel();
			if(acc.getPassword() != null)
			{
				if(accountModel.login(acc.getUsername(), acc.getPassword()))
				{
					this.account = acc;
					redirect("items.xhtml");
					
				}
				else
				{
					this.errorMessage = "Account's Invalid";
				}
			}

		}
		else
		{
			FacesContext context = FacesContext.getCurrentInstance();
			System.out.println(context.getExternalContext().getSessionMap().get("username"));
			if(context.getExternalContext().getSessionMap().get("username") != null)
			{
				System.out.println("Session already exists");
				redirect("items.xhtml");
			}
		}
	}
	public void verifyLoginOnWelcome()
	{
		this.errorMessage = "";
		FacesContext context = FacesContext.getCurrentInstance();
		String url = context.getViewRoot().getViewId();
		System.out.println(url);
		//check for cookie
		Account acc = checkCookie();
		if(acc != null)
		{
			System.out.println("Cookie found");
			System.out.println(acc.getUsername());
			this.account.setUsername(acc.getUsername());
			this.account.setPassword(acc.getPassword());
		}
		//check if session exists
		else if(context.getExternalContext().getSessionMap().get("username") == null || context.getExternalContext().getSessionMap().get("username").equals(""))
		{
			redirect("index.xhtml");
		}
		
	}
	public void verifyLoginOnWelcomedummy()
	{
		this.errorMessage = "";
		FacesContext context = FacesContext.getCurrentInstance();
		String url = context.getViewRoot().getViewId();
		System.out.println(url);
		//check for cookie
		Account acc = checkCookie();
		if(acc != null)
		{
			System.out.println("Cookie found");
			System.out.println(acc.getUsername());
			this.account.setUsername(acc.getUsername());
			this.account.setPassword(acc.getPassword());
		}
		//check if session exists
		else if(context.getExternalContext().getSessionMap().get("username") == null || context.getExternalContext().getSessionMap().get("username").equals(""))
		{
			
		}
		
	}
	
	private void redirect(String page)
	{
		try{
			FacesContext fc = FacesContext.getCurrentInstance();
			fc.getExternalContext().redirect(page);
			
		} catch(Exception e){
			System.out.println(e);
		}
	}
	
	private Account checkCookie()
	{
		Account account = null;
		FacesContext facesContext = FacesContext.getCurrentInstance();
		HttpServletRequest request = (HttpServletRequest) facesContext.getExternalContext().getRequest();
		String username = "", password = "";
		Cookie [] cookies = request.getCookies();
		if(cookies != null)
		{
			for(Cookie ck : cookies)
			{
				if(ck.getName().equalsIgnoreCase("username"))
					username = ck.getValue();
				if(ck.getName().equalsIgnoreCase("password"))
					password = ck.getValue();
			}
		}
		if(!username.isEmpty() && !password.isEmpty())
		{
			account = new Account(username,password);
		}
		return account;
	}
	
	public void signup()
	{
		AccountModel accountModel = new AccountModel();
		String pass1 = this.account.getPassword();
		String pass2 = this.getRepassword();
		//check if username exists already
		if(accountModel.checkusernameredundancy(this.account.getUsername()))
		{
			System.out.println("here");
			FacesContext.getCurrentInstance().addMessage("form:password", new FacesMessage("Username can't be used", "Username can't be used"));
		}
		//check if email already exists
		else if(accountModel.checkemailalreadyused(this.getEmail()))
		{
			FacesContext.getCurrentInstance().addMessage("form:password", new FacesMessage("E-mail already in use", "E-mail already in use"));
		}
		else
		{
			//check if password is same
			if(pass1.equals(pass2))
			{
				//add entry to database
				accountModel.signup(this.account.getUsername(), this.account.getPassword(),this.getEmail(),this.mobile,0);
				//send email verification link
				String msg = "Hi " + this.account.getUsername() + "!\n\n" + "Please click the below link to verify your account : \n";
				HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
				String url = request.getRequestURL().toString() ;
				url = url.substring(0, url.length() - 12)  + "verify.xhtml";
				String username = this.account.getUsername();
				//hash username
				try{
				String text = username;
				String key = "Bar12345Bar12345";
				Key aesKey = new SecretKeySpec(key.getBytes(), "AES");
				Cipher cipher = Cipher.getInstance("AES");
				cipher.init(Cipher.ENCRYPT_MODE, aesKey);
				byte[] encrypted = cipher.doFinal(text.getBytes());
				Base64.Encoder encoder = Base64.getEncoder();
				String encryptedString = encoder.encodeToString(encrypted);
				System.out.println(encryptedString);
				username = encryptedString;
				}catch(Exception e)
				{
					System.out.println(e);
				}
				
				
				url = url + "?user=" + username;
				System.out.println(url);
				msg = msg + url;
				String from = "test@gmail.com";
				 String password = "test@123";
				 String to = this.getEmail();
				 String sub = "Account Verification";
				Properties props = new Properties();    
		         props.put("mail.smtp.host", "smtp.gmail.com");    
		         props.put("mail.smtp.socketFactory.port", "465");    
		         props.put("mail.smtp.socketFactory.class",    
		                   "javax.net.ssl.SSLSocketFactory");    
		         props.put("mail.smtp.auth", "true");   
		         props.put("mail.smtp.port", "465");    
		         //get Session   
		         Session session = Session.getDefaultInstance(props,    
		          new javax.mail.Authenticator() {    
		          protected PasswordAuthentication getPasswordAuthentication() {    
		          return new PasswordAuthentication(from,password);  
		          }    
		         });    
		         //compose message    
		         try {    
		          MimeMessage message = new MimeMessage(session);    
		          message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));    
		          message.setSubject(sub);    
		          message.setText(msg);    
		          //send message  
		          Transport.send(message);    
		          System.out.println("message sent successfully");    
		         } catch (MessagingException e) {throw new RuntimeException(e);} 
				
				
				
				this.login();
				
				
			}
			else
			{
				FacesContext.getCurrentInstance().addMessage("form:password", new FacesMessage("Passwords don't match", "Passwords don't match"));
			}
		}
		
	}
	
	public void loadgallery()
	{
		//set variables such that javascript can fetch it
		ArrayList<ArrayList<String>> map = new ArrayList<ArrayList<String>>();
		ArrayList<String> values = new ArrayList<String>();
		AccountModel accountModel = new AccountModel();
		map = accountModel.getitems();
		boolean shuffle = true;
		
		//map contains the whole items in our database
		System.out.println("Key = ");
		System.out.println(this.search_input);
		if(this.search_input == null || this.search_input == "")
		{
			//now if the search_input is empty , load everything (NAYAN)
			
			items = map;
			//shuffle the gallery
			/*if(shuffle)
			{
				long seed = System.nanoTime();
				Collections.shuffle(items,new Random(seed));
				System.out.println(items);
			}*/
			
		}
			
		else
		{
			//else load only elements with search key
			System.out.println(map);
			int s = map.size(),i,j;
			ArrayList<ArrayList<String>> mapn = new ArrayList<ArrayList<String>>();
			ArrayList<String> valuesn = new ArrayList<String>();
			ArrayList<String> tmp = new ArrayList<String>();
			String name;
			for(i=0;i<s;i++)
			{
				tmp = map.get(i);
				name = tmp.get(1);
				if(name.toLowerCase().contains(this.search_input.toLowerCase()))
				{
					mapn.add(tmp);
				}
			}
			items = mapn;
			//if items not found give an error and load everything
			if(items.size() == 0)
			{
				items = map;
				//FacesContext.getCurrentInstance().addMessage("form:search_input", new FacesMessage("No results found! ... showing evertyhing", ""));
			}
		}
		if(this.sort == 1 || this.sort == 2)
		{
			items = this.sortedlist;
		}
		System.out.println(map);
	}
	public void loadgalleryn()
	{
		//set variables such that javascript can fetch it
		ArrayList<ArrayList<String>> map = new ArrayList<ArrayList<String>>();
		ArrayList<String> values = new ArrayList<String>();
		AccountModel accountModel = new AccountModel();
		map = accountModel.getitems();
		//map contains the whole items in our database
		System.out.println("Key = ");
		System.out.println(this.search_input);
		if(this.search_input == null || this.search_input == "")
		{
			//now if the search_input is empty , load everything (NAYAN)
			
			items = map;
			
		}
			
		else
		{
			//else load only elements with search key
			this.sort = 0;
			System.out.println(map);
			int s = map.size(),i,j;
			ArrayList<ArrayList<String>> mapn = new ArrayList<ArrayList<String>>();
			ArrayList<String> valuesn = new ArrayList<String>();
			ArrayList<String> tmp = new ArrayList<String>();
			String name;
			for(i=0;i<s;i++)
			{
				tmp = map.get(i);
				name = tmp.get(1);
				if(name.toLowerCase().contains(this.search_input.toLowerCase()))
				{
					mapn.add(tmp);
				}
			}
			items = mapn;
			//if items not found give an error and load everything
			if(items.size() == 0)
			{
				items = map;
				FacesContext.getCurrentInstance().addMessage("form:search_input", new FacesMessage("No results found! ... showing evertyhing", ""));
			}
		}
		if(this.sort == 1 || this.sort == 2)
		{
			items = this.sortedlist;
		}
		System.out.println(map);
		System.out.println(map);
	}
	public void addtocart()
	{
		System.out.println("yo");
		
	}
	
	public void mail()
	{
		
	}
	
	public void updatecart()
	{
		//check if user is logged in or not
		System.out.println("user is :" + this.account.getUsername());
		if(this.account.getUsername() == null || this.account.getUsername() == "")
		{
			this.errorcode = 7;
			return;
		}
		
		System.out.println(this.name);
		System.out.println(this.quantity);
		System.out.println("added to cart");
		AccountModel accountModel = new AccountModel();
		int status = accountModel.updateusercart(this.account.getUsername() , this.name , this.quantity);
		if(status == 2)
		{
			System.out.println("Addition to cart successfull! ....");
			cartquantity++;
		}
		else if(status == 0)
		{
			System.out.println("Item not found ! ...");
		}
		else if(status == 1)
		{
			System.out.println("Demanded quantity not available! ...");
		}
		else if(status == 3)
		{
			System.out.println("Please login from a valid account!....");
		}
		this.errorcode = status;
	}
	
	public void loadcart()
	{
		//get the contents of cart
		
		//Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		ArrayList<ArrayList<Integer>> map = new ArrayList<ArrayList<Integer>>();
		AccountModel accountModel = new AccountModel();
		
		imgarr = accountModel.loadimages();
		itemnamearr = accountModel.loaditemname();
		pricearr = accountModel.loadprice();
		carttotal = accountModel.calculatetotal(this.account.getUsername(),pricearr);
		carttax = (0.125) * carttotal;
		cartdis = (dis/100) * carttotal;
		cartgrandtotal = carttotal + carttax - cartdis;
		System.out.println(imgarr);
		System.out.println(itemnamearr);
		map = accountModel.loadcart(this.account.getUsername());
		System.out.println(map);
		cart = map;
		this.cartquantity = cart.size();
		//System.out.println(cart[0]);
	}
	public void verifycartisnotempty()
	{
		try{
			if(cart.size() == 0)
			{
				redirect("items.xhtml");
				this.errorcode = 4;
				/*ERROR CODE
				 * 4-->cart is empty
				 * */
			}
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}
	public void remove()
	{
		System.out.println(this.removequan);
		System.out.println(this.removeid);
		//here the given quantity is removed with itemid in removeid
		
		//first check that the quantity is valid
		int incart = 0;
		for(int i=0;i<this.cart.size();i++)
		{
			int id = this.cart.get(i).get(0);
			System.out.println(this.cart.get(i).get(0));
			if(id == removeid)
			{
				incart = this.cart.get(i).get(1);
				break;
			}
		}
		
		
		//System.out.println(this.cart.get(removeid-1));
		//int incart = this.cart.get(removeid-1).get(1);
		if(incart < removequan || removequan < 0)
		{
			FacesContext.getCurrentInstance().addMessage("form:password", new FacesMessage("Dont try to be smart!", "Dont try to be smart!"));
		}
		else
		{
			AccountModel accountModel = new AccountModel();
			boolean rem = false;  //this bool is used to tell remove fn. of model that the all quantities of the item are to be removed or not
			if(incart == removequan)
			{
				rem = true;
			}
			accountModel.remove(this.account.getUsername(),this.removeid,this.removequan,incart,rem);
		}
		
	}
	public void applycode()
	{
		String code = this.getCurrcouponcode();
		System.out.println(code);
		if(code != null)
		{
			//check if code is valid
			int l = couponcode.length;
			int i;
			boolean f = false;
			for(i=0;i<l;i++)
			{
				if(code.equals(couponcode[i]))
				{
					f = true;
					break;
				}
			}
			if(f)
			{
				//fetch the corresponding discount
				this.dis = couponcodedis[i];
				this.cartdis = ((double)(this.dis)/100)*(this.carttotal);
				System.out.println(this.cartdis);
				cartgrandtotal = this.carttotal + this.carttax - this.cartdis;
				cartgrandtotals = Double.toString(cartgrandtotal);
				System.out.println(this.cartgrandtotals);
				test();
			}
			else
			{
				//Throw an error message
				FacesContext.getCurrentInstance().addMessage("form:password", new FacesMessage("Invalid Code !", "Invalid Code!"));
			}
		}
	}
	public void test()
	{
		System.out.println(cartgrandtotal);
	}
	public void upload()
	{
		
	}
	
	public void checkout()
	{
		//check if dropdown is on select 
		if(this.selectedState.equals("select"))
		{
			FacesContext.getCurrentInstance().addMessage("", new FacesMessage("Please select a State!", "Please select a State!"));
			return;
		}
		if(this.selectedCity.equals("select"))
		{
			FacesContext.getCurrentInstance().addMessage("", new FacesMessage("Please select a City!", "Please select a City!"));
			return;
		}
		if(this.checkoutaddress == null || this.checkoutaddress == "")
		{
			FacesContext.getCurrentInstance().addMessage("", new FacesMessage("Please enter your street address!", "Please enter your street address!"));
			return;
		}
		//remove items from cart table and add to orders table
		//first check if cart has not been made empty by other session
		AccountModel accountModel = new AccountModel();
		cart = accountModel.loadcart(this.account.getUsername());
		System.out.println("cart is ::::::: " + cart);
		System.out.println(Arrays.toString(this.itemnamearr));
		if(this.cart.size() == 0)
		{
			this.errorcode = 4;
			redirect("items.xhtml");
			return;
		}
		
		Boolean success = false;
		success = accountModel.carttoorders(this.account.getUsername(),this.cart,this.cartgrandtotal);
		if(success)
		{
			//then send a mail
			 String from = "test@gmail.com";
			 String password = "test@123";
			 String to = accountModel.getEmail(this.account.getUsername());
			 String sub = "ORDER PLACED";
			 String msg = "Hi " + this.account.getUsername() + "!\n\n" + "Your Order has been placed successfully for the following items:\n\n" + "NAME                                         QUANTITY\n--------------------------------------------------------------------------------------\n";
				for(int i=0;i<this.cart.size();i++)
				{
					int id = this.cart.get(i).get(0);
					msg +=  this.itemnamearr[id-1] + "                                  " + this.cart.get(i).get(1) + "\n";
					
					System.out.println(id);
				}
				System.out.println(msg);
			 msg += "\n\nPlease pay ₹" + this.cartgrandtotal + " through COD when the delivery guy arrives.\n" + "Address provided : " + this.checkoutaddress + " " + this.selectedCity + ", " + this.selectedState;
			 msg +=  "\n\nThanks for shopping....\n\n\n";
			 msg += "MyEcommerce :)";
			 
			 
			 Properties props = new Properties();    
	         props.put("mail.smtp.host", "smtp.gmail.com");    
	         props.put("mail.smtp.socketFactory.port", "465");    
	         props.put("mail.smtp.socketFactory.class",    
	                   "javax.net.ssl.SSLSocketFactory");    
	         props.put("mail.smtp.auth", "true");   
	         props.put("mail.smtp.port", "465");    
	         //get Session   
	         Session session = Session.getDefaultInstance(props,    
	          new javax.mail.Authenticator() {    
	          protected PasswordAuthentication getPasswordAuthentication() {    
	          return new PasswordAuthentication(from,password);  
	          }    
	         });    
	         //compose message    
	         try {    
	          MimeMessage message = new MimeMessage(session);    
	          message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));    
	          message.setSubject(sub);    
	          message.setText(msg);    
	          //send message  
	          Transport.send(message);    
	          System.out.println("message sent successfully");    
	         } catch (MessagingException e) {throw new RuntimeException(e);} 
	         
	         //send SMS
	         try {
	 			// Construct data
	 			String apiKey = "apikey=" + "8uSGVAouwXI-QpcD8vMVl1PdHJaZvXiRh9YPITicYC";
	 		    msg = "Hi " + this.account.getUsername() + "!.Your Order amounting to Rs." + this.cartgrandtotal + " placed successfully.";
	 			String message = "&message=" + msg;
	 			String sender = "&sender=" + "TXTLCL";
	 			String mobile = accountModel.getMobile(this.account.getUsername());
	 			String numbers = "&numbers=" + "91" + mobile;
	 			System.out.println(numbers);
	 			// Send data
	 			HttpURLConnection conn = (HttpURLConnection) new URL("https://api.textlocal.in/send/?").openConnection();
	 			String data = apiKey + numbers + message + sender;
	 			conn.setDoOutput(true);
	 			conn.setRequestMethod("POST");
	 			conn.setRequestProperty("Content-Length", Integer.toString(data.length()));
	 			conn.getOutputStream().write(data.getBytes("UTF-8"));
	 			final BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	 			final StringBuffer stringBuffer = new StringBuffer();
	 			String line;
	 			while ((line = rd.readLine()) != null) {
	 				stringBuffer.append(line);
	 			}
	 			rd.close();
	 			
	 			System.out.println(stringBuffer.toString());
	 		} catch (Exception e) {
	 			System.out.println("Error SMS "+e);
	 			//return "Error "+e;
	 		}
	        
	        this.errorcode = 5;
	        redirect("items.xhtml");
		}
		
		
		
	}
	public void populateState()
	{
		//here the state dropdown is populated from database
		AccountModel accountModel = new AccountModel();
		this.states = accountModel.getStates();
		System.out.println(this.selectedState);
		this.cities = accountModel.getCities(this.selectedState);
		//System.out.println(states);
	}
	
	public void changeState()
	{
		//load cities for selected state
		AccountModel accountModel = new AccountModel();
		this.cities = accountModel.getCities(this.selectedState);
	}
	
	
	public void loadorders()
	{
		ArrayList<ArrayList<Integer>> map = new ArrayList<ArrayList<Integer>>();
		AccountModel accountModel = new AccountModel();
		
		imgarr = accountModel.loadimages();
		itemnamearr = accountModel.loaditemname();
		pricearr = accountModel.loadprice();
		ordertotal = accountModel.calculateorderstotal(this.account.getUsername());
		orderstatus = "Under Process";
		map = accountModel.loadorders(this.account.getUsername());
		System.out.println(map);
		orders = map;
		this.orderquantity = orders.size();
	}
	public void verifyordersisnotempty()
	{
		try{
			if(orders.size() == 0)
			{
				redirect("items.xhtml");
				this.errorcode = 6;
				/*ERROR CODE
				 * 6-->order is empty
				 * */
			}
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}
	public void loadtshirts()
	{
		this.search_input = "tshirt";
		this.sort = 0;
		loadgalleryn();
	}
	public void loadshoes()
	{
		this.search_input = "shoes";
		this.sort = 0;
		loadgalleryn();
	}
	public void loadeverything()
	{
		this.search_input = "";
		this.sort = 0;
		loadgalleryn();
	}
	public void next()
	{
			redirect("items.xhtml");	
	}
	public void sort(String order)
	{
		ArrayList<ArrayList<String>> map = new ArrayList<ArrayList<String>>();
		ArrayList<String> values = new ArrayList<String>();
		AccountModel accountModel = new AccountModel();
		map = this.items;
		int s = map.size(),i,j;
			ArrayList<ArrayList<String>> mapn = new ArrayList<ArrayList<String>>();
			ArrayList<String> valuesn = new ArrayList<String>();
			ArrayList<String> tmp = new ArrayList<String>();
			String name;
			ArrayList<Integer> prices = new ArrayList<Integer>();
			for(i=0;i<s;i++)
			{
				tmp = map.get(i);
				prices.add(Integer.parseInt(tmp.get(5)));
				
			}
			Collections.sort(prices);
			System.out.println(prices);
			int l = prices.size(),curr;
			for(i=0;i<l;i++)
			{
				curr = prices.get(i);
				//search in map for the element where it has this price
				for(j=0;j<map.size();j++)
				{
					tmp = map.get(j);
					if(Integer.parseInt(tmp.get(5)) == curr)
					{
						mapn.add(tmp);
						map.remove(j);
					}
				}
			}
			System.out.println(mapn);
			if(order.equals("asc"))
			{
				this.sortedlist = mapn;
				this.sort = 1;
			}
			else if(order.equals("desc"))
			{
				this.sortedlist = mapn;
				Collections.reverse(this.sortedlist);
				this.sort = 2;
			}
			else
			{
				this.sortedlist = accountModel.getitems();
				this.sort = 0;
			}
	}
	public void verifyuseraccount()
	{
		System.out.println(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("user"));
		//verify this user
		String encryptedString = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("user");
		if(encryptedString.contains(" "))
		{
			System.out.println("hi");
			encryptedString = encryptedString.replace(" ", "+");
			System.out.println(encryptedString);
		}
		System.out.println(encryptedString);

		String key = "Bar12345Bar12345";
		Key aesKey = new SecretKeySpec(key.getBytes(), "AES");
		
		try{
			Cipher cipher = Cipher.getInstance("AES");
			Base64.Decoder decoder = Base64.getDecoder();
			cipher.init(Cipher.DECRYPT_MODE, aesKey);
			String decrypted = new String(cipher.doFinal(decoder.decode(encryptedString)));
			System.out.println(decrypted);
			AccountModel accountModel = new AccountModel();
			int status = accountModel.verifyuseraccount(decrypted);
			if(status == 1)
			{
				//account verified 
				this.errorMessage = "Account verified! login now";
				redirect("index.xhtml");
			}
			else if(status == 2)
			{
				this.errorMessage = "Account already verified!";
				redirect("index.xhtml");
			}
		}catch(Exception e)
		{}
			
	}
	public void redirectto()
	{
		redirect("items.xhtml");
	}
	public void clearerrors()
	{
		this.errorMessage = "";
	}
	public void checkloginstatus()
	{
		if(this.account.getUsername() == null || this.account.getUsername().equals(""))
		{
			this.loggedin = false;
		}
		else
		{
			this.loggedin = true;
		}
	}
	public void reset()
	{
		this.errorMessage2 = "";
		redirect("forgot.xhtml");
	}
	public void resetpass()
	{
		System.out.println(this.resetuser);
		String email;
		if(this.resetuser.equals("") || this.resetuser == null)
		{
			this.errorMessage2 = "Username is Required!";
			return;
		}
		AccountModel accountModel = new AccountModel();
		email = accountModel.resetpass(this.resetuser);
		if(email == "")
		{
			this.errorMessage2 = "Invalid Username!";
			return;
		}
		else
		{
			//send password reset link to email
			String msg = "Hi " + this.resetuser + "!\n\n" + "Please click the below link to reset your password : \n";
			HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
			String url = request.getRequestURL().toString() ;
			url = url.substring(0, url.length() - 12)  + "reset.xhtml";
			String username = this.resetuser;
			//hash username
			try{
			String text = username;
			String key = "Bar12345Bar12345";
			Key aesKey = new SecretKeySpec(key.getBytes(), "AES");
			Cipher cipher = Cipher.getInstance("AES");
			cipher.init(Cipher.ENCRYPT_MODE, aesKey);
			byte[] encrypted = cipher.doFinal(text.getBytes());
			Base64.Encoder encoder = Base64.getEncoder();
			String encryptedString = encoder.encodeToString(encrypted);
			System.out.println(encryptedString);
			username = encryptedString;
			}catch(Exception e)
			{
				System.out.println(e);
			}
			
			
			url = url + "?user=" + username;
			System.out.println(url);
			msg = msg + url;
			String from = "test@gmail.com";
			 String password = "test@123";
			 String to = email;
			 String sub = "Password Reset";
			Properties props = new Properties();    
	         props.put("mail.smtp.host", "smtp.gmail.com");    
	         props.put("mail.smtp.socketFactory.port", "465");    
	         props.put("mail.smtp.socketFactory.class",    
	                   "javax.net.ssl.SSLSocketFactory");    
	         props.put("mail.smtp.auth", "true");   
	         props.put("mail.smtp.port", "465");    
	         //get Session   
	         Session session = Session.getDefaultInstance(props,    
	          new javax.mail.Authenticator() {    
	          protected PasswordAuthentication getPasswordAuthentication() {    
	          return new PasswordAuthentication(from,password);  
	          }    
	         });    
	         //compose message    
	         try {    
	          MimeMessage message = new MimeMessage(session);    
	          message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));    
	          message.setSubject(sub);    
	          message.setText(msg);    
	          //send message  
	          Transport.send(message);    
	          System.out.println("message sent successfully");    
	         } catch (MessagingException e) {throw new RuntimeException(e);} 
			
			
			
			
			this.errorMessage2 = "";
			this.errorMessage3 = "Password Reset Link sent to registered email!";
			
		}
	}
	public void verifyuseraccount2()
	{
		System.out.println(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("user"));

		//verify this user
		String encryptedString = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("user");
		if(encryptedString == null)
		{
			redirect("index.xhtml");
		}
		if(encryptedString.contains(" "))
		{
			System.out.println("hi");
			encryptedString = encryptedString.replace(" ", "+");
			System.out.println(encryptedString);
		}
		System.out.println(encryptedString);

		String key = "Bar12345Bar12345";
		Key aesKey = new SecretKeySpec(key.getBytes(), "AES");
		
		try{
			Cipher cipher = Cipher.getInstance("AES");
			Base64.Decoder decoder = Base64.getDecoder();
			cipher.init(Cipher.DECRYPT_MODE, aesKey);
			String decrypted = new String(cipher.doFinal(decoder.decode(encryptedString)));
			System.out.println(decrypted);
			this.resetuser = decrypted;
			
		}catch(Exception e)
		{
			this.errorMessage = "Some Error occured!";
			redirect("index.xhtml");
		}
			
	}
	public void resetfinalpass()
	{
		AccountModel accountModel = new AccountModel();
		accountModel.resetfinalpass(this.resetuser,this.newpass);
		this.errorMessage = "Password reset sucessful!";
		redirect("index.xhtml");
	}
	
}


