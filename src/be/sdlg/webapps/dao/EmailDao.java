package be.sdlg.webapps.dao;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import be.sdlg.webapps.model.Email;

@Repository
public class EmailDao extends CustomDao<Email, Long> {
	public List<Email> retrieveEmailByMember(Long memberId) {
		Query query =  (Query) getCurrentSession().getNamedQuery("findEmailByMemberId").setLong("memberId", memberId);
		return query.list();
	}
}
