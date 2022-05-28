package com.company;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

//@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			
			PrintWriter out=response.getWriter();
			
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/login","root","root");
			
			String user=request.getParameter("userName");
			String pass=request.getParameter("password");
			PreparedStatement ps =con.prepareStatement("Select username from login where username=? and password=?");
			ps.setString(1, user);
			ps.setString(2, pass);
			ResultSet rs=ps.executeQuery();
			if(rs.next()) {
				RequestDispatcher rd = request.getRequestDispatcher("Welcome.jsp");
				rd.forward(request, response);
			}
			else {
				
				out.println("<font color=red size=18>Login Failed<br>");
				out.println("<a href=login.jsp>Try Agian!</a>");
				
			}
			
		}
		
		catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
	}

}
