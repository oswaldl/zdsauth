
<%@ page import="com.zy.zds.auth.Application" %>
<!DOCTYPE html>
<html>
	<head>
        <link rel="stylesheet" type="text/css" href="${resource(dir: 'css', file: 'style.css')}" />
        <link rel="stylesheet" type="text/css" href="${resource(dir: 'css', file: 'console_style.css')}" />
		<g:set var="entityName" value="${message(code: 'application.label', default: 'Application')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
    <div class="content">
    <div class="contiter">
        <h2>应用列表</h2>
    </div>
    <div class="conmain">
        <strong class="Orange">
            <g:if test="${flash.message}">
                <div class="message" role="status">${flash.message}</div>
            </g:if>
        </strong>
    </div>
    <div class="conmain">
        <table width="100%" border="0" cellspacing="0" cellpadding="0">
            <tr class="tbbg">
                <td width="40%" align="center"><strong>应用名</strong></td>
                <td width="60%" align="center"><strong>是否允许匿名访问</strong></td>
            </tr>
            <g:each in="${applicationInstanceList}" status="i" var="applicationInstance">
                <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">

                    <td><g:link action="edit" id="${applicationInstance.id}">${fieldValue(bean: applicationInstance, field: "applicationId")}</g:link></td>

                    <td>${applicationInstance.isSupportAnonymous?"允许":"不允许"}</td>

                </tr>
            </g:each>
        </table>
        <div class="pagination">
            <g:paginate total="${applicationInstanceCount ?: 0}" />
        </div>
		</div>
        </div>
	</body>
</html>
