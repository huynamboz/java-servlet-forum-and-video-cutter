package model.bean;


public class User {

	private String id;
	
	private String username;
	
	private String password;
	
	private String firstname;
	
	private String lastname;
	
	private String avatar;
	
	private String role;
	
	public void setAvatar(String a) {
		this.avatar = a;
	}
	
	public String getAvatar() {
		return this.avatar;
	}
	public void setRole (String r) {
		this.role = r;
	}
	
	public String getRole () {
		return this.role;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getId() {
		return id;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	
	public String getFirstname() {
		return firstname;
	}
	
	public String getName () {
		return this.firstname + this.lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	
	public String getLastname() {
		return lastname;
	}
}