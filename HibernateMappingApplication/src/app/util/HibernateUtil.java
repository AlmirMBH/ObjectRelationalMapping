
package app.util;

import org.hibernate.HibernateException;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

/**
 * Hibernate Utility class with a convenient method to get Session Factory
 * object.
 *
 * @author Almir
 */
public class HibernateUtil {

    // Kreirat će se samo jedan objekat na nivou aplikacije
    private static final SessionFactory SESSION_FACTORY; 
    
    // Statički blok koda izvršava se samo jednom, bez obzira na kreirani broj objekata po
    // šablonu klase u kojoj se nalazi
    static { 
        try {
     // Configuration kreira objekat sa svim konfiguracionim parametrima
     // Serviceregistry kreira servis za bazu
    // Ovaj blok koda (TRY) je standardan i obično se kopira
    // Configuration i ServiceRegistry su neophodni za kreiranje SessionFactory
            Configuration configuration = new Configuration();
            configuration.configure();
            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().
                    applySettings(configuration.getProperties())
                    .build();
    SESSION_FACTORY = configuration.buildSessionFactory(serviceRegistry);
            
        } catch (HibernateException ex) {
            // Log the exception. 
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }
    
    public static SessionFactory getSessionFactory() {
        return SESSION_FACTORY;
    }
}
