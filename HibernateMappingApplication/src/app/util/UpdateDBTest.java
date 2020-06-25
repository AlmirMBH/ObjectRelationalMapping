package app.util;

import app.model.Actor;
import org.hibernate.HibernateException;
import org.hibernate.Session;

public class UpdateDBTest {
    public static void main(String[] args) {
        
        Session session = null;
        
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            Actor actor = (Actor) session.get(Actor.class, 214);
            
            session.getTransaction().begin();
            
            actor.setFirstName("ELMA");
            actor.setLastName("MUJIC");
            session.update(actor);
            
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
