<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<html>
<head>
    <title>文档权限配置切换</title>
    <%--<%@ include file="/common/meta.jsp" %>--%>
    <link href="${themesPath}/oldcss/style.css" rel="stylesheet" type="text/css">
    <script type="text/javascript">
        /**
         * TAB按钮点击的公用方法
         * @param obj:当前点击元素
         * @param count:TAB总数量
         * @param eventFunc:点击触发JS函数名，该函数请自行实现
         * 注意：对于eventFunc可以写成jumpPage(url)的形式
         */
        function tabClickFunc(obj, count, eventFunc)
        {
            if (null == count || "" == count) return;
            for (var i = 1; i <= count; i++)
            {
                var tabObj = eval("document.all.tab" + i);
                if (tabObj)
                    tabObj.className = "button_one3";
            }
            if (obj)
            {
                obj.className = "button_one4";
            }
            if (null != eventFunc)
                eval(eventFunc);
        }

        function jumpTab(url) {
            if(url){
                document.all.childFrame.src = url;
            }
        }

    </script>

</head>
<body class="tab_body" scroll=no topmargin="0" leftmargin="0">
<%--<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">--%>
  <%--<tr>--%>
    <%--<td width="12" height="12" valign="top" background="${themesPath}/oldimages/azuo.gif"><img src="${themesPath}/oldimages/a1.gif" width="13" height="13"></td>--%>
    <%--<td background="${themesPath}/oldimages/ashang.gif"></td>--%>
    <%--<td width="12" height="12" valign="top" nowrap background="${themesPath}/oldimages/ayou.gif"><img src="${themesPath}/oldimages/a2.gif" width="13" height="13"></td>--%>
  <%--</tr>--%>
  <%--<tr>--%>
    <%--<td background="${themesPath}/oldimages/azuo.gif"></td>--%>
    <%--<td width="100%" valign="top" bgcolor="#fbfbfb" align="center">--%>

    <%--<table width="100%"  border="0" cellpadding="0" cellspacing="0">--%>
      <%--<tr>--%>
        <%--<td width="10" height="27" nowrap background="${themesPath}/oldimages/bgzuo.gif"></td>--%>
        <%--<td background="${themesPath}/oldimages/bgz.gif"><table width="100%" height="10"  border="0" align="right" cellpadding="0" cellspacing="2">--%>
            <%--<tr>--%>
              <%--<td width="20" class="textone">&nbsp;</td>--%>
              <%--<td height="23" valign="bottom" class="textone"><div align="center"><strong>文档权限</strong></div></td>--%>
              <%--<td width="20">&nbsp;</td>--%>
            <%--</tr>--%>
        <%--</table></td>--%>
        <%--<td width="10" height="27" nowrap background="${themesPath}/oldimages/bgyou.gif"></td>--%>
      <%--</tr>--%>
      <%--<tr>--%>
        <%--<td height="480" background="${themesPath}/oldimages/bgtua.gif"></td>--%>
          <%--<td valign="top" bgcolor="#eff6fe">--%>
              <!-- start insert your custom code -->
              <table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0">
                  <tr>
                      <td width="100%" height="30" valign="top" id="tabTd">
                          <input type="button" name="tab1" value="单位/用户" class="button_one4"
                                 onClick="tabClickFunc(this,2,jumpTab('dept-config-main'));">
                          <input type="button" name="tab2" value="权限项" class="button_one3"
                                 onClick="tabClickFunc(this,2,jumpTab('privilege-config-main'));">
                      </td>
                  </tr>
                  <tr>
                      <td class="button_bar_bg"><img src="${themesPath}/system/icons/tm.gif" width="1" height="1"></td>
                  </tr>
                  <tr valign="top">
                      <td width="100%" height="100%" valign="top">
                          <iframe name="childFrame" scrolling="auto" src="dept-config-main" frameborder="0" style="vertical-align:top"
                                  width="100%"
                                  height="100%"></iframe>
                      </td>
                  </tr>
              </table>
              <!-- end insert your custom code -->
          <%--</td>--%>
        <%--<td background="${themesPath}/oldimages/bgtub.gif"></td>--%>
      <%--</tr>--%>
      <%--<tr>--%>
        <%--<td valign="top"><img src="${themesPath}/oldimages/bgtuc.gif" width="10" height="11"></td>--%>
        <%--<td height="11" background="${themesPath}/oldimages/bgxia.gif"></td>--%>
        <%--<td valign="top"><img src="${themesPath}/oldimages/bgtud.gif" width="10" height="11"></td>--%>
      <%--</tr>--%>
    <%--</table>--%>

    <%--</td>--%>
    <%--<td background="${themesPath}/oldimages/ayou.gif"></td>--%>
  <%--</tr>--%>
  <%--<tr>--%>
    <%--<td width="12" height="12" background="${themesPath}/oldimages/a3.gif"></td>--%>
    <%--<td background="${themesPath}/oldimages/axia.gif"></td>--%>
    <%--<td width="12" height="12" valign="top" background="${themesPath}/oldimages/a4.gif"></td>--%>
  <%--</tr>--%>
<%--</table>--%>
</body>
</html>