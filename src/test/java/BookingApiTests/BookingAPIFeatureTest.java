package BookingApiTests;

import static io.restassured.RestAssured.given;
import java.io.File;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import static org.hamcrest.Matchers.*;

/**
 * 
 * @apiNote:best class of API Chaining
 * 
 * 	pojo --> json: serialization
	json--->pojo: de-serialization
	jackson-databind/gson/yasson
 * 
 */
public class BookingAPIFeatureTest {
	
	String tokenId;
	
	@BeforeMethod
	public void getToken() {
		
		RestAssured.baseURI = "https://restful-booker.herokuapp.com";
		
		tokenId = given().log().all()
			.contentType(ContentType.JSON)
				.body(new File("./src/test/resources/jsons/auth.json"))
					.when().log().all()
						.post("/auth")
							.then().log().all()
								.assertThat()
									.statusCode(200)
										.extract()
											.path("token");
		
		System.out.println("token id :" + tokenId);
	}
	
	@Test
	public void getBookingIDsTest() {
		
		RestAssured.baseURI = "https://restful-booker.herokuapp.com";

		Response response = given()
			.when().log().all()
				.get("/booking")
					.then().log().all()
						.assertThat()
							.statusCode(200)
								.and()
								 	.extract()
								 		.response();
		JsonPath js = response.jsonPath();
		List<Integer> ids = js.getList("bookingid");
		System.out.println("total booking ids:" +ids.size());
		
		for(Integer id : ids) {
			//System.out.println(id);
		Assert.assertNotNull(id);
	}
}

	@Test
	public void getBookingIDTest() {
		
		RestAssured.baseURI = "https://restful-booker.herokuapp.com";
		
		int newbookingId = createBooking();
		
		given().log().all()
			.pathParam("bookingid", newbookingId)
				.when().log().all()
					.get("/booking/{bookingid}")
						.then().log().all()
							.assertThat()
								.statusCode(200)
									.and()
										.body("firstname", equalTo("Jim"))
											.and()
												.body("lastname", equalTo("Brown"))
													.and()
														.body("bookingdates.checkout", equalTo("2019-01-01"));
													
	}
	
	
	@Test
	public void createBookingTest() {
		
		Assert.assertNotNull(createBooking());
		
	}
	
	
	@Test
	public void updateBookingTest() {
		
		RestAssured.baseURI = "https://restful-booker.herokuapp.com";
		
		//create a new booking id: POST CALL
		int newbookingId = createBooking();
		
		//get a same booking id: PUT CALL
			verifyBookingID(newbookingId);
		
		//update a booking id: PUT CALL
			given().log().all()
				.pathParam("bookingid", newbookingId)
					.contentType(ContentType.JSON)
					.header("cookie", "token="+tokenId)
						.body("{\n"
								+ "    \"firstname\" : \"Maiyarasu\",\n"
								+ "    \"lastname\" : \"charley\",\n"
								+ "    \"totalprice\" : 111,\n"
								+ "    \"depositpaid\" : true,\n"
								+ "    \"bookingdates\" : {\n"
								+ "        \"checkin\" : \"2018-01-01\",\n"
								+ "        \"checkout\" : \"2019-01-01\"\n"
								+ "    },\n"
								+ "    \"additionalneeds\" : \"lunch\"\n"
								+ "}")
									.when().log().all()
										.put("/booking/{bookingid}")
											.then().log().all()
												.assertThat()
													.statusCode(200)
														.body("firstname", equalTo("Maiyarasu"))
															.and()
																.body("lastname", equalTo("charley"))
																	.and()
																		.body("additionalneeds", equalTo("lunch"));
			
	}
	
	@Test
	public void partialBookingTest() {
		
		RestAssured.baseURI = "https://restful-booker.herokuapp.com";
		
		//create a new booking id: POST CALL
		int newbookingId = createBooking();
		
		//get a same booking id: PUT CALL
			verifyBookingID(newbookingId);
		
		//update a booking id: PATCH CALL
			given().log().all()
				.pathParam("bookingid", newbookingId)
					.contentType(ContentType.JSON)
					.header("cookie", "token="+tokenId)
						.body("{\n"
								+ "    \"firstname\" : \"Ajith\",\n"
								+ "    \"lastname\" : \"Crawley\"\n"
								+ "}")
									.when().log().all()
										.patch("/booking/{bookingid}")
											.then().log().all()
												.assertThat()
													.statusCode(200)
														.body("firstname", equalTo("Ajith"))
															.and()
																.body("lastname", equalTo("Crawley"));
																	
	}
	
	@Test
	public void deleteBookingTest() {
		
		RestAssured.baseURI = "https://restful-booker.herokuapp.com";
		
		//create a new booking id: POST CALL
		int newbookingId = createBooking();
		
		//get a same booking id: PUT CALL
			verifyBookingID(newbookingId);
		
		//delete a booking id: DELETE CALL
			given().log().all()
				.pathParam("bookingid", newbookingId)
					.contentType(ContentType.JSON)
					.header("cookie", "token="+tokenId)
						.when().log().all()
							.delete("/booking/{bookingid}")
									.then().log().all()
											.assertThat()
												.statusCode(201);
	}
	
	//genric method
	public void verifyBookingID(int newbookingId) {
		
		given()
		.pathParam("bookingid", newbookingId)
			.when()
				.get("/booking/{bookingid}")
					.then().log().all()
						.assertThat()
							.statusCode(200);
	}
	
	//genric method
	public int createBooking() {
		
		RestAssured.baseURI = "https://restful-booker.herokuapp.com";
		
		int bookingId = given().log().all()
			.contentType(ContentType.JSON)
				.body("{\n"
					+ "    \"firstname\" : \"Jim\",\n"
					+ "    \"lastname\" : \"Brown\",\n"
					+ "    \"totalprice\" : 111,\n"
					+ "    \"depositpaid\" : true,\n"
					+ "    \"bookingdates\" : {\n"
					+ "        \"checkin\" : \"2018-01-01\",\n"
					+ "        \"checkout\" : \"2019-01-01\"\n"
					+ "    },\n"
					+ "    \"additionalneeds\" : \"Breakfast\"\n"
					+ "}")
				.when()
					.post("/booking")
						.then()
							.extract()
								.path("bookingid");
		System.out.println("booking id:" +bookingId);
		return bookingId;
							
	}
	
	
}
