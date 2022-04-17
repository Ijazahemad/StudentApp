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


@WebServlet("/delete")
public class DeleteStudentServlet extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		resp.setContentType("text/html");
		
		PrintWriter out =  resp.getWriter();
		
		HttpSession session = req.getSession();
		
		if(session != null)
		{
			new StudentDatabaseOperations().deleteStudent(Integer.parseInt(req.getParameter("id")));
			
			req.getRequestDispatcher("./allStudents").include(req, resp);
		}
		else
			req.getRequestDispatcher("loginStudent.html").forward(req, resp);
	}

}
