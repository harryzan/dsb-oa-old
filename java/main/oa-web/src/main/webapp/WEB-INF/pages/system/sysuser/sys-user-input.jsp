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
<title>维护用户信息</title>
<link href="${themesPath}/oldcss/style.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="${scriptsPath}/system/function.js"></script>
</head>

<body leftmargin="0" topmargin="0" marginwidth="0" marginheight="0">
<%--<table width="100%"  border="0" cellpadding="0" cellspacing="0">--%>
      <%--<tr>--%>
        <%--<td width="10" height="27" nowrap background="${themesPath}/oldimages/bgzuo.gif">&nbsp;</td>--%>
        <%--<td background="${themesPath}/oldimages/bgz.gif"><table width="100%" height="10"  border="0" align="right" cellpadding="0" cellspacing="2">--%>
            <%--<tr>--%>
              <%--<td width="20" class="textone">&nbsp;</td>--%>
              <%--<td height="23" valign="bottom" class="textone" align="center"><strong style="font-weight:bold;">维护用户信息</strong></td>--%>
              <%--<td width="20">&nbsp;</td>--%>
            <%--</tr>--%>
        <%--</table></td>--%>
        <%--<td width="10" height="27" nowrap background="${themesPath}/oldimages/bgyou.gif">&nbsp;</td>--%>
      <%--</tr>--%>
      <%--<tr>--%>
        <%--<td background="${themesPath}/oldimages/bgtua.gif">&nbsp;</td>--%>
        <%--<td valign="top" bgcolor="#eff6fe">--%>
        <table width="100%" height="100%" border="0" align="center" cellpadding="0" cellspacing="1">
            <form action="sys-user!save" method="post" onsubmit="javascript:return check()" >
                <input type="hidden" name="sysdeptid" value="${sysdeptid}${sysdept.id}">
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
                            <td width="32%"><div align="right">用户名：</div></td>
                            <td width="68%">&nbsp;<input id="loginname" name="loginname" type="text" class="input_one" value="${loginname}" onblur="unique();">&nbsp;<span class="textxing">*</span><span id ="load"></span></td>
                          </tr>
                          <tr class="textone12">
                            <td><div align="right">显示名：</div></td>
                            <td>&nbsp;<input name="displayname" id="displayname" type="text" class="input_one" value="${displayname}">&nbsp;<span class="textxing">*</span>
                              </td>
                          </tr>
                          <tr class="textone1">
                            <td><div align="right">手机：</div></td>
                            <td>&nbsp;<input name="phonenumber" id="phonenumber" type="text" class="input_one" value="${phonenumber}">&nbsp;<span class="textxing">*</span>
                              </td>
                          </tr>
                          <tr class="textone12">
                            <td><div align="right">邮箱：</div></td>
                            <td>&nbsp;<input name="email" id="email" type="text" class="input_one" value="${email}">&nbsp;<span class="textxing">*</span>
                              </td>
                          </tr>
                          <tr class="textone1">
                            <td><div align="right">密码：</div></td>
                            <td>&nbsp;<input name="password" id="password" type="password" class="input_one" value="" onblur="nullcheck();">&nbsp;<span id="pwdtext1"></span></td>
                          </tr>
                          <tr class="textone12">
                            <td><div align="right">确认密码：</div></td>
                            <td>&nbsp;<input name="password2" id="password2" type="password" class="input_one" value="" onblur="passwordcheck();">&nbsp;<span id ="pwdtext2"></span>
							</td>
                          </tr>
                          <tr class="textone1">
                            <td><div align="right">状态：</div></td>
                            <td>&nbsp;<input name="status" id="status" type="checkbox" value="true" onblur="passwordcheck();" <s:if test="status">checked="checked"</s:if>>
							</td>
                          </tr>
                        </table>

                        <table width="100%" border="0" cellpadding="0" cellspacing="1">
                          <tr class="textone12">
                            <td colspan="4"><div align="center"><strong>设置角色</strong></div></td>
                          </tr>
                          <s:iterator value="colsysrole" status="sta">
                              ${((sta.index % 4) == 0) && (((sta.index / 4) % 2) == 0) ? "<tr class=\"textone1\">":""}
                              ${((sta.index % 4) == 0) && (((sta.index / 4) % 2) != 0) ? "<tr class=\"textone12\">":""}
                                     <td width="25%" height="26">
                                         <div align="center">
                                              &nbsp;<s:if test="!isnull"><input type="checkbox" id="${sysrole.id}" name="sysroleids" value="${sysrole.id}" ${checked?"checked=\"checked\"":""}>${sysrole.name}</s:if>
                                         </div></td>
                               ${((sta.index % 4) == 3) ? "</tr>":""}
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
                      <input type="submit" class="button_bc" name="" value="保 存">
					  </div></td>
                    <td width="10%"><div align="center">
                      <input type="reset" class="button_cc" name="" value="重 写">
                    </div></td>
                    <td width="45%"><input type="button" class="button_cc" name="" value="返 回" onclick="location='${ctx}/common/blank'"></td>
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

    var bunique = true;

    function unique(){
        var id= "${id}";
        var load = document.getElementById("load");
        load.innerHTML = "<img src = \"${themesPath}/oldimages/loading.gif\">";
        var loginname = document.getElementById("loginname").value;
//        alert(loginname)
        var ret = getUrlContent("${ctx}/common/util/ajax-util!uniqueloginname?entityId=${id}&loginname=" + loginname + "&tail=" + Math.random(), "POST",null);
//        alert(ret);
        if(ret.indexOf("true") >= 0){
            load.innerHTML = "<img src = \"${themesPath}/oldcss/web_icon_006.gif\">";
            bunique = true;
            if(id.length <= 0){
                document.getElementById("id").value = getElementValue(ret, "id");
                document.getElementById("displayname").value = getElementValue(ret, "displayname");
                var roleids = getElementValue(ret, "roleids").split(",");
                for(var i = 0; i < roleids.length; i++){
                    document.getElementById(roleids[i]).checked = "checked";
                }
            }
        }
        else {
            load.innerHTML = "<img src  =\"${themesPath}/oldcss/sc.gif\">";
            bunique = false;
        }
    }

    function nullcheck(){
        var password = document.getElementById("password").value;
        document.getElementById("pwdtext1").innerHTML = "";
        if(password.length <= 0){
            document.getElementById("pwdtext1").innerHTML = "<img src  =\"${themesPath}/oldcss/sc.gif\">密码为空,请重新填写！";
        }
        else {
            document.getElementById("pwdtext1").innerHTML = "<img src = \"${themesPath}/oldcss/web_icon_006.gif\">";
        }
    }

    function passwordcheck(){
        document.getElementById("pwdtext2").innerHTML = "";
        var password = document.getElementById("password").value;
        var password2 = document.getElementById("password2").value;
        if(password.length <= 0){
            document.getElementById("pwdtext2").innerHTML = "<img src  =\"${themesPath}/oldcss/sc.gif\">密码为空,请重新填写！";
        }
        else if(password != password2){
            document.getElementById("pwdtext2").innerHTML = "<img src  =\"${themesPath}/oldcss/sc.gif\">密码确认错误,请检查后重试！";
        }
        else {
            document.getElementById("pwdtext2").innerHTML = "<img src = \"${themesPath}/oldcss/web_icon_006.gif\">";
        }
    }

    function check(){
        if(!bunique) {
            Ext.MessageBox.alert("提示", "用户名已经被注册，请重新填写用户名！");
            return false;
        }

        var loginname = document.getElementById("loginname").value;
        var displayname = document.getElementById("displayname").value;
        var phonenumber = document.getElementById("phonenumber").value;
        var email = document.getElementById("email").value;
        var password = document.getElementById("password").value;
        var password2 = document.getElementById("password2").value;
        var regist = /^[ ]{0,}$/;
        if(loginname.length <= 0 || regist.exec(loginname)){
            Ext.MessageBox.alert("提示", "用户名不能为空！");
            return false;
        }
        if(displayname.length <= 0 || regist.exec(displayname)){
            Ext.MessageBox.alert("提示", "显示名称不能为空！");
            return false;
        }
        if(phonenumber.length <= 0 || regist.exec(phonenumber)){
            Ext.MessageBox.alert("提示", "手机号不能为空！");
            return false;
        }
        if(phonenumber.length != 11){
            Ext.MessageBox.alert("提示", "手机号不符合规则！");
            return false;
        }
        if(email.length <= 0 || regist.exec(email)){
            Ext.MessageBox.alert("提示", "邮箱不能为空！");
            return false;
        }
        if(email.indexOf("@") < 0){
            Ext.MessageBox.alert("提示", "邮箱不符合规则！");
            return false;
        }
        if(password.length <= 0){
            Ext.MessageBox.alert("提示", "密码不能为空！");
            return false;
        }
        if(password != password2){
            Ext.MessageBox.alert("提示", "密码确认错误！");
            return false;
        }
        return true;
    }
</script>
</body>
</html>
