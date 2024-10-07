package GETAPITESTWithBDD;

import static io.restassured.RestAssured.given;

import org.testng.annotations.Test;

import static org.hamcrest.Matchers.equalTo;
import io.restassured.RestAssured;

public class ProductAPIs {
	
	//BDD Approach--Given-->When-->Then
	@Test
	public void getProductsTest_1() {
		RestAssured.baseURI = "https://fakestoreapi.com";

		given()
			.log().all()
				.get("/products")
					.then().log().all()
						.assertThat()
							.statusCode(200);

	}

	@Test
	public void getProductsTest_2() {
		RestAssured.baseURI = "https://fakestoreapi.com";
		
		given().log().all()
			.when().log().all()
				.get("/products")
					.then().log().all()
						.assertThat()
							.statusCode(200)
								.and()
									.body("$.size()", equalTo(20));
	
	}

}
