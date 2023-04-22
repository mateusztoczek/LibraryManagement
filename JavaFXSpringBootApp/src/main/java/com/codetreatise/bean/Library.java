
package com.codetreatise.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * @author Mateusz Toczek & Beata Korzeniewska
 * @since 08-01-2023
 */

@Entity (name="Library")
@Table(name="Library")
public class Library {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "idLibrary", updatable = false, nullable = false)
    private long idLibrary;
    private String name;
    private String telephone;
    
    @OneToOne(optional=false)
    @JoinColumn(name="User",referencedColumnName="id")
    private User owner;
    
    @OneToOne(optional=false)
    @JoinColumn(name="location",referencedColumnName="idLocation")
    private Location location;
    
    private long numEmployees;

    public long getId() {
        return idLibrary;
    }

    public void setId(long id) {
        this.idLibrary = id;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getIdLocation() {
        return this.location.getId();
    }
    public Location getLocation() {
        return this.location;
    }
    
    public void setLocation(Location location) {
         this.location=location;
    }

    public void setIdLoctaion(long IdLocation) {
        this.location.setId(IdLocation);
    }

    public long getOwner() {
        return this.owner.getId();
    }
    public void setOwner(User user) {
        this.owner=user;
    }
    public String getOwnerName() {
        return this.owner.getName();
    }


    public void setOwnerId(long idOwner) {
        this.owner.setId(idOwner);
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public long getNumEmployees() {
        return numEmployees;
    }

    public void setNum_employees(long numEmployees) {
        this.numEmployees = numEmployees;
    }
    
    public String getAddress()
    {
    	return this.location.getAddress();
    }

    @Override
    public String toString() {
        return "User [id=" + idLibrary +", name=" + name  + ", idLoctaionidLoctaion=" + getIdLocation() + ", idOwner=" + getOwner() + ", telephone=" + telephone + ", num_employees="
                + numEmployees + "]";
    }


}