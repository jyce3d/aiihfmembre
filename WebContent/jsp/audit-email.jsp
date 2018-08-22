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
					<tr><th><label>Active</label></th><th><label>Email</label></th>	
					<TH>Chang&eacute; par</TH><TH>Raison</TH><TH>Date</TH></tr>
					<c:forEach var="i" items="${emaList}">
						<tr><td><label><c:choose>
			  				<c:when test="${i.active ==1 }">Yes</c:when>
			  				<c:when test="${i.active ==0 }">No</c:when>
			  			</c:choose>
			  			</label></td>
			  			<td><label>${i.email}</label></td>
						<TD><label >${i.changedBy}</label></TD>		
						<TD><label >${i.reason}</label></TD>		
						<TD><label >${i.changeTime}</label></TD></tr>
					</c:forEach>		
			  	</TABLE>
		  	</div>
		  	<br>
			<%@include file="bottom.jsp" %>		
</body>
</HTML>