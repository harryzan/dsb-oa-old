<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2009-5-15
  Time: 12:10:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/metaGrid.jsp" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>车辆信息维护</title>

<link href="${themesPath}/oldcss/style.css" rel="stylesheet" type="text/css">

    <script type="text/javascript" src="${scriptsPath}/system/calendar.js"></script>
    <script type="text/javascript" src="${scriptsPath}/system/function.js"></script>
</head>

<body leftmargin="0" topmargin="0" marginwidth="0" marginheight="0">
<%--<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">--%>
  <%--<tr>--%>
    <%--<td width="13" height="12" valign="top" background="${themesPath}/images/azuo.gif"><img src="${themesPath}/images/a1.gif" width="13" height="13"></td>--%>
    <%--<td background="${themesPath}/images/ashang.gif"></td>--%>
    <%--<td width="12" height="12" valign="top" nowrap background="${themesPath}/images/ayou.gif"><img src="${themesPath}/images/a2.gif" width="13" height="13"></td>--%>
  <%--</tr>--%>
  <%--<tr>--%>
    <%--<td background="${themesPath}/images/azuo.gif"></td>--%>
    <%--<td  valign="top" bgcolor="#fbfbfb"><table width="100%"  border="0" cellpadding="0" cellspacing="0">--%>
      <%--<tr>--%>
        <%--<td width="10" height="27" nowrap background="${themesPath}/images/bgzuo.gif">&nbsp;</td>--%>
        <%--<td background="${themesPath}/images/bgz.gif"><table width="100%" height="10"  border="0" align="right" cellpadding="0" cellspacing="2">--%>
            <%--<tr>--%>
              <%--<td width="20" class="textone">&nbsp;</td>--%>
              <%--<td height="23" valign="bottom" class="textone" align="center"><strong style="font-weight:bold;">维护型号信息</strong></td>--%>
              <%--<td width="20">&nbsp;</td>--%>
            <%--</tr>--%>
        <%--</table></td>--%>
        <%--<td width="10" height="27" nowrap background="${themesPath}/images/bgyou.gif">&nbsp;</td>--%>
      <%--</tr>--%>
      <%--<tr>--%>
        <%--<td background="${themesPath}/images/bgtua.gif">&nbsp;</td>--%>
        <%--<td valign="top" bgcolor="#eff6fe">--%>
        <table width="100%" height="100%" border="0" align="center" cellpadding="0" cellspacing="1">
            <form action="demand-type!save?id=${id}" method="post" onsubmit="javascript:return check_form(this)">
                <input type ="hidden" name="gridParam" value='${gridParam}'>
                <tr>
                  <td valign="top"><table width="100%" border="0" cellspacing="0" cellpadding="0">
                    <tr>
                      <td width="4" height="4" background="${themesPath}/images/bg/shang.gif"><div align="right"><img src="${themesPath}/images/bg/1.gif" width="4" height="4"></div></td>
                      <td height="4" background="${themesPath}/images/bg/shang.gif"></td>
                      <td width="4" height="4" align="right"><img src="${themesPath}/images/bg/2.gif" width="4" height="4"></td>
                    </tr>
                    <tr>
                      <td width="4" background="${themesPath}/images/bg/zuo.gif"><img src="${themesPath}/images/bg/zuo.gif" width="4" height="4"></td>
                      <td valign="top" bgcolor="#FFFFFF"><table width="100%" border="0" cellpadding="0" cellspacing="1">
                          <tr class="textone1">
                            <td width="30%"><div align="right">需求名称：</div></td>
                            <td width="70%">&nbsp;
                                <input name="name" type="text" class="input_one" value="${name}">
                                <span class="textxing">*</span></td>
                          </tr>
                          <tr class="textone12">
                              <td><div align="right">描述：</div></td>
                              <td height="60">&nbsp;
                                  <textarea name="desc" class="input_five">${desc}</textarea></td>
                          </tr>
                          <tr class="textone1">
                              <td width="30%"><div align="right">负责人：</div></td>
                              <td width="70%">&nbsp;
                                  <%--<s:date name="buydate" format="yyyy-MM-dd"/>--%>
                                  <%--<input id="buydate" name="buydate" type="text" class="input_one" value="${buydate}"><img src="${themesPath}/oldimages/calendar.gif"  width="13" height="12" style="cursor:pointer;" onclick="calendar(buydate, 'date');" />--%>
                                  <input id="username" name="username" type="text" class="input_one" value="${user.displayname}" onclick="user()" >
                                  <input id="userid" type ="hidden" name="userid" value="${user.id}">
                                  <img src="${themesPath}/oldcss/cl.gif" width="16" height="16" style="cursor:pointer" onclick="user()" >
                              </td>
                          </tr>
                          <%--<tr class="textone12">--%>
                            <%--<td><div align="right">其他属性：</div></td>--%>
                            <%--<td height="60">&nbsp;--%>
                                <%--<textarea name="misc" class="input_four">${misc}</textarea></td>--%>
                          <%--</tr>--%>
                          <%--<tr class="textone1">--%>
                            <%--<td><div align="right">其他说明：</div></td>--%>
                            <%--<td height="60">&nbsp;--%>
                                <%--<textarea name="note" class="input_four">${note}</textarea></td>--%>
                          <%--</tr>--%>
                          <%--<tr class="textone12">--%>
                            <%--<td><div align="right">照片：</div></td>--%>
                            <%--<td>&nbsp;<span class="textxing" style ="cursor:pointer;" onclick="docdocument();">关联图片文档</span><input type ="hidden" name="documentid" value="${docdocument.id}" ></td>--%>
                          <%--</tr>--%>

                      </table></td>
                      <td width="4" background="${themesPath}/images/bg/you.gif"><img src="${themesPath}/images/bg/you.gif" width="4" height="4"></td>
                    </tr>
                    <tr>
                      <td width="4" height="4" background="${themesPath}/images/bg/xia.gif"><div align="right"><img src="${themesPath}/images/bg/3.gif" width="4" height="4"></div></td>
                      <td height="4" background="${themesPath}/images/bg/xia.gif"></td>
                      <td width="4" align="right"><img src="${themesPath}/images/bg/4.gif" width="4" height="4"></td>
                    </tr><tr>
                            <td colspan="3" bgcolor="#eff6fe"><table width="100%" border="0" cellspacing="1" cellpadding="0">
                                <tr valign="top">
                                  <td width="45%"><div align="right">
                                      <input type="submit" class="button_bc" name="input" value="保 存">
                                  </div></td>
                                  <td width="10%"><div align="center">
                                      <input type="reset" class="button_cc" name="input" value="重 写">
                                  </div></td>
                                  <td width="45%"><input type="button" class="button_cc" name="input" value="返 回" onClick="history.back()"></td>
                                </tr>
                            </table></td>
                          </tr>
                  </table>
                  </td>
                </tr>

            </form>
        </table>
        <%--</td><td background="${themesPath}/images/bgtub.gif">&nbsp;</td>--%>
      <%--</tr>--%>
      <%--<tr>--%>
        <%--<td valign="top"><img src="${themesPath}/images/bgtuc.gif" width="10" height="11"></td>--%>
        <%--<td height="11" background="${themesPath}/images/bgxia.gif"></td>--%>
        <%--<td valign="top"><img src="${themesPath}/images/bgtud.gif" width="10" height="11"></td>--%>
      <%--</tr>--%>
    <%--</table></td>--%>
    <%--<td background="${themesPath}/images/ayou.gif">&nbsp;</td>--%>
  <%--</tr>--%>
  <%--<tr>--%>
    <%--<td width="13" height="12" background="${themesPath}/images/axia.gif"><img src="${themesPath}/images/a3.gif" width="13" height="13"></td>--%>
    <%--<td background="${themesPath}/images/axia.gif"></td>--%>
    <%--<td width="19" height="12" valign="top"><img src="${themesPath}/images/a4.gif" width="13" height="13"></td>--%>
  <%--</tr>--%>
<%--</table>--%>
<script type="text/javascript">
    function check_form(afrom){
        if(afrom.carmodel.value == ""){
            Ext.MessageBox.alert("提示", "车辆型号不能为空！");
            return false;
        }
        return true;
    }

    function user(){
        var returnvalue = window.showModalDialog("${ctx}/common/tree/sys-user-tree", 1);
        if(returnvalue){
            document.getElementById("username").value = returnvalue.split(",")[0];
            document.getElementById("userid").value = returnvalue.split(",")[1];
        }
    }
</script>
</body>
</html>