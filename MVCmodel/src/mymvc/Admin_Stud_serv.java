package mymvc;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Admin_Stud_serv extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		int devid = Integer.parseInt(request.getParameter("devid"));
		int rollno = Integer.parseInt(request.getParameter("rollno"));
		String stname = request.getParameter("stname");
		
		try {
			
			Admin_Stud_control asc = new Admin_Stud_control();
			asc.add_students(devid, rollno, stname);
			out.println("Student detailes added successfully");
			RequestDispatcher rd = request.getRequestDispatcher("add_students.html");
			rd.include(request, response);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

}
