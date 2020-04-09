package mymvc;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
public class Dev_Authenticate extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		try{
			String name = request.getParameter("name");
			String pass = request.getParameter("pass");
			
			if(Developer_Login.checkUser(name,pass)) {
				RequestDispatcher rd = request.getRequestDispatcher("developer3.html");
				rd.forward(request, response);
			}
			else {
				out.println("The account with these credentials doesn't exist.");
				out.println("Please Check your password");
				RequestDispatcher rd = request.getRequestDispatcher("developer2.html");
				rd.include(request, response);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}