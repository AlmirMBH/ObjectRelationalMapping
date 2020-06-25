package app1;

import first.dao.file.FileDAO;
import first.dao.file.PersistenceStrategy;
import first.dao.serializable.SerializableDAO;
import first.dao.xml.XmlDAO;
import first.model.Person;
import swing.SwingFrame;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.swing.SwingUtilities;
import first.dao.PersonDAO;
import first.dao.json.JsonDAO;

public class ApplicationStarter {

    public static void main(String[] args) {
// OBJAŠNJENJE APLIKACIJE I KLASA       
// Tranzijentno -> Perzistentno stanje (persons.txt)
// Konzolna aplikacija (KONZOLA) koristi PersistenceStrategy kako bi snimala/čitala 
// persons objekte u File/XML/JSon/Serial
// Interface DAO sadrži apstraktne metode za snimanje/čitanje objekata;
// Konkretne metode nalaze se u File/XML/JSon/SerialDAO, 
// za snimanje u/čitanje iz persons.txt itd. koristi ih PeristenceStrategy preko DAO 
// npr. DAO dao = new FileDAO(); ili DAO dao = new XmlDAO(); i sl.
// Koristiti jedan ili drugi blok koda ispod (čitanje iz ili snimanje u persons.txt)

        // Za čitanje fajla kreira se prvo objekat DAO, koji implementira DAO, a onda ga
        // proslijeđujemo u PersistenceStrategy objekat koji ima metodu za čitanje 
        // persons "read()" iz fajla persons.txt
        
        // ČITANJE IZ BAZE (persons.txt, persons.dat)
         PersonDAO dao = new FileDAO();
        // PersonDAO dao = new SerializableDAO();
        // PersonDAO dao = new XmlDAO();        
         //PersonDAO dao = new JsonDAO();//new XmlDAO();
         
         PersistenceStrategy persistenceStrategy = new PersistenceStrategy(dao);
         List<Person> readedPersons = persistenceStrategy.read();
         for (Person person : readedPersons) {
            System.out.println(person);
         }
        
//        ALTERNATIVNE FOREACH PETLJE
//        readedPersons.forEach(System.out::println);
//        readedPersons.stream().forEach(System.out::println);
//        readedPersons.forEach((person) -> {
//            System.out.println(person);
//        });
        
/*==================================================        
        // SNIMANJE U BAZU PODATAKA (persons.txt)
====================================================        */
        // PersonDAO dao = new FileDAO();
        // PersonDAO dao = new SerializableDAO();  
        // PersonDAO dao = new XmlDAO();
        //   PersonDAO dao = new JsonDAO();
        
//        PersistenceStrategy persistence = new PersistenceStrategy(dao);
//        Person p1 = new Person("1", "Elmedin", "Mustafic", 40);
//        Person p2 = new Person("2", "Samer", "Sacic", 30);
//        Person p3 = new Person("3", "Nadira", "Puskar", 40);         
//        List<Person> persons = Arrays.asList(p1, p2, p3);
//        persistence.write(persons);
//      
// ZA XML koristiti ovaj dio koda
//      List<Person> persons = new ArrayList<>( Arrays.asList(p1, p2, p3));        
//      persistence.write(persons);

// (XMLDAO) XmlEncoder ZAHTIJEVA eksplicitnu konverziju jer ne može upisivati 
// "Arrays.asList()" u XML fajl, a ne podržava ni "remove()" npr. "remove(0)";
// privatna je klasa unutar "ArrayList" klase
//===================================================================
        

// DODAVANJE OSOBA PREKO ARRAYLIST
//        List<Person> persons = new ArrayList<>();
//        persons.add(p1);
//        persons.add(p2);
//        persons.add(p3);
    }
}


//        Swing, FX itd. može se pokretati i iz ove klase
//        SwingFrame personFrame = new SwingFrame(); 
//        SwingUtilities.invokeLater(personFrame::showFrame);
