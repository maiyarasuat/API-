package LombokWithJsonArray;

import java.util.Arrays;

import org.testng.annotations.Test;
import static org.hamcrest.Matchers.*;

import static io.restassured.RestAssured.given;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class CreateUserWithJsonArrayLombokPojo {
	
	@Test
	public void createUserWithJsonArrayPojo() {
		
		RestAssured.baseURI = "http://httpbin.org";
		
		User.UserData user1 = new User.UserData(1, "apitest@mail.com", "lala", "venkat", "https://reqres.in/img/faces/6-image.jpg");
		User.UserData user2 = new User.UserData(2, "uitest@mail.com", "ui", "uii", "https://reqres.in/img/faces/5-image.jpg");
		User.UserData user3 = new User.UserData(3, "dbtest@mail.com", "db", "mangoo", "https://reqres.in/img/faces/4-image.jpg");
		User.UserData user4 = new User.UserData(4, "perfomtest@mail.com", "perform", "test", "https://reqres.in/img/faces/3-image.jpg");
		User.UserData user5 = new User.UserData(5, "function@mail.com", "funct", "ion", "https://reqres.in/img/faces/2-image.jpg");
		User.UserData user6 = new User.UserData(6, "sql@mail.com", "sql", "query", "https://reqres.in/img/faces/1-image.jpg");
		
		User.Support support = new User.Support("https://reqres.in/#support-heading","contributions towards server");
		
		User user = new User(1,
							 6, 
							 12,
							 2,
					  	     Arrays.asList(user1,user2,user3,user4,user5,user6), 
					  	     support);
				
		
		given().log().all()
			.contentType(ContentType.JSON)
				.body(user)
					.when().log().all()
						.post("/post")
							.then()
								.assertThat()
									.statusCode(200);
	}
}
