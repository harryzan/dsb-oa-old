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
<title>文档挂接</title>
<link href="${themesPath}/css/style.css" rel="stylesheet" type="text/css">
</head>

<body leftmargin="0" topmargin="0" marginwidth="0" marginheight="0">
<%--<table width="100%"  border="0" cellpadding="0" cellspacing="0">--%>
      <%--<tr>--%>
        <%--<td width="10" height="27" nowrap background="${themesPath}/images/bgzuo.gif">&nbsp;</td>--%>
        <%--<td background="${themesPath}/images/bgz.gif"><table width="100%" height="10"  border="0" align="right" cellpadding="0" cellspacing="2">--%>
            <%--<tr>--%>
              <%--<td width="20" class="textone">&nbsp;</td>--%>
              <%--<td height="23" valign="bottom" class="textone" align="center"><strong style="font-weight:bold;">文档挂接</strong></td>--%>
              <%--<td width="20">&nbsp;</td>--%>
            <%--</tr>--%>
        <%--</table></td>--%>
        <%--<td width="10" height="27" nowrap background="${themesPath}/images/bgyou.gif">&nbsp;</td>--%>
      <%--</tr>--%>
      <%--<tr>--%>
        <%--<td background="${themesPath}/images/bgtua.gif">&nbsp;</td>--%>
        <%--<td valign="top" bgcolor="#eff6fe">--%>
        <table width="100%" height="100%" border="0" align="center" cellpadding="0" cellspacing="1">
            <form action="doc-document!save" method="post" enctype="multipart/form-data" onsubmit="javascript: return check()">
                <input type ="hidden" name="id" value="${id}">
                <input type ="hidden" name="doccategoryid" value="${doccategoryid}${doccategory.id}">
                <input type ="hidden" name="modelname" value="${modelname}"> 
                <tr>
                  <td valign="top"><table width="100%" border="0" cellspacing="0" cellpadding="0">
                    <tr>
                      <td width="4" height="4" background="${themesPath}/images/bg/shang.gif"><div align="right"><img src="${themesPath}/images/bg/1.gif" width="4" height="4"></div></td>
                      <td height="4" background="${themesPath}/images/bg/shang.gif"></td>
                      <td width="4" height="4" align="right"><img src="${themesPath}/images/bg/2.gif" width="4" height="4"></td>
                    </tr>
                    <tr>
                      <td width="4" background="${themesPath}/images/bg/zuo.gif"><img src="${themesPath}/images/bg/zuo.gif" width="4" height="4"></td>
                      <td valign="top" bgcolor="#FFFFFF"><table width="100%" border="0" cellspacing="1" cellpadding="0">
                        <tr class="textone1">
                          <td width="20%"><div align="right">原始编号：</div></td>
                          <td width="20%">&nbsp;<input name="origincode" type="text" class="input_xiao" value="${origincode}">&nbsp;<span class="textxing">*</span></td>
                          <td width="15%"><div align="right">编号：</div></td>
                          <td width="30%">&nbsp;<input name="code" type="text" class="input_xiao" value="${code}" >&nbsp;<span class="textxing">*</span></td>
                          </tr>
                        <tr class="textone12">
                          <td><div align="right">标题：</div></td>
                          <td colspan="3">&nbsp;<input name="name" type="text" class="input_chang" value="${name}" >&nbsp;<span class="textxing">*</span></td>
                          </tr>
                        <tr class="textone1">
                          <td><div align="right">作者：</div></td>
                          <td>&nbsp;<input name="author" type="text" class="input_one" value="${author}"></td>
                          <td><div align="right">文档性质：
                            </div></td>
                          <td>&nbsp;<select name="propertyid" class="input_one">
                              <s:iterator value="properties">
                                  <option value="${id}" ${property.id == id ?"selected=\"selected\"":""}>${listname}</option>
                              </s:iterator>
                          </select></td>
                          </tr>
                        <tr class="textone12">
                          <td><div align="right">关键字：</div></td>
                          <td>&nbsp;<input name="keywords" type="text" class="input_one" value="${keywords}" ></td>
                          <td><div align="right">文档格式：</div></td>
                          <td>&nbsp;<select name="formatid" class="input_one">
                              <s:iterator value="formats">
                                  <option value="${id}" ${format.id==id?"selected=\"selected\"":""}>${listname}</option>
                              </s:iterator>
                            </select></td>
                          </tr>
                        <tr class="textone1">
                          <td><div align="right">单位：</div></td>
                          <td nowrap colspan="3" >&nbsp;<input name="deptname" type="text" class="input_one" value="${sysdept.name}" onclick="dept()" ><input type ="hidden" name="deptid" value="${sysdept.id}">
                            <img src="${themesPath}/css/cl.gif" width="16" height="16" style="cursor:pointer" onclick="dept()" ></td>
                          </tr>
                        <tr class="textone12">
                          <td><div align="right">摘要：</div></td>
                          <td height="60" colspan="3">&nbsp;<textarea name="abstractcontent" class="input_four">${abstractcontent}</textarea></td>
                          </tr>
                        <tr class="textone1">
                          <td><div align="right">描述：</div></td>
                          <td height="60" colspan="3">&nbsp;<textarea name="description" class="input_four">${description}</textarea></td>
                          </tr>
                      </table>
                        <table class="textoneA" width="100%" border="0" id="attachtable" cellspacing="0" cellpadding="0" >
                          <tr>
                            <td height="25" colspan="4"><div align="center" class="text"><strong>附件列表</strong></div></td>
                            </tr>
                          <tr>
                            <td height="25" width="2%"  onclick="addattachrow();" background="${themesPath}/css/diandi.gif" style="cursor:pointer;" title="添加附件"><img src="${themesPath}/images/jia.gif"></td>
                            <td width="48%" background="${themesPath}/css/diandi.gif"><div align="center">文件名称(<font style="color:red">文件大小100M以内</font>)</div></td>
                            <td width="25%" background="${themesPath}/css/diandi.gif"><div align="center">说明</div></td>
                            <td width="25%" background="${themesPath}/css/diandi.gif"><div align="center">备注</div></td>
                            </tr>
                            <s:iterator value ="attachs">
                               <tr>
                            <td height="25" onclick="deleteattachrow();" background="${themesPath}/images/jian.gif" style="background-repeat:no-repeat;background-position:left;cursor:pointer;" title="删除附件">&nbsp;</td>
                            <td><div align="center">${filename}<input type="hidden" name ="attachids" value="${id}"> </div></td>
                            <td><div align="center"><input type ="text" name="existattachcontents" value="${content}"> </div></td>
                            <td><div align="center"><input type ="text" name="existattachdescriptions" value="${description}"></div></td>
                            </tr>
                            </s:iterator>
                          <tr>
                            <td onclick="deleteattachrow();" background="${themesPath}/images/jian.gif" style="background-repeat:no-repeat;background-position:left;cursor:pointer;" title="删除附件">&nbsp;</td>
                            <td><div align="center">
                                附件：<input type ="file" name="upload">
                            </div></td>
                            <td><div align="center"><input type ="text" name="uploadattachcontents" ></div></td>
                            <td><div align="center"><input type ="text" name="uploadattachdescriptions" ></div></td>
                            </tr>
                        </table></td>
                      <td width="4" background="${themesPath}/images/bg/you.gif"><img src="${themesPath}/images/bg/you.gif" width="4" height="4"></td>
                    </tr>
                    <tr>
                      <td width="4" height="4" background="${themesPath}/images/bg/xia.gif"><div align="right"><img src="${themesPath}/images/bg/3.gif" width="4" height="4"></div></td>
                      <td height="4" background="${themesPath}/images/bg/xia.gif"></td>
                      <td width="4" align="right"><img src="${themesPath}/images/bg/4.gif" width="4" height="4"></td>
                    </tr><tr>
                <td colspan="3"><table width="100%" border="0" cellspacing="1" cellpadding="0">
                <tr>
                  <td width="45%"><div align="right">
                      <input type="submit" class="button_bc" name="input5" value="保 存">
                  </div></td>
                  <td width="10%"><div align="center">
                      <input type="reset" class="button_cc" name="Submit52" value="重 写">
                  </div></td>
                  <td width="45%"><input type="button" class="button_cc" name="Submit522" value="关 闭" onClick="below();"></td>
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
    <%--</table>--%>
<script type="text/javascript">
    function deleteattachrow() {
        event.cancelBubble = true;
        with (event.srcElement.parentElement) {
            if (tagName.toLowerCase() == "tr") {
                Ext.MessageBox.confirm("提示", "确定删除该附件！", function(btn){
                    if(btn == "yes"){
                        attachtable.deleteRow(rowIndex);
                    }
                });
            }
        }
    }

    function addattachrow(){
        var newTr = attachtable.insertRow();

        var newTd1 = newTr.insertCell();
        newTd1.onclick = deleteattachrow;
        newTd1.background = "${themesPath}/images/jian.gif";
        newTd1.style.cursor = "pointer";
        newTd1.style.backgroundRepeat = "no-repeat";
        newTd1.style.backgroundPosition = "left";
        newTd1.title = "删除附件";
        newTd1.height = "25";

        var newTd2 = newTr.insertCell();
        newTd2.innerHTML = "<div align=\"center\"> 附件：<input type =\"file\" name=\"upload\"></div>";
        var newTd3 = newTr.insertCell();
        newTd3.innerHTML = "<div align=\"center\"><input type =\"text\" name=\"uploadattachcontents\"></div>";
        var newTd4 = newTr.insertCell();
        newTd4.innerHTML = "<div align=\"center\"><input type =\"text\" name=\"uploadattachdescriptions\"></div>";
    }

    function check(){
        var regist = /^[ ]{0,}$/;
        var origincode = document.getElementById("origincode").value;
        var code = document.getElementById("code").value;
        var name = document.getElementById("name").value;


        if (origincode.length <= 0 || regist.exec(origincode)) {
            Ext.MessageBox.alert("提示", "原始编号不能为空!");
            return false;
        }
        if (code.length <= 0 || regist.exec(code)) {
            Ext.MessageBox.alert("提示", "编号不能为空!");
            return false;
        }
        if (name.length <= 0 || regist.exec(name)) {
            Ext.MessageBox.alert("提示", "名称不能为空!");
            return false;
        }

        return true;
    }

    function dept(){
        var returnvalue = window.showModalDialog("${ctx}/common/tree/sys-user-tree", 0);
        if(returnvalue){
            document.getElementById("deptname").value = returnvalue.split(",")[0];
            document.getElementById("deptid").value = returnvalue.split(",")[1];
        }
    }

    function below(){
        try{
            window.parent.OK();
        }catch(E){

        }
    }

</script>
</body>
</html>
