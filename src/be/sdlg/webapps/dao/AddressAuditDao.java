package be.sdlg.webapps.dao;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import be.sdlg.webapps.model.AddressAudit;

@Repository
public class AddressAuditDao extends CustomDao<AddressAudit, Long> {
	public List<AddressAudit> retrieveAddressAuditByMember(Long addressId) {
		Query query =  (Query) getCurrentSession().getNamedQuery("findAdressAuditByMemberId").setLong("addressId", addressId);
		return query.list();
	}
}
