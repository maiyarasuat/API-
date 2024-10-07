package UpdateUser;

import static io.restassured.RestAssured.given;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class CreateUserTest {
	
	public String createRandomMail() {
		return "api"+System.currentTimeMillis()+"@email.com";
	}
	
	@Test
	public void updateUser_WithLombok() {
		RestAssured.baseURI = "https://gorest.co.in";
		
		UserLombok user = new UserLombok("naveen", createRandomMail(), "male", "active");
		
		//POST - create a new user
		Response response = given().log().all()
			.contentType(ContentType.JSON)
			.header("Authorization" , "Bearer 688153bb0768aae950a7f765cf286bf349eb73c6e6dd22b85c4330905e5888df")
			.body(user)
				.when().log().all()
					.post("/public/v2/users");
						
		response.prettyPrint();
		Object id = response.jsonPath().get("id");
		System.out.println("user id is====>" +id);
	}
	
	
	@Test
	public void updateUser_WithLombokBuilder() {
		RestAssured.baseURI = "https://gorest.co.in";
		
		UserLombok userL = new UserLombok.UserLombokBuilder()
							.name("ajith")
							.email(createRandomMail())
							.gender("male")
							.status("active")
							.build();
		
		//POST - create a new user
		Response response = given().log().all()
			.contentType(ContentType.JSON)
			.header("Authorization" , "Bearer 688153bb0768aae950a7f765cf286bf349eb73c6e6dd22b85c4330905e5888df")
			.body(userL)
				.when().log().all()
					.post("/public/v2/users");
						
		response.prettyPrint();
		Object id = response.jsonPath().get("id");
		System.out.println("user id is====>" +id);
	}
}
