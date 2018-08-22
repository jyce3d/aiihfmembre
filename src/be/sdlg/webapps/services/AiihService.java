package be.sdlg.webapps.services;

import java.util.List;
import java.util.Set;

import be.sdlg.webapps.model.Address;
import be.sdlg.webapps.model.AddressAudit;
import be.sdlg.webapps.model.Country;
import be.sdlg.webapps.model.Email;
import be.sdlg.webapps.model.EmailAudit;
import be.sdlg.webapps.model.MemberAudit;
import be.sdlg.webapps.model.Membre;
import be.sdlg.webapps.model.PostalCode;

public interface AiihService {
	public Membre getMember(Long id);
	public void saveMember(Membre member, String reason, String username);
	
	public Set<Membre> retrieveMembers();
	
	public Email getEmail(Long id);
	public void saveEmail(Long memberId, String reason, String username, Email email);
	public void delMember(Long memberId, String username);
	public void delEmail(Long memberId, Long emailId, String username);
	
	public List<MemberAudit> retrieveMemberAudit(Long memberId);
	public List<AddressAudit> retrieveAddressAudit(Long memberId);
	public List<Email> retrieveEmail(Long memberId);
	public List<EmailAudit> retrieveEmailAudit(Long emailId);

	public Set<Country> retrieveCountryList();
	public Set<PostalCode> retrievePostalCodeList();
	public Address getCurrentAddress(Membre m);
	
}
