<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <title>型号信息</title>
    <%@ include file="/common/metaGrid.jsp" %>
    <%@ include file="/common/metaMocha.jsp" %>

      <link href="${themesPath}/oldcss/style.css" rel="stylesheet" type="text/css">
    <script type="text/javascript" src="${scriptsPath}/system/function.js"></script>
    <script type="text/javascript">
        //扩展参数,备用
        var pageParam = "";
//        var privilegecode = "b01_model_D,b01_model_R,b01_model_U,b01_model_C";
//        var result = doPrivilege(privilegecode);
//        var addurl = "car-use!input";
        var modifyurl = "car-check!save";
//        var deleteurl = "car-use!delete";
//        if(result.b01_model_D){
//            deleteurl = false;
//        }
//        if(result.b01_model_U){
//            modifyurl = false;
//        }
//        if(result.b01_model_C){
//            addurl = false;
//        }
        //grid 参数配置
        var params = {
            //url:grid 请求数据url,addUrl:添加记录页面url,view:查看记录页面url
            // (修改和删除的url:modify.html,delete.html 放在grid.js中)
            url:"car-use-grid!griddata",
//            addUrl:addurl,
            modifyUrl:modifyurl,
//            deleteUrl:deleteurl,
            //name:实体类属性名称，header:gird列表的表头，width:列宽
            gridParams:[
                {name:"id",header:"",width:"10%"},
                {name:"car.carmodel",header:"车辆型号",width:"15%"},
                {name:"car.carlicense",header:"车辆牌照",width:"15%"},
                {name:"user.displayname",header:"申请人",width:"15%"},
                {name:"usedate",header:"使用时间",width:"20%"},
                {name:"submitdate",header:"提交时间",width:"20%"},
                {name:"desc",header:"备注",width:"30%"}
            ],
            //控制列表中操作按钮,如果注释该行,列表中将不显示操作列
            buttonParams:[{header:"操作",renderer:"displayButton"}],
            //用户自定义按钮 name：按钮名称；css按钮css样式；event:按钮点击事件，fparam：按钮点击事件的参数 event(fparam)
            //查询条件：["姓名","","String","name"]对应--- 表别名,数据类型,数据字段
            queryCondition:[
//                ["编码","","String","code"],
//                ["描述","","String","description"],
//                ["生产厂商","","String","manufacturer"]
            ],
            //每页显示的记录条数
            pageSize:30,
         //title:"设备型号",
           //frame:true,
            div:"list"
        };

        function checkview(value){
//            if(result.b01_model_R){
//                return value;
//            }
            return "<a style=\"cursor:pointer;\" onclick=\"viewwindow();\">"+value+"</a>";
        }
        function viewwindow(){
            var record = Ext.getCmp("grid").getSelectionModel().getSelected();
            var id = record.data["id"];
            var title = record.data["description"] + "(" + record.data["code"] + ")";
            var url = '${ctx}/b/b01/model-property?id=' + id;
//            window.open('model-property?id=' + id,'','width=800px,height=500px,center=yes,help=no,status=no,scrollbars=yes,toolbar=no,resizable=yes');
            enter(title,url,600,400);
        }

    </script>

</head>

<body onLoad="gridinit(params);">
<input type="hidden" name="gridParam" id="gridParam" value='${gridParam}'>
<table width="100%" height="100%" border="0" align="center" cellpadding="0" cellspacing="1">

<tr>
      <td valign="top"><table width="100%" border="0" cellspacing="0" cellpadding="0">
        <tr>
          <td width="4" height="4" background="${themesPath}/images/bg/shang.gif"><div align="right"><img src="${themesPath}/images/bg/1.gif" width="4" height="4"></div></td>
          <td height="4" background="${themesPath}/images/bg/shang.gif"></td>
          <td width="4" height="4" align="right"><img src="${themesPath}/images/bg/2.gif" width="4" height="4"></td>
        </tr>
        <tr>
          <td width="4" background="${themesPath}/images/bg/zuo.gif"><img src="${themesPath}/images/bg/zuo.gif" width="4" height="4"></td>
          <td width="100%">
            <!-- start insert your custom code -->
         <div id="list" align="left"></div>
            <!-- end insert your custom code -->
        </td>
          <td width="4" background="${themesPath}/images/bg/you.gif"><img src="${themesPath}/images/bg/you.gif" width="4" height="4"></td>
        </tr>
        <tr>
          <td width="4" height="4" background="${themesPath}/images/bg/xia.gif"><div align="right"><img src="${themesPath}/images/bg/3.gif" width="4" height="4"></div></td>
          <td height="4" background="${themesPath}/images/bg/xia.gif"></td>
          <td width="4" align="right"><img src="${themesPath}/images/bg/4.gif" width="4" height="4"></td>
        </tr>
      </table>
    </td>
  </tr>

</table>

</body>
</html>