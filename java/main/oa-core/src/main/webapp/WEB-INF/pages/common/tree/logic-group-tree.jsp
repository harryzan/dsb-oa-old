<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <title>逻辑组树</title>
    <%@ include file="/common/metaTree.jsp" %>
    <script type="text/javascript">
        function initTree() {
            //节点图标
            var imageUrl = treeImage(iconPath, [
                "main_material.gif",       //0
                "swimming.gif",       //1
                "template_pic.gif"       //2
            ]);

            //定义树的根节点
            var root = new Ext.tree.AsyncTreeNode({id:'root',text:'逻辑组信息',icon:iconPath + 'qbs_subbid2.gif'});

            //构造树面板 ，url="treedata.jsp"定义了异步数据源 .
            var treePanel = new TreePanel(root, null, "logic-group-tree!treedata", imageUrl, " ");

            //默认加载第一层节点
            root.expand(1);

            //添加菜单显示函数，根据自己需求在要显示菜单的节点上显示菜单
            treePanel.setMenuStatus(menuStatus);

            //添加点击节点触发的函数onClick
            treePanel.setClickNodeFunction(onClick);
        }


        //根据条件限定在节点上显示何种菜单
        function menuStatus(node, e) {
            var treeMenu = new TreeMenu();
            var id = node.id;
//            if (node.id == "root") {
//                treeMenu.addItem(new MenuItem(node, "刷新", updateChildren));
//            } else if (node.id.indexOf("monitor-proj-type") >= 0) {
//                treeMenu.addItem(new MenuItem(node, "添加", "monitor-proj!input?monitortypeid=" + getIdElement(id)));
//                treeMenu.addSeparator();
//                treeMenu.addItem(new MenuItem(node, "刷新", updateChildren));
//            }
//            else if(node.id.indexOf("monitor-proj") >= 0) {
//                treeMenu.addItem(new MenuItem(node, "更新", "monitor-proj!input?id=" + getIdElement(id)));
//                treeMenu.addSeparator();
//                treeMenu.addItem(new MenuItem(node, "删除", "monitor-proj!delete?id=" + getIdElement(id)));
//                treeMenu.addSeparator();
//                treeMenu.addItem(new MenuItem(node, "设置", "monitor-proj!device?id=" + getIdElement(id)));
//                treeMenu.addSeparator();
//                treeMenu.addItem(new MenuItem(node, "刷新", updateChildren));
//            }
//            else {
//                treeMenu.addItem(new MenuItem(node, "刷新", updateChildren));
//            }
            treeMenu.addItem(new MenuItem(node, "刷新", updateChildren));

            //根据节点id定义加载一项菜单
            treeMenu.showAt(e.getXY());
        }

        //点击事件

        var returnvalue = undefined;
        function onClick(node, e)
        {
            var id = node.id;
            if (id == null) return;
            if (id.indexOf("logic-group") >= 0) {
                returnvalue = getIdElement(id) + "," + node.text;
            }
            else {
                returnvalue = undefined;
            }
        }

        function OK(){

            if(returnvalue == undefined){
                Ext.MessageBox.alert("提示", "未选择任何逻辑组！");
                return;
            }
            window.returnValue = returnvalue;
            self.close();
        }
        function EXIT(){
            window.returnValue = undefined;
            self.close();
        }
    </script>


</head>

<body style="overflow:hidden;" onload="initTree();">
<center><input type ="button" class="button_bc" value="确定" onclick="OK();" > &nbsp;<input type ="button" class="button_cc" value="取消" onclick="EXIT();" >  </center>
<div id="treeDiv"></div>
</body>
</html>