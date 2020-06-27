package bank.model;

import hibernate.CloseableSession;
import hibernate.HibernateUtil;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

public abstract class AbstractBankAccount {

    
    // Za apstraktnu klasu AbstractBankAccount, polja accountNumber i amount iz klase
    // BankAccount koja se skladište u objekat BankAccount nisu
    // vidljiva pa ih injektujemo pomoću metode getThis() - inverzija naslijeđivanja,
    // odnosno eksplicitna konverzija. Ukoliko imamo više klasa, nije moguće koristiti
    // apstraktnu klasu, neophodan je DAO pattern. Ovaj pattern (active record) koristi
    // se samo za OSNOVNE OPERACIJE
    // Active recor vs abstract active record
    
    public BankAccount getThis() {
        return (BankAccount) this;
    }

/*  =================================================================================      
        CRUD OPERACIJE
    =================================================================================    
*/
    public void create() {

        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();

            session.getTransaction().begin();
            session.save(this);
            session.getTransaction().commit();

        } catch (HibernateException exception) {
            throw new RuntimeException(exception.getMessage());
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    public BankAccount get() {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();

            // U donjoj liniji koda AccountNumber koristi se u tabeli umjesto ID
            // Umjesto BankAccount.class može se pisati this.getClass()
            BankAccount bankAccount = (BankAccount) session.get(BankAccount.class,
                    getThis().getAccountNumber());
            return bankAccount;

        } catch (HibernateException exception) {
            throw new RuntimeException(exception.getMessage());
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    public void update() {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();

            session.getTransaction().begin();
            session.merge(this);
            session.getTransaction().commit();

        } catch (HibernateException exception) {
            throw new RuntimeException(exception.getMessage());
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    public void delete() {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();

            session.getTransaction().begin();
            session.delete(this);
            session.getTransaction().commit();

        } catch (HibernateException exception) {
            throw new RuntimeException(exception.getMessage());
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    public static List<BankAccount> loadAll() {

        // Hibernate 4.3. session nije autoclosable; Java 7 je uvela try with resource.
        // Hibernate 5.0. ima session koji je autoclosable
        // Ukoliko kreiramo closeable session klasu (pogledati paket hibernate), možemo
        // koristiti try with resource - cath blok umjesto try-catch-finally
        
/*  =================================================================================      
        TRY WITH RESOURCE (može se koristiti ukoliko imamo klasu CloseableSession)
    =================================================================================    
*/      
        try(CloseableSession closeableSession = 
                new CloseableSession(HibernateUtil.getSessionFactory().openSession())){
            return closeableSession.getSession().createQuery("from BankAccount").list();
        }catch(Exception exception){
            throw new RuntimeException(exception.getMessage());
        }
        
/*  =================================================================================      
        TRY - CATCH - FINALLY (TRY WITH RESOURCE, ako nemamo CloseableSession)
    =================================================================================    
*/                     
        /*Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            Query query = session.createQuery("from BankAccount");
            List<BankAccount> bankAccounts = query.list();
            return bankAccounts;

        // Linija koda ispod može se pisati umjesto dvije linije iznad
        // return session.createQuery("from BankAccount").list();

        } catch (HibernateException exception) {
            throw new RuntimeException(exception.getMessage());
        } finally {
            if (session != null) {
                session.close();
            }
        } */
    }

/*  =================================================================================      
            LOGIKA ZA PREBACIVANJE SREDSTAVA SA RAČUNA NA RAČUN
    =================================================================================    
*/    
    public static boolean transferMOney(BankAccount fromAccount, 
            BankAccount toAccount, double amount) {
        
        if (fromAccount == null || toAccount == null || amount <= 0) {
            return false; // Spriječavanje 'praznih' transakcija
        }
        
        if (fromAccount.getAccountNumber().equals(toAccount.getAccountNumber())) {
            return false; // Spriječavanje prebacivanje novca samom sebi
        }
        
                    Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();

            session.getTransaction().begin();

            if (fromAccount.getAmount() < amount) {
                System.err.println("Nedovoljno sredstava na računu!");
                return false;
            }
            // Trenutni iznos na računima
            double stariIznosFromAccount = fromAccount.getAmount();
            double stariIznosToAccount = toAccount.getAmount();

            fromAccount.setAmount(stariIznosFromAccount - amount);
            toAccount.setAmount(stariIznosToAccount + amount);

            session.update(fromAccount);
            session.update(toAccount);

            session.getTransaction().commit();
            return true;

        } catch (HibernateException exception) {
            throw new RuntimeException(exception.getMessage());
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
}
