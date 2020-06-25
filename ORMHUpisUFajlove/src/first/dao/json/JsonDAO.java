package first.dao.json;

import first.dao.DAOException;
import first.dao.PersonDAO;
import first.model.Person;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;


// Java SE nema klasu za snimanje objekata u JSON file, ali se na netu mogu naći
// biblioteke koje su napisane u Javi i moguće ih je integrisati u Javu
public class JsonDAO implements PersonDAO{
    
    private static final String FILENAME = "persons.json";

// ČITANJE OBJEKATA IZ FAJLA
// JSON string -> JSON array -> JSON objekt -> person objekt
// 1. (JSONParser) čitanje JSON formatiranog stringa tj. prebacivanje u JSON array
// 2. Od JSON objekta kreiramo objekat person
    
@Override
public List<Person> readPersons() throws DAOException {        
try(FileReader fileReader = new FileReader(FILENAME)){ // Čitanje sadržaja iz fajla
    JSONParser jSONParser = new JSONParser();
    // Parsiranje sadržaja u JSON array
    JSONArray jSONArray = (JSONArray) jSONParser.parse(fileReader); 
    List<Person> persons = new ArrayList<>();
    
    for(int i = 0; i < jSONArray.size(); i++){ // Enhanced petlja ne može sa array
        JSONObject jSONObject = (JSONObject) jSONArray.get(i);
        Person person = new Person();
        person.setNin((String) jSONObject.get("nin")); 
        person.setName((String) jSONObject.get("name"));// Kastanje ili toString();
        person.setSurname(jSONObject.get("surname").toString()); 
        person.setAge(Integer.parseInt(jSONObject.get("age").toString()));
        persons.add(person);
        }
    return persons;        
    
        }catch(Exception exception){
            throw new RuntimeException(exception.getMessage());
        }
}

    
    
// SNIMANJE OBJEKATA U FAJL 
// Objekti -> JSON objekti -> JSON array -> JSON string
// 1. Podaci tranzijentnog person objekta vežu se za JSON objekat, zatim se dodaju
// u JSONArray, a nakon toga se array konvertuje u JSONstring
// 2. JSON skladišti polja u formatu ključ=>vrijednost tj. kao mapa
// 3. Sa neta se skine i importuje "json-simple-1.1.jar". 
// 4. Kada imamo listu objekata (persons) koje trebamo mapirati u listu JSON objekata,
// koristi se JSONArray klasa iz "json-simple-1.1.jar" koja može primiti listu JSON objekata

    @Override
    public void writePersons(List<Person> persons) throws DAOException {
        if(persons == null || persons.isEmpty()){
            return;
        }    
    JSONArray jSONArray = new JSONArray();
    
    for(Person person : persons){            
     JSONObject jsonObject = new JSONObject();
     
     jsonObject.put("nin", person.getNin()); // Key (npr. 'nin') je 'proizvoljan', a iz 
     jsonObject.put("name", person.getName()); // person objekta uzimamo vrijednosti
     jsonObject.put("surname", person.getSurname());// 123, John, Doe, 99
     jsonObject.put("age", person.getAge());         
     jSONArray.add(jsonObject);
        }
        
// 1. Konverzija JSONArray objekta u string tj. JSON formatirani tekst (JSONArray klasa)
// (vitičaste zagrade, dvotačka, zarez i sl.)
// 2. metoda write iz paketa Writer;
// naslijeđivanje: FileWriter -> OutputStream -> Writer
        String formatiraniJSONString = jSONArray.toJSONString();
    try(FileWriter out = new FileWriter(FILENAME)){
        out.write(formatiraniJSONString);
    }catch(Exception exception){
        throw new RuntimeException(exception.getMessage());
    }
    }
}

