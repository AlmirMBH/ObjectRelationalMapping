package app;

import app.model.Author;
import app.model.Book;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

public class Application {

    public static void main(String[] args) {

        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            // ZA IZMJENU UPITA MIJENJA SE SAMO DIO U ZAGRADI PRVE LINIJE KODA (fromBook, from Author)
            // Query query = session.createQuery("from Author");
            // List<Author> authors = query.list();
            // System.out.println(authors);
            // for(Author author : authors){
            //    System.out.println(author); 
            // }
            Query query = session.createQuery("from Book");
            List<Book> books = query.list();
                        
            for(Book book : books){
                System.out.println(book); 
             }
            
        } catch (HibernateException exception) {
            throw new RuntimeException(exception.getMessage());
        } finally {
            if (session != null) {
                session.close();
            }
        }
        System.exit(0);
    }
}
