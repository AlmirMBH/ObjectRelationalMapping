package first.dao;

import first.model.Person;
import java.util.List;

public interface PersonDAO {
    // Metoda za čitanje i ispisivanje liste trajno snimljenih osoba: list of persons
    public List<Person> readPersons() throws DAOException; // Funkcija, metoda, konstruktor može ispisati exception
    
    // Metoda za snimanje liste osoba u bazu
    public void writePersons(List<Person> persons) throws DAOException;    
        
}
