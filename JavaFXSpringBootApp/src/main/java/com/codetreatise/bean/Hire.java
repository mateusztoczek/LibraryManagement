
package com.codetreatise.bean;

import java.time.LocalDate;
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

@Entity(name="Hire")
@Table(name="Hire")
public class Hire {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private long id;
    @ManyToOne(optional=false)
    @JoinColumn(name="Copy",referencedColumnName="id")
    private Copy copy;
    private LocalDate dateHire;
    private LocalDate dateBack;
    @ManyToOne(optional=false)
    @JoinColumn(name="Reader",referencedColumnName="id")
    private Reader reader;
    private long idReader;
    
    private int penalty;
    
    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LocalDate getDateHire() {
        return LocalDate.now();
    }

    public void setDateHire(LocalDate dateHire) {
        this.dateHire = LocalDate.now();
    }

    public LocalDate getDateBack() {
        return LocalDate.now();
    }

    public void setDateBack(LocalDate dateBack) {
        this.dateBack = LocalDate.now();
    }

    public long getIdReader() {
        return this.reader.getId();
    }

    public void setIdReader(long idReader) {
        this.reader.setId(idReader);
    }
    
    public long getIdCopy() {
        return this.copy.getId();
    }

    public void setIdCopy(long idCopy) {
         this.copy.setId(idCopy);
    }

    public int getPenalty() {
        return penalty;
    }

    public void setPenalty(int penalty) {
        this.penalty = penalty;
    }

    public Reader getReader() {
        return reader;
    }

    public void setReader(Reader reader) {
        this.reader = reader;
    }

    public Copy getCopy() {
        return copy;
    }

    public void setCopy(Copy copy) {
        this.copy = copy;
    }
    public long getIdBook() {
    	return this.copy.getIdBook();
    }
    

    public void setIdBook(long idBook) {
    	 this.copy.setIdBook(idBook);
    }

    @Override
    public String toString() {
        return "Hire [id=" + id + ", idCopy=" + getIdCopy() + ", dateHire=" + dateHire + ", dateBack=" + dateBack + ", idReader=" + getIdReader() + ", penalty="
                + penalty + "]";
    }


}
