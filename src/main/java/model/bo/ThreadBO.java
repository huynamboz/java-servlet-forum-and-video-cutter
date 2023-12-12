package model.bo;

import model.bean.Thread;
import model.bean.Message;
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
	
	public Thread getDetail(String id) {
		return threadDao.getDetail(id);
	}
	
	public Message[] getListMessage(String id) {
		return threadDao.getListMessage(id);
	}
	
	public Thread[] getListThread() {
		return threadDao.getListThread();
	}
	
	public void createMessage(String user_id, String body, String thread_id, String id) {
		Message message = new Message();
		message.setData("id", id);
		message.setData("user_id", user_id);
		message.setData("body", body);
		message.setData("thread_id", thread_id);
		threadDao.createMessage(message);
	}
	
}
