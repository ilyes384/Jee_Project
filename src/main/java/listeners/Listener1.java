package listeners;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import database_c.DatabaseConnection;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

@WebListener
public class Listener1 implements ServletContextListener {

    // Méthode appelée lors du démarrage de l'application
    @Override
    public void contextInitialized (ServletContextEvent sce) {
    	ServletContext servletContext = sce.getServletContext();
    	String url = "jdbc:mysql://localhost:3306/db";
        String user = "root";
        String password = "0000";
        try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connection = DriverManager.getConnection(url, user, password);
			servletContext.setAttribute("dbConnection", connection);
		} catch (ClassNotFoundException| SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
       
    }

    
    @Override
    public void contextDestroyed(ServletContextEvent sce) {
         try {
        	 ServletContext servletContext = sce.getServletContext();
        	 Connection connection = (Connection) servletContext.getAttribute("dbConnection");
        	 if (connection != null) {
        	 connection.close();
        	 }
             System.out.println("Connexion à la base de données fermée.");
         } catch (SQLException e) {
             System.err.println("Erreur");
             e.printStackTrace();
         }
    }
}
