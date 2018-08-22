<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<LINK REL ="stylesheet" TYPE="text/css" HREF="<c:url value='/resources/sdlg.css' />" TITLE="Style">
<title>Rapport audit du membre : ${m.firstname}&nbsp;${m.lastname}</title>
</head>
<body>
		  	<%@include file="top.jsp" %>
		  	<br>
		  	<div id="members">
					<TABLE width="100%">
			<TR><TH>Pr&eacute;nom</TH><TH>Nom</TH><TH>Matricule</TH><TH>Ann&eacute;e</TH><TH>status</TH><TH>Sp&eacute;cialit&eacute;</TH><TH>Certifications</TH>
			<TH>Tel</TH><TH>Mobile</TH></TR>
			<TR><TD><label >${m.firstname}</label></TD><TD><label >${m.lastname}</label></TD>
			<TD><label >${m.matricule}</label></TD><TD><label >${m.year}</label></TD>
			<TD><c:choose>
							<c:when test="${m.currentAIIHStatus ==1 }">Membre</c:when>
							<c:when test="${m.currentAIIHStatus ==2 }">Pr&eacute;sident</c:when>
							<c:when test="${m.currentAIIHStatus ==3 }">Secr&eacute;taire</c:when>
							<c:when test="${m.currentAIIHStatus ==4 }">Tr&eacute;sorier</c:when>
							<c:when test="${m.currentAIIHStatus ==5 }">Vice Pr&eacutesident</c:when>
							<c:when test="${m.currentAIIHStatus ==6 }">Administrateur</c:when>
							<c:when test="${m.currentAIIHStatus ==7 }">Excomuni&acute;</c:when>
							<c:when test="${m.currentAIIHStatus ==8 }">Unsubscribed</c:when>
				</c:choose></TD>
				
			<TD><c:choose>
							<c:when test="${m.speciality ==1 }">AGRONOMIE</c:when>
							<c:when test="${m.speciality ==2 }">BIOCHIMIE</c:when>
							<c:when test="${m.speciality ==3 }">CHIMIE</c:when>
							<c:when test="${m.speciality ==4 }">CONSTRUCTION</c:when>
							<c:when test="${m.speciality ==5 }">ELECTRICITE</c:when>
							<c:when test="${m.speciality ==6 }">ELECTRONIQUE</c:when>
							<c:when test="${m.speciality ==7 }">ELECTROMECANIQUE</c:when>
							<c:when test="${m.speciality ==8 }">MECANIQUE</c:when>
				</c:choose>
			</TD>	
			<TD><label >${m.extraDiploma}</label></TD>
			<TD><label >${m.phone}</label></TD>
			<TD><label >${m.mobilePhone}</label></TD>			
			</TR>
			</TABLE>
			<H1>Audit membre</H1>
			<TABLE width="100%">
			<TR><TH>Pr&eacute;nom</TH><TH>Nom</TH><TH>Matricule</TH><TH>Ann&eacute;e</TH><TH>status</TH><TH>Sp&eacute;cialit&eacute;</TH><TH>Certifications</TH>
			<TH>Tel</TH><TH>Mobile</TH><TH>Chang&eacute; par</TH><TH>Raison</TH><TH>Date</TH></TR>
			<c:forEach var="i" items="${maList}">
				<TR><TD><label >${i.firstname}</label></TD><TD><label >${i.lastname}</label></TD>
				<TD><label >${i.matricule}</label></TD><TD><label >${i.year}</label></TD>
				<TD><c:choose>
							<c:when test="${i.currentAIIHStatus ==1 }">Membre</c:when>
							<c:when test="${i.currentAIIHStatus ==2 }">Pr&eacute;sident</c:when>
							<c:when test="${i.currentAIIHStatus ==3 }">Secr&eacute;taire</c:when>
							<c:when test="${i.currentAIIHStatus ==4 }">Tr&eacute;sorier</c:when>
							<c:when test="${i.currentAIIHStatus ==5 }">Vice Pr&eacutesident</c:when>
							<c:when test="${i.currentAIIHStatus ==6 }">Administrateur</c:when>
							<c:when test="${i.currentAIIHStatus ==7 }">Excomuni&acute;</c:when>
							<c:when test="${i.currentAIIHStatus ==8 }">Unsubscribed</c:when>
					</c:choose></TD>
				
				<TD><c:choose>
							<c:when test="${i.speciality ==1 }">AGRONOMIE</c:when>
							<c:when test="${i.speciality ==2 }">BIOCHIMIE</c:when>
							<c:when test="${i.speciality ==3 }">CHIMIE</c:when>
							<c:when test="${i.speciality ==4 }">CONSTRUCTION</c:when>
							<c:when test="${i.speciality ==5 }">ELECTRICITE</c:when>
							<c:when test="${i.speciality ==6 }">ELECTRONIQUE</c:when>
							<c:when test="${i.speciality ==7 }">ELECTROMECANIQUE</c:when>
							<c:when test="${i.speciality ==8 }">MECANIQUE</c:when>
					</c:choose>
				</TD>	
				<TD><label >${i.extraDiploma}</label></TD>
				<TD><label >${i.phone}</label></TD>
				<TD><label >${i.mobilePhone}</label></TD>		
				<TD><label >${i.changedBy}</label></TD>		
				<TD><label >${i.reason}</label></TD>		
				<TD><label >${i.changeTime}</label></TD>		
			</c:forEach>
			</TABLE>
		  	<H1>Addresse</H1>
		  	<TABLE width="100%">
		  		<tr><th><label>N°</label></th><th><label>Rue</label></th><th><label>Code Postal</label></th><th><label>Ville</label></th><th><label>Pays</label></th></tr>
		  		<tr><td><label>${addr.number}</label></td><td><label>${addr.street}</label></td><td><label>${addr.zip}</label></td>
		  		<td><label>${addr.city}</label></td>
		  		<td><label>${addr.country}</label></td>
		  		</tr>
		  	</TABLE>
		  	<H1>Audit Address</H1>
			<TABLE width="100%">
				<tr><th><label>N°</label></th><th><label>Rue</label></th><th><label>Code Postal</label></th><th><label>Ville</label></th>
				<th><label>Pays</label></th><TH>Chang&eacute; par</TH><TH>Raison</TH><TH>Date</TH></tr>
				
				<c:forEach var="i" items="${aaList}">
					<tr><td><label>${i.number}</label></td><td><label>${i.street}</label></td><td><label>${i.zip}</label></td>
		  			<td><label>${i.city}</label></td>
		  			<td><label>${i.countryCode}</label></td>
		  			<TD><label >${i.changedBy}</label></TD>		
					<TD><label >${i.reason}</label></TD>		
					<TD><label >${i.changeTime}</label></TD>		
				</c:forEach>
			</TABLE>		  	
		  	<H1>Emails</H1>
		  	<TABLE width="100%">
		  		<tr><th>Active</th><th colspan="2"><label >Email</label></th></tr>
		  		<c:forEach var="i" items="${emailList}">
			  		<tr><td><label><c:choose>
			  			<c:when test="${i.active ==1 }">Yes</c:when>
			  			<c:when test="${i.active ==0 }">No</c:when>
			  		</c:choose>
			  		</label></td><td><label>${i.email}</label></td><td><label><a href="audit_email.htm?id=${i.id}">Audit</a></label></td></tr>
			  	</c:forEach>
		  	</TABLE>
		  	</div>
		  	<br>
			<%@include file="bottom.jsp" %>		
</body>
</HTML>