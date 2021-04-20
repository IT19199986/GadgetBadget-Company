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


import model.Order;

@Path("/Orders")
public class OrderService 
{
	Order orderObj = new Order();
	
	@GET
	@Path("/") 
	@Produces(MediaType.TEXT_HTML) 
	public String readOrder() 
	{ 
		return orderObj.readOrder(); 
	}
	
	@POST
	@Path("/") 
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String insertOrder(@FormParam("ProductID") String PID, 
	 @FormParam("ProductName") String PName, 
	 @FormParam("Date") String date, 
	 @FormParam("BuyerName") String BName) 
	{ 
		String output = orderObj.insertOrder(PID, PName, date, BName); 
		return output; 
	}
	
	@PUT
	@Path("/") 
	@Consumes(MediaType.APPLICATION_JSON) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String updateOrder(String orderData) 
	{ 
	//Convert the input string to a JSON object 
	 JsonObject orderObject = new JsonParser().parse(orderData).getAsJsonObject(); 
	//Read the values from the JSON object
	 String OID = orderObject.get("OrderID").getAsString(); 
	 String PID = orderObject.get("ProductID").getAsString(); 
	 String PName = orderObject.get("ProductName").getAsString(); 
	 String date = orderObject.get("Date").getAsString(); 
	 String BName = orderObject.get("BuyerName").getAsString(); 
	 String output = orderObj.updateOrder(OID, PID, PName, date, BName); 
	return output; 
	}
	
	@DELETE
	@Path("/") 
	@Consumes(MediaType.APPLICATION_XML) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String deleteOrder(String orderData) 
	{ 
	//Convert the input string to an XML document
	 Document doc = Jsoup.parse(orderData, "", Parser.xmlParser()); 
	 
	//Read the value from the element <itemID>
	 String OID = doc.select("OrderID").text(); 
	 String output = orderObj.deleteOrder(OID); 
	return output; 
	}
}
