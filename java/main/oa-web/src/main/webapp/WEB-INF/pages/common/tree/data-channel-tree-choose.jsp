<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<html>
<head>
    <title>设备信息</title>
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
                eval("document.all.type").value = obj.value;
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
<input type="hidden" id="type" >
  <table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0">
      <tr>
          <td width="100%" height="30" valign="top" id="tabTd">
              <input type="button" name="tab1" value="按类别" class="button_one4"
                     onClick="tabClickFunc(this,2,jumpTab('device-tree?treetype=type'));">
              <input type="button" name="tab2" value="按位置" class="button_one3"
                     onClick="tabClickFunc(this,2,jumpTab('device-tree?treetype=structure'));">
          </td>
      </tr>
      
      <tr>
          <td class="button_bar_bg"><img src="${themesPath}/system/icons/tm.gif" width="1" height="1"></td>
      </tr>
      <tr valign="top">
          <td width="100%" height="100%" valign="top">
              <iframe name="childFrame" scrolling="auto" src="device-tree?treetype=type" frameborder="0" style="vertical-align:top"
                      width="100%"
                      height="100%"></iframe>
          </td>
      </tr>
  </table>
</body>

</html>