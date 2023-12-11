package model.dao;
import java.util.Optional;
import java.sql.Connection;
import model.bean.*;
import java.sql.Statement;
import database.ConnectDB;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

public class WorkerDAO {
	
	Connection conn = null;
	Statement st = null;
	PreparedStatement preSt = null;
	ResultSetMetaData rmd;

	public void save(Worker worker) {
	    try (Connection conn = ConnectDB.getMySQLConnection();
	         PreparedStatement pstm = conn.prepareStatement("INSERT INTO workers (id, user_id, data) VALUES (?, ?, ?)")) {
	    	
	    	pstm.setString(1, worker.getId());
	    	pstm.setString(2, worker.getUserId());
	    	pstm.setString(3, worker.getData());

	        pstm.executeUpdate();

	    } catch (ClassNotFoundException | SQLException e) {
	        e.printStackTrace();
	    }
	}
	
	public void update(Worker worker) {
	    try (Connection conn = ConnectDB.getMySQLConnection();
	         PreparedStatement pstm = conn.prepareStatement("UPDATE workers SET data = ? WHERE id = ?")) {
	    	
	    	pstm.setString(2, worker.getId());
	        pstm.setString(1, worker.getData());

	        pstm.executeUpdate();

	    } catch (ClassNotFoundException | SQLException e) {
	        e.printStackTrace();
	    }
	}
	
	public Worker[] getList(String id) {
		try {
				Connection conn = ConnectDB.getMySQLConnection();
	            PreparedStatement pstm = conn.prepareStatement("SELECT * FROM workers WHERE user_id = ?");
	            pstm.setString(1, id);
				ResultSet rs = pstm.executeQuery();
				rmd  = rs.getMetaData();
				int rowIndex = 0;
	            while (rs.next()) {
				     rowIndex++;
				 }
	            rs.beforeFirst();
	            Worker[] workers = new Worker[rowIndex];
	            int i = 0;
	            while (rs.next()) {
	            	workers[i] = new Worker(rs.getString("id"), rs.getString("data"));
	            	System.out.print(workers[i].getData());
				     i++;
				 }
	           return workers;
	        } catch (ClassNotFoundException | SQLException e) {
	            e.printStackTrace();
	        }
			System.out.println("err");
			return new Worker[0];
	}
}
