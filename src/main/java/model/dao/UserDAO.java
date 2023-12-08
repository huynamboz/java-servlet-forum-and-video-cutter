package model.dao;
import java.util.Optional;
import java.sql.Connection;
import model.bean.User;
import java.sql.Statement;
import database.ConnectDB;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
public class UserDAO {
	
	Connection conn = null;
	Statement st = null;
	PreparedStatement preSt = null;
	

	public void save(User user) {
	    try (Connection conn = ConnectDB.getMySQLConnection();
	         PreparedStatement pstm = conn.prepareStatement("INSERT INTO users (id,username, password, firstname, lastname) VALUES (?, ?, ?, ?, ?)")) {
	    	
	    	pstm.setString(1, user.getId());
	        pstm.setString(2, user.getUsername());
	        pstm.setString(3, user.getPassword());
	        pstm.setString(4, user.getFirstname());
	        pstm.setString(5, user.getLastname());

	        pstm.executeUpdate();

	    } catch (ClassNotFoundException | SQLException e) {
	        e.printStackTrace();
	    }
	}
	
	public void update(User user) {
	    try (Connection conn = ConnectDB.getMySQLConnection();
	         PreparedStatement pstm = conn.prepareStatement("UPDATE users SET avatar = ?, password = ?, firstname = ?, lastname = ? WHERE username = ?")) {
	    	
	    	pstm.setString(5, user.getUsername());
	        pstm.setString(1, user.getAvatar());
	        pstm.setString(2, user.getPassword());
	        pstm.setString(3, user.getFirstname());
	        pstm.setString(4, user.getLastname());

	        pstm.executeUpdate();

	    } catch (ClassNotFoundException | SQLException e) {
	        e.printStackTrace();
	    }
	}
	
	public Optional<User> findByUsername(String username) {
		try (Connection conn = ConnectDB.getMySQLConnection();
	             PreparedStatement pstm = conn.prepareStatement("SELECT * FROM users WHERE username = ?")) {
	            pstm.setObject(1, username);
	            ResultSet rs = pstm.executeQuery();
	            if (rs.next()) {
	                String userId = rs.getString("id");
	                String avatar = rs.getString("avatar");
	                String username1 = rs.getString("username");
	                String password = rs.getString("password");
	                String firstname = rs.getString("firstname");
	                String lastname = rs.getString("lastname");

	                User user = new User();
	                user.setId(userId);
	                user.setAvatar(avatar);
	                user.setUsername(username1);
	                user.setPassword(password);
	                user.setFirstname(firstname);
	                user.setLastname(lastname);
	                return Optional.of(user);
	            }
	        } catch (ClassNotFoundException | SQLException e) {
	            e.printStackTrace();
	        }
	        return Optional.empty();
	}
}
