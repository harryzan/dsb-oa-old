<%--
  Created by IntelliJ IDEA.
  User: cxs
  Date: 2009-8-24
  Time: 17:27:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <title>多选树</title>
    <%@ include file="/common/metaTree.jsp" %>
    <script type="text/javascript">
//        window.returnValue = "cancel";
        <%--var isDirectInput = ${isPermitInput};--%>
        var choose = "${choose}";
        function initTree() {
            //树的图标
            var imageUrl = treeImage(iconPath, [
                "main_material.gif",       //0
                "swimming.gif",       //1
                "template_pic.gif",       //2
                "working_drawings.gif"
            ]);

            //定义树的根节点
            var root = new Ext.tree.AsyncTreeNode({id:'root',text:'逻辑组信息',icon:iconPath + 'root.gif'});

            //构造树面板 ，url="treedata.jsp"定义了异步数据源 .
            var treePanel = new TreePanel(root, null, "multi-logic-channel-tree!treedata?structureid=${structureid}&&check=${check}", imageUrl, " ");

            //加载第一层节点
            root.expand(1);

//            添加点击节点触发的函数onClick

            treePanel.getTree().on("checkChange", function(node) {
                onCheckChange(node);
            });

            var choose = "${choose}";
            var name = "${name}";
            if(choose != "" && name != ""){
                var ids = choose.split(",");
                var names = name.split(",");
                var divObj = document.getElementById("selectedDiv");
                for(var i = 0; i < ids.length; i++){
                    var str = "<div id='node_" + ids[i] + "'><span class='tree_selected_span'>";
                    str += names[i];
                    str += "<input type='button' class='button_delete' value=' ' onclick='removeNodeFromList(\"logic-channel|<id>" + ids[i] + "</id>\")'/></span><br/></div>";
                    if(divObj.innerHTML == ""){
                        str = "<br/>"+str;
                    }
                    divObj.innerHTML = divObj.innerHTML + str;
                }
            }

        }

        //加载checkbox，设置了attr就会显示checkbox
        function addCheckedBox(node) {
            var id = node.id;
            if(id.indexOf("logic-channel") > -1){
                var value = ""+getIdElement(id);
                if(choose != "" && choose.indexOf(value) > -1){
                    node.attributes["checked"] = true;
                }else{
                    node.attributes["checked"] = false;                                                                       
                }
            }
        }

        //点击checkbox事件
        function onCheckChange(node) {
            var id = node.id;
            if (id == null) return;

//            alert(node.getUI().isChecked())
            if(node.getUI().isChecked()){

                //选中节点层次判断 -- select.js中启用
                var nodeId = id;
                <%--if(${isCheck}){--%>
                    <%--if(!${check}) {--%>
                        <%--//取消选中状态--%>
                        <%--node.getUI().toggleCheck(false);--%>
                        <%--return;--%>
                    <%--}--%>
//                }

                addNodeToList(node);
            }else{
                removeNodeFromList(node.id);
            }
        }

        //判断是否已存在节点
        function existNodeList(node){
            if(document.getElementById("node_"+getIdElement(node.id))){
                return true;
            }else{
                return false;
            }
        }

        //增加选中节点
        function addNodeToList(node) {
            if(existNodeList(node)) return;
            var divObj = document.getElementById("selectedDiv");
            var str = "<div id='node_" + getIdElement(node.id) + "'><span class='tree_selected_span'>";
            str += node.text;
            str += "<input type='button' class='button_delete' value=' ' onclick='removeNodeFromList(\"" + node.id + "\")'/></span><br/></div>";
            if(divObj.innerHTML == ""){
                str = "<br/>"+str;
            }
            divObj.innerHTML = divObj.innerHTML + str;
        }

        //移除选中节点
        function removeNodeFromList(nodeId) {
            var divObj = document.getElementById("selectedDiv");
            var obj = document.getElementById("node_"+getIdElement(nodeId));
            if(obj == null) return;
            document.all.selectedDiv.removeChild(obj);
            var selectedNodes = Ext.getCmp("tree").getChecked();
            Ext.each(selectedNodes, function(node) {
                if (node.id == nodeId) {
                    //取消选中状态
                    node.getUI().toggleCheck(false);
                }
            });
        }

        //返回结果
        function setResult() {

            var selectedNodes = Ext.getCmp("tree").getChecked();
            var ids = "";
            var names = "";
//            Ext.each(selectedNodes, function(node) {
//                ids += "," + getIdElement(node.id);
//                names += "," + node.text;
//            });
            var divObj = document.getElementById("selectedDiv");
            var a = divObj.innerHTML;
            var select = a.split("<BR>");
            for(var i = 1; i < select.length-1; i++){
                ids += "," + getIdElement(select[i]);
                names += "," + select[i].substring(select[i].indexOf("span>")+5,select[i].indexOf("<INPUT"));
            }
            if (ids != "")
            {
                //alert("<id>" + ids.substring(1, ids.length) + "</id>" + "<name>" + names.substring(1, names.length) + "</name>");
                window.returnValue = "<id>" + ids.substring(1, ids.length) + "</id>" + "<name>" + names.substring(1, names.length) + "</name>";
            } else
            {
                Ext.MessageBox.alert("提示", "请选择逻辑组通道！");
                return;
            }
            window.close();
        }

        //
        function delResult() {
            window.returnValue = undefined;
            self.close();
        }
    </script>
</head>

<body style="overflow:hidden;background-color:#E5F8FC" onload="initTree();">
<center><input type="button" onclick="setResult()" class="button_bc" value="确定" >
    <input class="button_cc" type="button" onclick="delResult()" value="取消" ></center>
<table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0">
    <%--<tr>--%>
    <%--<td height="20" width="60%">--%>
    <%--<input type="button" onClick="setResult();" value=" 确 认 " style="width:100%">--%>
    <%--</td>--%>
    <%--<td width="40%">--%>
    <%--<input type="button" onClick="delResult();" value=" 清 除 " style="width:100%">--%>
    <%--</td>--%>
    <%--</tr>--%>

    <tr>
        <td nowrap>
            <div  id="treeDiv" style="height:100%;width:100%;">
            </div>
        </td>
        <td valign="top" height="100%"><div style="display:none" class="tree_selected_div" id="selectedDiv"></div>
        </td>
    </tr>
</table>
</body>
</html>