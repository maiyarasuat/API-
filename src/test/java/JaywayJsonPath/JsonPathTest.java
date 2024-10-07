package JaywayJsonPath;

import static io.restassured.RestAssured.given;

import java.util.List;
import java.util.Map;

import org.testng.annotations.Test;

import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.ReadContext;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class JsonPathTest {
	
	@Test
	public void getProductApiTestWith_JsonPath() {
		
		RestAssured.baseURI = "https://fakestoreapi.com";

		Response response = given()
			.when()
				.get("/products");
		
		String jsonResponse = response.asString();
		System.out.println(jsonResponse);
		
		ReadContext ctx =  JsonPath.parse(jsonResponse);
		
		List<Integer> allIds = ctx.read("$..id");
		System.out.println(allIds);
		
		List<Integer> prices = ctx.read("$[?(@.price > 100)].price");
		System.out.println(prices);
		
		List<Integer> ids = ctx.read("$[?(@.price > 100)].id");
		System.out.println(ids);
		
		List<Integer> rates = ctx.read("$[?(@.price > 100)].rating.rate");
		System.out.println(rates);
		
		List<Integer> counts = ctx.read("$[?(@.price > 20)].rating.count");
		System.out.println(counts);
		
		List<String> titles = ctx.read("$[?(@.price > 50)].title");
		System.out.println(titles);
		
		List<Integer> id = ctx.read("$.[?(@.rating.rate<3)].id");
		System.out.println(id);
		
	}
	
	@Test
	public void productApiTest_ConditionalExamples_WithTwoAttributes() {
		
		RestAssured.baseURI = "https://fakestoreapi.com";

		Response response = given()
			.when()
				.get("/products");
		
		String jsonResponse = response.asString();
		//System.out.println(jsonResponse);
		
		ReadContext ctx =  JsonPath.parse(jsonResponse);
		
		List<Map<String,Object>> jeweleryList = ctx.read("$.[?(@.category=='jewelery')].['title','price']");
		System.out.println(jeweleryList);
		System.out.println(jeweleryList.size());
		System.out.println("--------------------------------");

		
		for(Map<String,Object> product : jeweleryList) {
			
			String title = (String)product.get("title");
			System.out.println("title:"+ " " + title);
			Number price = (Number)product.get("price");
			System.out.println("price:"+ " "+ price);
			System.out.println("--------------");

		}	
	}
	
	@Test
	public void productApiTest_ConditionalExamples_WithThreeAttributes() {
		
		RestAssured.baseURI = "https://fakestoreapi.com";

		Response response = given()
			.when()
				.get("/products");
		
		String jsonResponse = response.asString();
		//System.out.println(jsonResponse);
		
		ReadContext ctx =  JsonPath.parse(jsonResponse);
		
		List<Map<String,Object>> jeweleryList = ctx.read("$.[?(@.category=='jewelery')].['title','price','id']");
		System.out.println(jeweleryList);
		System.out.println(jeweleryList.size());
		System.out.println("--------------------------------");

		
		for(Map<String,Object> product : jeweleryList) {
			
			String title = (String)product.get("title");
			Number price = (Number)product.get("price");
			Integer id = (Integer)product.get("id");
			System.out.println("price:"+ " "+ price);
			System.out.println("title:"+ " " + title);
			System.out.println("id:"+ " " +id);
			System.out.println("--------------");

		}	
	}

}
