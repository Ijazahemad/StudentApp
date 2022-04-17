package edu.jspiders.studentapp.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.jspiders.studentapp.dao.StudentDatabaseOperations;

@WebServlet("/login")
public class LoginStudentServlet extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		resp.setContentType("text/html");
		
		PrintWriter out = resp.getWriter();
		
		String name = new StudentDatabaseOperations().loginValidation(req.getParameter("email"),req.getParameter("password"));
		
		if(name != null)
		{
			HttpSession session = req.getSession();
			
			session.setMaxInactiveInterval(100000);
			
			session.setAttribute("name", name);
			
			req.getRequestDispatcher("dashboard.html").forward(req, resp);
		}
		else
		{
			req.getRequestDispatcher("loginStudent.html").forward(req, resp);
		}
	}

}
