<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2009-5-15
  Time: 12:21:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/metaMocha.jsp" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>公告信息查看</title>
<link href="${themesPath}/css/style.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="${scriptsPath}/system/function.js"></script>
</head>

<body leftmargin="0" topmargin="0" marginwidth="0" marginheight="0">
        <table width="100%" height="100%" border="0" align="center" cellpadding="0" cellspacing="1">

                <tr>
                  <td valign="top">
                      <table width="100%" border="0" cellspacing="0" cellpadding="0">
                    <tr>
                      <td width="4" height="4" background="${themesPath}/oldimages/bg/shang.gif"><div align="right"><img src="${themesPath}/oldimages/bg/1.gif" width="4" height="4"></div></td>
                      <td height="4" background="${themesPath}/oldimages/bg/shang.gif"></td>
                      <td width="4" height="4" align="right"><img src="${themesPath}/oldimages/bg/2.gif" width="4" height="4"></td>
                    </tr>
                    <tr>
                      <td width="4" background="${themesPath}/oldimages/bg/zuo.gif"><img src="${themesPath}/oldimages/bg/zuo.gif" width="4" height="4"></td>
                      <td valign="top" bgcolor="#FFFFFF">
                      <table width="100%" border="0" cellpadding="0" cellspacing="1">
                            <tr class="textone1">
                              <td width="30%"><div align="right">标题：</div></td>
                              <td width="70%">&nbsp;${name}</td>
                            </tr>
                            <tr class="textone12">
                              <td><div align="right">发布时间：</div></td>
                              <td>&nbsp;${starttime}</td>
                            </tr>
                          <tr class="textone1">
                              <td><div align="right">结束时间：</div></td>
                              <td>&nbsp;${endtime}</td>
                          </tr>
                          <tr class="textone12">
                              <td><div align="right">内容：</div></td>
                              <td>&nbsp;${description}</td>
                            </tr>
                          <%--<tr class="textone12">--%>
                              <%--<td><div align="right">状态：</div></td>--%>
                              <%--<td>&nbsp;${status  ? "已发布" : "未发布"}</td>--%>
                            <%--</tr>--%>

                          <tr class="textone1">
                              <td><div align="right">发布人：</div></td>
                              <td>&nbsp;${adduser.displayname}</td>
                            </tr>
                          <%--<s:if test="map.myuser == adduser.id">--%>
                          <%--<tr class="textone1">--%>
                              <%--<td><div align="right">仅限查看用户：</div></td>--%>
                              <%--<td>&nbsp;${map.viewusernames}</td>--%>
                            <%--</tr>--%>
                              <%--</s:if>--%>
                        </table>
                      </td>
                      <td width="4" background="${themesPath}/oldimages/bg/you.gif"><img src="${themesPath}/oldimages/bg/you.gif" width="4" height="4"></td>
                    </tr>
                    <tr>
                      <td width="4" height="4" background="${themesPath}/oldimages/bg/xia.gif"><div align="right"><img src="${themesPath}/oldimages/bg/3.gif" width="4" height="4"></div></td>
                      <td height="4" background="${themesPath}/oldimages/bg/xia.gif"></td>
                      <td width="4" align="right"><img src="${themesPath}/oldimages/bg/4.gif" width="4" height="4"></td>
                    </tr><tr>
                <td colspan="3"><table width="100%" border="0" cellspacing="1" cellpadding="0">
                    <tr>
                      <td width="45%"><div align="right">
                      </div></td>
                      <td width="10%"><div align="center">
                          <%--<input type="button" class="button_cc" name="input" value="关 闭" onClick="closewindow('${name}');">--%>
                      </div></td>
                      <td width="45%"></td>
                    </tr>
                </table></td>
              </tr>
                  </table>
                  </td>
                </tr>

        </table>
</body>
</html>
