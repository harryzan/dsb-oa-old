<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2009-5-19
  Time: 20:04:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
    <%@ include file="/common/metaMocha.jsp" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>文档信息查看</title>
<link href="${themesPath}/oldcss/style.css" rel="stylesheet" type="text/css">
</head>

<body leftmargin="0" topmargin="0" marginwidth="0" marginheight="0">
<%--<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">--%>
  <%--<tr>--%>
    <%--<td width="13" height="12" valign="top" background="${themesPath}/oldimages/azuo.gif"><img src="${themesPath}/oldimages/a1.gif" width="13" height="13"></td>--%>
    <%--<td background="${themesPath}/oldimages/ashang.gif"></td>--%>
    <%--<td width="12" height="12" valign="top" nowrap background="${themesPath}/oldimages/ayou.gif"><img src="${themesPath}/oldimages/a2.gif" width="13" height="13"></td>--%>
  <%--</tr>--%>
  <%--<tr>--%>
    <%--<td background="${themesPath}/oldimages/azuo.gif"></td>--%>
    <%--<td  valign="top" bgcolor="#fbfbfb"><table width="100%"  border="0" cellpadding="0" cellspacing="0">--%>
      <%--<tr>--%>
        <%--<td width="10" height="27" nowrap background="${themesPath}/oldimages/bgzuo.gif">&nbsp;</td>--%>
        <%--<td background="${themesPath}/oldimages/bgz.gif"><table width="100%" height="10"  border="0" align="right" cellpadding="0" cellspacing="2">--%>
            <%--<tr>--%>
              <%--<td width="20" class="textone">&nbsp;</td>--%>
              <%--<td height="23" valign="bottom" class="textone" align="center"><strong>文档目录</strong></td>--%>
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
                      <td valign="top" bgcolor="#FFFFFF"><table width="100%" border="0" cellspacing="1" cellpadding="0">
                        <tr class="textone1">
                          <td width="20%"><div align="right">原始编号：</div></td>
                          <td width="20%">&nbsp;${origincode}</td>
                          <td width="15%"><div align="right">编号：</div></td>
                          <td width="30%">&nbsp;${code}</td>
                          </tr>
                        <tr class="textone12">
                          <td><div align="right">标题：</div></td>
                          <td colspan="3">&nbsp;${name}</td>
                          </tr>
                        <tr class="textone1">
                          <td><div align="right">作者：</div></td>
                          <td>&nbsp;${author}</td>
                          <td><div align="right">文档性质：
                            </div></td>
                          <td>&nbsp;${property.listname}</td>
                          </tr>
                        <tr class="textone12">
                          <td><div align="right">创建者：</div></td>
                          <td>&nbsp;${createuser.displayname}</td>
                          <td><div align="right">创建时间：</div></td>
                          <td>&nbsp;<fmt:formatDate value="${createtime}" pattern="yyyy-MM-dd"/> </td>
                          </tr>
                        <tr class="textone1">
                          <td><div align="right">最后修改者：</div></td>
                          <td nowrap>&nbsp;${updateuser.displayname}</td>
                          <td><div align="right">最后修改时间：</div></td>
                          <td>&nbsp;<fmt:formatDate value="${updatetime}" pattern="yyyy-MM-dd"/></td>
                          </tr>
                        <tr class="textone12">
                          <td><div align="right">关键字：</div></td>
                          <td>&nbsp;${keywords}</td>
                          <td><div align="right">文档格式：</div></td>
                          <td>&nbsp;${format.listname}</td>
                        </tr>
                        <tr class="textone1">
                          <td><div align="right">单位：</div></td>
                          <td colspan="3">&nbsp;${sysdept.name}</td>
                          </tr>
                        <tr class="textone12">
                          <td><div align="right">摘要：</div></td>
                          <td colspan="3">&nbsp;${abstractcontent}</td>
                        </tr>
                        <tr class="textone1">
                          <td><div align="right">描述：</div></td>
                          <td colspan="3">&nbsp;${description}</td>
                        </tr>
                      </table>
                        <table width="100%" border="0" cellspacing="0" cellpadding="0">
                          <tr>
                            <td height="25" colspan="4"><div align="center" class="text"><strong>附件列表</strong></div></td>
                            </tr>
                          <tr class="textoneA">
                            <td width="33%" background="${themesPath}/oldcss/diandi.gif"><div align="center">文件名称</div></td>
                            <td width="33%" background="${themesPath}/oldcss/diandi.gif"><div align="center">说明</div></td>
                            <td width="33%" background="${themesPath}/oldcss/diandi.gif"><div align="center">备注</div></td>
                            </tr>
                            <s:iterator value ="attachs">
                          <tr class="textoneA">
                            <td><div align="center"><a href="${ctx}/common/document/doc-attach!download?id=${id}" target="_blank">${filename}</a></div></td>
                            <td><div align="center">${content}</div></td>
                            <td><div align="center">${description}</div></td>
                            </tr>
                            </s:iterator>
                        </table></td>
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
                          <input type="button" class="button_cc" name="input" value="关 闭" onClick="closewindow('${name}');">
                      </div></td>
                      <td width="45%"></td>
                    </tr>
                </table></td>
              </tr>
                  </table>
                  </td>
                </tr>

        </table>
        <%--</td><td background="${themesPath}/oldimages/bgtub.gif">&nbsp;</td>--%>
      <%--</tr>--%>
      <%--<tr>--%>
        <%--<td valign="top"><img src="${themesPath}/oldimages/bgtuc.gif" width="10" height="11"></td>--%>
        <%--<td height="11" background="${themesPath}/oldimages/bgxia.gif"></td>--%>
        <%--<td valign="top"><img src="${themesPath}/oldimages/bgtud.gif" width="10" height="11"></td>--%>
      <%--</tr>--%>
    <%--</table></td>--%>
    <%--<td background="${themesPath}/oldimages/ayou.gif">&nbsp;</td>--%>
  <%--</tr>--%>
  <%--<tr>--%>
    <%--<td width="13" height="12" background="${themesPath}/oldimages/axia.gif"><img src="${themesPath}/oldimages/a3.gif" width="13" height="13"></td>--%>
    <%--<td background="${themesPath}/oldimages/axia.gif"></td>--%>
    <%--<td width="19" height="12" valign="top"><img src="${themesPath}/oldimages/a4.gif" width="13" height="13"></td>--%>
  <%--</tr>--%>
<%--</table>--%>
</body>
</html>
