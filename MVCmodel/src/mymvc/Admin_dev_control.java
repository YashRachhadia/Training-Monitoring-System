package mymvc;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistryBuilder;


public class Admin_dev_control {
	private Session getSession() {
        Session s = null;
        try {
            /*sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
            s = sessionFactory.openSession();*/
        	Configuration configuration = new Configuration().configure().addAnnotatedClass(Admin_Developer.class);
        	ServiceRegistryBuilder builder = new ServiceRegistryBuilder().applySettings(configuration.getProperties());
        	SessionFactory factory = configuration.buildSessionFactory(builder.buildServiceRegistry());
        	s = factory.openSession();
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
        }
        return s;
    }
    
    public void addNewDeveloper(int id, String name, String tech, int students)throws Exception {
    	try {
    		Session s = getSession();
    		Transaction t = s.beginTransaction();
    		Admin_Developer ad = new Admin_Developer();
    		ad.setId(id);
    		ad.setName(name);
    		ad.setTech(tech);
    		ad.setStudents(students);
    		s.save(ad);
    		t.commit();
    	}catch(HibernateException e) {
    		System.out.println(e.getMessage());
    	}
    	
    }
    
}
