package fx;

import first.dao.PersonDAO;
import first.dao.file.FileDAO;
import first.dao.file.PersistenceStrategy;
import first.dao.json.JsonDAO;
import first.model.Person;
import java.util.ArrayList;
import java.util.List;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;


// 1. FX aplikacije ne moraju imati main funkciju, one preuzimaju "start" funkciju via launch()
// 2. Umjesto frame (swing) koristi se stage (fx)
public class FXWindow extends Application{    
    
    private final List<Person> personList = new ArrayList<>();
    
    private TextField ninTextField;
    private TextField nameTextField;
    private TextField surnameTextField;
    private TextField ageTextField;

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Person Window");
        
        //AddEventHandler addEventHandler = new AddEventHandler();
        //addButton.setOnAction(addEventHandler); // setOnAction() - dodavanje button-a
        
        GridPane gridPane = new GridPane(); // Kontejner 
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(10);
        gridPane.setVgap(10);        
        Insets padding = new Insets(25, 25, 25, 25);
        gridPane.setPadding(padding);
        // gridPane.add(addButton, 0, 0);      
        
        Label ninLabel = new Label("National Identification Number:");
        gridPane.add(ninLabel, 0, 0);        
        ninTextField = new TextField();
        gridPane.add(ninTextField, 1, 0);
        
        Label nameLabel = new Label("Ime Osobe:");
        gridPane.add(nameLabel, 0, 1);
        nameTextField = new TextField();
        gridPane.add(nameTextField, 1, 1);
        
        Label surnameLabel = new Label("Prezime Osobe:");
        gridPane.add(surnameLabel, 0, 2);
        surnameTextField = new TextField();
        gridPane.add(surnameTextField, 1, 2);
        
        Label ageLabel = new Label("Starost Osobe:");
        gridPane.add(ageLabel, 0, 3);
        ageTextField = new TextField();
        gridPane.add(ageTextField, 1, 3);
        
        Button addPersonButton = new Button("Dodaj osobu");
        AddEventHandler addEventHandler = new AddEventHandler();
        addPersonButton.setOnAction(addEventHandler);
        gridPane.add(addPersonButton, 1, 4);
        
        Button savePersonButton = new Button("Snimi osobu");
        SaveEventHandler saveEventHandler = new SaveEventHandler();
        savePersonButton.setOnAction(saveEventHandler);                
        gridPane.add(savePersonButton, 1, 5);
        
        Scene scene =  new Scene(gridPane);//new Scene(gridPane, 300, 250);
        stage.setScene(scene);
        stage.show();

    }    
    // EventHandler je zamjena za implement ActionListener (swing)
    private class AddEventHandler implements EventHandler<ActionEvent>{ 

        @Override
        public void handle(ActionEvent event) { // Zamjena za ActionPerformed
            Person person = new Person(ninTextField.getText(), nameTextField.getText(),
                    surnameTextField.getText(), Integer.parseInt(ageTextField.getText()));
            personList.add(person);
            ninTextField.clear();
            nameTextField.clear();
            surnameTextField.clear();
            ageTextField.clear();
        }        
    }
    
    private class SaveEventHandler implements EventHandler<ActionEvent>{

        @Override
        public void handle(ActionEvent event) { // Zamjena za ActionPerformed
            PersonDAO dao = new JsonDAO(); // new XmlDAO();
            PersistenceStrategy persistenceStrategy = new PersistenceStrategy(dao);
            for(Person person : personList){
                persistenceStrategy.write(personList);
            }
        }        
    }
    
    
    public static void main(String[] args) {
        launch(args);
        
    }
}
