package model.bean;


public class Category {

	private String id;
	
	private String name;
	
	public Category(String id, String name) {
		this.id = id;
		this.name = name;
	}
	
	public void setName(String lastname) {
		this.name = lastname;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setId (String id) {
		this.id = id;
	}
	
	public String getId () {
		return this.id;
	}
}