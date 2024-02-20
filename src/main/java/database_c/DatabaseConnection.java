package database_c;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class DatabaseConnection {
	public static void getConnection() throws SQLException {
		String url = "jdbc:mysql://localhost:3306/db";
        String user = "root";
        String password = "0000";
        try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			DriverManager.getConnection(url, user, password);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
        
	}
	public static void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
