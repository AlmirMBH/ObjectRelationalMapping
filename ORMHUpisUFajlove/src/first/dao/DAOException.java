package first.dao;

public class DAOException extends Exception{
    
    public DAOException(String message){
        super("DAO izuzetak: " + message); // super - poziv konstruktora zbog ispisa poruke
    }

    
    
}
