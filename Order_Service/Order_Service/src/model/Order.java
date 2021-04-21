package model;

import java.sql.*; 

public class Order 
{
	//A common method to connect to the DB
	private Connection connect() 
	{ 
		Connection con = null; 
		try
		{ 
			Class.forName("com.mysql.jdbc.Driver"); 
			//
			//com.mysql.jdbc.Driver
			//Provide the correct details: DBServer/DBName, username, password 
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/order_service", "root", "12345"); 
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		} 
			return con; 
	} 
		
	public String insertOrder(String PID, String PName, String date, String BName) 
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
			String query = " insert into orders (`OrderID`,`ProductID`,`ProductName`,`Date`,`BuyerName`)"
						 + " values (?, ?, ?, ?, ?)"; 
			PreparedStatement preparedStmt = con.prepareStatement(query); 
			
			// binding values
			preparedStmt.setInt(1, 0); 
			preparedStmt.setString(2, PID); 
			preparedStmt.setString(3, PName); 
			preparedStmt.setString(4, date); 
			preparedStmt.setString(5, BName); 
				
			// execute the statement
			preparedStmt.execute(); 
			con.close(); 
			output = "Inserted successfully"; 
		} 
		catch (Exception e) 
		{ 
			output = "Error while inserting the order."; 
			System.err.println(e.getMessage()); 
		} 
		return output; 
	}
		
	
	public String readOrder() 
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
			output = "<table border='1'><tr><th>Product ID</th><th>Product Name</th>" +
					"<th>Date</th>" + 
					"<th>Buyer Name</th>" +
					"<th>Update</th><th>Remove</th></tr>"; 
 
			String query = "select * from orders"; 
			Statement stmt = con.createStatement(); 
			ResultSet rs = stmt.executeQuery(query); 
			// iterate through the rows in the result set
			while (rs.next()) 
			{ 
				String OID = Integer.toString(rs.getInt("OrderID")); 
				String PID = rs.getString("ProductID"); 
				String PName = rs.getString("ProductName"); 
				String date = rs.getString("Date"); 
				String BName = rs.getString("BuyerName"); 
				// Add into the html table
				output += "<tr><td>" + PID + "</td>"; 
				output += "<td>" + PName + "</td>"; 
				output += "<td>" + date + "</td>"; 
				output += "<td>" + BName + "</td>"; 
				// buttons
				output += "<td><input name='btnUpdate' type='button' value='Update' class='btn btn-secondary'></td>"
								+ "<td><form method='post' action='order.jsp'>"
								+ "<input name='btnRemove' type='submit' value='Remove' class='btn btn-danger'>"
								+ "<input name='OrderID' type='hidden' value='" + OID 
								+ "'>" + "</form></td></tr>"; 
			} 
			con.close(); 
			// Complete the html table
			output += "</table>"; 
		} 
		catch (Exception e) 
		{ 
			output = "Error while reading the orders."; 
			System.err.println(e.getMessage()); 
		} 
		return output; 
	} 

	
	public String updateOrder(String OID, String PID, String PName, String date, String BName)
	{ 
		String output = ""; 
		try
		{ 
			Connection con = connect(); 
			if (con == null) 
			{
				return "Error while connecting to the database for updating."; 
			} 
			
			// create a prepared statement
			String query = "UPDATE orders SET ProductID=?,ProductName=?,Date=?,BuyerName=? WHERE OrderID=?"; 
					
			PreparedStatement preparedStmt = con.prepareStatement(query);
					
			// binding values
			preparedStmt.setString(1, PID); 
			preparedStmt.setString(2, PName); 
			preparedStmt.setString(3, date); 
			preparedStmt.setString(4, BName); 
			preparedStmt.setInt(5, Integer.parseInt(OID)); 
					
			// execute the statement
			preparedStmt.execute(); 
			con.close(); 
			output = "Updated successfully"; 
		} 
		catch (Exception e) 
		{ 
			output = "Error while updating the order."; 
			System.err.println(e.getMessage()); 
		} 
			return output; 
	}
		
	public String deleteOrder(String OID) 
	{ 
		String output = ""; 
		try
		{ 
			Connection con = connect(); 
			if (con == null) 
			{
				return "Error while connecting to the database for deleting."; 
			} 
			
			// create a prepared statement
			String query = "delete from orders where OrderID=?"; 
			PreparedStatement preparedStmt = con.prepareStatement(query); 
					
			// binding values
			preparedStmt.setInt(1, Integer.parseInt(OID)); 
			
			// execute the statement
			preparedStmt.execute(); 
			con.close(); 
			output = "Deleted successfully"; 
		} 
		catch (Exception e) 
		{ 
			output = "Error while deleting the order."; 
			System.err.println(e.getMessage()); 
		} 
			return output; 
	}
}
