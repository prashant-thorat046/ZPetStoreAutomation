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
// This is used to perform CURD Operations  
public class UserEndPoints {

	   public static Response createUser(User payload)
	   {
	    	
	    	Response response=given()
	    	    .contentType(ContentType.JSON)
	    	    .accept(ContentType.JSON)	 
	    	    .body(payload)
	    	.when()
	    	    .post(Routes.post_url);
	    	
	    	return response;
	    	
	    }
	   public static Response readUser(String userName)
	   {
	    	
	    	Response response=given()
	    	    .pathParam("username", userName)
	    	.when()
	    	    .get(Routes.get_url);
	    	
	    	return response;
	    	
	    }
	   public static Response updateUser(User payload,String userName)
	   {
	    	
	    	Response response=given()
	    	    .contentType("application/json")
	    	    .accept("application/json")	 
	    	    .pathParam("username", userName)
	    	    .body(payload)
	    	.when()
	    	    .put(Routes.update_url);	    	
	    	return response;	    	
	    }
	   public static Response deleteUser(String userName)
	   {
	    	
	    	Response response=given()
	    	    .pathParam("username", userName)
	    	.when()
	    	    .delete(Routes.delete_url);
	    	
	    	return response;
	    	
	    }
}
