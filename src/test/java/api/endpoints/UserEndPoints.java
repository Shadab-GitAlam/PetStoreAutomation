package api.endpoints;

import org.json.JSONObject;
import org.testng.ITestContext;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;
import com.github.javafaker.Faker;

import api.payload.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
	
	// create end point.java
	// cretaed to perform create,read,update,delete req
	
public class UserEndPoints {
	
	
		
	public static Response CreateUser(User payload)
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
	
	
	public static Response UpdateUser(String userName,User payload)
	{
		Response response=given()
		
		.contentType(ContentType.JSON)
		.accept(ContentType.JSON)
		.body(payload)
		.pathParam("username", userName)
		
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
