<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <title>系统代码树</title>
    <%@ include file="/common/metaTree.jsp" %>
    <script type="text/javascript" src="${scriptsPath}/system/function.js"></script>
    <script type="text/javascript">
//        var privilegecode = "s05_code_D,s05_code_R,s05_code_U,s05_code_C,s05_list_D,s05_list_R,s05_list_U,s05_list_C";
//        var result = doPrivilege(privilegecode);
        function initTree() {
            //节点图标
            var imageUrl = treeImage(iconPath, [
                "cal_sch.gif",       //0
                "change_list.gif"
            ]);

            //定义树的根节点
            var root = new Ext.tree.AsyncTreeNode({id:'root',text:'系统代码信息',icon:iconPath + 'root.gif'});

            //构造树面板 ，url="treedata.jsp"定义了异步数据源 .
            var treePanel = new TreePanel(root, null, "sys-code-tree!treedata", imageUrl, " ");

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
            if (node.id == "root") {
//                if (!result.s05_code_C) {
                    treeMenu.addItem(new MenuItem(node, "添加代码集", "sys-code!input"));
                    treeMenu.addSeparator();
//                }
                treeMenu.addItem(new MenuItem(node, "刷新", updateChildren));
            } else if (node.id.indexOf("syscodelist") >= 0) {
//                if (!result.s05_list_C) {
                    treeMenu.addItem(new MenuItem(node, "添加代码项", "sys-code-list!input?parentid=" + getIdElement(id)));
                    treeMenu.addSeparator();
//                }
//                if (!result.s05_list_U) {
                    treeMenu.addItem(new MenuItem(node, "更新代码项", "sys-code-list!input?id=" + getIdElement(id)));
                    treeMenu.addSeparator();
//                }
//                if (!result.s05_list_D) {
                    treeMenu.addItem(new MenuItem(node, "删除代码项", "sys-code-list!delete?id=" + getIdElement(id), true));
                    treeMenu.addSeparator();
//                }
                treeMenu.addItem(new MenuItem(node, "刷新", updateChildren));
            } else if (node.id.indexOf("syscode") >= 0) {
//                if (!result.s05_list_C) {
                    treeMenu.addItem(new MenuItem(node, "添加代码项", "sys-code-list!input?syscodeid=" + getIdElement(id)));
                    treeMenu.addSeparator();
//                }
//                if (!result.s05_code_U) {
                    treeMenu.addItem(new MenuItem(node, "更新代码集", "sys-code!input?id=" + getIdElement(id)));
                    treeMenu.addSeparator();
//                }
//                if (!result.s05_code_D) {
                    treeMenu.addItem(new MenuItem(node, "删除代码集", "sys-code!delete?id=" + getIdElement(id), true));
                    treeMenu.addSeparator();
//                }
                treeMenu.addItem(new MenuItem(node, "刷新", updateChildren));
            }

            //根据节点id定义加载一项菜单
            treeMenu.showAt(e.getXY());
        }

        //点击事件
        function onClick(node, e)
        {
            var id = node.id;
            if (id == null) return;
            if (id == "root") {
                TreeAction("${ctx}/common/blank");
            } else if (id.indexOf("syscodelist") >= 0) {
//                if(!result.s05_list_R){
                    TreeAction("sys-code-list?id=" + getIdElement(id));
//                }
            } else if (id.indexOf("syscode") >= 0) {
//                if(!result.s05_code_R){
                    TreeAction("sys-code?id=" + getIdElement(id));
//                }
            }
        }
    </script>
</head>

<body onload="initTree();">
<div id="treeDiv"></div>
</body>
</html>