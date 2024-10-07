package DeserializationProcess;

import static io.restassured.RestAssured.given;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class UserAPITest {

	@Test
	public void createUserwithLombok() {
		
		RestAssured.baseURI = "https://gorest.co.in";
		
		User user = new User.UserBuilder()
							.name("naveen")
							.email("naveen@api")
							.gender("male")
							.status("inactive")
							.build();
		
		Response response = given()
			.contentType(ContentType.JSON)
			.header("Authorization" , "Bearer e4b8e1f593dc4a731a153c5ec8cc9b8bbb583ae964ce650a741113091b4e2ac6")
			.body(user)
				.when()
					.post("/public/v2/users");
		
		response.prettyPrint();
		Object userID = response.jsonPath().get("id");
		System.out.println("user id is:" +userID);
		
		System.out.println("======================");
		
		Response getResponse = given()
				.header("Authorization" , "Bearer e4b8e1f593dc4a731a153c5ec8cc9b8bbb583ae964ce650a741113091b4e2ac6")
					.when()
						.get("/public/v2/users"+userID);
		
		getResponse.prettyPrint();
		
		//De-Serailization
		 ObjectMapper mapper = new ObjectMapper();
		 try {
			User userRes = mapper.readValue(getResponse.getBody().asString(), User.class);//json--pojo conversion
			System.out.println(userRes.getId() + " " +userRes.getName()+ " "+userRes.getEmail()+ " " +userRes.getGender()+ " "+userRes.getStatus());
			Assert.assertEquals(userRes.getId(), userID);
			Assert.assertEquals(userRes.getName(), user.getName());
			Assert.assertEquals(userRes.getEmail(), user.getEmail());
			Assert.assertEquals(userRes.getGender(), user.getGender());
			Assert.assertEquals(userRes.getStatus(), user.getStatus());
			
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
	}
	
}
