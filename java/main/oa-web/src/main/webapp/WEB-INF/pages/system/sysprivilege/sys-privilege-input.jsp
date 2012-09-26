<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2009-5-19
  Time: 10:18:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/metaGrid.jsp" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>维护权限信息</title>
<link href="${themesPath}/oldcss/style.css" rel="stylesheet" type="text/css">

</head>

<body leftmargin="0" topmargin="0" marginwidth="0" marginheight="0">
<%--<table width="100%"  border="0" cellpadding="0" cellspacing="0">--%>
      <%--<tr>--%>
        <%--<td width="10" height="27" nowrap background="${themesPath}/oldimages/bgzuo.gif">&nbsp;</td>--%>
        <%--<td background="${themesPath}/oldimages/bgz.gif"><table width="100%" height="10"  border="0" align="right" cellpadding="0" cellspacing="2">--%>
            <%--<tr>--%>
              <%--<td width="20" class="textone">&nbsp;</td>--%>
              <%--<td height="23" valign="bottom" class="textone" align="center"><strong style="font-weight:bold;">维护权限信息</strong></td>--%>
              <%--<td width="20">&nbsp;</td>--%>
            <%--</tr>--%>
        <%--</table></td>--%>
        <%--<td width="10" height="27" nowrap background="${themesPath}/oldimages/bgyou.gif">&nbsp;</td>--%>
      <%--</tr>--%>
      <%--<tr>--%>
        <%--<td background="${themesPath}/oldimages/bgtua.gif">&nbsp;</td>--%>
        <%--<td valign="top" bgcolor="#eff6fe">--%>
        <table width="100%" height="100%" border="0" align="center" cellpadding="0" cellspacing="1">
            <form action="sys-privilege!save?id=${id}" method="post" onsubmit="javascript:return check()">
                <input type="hidden" name="parentid" value="${parent.id}" >
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
                            <td width="32%"><div align="right">权限代码：</div></td>
                            <td width="68%">&nbsp;<input name="code" id="code" type="text" class="input_one" value="${code}">
                              <span class="textxing">*</span></td>
                          </tr>
                          <tr class="textone12">
                            <td><div align="right">权限标号：</div></td>
                            <td>&nbsp;<input name="tag" id="tag" type="text" class="input_one" value="${tag}">
                              <span class="textxing">*</span></td>
                          </tr>
                          <tr class="textone1">
                            <td><div align="right">权限名称：</div></td>
                            <td>&nbsp;<input name="name" id="name" type="text" class="input_one" value="${name}">
                              <span class="textxing">*</span></td>
                          </tr>
                          <tr class="textone12">
                            <td height="90"><div align="right">权限描述：</div></td>
                            <td height="100">&nbsp;<textarea name="definition" class="input_three">${definition}</textarea></td>
                          </tr>
                          <tr class="textone1">
                            <td><div align="right">权限类型：</div></td>
                            <td>&nbsp;<select name="privilegetypeid" class="input_zhong">
                                <s:iterator value="colprivilegetype">
                                    <option value="${id}" ${privilegetype.id == id?"selected":""}>${listname}</option>
                                </s:iterator>
                              </select>
                              <span class="textxing">*</span></td>
                          </tr>
                          <tr class="textone12">
                            <td height="90"><div align="right">备注：</div></td>
                            <td height="100">&nbsp;<textarea name="description" class="input_three">${description}</textarea></td>
                          </tr>
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
                      <input type="submit" class="button_bc" name="" value="保 存">
					  </div></td>
                    <td width="10%"><div align="center">
                      <input type="reset" class="button_cc" name="" value="重 写">
                    </div></td>
                    <td width="45%"><input type="button" class="button_cc" name="Submit522" value="返 回" onclick="history.back();"></td>
                  </tr>
                </table></td>
              </tr>
                  </table>
                  </td>
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
<script type="text/javascript">
    function check(){
        var code = document.getElementById("code").value;
        var tag  = document.getElementById("tag").value;
        var name = document.getElementById("name").value;

        var parten = /^[0-9]{1,10}$/;
        var regist = /^[ ]{0,}$/;


        if(code.trim().length <= 0 || regist.exec(code)){
            Ext.MessageBox.alert("提示", "权限代码不能为空！");
            return false;
        }

       if(!parten.exec(tag)){
           Ext.MessageBox.alert("提示", "权限标号必须是10位内的整数！");
            return false;
        }

        if(name.trim().length <= 0 || regist.exec(name)){
            Ext.MessageBox.alert("提示", "权限名称不能为空！");
            return false;
        }
        return true;
    }
</script>
</body>
</html>
