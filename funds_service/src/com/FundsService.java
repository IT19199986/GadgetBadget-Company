package com;

import model.Funds;

//For REST Service
import javax.ws.rs.*; 
import javax.ws.rs.core.MediaType;

//For JSON
import com.google.gson.*; 

//For XML
import org.jsoup.*; 
import org.jsoup.parser.*; 
import org.jsoup.nodes.Document; 


@Path("/Funds") 
public class FundsService {

	Funds fundsObj = new Funds(); 
	@GET
	@Path("/") 
	@Produces(MediaType.TEXT_HTML) 
	public String readFunds() 
	 { 
	 return fundsObj.readFunds(); 
	 } 
	
	@POST
	@Path("/") 
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String insertfunds(@FormParam("ProductId") String ProductId, 
	 @FormParam("ProductName") String ProductName, 
	 @FormParam("FName") String FName, 
	 @FormParam("Amount") String Amount) 
	{ 
	 String output = fundsObj.insertfunds(ProductId, ProductName, FName, Amount); 
	return output; 
	}
	
	@PUT
	@Path("/") 
	@Consumes(MediaType.APPLICATION_JSON) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String updateFunds(String fundData) 
	{ 
	//Convert the input string to a JSON object 
	 JsonObject fundsObject = new JsonParser().parse(fundData).getAsJsonObject(); 
	//Read the values from the JSON object
	 String fundID = fundsObject.get("FundID").getAsString(); 
	 String productId = fundsObject.get("ProductId").getAsString(); 
	 String productName = fundsObject.get("ProductName").getAsString(); 
	 String fName = fundsObject.get("FName").getAsString(); 
	 String amount = fundsObject.get("Amount").getAsString(); 
	 String output = fundsObj.updateFunds(fundID, productId, productName, fName, amount); 
	return output; 
	}
	
	@DELETE
	@Path("/") 
	@Consumes(MediaType.APPLICATION_XML) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String deleteFunds(String fundData) 
	{ 
	//Convert the input string to an XML document
	 Document doc = Jsoup.parse(fundData, "", Parser.xmlParser()); 
	 
	//Read the value from the element <itemID>
	 String FundID = doc.select("FundID").text(); 
	 String output = fundsObj.deleteFunds(FundID); 
	return output; 
	}
	
}
