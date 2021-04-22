package com;

//For REST Service
import javax.ws.rs.*; 
import javax.ws.rs.core.MediaType; 
//For JSON
//import com.google.gson.*; 
//For XML
import org.jsoup.*; 
import org.jsoup.parser.*;

import com.google.gson.*;

import org.jsoup.nodes.Document;

import model.product;
@Path("/product")
public class productService {
	product productObj = new product();
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readdoctor()
	{
		return productObj.readproduct();
	}
	
	@POST
	@Path("/") 
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String insertproduct(@FormParam("product_ID") String product_ID, 
	      @FormParam("product_Name") String product_Name, 
	      @FormParam("Category") String Category, 
	      @FormParam("Serial_No") String Serial_No,
	      @FormParam("Price") String Price,
	      @FormParam("Description") String Description) 
	{ 
	      String output = productObj.insertproduct(product_ID,product_Name,Category,Serial_No,Price,Description); 
	      return output; 
	}

	
	@PUT
	@Path("/") 
	@Consumes(MediaType.APPLICATION_JSON) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String updateproduct(String productData) 
	{ 
	    //Convert the input string to a JSON object 
		JsonObject productObject = new JsonParser().parse(productData).getAsJsonObject();
		
		
	    //Read the values from the JSON object
	    String product_ID = productObject.get("product_ID").getAsString(); 
	    String product_Name = productObject.get("product_Name").getAsString(); 
	    String Category = productObject.get("Category").getAsString(); 
	    String Serial_No = productObject.get("Serial_No").getAsString(); 
	    String Price = productObject.get("Price").getAsString(); 
	    String Description = productObject.get("Description").getAsString(); 
	    
	    String output = productObj.updateproduct(product_ID,product_Name,Category,Serial_No,Price,Description); 
	    return output; 
	}

	
	@DELETE
	@Path("/") 
	@Consumes(MediaType.APPLICATION_XML) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String deleteproduct(String productData) 
	{ 
	    //Convert the input string to an XML document
	    Document doc = Jsoup.parse(productData, "", Parser.xmlParser()); 
	 
	  
	    String product_ID = doc.select("product_ID").text(); 
	    String output = productObj.deleteproduct(product_ID); 
	    return output; 
	}

}
