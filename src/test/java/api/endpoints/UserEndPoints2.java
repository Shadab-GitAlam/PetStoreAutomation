package api.endpoints;

import org.json.JSONObject;
import org.testng.ITestContext;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.ResourceBundle;

import com.github.javafaker.Faker;

import api.payload.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
	
	// create end point.java
	// cretaed to perform create,read,update,delete req
	
public class UserEndPoints2 {
	
	
	// method created to get getting urs from prof file
	static ResourceBundle getURL()
	{
	 ResourceBundle routes=ResourceBundle.getBundle("routes");
	 
	 return routes;
	}	
	public static Response CreateUser(User payload)
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
	
	
	public static Response UpdateUser(String userName,User payload)
	{
		String update_url=getURL().getString("update_url");
		Response response=given()
		
		.contentType(ContentType.JSON)
		.accept(ContentType.JSON)
		.body(payload)
		.pathParam("username", userName)
		
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
