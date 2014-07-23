
<%@ page import="com.zy.zds.auth.ApplicationUser" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'applicationUser.label', default: 'ApplicationUser')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-applicationUser" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-applicationUser" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list applicationUser">
			
				<g:if test="${applicationUserInstance?.OSVersion}">
				<li class="fieldcontain">
					<span id="OSVersion-label" class="property-label"><g:message code="applicationUser.OSVersion.label" default="OSV ersion" /></span>
					
						<span class="property-value" aria-labelledby="OSVersion-label"><g:fieldValue bean="${applicationUserInstance}" field="OSVersion"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${applicationUserInstance?.application}">
				<li class="fieldcontain">
					<span id="application-label" class="property-label"><g:message code="applicationUser.application.label" default="Application" /></span>
					
						<span class="property-value" aria-labelledby="application-label"><g:link controller="application" action="show" id="${applicationUserInstance?.application?.id}">${applicationUserInstance?.application?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${applicationUserInstance?.customAttribute1}">
				<li class="fieldcontain">
					<span id="customAttribute1-label" class="property-label"><g:message code="applicationUser.customAttribute1.label" default="Custom Attribute1" /></span>
					
						<span class="property-value" aria-labelledby="customAttribute1-label"><g:fieldValue bean="${applicationUserInstance}" field="customAttribute1"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${applicationUserInstance?.customAttribute2}">
				<li class="fieldcontain">
					<span id="customAttribute2-label" class="property-label"><g:message code="applicationUser.customAttribute2.label" default="Custom Attribute2" /></span>
					
						<span class="property-value" aria-labelledby="customAttribute2-label"><g:fieldValue bean="${applicationUserInstance}" field="customAttribute2"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${applicationUserInstance?.customAttribute3}">
				<li class="fieldcontain">
					<span id="customAttribute3-label" class="property-label"><g:message code="applicationUser.customAttribute3.label" default="Custom Attribute3" /></span>
					
						<span class="property-value" aria-labelledby="customAttribute3-label"><g:fieldValue bean="${applicationUserInstance}" field="customAttribute3"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${applicationUserInstance?.customAttribute4}">
				<li class="fieldcontain">
					<span id="customAttribute4-label" class="property-label"><g:message code="applicationUser.customAttribute4.label" default="Custom Attribute4" /></span>
					
						<span class="property-value" aria-labelledby="customAttribute4-label"><g:fieldValue bean="${applicationUserInstance}" field="customAttribute4"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${applicationUserInstance?.deviceType}">
				<li class="fieldcontain">
					<span id="deviceType-label" class="property-label"><g:message code="applicationUser.deviceType.label" default="Device Type" /></span>
					
						<span class="property-value" aria-labelledby="deviceType-label"><g:fieldValue bean="${applicationUserInstance}" field="deviceType"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${applicationUserInstance?.lastAccessedOn}">
				<li class="fieldcontain">
					<span id="lastAccessedOn-label" class="property-label"><g:message code="applicationUser.lastAccessedOn.label" default="Last Accessed On" /></span>
					
						<span class="property-value" aria-labelledby="lastAccessedOn-label"><g:formatDate date="${applicationUserInstance?.lastAccessedOn}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${applicationUserInstance?.registeredOn}">
				<li class="fieldcontain">
					<span id="registeredOn-label" class="property-label"><g:message code="applicationUser.registeredOn.label" default="Registered On" /></span>
					
						<span class="property-value" aria-labelledby="registeredOn-label"><g:formatDate date="${applicationUserInstance?.registeredOn}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${applicationUserInstance?.status}">
				<li class="fieldcontain">
					<span id="status-label" class="property-label"><g:message code="applicationUser.status.label" default="Status" /></span>
					
						<span class="property-value" aria-labelledby="status-label"><g:fieldValue bean="${applicationUserInstance}" field="status"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${applicationUserInstance?.user}">
				<li class="fieldcontain">
					<span id="user-label" class="property-label"><g:message code="applicationUser.user.label" default="User" /></span>
					
						<span class="property-value" aria-labelledby="user-label"><g:link controller="user" action="show" id="${applicationUserInstance?.user?.id}">${applicationUserInstance?.user?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form url="[resource:applicationUserInstance, action:'delete']" method="DELETE">
				<fieldset class="buttons">
					<g:link class="edit" action="edit" resource="${applicationUserInstance}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
