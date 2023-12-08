package database;
import java.sql.*;

public class ConnectDB {
	
	public static Connection getMySQLConnection() throws ClassNotFoundException, SQLException {
		String dbURL = "jdbc:mysql://localhost:3306/boiler?autoReconnect=true&useSSL=false";
		String username = "root";
		String password = "Huynam@2003";
		Class.forName("com.mysql.jdbc.Driver");
		Connection conn = (Connection) DriverManager.getConnection(dbURL, username, password);
		if (conn != null) {
			System.out.println("Kết nối thành công");
			return conn;
		}
		return null;
	}
}