<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2009-5-19
  Time: 10:18:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>角色信息查看</title>
<link href="${themesPath}/oldcss/style.css" rel="stylesheet" type="text/css">
</head>
    <script language="javascript">
        if (parent.tree_frame != null)
            parent.tree_frame.updateParent(parent.tree_frame.lastSelectedNode);
    </script>
<body leftmargin="0" topmargin="0" marginwidth="0" marginheight="0">
<%--<table width="100%"  border="0" cellpadding="0" cellspacing="0">--%>
      <%--<tr>--%>
        <%--<td width="10" height="27" nowrap background="${themesPath}/oldimages/bgzuo.gif">&nbsp;</td>--%>
        <%--<td background="${themesPath}/oldimages/bgz.gif"><table width="100%" height="10"  border="0" align="right" cellpadding="0" cellspacing="2">--%>
            <%--<tr>--%>
              <%--<td width="20" class="textone">&nbsp;</td>--%>
              <%--<td height="23" valign="bottom" class="textone" align="center"><strong>维护角色信息</strong></td>--%>
              <%--<td width="20">&nbsp;</td>--%>
            <%--</tr>--%>
        <%--</table></td>--%>
        <%--<td width="10" height="27" nowrap background="${themesPath}/oldimages/bgyou.gif">&nbsp;</td>--%>
      <%--</tr>--%>
      <%--<tr>--%>
        <%--<td background="${themesPath}/oldimages/bgtua.gif">&nbsp;</td>--%>
        <%--<td valign="top" bgcolor="#eff6fe">--%>
        <table width="100%" height="100%" border="0" align="center" cellpadding="0" cellspacing="1">
                <tr>
                  <td valign="top"><table width="100%" border="0" cellspacing="0" cellpadding="0">
                    <tr>
                      <td width="4" height="4" background="${themesPath}/oldimages/bg/shang.gif"><div align="right"><img src="${themesPath}/oldimages/bg/1.gif" width="4" height="4"></div></td>
                      <td height="4" background="${themesPath}/oldimages/bg/shang.gif"></td>
                      <td width="4" height="4" align="right"><img src="${themesPath}/oldimages/bg/2.gif" width="4" height="4"></td>
                    </tr>
                    <tr>
                      <td width="4" background="${themesPath}/oldimages/bg/zuo.gif"><img src="${themesPath}/oldimages/bg/zuo.gif" width="4" height="4"></td>
                      <td valign="top" bgcolor="#FFFFFF"><table width="100%" border="0" cellpadding="0" cellspacing="1">
                          <tr class="textone1">
                            <td width="32%"><div align="right">角色名称：</div></td>
                            <td width="68%">&nbsp;${name}<span class="textxing"></span></td>
                          </tr>
                          <tr class="textone12">
                            <td height="90"><div align="right">角色描述：</div></td>
                            <td>&nbsp;${description}</td>
                          </tr>
                          <tr >
                            <td colspan="2" align="center">
                            <table width="100%" border="0" align="center" cellpadding="0" cellspacing="1">
                                <tr class="textone1"><td colspan="4"><div align="center">拥有该角色的用户列表</div></td></tr>
                                <tr class="textone1">
                                    <td width="3%" height="25" ><div align="center">#</div></td>
                                    <td width="31%" ><div align="center">登录ID</div></td>
                                    <td width="31%" ><div align="center">用户名</div></td>
                                    <td width="31%" ><div align="center">所属部门</div></td>
                                </tr>
                                    <s:iterator value="sysRoleUsers" id="his" status="sta">
                                <tr class="textone12">
                                    <td ><div align="center">${sta.count}</div></td>
                                    <td ><div align="center">${loginname}</div></td>
                                    <td ><div align="center">${displayname}</div></td>
                                    <td ><div align="center">${sysdept.name}</div></td>
                                </tr>
                                    </s:iterator>
                            </table></td>
                        </tr>
                      </table></td>
                      <td width="4" background="${themesPath}/oldimages/bg/you.gif"><img src="${themesPath}/oldimages/bg/you.gif" width="4" height="4"></td>
                    </tr>
                    <tr>
                      <td width="4" height="4" background="${themesPath}/oldimages/bg/xia.gif"><div align="right"><img src="${themesPath}/oldimages/bg/3.gif" width="4" height="4"></div></td>
                      <td height="4" background="${themesPath}/oldimages/bg/xia.gif"></td>
                      <td width="4" align="right"><img src="${themesPath}/oldimages/bg/4.gif" width="4" height="4"></td>
                    </tr>
                  </table>
                  </td>
                </tr>
              <tr>
                <td><table width="100%" border="0" cellspacing="1" cellpadding="0">
                  <tr>
                    <td width="45%"><div align="right">
                    </div></td>

                    <td width="10%"><div align="center">
                      &nbsp;<%--<input type="button" class="button_cc" name="" value="返 回" onClick="location='biaoge4one.htm'">--%>
                    </div></td>
				 <td width="45%">&nbsp;</td>
                  </tr>
                </table></td>
              </tr>
            </form>
        </table>
        <%--</td><td background="${themesPath}/oldimages/bgtub.gif">&nbsp;</td>--%>
      <%--</tr>--%>
      <%--<tr>--%>
        <%--<td valign="top"><img src="${themesPath}/oldimages/bgtuc.gif" width="10" height="11"></td>--%>
        <%--<td height="11" background="${themesPath}/oldimages/bgxia.gif"></td>--%>
        <%--<td valign="top"><img src="${themesPath}/oldimages/bgtud.gif" width="10" height="11"></td>--%>
      <%--</tr>--%>
    <%--</table>--%>
</body>
</html>
