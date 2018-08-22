package be.sdlg.webapps.model;

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
@Table(name="postal_code")
public class PostalCode implements Comparable {
	protected Long id;
	protected Integer zipCode;
	protected String cityName;
	protected Set<Address> adresses;
	protected Country country;
	
	
	@Override
	public int compareTo(Object o) {
		if (this.getZipCode()> ((PostalCode) o).getZipCode()) return 1;
		else if (this.getZipCode()< ((PostalCode) o).getZipCode()) return -1;
		return 0;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Integer getZipCode() {
		return zipCode;
	}
	public void setZipCode(Integer zipCode) {
		this.zipCode = zipCode;
	}
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	@OneToMany(cascade = CascadeType.ALL, mappedBy="postalCode", fetch=FetchType.LAZY) // Cascade
	public Set<Address> getAdresses() {
		return adresses;
	}
	public void setAdresses(Set<Address> adresses) {
		this.adresses = adresses;
	}
	@ManyToOne
	public Country getCountry() {
		return country;
	}
	public void setCountry(Country country) {
		this.country = country;
	}
	
	
	
	

}
