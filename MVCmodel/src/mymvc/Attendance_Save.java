package mymvc;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Attendance_Save extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String date = request.getParameter("date");
		String topic = request.getParameter("topic");
		String[] attendance = request.getParameterValues("attendance");
		String id = request.getParameter("id");
		
		try{  
        	Class.forName("com.mysql.jdbc.Driver"); 
        }catch(Exception e){
        	System.out.println(e);
        }  
		//List<Admin_Students> list = new ArrayList<Admin_Students>();
        try {
        	Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/demo", "root", ""); 
        	PreparedStatement ps = con.prepareStatement("Select * from students_enrolled where Dev_Id=?");
        	ps.setString(1, id);
        	ResultSet rs = ps.executeQuery();
        	int temp=0, j=attendance.length-1;
        	while(rs.next()) {
        		String RollNo = rs.getString("RollNo");
        		String StudentName = rs.getString("StudentName");
     
        	//con.close();
 
	        	String sql = "INSERT INTO attendance (devid,rollno,student_name,topic,date,attendance) VALUES (?,?,?,?,?,?)";
	        	Connection con1 = DriverManager.getConnection("jdbc:mysql://localhost:3306/demo", "root", ""); 
	        	PreparedStatement stmt = con1.prepareStatement(sql);
	        	for(int i=temp;i<attendance.length-j;i++) {
	        		stmt.setString(1, id);
	        		stmt.setString(2, RollNo);
	        		stmt.setString(3, StudentName);
	        		stmt.setString(4, topic);
	                stmt.setString(5, date);
	        		stmt.setString(6, attendance[i]);
	        		stmt.addBatch();
	        	}
	        	temp++;
	        	j--;
	        	int r = stmt.executeUpdate();
	        	//stmt.executeBatch();
	        	
	        	
	        	if(r>0) {
		        	out.println("Attendance data saved successfully");
		        	RequestDispatcher rd = request.getRequestDispatcher("Students_info_serv");
		        	rd.include(request, response);
	        	}
        	}
        }catch(Exception e) {
        	e.printStackTrace();
		}
	}
}

