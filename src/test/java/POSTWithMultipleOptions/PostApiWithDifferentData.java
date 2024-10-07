package POSTWithMultipleOptions;

import static io.restassured.RestAssured.given;

import java.io.File;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class PostApiWithDifferentData {
	
	@Test
	public void bodyWithTextData() {
		
		RestAssured.baseURI = "https://postman-echo.com";
		
		String payload = "Hi this is for text testing purpose";
		
		given().log().all()
			.contentType(ContentType.TEXT)
				.body(payload)
					.when().log().all()
						.post("/post")
							.then()
								.assertThat()
									.statusCode(200);
		
	}
	
	@Test
	public void bodyWithJavaScript() {
		
		RestAssured.baseURI = "https://postman-echo.com";
		
		String payload = "window.scrollTo(0, document.body.scrollHeight/2)";
		
		given().log().all()
			.contentType("application/javascript")
				.body(payload)
					.when().log().all()
						.post("/post")
							.then()
								.assertThat()
									.statusCode(200);
		
	}
	
	@Test
	public void bodyWithMultipart() {
		
		RestAssured.baseURI = "https://postman-echo.com";
		
		
		given().log().all()
			.contentType(ContentType.MULTIPART)
			//.multiPart("screenshot", new File("/users/maiyarasu/Desktop/CI_CD_Tools_List_1710322841.pdf"))
			//.multiPart("picture", new File("/users/maiyarasu/Desktop/0Y1A7867.JPG"))
			.multiPart("image", new File("/users/maiyarasu/Downloads/peta-indonesia-png.png"))
					.when().log().all()
						.post("/post")
							.then()
								.assertThat()
									.statusCode(200);
		
	}
	

}
