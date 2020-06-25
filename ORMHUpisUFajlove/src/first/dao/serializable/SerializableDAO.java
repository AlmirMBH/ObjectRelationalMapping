package first.dao.serializable;

import first.dao.DAOException;
import first.model.Person;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import first.dao.PersonDAO;

public class SerializableDAO implements PersonDAO {

    private static final String FILENAME = "persons.dat";
/* 
==============================================
    ČITANJE OBJEKATA IZ FAJL-A
==============================================
    1. (ObjectInputStream) konverzija objekta iz fajla u input stream
    2. (FileInputStream) konverzija objekta iz input stream-a u objekte
    3. (readObject()) metoda iz ObjectInputStream za izlistavanje objekata u konzoli i sl.
    4. (Linija 29) neophodna explicitna konverzija objekata u listu objekata tipa Person
    
*/
    @Override
    public List<Person> readPersons() throws DAOException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILENAME))) {
            List<Person> persons = (List<Person>) ois.readObject();
            return persons;
        } catch (Exception exception) {
            throw new DAOException(exception.getMessage());
        }
    }

    /* 
==============================================
    SNIMANJE OBJEKATA IZ LISTE PERSONS U FAJL
==============================================
1. (ObjectOutputStream) klasa koja objekte prebacuje u "output stream". Extenda OutputStream. 
2. (FileOutputStream) konvertuje objekte u FileOutputStream i kreira ili specificira 
    fajl u koji će se objekti snimiti.
3. (writeObject()) iz klase ObjectOutputStream snima objekte (moraju biti serializable) u fajlove
   (primitivni tipovi podataka i grafovi java objekata). 
     */
    @Override
    public void writePersons(List<Person> persons) throws DAOException {
        if (persons == null || persons.isEmpty()) {
            return;
        }
        try (ObjectOutputStream ous = new ObjectOutputStream(new FileOutputStream(FILENAME, true))) {
            ous.writeObject(persons);
        } catch (Exception exception) {
            throw new DAOException(exception.getMessage());
        }
    }
}
