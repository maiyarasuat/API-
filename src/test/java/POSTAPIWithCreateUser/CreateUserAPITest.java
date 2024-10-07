package POSTAPIWithCreateUser;

import static io.restassured.RestAssured.given;

import java.io.File;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class CreateUserAPITest {
	
	@Test
	public void getAuthTokenTest_WithJSONFILE() {
		
		RestAssured.baseURI = "https://gorest.co.in";
		
		Integer Id = given().log().all()
				.header("Authorization" , "Bearer 688153bb0768aae950a7f765cf286bf349eb73c6e6dd22b85c4330905e5888df")
					.contentType(ContentType.JSON)
						.body(new File("./src/test/resources/jsons/user.json"))
							.when().log().all()
								.post("/public/v2/users")
									.then().log().all()
										.assertThat()
											.statusCode(201)
												.extract()
													.path("id");
		
		System.out.println("token id :" + Id);
		Assert.assertNotNull(Id);
	}
	
	
	
	
	

}
