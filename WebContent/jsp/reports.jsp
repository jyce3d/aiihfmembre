<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
<LINK REL ="stylesheet" TYPE="text/css" HREF="<c:url value='/resources/sdlg.css' />" TITLE="Style">
<title>Edition - Membre</title>
</head>
<body>
		  	<%@include file="top.jsp" %>
		  	<TABLE width=100%>
			<TR><TH>Liste des rapports</TH></TR>
			<TR><TD><label ><a href="audit_membre">Membres courants (cotisations)</a></label></TD></TR>
			<TR><TD><label ><a href="audit_membre">Membres par status</a></label></TD></TR>
		  	</TABLE>	
		  	
			<%@include file="bottom.jsp" %>		
</body>
</HTML>