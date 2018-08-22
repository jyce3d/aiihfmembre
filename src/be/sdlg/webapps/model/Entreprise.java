package be.sdlg.webapps.model;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name="entreprise")
@PrimaryKeyJoinColumn(name="addressable_id")
public class Entreprise extends Adressable {
	protected String name;
	protected Set<EntrepriseMember> entrepriseMember;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@OneToMany(cascade = CascadeType.ALL, mappedBy="entreprise", fetch=FetchType.LAZY) // Cascade
	public Set<EntrepriseMember> getEntrepriseMember() {
		return entrepriseMember;
	}
	public void setEntrepriseMember(Set<EntrepriseMember> entrepriseMember) {
		this.entrepriseMember = entrepriseMember;
	}
	
	
}
