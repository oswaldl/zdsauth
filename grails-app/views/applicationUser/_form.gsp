<%@ page import="com.zy.zds.auth.ApplicationUser" %>



<div class="fieldcontain ${hasErrors(bean: applicationUserInstance, field: 'OSVersion', 'error')} required">
	<label for="OSVersion">
		<g:message code="applicationUser.OSVersion.label" default="OSV ersion" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="OSVersion" required="" value="${applicationUserInstance?.OSVersion}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: applicationUserInstance, field: 'application', 'error')} required">
	<label for="application">
		<g:message code="applicationUser.application.label" default="Application" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="application" name="application.id" from="${com.zy.zds.auth.Application.list()}" optionKey="id" required="" value="${applicationUserInstance?.application?.id}" class="many-to-one"/>

</div>

<div class="fieldcontain ${hasErrors(bean: applicationUserInstance, field: 'customAttribute1', 'error')} required">
	<label for="customAttribute1">
		<g:message code="applicationUser.customAttribute1.label" default="Custom Attribute1" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="customAttribute1" required="" value="${applicationUserInstance?.customAttribute1}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: applicationUserInstance, field: 'customAttribute2', 'error')} required">
	<label for="customAttribute2">
		<g:message code="applicationUser.customAttribute2.label" default="Custom Attribute2" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="customAttribute2" required="" value="${applicationUserInstance?.customAttribute2}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: applicationUserInstance, field: 'customAttribute3', 'error')} required">
	<label for="customAttribute3">
		<g:message code="applicationUser.customAttribute3.label" default="Custom Attribute3" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="customAttribute3" required="" value="${applicationUserInstance?.customAttribute3}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: applicationUserInstance, field: 'customAttribute4', 'error')} required">
	<label for="customAttribute4">
		<g:message code="applicationUser.customAttribute4.label" default="Custom Attribute4" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="customAttribute4" required="" value="${applicationUserInstance?.customAttribute4}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: applicationUserInstance, field: 'deviceType', 'error')} required">
	<label for="deviceType">
		<g:message code="applicationUser.deviceType.label" default="Device Type" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="deviceType" required="" value="${applicationUserInstance?.deviceType}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: applicationUserInstance, field: 'lastAccessedOn', 'error')} required">
	<label for="lastAccessedOn">
		<g:message code="applicationUser.lastAccessedOn.label" default="Last Accessed On" />
		<span class="required-indicator">*</span>
	</label>
	<g:datePicker name="lastAccessedOn" precision="day"  value="${applicationUserInstance?.lastAccessedOn}"  />

</div>

<div class="fieldcontain ${hasErrors(bean: applicationUserInstance, field: 'registeredOn', 'error')} required">
	<label for="registeredOn">
		<g:message code="applicationUser.registeredOn.label" default="Registered On" />
		<span class="required-indicator">*</span>
	</label>
	<g:datePicker name="registeredOn" precision="day"  value="${applicationUserInstance?.registeredOn}"  />

</div>

<div class="fieldcontain ${hasErrors(bean: applicationUserInstance, field: 'status', 'error')} required">
	<label for="status">
		<g:message code="applicationUser.status.label" default="Status" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="status" type="number" value="${applicationUserInstance.status}" required=""/>

</div>

<div class="fieldcontain ${hasErrors(bean: applicationUserInstance, field: 'user', 'error')} required">
	<label for="user">
		<g:message code="applicationUser.user.label" default="User" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="user" name="user.id" from="${com.zy.zds.auth.User.list()}" optionKey="id" required="" value="${applicationUserInstance?.user?.id}" class="many-to-one"/>

</div>

