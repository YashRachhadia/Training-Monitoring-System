package mymvc;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistryBuilder;

public class Admin_Stud_control {
	private Session getSession() {
        Session s = null;
        try {
        	Configuration configuration = new Configuration().configure().addAnnotatedClass(Admin_Developer.class);
        	ServiceRegistryBuilder builder = new ServiceRegistryBuilder().applySettings(configuration.getProperties());
        	SessionFactory factory = configuration.buildSessionFactory(builder.buildServiceRegistry());
        	s = factory.openSession();
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
        }
        return s;
    }
	
	public void add_students(int devid, int rollno, String stname) throws Exception {
		try{
			Session s = getSession();
			Transaction t = s.beginTransaction();
			Admin_Students as = new Admin_Students();
			as.setDevid(devid);
			as.setRollno(rollno);
			as.setStname(stname);
			s.save(as);
			t.commit();
		}catch(HibernateException e) {
			e.printStackTrace();
		}
	}
}
