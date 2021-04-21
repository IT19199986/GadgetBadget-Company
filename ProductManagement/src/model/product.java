package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class product {
	
	private Connection connect() 
	 { 
	    Connection con = null; 
	    try
	    { 
	        Class.forName("com.mysql.jdbc.Driver"); 
	 
	        //Provide the correct details: DBServer/DBName, username, password 
	        con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/paf", "root", ""); 
	   } 
	    
	   catch (Exception e) 
	   {
		   e.printStackTrace();
	   } 
	 return con; 
	 } 

	public String insertproduct(String product_ID,String product_Name,String Category,String Serial_No,String Price,String Description) 
	{ 
	   String output = ""; 
	   try
	   { 
	      Connection con = connect(); 
	      if (con == null)
	      {
	    	  return "Error while connecting to the database for inserting.";
	      } 
	       // create a prepared statement
	      String query = " insert into product(`product_ID`,`product_Name`,`Category`,`Serial_No`,`Price`,`Description`)"

	       + " values (?, ?, ?, ?, ?, ?)"; 
	      PreparedStatement preparedStmt = con.prepareStatement(query); 
	      // binding values
	   
	      preparedStmt.setString(1, product_ID);
	      preparedStmt.setString(2, product_Name); 
	      preparedStmt.setString(3, Category); 
	      preparedStmt.setString(4, Serial_No); 
	      preparedStmt.setDouble(5, Double.parseDouble(Price)); 
	      preparedStmt.setString(6, Description); 
	      // execute the statement3
	      preparedStmt.execute(); 
	      con.close(); 
	      output = "Inserted successfully"; 
	   } 
	   
	   catch (Exception e)
	   { 
	    output = "Error while inserting the item."; 
	    System.err.println(e.getMessage()); 
	   } 
	   
	 return output; 
	 }
	
	public String readproduct()
	{ 
	    String output = ""; 
	    try 
	    { 
	     Connection con = connect(); 
	     if (con == null)
	     {
	    	 return "Error while connecting to the database for reading."; 
	     } 
	     // Prepare the html table to be displayed
	     output = "<table border='1'><tr><th>Product ID</th><th>Product Name</th><th>Category</th><th>Serial no</th><th>Item Price</th><th>Item Description</th><th>Update</th><th>Remove</th></tr>"; 
	     String query = "select * from Product"; 
	     Statement stmt = con.createStatement(); 
	     ResultSet rs = stmt.executeQuery(query); 
	     // iterate through the rows in the result set 
	     while (rs.next())
			{
				String product_ID = Integer.toString(rs.getInt("product_ID"));
				String product_Name=rs.getString("product_Name");
				String Category = rs.getString("Category");
				String Serial_No = rs.getString("Serial_No");
				String Price = Double.toString(rs.getDouble("Price")); 
				String Description = rs.getString("Description");
				


				// Add into the html table

				output += "<tr><td>" + product_ID + "</td>";
				output += "<td>" +product_Name+ "</td>";
				output += "<td>" + Category+ "</td>";
				output += "<td>" + Serial_No + "</td>";
				output += "<td>" + Price + "</td>";
				output += "<td>" + Description + "</td>";


				// buttons
				output += "<td><input name=\"btnUpdate\" type=\"button\"value=\"Update\" class=\"btn btn-secondary\"></td>"+ "<td><form method=\"post\" action=\"appoinment.jsp\">"+ "<input name=\"btnRemove\" type=\"submit\" value=\"Remove\"class=\"btn btn-danger\">"+ "<input name=\"product_ID\" type=\"hidden\" value=\"" + product_ID+ "\">" + "</form></td></tr>";

			}
	   con.close(); 
	      // Complete the html table
	      output += "</table>"; 
	 } 
	    
	  catch (Exception e)  { 
	     output = "Error while reading the items."; 
	     System.err.println(e.getMessage()); 
	 } 
	 return output; 
  } 
	
	public String updateproduct(String product_ID,String product_Name,String Category,String Serial_No,String Price,String Description){ 
	     
		String output = ""; 
	    try
	    { 
	         Connection con = connect(); 
	         if (con == null)
	         {
	        	 return "Error while connecting to the database for updating.";
	         } 
	         // create a prepared statement
	         String query = "UPDATE product SET product_ID=?,product_Name=?,Category=?,Serial_No=?,Price=?,Description=?  WHERE product_ID=?"; 
	         PreparedStatement preparedStmt = con.prepareStatement(query); 
	         // binding values
             preparedStmt.setString(1, product_ID); 
		     preparedStmt.setString(1, product_Name); 
		     preparedStmt.setString(2, Category); 
		     preparedStmt.setString(3, Serial_No); 
		     preparedStmt.setDouble(4, Double.parseDouble(Price)); 
		     preparedStmt.setString(5, Description); 
		     preparedStmt.setInt(6, Integer.parseInt(product_ID));
		     
	         // execute the statement
	         preparedStmt.execute(); 
	         con.close(); 
	         output = "Updated successfully"; 
	    } 
	    
	    catch (Exception e) 
	    { 
	        output = "Error while updating the products."; 
	        System.err.println(e.getMessage()); 
	    } 
	 return output; 
	}
	
	public String deleteproduct(String product_ID) { 
		
	      String output = ""; 
	      try
	      { 
	          Connection con = connect(); 
	          if (con == null) 
	          {
	    	         return "Error while connecting to the database for deleting."; 
	    	  } 
	          // create a prepared statement
	          String query = "delete from items where product_ID=?"; 
	          PreparedStatement preparedStmt = con.prepareStatement(query); 
	          // binding values
	          preparedStmt.setString(1, product_ID); 
	          // execute the statement
	          preparedStmt.execute(); 
	          con.close(); 
	          output = "Deleted successfully"; 
	     } 
	     catch (Exception e) 
	     { 
	         output = "Error while deleting the item."; 
	         System.err.println(e.getMessage()); 
	     }
	      
	  return output; 
   } 
}
