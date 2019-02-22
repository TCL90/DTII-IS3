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
	
	<h2>
		<spring:message code="brotherhood.title" />
	</h2>
	<p><jstl:out value="${brotherhood.title}"/></p>
	
	<h2>
		<spring:message code="brotherhood.establishmentDate" />
	</h2>
	<p><jstl:out value="${brotherhood.stablishmentDate}"/></p>
	
	<h2>
		<spring:message code="enrolement.position" />
	</h2>
	<jstl:if test="${ pageContext.response.locale.language=='en'}">
	<p><jstl:out value="${enrolement.position.positionEng}"/></p>
	</jstl:if>
	
	<jstl:if test="${ pageContext.response.locale.language=='es'}">
	<p><jstl:out value="${enrolement.position.positionEsp}"/></p>
	</jstl:if>
	
	<br/>
	<form:form action="brotherhood/member/show.do" modelAttribute="brotherhood">
	<form:hidden path="id"/>
	<input type="submit" name="delete"
			value="<spring:message code="brotherhood.delete" />"
			onclick="return confirm('<spring:message code="message.confirm.delete" />')" />&nbsp;
	</form:form>
	<input type="button" name="back" onclick="javascript: window.location.replace('members/brotherhood/list.do')"
		value="<spring:message code="member.back" />" />
	
</security:authorize>