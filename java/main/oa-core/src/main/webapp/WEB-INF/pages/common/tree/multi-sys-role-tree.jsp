<%--
  Created by IntelliJ IDEA.
  User: cxs
  Date: 11-9-27
  Time: 上午10:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <title>角色树</title>
    <%@ include file="/common/metaTree.jsp" %>
    <script type="text/javascript">
        var choose = "${choose}";
        function initTree() {
            //节点图标
            var imageUrl = treeImage(iconPath, [
                "closeread.gif",       //0
                "member.gif",       //1
                "fileread.gif"      //2
            ]);

            //定义树的根节点
            var root = new Ext.tree.AsyncTreeNode({id:'root',text:'角色信息',icon:iconPath + 'departments.gif'});

            //构造树面板 ，url="treedata.jsp"定义了异步数据源 .
            var treePanel = new TreePanel(root, null, "multi-sys-role-tree!treedata", imageUrl, " ");

            //默认加载第一层节点
            root.expand(1);

            treePanel.getTree().on("checkChange", function(node) {
                onCheckChange(node);
            });

            if(choose != ""){
                var ids = choose.split("|")[0].split(",");
                var names = choose.split("|")[1].split(",")
                var divObj = document.getElementById("selectedDiv");
                for(var i = 0; i < ids.length; i++){
                    var str = "<div id='node_" + ids[i] + "'><span class='tree_selected_span'>";
                    str += names[i];
                    str += "<input type='button' class='button_delete' value=' ' onclick='removeNodeFromList(\"sys-role|<id>" + ids[i] + "</id>\")'/></span><br/></div>";
                    if(divObj.innerHTML == ""){
                        str = "<br/>"+str;
                    }
                    divObj.innerHTML = divObj.innerHTML + str;
                }
            }

            //添加菜单显示函数，根据自己需求在要显示菜单的节点上显示菜单
            //treePanel.setMenuStatus(menuStatus);

            //添加点击节点触发的函数onClick
            regist();
        }

        //加载checkbox，设置了attr就会显示checkbox
        function addCheckedBox(node) {
            var id = node.id;
            if(id.indexOf("sys-role") > -1){
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
            if(node.getUI().isChecked()){
                var nodeId = id;
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

        var result;
        //点击事件
        function onClick(node, e)
        {
            var id = node.id;
            if (id == null) return;
            if(id == "root"){
                result = undefined;
            }
            else if(id.indexOf("sys-role") >= 0){
                 result = getIdElement(id) + "," + node.text;
            }
            else {
                result = undefined;
            }
        }

     function OK(){
         var selectedNodes = Ext.getCmp("tree").getChecked();
            var ids = "";
            var names = "";
            var divObj = document.getElementById("selectedDiv");
            var a = divObj.innerHTML;
            var select = a.split("<BR>");
            for(var i = 1; i < select.length-1; i++){
                ids += "," + getIdElement(select[i]);
                names += "," + select[i].substring(select[i].indexOf("span>")+5,select[i].indexOf("<INPUT"));
            }
            if (ids != "")
            {
                window.returnValue = "<id>" + ids.substring(1, ids.length) + "</id>" + "<name>" + names.substring(1, names.length) + "</name>";
            } else
            {
                Ext.MessageBox.alert("提示", "请选择角色！");
                return;
            }
            window.close();
    }

    function EXIT(){
        window.returnValue = undefined;
        self.close();
    }
    </script>
</head>

<body style="overflow:hidden;" onload="initTree();">
<center><input type="button"  class="button_bc" onclick="OK()" value="确定" ><input type="button"  class="button_bc" onclick="EXIT()" value="取消" ></center>
<table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0">
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