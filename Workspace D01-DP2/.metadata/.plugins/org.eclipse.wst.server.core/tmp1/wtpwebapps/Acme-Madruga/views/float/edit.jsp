<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags" %>
<head>
<title><spring:message code="float.edit" /></title>
</head>

<body>

	<form:form modelAttribute="float" action="float/brotherhood/edit.do">
		<form:hidden path="id" />
		<form:hidden path="version" />

		<!-- Brotherhood -->
		<form:label path="brotherhood">
			<spring:message code="float.brotherhood" />
		</form:label>
		<form:select path="brotherhood">
			<jstl:forEach items="${brotherhoods}" var="e">
				<form:option value="${e.id}">
					${e.title}
				</form:option>
			</jstl:forEach>
		</form:select>
		<form:errors path="brotherhood.title" />
		<br>
		<br>

		<!-- Title -->
		<acme:textbox code="float.title" path="title"/>
		<br>
		<br>

		<!-- Description -->
		<acme:textbox code="float.description" path="description"/>
		<br>
		<br>

		<!-- Pictures -->
		<acme:textbox code="float.pictures" path="pictures"/>
		<br>
		<br>

		<input type="submit" name="saveEdit"
			value="<spring:message code="float.save" />" />

		<input type="submit" name="delete"
			value="<spring:message code="float.delete" />" />
	</form:form>

	<input type="submit" name="cancel"
		value="<spring:message code="float.cancel" />"
		onclick="javascript: relativeRedir('float/brotherhood/list.do');" />

</body>
