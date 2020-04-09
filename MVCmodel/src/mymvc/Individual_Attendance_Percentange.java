package mymvc;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Individual_Attendance_Percentange extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		try{  
        	Class.forName("com.mysql.jdbc.Driver"); 
        }catch(Exception e){
        	System.out.println(e);
        }  
		
		try {
			String rollno = request.getParameter("rollno");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/demo", "root", "");
			Statement stmt = con.createStatement();
			String sql = "select * from attendance where RollNo = "+rollno;
			ResultSet result = stmt.executeQuery(sql);
			double p=0, a=0;
			while(result.next()) {
				String att = result.getString("Attendance");
				if(att.equals("Present")){
					p++;
				}
				else {
					a++;
				}
			}
			out.println("<h1>Attendance Percentage of Student with RollNo.: "+rollno+"</h1>");
			out.println("<br><br>");
			out.println("Total Number of classes:"+(p+a));
			out.println("<br>");
			out.println("Student was present in "+p+" classes");
			out.println("<br>");
			out.println("Student was absent in "+a+" classes");
			double percent = (p/(p+a))*100;
			out.println("<br>");
			out.println("<h3>Percentage Attendance: "+percent+ "%</h3>");
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}

}
