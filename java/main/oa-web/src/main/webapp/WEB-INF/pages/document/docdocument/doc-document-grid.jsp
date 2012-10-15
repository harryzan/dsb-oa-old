<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/metaGrid.jsp" %>
    <%@ include file="/common/metaMocha.jsp" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
      <link href="${themesPath}/oldcss/style.css" rel="stylesheet" type="text/css">
    <title>文档列表</title>
    <SCRIPT src='${scriptsPath}/system/function.js'></SCRIPT>
    <SCRIPT src='${scriptsPath}/system/calendar_ext.js'></SCRIPT>
    <script type="text/javascript">
        //扩展参数,备用
        var pageParam = "";
//        var privilegecode = "d02_document_D,d02_document_R,d02_document_U,d02_document_C";
//        var result = doPrivilege(privilegecode);
        var addurl = "doc-document!input?doccategoryid=${doccategoryid}";
        var modifyurl = "doc-document!input";
        var deleteurl = "doc-document!delete";
//        if(result.d02_document_C){
//            addurl = false;
//        }
//        if(result.d02_document_U){
//            modifyurl = false;
//        }
//        if(result.d02_document_D){
//            deleteurl = false;
//        }
        //grid 参数配置
        var params = {
            //url:grid 请求数据url,addUrl:添加记录页面url,view:查看记录页面url
            // (修改和删除的url:modify.html,delete.html 放在grid.js中)
            url:"doc-document-grid!griddata?doccategoryid=${doccategoryid}",
            addUrl:addurl,
            //viewUrl:"doc-document",
            modifyUrl:modifyurl,
            deleteUrl:deleteurl,
            //name:实体类属性名称，header:gird列表的表头，width:列宽
            gridParams:[
                {name:"id",header:"",width:"10%"},
//                {name:"origincode",header:"原始编号",width:"15%"},
                {name:"name",renderer:checkview,header:"标题",width:"20%"} ,
                {name:"createtime",renderer:checktime,header:"创建时间",width:"20%"} ,
//                {name:"sysdept.name",header:"单位",width:"20%"},
                {name:"abstractcontent",header:"摘要",width:"20%"},
                {name:"description",renderer:getattachs,header:"&nbsp;&nbsp;&nbsp;&nbsp;附件列表",width:"15%"}
//                {name:"docdocumentattaches[0].filename",renderer:checktime,header:"附件",width:"20%"} ,
            ],
            //控制列表中操作按钮,如果注释该行,列表中将不显示操作列
            buttonParams:[{header:"操作",renderer:"displayButton"}],
            //用户自定义按钮 name：按钮名称；css按钮css样式；event:按钮点击事件，fparam：按钮点击事件的参数 event(fparam)
            //查询条件：["姓名","","String","name"]对应--- 表别名,数据类型,数据字段
            queryCondition:[
//               ["原始编号","","String","origincode"],
               ["标题","","String","name"],
//               ["单位","","String","sysdept.name"],
               ["创建时间", "", "Datetime", "createtime"]
            ],
            //每页显示的记录条数
            pageSize:10,
//            title:"人工检测报告列表",
//            frame:true,
            div:"list"
        };
        function check(value){
            if(value > 90){
                return "好("+value+")";
            }
            else{
                return "差("+value+")";
            }
        }

        function checktime(value){
            return value.split(".")[0];
        }

        function getattachs(value){

            var attachs = new String(value);
            var list;
            if(attachs.indexOf("?") > -1){
                list = attachs.substring(attachs.indexOf("?") + 1).split(",");
            }
            else{
                list = "";
            }

            var i = 0;
            attachs = "";
            for (i = 0; i < list.length; i++) {

                var s = new String(list[i]);

                attachs += "<a href='${ctx}/common/document/doc-attach!download?id=" +
                           s.substring(0, s.indexOf(":")) + "' target='_blank'>" +
                           "<img src='${themesPath}/oldimages/icons/doc.gif' border='0' style='cursor:hand' alt='" +
                           s.substring(s.indexOf(":") + 1) + "'></a>&nbsp;&nbsp;";

            }
            return attachs;
        }

        function checkview(value){
//            if(result.d02_document_R) {
//                return value;
//            }
            return "<a style=\"cursor:pointer;\" onclick=\"viewwindow();\">"+value+"</a>";
        }
        function viewwindow(){
            var record = Ext.getCmp("grid").getSelectionModel().getSelected();
            var id = record.data["id"];
            var title = record.data["name"];
            var url = '${ctx}/d/d02/doc-document?id=' + id;
            enter(title,url,600,400);
//            window.open('doc-document?id=' + id,'','width=800px,height=420px,center=yes,help=no,status=no,scrollbars=yes,toolbar=no,resizable=yes');
        }

        function resolveCondition(conditions){
            var url = "doc-document-grid!resolveCondition?doccategoryid=${doccategoryid}&conditions=" + conditions;
            url = encodeURI(url);
            //alert(conditions);
            doRequest(url, "POST", function(ret){
                document.getElementById("queryCondition").innerHTML = "符合&nbsp;<font color='red'>" + ret + "</font> 的文档列表";
            });

        }

    </script>

</head>

<body onLoad="gridinit(params);resolveCondition('');">
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
              <td width="4" background="${themesPath}/oldimages/bg/zuo.gif"><img src="${themesPath}/oldimages/bg/zuo.gif"
                                                                              width="4" height="4"></td>
              <td width="100%" height="20px">
                   <div  align="center" style="font-size:12px"><label id="queryCondition"></label></div>
              </td>
              <td width="4" background="${themesPath}/oldimages/bg/you.gif"><img src="${themesPath}/oldimages/bg/you.gif"
                                                                              width="4" height="4"></td>
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