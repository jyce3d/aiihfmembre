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

@NamedQueries({
	@NamedQuery(
	name = "findEmailAuditByEmailId",
	query = "from EmailAudit ema where ema.emailId = :emailId"
	)
})
@Entity
@Table(name="email_audit")
public class EmailAudit {

	protected String email;
	protected Long principal;
	protected Long id;
	protected Long emailId;
	protected Long active;
	
	protected Date changeTime;
	protected String changedBy;
	protected String reason;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Long getPrincipal() {
		return principal;
	}
	public void setPrincipal(Long principal) {
		this.principal = principal;
	}
	public Long getActive() {
		return active;
	}

	public void setActive(Long active) {
		this.active = active;
	}
	@Lob
	@Column( length = 100000 )
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
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
	public Long getEmailId() {
		return emailId;
	}
	public void setEmailId(Long emailId) {
		this.emailId = emailId;
	}
	
	

}
