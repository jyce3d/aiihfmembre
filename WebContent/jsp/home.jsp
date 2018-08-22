<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<!--<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">-->
<LINK REL ="stylesheet" TYPE="text/css" HREF="<c:url value='/resources/sdlg.css' />" TITLE="Style">
<title>Fichier AIIH : Listing </title>
</head>
<body>
  	<%@include file="top.jsp" %>		   
    <div id="members"><b>Fichier membre de l'AIIH</b><br><br>
    	<table>
    	<tr><th>Nom</th><th>Pr&eacute;nom</th><th>Matricule</th><th>Sp&eacute;cialit&eacute;</th><th>Promotion</th><th>Emails</th><th>Supprimer</th><th>Autres</th></tr>
    	<c:forEach items="${memberList}" var="m">
    	<tr>
    		<td><a href="member.htm?id=${m.id}" target="_self" >${m.lastname}</a></td>
    		<td>${m.firstname}</td>
    		<td>${m.matricule}</td>
    		<td><c:choose>
							<c:when test="${m.speciality ==1 }">AGRONOMIE</c:when>
							<c:when test="${m.speciality ==2 }">BIOCHIMIE</c:when>
							<c:when test="${m.speciality ==3 }">CHIMIE</c:when>
							<c:when test="${m.speciality ==4 }">CONSTRUCTION</c:when>
							<c:when test="${m.speciality ==5 }">ELECTRICITE</c:when>
							<c:when test="${m.speciality ==6 }">ELECTRONIQUE</c:when>
							<c:when test="${m.speciality ==7 }">ELECTROMECANIQUE</c:when>
							<c:when test="${m.speciality ==8 }">MECANIQUE</c:when>
				</c:choose></td>   
			<td>${m.year}</td>
			<td><c:forEach items="${m.emails}" var="em">${em.email}<br>
				</c:forEach>
				</td>
			<td align="right"><a href="delMembre.htm?id=${m.id}" target="_self">Sup</a></td>
			<td ><a href="audit.htm?id=${m.id}" target="_self">Audit</a></td>
			
			</tr> 	
    	</c:forEach>
    	<td colspan=8><a href="member.htm" target="_self">Nouveau</a></td>
    	
    	</table>
    </div>
    <br>
   	<%@include file="bottom.jsp" %>		
</body>
</html>