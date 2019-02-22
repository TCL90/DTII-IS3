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
		<display:column property="brotherhood.title" titleKey="enrolement.brotherhood" style="background-color:lightgrey;"/>
		<display:column property="enrolMoment" titleKey="enrolement.enrolMoment" style="background-color:lightgrey;"/>
		<display:column property="status" titleKey="enrolement.status" sortable="true" style="background-color:lightgrey;"/>
		

		<display:column property="dropOutMoment" titleKey="enrolement.dropOutMoment" style="background-color:lightgrey;"/>
		
		</jstl:when>
		
		<jstl:when test="${row.status=='APPROVED'}">
		<display:column property="brotherhood.title" titleKey="enrolement.brotherhood" style="background-color:lightgreen;"/>
		<display:column property="enrolMoment" titleKey="enrolement.enrolMoment" style="background-color:lightgreen;"/>
		<display:column property="status" titleKey="enrolement.status" sortable="true" style="background-color:lightgreen;"/>
	
		<display:column property="dropOutMoment" titleKey="enrolement.dropOutMoment" style="background-color:lightgreen;"/>
		
		<display:column><a href="brotherhood/member/show.do?brotherhoodId=${row.brotherhood.id}"><spring:message code="brotherhood.show"/></a></display:column>
		</jstl:when>
		
		<jstl:when test="${row.status=='REJECTED'}">
		<display:column property="brotherhood.title" titleKey="enrolement.brotherhood" style="background-color:orange;"/>
		<display:column property="enrolMoment" titleKey="enrolement.enrolMoment" style="background-color:orange;"/>
		<display:column property="status" titleKey="enrolement.status" sortable="true" style="background-color:orange;"/>
		<display:column property="dropOutMoment" titleKey="enrolement.dropOutMoment" style="background-color:orange;"/>
		</jstl:when>
		
		<jstl:when test="${row.status=='EXPELLED'}">
		<display:column property="brotherhood.title" titleKey="enrolement.brotherhood" style="background-color:orange;"/>
		<display:column property="enrolMoment" titleKey="enrolement.enrolMoment" style="background-color:orange;"/>
		<display:column property="status" titleKey="enrolement.status" sortable="true" style="background-color:orange;"/>
	
		<display:column property="dropOutMoment" titleKey="enrolement.dropOutMoment" style="background-color:orange;"/>
		
		</jstl:when>
		
		</jstl:choose>
		
		
	</display:table>

<div>
	<a href="enrolements/member/create.do"> 
	<spring:message	code="enrolement.create" />
	</a>
</div>
</security:authorize>