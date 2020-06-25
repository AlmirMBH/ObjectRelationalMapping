package first.dao.file;

import first.dao.DAOException;
import first.model.Person;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import first.dao.PersonDAO;

//STOPPED AT 1:47

public class FileDAO implements PersonDAO{

    private static final String FILENAME = "persons.txt";
    
    @Override
    // Izlistavanje osoba iz fajla/baze
    public List<Person> readPersons() throws DAOException {        
    
    // (BufferedReader) Čita tekst iz karakter input strima i omogućava čitanje
    // karaktera, nizova i linija. Obično se koristi kao omotač 'skupih' čitača
    // kao što su Filereader i InputStreamReader
    // (FileReader) Čitač karaktera iz fajlova i predviđen je za čitanje strimova
    // karaktera. Za čitanje strimova 'sirovih' bajta može poslužiti FileInputStream
    // FileReader ekstenda InputStreamReader
    
    List<Person> persons = new ArrayList<>();
        try(BufferedReader reader = new BufferedReader(new FileReader(FILENAME))){
            String line = null;
            while((line = reader.readLine()) != null){
                
    // Opcija za dohvatanje i razdvajanje podataka iz baze
    // String[] fields = line.split(";");
    
    StringTokenizer stringTokenizer = new StringTokenizer(line, ";");
    
    // (StringTokenizer) razdvaja stringove na tokene. Ne razlikuje identifikatore,
    // brojeve, stringove sa navodnicima, ne razlikuje i ne preskače komentare
    // Kreira 'pod-stringove' od unosa koji razdvaja. Preporučuje se split() umjesto tokenizer-a
    
    String nin = stringTokenizer.nextToken(); // nextToken() dohvata naredni token
    String name = stringTokenizer.nextToken();
    String surname = stringTokenizer.nextToken();    
    int age = Integer.parseInt(stringTokenizer.nextToken());
    Person person = new Person(nin, name, surname, age);
    persons.add(person);    
                
            }
        }catch(Exception exception){
            throw new DAOException(exception.getMessage());
        }
        
        return persons;
    }

    @Override
    // Snimanje osoba u fajl/bazu
    public void writePersons(List<Person> persons) throws DAOException {
       if(persons == null || persons.isEmpty()){
           return;
       } 
       
       // (PrintWriter) Objekti -> tekst; (FileWriter) text -> file/baza
       
       try(PrintWriter writer = new PrintWriter(new FileWriter(FILENAME, true))){
           for(Person person : persons){
               
       // (println) printa string i terminira liniju
               
               writer.println(person.getNin()+";"+person.getName()+";"+
                       person.getSurname()+";"+person.getAge());
           }
       }catch(Exception exception){
           throw new DAOException(exception.getMessage());
       }
    }
    
}
