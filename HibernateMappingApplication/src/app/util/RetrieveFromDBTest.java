package app.util;

// Session objekat kreira fizičku konekciju sa bazom; kad god se radi perzistencija, radi se

import app.model.Actor;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

// preko session objekta

public class RetrieveFromDBTest {
    public static void main(String[] args) {
        
        Session session = null;
        
        // ČITANJE PODATAKA IZ MAPIRANE BAZE
        try{
            SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
            session = sessionFactory.openSession();
            
            Query query = session.createQuery("from Actor");
            List<Actor> actors = query.list();            
            for(Actor actor : actors){                
                if(actor.getFirstName().equals("ALMIR"))
                System.out.println("Dear " + actor.getFirstName() + " " 
                        + actor.getLastName() + ", you are logged in.");                
            }
        // PRETRAŽIVANJE BAZE PO INDEKSU
        int id = 299; // Kreirati UI za unos ID-ja
        Actor actor1 = (Actor) session.get(Actor.class, 299); // get vraća null, ako nema podatka u bazi
        // Actor actor1 = (Actor) session.load(Actor.class, 199); // load ispisuje exception
        if(actor1 != null){
            System.out.println(actor1.getFirstName() + " " + actor1.getLastName());
        }else{
            System.out.println("Korisnik " + id + " ne postoji u bazi");
        }
            
            
        }catch(HibernateException exception){
            throw new RuntimeException(exception);
        }finally {
            if(session != null){
            session.close();
            }
        }
        System.exit(0);
    }
    
}
