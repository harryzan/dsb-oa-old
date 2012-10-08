<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2009-5-19
  Time: 10:18:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/metaGrid.jsp"%>
<%@ include file="/common/metaMocha.jsp" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>登录用户密码修改</title>
<link href="${themesPath}/oldcss/style.css" rel="stylesheet" type="text/css">
    <script type="text/javascript" src="${scriptsPath}/system/function.js"></script>
	<script type="text/javascript" src="${scriptsPath}/mootools/mootools-core.js"></script>
	<script type="text/javascript" src="${scriptsPath}/mootools/noobSlide/noobSlide_packed.js"></script>
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
              <%--<td height="23" valign="bottom" class="textone" align="center"><strong style="font-weight:bold;">修改密码</strong></td>--%>
              <%--<td width="20">&nbsp;</td>--%>
            <%--</tr>--%>
        <%--</table></td>--%>
        <%--<td width="10" height="27" nowrap background="${themesPath}/oldimages/bgyou.gif">&nbsp;</td>--%>
      <%--</tr>--%>
      <%--<tr>--%>
        <%--<td background="${themesPath}/oldimages/bgtua.gif">&nbsp;</td>--%>
        <%--<td valign="top" bgcolor="#eff6fe">--%>
            
        <table width="100%" height="100%" border="0" align="center" cellpadding="0" cellspacing="1">
                <form action="sys-user!savepwd" onsubmit="javascript:return check();">
                <input type ="hidden" name="id" value="${id}"><tr>
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
                            <td width="40%"><div align="right"><span class="textxing">*</span>&nbsp;原密码：</div></td>
                            <td width="60%">&nbsp;<input type="password" class="input_one" name="oldpwd" onblur="checkpwd();">&nbsp;<span id="oldpwdtext"></span></td>
                          </tr>
                          <tr class="textone12">
                            <td><div align="right"><span class="textxing">*</span>&nbsp;新密码：</div></td>
                            <td>&nbsp;<input type="password" class="input_one" name="newpwd" onblur="checknewpwd();">&nbsp;<span id="newpwdtext"></span></td>
                          </tr>
                          <tr class="textone1">
                            <td><div align="right"><span class="textxing">*</span>&nbsp;密码确认：</div></td>
                            <td>&nbsp;<input type="password" class="input_one" name="newpwd2" onblur="checknewpwd2();" >&nbsp;<span id="newpwd2text"></span></td>
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
                    <td width="45%"><input type="button" class="button_cc" name="" value="关 闭" onclick="closewindow('修改密码');"></td>
                  </tr>
                </table></td>
              </tr>
                  </table>
                  </td>
                </tr>
              </form>
        </table>
        <%--</td><td background="${themesPath}/oldimages/bgtub.gif"></td>--%>
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
<script type="text/javascript">
    function check() {
        if(!checkpwd()){
            return false;
        }
        if(!checknewpwd()){
            return false;
        }
        return checknewpwd2();

        
    }

    function checkpwd(){
        document.getElementById("oldpwdtext").innerHTML = "<img src = \"${themesPath}/oldimages/loading.gif\">";
        var oldpwd = document.getElementById("oldpwd").value;
        var ret = getUrlContent("${ctx}/common/util/ajax-util!checkuserpwd?userpwd=" + oldpwd);
        if(ret.indexOf("false") >= 0){
            document.getElementById("oldpwdtext").innerHTML = "<img src  =\"${themesPath}/oldcss/sc.gif\">原密码错误";
            return false;
        }
        else {
            document.getElementById("oldpwdtext").innerHTML = "<img src = \"${themesPath}/oldcss/web_icon_006.gif\">";
            return true;
        }
    }

    function checknewpwd(){
        var newpwd = document.getElementById("newpwd").value;

        if(newpwd.length <= 0){
           document.getElementById("newpwdtext").innerHTML = "<img src  =\"${themesPath}/oldcss/sc.gif\">密码为必填项";
            return false;
        }
        else{
           document.getElementById("newpwdtext").innerHTML = "<img src = \"${themesPath}/oldcss/web_icon_006.gif\">";
            return true;
        }
    }

    function checknewpwd2(){
        var newpwd = document.getElementById("newpwd").value;
        var newpwd2 = document.getElementById("newpwd2").value;

        if(newpwd != newpwd2){
           document.getElementById("newpwd2text").innerHTML = "<img src  =\"${themesPath}/oldcss/sc.gif\">密码确认错误";
            return false;
        }
        else{
           document.getElementById("newpwd2text").innerHTML = "<img src = \"${themesPath}/oldcss/web_icon_006.gif\">";
            return true;
        }
    }
</script>
</html>
