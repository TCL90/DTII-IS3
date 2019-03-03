<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>


<security:authorize access="hasRole('BROTHERHOOD')">
<form:form action="requests/brotherhood/edit.do" modelAttribute="request">
	<form:hidden path="id"/>
	<form:hidden path="version"/>
	<form:hidden path="status" value="${status }"/>
	<form:hidden path="procession"/>
	
	<jstl:if test="${status=='APPROVED'}">
	<form:label path="rowPosition"><spring:message code="request.row"/></form:label>
	<form:input path="rowPosition" type="number" value="${row }"/>
	<form:errors cssClass="error" path="rowPosition"/>
		
	<form:label path="columnPosition"><spring:message code="request.column"/></form:label>
	<form:input path="columnPosition" type="number" value="column"/>
	
	<form:errors cssClass="error" path="columnPosition"/>
	</jstl:if>
	
		<jstl:if test="${status=='REJECTED'}">
	<form:label path="rejectReason"><spring:message code="request.reject.reason"/></form:label>
	<form:input path="rejectReason"/>
	<form:errors cssClass="error" path="rejectReason"/>
	</jstl:if>
	
	<br/>
	<br/>
	<input type="submit" name="save"
			value="<spring:message code="request.save" />" />
			<input type="button" name="back" onclick="javascript: window.location.replace('requests/brotherhood/list.do')"
		value="<spring:message code="request.back" />" />
		<br/>
	</form:form>
	
	
</security:authorize>