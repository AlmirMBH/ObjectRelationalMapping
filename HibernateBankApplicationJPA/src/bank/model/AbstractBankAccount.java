package bank.model;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import javax.persistence.Persistence;
import javax.persistence.Query;


public class AbstractBankAccount {

    // Hibernate zahtijeva SessionFactory, a JPA EntityManagerFactory
    
    private static final EntityManagerFactory ENTITY_MANAGER_FACTORY = 
            Persistence.createEntityManagerFactory("HibernateBankApplicationJPAPU");
            
            
    // Za apstraktnu klasu AbstractBankAccount, polja accountNumber i amount iz klase
    // BankAccount (extenda ovu klasu) koja se skladište u objekat BankAccount nisu
    // vidljiva pa ih injektujemo pomoću metode getThis() - inverzija naslijeđivanja,
    // odnosno eksplicitna konverzija. Ukoliko imamo više klasa, nije moguće koristiti
    // apstraktnu klasu, neophodan je DAO pattern. Ovaj pattern (active record) koristi
    // se samo za OSNOVNE OPERACIJE
    // Active recor vs abstract active record
    
    public Bankaccount getThis() {
        return (Bankaccount) this;
    }

/*  =================================================================================      
        CRUD OPERACIJE
    =================================================================================    
*/
    public void create() {

        EntityManager entityManager = null;
        try {
            entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();

            entityManager.getTransaction().begin();
            entityManager.persist(this); // U Hibernate-u je 'save'
            entityManager.getTransaction().commit();

        } catch (Exception exception) {
            throw new RuntimeException(exception.getMessage());
        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }
    }
 
    public Bankaccount get() {
        EntityManager entityManager = null;
        try {
            entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();

            // U donjoj liniji koda AccountNumber koristi se u tabeli umjesto ID
            // Umjesto BankAccount.class može se pisati this.getClass()
            Bankaccount bankAccount = (Bankaccount) entityManager.find(Bankaccount.class,
                    getThis().getAccountNumber());
            return bankAccount;

        } catch (Exception exception) {
            throw new RuntimeException(exception.getMessage());
        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }
    }

    public void update() {
        EntityManager entityManager = null;
        try {
            entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();

            entityManager.getTransaction().begin();
            entityManager.merge(this);
            entityManager.getTransaction().commit();

        } catch (Exception exception) {
            throw new RuntimeException(exception.getMessage());
        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }
    }

    public void delete() {
        EntityManager entityManager = null;
        try {
            entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();

            entityManager.getTransaction().begin();
            entityManager.remove(this); // U hibernate-u je 'delete'
            entityManager.getTransaction().commit();

        } catch (Exception exception) {
            throw new RuntimeException(exception.getMessage());
        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }
    }

    public static List<Bankaccount> loadAll() {       
        
        EntityManager entityManager = null;

        try {
            entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();
            
            // Named Query (uobičajeni upiti) -> blok koda ispod ili samo linija 122
            Query query = entityManager.createNamedQuery("Bankaccount.findAll");
            List<Bankaccount> bankAccounts = query.getResultList();
            return bankAccounts;
            
            // return entityManager.createNamedQuery("Bankaccount.findAll").getResultList();

        } catch (Exception exception) {
            throw new RuntimeException(exception.getMessage());
        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }         
    }

/*  =================================================================================      
            LOGIKA ZA PREBACIVANJE SREDSTAVA SA RAČUNA NA RAČUN
    =================================================================================    
*/    
    public static boolean transferMOney(Bankaccount fromAccount, 
            Bankaccount toAccount, double amount) {
        
        if (fromAccount == null || toAccount == null || amount <= 0) {
            return false; // Spriječavanje 'praznih' transakcija
        }
        
        if (fromAccount.getAccountNumber().equals(toAccount.getAccountNumber())) {
            return false; // Spriječavanje prebacivanje novca samom sebi
        }
        
                    EntityManager entityManager = null;
        try {
            entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();

            entityManager.getTransaction().begin();

            if (fromAccount.getAmount() < amount) {
                System.err.println("Nedovoljno sredstava na računu!");
                return false;
            }
            // Trenutni iznos na računima
            double stariIznosFromAccount = fromAccount.getAmount();
            double stariIznosToAccount = toAccount.getAmount();

            fromAccount.setAmount(stariIznosFromAccount - amount);
            toAccount.setAmount(stariIznosToAccount + amount);

            entityManager.merge(fromAccount);
            entityManager.merge(toAccount);

            entityManager.getTransaction().commit();
            return true;

        } catch (Exception exception) {
            throw new RuntimeException(exception.getMessage());
        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }
    }
    
}
