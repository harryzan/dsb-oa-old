<%--
  Created by IntelliJ IDEA.
  User: cxs
  Date: 2010-4-1
  Time: 14:19:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <title>公告信息</title>
    <%@ include file="/common/metaGrid.jsp" %>
    <%@ include file="/common/metaMocha.jsp" %>

    <link href="${themesPath}/oldcss/style.css" rel="stylesheet" type="text/css">
    <script type="text/javascript" src="${scriptsPath}/system/function.js"></script>
    <script type="text/javascript">
        //扩展参数,备用
        var pageParam = "";
//        var privilegecode = "s07_bulletin_D,s07_bulletin_R,s07_bulletin_U,s07_bulletin_C";
//        var result = doPrivilege(privilegecode);
        var addurl = "bulletin!input?bulletinstatus=${bulletinstatus}";
        var modifyurl = "bulletin!input?bulletinstatus=${bulletinstatus}";
        var deleteurl = "bulletin!delete";
//        addurl = false; // 目前的系统公告功能仅供报警推送之用
//        deleteurl = false;
//        modifyurl = false;
//        if(result.s07_bulletin_C){
//            addurl = false;
//        }
//        if(result.s07_bulletin_D){
//            deleteurl = false;
//        }
//        if(result.s07_bulletin_U){
//            modifyurl = false;
//        }
        //grid 参数配置
        var params = {
            //url:grid 请求数据url,addUrl:添加记录页面url,view:查看记录页面url
            // (修改和删除的url:modify.html,delete.html 放在grid.js中)
            url:"bulletin-grid!griddata?bulletinstatus=${bulletinstatus}",
            addUrl:addurl,
            modifyUrl:modifyurl,
            deleteUrl:deleteurl,

            //name:实体类属性名称，header:gird列表的表头，width:列宽
            gridParams:[
                {name:"id",header:"",width:"10%"},
                {name:"name",renderer:checkview,header:"标题",width:"20%"},
                {name:"starttime",header:"发布时间",width:"20%"},
                {name:"endtime",header:"结束时间",width:"10%"},
                {name:"adduser.displayname",header:"发布人",width:"20%"}
//                {name:"status",renderer:check,header:"状态",width:"10%"}
            ],
            //控制列表中操作按钮,如果注释该行,列表中将不显示操作列
            buttonParams:[{header:"操作",renderer:"displayButton"}],
            customButtons:[{name:"",value:"", css:"button_bssdetail", event:"viewwindow", title:"查看"}],
            //用户自定义按钮 name：按钮名称；css按钮css样式；event:按钮点击事件，fparam：按钮点击事件的参数 event(fparam)
            //查询条件：["姓名","","String","name"]对应--- 表别名,数据类型,数据字段
            queryCondition:[
                ["标题","","String","code"]
            ],
            //每页显示的记录条数
            pageSize:15,
         //title:"设备型号",
           //frame:true,
            div:"list"
        };

        function goResult(id, name){
            <%--enter(name, '${ctx}/f/f08/alarm-push-result?id=' + id,700,400,null);--%>
            var record = Ext.getCmp("grid").getSelectionModel().getSelected();
            var bulletinId = record.data["id"];
            doRequest("${ctx}/s/s07/bulletin!changeStatus?id=" + bulletinId, "POST", null);
        }

        function check(value){
            if(value && value.indexOf("true") >= 0){
                return "已确认";
            }
            else {
                return "未确认";
            }
        }

        function checkview(value){
//            if(result.s07_bulletin_R){
                return value;
//            }

//            var temp = value.split("|");
//            if(temp.length == 3){
//                value = "[<a style='cursor:pointer;'onclick='goResult("+ temp[0] +", \""+temp[1]+"\")'>推送结果</a>]" + temp[2];
//            }
//            return value;
        }
        function viewwindow(){
            var record = Ext.getCmp("grid").getSelectionModel().getSelected();
            var id = record.data["id"];
            var title = record.data["name"];
            var temp = title.split("|");
            if(temp.length == 3){
                title = temp[2];
            }
            var url = '${ctx}/message/bulletin/bulletin!view?id=' + id;
            enter(title,url,500,300);
        }

    </script>

</head>

<body onLoad="gridinit(params);">
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