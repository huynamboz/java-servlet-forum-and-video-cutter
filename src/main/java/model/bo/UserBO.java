package model.bo;
import java.util.Optional;
import model.bean.User;
import model.dao.UserDAO;
public class UserBO {
	
	UserDAO userDao = new UserDAO();
	public void createUser(String id, String role, String username, String password, String firstname, String lastname) {
		User user = new User();
		user.setId(id);
		user.setUsername(username);
		user.setPassword(password);
		user.setFirstname(firstname);
		user.setLastname(lastname);
		user.setRole(role);
		userDao.save(user);
	}
	
	public Optional<User> getUserByUsername(String username) {
		// TODO Auto-generated method stub
		Optional<User> user = userDao.findByUsername(username);
		return user;
	}
	
	public void UpdateInfo(String username, String name, String avatar, String password) {
		User user = new User();
		user.setUsername(username);
		user.setFirstname(name.split(" ")[0]);
		user.setLastname(name.split(" ")[1]);
		user.setAvatar(avatar); 
		user.setPassword(password);
		userDao.update(user);
	}
}
