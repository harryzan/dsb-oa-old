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
<title>单位管理－维护单位信息</title>
<link href="${themesPath}/oldcss/style.css" rel="stylesheet" type="text/css">

</head>

<body leftmargin="0" topmargin="0" marginwidth="0" marginheight="0">
<%--<table width="100%"  border="0" cellpadding="0" cellspacing="0">--%>
      <%--<tr>--%>
        <%--<td width="10" height="27" nowrap background="${themesPath}/oldimages/bgzuo.gif">&nbsp;</td>--%>
        <%--<td background="${themesPath}/oldimages/bgz.gif"><table width="100%" height="10"  border="0" align="right" cellpadding="0" cellspacing="2">--%>
            <%--<tr>--%>
              <%--<td width="20" class="textone">&nbsp;</td>--%>
              <%--<td height="23" valign="bottom" class="textone" align="center"><strong style="font-weight:bold;">维护单位信息</strong></td>--%>
              <%--<td width="20">&nbsp;</td>--%>
            <%--</tr>--%>
        <%--</table></td>--%>
        <%--<td width="10" height="27" nowrap background="${themesPath}/oldimages/bgyou.gif">&nbsp;</td>--%>
      <%--</tr>--%>
      <%--<tr>--%>
        <%--<td background="${themesPath}/oldimages/bgtua.gif">&nbsp;</td>--%>
        <%--<td valign="top" bgcolor="#eff6fe">--%>
        <table width="100%" height="100%" border="0" align="center" cellpadding="0" cellspacing="1">
            <form action="sys-dept!save" method="post" onsubmit="javascript:return judge();">
                <input type="hidden" name="parentid" value="${parentid}${parent.id}" >
                <input type ="hidden" name="id" value="${id}">
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
                            <td width="30%"><div align="right">编码：</div></td>
                            <td width="70%">&nbsp;<input name="code" id="code" type="text" class="input_one" value="${code}">
                              <span class="textxing">*</span></td>
                          </tr>
                          <tr class="textone12">
                            <td><div align="right">名称：</div></td>
                            <td>&nbsp;<input name="name" id="name" type="text" class="input_one" value="${name}">
                              <span class="textxing">*</span></td>
                          </tr>
                          <tr class="textone1">
                            <td><div align="right">类型：</div></td>
                            <td>&nbsp;<select name="type" class="input_xiao">
                              <option value="true" <s:if test="type">selected</s:if>>单位</option>
                              <option value="false" <s:if test="!type">selected</s:if>>部门</option>
                              </select>
                              <span class="textxing">*</span></td>
                          </tr>
                          <tr class="textone12">
                            <td><div align="right">简称：</div></td>
                            <td>&nbsp;<input name="shortname" type="text" class="input_one" value="${shortname}">
							</td>
                          </tr>
                          <tr class="textone1">
                            <td height="100"><div align="right">介绍：</div></td>
                            <td>&nbsp;<textarea name="profile" class="input_three">${profile}</textarea></td>
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
                      <input type="reset" class="button_cc" name="Submit52" value="重 写">
                    </div></td>
                    <td width="45%"><input type="button" class="button_cc" name="Submit522" value="返 回" onclick="location='${ctx}/common/blank'"></td>
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
    function judge(){
        var code = document.getElementById("code").value;
        var name = document.getElementById("name").value;
        var regist = /^[ ]{0,}$/;

        if(code.length <= 0 || regist.exec(code)){
            //alert("编码不能为空！");
            Ext.MessageBox.alert("提示", "编码不能为空！");
            return false;
        }
        if(name.length <= 0 || regist.exec(name)){
            Ext.MessageBox.alert("提示", "名称不能为空！");
            return false;
        }
        return true;
    }
</script>
</body>
</html>
