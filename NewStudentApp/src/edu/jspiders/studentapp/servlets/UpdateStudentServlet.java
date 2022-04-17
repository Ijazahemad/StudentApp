package edu.jspiders.studentapp.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.jspiders.studentapp.dao.StudentDatabaseOperations;
import edu.jspiders.studentapp.dto.Student;

@WebServlet("/update")
public class UpdateStudentServlet extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");
		
		
		
		HttpSession session = req.getSession(false);
		
		if(session != null)
		{
			Student s = new Student();
			
			s.setId(Integer.parseInt(req.getParameter("id")));
			s.setFirstName(req.getParameter("fname"));
			s.setLastName(req.getParameter("lname"));
			s.setMarks(Double.parseDouble(req.getParameter("marks")));
			s.setEmail(req.getParameter("email"));
			
			
			new StudentDatabaseOperations().updateStudent(s);
			
			req.getRequestDispatcher("./allStudents").forward(req, resp);
		}
		else
			req.getRequestDispatcher("./loginStudent.html").forward(req, resp);
		
		
		
	}
}
