package be.sdlg.webapps.model;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Entity
@Table(name="membre")
public class Membre extends Adressable {
	
	public static final int YES = 1;
	public static final int NO = 0;
	
	public static final int AGRO = 1;
	public static final int BIOCH = 2;
	public static final int CHIM = 3;
	public static final int CONS = 4;
	public static final int ELEC = 5;
	public static final int ELEL = 6;
	public static final int ELME = 7;
	public static final int MECA = 8;
	
	public static final int CHARLEROI = 1;
	public static final int TOURNAI = 2;
	public static final int ATH = 3;
	
	
	public static final int AIIH_MEMBRE = 1;
	public static final int AIIH_PRESIDENT = 2;
	public static final int AIIH_SECRETAIRE = 3;
	public static final int AIIH_TRESORIER = 4;
	public static final int AIIH_VP = 5;
	public static final int AIIH_ADMINISTRATEUR = 6;
	public static final int AIIH_EXCOMMUNIE = 7;
	public static final int AIIH_UNSUBSCRIBED = 8;
	
	protected Set<Email> emails;
	protected Set<EntrepriseMember> enterpriseMember;
	protected Long matricule; 
	protected Long year;
	protected String speciality;
	protected Long shiftCourse;
	protected String lastname;
	protected String firstname;
	protected String extraDiploma;
	protected String hobbies;
	protected Long implantation;
	protected String phone;
	protected String mobilePhone;
	protected Long currentAIIHStatus;
	// Transient information for HTTP binding;
	protected String reasonForChange;
	protected String _street;
	protected String _number;
	protected Long _countryId;
	protected Long _postalCodeId;
	protected Date creationDate;
	
	
	public Long getCurrentAIIHStatus() {
		return currentAIIHStatus;
	}
	public void setCurrentAIIHStatus(Long currentAIIHStatus) {
		this.currentAIIHStatus = currentAIIHStatus;
	}
	public String getExtraDiploma() {
		return extraDiploma;
	}
	public void setExtraDiploma(String extraDiploma) {
		this.extraDiploma = extraDiploma;
	}
	public String getHobbies() {
		return hobbies;
	}
	public void setHobbies(String hobbies) {
		this.hobbies = hobbies;
	}
	public Long getMatricule() {
		return matricule;
	}
	public void setMatricule(Long matricule) {
		this.matricule = matricule;
	}
	@OneToMany(cascade = CascadeType.ALL, mappedBy="membre", fetch=FetchType.EAGER) // Cascade
	public Set<Email> getEmails() {
		return emails;
	}
	public void setEmails(Set<Email> emails) {
		this.emails = emails;
	}
	public Long getYear() {
		return year;
	}
	public void setYear(Long year) {
		this.year = year;
	}
	public String getSpeciality() {
		return speciality;
	}
	public void setSpeciality(String speciality) {
		this.speciality = speciality;
	}
	public Long getShiftCourse() {
		return shiftCourse;
	}
	public void setShiftCourse(Long shiftCourse) {
		this.shiftCourse = shiftCourse;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy="member", fetch=FetchType.LAZY) // Cascade
	public Set<EntrepriseMember> getEnterpriseMember() {
		return enterpriseMember;
	}
	public void setEnterpriseMember(Set<EntrepriseMember> enterpriseMember) {
		this.enterpriseMember = enterpriseMember;
	}
	public Long getImplantation() {
		return implantation;
	}
	public void setImplantation(Long implantation) {
		this.implantation = implantation;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getMobilePhone() {
		return mobilePhone;
	}
	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}
	
	@Transient
	public String getReasonForChange() {
		return reasonForChange;
	}
	public void setReasonForChange(String reasonForChange) {
		this.reasonForChange = reasonForChange;
	}
	@Transient
	public String getStreet() {
		return _street;
	}
	public void setStreet(String street) {
		this._street = street;
	}
	@Transient
	public String getNumber() {
		return _number;
	}

	public void setNumber(String number) {
		this._number = number;
	}
	@Transient
	public Long getCountryId() {
		return _countryId;
	}

	public void setCountryId(Long countryId) {
		this._countryId = countryId;
	}
	@Transient
	public Long getPostalCodeId() {
		return _postalCodeId;
	}

	public void setPostalCodeId(Long postalCodeId) {
		this._postalCodeId = postalCodeId;
	}
	@Temporal(TemporalType.TIMESTAMP)
	public Date getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}	
	
}
