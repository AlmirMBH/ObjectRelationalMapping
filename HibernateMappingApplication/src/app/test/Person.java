package app.test;

// Kreiranje najmanje 2 objekta po šablonu ove klase pokazuje kojim redoslijedom se 
// izvršava kod, a takođe i koliko puta se koji blok koda izvršava (statički samo jednom
// nestatički svaki put kad se kreira objekat)
public class Person {    

    {
        System.out.println("Nestatički blok koda unutar klase Person");
    }
    
    static {
        System.out.println("Statički blok koda unutar klase Person");
    }

    public Person() {
        System.out.println("Poziv konstruktora Person");
    }
}
