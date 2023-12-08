package model.bean;

public class Thread {
	
	private String id;
	private String title;
	private String content;
	private String user_id;
	private String category_id;
	
	public void setId(String id) {
		this.id = id;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public void setContent (String content) {
		this.content = content;
	}
	
	public void setUserId(String id) {
		this.user_id = id;
	}
	
	public void setCategoryId(String id) {
		this.category_id = id;
	}
	
	public String getId() {
		return this.id;
	}
	
	public String getTitle() {
		return this.title;
	}
	
	public String getContent() {
		return this.content;
	}
	
	public String getUserId() {
		return this.user_id;
	}
	
	public String getCategoryId() {
		return this.category_id;
	}
	
}
