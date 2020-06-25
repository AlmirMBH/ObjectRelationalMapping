package first.dao.xml;

import first.dao.DAOException;
import first.model.Person;
import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.List;
import first.dao.PersonDAO;


/* 
==============================================
    ČITANJE OBJEKATA IZ FAJL-A
==============================================
    1. (XMLDecoder) konverzija objekta iz fajla u input stream
    2. (FileInputStream) konverzija objekta iz input stream-a u objekte
    3. (readObject()) metoda iz ObjectInputStream za izlistavanje objekata u konzoli i sl.
    4. (Linija 29) neophodna explicitna konverzija objekata u listu objekata tipa Person
    
*/
public class XmlDAO implements PersonDAO{

    private static String FILENAME = "persons.xml";
    @Override
    public List<Person> readPersons() throws DAOException {
        try(XMLDecoder xmlDecoder = new XMLDecoder(new FileInputStream(FILENAME))){
            List<Person> persons = (List<Person>) xmlDecoder.readObject();
            return persons;
        }catch(Exception exception){
            throw new DAOException(exception.getMessage());
        }
    }
/* 
==============================================
    SNIMANJE OBJEKATA IZ LISTE PERSONS U FAJL
==============================================
1. (XMLEncoder) klasa koja objekte prebacuje u "output stream" koristeći XML encoding.
    Extenda Encoder. 
2. (FileOutputStream) konvertuje objekte u FileOutputStream i kreira ili specificira 
    fajl u koji će se objekti snimiti.
3. (writeObject()) iz klase ObjectOutputStream snima objekte (moraju biti serializable) u fajlove
   (primitivni tipovi podataka i grafovi java objekata). 
     */
    @Override
    public void writePersons(List<Person> persons) throws DAOException {
        if(persons == null || persons.isEmpty()){
            return;
        }
        try(XMLEncoder xmlEncoder = new XMLEncoder(new FileOutputStream(FILENAME))){
            xmlEncoder.writeObject(persons);
        }catch(Exception exception){
            throw new DAOException(exception.getMessage());
        }
    }
    
    
    
}
