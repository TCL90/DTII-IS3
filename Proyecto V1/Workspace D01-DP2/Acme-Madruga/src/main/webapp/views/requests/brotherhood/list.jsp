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
	name="processions" requestURI="${requestURI}" id="row">

<!-- Attributes -->	
		
		<display:column property="title" titleKey="request.procession.title"/>
		<display:column property="description" titleKey="request.procession.description"/>
		<display:column property="departureDate" titleKey="request.procession.departureDate"/>
		<display:column property="ticker" titleKey="request.procession.ticker"/>
		
		<jstl:if test="${row.finalMode==true}">
		<display:column><a href="requests/brotherhood/show.do?processionId=${row.id}"><spring:message code="procession.show"/></a></display:column>
		</jstl:if>
		
		
	</display:table>

</security:authorize>