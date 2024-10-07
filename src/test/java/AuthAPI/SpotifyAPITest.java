package AuthAPI;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class SpotifyAPITest {
	
	private String accessToken;
	
	@Test
	public void generateToken() {
		
		RestAssured.baseURI = "https://accounts.spotify.com";
		
		Response response = RestAssured.given()
						.contentType(ContentType.URLENC)
						.formParam("grant_type", "client_credentials")
						.formParam("client_id", "74084f9cdf574dc696b432b4977a8344")
						.formParam("client_secret", "a577e2579431434e82c53de36f34c682")
						.when()
						.post("/api/token");
		
		response.prettyPrint();
		accessToken = response.jsonPath().getString("access_token");		
	}
	
	@Test
	public void getAlbumList() {
		
		RestAssured.baseURI = "https://api.spotify.com";
		
		Response response = RestAssured.given()
										.header("Authorization", "Bearer " + accessToken)
										//.auth()
										//.oauth2(accessToken)
										.when()
										.get("/v1/albums/4aawyAB9vmqN3uQ7FjRGTy");

		
		response.prettyPrint();

		
	}
	

}
