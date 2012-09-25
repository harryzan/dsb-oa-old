<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>403 - 缺少权限</title>
    <%@ include file="/common/meta.jsp" %>
    <link href="${themesPath}/css/style.css" rel="stylesheet" type="text/css">            
</head>

<body>
<br>
<table width="395" height="243" border="0" align="center" cellpadding="0" cellspacing="0">
    <tr>
        <td background="${themesPath}/images/error.jpg">
            <table width="240" height="180" border="0" align="center" cellpadding="0" cellspacing="0">
                <tr>
                    <td colspan="2" height="40"></td>
                </tr>
                <tr>
                    <td width="140">&nbsp;</td>
                    <td height="130" style="word-break:break-all" class="text1">
                        你没有访问该页面的权限！&nbsp;
                    </td>
                </tr>
                <tr>
                    <td height="30" align="center">&nbsp; </td>
                    <td class="text1" >
                        <div><a href="<c:url value="/main"/>">返回首页</a>&nbsp;&nbsp;<a href="<c:url value="/${ctx}/logon!logout"/>">退出登录</a></div>
                    </td>
                </tr>
                <tr>
                    <td colspan="2" height="20"></td>
                </tr>
            </table>
        </td>
    </tr>
</table>
</body>
</html>