package servlets;

import jakarta.servlet.ServletException;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.SQLException;

import database_c.DatabaseConnection;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Servlet implementation class servlet1
 */

public class Servlet1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Servlet1() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		 try {
			 
			 Connection connection = DatabaseConnection.getConnection();
		        String query = "SELECT * FROM utilisateur";
		        PreparedStatement preparedStatement = connection.prepareStatement(query) ;
		           
		        
	            ResultSet resultSet =  preparedStatement.executeQuery();

	            out.println("<html><head><title>User Data</title></head><body>");
	            out.println("<h2>User Data</h2>");

	          
	            out.println("<table border='1'>");
	            out.println("<tr><th>ID</th><th>AGE</th><th>Name</th><th>Email</th></tr>");

	            while (resultSet.next()) {
	                out.println("<tr>");
	                out.println("<td>" + resultSet.getInt("id") + "</td>");
	                out.println("<td>" + resultSet.getInt("age") + "</td>");
	                out.println("<td>" + resultSet.getString("name") + "</td>");
	                out.println("<td>" + resultSet.getString("email") + "</td>");
	                out.println("</tr>");
	            }

	            out.println("</table>");

	            out.println("</body></html>");
	            resultSet.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Integer age = Integer.parseInt(request.getParameter("age"));
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		try {insertData(age,name, email);
		HttpSession session = request.getSession();
		session.setAttribute("user", name);
			} catch(SQLException e) {
				e.printStackTrace();
			}
		response.sendRedirect("servlet1");
	}
	private void insertData(int age,String name, String email) throws SQLException {
        Connection connection = DatabaseConnection.getConnection();
        String query = "INSERT INTO utilisateur (age,name, email) VALUES (?,?,?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
        	preparedStatement.setInt(1, age);
            preparedStatement.setString(2, name);
            preparedStatement.setString(3, email);
            preparedStatement.executeUpdate();
        }
    }
	
}
