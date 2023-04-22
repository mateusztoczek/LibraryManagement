package com.codetreatise.bean;
import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
/**
 * @author Mateusz Toczek & Beata Korzeniewska
 * @since 08-01-2023
 */
@Entity(name="Plan")
@Table(name="Plan")
public class Plan {
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private long id;
    @OneToOne(optional=false)
    @JoinColumn(name="User",referencedColumnName="id")
    private  User user;
    private String Monday;
    private String Tuesday;
    private String Wednesday;
    private String Thursday;
    private String Friday;
    private String Saturday;
    
    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }
    public User getUser() {
        return this.user;
    }
    public void setUser(User user) {
         this.user= user;
    }
    public long getIdUser() {
        return this.user.getId();
    }
    public void setIdUser(long id) {
        this.user.setId(id);
    }
    
    public String getNameUser() {
        return this.user.getFirstName();
    }
    
    public String getSurameUser() {
        return this.user.getLastName();
    }
    
    public String getName()
    {
    	return getNameUser()+" "+getSurameUser();
    }
    
    public String getMonday() {
		return Monday;
	}
    public String getTuesday() {
		return Tuesday;
	}
    public String getWednesday() {
		return Wednesday;
	}
    public String getThursday() {
		return Thursday;
	}
    public String getFriday() {
		return Friday;
	}
    public String getSaturday() {
		return Saturday;
	}
 
    
    public void setMonday(String day) {
		 this.Monday=day;
	}
    public void setTuesday(String day) {
		 this.Tuesday=day;
	}
    public void setWednesday(String day) {
		 this.Wednesday=day;
	}
    public void setThursday(String day) {
		 this.Thursday=day;
	}
    public void setFriday(String day) {
		 this.Friday=day;
	}
    public void setSaturday(String day) {
    	this.Saturday=day;
	}

    
}
