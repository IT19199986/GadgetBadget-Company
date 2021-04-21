package com;

import model.FundingBody;

//For REST Service
import javax.ws.rs.*; 
import javax.ws.rs.core.MediaType;

//For JSON
import com.google.gson.*; 

//For XML
import org.jsoup.*; 
import org.jsoup.parser.*; 
import org.jsoup.nodes.Document; 


@Path("/FundingBody") 

public class FundingBodyService {
	
	FundingBody fundingBObj = new FundingBody(); 
	@GET
	@Path("/") 
	@Produces(MediaType.TEXT_HTML) 
	public String readFundingBody() 
	 { 
	 return fundingBObj.readFundingBody(); 
	 } 
	
	@POST
	@Path("/") 
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String insertfundingBody(@FormParam("FName") String FName, 
	 @FormParam("Address") String Address, 
	 @FormParam("Email") String Email, 
	 @FormParam("Phone") String Phone) 
	{ 
	 String output = fundingBObj.insertfundingBody(FName, Address, Email, Phone); 
	return output; 
	}
	
	@PUT
	@Path("/") 
	@Consumes(MediaType.APPLICATION_JSON) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String updateFundingBody(String fundBData) 
	{ 
	//Convert the input string to a JSON object 
	 JsonObject fundsBObject = new JsonParser().parse(fundBData).getAsJsonObject(); 
	//Read the values from the JSON object
	 String ID = fundsBObject.get("ID").getAsString(); 
	 String Fname = fundsBObject.get("FName").getAsString(); 
	 String Address = fundsBObject.get("Address").getAsString(); 
	 String Email = fundsBObject.get("Email").getAsString(); 
	 String Phone = fundsBObject.get("Phone").getAsString(); 
	 String output = fundingBObj.updateFundingBody(ID, Fname, Address, Email, Phone); 
	return output; 
	}
	
	@DELETE
	@Path("/") 
	@Consumes(MediaType.APPLICATION_XML) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String deleteFundingBody(String fundBData) 
	{ 
	//Convert the input string to an XML document
	 Document doc = Jsoup.parse(fundBData, "", Parser.xmlParser()); 
	 
	//Read the value from the element <itemID>
	 String ID = doc.select("ID").text(); 
	 String output = fundingBObj.deleteFundingBody(ID); 
	return output; 
	}
		

}
