package model.bo;

import model.bean.Thread;
import model.dao.ThreadDAO;
public class ThreadBO {
	
	ThreadDAO threadDao = new ThreadDAO();
	public void createThread(String id, String title, String user_id, String content, String categoryId) {
		Thread thread = new Thread();
		thread.setId(id);
		thread.setTitle(title);
		thread.setUserId(user_id);
		thread.setContent(content);
		thread.setCategoryId(categoryId);
		threadDao.save(thread);
	}
}
