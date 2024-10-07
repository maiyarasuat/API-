package FakeUserAPITest;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User1 {
	
	private String email;
	private String username;
	private String password;
	private String phone;
	private Name name;
	private Address address;
	
	@Data
	@Builder
	@AllArgsConstructor
	@NoArgsConstructor
	public static class Name{
		private String firstname;
		private String lastname;
	}
	@Data
	@Builder
	@AllArgsConstructor
	@NoArgsConstructor
	public static class Address{
		private String city;
		private String street;
		private int number;
		private String zipcode;
		private Geolocation geoLocation;
		
		@Data
		@Builder
		@AllArgsConstructor
		@NoArgsConstructor
		public static class Geolocation{
			private String lat;
		//	@JsonProperty("long")
			private String longitude;
		}
	}
}
