package mymvc;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Students_info_serv extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
        try{  
        	Class.forName("com.mysql.jdbc.Driver");
    		//Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/demo", "root", ""); 
        }catch(Exception e){
        	System.out.println(e);
        }  
        List<Admin_Students> list = new ArrayList<Admin_Students>();
        try {
        	int id = Integer.parseInt(request.getParameter("id"));
        	Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/demo", "root", ""); 
        	PreparedStatement ps = con.prepareStatement("Select * from students_enrolled where Dev_Id=?");
        	ps.setInt(1, id);
        	ResultSet rs = ps.executeQuery();
        	while(rs.next()) {
        		Admin_Students ast = new Admin_Students();
        		ast.setRollno(rs.getInt(1));
        		ast.setStname(rs.getString(2));
        		list.add(ast);
        	}
        	con.close();
        	DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        	Date date = new Date();
        	out.println("Date: "+dateFormat.format(date)); 
        	out.println("<br><br><br>");
        	out.println("<form action='Attendance_Save' method='post'>");
        	out.println("<html><head></head><body>"+"Enter today's date: <input type='text' name='date'");
        	out.println("<br><br><br>");
        	out.println("Enter today's topic: <input type='text' name='topic'>");
        	out.println("<br><br>");
        	out.print("<table border='1' width='60%'");  
            out.print("<tr><th>RollNo</th><th>StudentName</th><th>Attendance</th><tr>");  
            for(Admin_Students e:list){  
            	out.print("<tr><td>"+e.getRollno()+"</td><td>"+e.getStname()+"<td><select name='attendance'>\r\n" + 
             		"  <option value=\"Present\">Present</option>\r\n" + 
             		"  <option value=\"Absent\">Absent</option>\r\n" + "</select>\r\n</td><tr>");   
            }  
            out.print("</table>");
            out.println("<br><br>");
            out.println("Enter your id to save: <input type='text' name='id'>");
        	out.println("<br><br><br>");
            out.println("<input type='submit' name='save' value='Save'>");
            out.println("<br><br>");
            out.println("</form>");
            out.println("<a href='developer3.html'>Go Back</a>");
            out.println("</body></html>");
            out.close();  
        }catch(Exception e) {
        	e.printStackTrace();
        } 
        
	}
}
