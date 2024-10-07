package GETAPITESTWithBDD;

import static org.hamcrest.Matchers.equalTo;

import static io.restassured.RestAssured.given;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class ContactsAPI {
	
	@BeforeMethod
	public void setup() {
		RestAssured.baseURI = "https://thinking-tester-contact-list.herokuapp.com";
	}
	
	
	@Test
	public void getContactsApiTest() {
		
		given().log().all()
			.header("Authorization", "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJfaWQiOiI2NmE1YmNjNzQzZDFhYTAwMTNlNzE2NjciLCJpYXQiOjE3MjUzNTc4MDF9.QNlKGcRx2oo-t0mvL3BMgwwyElYhpbSrcmyt6tFjuns\n"+ "")
				.when().log().all()
					.get("/contacts")
						.then().log().all()
							.assertThat()
								.statusCode(200)
									.and()
										.statusLine("HTTP/1.1 200 OK")
											.and()
												.contentType(ContentType.JSON)
													.and()
														.body("$.size()", equalTo(34));
													
		
		
	}
}
