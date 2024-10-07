package AuthAPI;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.config.SSLConfig;
import io.restassured.response.Response;

public class OAuthAPITest {
	
	@Test
	public void flickrAPITest() {
		
		RestAssured.baseURI = "https://www.flickr.com";
		
		Response response =	RestAssured.given()
						.auth()
						.oauth("1da9a521f947c987ab26043d3ea2d776",
									"0006433975197cad", 
									"72157720930394674-d2fdc1a5ac35998a", 
									"cd488756020d894c")
						.queryParam("nojsoncallback", 1)
						.queryParam("format", "json")
						.queryParam("method", "flickr.test.login")
							.when()
								.get("/services/rest")
									.then()
										.assertThat()
											.statusCode(200)
												.extract()
													.response();
		response.prettyPrint();
			
	}
}
