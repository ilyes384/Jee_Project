package servlets;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import wrappers.Wrapper1;

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
    private Connection connection;
    /**
     * @see HttpServlet#HttpServlet()
     */
    @Override
    public void init(ServletConfig se) {
    	connection = (Connection) se.getServletContext().getAttribute("dbConnection");
    }
    public Servlet1() {
		super();
		

    // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    
    


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Wrapper1 wrapperRequest = new Wrapper1(request);
		
        String username = wrapperRequest.getParameter("username");
        String password = wrapperRequest.getParameter("password");
        // Controle d'accés sur les sessions 
        if ( check(username,password)) {
          
        	HttpSession session = wrapperRequest.getSession();
            session.setAttribute("user", username);
            response.sendRedirect("userData.jsp");
        } else {
            
            response.sendRedirect("Login.jsp");
        }
	}
	private boolean check (String username, String password) {
	    try {
	        
	        String query = "SELECT * FROM utilisateur WHERE username = ? AND password = ?";
	        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
	            preparedStatement.setString(1, username);
	            preparedStatement.setString(2, password);

	            ResultSet resultSet = preparedStatement.executeQuery();
	            return resultSet.next(); 
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return false; 
	    }
	
	
	}}
