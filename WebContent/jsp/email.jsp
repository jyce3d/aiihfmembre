<%--
  User: jean-yves C�lis
  Date: 27/04/2016
  <!--<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>-->
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
<LINK REL ="stylesheet" TYPE="text/css" HREF="<c:url value='/resources/sdlg.css' />" TITLE="Style">
<title>email</title>
</head>
<body>
  	<%@include file="top.jsp" %>		   
		<form:form method="POST" modelAttribute="email" > 
		<TABLE width=100%>
			<TR><TH colspan=2>Edition - Email</TH></TR>
			<TR><TD><label >Adresse email</label></TD><TD>
				<form:input path="email" />
				<label><form:errors path="email" cssClass="error" /></label>
			</TD></TR>
			<TR><TD><label >Active</label></TD><TD>
				<form:select path="active" >
                	<form:options items="${yesNoItems}" />				
				</form:select>
			</TD></TR>
			<TR><TD><input type="button" onclick="document.location.href='home.htm'" value="Menu"></TD><TD><input type="submit" class="button" value="Save" />
			</TD></TR>
			<c:if test="${email.id != null}" >
				<TR><TD><label >Raison<br>du changement</label></TD><TD>
				<form:textarea path="reasonForChange" rows="5" cols="60" />
				<label><form:errors path="reasonForChange" cssClass="error" /></label>
				</TD></TR>
			</c:if>
		</TABLE>
		</form:form>
		<%@include file="bottom.jsp" %>		
</body>
</HTML>