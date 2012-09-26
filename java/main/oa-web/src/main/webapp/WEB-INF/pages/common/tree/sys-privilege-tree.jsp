<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2009-5-19
  Time: 10:18:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>系统权限树</title>
<link href="${themesPath}/oldcss/style.css" rel="stylesheet" type="text/css">
    <%@ include file="/common/metaTree.jsp" %>
    <script type="text/javascript">
        function initTree() {
            //节点图标
            var imageUrl = treeImage(iconPath, [
                "category.gif",       //0
                "working_drawings.gif",       //1
                "fileread.gif"      //2
            ]);

            //定义树的根节点
            var root = new Ext.tree.AsyncTreeNode({id:'root',text:'系统权限信息',icon:iconPath + 'privilege_relate.gif'});

            //构造树面板 ，url="treedata.jsp"定义了异步数据源 .
            var treePanel = new TreePanel(root, null, "sys-privilege-tree!treedata?sysuserid=${sysuserid}", imageUrl, " ");

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

            treeMenu.addItem(new MenuItem(node, "刷新", updateChildren));

            //根据节点id定义加载一项菜单
            treeMenu.showAt(e.getXY());
        }
        var result = undefined;
        //点击事件
        function onClick(node, e)
        {
            var id = node.id;
            if (id == null) return;
            if (id == "root") {
                result = undefined;
            } else if (id.indexOf("sys-doc-privilege") >= 0) {
                result = node.text + "," + getIdElement(id);
            }
        }

        function OK(){
            if(!result){
                Ext.MessageBox.alert("提示", "请选择文档权限！");
            }
            else {
                window.returnValue = result;
                self.close();
            }

        }
        function EXIT(){
            window.returnValue = undefined;
            self.close();
        }
    </script>
</head>

<body  style="overflow:hidden;" onload="initTree();">
<center><input type ="button" class="button_cc" value="确 定" onclick="OK()">&nbsp;<input type ="button" class="button_cc" value="取 消" onclick="EXIT()"> </center>
<div id="treeDiv"></div>
</body>
</html>
