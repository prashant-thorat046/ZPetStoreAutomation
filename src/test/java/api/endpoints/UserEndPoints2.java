package api.endpoints;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.payloads.User;
import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.ResourceBundle;
// This is used to perform CURD Operations  
public class UserEndPoints2 {
	
	   //create a Method for getting URLs from Properties file
	   public static ResourceBundle getURL()
	   {
		  ResourceBundle routes=ResourceBundle.getBundle("routes");  
		  return routes;
	   }

	   public static Response createUser(User payload)
	   {
	    	
		    String post_url=getURL().getString("post_url");
	    	Response response=given()
	    	    .contentType(ContentType.JSON)
	    	    .accept(ContentType.JSON)	 
	    	    .body(payload)
	    	.when()
	    	    .post(post_url);
	    	
	    	return response;
	    	
	    }
	   public static Response readUser(String userName)
	   {
		   String get_url=getURL().getString("get_url");
	    	Response response=given()
	    	    .pathParam("username", userName)
	    	.when()
	    	    .get(get_url);
	    	
	    	return response;
	    	
	    }
	   public static Response updateUser(User payload,String userName)
	   {
		   String update_url=getURL().getString("update_url");
	    	Response response=given()
	    	    .contentType("application/json")
	    	    .accept("application/json")	 
	    	    .pathParam("username", userName)
	    	    .body(payload)
	    	.when()
	    	    .put(update_url);	    	
	    	return response;	    	
	    }
	   public static Response deleteUser(String userName)
	   {
		    String delete_url=getURL().getString("delete_url");
	    	Response response=given()
	    	    .pathParam("username", userName)
	    	.when()
	    	    .delete(delete_url);
	    	
	    	return response;
	    	
	    }
}
