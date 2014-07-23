
<%@ page import="com.zy.zds.auth.Application" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'application.label', default: 'Application')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-application" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-application" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list application">
			
				<g:if test="${applicationInstance?.applicationId}">
				<li class="fieldcontain">
					<span id="applicationId-label" class="property-label"><g:message code="application.applicationId.label" default="Application Id" /></span>
					
						<span class="property-value" aria-labelledby="applicationId-label"><g:fieldValue bean="${applicationInstance}" field="applicationId"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${applicationInstance?.isSupportAnonymous}">
				<li class="fieldcontain">
					<span id="isSupportAnonymous-label" class="property-label"><g:message code="application.isSupportAnonymous.label" default="Is Support Anonymous" /></span>
					
						<span class="property-value" aria-labelledby="isSupportAnonymous-label"><g:formatBoolean boolean="${applicationInstance?.isSupportAnonymous}" /></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form url="[resource:applicationInstance, action:'delete']" method="DELETE">
				<fieldset class="buttons">
					<g:link class="edit" action="edit" resource="${applicationInstance}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
