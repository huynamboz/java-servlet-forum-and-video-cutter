package model.dao;
import java.sql.*;
import java.util.Optional;
import java.sql.Connection;
import model.bean.Category;
import java.sql.Statement;
import database.ConnectDB;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import model.bean.Thread;
public class ThreadDAO {
	
	Connection conn = null;
	Statement st = null;
	PreparedStatement preSt = null;
	ResultSetMetaData rmd;

	public void save(Thread thread) {
	    try (Connection conn = ConnectDB.getMySQLConnection();
	         PreparedStatement pstm = conn.prepareStatement("INSERT INTO threads (id, title, category_id, body, user_id) VALUES (?, ?, ?, ?, ?)")) {
	    	
	    	pstm.setString(1, thread.getId());
	        pstm.setString(2, thread.getTitle());
	        pstm.setString(3, thread.getCategoryId());
	        pstm.setString(4, thread.getContent());
	        pstm.setString(5, thread.getUserId());

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
}
