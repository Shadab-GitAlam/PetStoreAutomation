package api.endpoints;


// for capturing all users   go to swagger; 
//swagger uri https://petstore.swagger.io/
 // create user (post)  https://petstore.swagger.io/v2/user
// get user   GET   https://petstore.swagger.io/v2/user/{username}
// update user  PUT  https://petstore.swagger.io/v2/user/{username}
// delete user Delete   https://petstore.swagger.io/v2/user/{username}

//Routes mean urls

public class Routes {

	
	// user module urls
	
	public static String base_url="https://petstore.swagger.io/v2";
	
	public static String post_url=base_url +"/user";
	public static String get_url=base_url +"/user/{username}";
	public static String update_url=base_url +"/user/{username}";
	
	public static String delete_url=base_url +"/user/{username}";
}
