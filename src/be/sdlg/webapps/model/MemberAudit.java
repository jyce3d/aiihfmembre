package be.sdlg.webapps.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
@NamedQueries({
	@NamedQuery(
	name = "findAuditByMemberId",
	query = "from MemberAudit ma where ma.memberId = :memberId"
	)
})
@Entity
@Table(name="membre_audit")
public class MemberAudit {
	protected Long id;
	protected Long memberId;
	protected Long matricule; 
	protected Long year;
	protected String speciality;
	protected Long shiftCourse;
	protected String lastname;
	protected String firstname;
	protected Long implantation;
	protected String phone;
	protected String mobilePhone;
	protected String extraDiploma;
	protected String hobbies;
	protected Long currentAIIHStatus;
	protected Date changeTime;
	protected String changedBy;
	protected String reason;
	protected Date creationDate;

	public Long getCurrentAIIHStatus() {
		return currentAIIHStatus;
	}
	public void setCurrentAIIHStatus(Long currentAIIHStatus) {
		this.currentAIIHStatus = currentAIIHStatus;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getMemberId() {
		return memberId;
	}
	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}
	public Long getMatricule() {
		return matricule;
	}
	public void setMatricule(Long matricule) {
		this.matricule = matricule;
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
	public void init(Membre ma, String reason, String changedBy) {
		this.matricule=ma.matricule; 
		this.year=ma.year;
		this.speciality=ma.speciality;
		this.shiftCourse=ma.shiftCourse;
		this.lastname=ma.lastname;
		this.firstname=ma.firstname;
		this.extraDiploma=ma.extraDiploma;
		this.hobbies=ma.hobbies;
		this.implantation=ma.implantation;
		this.phone=ma.phone;
		this.mobilePhone=ma.mobilePhone;
		this.currentAIIHStatus=ma.currentAIIHStatus;
		this.creationDate=ma.creationDate;
		this.reason=ma.reasonForChange;
		this.changedBy= changedBy;
		this.changeTime = new Date();
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
	public Date getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	
	
}
