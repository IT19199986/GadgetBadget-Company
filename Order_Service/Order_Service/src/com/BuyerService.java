package com;

import javax.ws.rs.Consumes;

import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.parser.Parser;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import model.Buyer;


@Path("/Buyers")
public class BuyerService 
{
Buyer buyerObj = new Buyer();
	
	@GET
	@Path("/") 
	@Produces(MediaType.TEXT_HTML) 
	public String readBuyer() 
	{ 
		return buyerObj.readBuyer(); 
	}
	
	@POST
	@Path("/") 
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED) 
	@Produces(MediaType.TEXT_PLAIN) 
	 public String insertBuyer(@FormParam("BuyerName") String name, 
	 @FormParam("Address") String address, 
	 @FormParam("Email") String email, 
	 @FormParam("Phone") String phone) 
	{ 
		String output = buyerObj.insertBuyer(name, address, email, phone); 
		return output; 
	}
	
	@PUT
	@Path("/") 
	@Consumes(MediaType.APPLICATION_JSON) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String updateBuyer(String buyerData) 
	{ 
	//Convert the input string to a JSON object 
	 JsonObject buyerObject = new JsonParser().parse(buyerData).getAsJsonObject(); 
	 
	//Read the values from the JSON object
	 String id = buyerObject.get("ID").getAsString(); 
	 String name = buyerObject.get("BuyerName").getAsString(); 
	 String address = buyerObject.get("Address").getAsString(); 
	 String email = buyerObject.get("Email").getAsString(); 
	 String phone = buyerObject.get("Phone").getAsString(); 
	 String output = buyerObj.updateBuyer(id, name, address, email, phone); 
	 return output; 
	}
	
	@DELETE
	@Path("/") 
	@Consumes(MediaType.APPLICATION_XML) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String deleteBuyer(String buyerData) 
	{ 
	//Convert the input string to an XML document
	 Document doc = Jsoup.parse(buyerData, "", Parser.xmlParser()); 
	 
	//Read the value from the element <itemID>
	 String ID = doc.select("ID").text(); 
	 String output = buyerObj.deleteBuyer(ID); 
	return output; 
	}

}
