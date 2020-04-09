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

public class Attendance_Report extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		try{  
        	Class.forName("com.mysql.jdbc.Driver"); 
        }catch(Exception e){
        	System.out.println(e);
        }  
		
		String devid = request.getParameter("devid");
		
		try {
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/demo", "root", "");
			Statement stmt = con.createStatement();
			String sql = "select * from attendance where DevId = "+devid;
			ResultSet result = stmt.executeQuery(sql);
			
			out.println("<html><head></head><body><h1>Attendance Report for Developer (DevId:"+devid+")</h1>");
			out.println("<br><br><br>");
			out.print("<table border='1' width='60%'");  
            out.print("<tr><th>RollNo</th><th>Student_Name</th><th>Topic</th><th>Date</th><th>Attendance</th><tr>");
            while(result.next()) {
            	out.print("<tr><td>"+result.getString("RollNo")+"</td><td>"+result.getString("Student_Name")+"</td><td>"
            			+result.getString("Topic")+"</td><td>"+result.getString("Date")+"</td><td>"
            			+result.getString("Attendance")+"</td></tr>");
            }
            out.print("</table>");
            out.print("<br><br><br>");
            out.println("<a href='Attendance_Report.html'>Go Back</a>");
            out.println("</body></html>");
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		
	}

}
