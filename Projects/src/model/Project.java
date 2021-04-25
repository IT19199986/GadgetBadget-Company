package model; 
import java.sql.*; 
public class Project
{ //A common method to connect to the DB
private Connection connect() 
 { 
 Connection con = null; 
 try
 { 
 Class.forName("com.mysql.jdbc.Driver"); 
 
 //Provide the correct details: DBServer/DBName, username, password 
 con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/gadgetbadget", "root", "chamodi"); 
 } 
 catch (Exception e) 
 {e.printStackTrace();} 
 return con; 
 } 
public String insertProject(String projName, String projSubject, String projPrice, String projDesc) 
 { 
 String output = ""; 
 try
 { 
 Connection con = connect(); 
 if (con == null) 
 {return "Error while connecting to the database for inserting."; } 
 // create a prepared statement
 String query = " insert into Project(`projID`,`projName`,`projSubject`,`projPrice`,`projDesc`)"
 + " values (?, ?, ?, ?, ?)"; 
 PreparedStatement preparedStmt = con.prepareStatement(query); 
 // binding values
 preparedStmt.setInt(1, 0); 
 preparedStmt.setString(2, projName); 
 preparedStmt.setString(3, projSubject); 
 preparedStmt.setDouble(4, Double.parseDouble(projPrice)); 
 preparedStmt.setString(5, projDesc); 
// execute the statement
 preparedStmt.execute(); 
 con.close(); 
 output = "Inserted successfully"; 
 } 
 catch (Exception e) 
 { 
 output = "Error while inserting the project."; 
 System.err.println(e.getMessage()); 
 } 
 return output; 
 } 
public String readProject() 
 { 
 String output = ""; 
 try
 { 
 Connection con = connect(); 
 if (con == null) 
 {return "Error while connecting to the database for reading."; } 
 // Prepare the html table to be displayed
 output = "<table border='1'><tr><th>Project ID</th><th>Project Name</th><th>Project Subject</th>" +
 "<th>Project Price</th>" + 
 "<th>Project Description</th>" +
 "<th>Update</th><th>Remove</th></tr>"; 
 
 String query = "select * from project"; 
 Statement stmt = con.createStatement(); 
 ResultSet rs = stmt.executeQuery(query); 
 // iterate through the rows in the result set
 while (rs.next()) 
 { 
 String projID = Integer.toString(rs.getInt("projID")); 
 String projName = rs.getString("projName"); 
 String projSubject = rs.getString("projSubject"); 
 String projPrice = Double.toString(rs.getDouble("projPrice")); 
 String projDesc = rs.getString("projDesc"); 
 // Add into the html table
 output += "<tr><td>" +  projID + "</td>"; 
 output += "<td>" +  projName + "</td>"; 
 output += "<td>" + projSubject + "</td>"; 
 output += "<td>" + projPrice + "</td>"; 
 output += "<td>" + projDesc + "</td>"; 
 // buttons
 output += "<td><input name='btnUpdate' type='button' value='Update'class='btn btn-secondary'></td>"
 + "<td><form method='post' action='items.jsp'>"
 + "<input name='btnRemove' type='submit' value='Remove' class='btn btn-danger'>"
 + "<input name='projID' type='hidden' value='" + projID + "'>" + "</form></td></tr>"; 
 } 
 con.close(); 
 // Complete the html table
 output += "</table>"; 
 } 
 catch (Exception e) 
 { 
 output = "Error while reading the research."; 
 System.err.println(e.getMessage()); 
 } 
 return output; 
 } 
public String updateProject(String projID, String projName, String projSubject, String projPrice, String projDesc)
 { 
 String output = ""; 
 try
 { 
 Connection con = connect(); 
 if (con == null) 
 {return "Error while connecting to the database for updating."; } 
 // create a prepared statement
 String query = "UPDATE project SET projName=?,projSubject=?,projPrice=?,projDesc=? WHERE projID=?"; 
 PreparedStatement preparedStmt = con.prepareStatement(query); 
 // binding values
 preparedStmt.setString(1, projName); 
 preparedStmt.setString(2, projSubject); 
 preparedStmt.setDouble(3, Double.parseDouble(projPrice)); 
 preparedStmt.setString(4, projDesc); 
 preparedStmt.setInt(5, Integer.parseInt(projID)); 
 // execute the statement
 preparedStmt.execute(); 
 con.close(); 
 output = "Updated successfully"; 
 } 
 catch (Exception e) 
 { 
 output = "Error while updating the research."; 
 System.err.println(e.getMessage()); 
 } 
 return output; 
 } 
public String deleteProject(String pID) 
 { 
 String output = ""; 
 try
 { 
 Connection con = connect(); 
 if (con == null) 
 {return "Error while connecting to the database for deleting."; } 
 // create a prepared statement
 String query = "delete from project where projID=?"; 
 PreparedStatement preparedStmt = con.prepareStatement(query); 
 // binding values
 preparedStmt.setInt(1, Integer.parseInt(pID)); 
 // execute the statement
 preparedStmt.execute(); 
 con.close(); 
 output = "Deleted successfully"; 
 } 
 catch (Exception e) 
 { 
 output = "Error while deleting the research."; 
 System.err.println(e.getMessage()); 
 } 
 return output; 
 } 
}