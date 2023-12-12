package model.bean;

public class Message {
	private String id;
	private String thread_id;
	private String user_id;
	private String body;
	private String nameUser;
	private String avatar;
	private String createdAt;
	public Message() {
	}

	public Message(String name, String avatar, String body, String createdAt) {
		this.nameUser = name;
		this.avatar = avatar;
		this.body = body;
		this.createdAt = createdAt;
	}
	public String getData(String type) {
		switch(type) {
		case "id": return this.id;
		case "thread_id": return this.thread_id;
		case "user_id": return this.user_id;
		case "body": return this.body;
		case "nameUser": return this.nameUser;
		case "createdAt": return this.createdAt;
		case "avatar": return this.avatar;
		}
		return "null";
	}
	
	public void setData(String type, String val) {
		switch(type) {
		case "id":  
			this.id = val;
			break;

		case "thread_id":  
			this.thread_id = val;
			break;
			
		case "user_id":  
			this.user_id = val;
			break;
			
		case "body":  
			this.body = val;
			break;
			
		case "nameUser": 
			this.nameUser = val;
			break;
			
		case "createdAt": 
			this.createdAt = val;
			break;
			
		case "avatar": 
			this.avatar = val;
			break;
			
		}
	}
}
	