package be.sdlg.webapps.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="address")
public class Address {
	
	protected Long id;
	protected String street;
	protected String number;
	protected PostalCode postalCode;
	protected Adressable adressable;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public Long getId() {
		return id;
	}
	@ManyToOne
	public Adressable getAdressable() {
		return adressable;
	}
	public void setAdressable(Adressable adressable) {
		this.adressable = adressable;
	}
	public void setId(Long id) {
		this.id = id;
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
	@ManyToOne
	public PostalCode getPostalCode() {
		return postalCode;
	}
	public void setPostalCode(PostalCode postalCode) {
		this.postalCode = postalCode;
	}
	
}
