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
		<display:column property="procession.title" titleKey="request.procession.title" style="background-color:lightgrey;"/>
		<display:column property="procession.departureDate" titleKey="request.procession.departureDate" style="background-color:lightgrey;"/>
		<display:column property="status" titleKey="request.status" sortable="true" style="background-color:lightgrey;"/>
		
		<display:column style="background-color:lightgrey;"></display:column>
		</jstl:when>
		
		<jstl:when test="${row.status=='APPROVED'}">
		<display:column property="procession.title" titleKey="request.procession.title" style="background-color:lightgreen;"/>
		<display:column property="procession.departureDate" titleKey="request.procession.departureDate" style="background-color:lightgreen;"/>
		<display:column property="status" titleKey="request.status" sortable="true" style="background-color:lightgreen;"/>
	
		<display:column style="background-color:lightgreen;"><a href="requests/member/show.do?requestId=${row.id}"><spring:message code="request.show"/></a></display:column>
		</jstl:when>
		
		<jstl:when test="${row.status=='REJECTED'}">
		<display:column property="procession.title" titleKey="request.procession.title" style="background-color:orange;"/>
		<display:column property="procession.departureDate" titleKey="request.procession.departureDate" style="background-color:orange;"/>
		<display:column property="status" titleKey="request.status" sortable="true" style="background-color:orange;"/>
		
		<display:column style="background-color:orange;"></display:column>
		</jstl:when>
		
		<jstl:when test="${row.status=='EXPELLED'}">
		<display:column property="procession.title" titleKey="request.procession.title" style="background-color:orange;"/>
		<display:column property="procession.departureDate" titleKey="request.procession.departureDate" style="background-color:orange;"/>
		<display:column property="status" titleKey="request.status" sortable="true" style="background-color:orange;"/>
	
		<display:column style="background-color:orange;"></display:column>
		</jstl:when>
		
		</jstl:choose>
		
		
	</display:table>

<div>
	<a href="requests/member/create.do"> 
	<spring:message	code="request.create" />
	</a>
</div>
</security:authorize>