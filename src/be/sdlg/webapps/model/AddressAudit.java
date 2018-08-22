package be.sdlg.webapps.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@NamedQueries({
	@NamedQuery(
	name = "findAdressAuditByMemberId",
	query = "from AddressAudit aa where aa.addressId = :addressId"
	)
})
@Entity
@Table(name="address_audit")
public class AddressAudit {
	protected Long id;
	protected Long addressId;
	protected String street;
	protected String number;
	protected Long postalCodeId;
	
	protected Date changeTime;
	protected String changedBy;
	protected String reason;
	protected String zip;
	protected String city;
	protected String countryCode;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public Long getId() {
		return id;
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

	public void setId(Long id) {
		this.id = id;
	}
	
	public Long getPostalCodeId() {
		return postalCodeId;
	}

	public void setPostalCodeId(Long postalCodeId) {
		this.postalCodeId = postalCodeId;
	}
	
	@Lob
	@Column( length = 100000 )
	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	public Date getChangeTime() {
		return changeTime;
	}

	public void setChangeTime(Date changeTime) {
		this.changeTime = changeTime;
	}

	
	public String getChangedBy() {
		return changedBy;
	}

	public void setChangedBy(String changedBy) {
		this.changedBy = changedBy;
	}

	public Long getAddressId() {
		return addressId;
	}

	public void setAddressId(Long addressId) {
		this.addressId = addressId;
	}

	public void init(Address a, String reason, String username) {
		this.setStreet(a.getStreet());
		this.setNumber(a.getNumber());
		PostalCode pc = a.getPostalCode();
		if (pc != null && pc.getId() != null)  
			this.setPostalCodeId(pc.getId());
		this.setReason(reason);
		this.setChangedBy(username);
		this.setChangeTime(new Date());
		this.setAddressId(a.getId());
		if (a.getPostalCode() != null) {
			this.setZip(a.getPostalCode().getZipCode().toString());
			this.setCity(a.getPostalCode().getCityName());
			if (a.getPostalCode().getAdresses() !=null)
			this.setCountryCode(a.getPostalCode().getCountry().getShortCode());
		}
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}
	

	

}
