<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form>
<acme:input-textbox code="inventor.domp.form.label.pattern" path="pattern" /> 
<jstl:if test="${acme:anyOf(command, 'show, update, delete')}">
<acme:input-textbox code="inventor.domp.form.label.code" path="code" readonly="true"/> 
</jstl:if>

<acme:input-textbox code="inventor.domp.form.label.subject" path="subject"/> 
<acme:input-textbox code="inventor.domp.form.label.summary" path="summary"/>
<acme:input-money code="inventor.domp.form.label.helping" path="helping"/> 
<jstl:if test="${isExchange}">
			<acme:input-money code="any.item.list.label.exchange" path="exchange" readonly="true"/>
	</jstl:if>
<acme:input-moment code="inventor.domp.form.label.creationMoment" path="creationMoment" readonly="true"/> 
<acme:input-moment code="inventor.domp.form.label.startDate" path="startDate"/> 
<acme:input-moment code="inventor.domp.form.label.endDate" path="endDate"/> 
<acme:input-textbox code="inventor.domp.form.label.furtherInfo" path="furtherInfo"/> 

<jstl:choose>
	<jstl:when test="${command== 'create'}">
		<acme:submit code="inventor.domp.form.button.create" action="/inventor/domp/create?itemId=${itemId}"/>
	</jstl:when>
	<jstl:when test="${acme:anyOf(command, 'show, update, delete')}">
		<acme:submit code="inventor.domp.form.button.update" action="/inventor/domp/update"/>
		<acme:submit code="inventor.domp.form.button.delete" action="/inventor/domp/delete"/>
		<acme:button code="inventor.domp.form.button.items" action="/inventor/item/show?id=${itemId}"/>
	</jstl:when>
</jstl:choose>
</acme:form>