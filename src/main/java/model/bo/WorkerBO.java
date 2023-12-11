package model.bo;
import model.bean.*;
import model.dao.*;
public class WorkerBO {
	
	WorkerDAO workerDao = new WorkerDAO();
	public void createWorker(String id, String user_id, String data) {
		Worker worker = new Worker();
		worker.setData("id", id);
		worker.setData("user_id", user_id);
		worker.setData("data", data);
		workerDao.save(worker);
	}
	
	public void update(String id, String data) {
		Worker worker = new Worker();
		worker.setData("id", id);
		worker.setData("data", data);
		workerDao.update(worker);
	}
	
	public Worker[] getList(String id) {
		return workerDao.getList(id);
	}
}
