package model;

import java.sql.*;

public class Pay {
	
	private Connection connect() 
	 { 
	 Connection con = null; 
	 try
	 { 
		 Class.forName("com.mysql.jdbc.Dr"); 
		 //Provide the correct details: DBServer/DBName, username, password 
		 con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/pafcus", "root", ""); 
	 } 
	 catch (Exception e) 
	 {e.printStackTrace();} 
	 return con; 
	 } 
	
	public String insertPay(String 	customer_n, String phone_no, String amount, String email, String card_type, String card_no, String exp_month, String exp_year, String cvn) 
	 { 
		String output = ""; 
		try
		{ 
			Connection con = connect(); 
			if (con == null) 
			{return "Error while connecting to the database for inserting."; } 
			
			// create a prepared statement
			String query = " insert into payment (payment_id,customer_n,phone_no,amount,email,card_type,card_no,exp_month,exp_year,cvn)" +  "values (?,?,?,?,?,?,?,?,?,?)"; 
			PreparedStatement preparedStmt = con.prepareStatement(query); 
			
			// binding values
			preparedStmt.setInt(1, 0); 
			preparedStmt.setString(2, customer_n);
			preparedStmt.setString(3, phone_no);
			preparedStmt.setString(4, amount); 
			preparedStmt.setString(5, email); 
			preparedStmt.setString(6, card_type);
			preparedStmt.setString(7, card_no); 
			preparedStmt.setString(8, exp_month); 
			preparedStmt.setString(9, exp_year); 
			preparedStmt.setString(10, cvn); 
			
			// execute the statement3
			preparedStmt.execute(); 
			con.close(); 
			output = "Inserted successfully"; 
	 } 
	 catch (Exception e) 
	 { 
		 output = "Error while inserting the payment."; 
		 System.err.println(e.getMessage()); 
	 } 
	 return output; 
	 } 
	
	public String readItems() 
	 { 
		String output = ""; 
	 try
	 { 
		 Connection con = connect(); 
		 if (con == null) 
		 {return "Error while connecting to the database for reading."; } 
		 
		 // Prepare the html table to be displayed
		 output = "<table border='1'><tr><th>Customer Name</th><th>Amount</th>" +
				 "<th>phone_no</th>"+
				 "<th>Email</th>" + 
				 "<th>Card type</th>" +
				 "<th>Card Number</th>" +
				 "<th>Expiration Month</th>" +
				 "<th>Expiration Year</th>" +
				 "<th>CVN</th>" +
				 "<th>Update</th><th>Remove</th></tr>"; 
	 
		 String query = "select * from payment"; 
		 Statement stmt = con.createStatement(); 
		 ResultSet rs = stmt.executeQuery(query); 
		 
		 // iterate through the rows in the result set
		 
	 while (rs.next()) 
	 { 
		 String payment_id = Integer.toString(rs.getInt("payment_id")); 
		 String customer_n = rs.getString("customer_n"); 
		 String phone_no = rs.getString("phone_no");
		 String amount = rs.getString("amount"); 
		 String email = rs.getString("email");
		 String card_type = rs.getString("card_type");
		 String card_no = rs.getString("card_no");
		 String exp_month = rs.getString("exp_month");
		 String exp_year = rs.getString("exp_year");
		 String cvn = rs.getString("cvn");
		 
		 // Add into the html table
		 output += "<tr><td>" + customer_n + "</td>"; 
		 output += "<td>" + phone_no + "</td>";
		 output += "<td>" + amount + "</td>"; 
		 output += "<td>" + email + "</td>"; 
		 output += "<td>" + card_type + "</td>";
		 output += "<td>" + card_no + "</td>";
		 output += "<td>" + exp_month + "</td>";
		 output += "<td>" + exp_year + "</td>";
	 	 output += "<td>" + cvn + "</td>";
	 	 
	 	 // buttons
	 output += "<td><input name='btnUpdate' type='button' value='Update'class='btn btn-secondary'></td>"
			 + "<td><form method='post' action='items.jsp'>"
			 + "<input name='btnRemove' type='submit' value='Remove'  class='btn btn-danger'>"
			 + "<input name='payment_id' type='hidden' value='" + payment_id 
			 + "'>" + "</form></td></tr>"; 
	 } 
	 con.close(); 
	 
	 // Complete the html table
	 output += "</table>"; 
	 } 
	 
	 catch (Exception e) 
	 { 
		 output = "Error while reading the items."; 
		 System.err.println(e.getMessage()); 
	 } 
	 	return output; 
	 } 
	
	
	public String updatePay(String payment_id, String customer_n, String phone_no, String amount, String email, String card_type,String 	card_no,String exp_month, String exp_year, String cvn)
	 { 
		String output = ""; 
	 try
	 { 
		 Connection con = connect(); 
		 if (con == null) 
		 {return "Error while connecting to the database for updating."; } 
		 // create a prepared statement
		 String query = "UPDATE payment SET customer_n=?,phone_no=?,amount=?,email=?,card_type=?,card_no=?,exp_month=?,exp_year=?,cvn=? WHERE payment_id=?"; 
		PreparedStatement preparedStmt = con.prepareStatement(query); 
		
		// binding values
		preparedStmt.setString(1, customer_n);
		preparedStmt.setString(2, phone_no);
		preparedStmt.setString(3, amount); 
		preparedStmt.setString(4, email);
		preparedStmt.setString(5, card_type);
		preparedStmt.setString(6, card_no);
		preparedStmt.setString(7, exp_month);
		preparedStmt.setString(8, exp_year);
		preparedStmt.setString(9, cvn);
		preparedStmt.setInt(10, Integer.parseInt(payment_id)); 
		
		// execute the statement
		preparedStmt.execute(); 
	 	con.close(); 
	 	output = "Updated successfully"; 
	 } 
	 
	 catch (Exception e) 
	 { 
		 output = "Error while updating the payment."; 
		 System.err.println(e.getMessage()); 
	 } 
	 
	 return output; 
} 
	
	public String deletePay(String payment_id) 
	 { 
		String output = ""; 
	 try
	 { 
		 Connection con = connect(); 
		 if (con == null) 
		 {return "Error while connecting to the database for deleting."; } 
		 
		 // create a prepared statement
		 String query = "delete from payment where payment_id=?"; 
		 PreparedStatement preparedStmt = con.prepareStatement(query); 
		 
		 // binding values
		 preparedStmt.setInt(1, Integer.parseInt(payment_id)); 
		 
		 // execute the statement
		 preparedStmt.execute(); 
		 con.close(); 
		 output = "Deleted successfully"; 
	 } 
	 catch (Exception e) 
	 { 
		 output = "Error while deleting the payment."; 
		 System.err.println(e.getMessage()); 
	 } 
	 return output; 
	 } 

}

