package api.test;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.UserEndPoints;
import api.endpoints.UserEndPoints2;
import api.payloads.User;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
public class UserTests {

	Faker faker;
	User userPayload;
	public Logger Log;
	@BeforeClass
	public void setUpData() {
		
	  faker=new Faker();
	  userPayload=new User();
	  
	  userPayload.setId(faker.idNumber().hashCode());
	  userPayload.setUsername(faker.name().username());
	  userPayload.setFirstName(faker.name().firstName());
	  userPayload.setLastName(faker.name().lastName());
	  userPayload.setEmail(faker.internet().safeEmailAddress());
	  userPayload.setPassword(faker.internet().password(5,10));
	  
	  //Logs
	  Log=LogManager.getLogger(this.getClass());
	  		
	}
	
	@Test(priority=1)
	public void testPostUser() {
		
		  Log.info("Creating the User");
		  Response response=UserEndPoints.createUser(userPayload);
		  response.then().log().all();
		  Assert.assertEquals(response.getStatusCode(),200);
		  Log.info("User Created");
	}
	
	@Test(priority=2)
	public void testGetUserByName() {
		
		 Log.info("Reading User info");
		Response response=UserEndPoints.readUser(this.userPayload.getUsername());
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(),200);
		 Log.info("Display User Info");
	}
	
	@Test(priority=3)
	public void testUpdateUserByName() {
		
		  Log.info("Updating User");
		  userPayload.setFirstName(faker.name().firstName());
		  userPayload.setLastName(faker.name().lastName());
		  userPayload.setEmail(faker.internet().safeEmailAddress());
		   
		  Response  response=UserEndPoints.updateUser(userPayload,this.userPayload.getUsername());
			
		  response.then().log().all();
		  Assert.assertEquals(response.getStatusCode(),200); 
		  
		  Log.info("Updated User");
		  // Checking the response after Update
		  Response responseAfterUpdate=UserEndPoints.createUser(userPayload);
		  responseAfterUpdate.then().log().all();
		  Assert.assertEquals(response.getStatusCode(),200);
		
		
	}
	
	@Test(priority=4)
	public void testDeleteUserByName() {
		
		Log.info("Deleting User");
		Response response=UserEndPoints.deleteUser(this.userPayload.getUsername());
		response.then().log().all();
		Log.info("Deleted User");
		Assert.assertEquals(response.getStatusCode(),200);
		
	}
	
}
