<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib prefix="view" tagdir="/WEB-INF/tags"%>


<spring:message code="float.brotherhood" var="brotherhood.title" />
<spring:message code="float.title" var="title" />
<spring:message code="float.description" var="description" />
<spring:message code="float.pictures" var="pictures" />

<display:table name="float">

	<display:column property="brotherhood.title" title="${brotherhood}" />
	<display:column property="title" title="${title}" />
	<display:column property="description" title="${description}" />
	<display:column property="pictures" title="${pictures}" />


 
</display:table>