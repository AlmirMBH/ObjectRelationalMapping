
package bank;

import bank.model.AbstractBankAccount;
import bank.model.BankAccount;
import hibernate.HibernateUtil;
import java.util.List;
import org.hibernate.Session;


public class BankApplication {

    public static void main(String[] args) {
        //ACTIVE RECORD PATTERN (metode u apstraktnoj klasi)
//        BankAccount bankAccount = new BankAccount("3380000000000", 10000);        
//        bankAccount.save();
        
        BankAccount bankAccount = new BankAccount();
        // loadAll() je statička funkcija (AbstractBankAccount klasa)
        List<BankAccount> bankAccounts = BankAccount.loadAll();
        bankAccounts.forEach(System.out::println);
        
        
        System.exit(0);
        
        // BEZ active record pattern-a
        // Session session = HibernateUtil.getSessionFactory().openSession();
        // session.save(bankAccount);
            
        }        
    }
    

