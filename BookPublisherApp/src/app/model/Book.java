package app.model;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class Book  implements java.io.Serializable {

     private Integer id;
     private Publisher publisher;
     private String title;
     private int pages;
     private Set<Author> authors = new HashSet<>(0);

    public Book() {
    }
	
    public Book(Publisher publisher, String title, int pages) {
        this.publisher = publisher;
        this.title = title;
        this.pages = pages;
    }
    
    public Book(Publisher publisher, String title, int pages, Set authors) {
       this.publisher = publisher;
       this.title = title;
       this.pages = pages;
       this.authors = authors;
    }
   
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    
    public Publisher getPublisher() {
        return this.publisher;
    }
    
    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }
    
    public String getTitle() {
        return this.title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    public int getPages() {
        return this.pages;
    }
    
    public void setPages(int pages) {
        this.pages = pages;
    }
    
    public Set<Author> getAuthors() {
        return this.authors;
    }

    public void setAuthors(Set<Author> authors) {
        this.authors = authors;
    }

    @Override
    public String toString() {
        //return  " " + title + ": " + pages + " stranica";
        
        StringBuilder sb = new StringBuilder();
        sb.append(title);
        sb.append(System.lineSeparator());
        sb.append(publisher.getName());
        sb.append(System.lineSeparator());
        String authorsText = authors.stream()
                            .map(Author::getName)
                            .collect(Collectors.joining("; "));
        sb.append(authorsText);
        sb.append(System.lineSeparator());
        return sb.toString();
    }
}


