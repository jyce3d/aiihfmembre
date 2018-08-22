package be.sdlg.webapps.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.InheritanceType;

@Entity
@Table(name="addressable")
@Inheritance(strategy = InheritanceType.JOINED)
public class Adressable {
	protected Long id;
	protected Set<Address> adresses;
	@Column(name="adressable_id")
	@OneToMany(cascade = CascadeType.ALL, mappedBy="adressable", fetch=FetchType.LAZY, orphanRemoval = true) // Cascade
	public Set<Address> getAdresses() {
		if (adresses == null) adresses = new HashSet<Address>(0);
		return adresses;
	}
	public void setAdresses(Set<Address> adresses) {
		this.adresses = adresses;
	}
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public void addAddress(Address a) {
		this.getAdresses().add(a);
		a.setAdressable(this);
	}
}
