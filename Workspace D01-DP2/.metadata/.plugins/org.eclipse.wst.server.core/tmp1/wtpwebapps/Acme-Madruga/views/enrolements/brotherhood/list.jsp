<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<security:authorize access="isAuthenticated()">
<display:table pagesize="5" class="displaytag" keepStatus="true"
	name="enrolements" requestURI="${requestURI}" id="row">

<!-- Attributes -->	
		<jstl:choose>
		
		<jstl:when test="${row.status=='PENDING'}">
		<%-- <%-- <display:column property="${members.name }" titleKey="member.name" style="background-color:lightgrey;"/>
		<display:column property="${members.surname }" titleKey="member.surname" style="background-color:lightgrey;"/>
		<display:column property="${members.email }" titleKey="member.email" style="background-color:lightgrey;"/>
		<display:column property="${members.phoneNumber }" titleKey="member.phoneNumber" style="background-color:lightgrey;" />
		<display:column property="${members.address }" titleKey="member.address" style="background-color:lightgrey;"/> --%> --%>
		<display:column property="enrolMoment" titleKey="enrolement.enrolMoment1" style="background-color:lightgrey;"/>
		<display:column property="status" titleKey="enrolement.status" sortable="true" style="background-color:lightgrey;"/>
		<display:column property="dropOutMoment" titleKey="enrolement.dropOutMoment" style="background-color:lightgrey;"/>
		
		<display:column style="background-color:lightgrey;"><a href="enrolements/brotherhood/enrol.do?enrolementId=${row.id}">
			<spring:message code="enrolement.acept1"/></a></display:column>

		<display:column style="background-color:lightgrey;"><div style="align:center;"><a href="enrolements/brotherhood/reject.do?enrolementId=${row.id}">
			<spring:message code="enrolement.reject" /></a></div></display:column>
		<display:column style="background-color:lightgrey;"><a href="enrolements/brotherhood/show.do?enrolementId=${row.id }">
		<spring:message code="enrolement.showMore"/></a></display:column>

		
		</jstl:when>
		
		<%-- <jstl:when test="${row.status=='APPROVED'}">
		<%-- <display:column property="${members.name }" titleKey="member.name" style="background-color:lightgreen;"/>
		<display:column property="${members.surname }" titleKey="member.surname" style="background-color:lightgreen;"/>
		<display:column property="${members.email }" titleKey="member.email" style="background-color:lightgreen;"/>
		<display:column property="${members.phoneNumber }" titleKey="member.phoneNumber" style="background-color:lightgreen;" />
		<display:column property="${members.address }" titleKey="member.address" style="background-color:lightgreen;"/> --%>
		<%-- <display:column property="enrolMoment" titleKey="enrolement.enrolMoment" style="background-color:lightgreen;"/>
		<display:column property="status" titleKey="enrolement.status" sortable="true" style="background-color:lightgreen;"/>
		

		<display:column property="dropOutMoment" titleKey="enrolement.dropOutMoment" style="background-color:lightgreen;"/>
		</jstl:when> --%>
		
		<%-- <jstl:when test="${row.status=='REJECTED'}">
		<display:column property="${members.name }" titleKey="member.name" style="background-color:orange;"/>
		<display:column property="${members.surname }" titleKey="member.surname" style="background-color:orange;"/>
		<display:column property="${members.email }" titleKey="member.email" style="background-color:orange;"/>
		<display:column property="${members.phoneNumber }" titleKey="member.phoneNumber"style="background-color:orange;" />
		<display:column property="${members.address }" titleKey="member.address" style="background-color:orange;"/>
		<display:column property="enrolMoment" titleKey="enrolement.enrolMoment" style="background-color:orange;"/>
		<display:column property="status" titleKey="enrolement.status" sortable="true" style="background-color:orange;"/>
		

		<display:column property="dropOutMoment" titleKey="enrolement.dropOutMoment" style="background-color:orange;"/>
		</jstl:when>
		
		<jstl:when test="${row.status=='EXPELLED'}">
		<display:column property="${members.name }" titleKey="member.name" style="background-color:orange;"/>
		<display:column property="${members.surname }" titleKey="member.surname" style="background-color:orange;"/>
		<display:column property="${members.email }" titleKey="member.email" style="background-color:orange;"/>
		<display:column property="${members.phoneNumber }" titleKey="member.phoneNumber"style="background-color:orange;" />
		<display:column property="${members.address }" titleKey="member.address" style="background-color:orange;"/>
		<display:column property="enrolMoment" titleKey="enrolement.enrolMoment" style="background-color:orange;"/>
		<display:column property="status" titleKey="enrolement.status" sortable="true" style="background-color:orange;"/>
		

		<display:column property="dropOutMoment" titleKey="enrolement.dropOutMoment" style="background-color:orange;"/>
		
		</jstl:when>  --%>
		
		</jstl:choose>
		
		
	</display:table>
</security:authorize>