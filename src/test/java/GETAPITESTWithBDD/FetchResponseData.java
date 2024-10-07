package GETAPITESTWithBDD;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class FetchResponseData {
	
	
	@Test
	public void getContactsAPIWithInvalidToken() {
		
		RestAssured.baseURI = "https://thinking-tester-contact-list.herokuapp.com";
		
		given().log().all()
			.header("Authorization" , "Bearer aghsdjg")
				.when().log().all()
					.get("/contacts")
						.then().log().all()
							.assertThat()
								.statusCode(401)
									.and()
										.assertThat()
											.body("error", equalTo("Please authenticate."));
		
	}
	
	@Test
	public void getContactsAPIWithInvalidTokenWithExtract() {
		
		RestAssured.baseURI = "https://thinking-tester-contact-list.herokuapp.com";
		
		Object errormesg = given().log().all()
				.when().log().all()
					.get("/contacts")
						.then().log().all()
							.extract()
								.path("error");
		System.out.println(errormesg);
		Assert.assertEquals(errormesg, "Please authenticate.");
	}
	
	
	@Test
	public void getSingleUser_FetchUserData() {
		
		RestAssured.baseURI = "https://gorest.co.in";
		
		Response response = given().log().all()
			.get("/public/v2/users/7396318");
				
		System.out.println("status code is:" + response.statusCode());
		System.out.println("status line is :"+ response.statusLine());
		response.prettyPrint();
		
		JsonPath js = response.jsonPath();
		
		int userid = js.getInt("id");
		System.out.println("user id:" +userid);
		
		String username = js.getString("name");
		System.out.println("user name:" +username);
		
		String usermail = js.getString("email");
		System.out.println("user mail:" +usermail);
		
		String userstatus = js.getString("status");
		System.out.println("user status is :" +userstatus);

	}
	
	@Test
	public void getSingleUser_FetchFullUSerData() {
		
		RestAssured.baseURI = "https://gorest.co.in";
		
		Response response = given().log().all()
			.get("/public/v2/users");
				
		System.out.println("status code is:" + response.statusCode());
		System.out.println("status line is :"+ response.statusLine());
		response.prettyPrint();
		
		JsonPath js = response.jsonPath();
		
		List<Integer> listsID = js.getList("id");
		System.out.println("ids are :" +listsID);
		
		
		//Assert.assertTrue(listsID.contains("7396315"));
		
		List<String> genders = js.getList("gender");
		System.out.println("genders are :" +genders);
		//Assert.assertTrue(listsID.contains("male"));
		
		for(String e : genders) {
			
			System.out.println(e);
		}	
	}
	
	@Test
	public void getProduct_FetchNestedData() {
		RestAssured.baseURI = "https://fakestoreapi.com";
				
		
		Response response = given()
								.when()
									.get("/products");
						
		
		System.out.println("status code: " + response.statusCode());
		System.out.println("status line: " + response.statusLine());
		response.prettyPrint();
		
		JsonPath js = response.jsonPath();
		
		List<Integer> ids = js.getList("id");
		System.out.println(ids);
		
		List<Object> prices = js.getList("price");
		System.out.println(prices);
		
		List<Object> rates = js.getList("rating.rate");
		System.out.println(rates);
		
		List<Integer> counts = js.getList("rating.count");
		System.out.println(counts);
		
		for(int i=0; i<ids.size(); i++) {
			int id = ids.get(i);
			Object price = prices.get(i);
			Object rate = rates.get(i);
			int count = counts.get(i);
			
			System.out.println("id:"+id +"price="+price +"rate:"+rate +"count:" +count);
			
		}
		
		Assert.assertTrue(rates.contains(4.7f));
		
		
	}
	
	
	

}
