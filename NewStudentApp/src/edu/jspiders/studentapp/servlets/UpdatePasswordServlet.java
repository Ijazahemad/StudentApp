package edu.jspiders.studentapp.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.jspiders.studentapp.dao.StudentDatabaseOperations;
@WebServlet("/updatePassword")
public class UpdatePasswordServlet extends HttpServlet {
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
			resp.setContentType("text/html");
		
		
			
			if(new StudentDatabaseOperations().updatePassword(req.getParameter("email"),req.getParameter("newPassword")))
				req.getRequestDispatcher("./loginStudent.html").forward(req, resp);
			
			else
				req.getRequestDispatcher("./forgotPassword.html").forward(req, resp);
	}
}
