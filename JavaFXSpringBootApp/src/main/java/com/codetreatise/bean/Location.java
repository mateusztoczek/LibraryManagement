
package com.codetreatise.bean;

        import javax.persistence.Column;
        import javax.persistence.Entity;
        import javax.persistence.GeneratedValue;
        import javax.persistence.GenerationType;
        import javax.persistence.Id;
        import javax.persistence.Table;


@Entity (name="Location")
@Table(name="Location")
public class Location {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "idLocation", updatable = false, nullable = false)
    private long idLocation;
    private String street;
    private String number;
    private int apartment;
    private String city;
    private String postCode;


    public long getId() {
        return idLocation;
    }

    public void setId(long idLocation) {
        this.idLocation = idLocation;
    }

    public String getStreet() {
        return street;
    }


    public void setStreet(String street) {
        this.street = street;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public int getApartment() {
        return apartment;
    }

    public void setApartment(int apartment) {
        this.apartment = apartment;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }
    
    public String getAddress() {
    	if (apartment==0) {
    		return " " + street + " " + number + " ,"+  city + "  "+postCode;
    	}
    	else
    	{
    		return " " + street + " " + number + "/" + apartment + ", "
                    + city + "   "+postCode;
    	}
        
    }

    @Override
    public String toString() {
        return "Location [id=" + idLocation + ", street=" + street + ", number=" + number + ", apartment=" + apartment + ", city="
                + city + ", postCode "+postCode+" ]";
    }


}
