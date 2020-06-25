package app.util;

import app.model.Actor;
import java.util.Date;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class InsertIntoDBTest {
    public static void main(String[] args) {
        
        Session session = null;
        
        // SNIMANJE PODATAKA U MAPIRANU BAZU
        try{
            Actor actor = new Actor("ALMIR", "MUSTAFIC", new Date());
            // session = HibernateUtil.getSessionFactory().openSession();
            
            SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
            session = sessionFactory.openSession();
            
            session.getTransaction().begin();
            
            session.save(actor); 
            //session.persist(actor); // Novija, JPA objektna metoda
            
            session.getTransaction().commit();
            
           
        }catch(HibernateException exception){
            throw new RuntimeException(exception.getMessage());
        }finally {
            if(session != null){
            session.close();
            }
        }
        System.exit(0);
        
    }    
}
