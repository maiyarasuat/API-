package DeserializationProcess;

import static io.restassured.RestAssured.given;

import java.util.Arrays;

import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.restassured.RestAssured;
import io.restassured.response.Response;


public class ProductAPIWithTest {
	
	@Test
	public void getProductAPITest(){
		
		RestAssured.baseURI = "https://fakestoreapi.com";
		
		Response response = given().log().all()
			.when()
				.get("/products");
		response.prettyPrint();
		
		ObjectMapper mapper = new ObjectMapper();
		
		try {
			Product[] product = mapper.readValue(response.getBody().asString(), Product[].class);//De-serailization
			
			System.out.println(Arrays.toString(product));
			
			for(Product e : product) {
				System.out.println(" ");
				System.out.println("ID:" + e.getId());
				System.out.println("Title:" + e.getTitle());
				System.out.println("Price:" + e.getPrice());
				System.out.println("DESCRIPTION:" + e.getDescription());
				System.out.println("CATEGORY:" + e.getCategory());
				System.out.println("Rate:" + e.getRating().getRate());
				System.out.println("Count:" + e.getRating().getCount());

				System.out.println("-------------------");
				
			}
			
			
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}	
	}
}
