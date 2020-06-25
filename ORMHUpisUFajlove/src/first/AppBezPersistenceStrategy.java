package first;

import first.dao.DAOException;
import first.dao.PersonDAO;
import first.dao.file.FileDAO;
import first.dao.file.PersistenceStrategy;
import first.dao.json.JsonDAO;
import first.dao.serializable.SerializableDAO;
import first.dao.xml.XmlDAO;
import first.model.Person;
import java.util.List;

class AppBezPersistenceStrategy{
    public static void main(String[] args) throws DAOException {
 // PersonDAO dao = new JsonDAO();
   //       PersonDAO dao = new XmlDAO();
        PersonDAO dao = new SerializableDAO();
         List<Person> readedPersons = dao.readPersons();
         for (Person person : readedPersons) {
            System.out.println(person);
         }

    
    }
}
    
