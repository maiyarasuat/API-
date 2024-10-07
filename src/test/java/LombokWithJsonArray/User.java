package LombokWithJsonArray;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
	
	private int page;
	private int per_page;
	private int total;
	private int total_pages;
	private List<UserData> data; 
	private Support support;
	
	@Data
	@NoArgsConstructor
	@AllArgsConstructor
	@Builder
	public static class UserData {
		private int id;
		private String email;
		private String first_name;
		private String last_name;
		private String avatar;
		
	}
	
	@Data
	@NoArgsConstructor
	@AllArgsConstructor
	@Builder
	public static class Support{
		private String url;
		private String text;
		
	}
}
