package database_c;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class DatabaseConnection {
	public static Connection getConnection() throws SQLException {
		String url = "jdbc:mysql://localhost:3306/db";
        String user = "root";
        String password = "0000";
        try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return DriverManager.getConnection(url, user, password);
	}
}
