<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2009-5-19
  Time: 20:04:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/metaGrid.jsp" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>文档目录管理</title>
<link href="${themesPath}/oldcss/style.css" rel="stylesheet" type="text/css">
</head>

<body leftmargin="0" topmargin="0" marginwidth="0" marginheight="0">
<%--<table width="100%"  border="0" cellpadding="0" cellspacing="0">--%>
      <%--<tr>--%>
        <%--<td width="10" height="27" nowrap background="${themesPath}/oldimages/bgzuo.gif">&nbsp;</td>--%>
        <%--<td background="${themesPath}/oldimages/bgz.gif"><table width="100%" height="10"  border="0" align="right" cellpadding="0" cellspacing="2">--%>
            <%--<tr>--%>
              <%--<td width="20" class="textone">&nbsp;</td>--%>
              <%--<td height="23" valign="bottom" class="textone" align="center"><strong style="font-weight:bold;">文档目录管理</strong></td>--%>
              <%--<td width="20">&nbsp;</td>--%>
            <%--</tr>--%>
        <%--</table></td>--%>
        <%--<td width="10" height="27" nowrap background="${themesPath}/oldimages/bgyou.gif">&nbsp;</td>--%>
      <%--</tr>--%>
      <%--<tr>--%>
        <%--<td background="${themesPath}/oldimages/bgtua.gif">&nbsp;</td>--%>
        <%--<td valign="top" bgcolor="#eff6fe">--%>
        <table width="100%" height="100%" border="0" align="center" cellpadding="0" cellspacing="1">
            <form action="doc-category!save" method="post" onsubmit="javascript: return check()">
                <input type ="hidden" name="id" value="${id}">
                <input type="hidden" name="parentid" value="${parentid}${parent.id}" >
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

                          <td width="20%"><div align="right">编号：</div></td>
                          <td width="80%" colspan="3">&nbsp;<input name="code" type="text" class="input_one" value="${code}">&nbsp;<span class="textxing">*</span>

                        </tr>
                        <tr class="textone12">
                          <td><div align="right">名称：</div></td>
                          <td colspan="3">&nbsp;<input name="name" type="text" class="input_one" value="${name}">&nbsp;<span class="textxing">*</span></td>


                        </tr>
                          <tr class="textone1">
                          <td><div align="right">路径：</div></td>
                          <td colspan="3">&nbsp;<input name="path" type="text" class="input_one" value="${path}"></td>
                        </tr>
                          <tr class="textone12">
                          <td><div align="right">系统目录：</div></td>
                          <td colspan="3">&nbsp;<input type="radio" value="true" name="issystem" ${issystem ? "checked" : ""}>&nbsp;是&nbsp;<input type="radio" value="false" name="issystem" ${issystem ? "" : "checked"}> &nbsp;否  </td>
                        </tr>
                        <tr class="textone1">
                          <td rowspan="${viewrow}"><div align="right">查看权限：</div></td>
                          <td width="20%" height="25"><div align="center">单位:&nbsp;<input type ="hidden" name="viewdeptid"><input type ="text" name="viewdeptname" class="input_one2">&nbsp;<img src="${themesPath}/oldimages/jia.gif" width="11" height="11" style="cursor:pointer" onclick="viewdept()">&nbsp;<img src="${themesPath}/oldimages/jian.gif" width="11" height="11" style="cursor:pointer" onclick="dviewdept()"></div></td>
                          <td width="20%"><div align="center">用户:&nbsp;<input type ="hidden" name="viewuserid"><input type ="text" name="viewusername" class="input_one2">&nbsp;<img src="${themesPath}/oldimages/jia.gif" width="11" height="11" style="cursor:pointer" onclick="viewuser()">&nbsp;<img src="${themesPath}/oldimages/jian.gif" width="11" height="11" style="cursor:pointer" onclick="dviewuser()"></div></td>
                          <td width="20%"><div align="center">权限项:&nbsp;<input type ="hidden" name="viewprivilegeid"><input type ="text" name="viewprivilegename" class="input_one2">&nbsp;<img src="${themesPath}/oldimages/jia.gif" width="11" height="11" style="cursor:pointer" onclick="viewprivilege()">&nbsp;<img src="${themesPath}/oldimages/jian.gif" width="11" height="11" style="cursor:pointer" onclick="dviewprivilege()"></div></td>
                        </tr>
                          <s:iterator value="viewunit">
                        <tr class="textone1">
                          <td><div align="center">${sysdept.name}</div></td>
                          <td><div align="center">${sysuser.displayname}</div></td>
                          <td><div align="center">${sysprivilege.name}</div></td>
                        </tr>
                          </s:iterator>
                        <tr class="textone12">
                          <td rowspan="${editrow}"><div align="right">修改权限：</div></td>
                          <td height="25"><div align="center">单位:&nbsp;<input type ="hidden" name="editdeptid"><input type ="text" name="editdeptname" class="input_one2">&nbsp;<img src="${themesPath}/oldimages/jia.gif" width="11" height="11" style="cursor:pointer" onclick="editdept()">&nbsp;<img src="${themesPath}/oldimages/jian.gif" width="11" height="11" style="cursor:pointer" onclick="deditdept()"></div></td>
                          <td><div align="center">用户:&nbsp;<input type ="hidden" name="edituserid"><input type ="text" name="editusername" class="input_one2">&nbsp;<img src="${themesPath}/oldimages/jia.gif" width="11" height="11" style="cursor:pointer" onclick="edituser()">&nbsp;<img src="${themesPath}/oldimages/jian.gif" width="11" height="11" style="cursor:pointer" onclick="dedituser()"></div></td>
                          <td><div align="center">权限项:&nbsp;<input type ="hidden" name="editprivilegeid"><input type ="text" name="editprivilegename" class="input_one2">&nbsp;<img src="${themesPath}/oldimages/jia.gif" width="11" height="11" style="cursor:pointer" onclick="editprivilege()">&nbsp;<img src="${themesPath}/oldimages/jian.gif" width="11" height="11" style="cursor:pointer" onclick="deditprivilege()"></div></td>
                        </tr>
                        <s:iterator value="editunit">
                        <tr class="textone12">
                          <td><div align="center">${sysdept.name}</div></td>
                          <td><div align="center">${sysuser.displayname}</div></td>
                          <td><div align="center">${sysprivilege.name}</div></td>
                        </tr>
                          </s:iterator>

                        <tr class="textone1">
                          <td>&nbsp;</td>
                          <td colspan="3">&nbsp;<input name="Submit2" type="button" class="button_main4" value="清空权限" onclick="clearall()"></td>
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
                      <input type="submit" class="button_bc" name="input5" value="保 存">
                  </div></td>
                  <td width="10%"><div align="center">
                      <input type="reset" class="button_cc" name="Submit52" value="重 写">
                  </div></td>
                  <td width="45%"><input type="button" class="button_cc" name="Submit522" value="返 回" onclick="location='doc-category?id=${id}'" ></td>
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
        var regist = /^[ ]{0,}$/;
        var code = document.getElementById("code").value;
        var name = document.getElementById("name").value;

        if(code.length <= 0 || regist.exec(code)){
            Ext.MessageBox.alert("提示", "编号不能为空！");
            return false;
        }
        if(name.length <= 0 || regist.exec(name)){
            Ext.MessageBox.alert("提示", "名称不能为空！");
            return false;
        }
        return true;
    }

    function viewdept(){
        var returnvalue = window.showModalDialog("${ctx}/common/tree/sys-user-tree", 0);
        if(returnvalue){
            document.getElementById("viewdeptname").value = returnvalue.split(",")[0];
            document.getElementById("viewdeptid").value = returnvalue.split(",")[1];
        }
    }
    function viewuser(){
        var returnvalue = window.showModalDialog("${ctx}/common/tree/sys-user-tree", 1);
        if(returnvalue){
            document.getElementById("viewusername").value = returnvalue.split(",")[0];
            document.getElementById("viewuserid").value = returnvalue.split(",")[1];
        }
    }
    function viewprivilege(){
        var returnvalue = window.showModalDialog("${ctx}/common/tree/sys-privilege-tree");
        if(returnvalue){
            document.getElementById("viewprivilegename").value = returnvalue.split(",")[0];
            document.getElementById("viewprivilegeid").value = returnvalue.split(",")[1];
        }
    }
    function editdept(){
        var returnvalue = window.showModalDialog("${ctx}/common/tree/sys-user-tree", 0);
        if(returnvalue){
            document.getElementById("editdeptname").value = returnvalue.split(",")[0];
            document.getElementById("editdeptid").value = returnvalue.split(",")[1];
        }
    }
    function edituser(){
        var returnvalue = window.showModalDialog("${ctx}/common/tree/sys-user-tree", 1);
        if(returnvalue){
            document.getElementById("editusername").value = returnvalue.split(",")[0];
            document.getElementById("edituserid").value = returnvalue.split(",")[1];
        }
    }
    function editprivilege(){
        var returnvalue = window.showModalDialog("${ctx}/common/tree/sys-privilege-tree");
        if(returnvalue){
            document.getElementById("editprivilegename").value = returnvalue.split(",")[0];
            document.getElementById("editprivilegeid").value = returnvalue.split(",")[1];
        }
    }
    function dviewdept(){
        document.getElementById("viewdeptname").value ="";
        document.getElementById("viewdeptid").value = "";

    }
    function dviewuser(){
        document.getElementById("viewusername").value = "";
        document.getElementById("viewuserid").value = "";
    }
    function dviewprivilege(){
        document.getElementById("viewprivilegename").value = "";
        document.getElementById("viewprivilegeid").value = "";
    }
    function deditdept(){
        document.getElementById("editdeptname").value = "";
        document.getElementById("editdeptid").value = "";
    }
    function dedituser(){
        document.getElementById("editusername").value = "";
        document.getElementById("edituserid").value = "";
    }
    function deditprivilege(){
        document.getElementById("editprivilegename").value = "";
        document.getElementById("editprivilegeid").value = "";
    }

    function clearall(){
        dviewdept();
        dviewuser();
        dviewprivilege();
        deditdept();
        dedituser();
        deditprivilege();
    }
</script>
</body>
</html>
