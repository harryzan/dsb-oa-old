<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" isErrorPage="true" %>
<%@ page import="org.slf4j.Logger" %>
<%@ page import="org.slf4j.LoggerFactory" %>
<%@ page import="com.justone.core.utils.StringHelp" %>
<%@ include file="/common/taglibs.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>500 - 系统内部错误</title>
    <%@ include file="/common/meta.jsp" %>
    <link href="${themesPath}/css/style.css" rel="stylesheet" type="text/css">    
</head>
<%
    Throwable ex = null;
    if (exception != null) {
        ex = exception;
    }
    if (request.getAttribute("javax.servlet.error.exception") != null) {
        ex = (Exception) request.getAttribute("javax.servlet.error.exception");
    }

    //记录日志
    Logger logger = LoggerFactory.getLogger("500.jsp");
    logger.error(ex.getMessage(), ex);

    boolean isSessionFail = false;
    String msg = "系统发生内部错误！";
    if (ex.getMessage().indexOf("请先登录系统") > -1) {
        msg = "用户Session已过期，请重新登录！";
        isSessionFail = true;
    }
    else if(ex.getMessage().indexOf("用户名或密码错误") > -1){
        msg = ex.getMessage();
        isSessionFail = true;
    }
    else {
        msg = ex.getMessage();
    }
//    else if (ex.getMessage().indexOf("没有找到文档目录") > -1) {
//        msg = StringHelp.getElementValue(ex.getMessage(),"java.lang.RuntimeException: 根据","没有找到文档目录");
//        if(!StringHelp.isEmpty(msg)){
//            msg = "根据业务文档编码["+msg+"]没有找到文档目录，请先添加对应文档目录！";
//        }
//    }
%>
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
                    <td width="80">&nbsp;</td>
                    <td height="130" style="word-break:break-all" class="text1" >
                        <%=msg%>&nbsp;
                    </td>
                </tr>
                <tr>
                    <td height="30" align="center">&nbsp; </td>
                    <td class="text1">
                        <%if (isSessionFail) { %>
                        <div><a href="javascript:top.location.href='${ctx}/';"><b>返回首页
                        </b></a></div>
                        <%} else {%>
                        <input type="button" value="返回" onClick="history.back();" class="button_back">
                        <%}%>
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
