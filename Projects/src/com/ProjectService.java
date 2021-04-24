
package com;
import model.Project; 
//For REST Service
import javax.ws.rs.*; 
import javax.ws.rs.core.MediaType; 
//For JSON
import com.google.gson.*; 
//For XML
import org.jsoup.*; 
import org.jsoup.parser.*; 
import org.jsoup.nodes.Document; 
@Path("/Project") 
public class ProjectService
{ 
	Project projectObj = new Project(); 
 @GET
 @Path("/") 
 @Produces(MediaType.TEXT_HTML) 
 public String readProject() 
  { 
  return projectObj.readProject(); 
  }
 @POST
 @Path("/") 
 @Consumes(MediaType.APPLICATION_FORM_URLENCODED) 
 @Produces(MediaType.TEXT_PLAIN) 
 public String insertProject(@FormParam("pName") String pName, 
  @FormParam("pSubject") String pSubject, 
  @FormParam("pPrice") String pPrice, 
  @FormParam("pDesc") String pDesc       ) 
 { 
  String output = projectObj.insertProject(pName, pSubject, pPrice, pDesc); 
 return output; 
 }
 @PUT
 @Path("/") 
 @Consumes(MediaType.APPLICATION_JSON) 
 @Produces(MediaType.TEXT_PLAIN) 
 public String updateProject(String projectData) 
 { 
 //Convert the input string to a JSON object 
  JsonObject projectObject = new JsonParser().parse(projectData).getAsJsonObject(); 
 //Read the values from the JSON object
  String pID = projectObject.get("pID").getAsString(); 
  String pName = projectObject.get("pName").getAsString(); 
  String pSubject = projectObject.get("pSubject").getAsString(); 
  String pPrice = projectObject.get("pPrice").getAsString(); 
  String pDesc = projectObject.get("pDesc").getAsString(); 
  String output = projectObj.updateProject(pID,pName, pSubject, pPrice, pDesc); 
 return output; 
 }
 @DELETE
 @Path("/") 
 @Consumes(MediaType.APPLICATION_XML) 
 @Produces(MediaType.TEXT_PLAIN) 
 public String deleteProject(String projectData) 
 { 
 //Convert the input string to an XML document
  Document doc = Jsoup.parse(projectData, "", Parser.xmlParser()); 
  
 //Read the value from the element <itemID>
  String pID = doc.select("pID").text(); 
  String output = projectObj.deleteProject(pID); 
 return output; 
 }
 
}