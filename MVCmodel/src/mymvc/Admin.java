package mymvc;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
public class Admin extends HttpServlet {
	private static final long serialVersionUID = 1L;   
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			String name = request.getParameter("username");
			String password = request.getParameter("pass");
			if(name.equals("admin") && password.equals("ad123")) {
				RequestDispatcher rd = request.getRequestDispatcher("admin_adds_developer.html");
				rd.forward(request, response);
			}
			else {
				out.println("The username or password entered is wrong !");
				RequestDispatcher rd = request.getRequestDispatcher("Login.html");
				rd.include(request, response);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

}
