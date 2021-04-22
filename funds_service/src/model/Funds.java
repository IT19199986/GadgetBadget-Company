package model;

import java.sql.*; 

public class Funds {

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
		 
		 public String insertfunds(String ProductId, String ProductName, String FName, String Amount) 
		 { 
			 String output = ""; 
			 
			 try
			 { 
				 Connection con = connect(); 
				 if (con == null) 
				 {return "Error while connecting to the database for inserting."; }
				 
				 // create a prepared statement
				 String query = " insert into funds (`FundID`,`ProductId`,`ProductName`,`FName`,`Amount`)"
						 		+ " values (?, ?, ?, ?, ?)"; 
				 
				 PreparedStatement preparedStmt = con.prepareStatement(query); 
				 
				 // binding values
				 preparedStmt.setInt(1, 0); 
				 preparedStmt.setString(2, ProductId); 
				 preparedStmt.setString(3, ProductName);
				 preparedStmt.setString(4, FName); 
				 preparedStmt.setString(5, Amount);
				 //preparedStmt.setDouble(5, Double.parseDouble(Amount)); 

				 
				 // execute the statement
				 preparedStmt.execute(); 
				 con.close(); 
				 
				 output = "Inserted successfully"; 
			  } 
			  catch (Exception e) 
			  { 
				 output = "Error while inserting the funds."; 
				 System.err.println(e.getMessage()); 
			  } 
			 
			  return output; 
			} 
		 
			public String readFunds() 
			{ 
				 String output = ""; 
				 
				 try
				 { 
					 Connection con = connect();
					 
					 if (con == null) 
					 {return "Error while connecting to the database for reading."; } 
					 
					 // Prepare the html table to be displayed
					 output = "<table border='1'><tr><th>Product ID</th><th>Product Name</th>" +
							  "<th>Funder's Name</th>" + 
							  "<th>Amount</th>" +
							  "<th>Update</th><th>Remove</th></tr>"; 
				 
					 String query = "select * from funds"; 
					 Statement stmt = con.createStatement(); 
					 ResultSet rs = stmt.executeQuery(query); 
					 
					 // iterate through the rows in the result set
					 while (rs.next()) 
					 { 
						 String FundID = Integer.toString(rs.getInt("FundID")); 
						 String ProductId = rs.getString("ProductId"); 
						 String ProductName = rs.getString("ProductName"); 
						 String FName = rs.getString("FName"); 
						 String Amount = rs.getString("Amount"); 
						 
						 // Add into the html table
						 output += "<tr><td>" + ProductId + "</td>"; 
						 output += "<td>" + ProductName + "</td>"; 
						 output += "<td>" + FName + "</td>"; 
						 output += "<td>" + Amount + "</td>"; 
						 
						 // buttons
						 output += "<td><input name='btnUpdate' type='button' value='Update' class='btn btn-secondary'></td>"
								 	+ "<td><form method='post' action='funds.jsp'>"
								 	+ "<input name='btnRemove' type='submit' value='Remove' class='btn btn-danger'>"
								 	+ "<input name='FundID' type='hidden' value='" + FundID 
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
			public String updateFunds(String FID, String proID, String ProName, String FName, String amount)
			{ 
				 String output = ""; 
				 
				 try
				 { 
					 Connection con = connect(); 
					 
					 if (con == null) 
					 {return "Error while connecting to the database for updating."; } 
					 
					 // create a prepared statement
					 String query = "UPDATE funds SET ProductId=?,ProductName=?,FName=?,Amount=? WHERE FundID=?"; 
					 
					 PreparedStatement preparedStmt = con.prepareStatement(query); 
					 
					 // binding values
					 preparedStmt.setString(1, proID); 
					 preparedStmt.setString(2, ProName); 
					 preparedStmt.setString(3, FName); 
					 preparedStmt.setString(4, amount); 
					 //preparedStmt.setDouble(4, Double.parseDouble(amount)); 
					 preparedStmt.setInt(5, Integer.parseInt(FID)); 
					 
					 // execute the statement
					 preparedStmt.execute(); 
					 con.close(); 
					 
					 output = "Updated successfully"; 
				 } 
				 catch (Exception e) 
				 { 
					 output = "Error while updating the funds."; 
					 System.err.println(e.getMessage()); 
				 } 
				 
				 return output; 
			} 
			public String deleteFunds(String FundID) 
			{ 
				 String output = ""; 
				 try
				 { 
					 Connection con = connect(); 
					 
					 if (con == null) 
					 {return "Error while connecting to the database for deleting."; } 
					 
					 // create a prepared statement
					 String query = "delete from funds where FundID=?"; 
					 PreparedStatement preparedStmt = con.prepareStatement(query); 
					 
					 // binding values
					 preparedStmt.setInt(1, Integer.parseInt(FundID)); 
					 
					 // execute the statement
					 preparedStmt.execute(); 
					 con.close(); 
					 output = "Deleted successfully"; 
				 } 
				 catch (Exception e) 
				 { 
					 output = "Error while deleting the Funds."; 
					 System.err.println(e.getMessage()); 
				 } 
				 return output; 
			} 
	
}
