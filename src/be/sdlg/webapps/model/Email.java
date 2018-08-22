package be.sdlg.webapps.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

@NamedQueries({
	@NamedQuery(
	name = "findEmailByMemberId",
	query = "from Email ea where ea.membre.id = :memberId"
	)
})
@Entity
@Table(name="email")
public class Email {
	
	protected String email;
	protected Long principal;
	protected Long id;
	protected Membre membre;
	protected Long active;
	protected String reasonForChange;
	
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
	@ManyToOne
	public Membre getMembre() {
		return membre;
	}
	public void setMembre(Membre membre) {
		this.membre = membre;
	}
	public Long getActive() {
		return active;
	}
	public void setActive(Long active) {
		this.active = active;
	}
	@Transient
	public String getReasonForChange() {
		return reasonForChange;
	}
	public void setReasonForChange(String reasonForChange) {
		this.reasonForChange = reasonForChange;
	}

	
	
}
