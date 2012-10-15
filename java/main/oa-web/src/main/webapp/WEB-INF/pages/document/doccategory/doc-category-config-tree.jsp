<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <title>文档目录权限设置</title>
    <%@ include file="/common/metaTree.jsp" %>
    <script type="text/javascript">
        function initTree() {
            //节点图标
            var imageUrl = treeImage(iconPath, [
                "2_1.gif",       //0查看权限图标
                "2_2.gif",       //1 修改权限图标
                "2_3.gif"      //2  未授权图标
            ]);

            //定义树的根节点
            var root = new Ext.tree.AsyncTreeNode({id:'root',text:'文档目录',icon:iconPath + 'biaoduan.gif'});

            //构造树面板 ，url="treedata.jsp"定义了异步数据源 .
            var treePanel = new TreePanel(root, null, "doc-category-config-tree!treedata?deptid=${deptid}&userid=${userid}&privilegeid=${privilegeid}", imageUrl, " ");

            //默认加载第一层节点
            root.expand(1);

            //添加菜单显示函数，根据自己需求在要显示菜单的节点上显示菜单
            treePanel.setMenuStatus(menuStatus);

            //添加点击节点触发的函数onClick
            treePanel.setClickNodeFunction(onClick);
        }

        var type = ${type};
        //根据条件限定在节点上显示何种菜单
        function menuStatus(node, e) {
            var treeMenu = new TreeMenu();
            var id = node.id;
            if (node.id == "root") {
                treeMenu.addItem(new MenuItem(node, "刷新", updateChildren));
            } else if (node.id.indexOf("doc-category") >= 0) {
                treeMenu.addItem(new MenuItem(node, "赋予查看权限", despense(getIdElement(id)) + "&permittype=1"));
                treeMenu.addSeparator();
                treeMenu.addItem(new MenuItem(node, "全部赋予查看", despense(getIdElement(id)) + "&permittype=2"));
                treeMenu.addSeparator();
                treeMenu.addItem(new MenuItem(node, "赋予修改权限", despense(getIdElement(id)) + "&permittype=3"));
                treeMenu.addSeparator();
                treeMenu.addItem(new MenuItem(node, "全部赋予修改", despense(getIdElement(id)) + "&permittype=4"));
                treeMenu.addSeparator();
                treeMenu.addItem(new MenuItem(node, "取消权限", despense(getIdElement(id)) + "&permittype=5"));
                treeMenu.addSeparator();
                treeMenu.addItem(new MenuItem(node, "全部取消", despense(getIdElement(id)) + "&permittype=6"));
            }
            else {
                treeMenu.addItem(new MenuItem(node, "刷新", updateChildren));
            }

            //根据节点id定义加载一项菜单
            treeMenu.showAt(e.getXY());
        }

        function despense(id){
            var url = "";

            if(type == 1){
                url = "sys-dept!savepermit?id=${deptid}&doccategoryid=" + id;
            }
            else if(type == 2){
                url = "sys-user!savepermit?id=${userid}&doccategoryid=" + id;
            }
            else if(type == 3){
                url = "sys-privilege!savepermit?id=${privilegeid}&doccategoryid=" + id;
            }

            return url;
            
        }

        //点击事件
        function onClick(node, e)
        {
            return;
            var id = node.id;
            if (id == null) return;
            if (id == "root") {
                TreeAction("${ctx}/common/blank");
            } else if (id.indexOf("doc-category") >= 0) {
                TreeAction("doc-category!view?id=" + getIdElement(id));
            }
        }

    </script>
</head>

<body onload="initTree();">
<div id="treeDiv"></div>
</body>
</html>