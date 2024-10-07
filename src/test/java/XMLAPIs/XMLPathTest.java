package XMLAPIs;

import static io.restassured.RestAssured.given;

import java.util.List;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;

public class XMLPathTest {
	
	@Test
	public void circuitApiTest_Xml() {
		RestAssured.baseURI = "https://ergast.com";
		
		Response response = given()
			.when()
				.get("/api/f1/2017/circuits.xml")
					.then()
						.extract().response();
		
		String responseBody = response.body().asString();
		System.out.println(responseBody);
		System.out.println("==================");
		
		XmlPath xmlPath = new XmlPath(responseBody);
		List<String> circuitList = xmlPath.getList("MRData.CircuitTable.Circuit.CircuitName");
		System.out.println(circuitList.size());
		System.out.println("------------------");
		for(String e : circuitList) {
			System.out.println(e);
		}	
		
		System.out.println("------------------");
		
		//fetch the locality where circuitID="americas"
		
		
		String locality = xmlPath.getString("**.findAll{ it.@circuitId == 'bahrain'}.Location.Locality");
		System.out.println(locality);
		System.out.println("------------------");
		
		String country = xmlPath.getString("**.findAll{ it.@circuitId == 'bahrain'}.Location.Country");
		System.out.println(country);
		System.out.println("------------------");

		
		
		
	}
	

}
