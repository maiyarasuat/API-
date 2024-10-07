package FakeUserAPITest;

import static io.restassured.RestAssured.given;

import org.testng.annotations.Test;

import io.restassured.RestAssured;

public class CreateFakeUserTest {
	
	@Test
	public void fakeUserTest() {
		
		RestAssured.baseURI = "https://fakestoreapi.com";
		
		//Geolocation geolocation = new Geolocation("22", "22.2");-37.3159
		
		User1.Address.Geolocation geolocation = new User1.Address.Geolocation("-37.3159", "81.1496");
		
		User1.Address address = new User1.Address("Banglore", "new road", 477, "696899", geolocation);
		
		User1.Name name = new User1.Name("john", "dea");
		
		User1 user = new User1("john@gmail.com", "john", "test@123", "1-570-236-7033", name, address);
		
		
		given().log().all()
		.body(user)
			.when().log().all()
				.post("/users")
					.then().log().all()
						.assertThat()
							.statusCode(200);
			
		
		
		
	}
	
	@Test
	public void fakeUserTest_WithBuilder() {
		
		RestAssured.baseURI = "https://fakestoreapi.com";
		
		User1.Address.Geolocation geolocation = new User1.Address.Geolocation.GeolocationBuilder()
												.lat("-90.99")
													.longitude("233.3")
														.build();
		
		User1.Address address =	new User1.Address.AddressBuilder()
									.city("bang")
										.street("rajaji")
											.number(998888)
												.zipcode("7272")
													.geoLocation(geolocation)
														.build();
		
		User1.Name name = new User1.Name.NameBuilder()
							.firstname("naveeen")
								.lastname("khunteta")
									.build();
		
		User1 user = new User1.User1Builder()
							.email("apitest")
								.username("lala")
									.password("lala@123")
										.phone("9888827277")
											.address(address)
												.name(name)
													.build();
		
		given().log().all()
		.body(user)
			.when().log().all()
				.post("/users")
					.then().log().all()
						.assertThat()
							.statusCode(200);
			
		
		
		
	}
	

}
