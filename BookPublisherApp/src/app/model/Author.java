package app.model;

import java.util.HashSet;
import java.util.Set;

public class Author  implements java.io.Serializable {

     private Integer id;
     private String name;
     private Set<Book> books = new HashSet<>(0);

    public Author() {
    }
	
    public Author(String name) {
        this.name = name;
    }
    
    public Author(String name, Set books) {
       this.name = name;
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
    
    public Set<Book> getBooks() {
        return this.books;
    }
    
    public void setBooks(Set<Book> books) {
        this.books = books;
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(name);
        sb.append("::");
        for(Book book : books){
            sb.append(book);
            sb.append(",");
        }
        return sb.toString();
    }
}


