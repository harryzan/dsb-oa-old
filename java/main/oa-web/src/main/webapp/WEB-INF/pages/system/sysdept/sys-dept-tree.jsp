<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <title>单位部门树</title>
    <%@ include file="/common/metaTree.jsp" %>
    <script type="text/javascript" src="${scriptsPath}/system/function.js"></script>
    <script type="text/javascript">
//        var privilegecode = "s01_dept_D,s01_dept_R,s01_dept_U,s01_dept_C";
//        var result = doPrivilege(privilegecode);
        function initTree() {
            //节点图标
            var imageUrl = treeImage(iconPath, [
                "closeread.gif",       //0
                "unit.gif",       //1
                "fileread.gif"      //2
            ]);

            //定义树的根节点
            var root = new Ext.tree.AsyncTreeNode({id:'root',text:'单位信息',icon:iconPath + 'departments.gif'});

            //构造树面板 ，url="treedata.jsp"定义了异步数据源 .
            var treePanel = new TreePanel(root, null, "sys-dept-tree!treedata", imageUrl, " ");

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
//                if(!result.s01_dept_C){
                    treeMenu.addItem(new MenuItem(node, "添加", "sys-dept!input"));
                treeMenu.addSeparator();
//                }
                treeMenu.addItem(new MenuItem(node, "刷新", updateChildren));
            } else if (node.id.indexOf("sys-dept") >= 0) {
//                if(!result.s01_dept_U){
                    treeMenu.addItem(new MenuItem(node, "更新", "sys-dept!input?id=" + getIdElement(id)));
                treeMenu.addSeparator();
//                }
//                if(!result.s01_dept_C){
                    treeMenu.addItem(new MenuItem(node, "添加", "sys-dept!input?parentid=" + getIdElement(id)));
                treeMenu.addSeparator();
//                }
//                if(!result.s01_dept_R){
                    treeMenu.addItem(new MenuItem(node, "查看", "sys-dept!view?id=" + getIdElement(id)));
                treeMenu.addSeparator();
//                }
//                if(!result.s01_dept_D){
                    treeMenu.addItem(new MenuItem(node, "删除", "sys-dept!delete?id=" + getIdElement(id), true));
                treeMenu.addSeparator();
//                }
//                if(!result.s01_dept_U){
                    treeMenu.addItem(new MenuItem(node, "上移", up));
                treeMenu.addSeparator();
                treeMenu.addItem(new MenuItem(node, "下移", down));
                treeMenu.addSeparator();
//                }
                treeMenu.addItem(new MenuItem(node, "刷新", updateChildren));
            } else {
//                treeMenu.addItem(new MenuItem(node, "删除", "logic-system!delete?id=" + getIdElement(id)));
//                treeMenu.addSeparator();
                treeMenu.addItem(new MenuItem(node, "刷新", updateChildren));
            }

            //根据节点id定义加载一项菜单
            treeMenu.showAt(e.getXY());
        }

        function up(node){
            operation(node, 'sys-dept!operation?upoperation=true&id=' + getIdElement(node.id));
        }

        function down(node){
            operation(node, 'sys-dept!operation?upoperation=false&id=' + getIdElement(node.id));
        }

        function operation(node, url){
            getUrlContent(url, "POST", null);
            updateParent(node);
        }

        //点击事件
        function onClick(node, e)
        {
            var id = node.id;
            if (id == null) return;
            if (id == "root") {
                TreeAction("${ctx}/common/blank");
            } else if (id.indexOf("sys-dept") >= 0) {
//                if(!result.s01_dept_R){
                    TreeAction("sys-dept?id=" + getIdElement(id));
//                }
            }
        }
    </script>
</head>

<body onload="initTree();">
<div id="treeDiv"></div>
</body>
</html>