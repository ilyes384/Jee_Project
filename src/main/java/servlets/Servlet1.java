package servlets;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import models.Usermodel;
import wrappers.Wrapper1;

import java.io.IOException;
import java.sql.SQLException;


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
     // Création l'objet UserModel
        Usermodel userModel = new Usermodel();
       

        
        boolean isAuthenticated = userModel.LogUser(username,password);
        if (isAuthenticated) {
            HttpSession session = request.getSession();
            session.setAttribute("userModel", userModel);
            session.setAttribute("isAuthenticated", true);
        }
      
        request.setAttribute("userModel", userModel);
        request.setAttribute("isAuthenticated", isAuthenticated);

       
        RequestDispatcher dispatcher = request.getRequestDispatcher("userData.jsp");
        dispatcher.forward(request, response);
	}
	}
