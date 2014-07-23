<%@ page import="com.zy.zds.auth.Application" %>

<table>
    <tr>
        <td class="fieldcontain ${hasErrors(bean: applicationInstance, field: 'applicationId', 'error')} required">应用名</td>
        <td><g:textField name="applicationId" required="" value="${applicationInstance?.applicationId}"/></td>
    </tr>
    <tr>
        <td class="fieldcontain ${hasErrors(bean: applicationInstance, field: 'isSupportAnonymous', 'error')} ">是否启用</td>
        <td><g:checkBox name="isSupportAnonymous" value="${applicationInstance?.isSupportAnonymous}" /></td>
    </tr>
</table>

