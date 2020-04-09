package mymvc;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Dev_reg_serv extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String pass = request.getParameter("pass");
		String re_pass = request.getParameter("re_pass");
		String phonenumber = request.getParameter("phonenumber");
		
		try {
			Dev_reg_control dc = new Dev_reg_control();
			if(pass.equals(re_pass)) {
				dc.registerdeveloper(name, email, pass, re_pass, phonenumber);
				out.println("New Developer successfully Registered");
				RequestDispatcher r = request.getRequestDispatcher("developer.html");
				r.include(request, response);
			}
			else {
				out.println("Password does not match with Retype Password");
				RequestDispatcher r = request.getRequestDispatcher("developer.html");
				r.include(request, response);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}	
	}
}
