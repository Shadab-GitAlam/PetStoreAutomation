package api.test;

import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.UserEndPoints;
import api.payload.User;
import groovyjarjarantlr4.v4.runtime.misc.LogManager;
import io.restassured.response.Response;

public class UserTest2 {
	Faker faker;
	User userPayload;
	public Logger logger;
	@BeforeClass
	public void setup()
	{
		
		faker=new Faker();
		userPayload=new User();
		
		 userPayload.setId(faker.idNumber().hashCode());
		 userPayload.setFirstName(faker.name().username());
		 userPayload.setLastName(faker.name().lastName());
		 userPayload.setUsername(faker.name().username());
		 userPayload.setEmail(faker.internet().emailAddress());
		 userPayload.setPassword(faker.internet().password());
		  userPayload.setPhone(faker.phoneNumber().cellPhone());
		  
		  // logs
		  
		 logger=org.apache.logging.log4j.LogManager.getLogger(this.getClass());
	}
	
	@Test(priority = 1)
	
	public void testPostUser() {
		
		logger.info("!!!!!!!!!!!!creating user");
		
		Response response=UserEndPoints.CreateUser(userPayload);
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
		
		logger.info("!!!!!!!!!!!!user created");
}
	
@Test(priority = 2)
	
	public void testGetUserByName() {
	
	logger.info("!!!!!!!!!!!!reading user info");
		
		Response response=UserEndPoints.readUser(this.userPayload.getUsername());
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
		logger.info("user info displayed");
}

@Test(priority = 3)

public void testUpdateUserByName() {
	logger.info("!!!!!!!!!!!!updating user ");
	// update data using payload
	userPayload.setFirstName(faker.name().username());
	 userPayload.setLastName(faker.name().lastName());
	 
	 userPayload.setEmail(faker.internet().emailAddress());
	
	Response response=UserEndPoints.UpdateUser(this.userPayload.getUsername(),userPayload);
	response.then().log().body();
	Assert.assertEquals(response.getStatusCode(), 200);
	//checking data after updation
	Response responseAfterUpdate=UserEndPoints.readUser(this.userPayload.getUsername());
	responseAfterUpdate.then().log().all();
	Assert.assertEquals(responseAfterUpdate.getStatusCode(), 200);
	
	logger.info("!!!!!!!!!!!!user is updated");
}
@Test(priority = 4)

public void testDeleteUserByName() {
	logger.info("!!!!!!!!!!!!delete user");
	Response response=UserEndPoints.deleteUser(this.userPayload.getUsername());
	response.then().log().body();
	Assert.assertEquals(response.getStatusCode(), 200);
	logger.info("!!!!!!!!!!!!user deleted");

}
}