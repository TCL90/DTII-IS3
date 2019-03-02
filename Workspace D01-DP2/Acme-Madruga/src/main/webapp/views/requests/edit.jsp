<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>


<security:authorize access="hasRole('MEMBER')">
<form:form action="requests/member/edit.do" modelAttribute="request">
	<form:hidden path="id"/>
	<form:hidden path="version"/>
	<form:hidden path="status"/>
	<jstl:out value="${errors }"/>
	<h2><form:label path="procession">
			<spring:message code="request.procession"/>:
		</form:label></h2>
		<form:select path="procession">
			<form:options items="${listProcessions}" itemLabel="title" itemValue="id"/>								 		
		</form:select>
		<form:errors cssClass="error" path="procession"/>
	
	<br/>
	<br/>
	<input type="submit" name="save"
			value="<spring:message code="request.save" />" />
			<input type="button" name="back" onclick="javascript: window.location.replace('requests/member/list.do')"
		value="<spring:message code="request.back" />" />
		<br/>
	</form:form>
	
	
</security:authorize>