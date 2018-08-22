package be.sdlg.webapps.dao;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import be.sdlg.webapps.model.EmailAudit;

@Repository
public class EmailAuditDao extends CustomDao<EmailAudit, Long> {
	public List<EmailAudit> retrieveEmailAuditByEmail(Long emailId) {
		Query query =  (Query) getCurrentSession().getNamedQuery("findEmailAuditByEmailId").setLong("emailId", emailId);
		return query.list();
	}
}
