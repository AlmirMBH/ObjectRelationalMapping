package swing;

import first.dao.file.FileDAO;
import first.dao.file.PersistenceStrategy;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import first.dao.PersonDAO;
import first.model.Person;

// OBJAŠNJENJE KLASE
// Sastoji se od 4 polja (ID, ime, prezime, godine) i 2 button-a (add, save)
// AddButton dodaje unos u listu persons i isprazni polja
// SaveButton sve unose iz liste persons snima u persons.txt
public class SwingFrame extends JFrame {

    private JTextField ninField = new JTextField();
    private JTextField nameField = new JTextField();
    private JTextField surnameField = new JTextField();
    private JTextField ageField = new JTextField();
    private JButton addButton = new JButton("Add");
    private JButton saveButton = new JButton("Save");

    private List<Person> persons = new ArrayList<>();

    public SwingFrame() {
        setTitle("Persons");
        // BoxLayout ne može se vezati za cijeli frame, već samo za jedan panel tj. okno
        // sa sadržajem koje se dobija funkcijom  "getContentPane()"
        BoxLayout boxLayout = new BoxLayout(getContentPane(), BoxLayout.Y_AXIS);
        setLayout(boxLayout);

        add(ninField);
        add(nameField);
        add(surnameField);
        add(ageField);

        // AddButtonListener klasa (ispod) implementira ActionListener i sadrži logiku
        // za dodavanje unosa u listu persons - addButton
        AddButtonListener addActionListener = new AddButtonListener();
        addButton.addActionListener(addActionListener);
        add(addButton);

        // SaveButtonListener klasa (ispod) implementira ActionListener i sadrži logiku
        // za snimanje unosa iz liste persons u persons.txt - saveButton
        SaveButtonListener saveButtonListener = new SaveButtonListener();
        saveButton.addActionListener(saveButtonListener);
        add(saveButton);
    }

    public void showFrame() {
        setSize(500, 500);
        pack();
        setVisible(true);
    }

    private class AddButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            // getText() - dohvata unos iz polja aplikacije (za promjene na textu 
            // koristiti DocumentListener)
            String nin = ninField.getText();
            String name = nameField.getText();
            String surname = surnameField.getText();
            int age = Integer.parseInt(ageField.getText());

            // Logika za dodavanje korisničkog unosa u objekat "p" kreiran po šablonu
            // klase Person, a onda u listu "persons"
            //Person p1 = new Person(nin, "Almir", "Mustafic", 40);
            Person p = new Person(nin, name, surname, age);
            persons.add(p);
            ninField.setText(""); // setText(); prazni polja za unos
            nameField.setText("");
            surnameField.setText("");
            ageField.setText("");
        }
    }

    private class SaveButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            // (DAO) Kreira se objekat "dao" po šablonu interface-a DAO koji sadrži  
            // apstraktne metode za snimanje u i čitanje objekata iz persons.txt
            // (PersistenceStrategy) Kreira se objekat po šablonu klase
            // PersistenceStrategy koji prima objekte "dao" (FileDAO, XmlDAO itd.)
            // Poziva se metoda write() za upisivanje u persons.txt iz
            // PersistenceStrategy koja u svom bloku poziva metodu writePersons() iz
            // interface DAO, a ona je definisana u FileDAO klasi koja implementira DAO
            // metodi write() proslijeđuje se lista persons koja je napunjena
            // nakon poziva metode actionPerformed u klasi AddButtonListener
            PersonDAO dao = new FileDAO();
            PersistenceStrategy persistence = new PersistenceStrategy(dao);
            persistence.write(persons);
        }
    }

    //SWING APLIKACIJA može se pokrenuti iz ove klase ili ApplicationStarter klase
    public static void main(String[] args) {
            SwingFrame personFrame = new SwingFrame(); 
        SwingUtilities.invokeLater(personFrame::showFrame);
        }
}
