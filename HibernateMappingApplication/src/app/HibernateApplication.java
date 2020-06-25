package app;

// HIBERNATE je skup java klasa

import app.test.Person;

// KONFIGURACIJA UKRATKO
// 1. Dodati Library Hibernate 
// 2. Hibernate Configuration Wizzard (hibernate.cfg.xml) na Source Packages (dodaje sql driver)
// 3. novi paket app.util -> Hibernate -> HibernateUtil.java kreira klasu -> 
// u njoj kreiramo objekte Configuration, ServiceRegistriSessionFactory, SessionFactory
// 4. u app.model -> Hibernate -> Hibernate Reverse Engineering Wizzard -> dodati željene
// tabele -> hibernate.reveng.xml (specificiramo da se koristi reverse engineering)
// 5. Mapiranje actor tabele u klasu Actor (app.model paket) -> Hibernate ->
// Hibernate Mapping Files and POJO from Database
// CRUDE operacije preko Hibernate

// POSTUPAK
// Libraries -> add library Hibernate 4.3.x(JPA2.1)
// Na Source Packages -> Hibernate -> Hibernate Configuration Wizzard 
// -> hibernate.cfg.xml (xml se dodaje automatski i sadrži podatke o dijalektu i bazi) -> 
// odabrati željenu bazu ili npr. MySQL Driver -> odabrati dostupne baze (driver se dodaje
//  automatski) kreirati paket util, a onda preko Hibernate->HibernateUtil kreirati klasu
// HibernateUtil.java (autogenerisana). U njoj kreirati objekte Configuration (koristi 
// konfiguracione parametre xml-a) i ServiceRegistriSessionFactory neophodne za 
// SessionFactory
// U app.model paket dodati klasu preko Hibernate Reverse Engineering Wizzard -> 
// hibernate.reveng.xml (automatski) (info o dodanoj tabeli će biti u hibernate.reveng.xml)
// Mapiranje actor tabele u klasu Actor (app.model paket) -> Hibernate ->
// Hibernate Mapping Files and POJO from Database

public class HibernateApplication {
    
    public static void main(String[] args) {
        
        Person personObject = new Person();
        Person personObject1 = new Person();
        
    }    
}
