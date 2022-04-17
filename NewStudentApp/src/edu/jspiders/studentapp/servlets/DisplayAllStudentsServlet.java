package edu.jspiders.studentapp.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.jspiders.studentapp.dao.StudentDatabaseOperations;
import edu.jspiders.studentapp.dto.Student;

@WebServlet("/allStudents")
public class DisplayAllStudentsServlet extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		resp.setContentType("text/html");
		
		PrintWriter out = resp.getWriter();
		
		req.getRequestDispatcher("/header.html").include(req, resp);
		HttpSession session = req.getSession(false);
		
		if(session != null)
		{
			req.getRequestDispatcher("/navigation.html").include(req, resp);
			
			ArrayList<Student> allStudentsList = new StudentDatabaseOperations().getAllStudents();
			
			String nm = (String) session.getAttribute("name");
			
			if(allStudentsList.isEmpty())
			{
				out.print("<h1 style='Margin-left:100px;'>No Student Records Found</h1>");
			}
			else
			{
				out.print("<h3 >Student Details<h3>");
				String table = "<table border='1' align='center'>"
						+ "<tr>"
						+ "<th>Id</th>"
						+ "<th>First Name</th>"
						+ "<th>Last Name</th>"
						+ "<th>Marks</th>"
						+ "<th>Email</th>"
						+ "<th>Edit</th>"
						+ "<th>Delete</th>"
						+ "</tr>";
				
				Iterator<Student> itr = allStudentsList.iterator();
				
				while(itr.hasNext())
				{
					Student s = itr.next();
					
					String htmlRow = "<tr>"
					+ "<td>"+s.getId()+"</td>"
					+ "<td>"+s.getFirstName()+"</td>"
					+ "<td>"+s.getLastName()+"</td>"
					+ "<td>"+s.getMarks()+"</td>"
					+ "<td>"+s.getEmail()+"</td>"
					+ "<td><a href='./edit?id="+s.getId()+"'>Edit</a></td>"
					+ "<td><a href='./delete?id="+s.getId()+"'>Delete</a></td>";
					
					table += htmlRow;
				}
				
				table += "</table>";
				out.print(table);
				
			}
		}
		else
			req.getRequestDispatcher("loginStudent.html").forward(req, resp);
				
	}

}
