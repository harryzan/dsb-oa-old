<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <title>系统权限树</title>
    <%@ include file="/common/metaTree.jsp" %>
    <script type="text/javascript" src="${scriptsPath}/system/function.js"></script>
    <script type="text/javascript">
//        var privilegecode = "s03_privilege_D,s03_privilege_R,s03_privilege_U,s03_privilege_C";
//        var result = doPrivilege(privilegecode);
        function initTree() {
            //节点图标
            var imageUrl = treeImage(iconPath, [
                "closeread.gif",       //0
                "working_drawings.gif",       //1
                "fileread.gif"      //2
            ]);

            //定义树的根节点
            var root = new Ext.tree.AsyncTreeNode({id:'root',text:'系统权限信息',icon:iconPath + 'privilege_relate.gif'});

            //构造树面板 ，url="treedata.jsp"定义了异步数据源 .
            var treePanel = new TreePanel(root, null, "sys-privilege-tree!treedata", imageUrl, " ");

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
//                if (!result.s03_privilege_C) {
                    treeMenu.addItem(new MenuItem(node, "添加权限", "sys-privilege!input?parentid="));
                    treeMenu.addSeparator();
//                }
                treeMenu.addItem(new MenuItem(node, "刷新", updateChildren));
            } else if (node.id.indexOf("sys-privilege") >= 0) {
//                if (!result.s03_privilege_C) {
                    treeMenu.addItem(new MenuItem(node, "添加权限", "sys-privilege!input?parentid=" + getIdElement(id)));
                    treeMenu.addSeparator();
                    treeMenu.addItem(new MenuItem(node, "添加操作权限", "sys-privilege!crudinput?parentid=" + getIdElement(id)));
                    treeMenu.addSeparator();
//                }
//                if (!result.s03_privilege_U) {
                    treeMenu.addItem(new MenuItem(node, "更新权限", "sys-privilege!input?id=" + getIdElement(id)));
                    treeMenu.addSeparator();
//                }
//                if (!result.s03_privilege_D) {
                    treeMenu.addItem(new MenuItem(node, "删除权限", "sys-privilege!delete?id=" + getIdElement(id), true));
                    treeMenu.addSeparator();
//                }
                treeMenu.addItem(new MenuItem(node, "刷新", updateChildren));
            }
            else {
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
            } else if (id.indexOf("sys-privilege") >= 0) {
//                if(!result.s03_privilege_R){
                    TreeAction("sys-privilege!view?id=" + getIdElement(id));
//                }
            }
        }
    </script>
</head>

<body onload="initTree();">
<div id="treeDiv"></div>
</body>
</html>