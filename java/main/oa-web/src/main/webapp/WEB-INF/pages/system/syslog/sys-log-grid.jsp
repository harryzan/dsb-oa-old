<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/metaGrid.jsp" %>
    <%@ include file="/common/metaMocha.jsp" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
      <link href="${themesPath}/oldcss/style.css" rel="stylesheet" type="text/css">
    <title>系统日志列表</title>
    <script type="text/javascript">
        //扩展参数,备用
        var pageParam = "";
        //grid 参数配置
        var params = {
            //url:grid 请求数据url,addUrl:添加记录页面url,view:查看记录页面url
            // (修改和删除的url:modify.html,delete.html 放在grid.js中)
            url:"sys-log-grid!griddata",
//            addUrl:"model-property!input",
//            viewUrl:"sys-log",
//            modifyUrl:"sys-log!input",
//            deleteUrl:"sys-log!delete",
            //name:实体类属性名称，header:gird列表的表头，width:列宽
            gridParams:[
                {name:"id",header:"",width:"5%"},
                {name:"sysuser.displayname",header:"用户名",width:"10%"},
                {name:"sysprivilege.name",header:"权限项",width:"15%"},
                {name:"logtime",header:"操作日期",width:"20%"},
                {name:"ipaddress",header:"IP地址",width:"15%"},
                {name:"content",header:"描述",width:"20%"},
                {name:"content",header:"查看",renderer:checkview,width:"10%"}
            ],
            //控制列表中操作按钮,如果注释该行,列表中将不显示操作列
           // buttonParams:[{header:"操作",renderer:"displayButton"}],
            //用户自定义按钮 name：按钮名称；css按钮css样式；event:按钮点击事件，fparam：按钮点击事件的参数 event(fparam)
            //查询条件：["姓名","","String","name"]对应--- 表别名,数据类型,数据字段
            queryCondition:[
                ["用户名","","String","sysuser.loginname"],
                ["IP地址","","String","ipaddress"],
                ["描述","","String","content"]
            ],
            //每页显示的记录条数
            pageSize:20,
//            title:"系统日志列表",
//            frame:false,
            div:"list"
        };
        function checkview(value){

            return "<a style=\"cursor:pointer;\" onclick=\"viewwindow();\">查看</a>";
        }
        function viewwindow(){
            var record = Ext.getCmp("grid").getSelectionModel().getSelected();
            var id = record.data["id"];
            var title = "日志查看-" + record.data["content"] + "("+record.data["sysuser_displayname"] + "/" + record.data["logtime"] +")";
            var url = '${ctx}/s/s06/sys-log?id=' + id;
            enter(title,url,600,250);
//            window.open('sys-log?id=' + id,'','width=800px,height=240px,center=yes,help=no,status=no,scrollbars=yes,toolbar=no,resizable=yes');
        }
    </script>

</head>

<body onload="gridinit(params);">

         <input type="hidden" name="gridParam" id="gridParam" value='${gridParam}'>
           <table width="100%" height="100%" border="0" align="center" cellpadding="0" cellspacing="1">

    <tr>
      <td valign="top"><table width="100%" border="0" cellspacing="0" cellpadding="0">
        <tr>
          <td width="4" height="4" background="${themesPath}/oldimages/bg/shang.gif"><div align="right"><img src="${themesPath}/oldimages/bg/1.gif" width="4" height="4"></div></td>
          <td height="4" background="${themesPath}/oldimages/bg/shang.gif"></td>
          <td width="4" height="4" align="right"><img src="${themesPath}/oldimages/bg/2.gif" width="4" height="4"></td>
        </tr>
        <tr>
          <td width="4" background="${themesPath}/oldimages/bg/zuo.gif"><img src="${themesPath}/oldimages/bg/zuo.gif" width="4" height="4"></td>
          <td width="100%">
            <!-- start insert your custom code -->
         <div id="list" align="left"></div>
            <!-- end insert your custom code -->
        </td>
          <td width="4" background="${themesPath}/oldimages/bg/you.gif"><img src="${themesPath}/oldimages/bg/you.gif" width="4" height="4"></td>
        </tr>
        <tr>
          <td width="4" height="4" background="${themesPath}/oldimages/bg/xia.gif"><div align="right"><img src="${themesPath}/oldimages/bg/3.gif" width="4" height="4"></div></td>
          <td height="4" background="${themesPath}/oldimages/bg/xia.gif"></td>
          <td width="4" align="right"><img src="${themesPath}/oldimages/bg/4.gif" width="4" height="4"></td>
        </tr>
      </table>
      </td>
    </tr>

</table>
</body>
</html>