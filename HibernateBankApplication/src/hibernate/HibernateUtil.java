package hibernate;

import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class HibernateUtil {

    private static final SessionFactory SESSION_FACTORY;

    static {
        try {
            // Configuration kreira objekat sa svim konfiguracionim parametrima
            // Serviceregistry kreira servis za bazu
            Configuration configuration = new Configuration().configure();

            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().
                    applySettings(configuration.getProperties()).build();

            SESSION_FACTORY = configuration.buildSessionFactory(serviceRegistry);

        } catch (Throwable ex) {

            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return SESSION_FACTORY;
    }
}
