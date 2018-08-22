<%--
  User: jean-yves C�lis
  Date: 21/04/2016
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
<title>Edition - Membre</title>
</head>
<body>
		  	<%@include file="top.jsp" %>		   
		<form:form method="POST" modelAttribute="member" > 
		<TABLE width=100%>
			<TR><TH colspan=2>Edition - Membre</TH></TR>
			<TR><TD><label >Pr&eacute;nom</label></TD><TD>
				<form:input path="firstname" />
				<label><form:errors path="firstname" cssClass="error" /></label>
			</TD></TR>
			<TR><TD><label >Nom</label></TD><TD>
				<form:input path="lastname" />
				<label><form:errors path="lastname" cssClass="error" /></label>
			</TD></TR>
			<TR><TD><label >Adresse rue</label></TD><TD>
				<form:input path="street" size="25"/>&nbsp;N°<form:input path="number" size="4" />
				<label><form:errors path="street" cssClass="error" /></label>
			</TD></TR>
			<TR><TD><label >Pays</label></TD><TD>
				<form:select path="countryId" >
                	<form:options items="${countryItems}" />				
				</form:select>
			</TD></TR>
			<TR><TD><label >Code postal</label></TD><TD>
				<form:select path="postalCodeId" >
                	<!--<form:options items="${postalCodeItems}" />-->				
                	<c:forEach var="i" items="${sortPC}">
                		            <option <c:if test="${i.id eq pc.id}">selected="selected"</c:if>    value="${i.id}">${i.zipCode} | ${i.cityName} </option>
                	</c:forEach>
				</form:select>
			</TD></TR>
			<TR><TD><label >Matricule</label></TD><TD>
				<form:input path="matricule" />
				<label><form:errors path="matricule" cssClass="error" /></label>
			</TD></TR>
			<TR><TD><label >Ann&eacute;e de promotion</label></TD><TD>
				<form:input path="year" />
				<label><form:errors path="year" cssClass="error" /></label>
			</TD></TR>
			<TR><TD><label >Cursus suppl&eacute;mentaires</label></TD><TD>
				<form:textarea path="extraDiploma" rows="5" cols="60" />
				<label><form:errors path="extraDiploma" cssClass="error" /></label>
			</TD></TR>			
			<TR><TD><label >Promotion Sociale</label></TD><TD>
				<form:select path="shiftCourse" >
                	<form:options items="${yesNoItems}" />				
				</form:select>
			</TD></TR>
			<TR><TD><label >Sp&eacute;cialit&eacute;</label></TD><TD>
				<form:select path="speciality" >
                	<form:options items="${specialityItems}" />				
				</form:select>
			</TD></TR>
			<TR><TD><label >Status AIIH</label></TD><TD>
				<form:select path="currentAIIHStatus" >
                	<form:options items="${statusAIIHItems}" />				
				</form:select>
			</TD></TR>
			<TR><TD><label >Implantation</label></TD><TD>
				<form:select path="implantation" >
                	<form:options items="${implantationItems}" />				
				</form:select>
			</TD></TR>
			<TR><TD><label >Hobbies</label></TD><TD>
				<form:textarea path="hobbies" rows="5" cols="60"/>
				<label><form:errors path="hobbies" cssClass="error" /></label>
			</TD></TR>
			<TR><TD><label >T&eacute;l&eacute;phone</label></TD><TD>
				<form:input path="phone" />
				<label><form:errors path="phone" cssClass="error" /></label>
			</TD></TR>
			<TR><TD><label >Cellulaire</label></TD><TD>
				<form:input path="mobilePhone" />
				<label><form:errors path="mobilePhone" cssClass="error" /></label>
			</TD></TR>
			
			<c:if test="${member.id != null}" >
				<TR><TD><label >Emails</label></TD><TD>
				   	<TABLE><c:forEach items="${member.emails}" var="em"><TR><TD><a href="email.htm?member-id=${member.id}&id=${em.id}" target="_self">${em.email}</a>
    			   	</TD><TD><a href="delEmail.htm?member-id=${member.id}&id=${em.id}" target="_self">Del</a></TD></TR>
    			   	</c:forEach>
					<TR><TD colspan="2"><a href="email.htm?member-id=${member.id}" target="_self">Nouveau</a></TD></TR>
					</TABLE>
				</TD></TR>
			</c:if>
			<c:if test="${member.id != null}" >
				<TR><TD><label >Raison<br>du changement</label></TD><TD>
				<form:textarea path="reasonForChange" rows="5" cols="60" />
				<label><form:errors path="reasonForChange" cssClass="error" /></label>
			</TD></TR>
	
			</c:if>
			<TR><TD><input type="button" value="Menu" onclick="document.location.href='home.htm'"></TD><TD><input type="submit" class="button" value="Save" />
			</TD></TR>
		</TABLE>	
		</form:form>	
		<%@include file="bottom.jsp" %>		
		</body>
</html>