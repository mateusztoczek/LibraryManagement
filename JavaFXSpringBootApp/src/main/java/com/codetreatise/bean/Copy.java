
package com.codetreatise.bean;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;



/**
 * @author Mateusz Toczek & Beata Korzeniewska
 * @since 08-01-2023
 */

@Entity (name="Copy")
@Table(name="Copy")
public class Copy {


    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private long id;
    
    @ManyToOne(optional=false)
    @JoinColumn(name="book",referencedColumnName="idBook")
	private Book book;
  
    @ManyToOne(optional=false)
    @JoinColumn(name="library",referencedColumnName="idLibrary")
    
	private Library library;
    private String status;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getIdBook() {
        return this.book.getId();
    }

    public void setIdBook(long idBook) {
        this.book.setId(idBook);
    }

    public long getIdLibrary() {
        return this.library.getId();
    }

    public void setIdLibrary(long idLibrary) {
        this.library.setId(idLibrary);
    }
    
    public String getLibraryName()
    {
    	return this.library.getName();
    }

    public String getStatus() {
        return status;
    }
    public void setStatus(String s) {
        this.status=s;
    }
    public void setBook(Book book)
    {
    	this.book=book;
   	}
    public String getLocationAddress()
    {
    	return this.library.getAddress();
    }

    
    @Override
    public String toString() {
        return "Copy [id=" + id + ", idBook=" + getIdBook() + ", idLibrary=" + getIdLibrary() + ", status=" + status + "]";
    }


}