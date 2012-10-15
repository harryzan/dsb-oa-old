<%--
  Created by IntelliJ IDEA.
  User: cxs
  Date: 2010-4-1
  Time: 14:19:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/metaGrid.jsp" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>工作日志维护</title>

<link href="${themesPath}/oldcss/style.css" rel="stylesheet" type="text/css">

    <script type="text/javascript" src="${scriptsPath}/system/calendar.js"></script>
    <script type="text/javascript" src="${scriptsPath}/system/function.js"></script>

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
              <%--<td height="23" valign="bottom" class="textone" align="center"><strong style="font-weight:bold;">维护型号信息</strong></td>--%>
              <%--<td width="20">&nbsp;</td>--%>
            <%--</tr>--%>
        <%--</table></td>--%>
        <%--<td width="10" height="27" nowrap background="${themesPath}/oldimages/bgyou.gif">&nbsp;</td>--%>
      <%--</tr>--%>
      <%--<tr>--%>
        <%--<td background="${themesPath}/oldimages/bgtua.gif">&nbsp;</td>--%>
        <%--<td valign="top" bgcolor="#eff6fe">--%>
        <table width="100%" height="100%" border="0" align="center" cellpadding="0" cellspacing="1">
            <form action="worklog!save?id=${id}" method="post" onsubmit="javascript:return check_form(this)">
                <input type ="hidden" name="gridParam" value='${gridParam}'>
                <input type ="hidden" name="bulletinstatus" value="${bulletinstatus}"> 
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
                              <td width="30%"><div align="right">工作日期：</div></td>
                              <td width="70%">&nbsp;
                                  <%--<s:date name="buydate" format="yyyy-MM-dd"/>--%>
                                  <input id="workdate" name="workdate" type="text" class="input_one" value="${workdate}"><img src="${themesPath}/oldimages/calendar.gif"  width="13" height="12" style="cursor:pointer;" onclick="calendar(workdate, 'date');" /></td>
                          </tr>
                          <tr class="textone12">
                              <td width="30%"><div align="right">开始时间：</div></td>
                              <td width="70%">&nbsp;
                                  <%--<s:date name="buydate" format="yyyy-MM-dd"/>--%>
                                  <input id="starttime" name="starttime" type="text" class="input_one" value="${starttime}"><img src="${themesPath}/oldimages/calendar.gif"  width="13" height="12" style="cursor:pointer;" onclick="calendar(starttime, 'datetime');" /></td>
                          </tr>
                          <tr class="textone1">
                              <td width="30%"><div align="right">结束时间：</div></td>
                              <td width="70%">&nbsp;
                                  <%--<s:date name="buydate" format="yyyy-MM-dd"/>--%>
                                  <input id="endtime" name="endtime" type="text" class="input_one" value="${endtime}"><img src="${themesPath}/oldimages/calendar.gif"  width="13" height="12" style="cursor:pointer;" onclick="calendar(endtime, 'datetime');" /></td>
                          </tr>
                          <tr class="textone12">
                            <td><div align="right">内容：</div></td>
                            <td height="60" colspan="2">&nbsp;
                                <textarea name="content" id="content" class="input_three" >${content}</textarea></td>
                          </tr>
                      </table></td>
                      <td width="4" background="${themesPath}/oldimages/bg/you.gif"><img src="${themesPath}/oldimages/bg/you.gif" width="4" height="4"></td>
                    </tr>
                    <tr>
                      <td width="4" height="4" background="${themesPath}/oldimages/bg/xia.gif"><div align="right"><img src="${themesPath}/oldimages/bg/3.gif" width="4" height="4"></div></td>
                      <td height="4" background="${themesPath}/oldimages/bg/xia.gif"></td>
                      <td width="4" align="right"><img src="${themesPath}/oldimages/bg/4.gif" width="4" height="4"></td>
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
<script type="text/javascript">
    function check_form(afrom){
        if(afrom.name.value == ""){
            Ext.MessageBox.alert("提示", "标题不能为空！");
            return false;
        }
        if(afrom.description.value == ""){
            Ext.MessageBox.alert("提示", "内容不能为空！");
            return false;
        }
        return true;
    }

    function jia(){
        var viewuserids = eval("document.all.viewuserids").value;
        var viewusernames = eval("document.all.viewusernames").value;
        viewusernames = encodeURI(viewusernames);
        if(viewuserids != null && "" != viewuserids){
            returnvalue = window.showModalDialog("${ctx}/common/tree/multi-sys-user-tree?viewuserids="+viewuserids+"&viewusernames="+viewusernames);
        }else{
            returnvalue = window.showModalDialog("${ctx}/common/tree/multi-sys-user-tree");
        }
        if(returnvalue != undefined){

            var id = returnvalue.substring(returnvalue.indexOf("<id>")+4,returnvalue.indexOf("</id>"));
            var name = returnvalue.substring(returnvalue.indexOf("<name>")+6,returnvalue.indexOf("</name>"));
            eval("document.all.viewusernames").value = name;
            eval("document.all.viewuserids").value = id;
        }
    }

    function jian(){
        eval("document.all.viewusernames").value = "";
        eval("document.all.viewuserids").value = "";
    }

</script>
</body>
</html>