package POSTWithAuthAPI;

import static io.restassured.RestAssured.given;

import java.io.File;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import pojo.Credentials;

public class AuthAPITest {
	
	@Test
	public void getAuthTokenTest_WithJson() {
		RestAssured.baseURI = "https://restful-booker.herokuapp.com";
		
		String tokenId = given()
			.contentType(ContentType.JSON)
				.body("{\n"
						+ "    \"username\" : \"admin\",\n"
						+ "    \"password\" : \"password123\"\n"
						+ "}")
					.when()
						.post("/auth")
							.then()
								.assertThat()
									.statusCode(200)
										.extract()
											.path("token");
		
		System.out.println("token id :" + tokenId);
		Assert.assertNotNull(tokenId);
	}
	
	
	@Test
	public void getAuthTokenTest_WithJSONFILE() {
		
		RestAssured.baseURI = "https://restful-booker.herokuapp.com";
		
		String tokenId = given()
			.contentType(ContentType.JSON)
				.body(new File("./src/test/resources/pojo/auth.json"))
					.when()
						.post("/auth")
							.then()
								.assertThat()
									.statusCode(200)
										.extract()
											.path("token");
		
		System.out.println("token id :" + tokenId);
		Assert.assertNotNull(tokenId);
	}
	
	//serialization and Deserialization
	@Test
	public void getAuthTokenTest_WithPOJOClass() {
		
		RestAssured.baseURI = "https://restful-booker.herokuapp.com";
		
		Credentials cred = new Credentials("admin", "password123");
		
		String tokenId = given().log().all()
			.contentType(ContentType.JSON)
				.body(cred)//pojo to json conversion-s(object mapper, use jackson databind)
					.when()
						.post("/auth")
							.then()
								.assertThat()
									.statusCode(200)
										.extract()
											.path("token");
		
		System.out.println("token id :" + tokenId);
		Assert.assertNotNull(tokenId);
		
			
	}
	
	

}
