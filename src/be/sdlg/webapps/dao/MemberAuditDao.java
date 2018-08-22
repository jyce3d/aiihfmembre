package be.sdlg.webapps.dao;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import be.sdlg.webapps.model.MemberAudit;

@Repository
public class MemberAuditDao extends CustomDao<MemberAudit, Long> {
	public List<MemberAudit> retrieveMemberAuditByMember(Long memberId) {
		Query query =  (Query) getCurrentSession().getNamedQuery("findAuditByMemberId").setLong("memberId", memberId);
		return query.list();
	}
}
