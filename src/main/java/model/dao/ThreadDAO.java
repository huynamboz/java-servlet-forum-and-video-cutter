package model.dao;
import java.sql.*;
import java.util.Optional;
import java.sql.Connection;
import model.bean.Category;
import java.sql.Statement;
import java.text.SimpleDateFormat;

import database.ConnectDB;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import model.bean.Thread;
import model.bean.User;
import model.bean.Message;
import java.util.Date;
public class ThreadDAO {
	
	Connection conn = null;
	Statement st = null;
	PreparedStatement preSt = null;
	ResultSetMetaData rmd;

	 public void save(Thread thread) {
	        try (Connection conn = ConnectDB.getMySQLConnection();
	             PreparedStatement pstm = conn.prepareStatement("INSERT INTO threads (id, title, category_id, body, user_id, createdAt) VALUES (?, ?, ?, ?, ?, ?)")) {

	            // Đặt giá trị cho các tham số
	            pstm.setString(1, thread.getId());
	            pstm.setString(2, thread.getTitle());
	            pstm.setString(3, thread.getCategoryId());
	            pstm.setString(4, thread.getContent());
	            pstm.setString(5, thread.getUserId());

	            // Đặt giá trị cho createdAt
	            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	            String formattedDate = sdf.format(new Date());
	            pstm.setString(6, formattedDate);

	            // Thực hiện câu lệnh SQL INSERT
	            pstm.executeUpdate();

	        } catch (ClassNotFoundException | SQLException e) {
	            e.printStackTrace();
	        }
	    }
	 
	 public void createMessage(Message message) {
	        try (Connection conn = ConnectDB.getMySQLConnection();
	             PreparedStatement pstm = conn.prepareStatement("INSERT INTO messages (id, user_id, thread_id, body, createdAt) VALUES (?, ?, ?, ?, ?)")) {
	        	System.out.println("insert: " + message.getData("user_id"));
	            // Đặt giá trị cho các tham số
	            pstm.setString(1, message.getData("id"));
	            pstm.setString(2, message.getData("user_id"));
	            pstm.setString(3, message.getData("thread_id"));
	            pstm.setString(4, message.getData("body"));

	            // Đặt giá trị cho createdAt
	            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	            String formattedDate = sdf.format(new Date());
	            pstm.setString(5, formattedDate);

	            // Thực hiện câu lệnh SQL INSERT
	            pstm.executeUpdate();

	        } catch (ClassNotFoundException | SQLException e) {
	            e.printStackTrace();
	        }
	    }
	
	public Message[] getListMessage(String id) {
		try {
				Connection conn = ConnectDB.getMySQLConnection();
	            PreparedStatement pstm = 
	            		conn.prepareStatement("SELECT users.lastname, users.firstname, users.avatar, messages.body, messages.createdAt FROM users join messages on users.id = messages.user_id where messages.thread_id = ?");
	            pstm.setObject(1, id);
	            ResultSet rs = pstm.executeQuery();
				rmd  = rs.getMetaData();
				int rowIndex = 0;
	            while (rs.next()) {
				     rowIndex++;
				 }
	            rs.beforeFirst();
	            Message[] messages = new Message[rowIndex];
	            int i = 0;
	            while (rs.next()) {
	            	messages[i] = new Message(rs.getString("firstname") + rs.getString("lastname"), rs.getString("avatar"), rs.getString("body"),rs.getString("createdAt"));
//	            	System.out.print(messages[i].getName());
				     i++;
				 }
	           return messages;
	        } catch (ClassNotFoundException | SQLException e) {
	            e.printStackTrace();
	        }
			return new Message[0];
	}
	
	
	public Thread[] getListThread() {
		try {
				Connection conn = ConnectDB.getMySQLConnection();
	            PreparedStatement pstm = 
	            		conn.prepareStatement("select threads.id, users.lastname, users.firstname, users.avatar, threads.title, threads.createdAt, categories.name as categoryName\r\n"
	            				+ "from threads join users on users.id = threads.user_id join categories on categories.id = threads.category_id where users.deleteAt is null");
	         
	            ResultSet rs = pstm.executeQuery();
				rmd  = rs.getMetaData();
				int rowIndex = 0;
	            while (rs.next()) {
				     rowIndex++;
				 }
	            rs.beforeFirst();
	            Thread[] threads = new Thread[rowIndex];
	            int i = 0;
	            while (rs.next()) {
	            	threads[i] = new Thread();
	            	threads[i].setId(rs.getString("id"));
	            	threads[i].setCategoryName(rs.getString("categoryName"));
	            	threads[i].setCreatedAt(rs.getString("createdAt"));
	            	threads[i].setTitle(rs.getString("title"));
	            	threads[i].firstname = rs.getString("firstname");
	            	threads[i].lastname = rs.getString("lastname");
	            	threads[i].avatar = rs.getString("avatar");
//	            	System.out.print(messages[i].getName());
				     i++;
				 }
	           return threads;
	        } catch (ClassNotFoundException | SQLException e) {
	            e.printStackTrace();
	        }
			return new Thread[0];
	}
	
	
	
	public Category[] getListCategory() {
		try {
				Connection conn = ConnectDB.getMySQLConnection();
	            PreparedStatement pstm = conn.prepareStatement("SELECT * FROM categories");
				ResultSet rs = pstm.executeQuery();
				rmd  = rs.getMetaData();
				int rowIndex = 0;
	            while (rs.next()) {
				     rowIndex++;
				 }
	            rs.beforeFirst();
	            Category[] categories = new Category[rowIndex];
	            int i = 0;
	            while (rs.next()) {
	            	categories[i] = new Category(rs.getString("id"), rs.getString("name"));
	            	System.out.print(categories[i].getName());
				     i++;
				 }
	           return categories;
	        } catch (ClassNotFoundException | SQLException e) {
	            e.printStackTrace();
	        }
			System.out.println("err");
			return new Category[0];
	}
	
	
	public Thread getDetail(String idThread) {
		try (Connection conn = ConnectDB.getMySQLConnection();
	             PreparedStatement pstm = conn.prepareStatement("select users.lastname, users.firstname, threads.id, users.avatar, threads.title, threads.body, threads.createdAt from users join threads on users.id = threads.user_id where threads.id = ?")) {
	            pstm.setObject(1, idThread);
	            ResultSet rs = pstm.executeQuery();
	            if (rs.next()) {
	                String id = rs.getString("id");
	                String avatar = rs.getString("avatar");
	                String title = rs.getString("title");
	                String content = rs.getString("body");
	                String firstname = rs.getString("firstname");
	                String lastname = rs.getString("lastname");
	                String createdAt = rs.getString("createdAt");

	                Thread thread = new Thread();
	                thread.setId(id);
	                thread.setCreatedAt(createdAt);
	                thread.setContent(content);
	                thread.setTitle(title);
	                thread.lastname = lastname;
	                thread.firstname = firstname;
	                thread.avatar = avatar;
	                return thread;
	            }
	        } catch (ClassNotFoundException | SQLException e) {
	            e.printStackTrace();
	        }
	        return new Thread();
	}
}
