package mymvc;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistryBuilder;

public class Dev_reg_control {
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
	public void registerdeveloper(String name, String email, String pass, String re_pass, String phonenumber)throws Exception {
    	try {
    		Session s = getSession();
    		Transaction t = s.beginTransaction();
    		Dev_Register dr = new Dev_Register();
    		dr.setName(name);
    		dr.setEmail(email);
    		dr.setPass(pass);
    		dr.setRe_pass(re_pass);
    		dr.setPhonenumber(phonenumber);
    		s.save(dr);
    		t.commit();
    	}catch(HibernateException e) {
    		System.out.println(e.getMessage());
    	}
    }
}
