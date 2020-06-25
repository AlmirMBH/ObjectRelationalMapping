package first.model;

import java.io.Serializable;

// Da bi se objekti po šablonu klase "Person" serijalizovali, klasa mora implementirati
// interface "Serializable" koji nema metoda.
// DA BI OBJEKAT BIO SERIJALIZOVAN, NJEGOVA KLASA MORA IMATI PRAZAN KONSTRUKTOR, SETERE
// I GETERE, MORA BITI PUBLIC I IMPLEMENTIRATI SERIALIZABLE - JAVA BEAN KONVENCIJA
// LISTA KOJA SE UPISUJE U FAJL MORA BITI IMPLEMENTACIJA SERIALIZABLE PUBLIC KLASE
// Java Bean je public klasa koja implementira Serializable interface
// Objekti se serijalizuju kada se upisuju u binarnu strukturu ili kada se šalju preko
// mreže
public class Person implements Serializable{
    
    private String nin;
    private String name;
    private String surname; // UKOLIKO NE ŽELIMO DA SE IME UPISUJE U BAZU
    private int age;                  // DODAJEMO RIJEČ "TRANSIENT"

    public Person() {
    }

    public Person(String nin, String name, String surname, int age) {
        this.nin = nin;
        this.name = name;
        this.surname = surname;
        this.age = age;
    }

    public String getNin() {
        return nin;
    }

    public void setNin(String nin) {
        this.nin = nin;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return nin + " " + name + " " + surname + " " + age;
    }
    
    
}
