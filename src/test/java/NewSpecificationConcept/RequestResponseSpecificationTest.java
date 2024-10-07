package NewSpecificationConcept;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class RequestResponseSpecificationTest {
	
	public static RequestSpecification user_req_spec() {
		
		RequestSpecification requestSpec = new RequestSpecBuilder()
							.setBaseUri("https://gorest.co.in")
							.setContentType(ContentType.JSON)
							.addHeader("Authorization", "Bearer e4b8e1f593dc4a731a153c5ec8cc9b8bbb583ae964ce650a741113091b4e2ac6")
							.build();
		
		return requestSpec;
							
		
		
		
		
		
	}
	
	
}
