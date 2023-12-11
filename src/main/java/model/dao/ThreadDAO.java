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
