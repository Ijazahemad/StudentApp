package edu.jspiders.studentapp.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.w3c.dom.html.HTMLParagraphElement;

import edu.jspiders.studentapp.dao.StudentDatabaseOperations;
import edu.jspiders.studentapp.dto.Student;

@WebServlet("/search")
public class SearchStudentServlet extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		resp.setContentType("text/html");
		
		PrintWriter out = resp.getWriter();
		
		int id = Integer.parseInt(req.getParameter("id"));
		
		Student s = new StudentDatabaseOperations().searchStudent(id);
		
		HttpSession session = req.getSession(false);
		
		req.getRequestDispatcher("header.html").include(req, resp);
		
		if(session != null)
		{
			req.getRequestDispatcher("navigation.html").include(req, resp);

			if(s != null)
			{
				out.print("<h2>Student Details</h2>");
				
				String table = "<table border ='1' align='center>'"
						
						+ "<tr>"
						+ "<th>Id</th>"
						+ "<th>First Name</th>"
						+ "<th>Last Name</th>"
						+ "<th>Marks</th>"
						+ "<th>Email</th>"
						+ "</tr>"
						+ "<tr>"
						+ "<td>"+s.getId()+"</td>"
						+ "<td>"+s.getFirstName()+"</td>"
						+ "<td>"+s.getLastName()+"</td>"
						+ "<td>"+s.getMarks()+"</td>"
						+ "<td>"+s.getEmail()+"</td>"
						+ "</tr>"
						+ "</table>";
				
				out.print(table);  
			}
			else
				out.print("<h1>Student data is not present for id = "+ id +"</h1>");
				
		}
		else
			req.getRequestDispatcher("loginStudent.html").forward(req, resp);
		
	
	}
}
