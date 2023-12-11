package model.bean;

public class Worker {
	private String id;
	
	private String user_id;
	
	private String data;
	
	public Worker() {
		
	}
	public Worker(String id, String Data) {
		this.id = id; 
		this.data = Data;
	}
	public String getId() {
		return this.id;
	}
	
	public String getUserId() {
		return this.user_id;
	}
	
	public String getData() {
		return this.data;
	}
	
	public void setData(String type, String val) {
		switch (type) {
		case "id": {
			this.id = val;
			break;
		}
		case "user_id": {
			this.user_id = val;
			break;
		}
		case "data": {
			this.data = val;
			break;
		}
		}
	}
}
