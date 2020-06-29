package model;

import org.hibernate.Session;

public class Main {

    public static void main(String[] args) {
        
     Session session = HibernateUtil.getSessionFactory().openSession();
     
     
     session.getTransaction().begin();
     
     Manufacturer manufacturer = new Manufacturer();          
     manufacturer.name = "Mercedes";     
     
     Manufacturer manufacturer1 = new Manufacturer();
     manufacturer1.name = "Audi";     
     
     Manufacturer manufacturer2 = new Manufacturer();
     manufacturer2.name = "Peugeot";     
     
     session.save(manufacturer);
     session.save(manufacturer1);
     session.save(manufacturer2);
     
     Car car = new Car();
     car.model = "SLK";
     car.color = "Black";
     car.price = 200000;
     car.manufacturer = manufacturer;
     
     session.save(car);
     
     session.getTransaction().commit();
     session.close();
        System.exit(0);
    } 
    
    
}
