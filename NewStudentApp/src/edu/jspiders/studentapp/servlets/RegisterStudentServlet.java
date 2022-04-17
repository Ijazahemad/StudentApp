package edu.jspiders.studentapp.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.jspiders.studentapp.dao.StudentDatabaseOperations;
import edu.jspiders.studentapp.dto.Student;

@WebServlet("/register")
public class RegisterStudentServlet extends HttpServlet {
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");
		
		PrintWriter out = resp.getWriter();
		
		Student s = new Student();
		
		s.setId(Integer.parseInt(req.getParameter("id")));
		s.setFirstName(req.getParameter("fname"));
		s.setLastName(req.getParameter("lname"));
		s.setMarks(Double.parseDouble(req.getParameter("marks")));
		s.setEmail(req.getParameter("email"));
		s.setPassword(req.getParameter("password"));
		
		StudentDatabaseOperations sdo = new StudentDatabaseOperations();
		
		 if(sdo.registerStudent(s))
			 out.println("<h1>Student Data Registered</h1>");
		else
			out.println("<h1>Student Data Not Registered</h1>");
	}

}
