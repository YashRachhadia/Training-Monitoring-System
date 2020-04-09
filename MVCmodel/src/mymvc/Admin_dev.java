package mymvc;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class Admin_dev extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		int id = Integer.parseInt(request.getParameter("id"));
		String name = request.getParameter("name");
		String tech = request.getParameter("tech");
		int students = Integer.parseInt(request.getParameter("students"));
		
		try {
			Admin_dev_control adc = new Admin_dev_control();
			adc.addNewDeveloper(id, name, tech, students);
			out.println("Developer Details successfully added");
			RequestDispatcher rd = request.getRequestDispatcher("admin_adds_developer.html");
			rd.include(request, response);
		}catch(Exception e) {
			e.printStackTrace();
		}		
	}
}
