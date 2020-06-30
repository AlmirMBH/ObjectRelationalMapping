package app.model;

import java.util.HashSet;
import java.util.Set;

public class Publisher  implements java.io.Serializable {

     private Integer id;
     private String name;
     private String address;
     private Set books = new HashSet(0);

    public Publisher() {
    }
	
    public Publisher(String name, String address) {
        this.name = name;
        this.address = address;
    }
    
    public Publisher(String name, String address, Set books) {
       this.name = name;
       this.address = address;
       this.books = books;
    }
   
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    
    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getAddress() {
        return this.address;
    }
    
    public void setAddress(String address) {
        this.address = address;
    }
    
    public Set getBooks() {
        return this.books;
    }
    
    public void setBooks(Set books) {
        this.books = books;
    }




}


