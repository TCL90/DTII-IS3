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
	name="requests" requestURI="${requestURI}" id="row">

<!-- Attributes -->	
		<jstl:choose>
		<jstl:when test="${row.status=='PENDING'}">
		<display:column value="${procession.title}" titleKey="procession.title" style="background-color:lightgrey;"/>
		<display:column value="${procession.departureDate}" titleKey="procession.departureDate" style="background-color:lightgrey;"/>
		<display:column property="status" titleKey="request.status" sortable="true" style="background-color:lightgrey;"/>
	
		<display:column style="background-color:lightgrey;">
		<a href="requests/brotherhood/accept.do?requestIdA=${row.id}">
		<spring:message code="request.acept"/></a></display:column>
		
		<display:column style="background-color:lightgrey;">
		<a href="requests/brotherhood/reject.do?requestIdR=${row.id}">
		<spring:message code="request.reject"/></a></display:column>

		</jstl:when>
		
		<jstl:when test="${row.status=='APPROVED'}">
		<display:column value="${procession.title}" titleKey="procession.title" style="background-color:lightgreen;"/>
		<display:column value="${procession.departureDate}" titleKey="procession.departureDate" style="background-color:lightgreen;"/>
		<display:column property="status" titleKey="request.status" sortable="true" style="background-color:lightgreen;"/>
		<display:column property="rowPosition" titleKey="request.rowPosition" style="background-color:lightgreen;"/>
		<display:column property="columnPosition" titleKey="request.columnPosition" style="background-color:lightgreen;"/>
		
		</jstl:when>
		
		<jstl:when test="${row.status=='REJECTED'}">
		<display:column value="${procession.title}" titleKey="procession.title" style="background-color:orange;"/>
		<display:column value="${procession.departureDate}" titleKey="procession.departureDate" style="background-color:orange;"/>
		<display:column property="status" titleKey="request.status" sortable="true" style="background-color:orange;"/>
		<display:column property="rejectReason" titleKey="request.rejectReason" style="background-color:orange;"/>
		<display:column style="background-color:orange;"></display:column>
		</jstl:when>
		
		</jstl:choose>
	</display:table>

</security:authorize>