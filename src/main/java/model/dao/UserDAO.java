package model.dao;
import java.util.Optional;
import java.sql.Connection;

import model.bean.Thread;
import model.bean.User;
import java.sql.Statement;
import database.ConnectDB;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
public class UserDAO {
	
	Connection conn = null;
	Statement st = null;
	PreparedStatement preSt = null;
	ResultSetMetaData rmd;

	public void save(User user) {
	    try (Connection conn = ConnectDB.getMySQLConnection();
	         PreparedStatement pstm = conn.prepareStatement("INSERT INTO users (id,username, password, firstname, lastname, role) VALUES (?, ?, ?, ?, ?, ?)")) {
	    	
	    	pstm.setString(1, user.getId());
	        pstm.setString(2, user.getUsername());
	        pstm.setString(3, user.getPassword());
	        pstm.setString(4, user.getFirstname());
	        pstm.setString(5, user.getLastname());
	        pstm.setString(6, "user");

	        pstm.executeUpdate();

	    } catch (ClassNotFoundException | SQLException e) {
	        e.printStackTrace();
	    }
	}
	
	public User[] getListUser() {
			try {
					Connection conn = ConnectDB.getMySQLConnection();
		            PreparedStatement pstm = 
		            		conn.prepareStatement("select * from users");
		         
		            ResultSet rs = pstm.executeQuery();
					rmd  = rs.getMetaData();
					int rowIndex = 0;
		            while (rs.next()) {
					     rowIndex++;
					 }
		            rs.beforeFirst();
		            User[] users = new User[rowIndex];
		            int i = 0;
		            while (rs.next()) {
		            	users[i] = new User();
		            	users[i].setId(rs.getString("id"));
		            	users[i].setUsername(rs.getString("username"));
		            	users[i].setLastname(rs.getString("lastname"));
		            	users[i].setFirstname(rs.getString("firstname"));
		            	users[i].setAvatar(rs.getString("avatar"));
		            	users[i].setRole(rs.getString("role"));
//		            	System.out.print(messages[i].getName());
					     i++;
					 }
		           return users;
		        } catch (ClassNotFoundException | SQLException e) {
		            e.printStackTrace();
		        }
				return new User[0];
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
	                user.setRole(rs.getString("role"));
	                return Optional.of(user);
	            }
	        } catch (ClassNotFoundException | SQLException e) {
	            e.printStackTrace();
	        }
	        return Optional.empty();
	}
}
