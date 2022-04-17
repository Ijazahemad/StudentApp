package edu.jspiders.studentapp.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import edu.jspiders.studentapp.dto.Student;

public class StudentDatabaseOperations {
	
	private final static String DRIVER_CLASS = "com.mysql.jdbc.Driver";
	private final static String DB_URL = "jdbc:mysql://localhost:3306/hejm33_db?user=root&password=root";
	
	private static Connection con;
	private static PreparedStatement pstmt;
	private static Statement stmt;
	private static ResultSet rs;
	
	
	public boolean registerStudent(Student s) {
		
		try {
			Class.forName(DRIVER_CLASS);
			
			con = DriverManager.getConnection(DB_URL);
			
			pstmt = con.prepareStatement("INSERT INTO student_table VALUES (?,?,?,?,?,?)");
			
			pstmt.setInt(1, s.getId());
			pstmt.setString(2, s.getFirstName());
			pstmt.setString(3, s.getLastName());
			pstmt.setDouble(4, s.getMarks());
			pstmt.setString(5, s.getEmail());
			pstmt.setString(6, s.getPassword());
			
			if(pstmt.executeUpdate() > 0)
				return true;
			
			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		finally 
		{
			if(con != null)
			{
				try
				{
					con.close();
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
			}
			
			if(pstmt != null)
			{
				try
				{
					pstmt.close();
				}
				catch( Exception e)
				{
					e.printStackTrace();
				}
			}
			
			if(rs != null)
			{
				try
				{
					rs.close();
				}
				catch (Exception e) 
				{
					e.printStackTrace();
				}
			}
		}
		return false;

	
	}


	public Student searchStudent(int id) {
		
		Student s = null;
		
		try {
			Class.forName(DRIVER_CLASS);
			
			con = DriverManager.getConnection(DB_URL);
			
			pstmt = con.prepareStatement("SELECT * FROM student_table WHERE id = ?");
			
			pstmt.setInt(1, id);
			
			rs = pstmt.executeQuery();
			
			
			while(rs.next())
			{
				 s = new Student();
				
				s.setId(rs.getInt(1));
				s.setFirstName(rs.getString(2));
				s.setLastName(rs.getString(3));
				s.setMarks(rs.getDouble(4));
				s.setEmail(rs.getString(5));
			}
			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		finally 
		{
			if(con != null)
			{
				try
				{
					con.close();
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
			}
			
			if(pstmt != null)
			{
				try
				{
					pstmt.close();
				}
				catch( Exception e)
				{
					e.printStackTrace();
				}
			}
			
			if(rs != null)
			{
				try
				{
					rs.close();
				}
				catch (Exception e) 
				{
					e.printStackTrace();
				}
			}
		}
		return s;
		
		
		
	}


	public String loginValidation(String email, String password) {
		
		try {
			Class.forName(DRIVER_CLASS);
			
			con = DriverManager.getConnection(DB_URL); 
			
			pstmt = con.prepareStatement("SELECT first_name FROM student_table WHERE emailid = ? AND password = ?");
			
			pstmt.setString(1, email);
			pstmt.setString(2, password);
			
			rs = pstmt.executeQuery();
			
			if(rs.next())
			{
				return rs.getString(1);
			}
			
			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		finally 
		{
			if(con != null)
			{
				try
				{
					con.close();
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
			}
			
			if(pstmt != null)
			{
				try
				{
					pstmt.close();
				}
				catch( Exception e)
				{
					e.printStackTrace();
				}
			}
			
			if(rs != null)
			{
				try
				{
					rs.close();
				}
				catch (Exception e) 
				{
					e.printStackTrace();
				}
			}
		}
		
		return null;
	}


	public ArrayList<Student> getAllStudents() {
		
		ArrayList<Student> allStudentsList = new ArrayList<>();
		
		try {
			Class.forName(DRIVER_CLASS);
			
			con = DriverManager.getConnection(DB_URL);
			
			stmt = con.createStatement();
			
			rs = stmt.executeQuery("SELECT * FROM student_table");
			
			while(rs.next())
			{
				Student s = new Student();
				
				s.setId(rs.getInt(1));
				s.setFirstName(rs.getString(2));
				s.setLastName(rs.getString(3));
				s.setMarks(rs.getDouble(4));
				s.setEmail(rs.getString(5));
				
				allStudentsList.add(s);
			}
			
			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		finally 
		{
			if(con != null)
			{
				try
				{
					con.close();
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
			}
			
			if(pstmt != null)
			{
				try
				{
					pstmt.close();
				}
				catch( Exception e)
				{
					e.printStackTrace();
				}
			}
			
			if(rs != null)
			{
				try
				{
					rs.close();
				}
				catch (Exception e) 
				{
					e.printStackTrace();
				}
			}
		}
		
		return allStudentsList;
	}


	public void updateStudent(Student s) {

		try {
			Class.forName(DRIVER_CLASS);
			
			con = DriverManager.getConnection(DB_URL);
			
			
			pstmt = con.prepareStatement("UPDATE student_table SET first_name = ? , last_name = ? , marks = ? , emailid = ? WHERE id = ?");
			
			pstmt.setString(1, s.getFirstName());
			pstmt.setString(2, s.getLastName());
			pstmt.setDouble(3, s.getMarks());
			pstmt.setString(4, s.getEmail());
			pstmt.setInt(5, s.getId());
			
			pstmt.executeUpdate();
		} 
		catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		finally 
		{
			if(con != null)
			{
				try
				{
					con.close();
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
			}
			
			if(pstmt != null)
			{
				try
				{
					pstmt.close();
				}
				catch( Exception e)
				{
					e.printStackTrace();
				}
			}
			
			if(rs != null)
			{
				try
				{
					rs.close();
				}
				catch (Exception e) 
				{
					e.printStackTrace();
				}
			}
		}
	}


	public void deleteStudent(int id) {
		
		try {
			
			Class.forName(DRIVER_CLASS);
			
			con = DriverManager.getConnection(DB_URL);
			
			pstmt = con.prepareStatement("DELETE FROM student_table where id = ?");
			
			pstmt.setInt(1,id);
			
			pstmt.executeUpdate();
			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		finally 
		{
			if(con != null)
			{
				try
				{
					con.close();
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
			}
			
			if(pstmt != null)
			{
				try
				{
					pstmt.close();
				}
				catch( Exception e)
				{
					e.printStackTrace();
				}
			}
		}
		
		
		
	}


	public boolean updatePassword(String email, String newPassword) {
		
		try {
			
			Class.forName(DRIVER_CLASS);
			
			con = DriverManager.getConnection(DB_URL);
			
			pstmt = con.prepareStatement("UPDATE student_table set password = ? where emailid = ?");
			
			pstmt.setString(1, newPassword);
			pstmt.setString(2, email);
			
			if(pstmt.executeUpdate() > 0)
				return true;
				
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		finally 
		{
			if(con != null)
			{
				try
				{
					con.close();
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
			}
			
			if(pstmt != null)
			{
				try
				{
					pstmt.close();
				}
				catch( Exception e)
				{
					e.printStackTrace();
				}
			}
		}
		
		
		return false;
		
	}
}
