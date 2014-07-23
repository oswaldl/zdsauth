
<%@ page import="com.zy.zds.auth.ApplicationUser" %>
<!DOCTYPE html>
<html>
	<head>
        <link rel="stylesheet" type="text/css" href="${resource(dir: 'css', file: 'style.css')}" />
        <link rel="stylesheet" type="text/css" href="${resource(dir: 'css', file: 'console_style.css')}" />
		<g:set var="entityName" value="${message(code: 'applicationUser.label', default: 'ApplicationUser')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
<body>
<div class="content">
    <div class="contiter">
        <h2>用户应用权限列表</h2>
    </div>
    <div class="conmain">
        <a class="botton" href="create?applicationId=${applicationId }">新增</a>
    </div>
    <div class="conmain">
        <strong class="Orange">
            <g:if test="${flash.message}">
                <div class="message" role="status">${flash.message}</div>
            </g:if>
        </strong>
    </div>
    <div class="conmain">
        <div class="c_left">
            <ul>
                <g:each in="${com.zy.zds.auth.Application.list()}" var="line">
                    <li><a ${applicationId==line.id?'class="current"':'' } href="list?applicationId=${line?.id}" >${line?.name }</a></li>
                </g:each>
            </ul>
        </div>
        <div class="c_right">
            <table>
                <tr class="tbbg">
                    <td align="center">名称</td>
                </tr>
                <g:each in="${applicationUserInstanceList}" status="i" var="applicationUserInstance">
                    <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">

                        <td><g:link action="edit" id="${applicationUserInstance.id}">${applicationUserInstance.user.username}</g:link></td>

                    </tr>
                </g:each>
            </table>
            <div class="pagination">
                <g:paginate params='[applicationId:"${applicationId }"]' total="${applicationUserInstanceTotal}" />
            </div>
        </div>
    </div>
</div>
</body>
</html>
