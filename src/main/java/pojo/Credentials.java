package pojo;

public class Credentials {

	private String username;
	private String password;

	//constructor
	public Credentials(String username, String password) {
		this.username = username;
		this.password = password;
	}

	//getters and setters
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
}
