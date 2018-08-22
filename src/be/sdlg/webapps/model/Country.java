package be.sdlg.webapps.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="country")
public class Country {
	Long Id;
	String shortCode;
	String countryName;
	protected Set<PostalCode> postalCodeList;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public Long getId() {
		return Id;
	}
	public void setId(Long id) {
		Id = id;
	}
	public String getShortCode() {
		return shortCode;
	}
	public void setShortCode(String shortCode) {
		this.shortCode = shortCode;
	}
	public String getCountryName() {
		return countryName;
	}
	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}
	@OneToMany(cascade = CascadeType.ALL, mappedBy="country", fetch=FetchType.LAZY) // Cascade
	public Set<PostalCode> getPostalCodeList() {
		return postalCodeList;
	}
	public void setPostalCodeList(Set<PostalCode> postalCodeList) {
		this.postalCodeList = postalCodeList;
	}

}
