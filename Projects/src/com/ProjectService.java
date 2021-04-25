
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
 public String insertProject(@FormParam("projName") String projName, 
  @FormParam("projSubject") String projSubject, 
  @FormParam("projPrice") String projPrice, 
  @FormParam("projDesc") String projDesc       ) 
 { 
  String output = projectObj.insertProject(projName, projSubject, projPrice, projDesc); 
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
  String projID = projectObject.get("projID").getAsString(); 
  String projName = projectObject.get("projName").getAsString(); 
  String projSubject = projectObject.get("projSubject").getAsString(); 
  String projPrice = projectObject.get("projPrice").getAsString(); 
  String projDesc = projectObject.get("projDesc").getAsString(); 
  String output = projectObj.updateProject(projID,projName, projSubject, projPrice, projDesc); 
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
  
 //Read the value from the element <projectID>
  String pID = doc.select("projID").text(); 
  String output = projectObj.deleteProject(pID); 
 return output; 
 }
 
}