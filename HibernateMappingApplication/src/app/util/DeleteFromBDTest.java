package app.util;

import app.model.Actor;
import org.hibernate.HibernateException;
import org.hibernate.Session;

public class DeleteFromBDTest {
    public static void main(String[] args) {
        
        Session session = null;
        
        try {            
            session = HibernateUtil.getSessionFactory().openSession();            
                        
            session.getTransaction().begin();
            
            Actor actor = (Actor) session.get(Actor.class, 215);                        
            session.delete(actor);
            
            session.getTransaction().commit();
            
        } catch (HibernateException exception) {
            throw new RuntimeException(exception.getMessage());
        }finally{
            if(session != null){
                session.close();
            }
        }
        System.exit(0);
    }
    
}
