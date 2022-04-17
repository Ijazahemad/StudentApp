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
import edu.jspiders.studentapp.dto.Student;

@WebServlet("/edit")
public class EditStudentServlet extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		resp.setContentType("text/html");
		
		PrintWriter out = resp.getWriter();
		
		HttpSession session = req.getSession();
	
		
		if(session != null)
		{
			req.getRequestDispatcher("./header.html").include(req, resp);
			req.getRequestDispatcher("./navigation.html").include(req, resp);
			
			Student s = new StudentDatabaseOperations().searchStudent(Integer.parseInt(req.getParameter("id")));
			
			String htmlForm = "<form action=\"update\" method=\"g\">\r\n" + 
					"			\r\n" + 
					"			<div class=\"user-details\">\r\n" + 
					"			\r\n" + 
					"				<div class=\"input-box\">\r\n" + 
					"					<span class=\"details\">Student ID</span> <input type=\"number\"\r\n" + 
					"						name=\"id\" value='"+s.getId()+"' readonly placeholder=\"Enter the Student id\" required>\r\n" + 
					"				</div>\r\n" + 
					"				\r\n" + 
					"				<div class=\"input-box\">\r\n" + 
					"					<span class=\"details\">Student First Name</span> <input type=\"text\"\r\n" + 
					"						name=\"fname\" value='"+s.getFirstName()+"' placeholder=\"Enter the Student First Name\" required>\r\n" + 
					"				</div>\r\n" + 
					"				\r\n" + 
					"				<div class=\"input-box\">\r\n" + 
					"					<span class=\"details\">Student Last Name</span> <input type=\"text\"\r\n" + 
					"						name=\"lname\"  value = '"+s.getLastName()+"'placeholder=\"Enter the Student last Name\" required>\r\n" + 
					"				</div>\r\n" + 
					"				<div class=\"input-box\">\r\n" + 
					"					<span class=\"details\">Student Marks</span> <input type=\"text\"\r\n" + 
					"						name=\"marks\" value = '"+s.getMarks()+"' placeholder=\"Enter the Student Marks\" required>\r\n" + 
					"				</div>\r\n" + 
					"				<div class=\"input-box\">\r\n" + 
					"					<span class=\"details\">Mail ID</span> <input type=\"email\"\r\n" + 
					"						name=\"email\" value='"+s.getEmail()+"' placeholder=\"Enter the Mail id\" required>\r\n" + 
					"				</div>\r\n" + 
					"				<div class=\"button\">\r\n" + 
					"					<input type=\"submit\" value=\"Update\">\r\n" + 
					"\r\n" + 
					"				</div>\r\n" + 
					"			</div>\r\n" + 
					"			\r\n" + 
					"		</form>";
			
			out.print(htmlForm);
		}
		else
			req.getRequestDispatcher("./loginStudent.html").forward(req, resp);
	}

}
