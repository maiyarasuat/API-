package CertificateVerification;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.config.RestAssuredConfig;
import io.restassured.config.SSLConfig;

public class SSLCertificateTest {
	
		@Test
		public void sslCertificateTest() {
			
			RestAssured.given().log().all()
								.relaxedHTTPSValidation()
									.when ()
										.get("https://expired.badssl.com/")
											.then().log().all()
												.statusCode(200);
	
		}
		
		@Test
		public void sslCertificateTest_with_config() {
			
			RestAssured.config = RestAssuredConfig.config()
												.sslConfig(SSLConfig.sslConfig().relaxedHTTPSValidation());

//			RestAssured.config()
//							.sslConfig(SSLConfig.sslConfig().relaxedHTTPSValidation());
			
			RestAssured.given().log().all()
									.when ()
										.get("https://untrusted-root.badssl.com/")
											.then().log().all()
												.statusCode(200);
	
		}

}
