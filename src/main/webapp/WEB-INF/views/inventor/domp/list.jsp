<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:list>
	<acme:list-column code="inventor.domp.list.label.code" path="code" width="10%"/>
	<acme:list-column code="inventor.domp.list.label.subject" path="subject" width="10%"/>
	<acme:list-column code="inventor.domp.list.label.helping" path="helping" width="10%"/> 
	<acme:list-column code="inventor.domp.list.label.startDate" path="startDate" width="10%"/> 
	<acme:list-column code="inventor.domp.list.label.endDate" path="endDate" width="10%"/> 
</acme:list>