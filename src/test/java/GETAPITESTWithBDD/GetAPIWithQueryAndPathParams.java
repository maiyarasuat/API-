package GETAPITESTWithBDD;

import static io.restassured.RestAssured.given;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.equalTo;;



public class GetAPIWithQueryAndPathParams {
	
	@Test
	public void getUserWithQueryParam() {
		
		RestAssured.baseURI = "https://gorest.co.in/";
		
		given().log().all()
			.header("Authorization", "Bearer 5bfddc04ede515d398bac50d58e232b35c4f2f1c3e889646c59fe34f6ca90f6e")
				.queryParam("name", "trivedi")
					.queryParam("status", "active")
						.when().log().all()
							.get("public/v2/users")
								.then().log().all()
									.assertThat()
										.statusCode(200)
											.and()	
												.contentType(ContentType.JSON);
	}
	
	@Test
	public void queryparamwithHashMap() {
		
		RestAssured.baseURI = "https://gorest.co.in/";
		
		Map<String, String> querymap = new HashMap<String, String>();
		
		querymap.put("name", "trivedi");
		querymap.put("status", "active");
		querymap.put("gender", "male");
		
		Map<String, String> queryMapOf = Map.of(
				"name", "trivedi",
				"status", "active"
				);
		
		given().log().all()	
			.queryParams(queryMapOf)
				.when().log().all()
					.get("public/v2/users")
						.then().log().all()
							.assertThat()
								.statusCode(200)
									.and()	
										.contentType(ContentType.JSON);
		
		}	
	
	@DataProvider
	public Object[][] getUserData() {
		
		return new Object[][] {
//			{"6940744"},
//			{"6940745"},
//			{"6940746"},
//			{"6940747"},
			{"6940748"}
		};
	}
	
	
	
	@Test(dataProvider = "getUserData")
	public void getUserAPIWithPathParams(String userid) {
		
		RestAssured.baseURI = "https://gorest.co.in";
		
		given().log().all()
			.header("Authorization" , "Bearer e62ced1b8ea1b85e09bbd3a54976c4bfd9838c1843e21ce683537bcf98f16102")
				.pathParam("userid", userid)
					.when().log().all()
						.get("/public/v2/users/{userid}/posts")
							.then().log().all()
								.assertThat()
									.statusCode(200)
										//.body("title",equalTo("Vix patruus theatrum culpa quidem arx."));
											
											.body("title",hasItem("Vix patruus theatrum culpa quidem arx."));
		
//		Expected: Vix patruus theatrum culpa quidem arx.
//		  Actual: <[Vix patruus theatrum culpa quidem arx.]>
//		normal text use --> equalTo
//		verify json aarray-->hasItem		

		
		}
	
	
	
	
	
	
}
