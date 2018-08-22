package be.sdlg.webapps.services;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import be.sdlg.webapps.dao.AddressAuditDao;
import be.sdlg.webapps.dao.CountryDao;
//import be.sdlg.webapps.dao.CountryDao;
import be.sdlg.webapps.dao.EmailAuditDao;
import be.sdlg.webapps.dao.EmailDao;
import be.sdlg.webapps.dao.MemberDao;
import be.sdlg.webapps.dao.PostalCodeDao;
import be.sdlg.webapps.dao.MemberAuditDao;
import be.sdlg.webapps.model.Address;
import be.sdlg.webapps.model.AddressAudit;
import be.sdlg.webapps.model.Country;
import be.sdlg.webapps.model.Email;
import be.sdlg.webapps.model.EmailAudit;
import be.sdlg.webapps.model.MemberAudit;
import be.sdlg.webapps.model.Membre;
import be.sdlg.webapps.model.PostalCode;

@Service
public class AiihServiceImpl implements AiihService {
	@Autowired
	private MemberDao memberDao;
	@Autowired
	private MemberAuditDao memberAuditDao;
	@Autowired
	private CountryDao countryDao;
	@Autowired
	private PostalCodeDao postalCodeDao;
	@Autowired
	private AddressAuditDao addressAuditDao;
	@Autowired
	private EmailAuditDao emailAuditDao;

	@Autowired
	private EmailDao emailDao;
	
	public Address getCurrentAddress(Membre m) {
		if (m.getAdresses().iterator().hasNext() )
			return (Address) m.getAdresses().iterator().next();
		return null;
	}
	private void initMember(Membre m) {
		Address current=null;
		current= getCurrentAddress(m);
		if (current != null) {
			m.setStreet(current.getStreet());
			m.setNumber(current.getNumber());
			if (current.getPostalCode() !=null) {
				m.setPostalCodeId(current.getPostalCode().getId());
				if (current.getPostalCode().getCountry() !=null)
					m.setCountryId(current.getPostalCode().getCountry().getId());

			}
		}

	}
	public Membre getMember(Long id) {
		Membre m = memberDao.get(id);
		initMember(m);
		return m;
	}
	private boolean isAddressDirty(Membre oldM, Membre newM) {
		//if (oldM == null ) return true;
		if (getCurrentAddress(oldM).getStreet() == null && newM.getStreet() ==null  )
				return false;
		if (getCurrentAddress(oldM).getNumber()==null && newM.getNumber() ==null) 
			return false;

		if (getCurrentAddress(oldM).getStreet() == null && newM.getStreet() !=null  )
			return true;
		if (getCurrentAddress(oldM).getNumber()==null && newM.getNumber() !=null) 
			return true;
		
		if (!getCurrentAddress(oldM).getStreet().equals(newM.getStreet())) return true;
		if (!getCurrentAddress(oldM).getNumber().equals(newM.getNumber())) return true;
		return isPostalCodeDirty(oldM, newM);
	}
	private boolean isPostalCodeDirty(Membre oldM, Membre newM) {
	//	if (oldM == null) return true;
		
		if (getCurrentAddress(oldM).getPostalCode() == null && newM.getPostalCodeId() == null) return false;
		if (getCurrentAddress(oldM).getPostalCode() == null && newM.getPostalCodeId() != null) return true;
		
		if (getCurrentAddress(oldM).getPostalCode().getId() != newM.getPostalCodeId()) return true;
		return false;
	}
	private AddressAudit changeAddress(Membre m, Membre old, String reason, String username) {
			// Audit trail
			AddressAudit na = new AddressAudit();
			Address oa = getCurrentAddress(old);
	//		if (oa==null) {
			//	oa=new Address();
			//	m.getAdresses().add(oa);
			//	TODO: raise exception
		//	}
			if (oa != null) na.init(oa, reason, username);
			Address naddr = new Address();
			if (oa!=null)
				naddr.setId(oa.getId());
	//		addressAuditDao.save(na);
			if (m!=null && m.getStreet()!=null) naddr.setStreet(m.getStreet());
			if (m!=null && m.getStreet()!=null) naddr.setNumber(m.getNumber());
			if (m!=null && m.getStreet()!=null) { 
				PostalCode pc = postalCodeDao.get(m.getPostalCodeId());
				naddr.setPostalCode(pc);
			}
			m.addAddress(naddr);
			return na;
	}
	public void saveMember(Membre member, String reason, String username) {
		MemberAudit ma = null;
		AddressAudit na= null;
		if (member.getId() != null ) {
		//	String reason = member.getReasonForChange();
			Membre old =(Membre) memberDao.get(member.getId());
			if (isAddressDirty(old, member)) na=changeAddress(member,old, reason, username);
			else member.addAddress(getCurrentAddress(old)); // add the proper address no need
			old.setReasonForChange(reason);
			ma =new MemberAudit();
			ma.init(old, reason, username);
		} else {
			member.setCreationDate(new Date());
			Address oa = new Address();
			if (member!=null && member.getStreet()!=null) oa.setStreet(member.getStreet());
			if (member!=null && member.getStreet()!=null) oa.setNumber(member.getNumber());
			if (member!=null && member.getStreet()!=null) { 
				PostalCode pc = postalCodeDao.get(member.getPostalCodeId());
				oa.setPostalCode(pc);
			}
			member.addAddress(oa);
			
		}
		Membre m2 = (Membre) memberDao.getCurrentSession().merge(member);
		memberDao.save(m2);
		if (ma != null) {
			ma.setMemberId(m2.getId());
			memberAuditDao.save(ma);
		}
		if (na !=null) 
			addressAuditDao.save(na);
	}

	public MemberDao getMemberDao() {
		return memberDao;
	}

	public void setMemberDao(MemberDao memberDao) {
		this.memberDao = memberDao;
	}

	public Set<Membre> retrieveMembers() {
		return memberDao.findAll();
	}

	public Email getEmail(Long id) {
		
		return emailDao.get(id);
	}
	private EmailAudit createEmailAudit(Email email, String reason, String username) {
		EmailAudit ea = new EmailAudit();
		ea.setActive(email.getActive());
		ea.setEmail(email.getEmail());
		ea.setPrincipal(email.getPrincipal());
		ea.setReason(reason);
		ea.setChangeTime(new Date());
		ea.setChangedBy(username);
		ea.setEmailId(email.getId());
		return ea;
	}
	public void saveEmail(Long memberId, String reason, String username, Email email) {
		Membre m = memberDao.get(memberId);
		if (m!=null) {
			if (email.getId() !=null) {
				Email old = emailDao.get(email.getId());
				EmailAudit ea=createEmailAudit(old, reason, username);
				emailAuditDao.save(ea);
				Email em2 = (Email) emailDao.getCurrentSession().merge(email);
				em2.setMembre(m);
				emailDao.save(em2);

			} else {
				Email em2 = (Email) emailDao.getCurrentSession().merge(email);
				em2.setMembre(m);
				m.getEmails().add(em2);
			
				memberDao.save(m);
			}
		}
		
	}

	@Override
	public void delMember(Long memberId, String username) {
		Membre m = memberDao.get(memberId);
		MemberAudit ma =new MemberAudit();
		ma.init(m, "DELETE", username);
		if (m!=null) {
			memberDao.delete(m);
			memberAuditDao.save(ma);
		}
		
	}

	@Override
	public void delEmail(Long memberId, Long emailId, String username) {
		boolean bFound = false;
		Membre member = this.getMember(memberId);
		if (member!=null) {
			for (Email em : member.getEmails()) {
				if (em.getId().equals(emailId)) {
					EmailAudit ea=createEmailAudit(em, "DELETE", username);
					member.getEmails().remove(em);
					emailDao.delete(em);
					emailAuditDao.save(ea);
					bFound = true;
					break;
				}		
			}
		}
		if (bFound) memberDao.save(member);
	}
	@Override
	public Set<Country> retrieveCountryList() {
		
		return countryDao.findAll();
	}
	@Override
	public Set<PostalCode> retrievePostalCodeList() {
		return postalCodeDao.findAll();
	}
	@Override
	public List<MemberAudit> retrieveMemberAudit(Long memberId) {
		return memberAuditDao.retrieveMemberAuditByMember(memberId);
	}
	@Override
	public List<AddressAudit> retrieveAddressAudit(Long memberId) {
		if (memberId !=null) {
			Membre m = memberDao.get(memberId);
			Address a =getCurrentAddress(m);
			return addressAuditDao.retrieveAddressAuditByMember(a.getId());
		}
		else return null;
	}
	@Override
	public List<Email> retrieveEmail(Long memberId) {
		return emailDao.retrieveEmailByMember(memberId);
	}
	@Override
	public List<EmailAudit> retrieveEmailAudit(Long emailId) {
		return emailAuditDao.retrieveEmailAuditByEmail(emailId);
	}
	
	
	

}
