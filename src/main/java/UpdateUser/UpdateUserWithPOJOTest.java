package UpdateUser;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static org.hamcrest.Matchers.*;

import static io.restassured.RestAssured.given;


public class UpdateUserWithPOJOTest {
	
	
	public String getRandomEmailID() {
		
		return "apiuser"+System.currentTimeMillis()+"@mail.com";
	}
	
	@Test
	public void UpdateUserWithPOJO() {
		RestAssured.baseURI = "https://gorest.co.in";
		
		User user = new User("naveen", getRandomEmailID(), "male", "active");
		
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
		
		//PUT - update a user
		user.setName("Naveen khunteta .");
		user.setStatus("inactive");
		given().log().all()
				.contentType(ContentType.JSON)
				.header("Authorization" , "Bearer 688153bb0768aae950a7f765cf286bf349eb73c6e6dd22b85c4330905e5888df")
				.body(user)
					.when().log().all()
						.put("/public/v2/users/"+id)
							.then()
								.assertThat()
									.statusCode(200)
										.and()
											.body("id", equalTo(id))
												.and()
													.body("name",equalTo(user.getName()))
														.and()
															.body("status", equalTo(user.getStatus()));
			
	}
	
}
