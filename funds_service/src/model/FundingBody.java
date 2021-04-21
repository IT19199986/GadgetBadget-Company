package model;

import java.sql.*; 

public class FundingBody {


	//A common method to connect to the DB
		 private Connection connect() 
		 { 
			 Connection con = null; 
			 
			 try
			 { 
				 Class.forName("com.mysql.jdbc.Driver"); 
		 
				 //Provide the correct details: DBServer/DBName, username, password 
				 con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/funds_service", "root", "anuwaniga#9096"); 
			 } 
			 catch (Exception e) 
			 {e.printStackTrace();} 
			 
			 return con; 
		 } 
		 
		 public String insertfundingBody(String FName, String Address, String Email, String Phone) 
		 { 
			 String output = ""; 
			 
			 try
			 { 
				 Connection con = connect(); 
				 if (con == null) 
				 {return "Error while connecting to the database for inserting."; }
				 
				 // create a prepared statement
				 String query = " insert into funding_body (`ID`,`FName`,`Address`,`Email`,`Phone`)"
						 		+ " values (?, ?, ?, ?, ?)"; 
				 
				 PreparedStatement preparedStmt = con.prepareStatement(query); 
				 
				 // binding values
				 preparedStmt.setInt(1, 0); 
				 preparedStmt.setString(2, FName); 
				 preparedStmt.setString(3, Address);
				 preparedStmt.setString(4, Email); 
				 preparedStmt.setString(5, Phone); 

				 
				 // execute the statement
				 preparedStmt.execute(); 
				 con.close(); 
				 
				 output = "Inserted successfully"; 
			  } 
			  catch (Exception e) 
			  { 
				 output = "Error while inserting the funding body."; 
				 System.err.println(e.getMessage()); 
			  } 
			 
			  return output; 
			} 
		 
			public String readFundingBody() 
			{ 
				 String output = ""; 
				 
				 try
				 { 
					 Connection con = connect();
					 
					 if (con == null) 
					 {return "Error while connecting to the database for reading."; } 
					 
					 // Prepare the html table to be displayed
					 output = "<table border='1'><tr><th>Funder's Name</th><th>Address</th>" +
							  "<th>Email</th>" + 
							  "<th>Phone Number</th>" +
							  "<th>Update</th><th>Remove</th></tr>"; 
				 
					 String query = "select * from funding_body"; 
					 Statement stmt = con.createStatement(); 
					 ResultSet rs = stmt.executeQuery(query); 
					 
					 // iterate through the rows in the result set
					 while (rs.next()) 
					 { 
						 String ID = Integer.toString(rs.getInt("ID")); 
						 String FName = rs.getString("FName"); 
						 String Address = rs.getString("Address"); 
						 String Email = rs.getString("Email"); 
						 String Phone = rs.getString("Phone"); 
						 
						 // Add into the html table
						 output += "<tr><td>" + FName + "</td>"; 
						 output += "<td>" + Address + "</td>"; 
						 output += "<td>" + Email + "</td>"; 
						 output += "<td>" + Phone + "</td>"; 
						 
						 // buttons
						 output += "<td><input name='btnUpdate' type='button' value='Update' class='btn btn-secondary'></td>"
								 	+ "<td><form method='post' action='funds.jsp'>"
								 	+ "<input name='btnRemove' type='submit' value='Remove' class='btn btn-danger'>"
								 	+ "<input name='ID' type='hidden' value='" + ID 
								 	+ "'>" + "</form></td></tr>"; 
				      } 
					 
					  con.close(); 
					  
					  // Complete the html table
					  output += "</table>"; 
				 } 
				 catch (Exception e) 
				 { 
					 output = "Error while reading the Funds."; 
					 System.err.println(e.getMessage()); 
				 } 
				 
				 return output; 
				 
			} 
			public String updateFundingBody(String ID, String FName, String address, String email, String phone)
			{ 
				 String output = ""; 
				 
				 try
				 { 
					 Connection con = connect(); 
					 
					 if (con == null) 
					 {return "Error while connecting to the database for updating."; } 
					 
					 // create a prepared statement
					 String query = "UPDATE funding_body SET FName=?,Address=?,Email=?,Phone=? WHERE ID=?"; 
					 
					 PreparedStatement preparedStmt = con.prepareStatement(query); 
					 
					 // binding values
					 preparedStmt.setString(1, FName); 
					 preparedStmt.setString(2, address); 
					 preparedStmt.setString(3, email); 
					 preparedStmt.setString(4, phone); 
					 preparedStmt.setInt(5, Integer.parseInt(ID)); 
					 
					 // execute the statement
					 preparedStmt.execute(); 
					 con.close(); 
					 
					 output = "Updated successfully"; 
				 } 
				 catch (Exception e) 
				 { 
					 output = "Error while updating the funding body."; 
					 System.err.println(e.getMessage()); 
				 } 
				 
				 return output; 
			} 
			public String deleteFundingBody(String ID) 
			{ 
				 String output = ""; 
				 try
				 { 
					 Connection con = connect(); 
					 
					 if (con == null) 
					 {return "Error while connecting to the database for deleting."; } 
					 
					 // create a prepared statement
					 String query = "delete from funding_body where ID=?"; 
					 PreparedStatement preparedStmt = con.prepareStatement(query); 
					 
					 // binding values
					 preparedStmt.setInt(1, Integer.parseInt(ID)); 
					 
					 // execute the statement
					 preparedStmt.execute(); 
					 con.close(); 
					 output = "Deleted successfully"; 
				 } 
				 catch (Exception e) 
				 { 
					 output = "Error while deleting the Fundinf body"; 
					 System.err.println(e.getMessage()); 
				 } 
				 return output; 
			} 
	
}
