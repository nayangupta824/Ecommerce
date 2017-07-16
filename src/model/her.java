package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.sql.*;
public class her {
	private String pass = "9bd9c0aa3476e985c6f0da52e0d7febc5ad34ed3704cc59cd68ef7b9e72fde3e";
	
	public boolean login(String username, String password)
	{
		//return username.equalsIgnoreCase("abc") && password.equalsIgnoreCase("123");
		
		
		//new code below
		String tmp;
		String query = "select password from users where name = '";
		query += username;
		query += "'";
		System.out.println(query);
		boolean flag = false;
		try{  
			Class.forName("org.postgresql.Driver");     
			Connection con = DriverManager.getConnection( "jdbc:postgresql://ec2-23-21-220-48.compute-1.amazonaws.com:5432/dd8bbekqc17qsj","ocpmrzqczmffnr",pass);  
			PreparedStatement stmt = con.prepareStatement(query);  
			ResultSet rs = stmt.executeQuery();
			System.out.println(rs);
			while(rs.next())
			{
				tmp = rs.getString(1);
				System.out.println(tmp);
				if(tmp.equals(password))
				{
					flag = true;
					break;
				}
			}
			con.close();
			
		}
		catch(Exception e)
		{  
			System.out.println(e);  
		}
		return flag;
	}
	public void signup(String username,String password,String email,String mobile)
	{
		String query = "insert into users(name,password,email,mobile) values('";
		query += username+"','"+password+"','"+email+"','" +mobile +"')";
		System.out.println(query);
		try{  
			Class.forName("org.postgresql.Driver");     
			Connection con = DriverManager.getConnection( "jdbc:postgresql://ec2-23-21-220-48.compute-1.amazonaws.com:5432/dd8bbekqc17qsj","ocpmrzqczmffnr",pass);  
			PreparedStatement stmt = con.prepareStatement(query);
			stmt.executeUpdate();
			con.close();
		}catch(Exception e)
		{
			System.out.println(e);
		}
	}
	public boolean checkusernameredundancy(String username)
	{
		String tmp;
		String query = "select name from users where name = '";
		query += username;
		query += "'";
		System.out.println(query);
		boolean flag = false;
		try{  
			Class.forName("org.postgresql.Driver");     
			Connection con = DriverManager.getConnection( "jdbc:postgresql://ec2-23-21-220-48.compute-1.amazonaws.com:5432/dd8bbekqc17qsj","ocpmrzqczmffnr",pass);  
			PreparedStatement stmt = con.prepareStatement(query);  
			ResultSet rs = stmt.executeQuery();
			System.out.println(rs);
			while(rs.next())
			{
				tmp = rs.getString(1);
				System.out.println(tmp);
				if(tmp.equals(username))
				{
					flag = true;
					break;
				}
			}
			con.close();
			
		}
		catch(Exception e)
		{  
			System.out.println(e);  
		}
		System.out.println(flag);
		return flag;
	}
	public boolean checkemailalreadyused(String email)
	{
		String tmp;
		String query = "select email from users where email = '";
		query += email;
		query += "'";
		System.out.println(query);
		boolean flag = false;
		try{  
			Class.forName("org.postgresql.Driver");     
			Connection con = DriverManager.getConnection( "jdbc:postgresql://ec2-23-21-220-48.compute-1.amazonaws.com:5432/dd8bbekqc17qsj","ocpmrzqczmffnr",pass);  
			PreparedStatement stmt = con.prepareStatement(query);  
			ResultSet rs = stmt.executeQuery();
			System.out.println(rs);
			while(rs.next())
			{
				tmp = rs.getString(1);
				System.out.println(tmp);
				if(tmp.equals(email))
				{
					flag = true;
					break;
				}
			}
			con.close();
			
		}
		catch(Exception e)
		{  
			System.out.println(e);  
		}
		System.out.println(flag);
		return flag;
	}
	
	public ArrayList<ArrayList<String>> getitems()
	{
		
		
		String tmp,id;
		String query = "select * from items";
		System.out.println(query);
		ArrayList<ArrayList<String>> outer = new ArrayList<ArrayList<String>>();
		try{  
			Class.forName("org.postgresql.Driver");     
			Connection con = DriverManager.getConnection( "jdbc:postgresql://ec2-23-21-220-48.compute-1.amazonaws.com:5432/dd8bbekqc17qsj","ocpmrzqczmffnr",pass);  
			PreparedStatement stmt = con.prepareStatement(query);  
			ResultSet rs = stmt.executeQuery();
			System.out.println(rs);
			
			ResultSetMetaData rsmd = rs.getMetaData();
			int columnsNumber = rsmd.getColumnCount();
			while (rs.next()) {
				ArrayList<String> inner = new ArrayList<String>(); 
			    for(int i=1; i<=columnsNumber; i++){
			       inner.add(rs.getString(i));
			    }    
			    outer.add(inner);               
			}
			con.close();
		}
		catch(Exception e)
		{  
			System.out.println(e);  
		}
		
		return outer;
	}
	
	public int updateusercart(String username , String itemname , String quantity)
	{
		if(username.equals(null))
		{
			return 3;
		}
		int quan = Integer.parseInt(quantity);
		int quantityavailable = 0;
		int returncode = 0;
		int itemid = 0;
		//check whether the item exists in database ... what if user send a invalid name for it.
		String tmp;
		String query = "select * from items where name = '";
		query += itemname;
		query += "'";
		System.out.println(query);
		boolean flag = false;
		try{  
			Class.forName("org.postgresql.Driver");     
			Connection con = DriverManager.getConnection( "jdbc:postgresql://ec2-23-21-220-48.compute-1.amazonaws.com:5432/dd8bbekqc17qsj","ocpmrzqczmffnr",pass);  
			PreparedStatement stmt = con.prepareStatement(query);  
			ResultSet rs = stmt.executeQuery();
			System.out.println(rs);
			while(rs.next())
			{
				tmp = rs.getString(2);
				System.out.println(tmp);
				if(tmp.equals(itemname))
				{
					flag = true;
					System.out.println("Item exists!");
					//check if quantity demanded is available
					tmp = rs.getString(4);
					quantityavailable = Integer.parseInt(tmp);
					if(quantityavailable < quan)
					{
						//not available
						returncode = 1;
					}
					else
					{
						//available
						returncode = 2;
						//get the itemid
						tmp = rs.getString(1);
						itemid = Integer.parseInt(tmp);
					}
					break;
				}
				
			}
			//if error code = 2 update the user cart and delete the quantity from database
			if(returncode == 2)
			{
				System.out.println("Updating user cart with item ID : " + itemid + "...");
				//check if itemId already exists in cart ... if yes then only update its quantity, else add to cart table a new entry
				query = "select * from cart where itemID = " + itemid + " and name = '" + username + "'";
				System.out.println(query);
				boolean flag1 = false;
				int currquan;
				String tmp1 = "";
				try{  
					Class.forName("com.mysql.jdbc.Driver");     
					PreparedStatement stmt1 = con.prepareStatement(query);  
					ResultSet rs1 = stmt1.executeQuery();
					System.out.println(rs1);
					while(rs1.next())
					{
						flag1 = true;
						tmp1 = rs1.getString(3);
					}
				}
				catch(Exception e)
				{  
					System.out.println(e);  
				}
				if(flag1)
				{
					//execute update
					currquan = Integer.parseInt(tmp1);
					query = "update cart set quantity = '" + (currquan+quan) + "' where itemID = " + itemid;
					System.out.println(query);
					try{
						PreparedStatement stmt1 = con.prepareStatement(query);  
						stmt1.executeUpdate();
						System.out.println("Quantity added to already existing one ....");
					}catch(Exception e)
					{
						System.out.println(e); 
					}
				}
				else
				{
					//execute insert
					query = "insert into cart values('" + username + "','" + itemid + "','" + quan + "')";
					System.out.println(query);
					try{
						PreparedStatement stmt1 = con.prepareStatement(query);  
						stmt1.executeUpdate();
						System.out.println("Added to cart...");
					}catch(Exception e)
					{
						System.out.println(e); 
					}
					//now delete the quantity from database
					int leftquantity = quantityavailable-quan;
					query = "update items set quantity = '" + leftquantity + "' where itemID = " + itemid;
					System.out.println(query);
					try{
						PreparedStatement stmt1 = con.prepareStatement(query);  
						stmt1.executeUpdate();
						System.out.println("Quantity removed from DB....");
					}catch(Exception e)
					{
						System.out.println(e); 
					}
				}
				
				
				
			}
			con.close();
			
		}
		catch(Exception e)
		{  
			System.out.println(e);  
		}
		/*ERROR CODES TO RETURN
		 *  0 --> "item not found in database"
		 *  1 --> "demanded quantity not available"
		 *  2 --> "demanded quantity is available"
		 *  3 --> "user is not valid"
		 */
		
		
		return returncode;
		
	}
	
	public ArrayList<ArrayList<Integer>> loadcart(String username)
	{
		String tmp,id;
		String query = "select * from cart where name = '" + username + "'";
		System.out.println(query);
		
		
		
		ArrayList<ArrayList<Integer>> outer = new ArrayList<ArrayList<Integer>>();
		try{  
			Class.forName("org.postgresql.Driver");     
			Connection con = DriverManager.getConnection( "jdbc:postgresql://ec2-23-21-220-48.compute-1.amazonaws.com:5432/dd8bbekqc17qsj","ocpmrzqczmffnr",pass);  
			PreparedStatement stmt = con.prepareStatement(query);  
			ResultSet rs = stmt.executeQuery();
			System.out.println(rs);
			
			ResultSetMetaData rsmd = rs.getMetaData();
			int columnsNumber = rsmd.getColumnCount();
			while (rs.next()) {
				ArrayList<Integer> inner = new ArrayList<Integer>(); 
			    for(int i=2; i<=columnsNumber; i++){
			       inner.add(Integer.parseInt(rs.getString(i)));
			    }    
			    outer.add(inner);               
			}	
			con.close();
		}
		catch(Exception e)
		{  
			System.out.println(e);  
		}
		//outer contains the list<itemid,quantity> of the user
		return outer;
	}
	
	public String[] loadimages()
	{
		String[] imgarr = new String[1000];
		
		String tmp;
		String query = "select * from items";
		System.out.println(query);
		int i;
		try{  
			Class.forName("org.postgresql.Driver");     
			Connection con = DriverManager.getConnection( "jdbc:postgresql://ec2-23-21-220-48.compute-1.amazonaws.com:5432/dd8bbekqc17qsj","ocpmrzqczmffnr",pass);  
			PreparedStatement stmt = con.prepareStatement(query);  
			ResultSet rs = stmt.executeQuery();
			System.out.println(rs);
			i = 0;
			while(rs.next())
			{
				tmp = rs.getString(5);
				System.out.println(tmp);
				imgarr[i] = tmp;
				i++;
			}
			
			con.close();
		}
		catch(Exception e)
		{  
			System.out.println(e);  
		}
		
		return imgarr;
	}
	public String[] loaditemname()
	{
		String[] itemnamearr = new String[1000];
		
		String tmp;
		String query = "select * from items";
		System.out.println(query);
		int i;
		try{  
			Class.forName("org.postgresql.Driver");     
			Connection con = DriverManager.getConnection( "jdbc:postgresql://ec2-23-21-220-48.compute-1.amazonaws.com:5432/dd8bbekqc17qsj","ocpmrzqczmffnr",pass);  
			PreparedStatement stmt = con.prepareStatement(query);  
			ResultSet rs = stmt.executeQuery();
			System.out.println(rs);
			i = 0;
			while(rs.next())
			{
				tmp = rs.getString(2);
				System.out.println(tmp);
				itemnamearr[i] = tmp;
				i++;
			}
			
			con.close();
		}
		catch(Exception e)
		{  
			System.out.println(e);  
		}
		return itemnamearr;
	}
	public String[] loadprice()
	{
		String[] pricearr = new String[1000];
		
		String tmp;
		String query = "select * from items";
		System.out.println(query);
		int i;
		try{  
			Class.forName("org.postgresql.Driver");     
			Connection con = DriverManager.getConnection( "jdbc:postgresql://ec2-23-21-220-48.compute-1.amazonaws.com:5432/dd8bbekqc17qsj","ocpmrzqczmffnr",pass);  
			PreparedStatement stmt = con.prepareStatement(query);  
			ResultSet rs = stmt.executeQuery();
			System.out.println(rs);
			i = 0;
			while(rs.next())
			{
				tmp = rs.getString(6);
				System.out.println(tmp);
				pricearr[i] = tmp;
				i++;
			}
			
			con.close();
		}
		catch(Exception e)
		{  
			System.out.println(e);  
		}
		return pricearr;
	}
	public int calculatetotal(String username,String pricearr[])
	{
		int total = 0;
		String tmp;
		String query = "select * from cart where name = '" + username + "'";
		System.out.println(query);
		int q;
		try{  
			Class.forName("org.postgresql.Driver");     
			Connection con = DriverManager.getConnection( "jdbc:postgresql://ec2-23-21-220-48.compute-1.amazonaws.com:5432/dd8bbekqc17qsj","ocpmrzqczmffnr",pass);  
			PreparedStatement stmt = con.prepareStatement(query);  
			ResultSet rs = stmt.executeQuery();
			System.out.println(rs);
			while(rs.next())
			{
				tmp = rs.getString(3);
				q = Integer.parseInt(tmp);
				System.out.println(tmp);
				total += (q*Integer.parseInt(pricearr[Integer.parseInt(rs.getString(2))-1]));
			}
			
			con.close();
		}
		catch(Exception e)
		{  
			System.out.println(e);  
		}
		return total;
	}
	public void remove(String username,int itemid,int quantitytoremove,int currentquan ,boolean rem)
	{
		
		//first remove from user cart
		if(rem)
		{
			//remove entire item from cart ... delete query
			String query = "delete from cart where name = '" + username + "' and itemID = '"+itemid+"'" ;
			try{  
				Class.forName("com.mysql.jdbc.Driver");     
				Connection con = DriverManager.getConnection( "jdbc:mysql://localhost:3306/Emp","root",pass);  
				PreparedStatement stmt = con.prepareStatement(query);  
				stmt.executeUpdate();
				System.out.println("Cart item deleted fully ....");
				con.close();
				
			}
			catch(Exception e)
			{  
				System.out.println(e);  
			}
		}
		else
		{
			//remove partial quantity ... update query
			String query = "update cart set quantity = '" + (currentquan-quantitytoremove) + "' where name = '" + username + "' and itemID = '" + itemid + "'"; 
			try{  
				Class.forName("com.mysql.jdbc.Driver");     
				Connection con = DriverManager.getConnection( "jdbc:mysql://localhost:3306/Emp","root",pass);  
				PreparedStatement stmt = con.prepareStatement(query);  
				stmt.executeUpdate();
				System.out.println("Cart updated! ....");
				con.close();
			}
			catch(Exception e)
			{  
				System.out.println(e);  
			}
		}
		
		
		//add to database the removed quantity
		String query = "select * from items where itemID = '" + itemid +'"';
		System.out.println(query);
		//fetch current quantity in db
		int currquan = 0;
		try{  
			Class.forName("org.postgresql.Driver");     
			Connection con = DriverManager.getConnection( "jdbc:postgresql://ec2-23-21-220-48.compute-1.amazonaws.com:5432/dd8bbekqc17qsj","ocpmrzqczmffnr",pass);  
			PreparedStatement stmt = con.prepareStatement(query);  
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next())
			{
				currquan = Integer.parseInt(rs.getString(4));
			}
			System.out.println("current quantity fetched!....");
			con.close();
		}
		catch(Exception e)
		{  
			System.out.println(e);  
		}
		query = "update items set quantity = '" + (currquan+quantitytoremove) + "' where itemID = '" + itemid + "'";
		try{  
			Class.forName("com.mysql.jdbc.Driver");     
			Connection con1 = DriverManager.getConnection( "jdbc:mysql://localhost:3306/Emp","root",pass);  
			PreparedStatement stmt1 = con1.prepareStatement(query);  
			stmt1.executeUpdate();
			System.out.println("DB updated! ....");
			con1.close();
		}
		catch(Exception e)
		{  
			System.out.println(e);  
		}
	}
	
	public String getEmail(String username)
	{
		String tmp = "";
		String query = "select email from user where name = '";
		query += username;
		query += "'";
		System.out.println(query);
		try{  
			Class.forName("org.postgresql.Driver");     
			Connection con = DriverManager.getConnection( "jdbc:postgresql://ec2-23-21-220-48.compute-1.amazonaws.com:5432/dd8bbekqc17qsj","ocpmrzqczmffnr",pass);  
			PreparedStatement stmt = con.prepareStatement(query);  
			ResultSet rs = stmt.executeQuery();
			System.out.println(rs);
			while(rs.next())
			{
				tmp = rs.getString(1);
				System.out.println(tmp);
			}
			con.close();
			
		}
		catch(Exception e)
		{  
			System.out.println(e);  
		}
		return tmp;
	}
	public ArrayList<String> getStates()
	{
		ArrayList<String> states = new ArrayList<String>();
		String tmp = "";
		String query = "select distinct city_state from cities order by city_state asc";
		System.out.println(query);
		try{  
			Class.forName("org.postgresql.Driver");     
			Connection con = DriverManager.getConnection( "jdbc:postgresql://ec2-23-21-220-48.compute-1.amazonaws.com:5432/dd8bbekqc17qsj","ocpmrzqczmffnr",pass);  
			PreparedStatement stmt = con.prepareStatement(query);  
			ResultSet rs = stmt.executeQuery();
			System.out.println(rs);
			while(rs.next())
			{
				tmp = rs.getString(1);
				states.add(tmp);
			}
			con.close();
			
		}
		catch(Exception e)
		{  
			System.out.println(e);  
		}
		return states;
	}
	
	public ArrayList<String> getCities(String state)
	{
		ArrayList<String> cities = new ArrayList<String>();
		if(state == null)
			return null;
		String tmp = "";
		String query = "select city_name from cities where city_state = '" + state + "' order by city_name asc";
		System.out.println(query);
		try{  
			Class.forName("org.postgresql.Driver");     
			Connection con = DriverManager.getConnection( "jdbc:postgresql://ec2-23-21-220-48.compute-1.amazonaws.com:5432/dd8bbekqc17qsj","ocpmrzqczmffnr",pass);  
			PreparedStatement stmt = con.prepareStatement(query);  
			ResultSet rs = stmt.executeQuery();
			System.out.println(rs);
			while(rs.next())
			{
				tmp = rs.getString(1);
				cities.add(tmp);
			}
			con.close();
			
		}
		catch(Exception e)
		{  
			System.out.println(e);  
		}
		return cities;
	}
	
	public boolean carttoorders(String username,ArrayList<ArrayList<Integer>> cart,double carttotal)
	{
		//add the cart table to orders table
		
		
		//first copy to orders table
		String query = "insert into orders(name,itemID,quantity) select * from cart where name = '" + username + "'";
		try{  
			Class.forName("org.postgresql.Driver");     
			Connection con = DriverManager.getConnection( "jdbc:postgresql://ec2-23-21-220-48.compute-1.amazonaws.com:5432/dd8bbekqc17qsj","ocpmrzqczmffnr",pass);  
			PreparedStatement stmt = con.prepareStatement(query);  
			stmt.executeUpdate();
			System.out.println("Orders updated! ....");
			con.close();
		}
		catch(Exception e)
		{  
			System.out.println(e);  
		}
		
		//now delete it from cart table
		query = "delete from cart where name = '" + username + "'";
		try{  
			Class.forName("org.postgresql.Driver");     
			Connection con = DriverManager.getConnection( "jdbc:postgresql://ec2-23-21-220-48.compute-1.amazonaws.com:5432/dd8bbekqc17qsj","ocpmrzqczmffnr",pass);  
			PreparedStatement stmt = con.prepareStatement(query);  
			stmt.executeUpdate();
			System.out.println("Cart deleted! ....");
			con.close();
		}
		catch(Exception e)
		{  
			System.out.println(e);  
		}
		
		//now the order_details table is updated
		Double tot = carttotal;
		query = "insert into order_details(name,totalprice,status) values('" + username + "','" + tot.intValue() + "','Under Process')";
		try{  
			Class.forName("org.postgresql.Driver");     
			Connection con = DriverManager.getConnection( "jdbc:postgresql://ec2-23-21-220-48.compute-1.amazonaws.com:5432/dd8bbekqc17qsj","ocpmrzqczmffnr",pass);  
			PreparedStatement stmt = con.prepareStatement(query);  
			stmt.executeUpdate();
			System.out.println("Order details updated! ....");
			con.close();
		}
		catch(Exception e)
		{  
			System.out.println(e);  
		}
		return true;
	}
	
	public int calculateorderstotal(String username)
	{
		//new code below
				String tmp;
				int total = 0;
				String query = "select totalprice from order_details where name = '";
				query += username;
				query += "'";
				System.out.println(query);
				boolean flag = false;
				try{  
					Class.forName("com.mysql.jdbc.Driver");     
					Connection con = DriverManager.getConnection( "jdbc:mysql://localhost:3306/Emp","root",pass);  
					PreparedStatement stmt = con.prepareStatement(query);  
					ResultSet rs = stmt.executeQuery();
					System.out.println(rs);
					while(rs.next())
					{
						tmp = rs.getString(1);
						total += Integer.parseInt(tmp);
						System.out.println(tmp);
					}
					con.close();
					
				}
				catch(Exception e)
				{  
					System.out.println(e);  
				}
				System.out.println("Order total = " + total);
				return total;
	}
	public ArrayList<ArrayList<Integer>> loadorders(String username)
	{
		String query = "select * from orders where name = '" + username + "'";
		System.out.println(query);
		
		
		
		ArrayList<ArrayList<Integer>> outer = new ArrayList<ArrayList<Integer>>();
		try{  
			Class.forName("org.postgresql.Driver");     
			Connection con = DriverManager.getConnection( "jdbc:postgresql://ec2-23-21-220-48.compute-1.amazonaws.com:5432/dd8bbekqc17qsj","ocpmrzqczmffnr",pass);  
			PreparedStatement stmt = con.prepareStatement(query);  
			ResultSet rs = stmt.executeQuery();
			System.out.println(rs);
			
			ResultSetMetaData rsmd = rs.getMetaData();
			int columnsNumber = rsmd.getColumnCount();
			while (rs.next()) {
				ArrayList<Integer> inner = new ArrayList<Integer>(); 
			    for(int i=2; i<=columnsNumber; i++){
			       inner.add(Integer.parseInt(rs.getString(i)));
			    }    
			    outer.add(inner);               
			}	
			con.close();
		}
		catch(Exception e)
		{  
			System.out.println(e);  
		}
		//outer contains the list<itemid,quantity> of the user
		return outer;
	}
	public String getMobile(String username)
	{
		String tmp = "";
		String query = "select mobile from user where name = '";
		query += username;
		query += "'";
		System.out.println(query);
		try{  
			Class.forName("org.postgresql.Driver");     
			Connection con = DriverManager.getConnection( "jdbc:postgresql://ec2-23-21-220-48.compute-1.amazonaws.com:5432/dd8bbekqc17qsj","ocpmrzqczmffnr",pass);  
			PreparedStatement stmt = con.prepareStatement(query);  
			ResultSet rs = stmt.executeQuery();
			System.out.println(rs);
			while(rs.next())
			{
				tmp = rs.getString(1);
				System.out.println(tmp);
			}
			con.close();
			
		}
		catch(Exception e)
		{  
			System.out.println(e);  
		}
		return tmp;
	}
}
