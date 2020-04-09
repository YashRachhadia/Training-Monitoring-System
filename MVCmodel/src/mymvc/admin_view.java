package mymvc;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistryBuilder;

public class admin_view extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		Session s = null; 
		try {
	        	Configuration configuration = new Configuration().configure().addAnnotatedClass(Admin_Developer.class);
	        	ServiceRegistryBuilder builder = new ServiceRegistryBuilder().applySettings(configuration.getProperties());
	        	SessionFactory factory = configuration.buildSessionFactory(builder.buildServiceRegistry());
	        	s = factory.openSession();
	        } catch (HibernateException e) {
	            System.out.println(e.getMessage());
	        }
		 
		 try { 
			 //Fetching details from new_registered_developers
			 
			 Transaction t = s.beginTransaction();
			 Query query = s.createQuery("select d.name, d.email, d.phonenumber from Dev_Register d");
			 List l = query.list();
			 Iterator it = l.iterator();
			 out.println("<html><head></head><body>");
			 out.println("<h2>New Registered Developers</h2>");
			 
			 out.print("<table border='1' width='60%'");
			 out.print("<tr><th>Name</th><th>Email</th><th>PhoneNumber</th><tr>");
			 while(it.hasNext()) {
				 Object o[] = (Object[])it.next();
				 out.print("<tr><td>"+o[0]+"</td><td>"+o[1]+"</td><td>"+o[2]+"</td></tr>"); 
			 }
			 out.print("</table>");
			 out.println("</body></html>");
			 t.commit();
			 
			 out.println("</br></br></br></br></br></br>");
			 out.println("*******************************************************"
			 		+"***********************************************************");

			 
			 //Fetching Data from developerdetails
			 
			 
			 Transaction t1 = s.beginTransaction();
			 Query query1 = s.createQuery("from Admin_Developer ad");
			 List l1 = query1.list();
			 Iterator it1 = l1.iterator();
			 out.println("<html><head></head><body>");
			 out.println("<h2>Developers already Assigned</h2>");
			 out.print("<table border='1' width='60%'");
			 out.print("<tr><th>ID</th><th>Name</th><th>Technology</th><th>Students</th><tr>");
			 while(it1.hasNext()) {
				 Object ob = (Object)it1.next();
				 Admin_Developer ad = (Admin_Developer)ob;
				 out.print("<tr><td>"+ad.getId()+"</td><td>"+ad.getName()+"</td><td>"+ad.getTech()+"</td><td>"+
				 ad.getStudents()+"</td></tr>"); 
			 }
			 out.print("</table>");
			 out.println("</body></html>");
			 t1.commit();
			 
			 out.println("<a href='admin_adds_developer.html'>Go Back</a>");
			 
			 
		 }catch(Exception e){
			 e.printStackTrace();
		 }
	}

}
