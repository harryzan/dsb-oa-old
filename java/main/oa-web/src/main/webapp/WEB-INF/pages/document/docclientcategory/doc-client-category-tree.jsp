<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <title>业务文档类型树</title>
    <%@ include file="/common/metaTree.jsp" %>
    <script type="text/javascript" src="${scriptsPath}/system/function.js"></script>
    <script type="text/javascript">
//        var privilegecode = "d03_client_D,d03_client_R,d03_client_U,d03_client_C,d03_relate_D,d03_relate_R,d03_relate_U,d03_relate_C";
//        var result = doPrivilege(privilegecode);
        function initTree() {
            //节点图标
            var imageUrl = treeImage(iconPath, [
                "closeread.gif",       //0
                "choose_status.gif",       //1
                "boq.gif"      //2
            ]);

            //定义树的根节点
            var root = new Ext.tree.AsyncTreeNode({id:'root',text:'业务文档类型',icon:iconPath + 'biaoduan.gif'});

            //构造树面板 ，url="treedata.jsp"定义了异步数据源 .
            var treePanel = new TreePanel(root, null, "doc-client-category-tree!treedata", imageUrl, " ");

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
//                if (!result.d03_client_C) {
                    treeMenu.addItem(new MenuItem(node, "添加类型", "doc-client-category!input"));
                    treeMenu.addSeparator();
//                }
                treeMenu.addItem(new MenuItem(node, "刷新", updateChildren));
            } else if (node.id.indexOf("doc-client-category") >= 0) {
//                if (!result.d03_client_C) {
                    treeMenu.addItem(new MenuItem(node, "添加类型", "doc-client-category!input?parentid=" + getIdElement(id)));
                    treeMenu.addSeparator();
//                }
//                if (!result.d03_client_U) {
                    treeMenu.addItem(new MenuItem(node, "更新类型", "doc-client-category!input?id=" + getIdElement(id)));
                    treeMenu.addSeparator();
//                }
//                if (!result.d03_client_D) {
                    treeMenu.addItem(new MenuItem(node, "删除类型", "doc-client-category!delete?id=" + getIdElement(id), true));
                    treeMenu.addSeparator();
//                }
//                if (!result.d03_relate_C) {
                    treeMenu.addItem(new MenuItem(node, "添加目录", "doc-category-tree?docclientcategoryid=" + getIdElement(id)));
                    treeMenu.addSeparator();
//                }
//                if (!result.d03_client_U) {
                    treeMenu.addItem(new MenuItem(node, "上移", up));
                    treeMenu.addSeparator();
                    treeMenu.addItem(new MenuItem(node, "下移", down));
                    treeMenu.addSeparator();
//                }
                treeMenu.addItem(new MenuItem(node, "刷新", updateChildren));
            }
            else if (node.id.indexOf("doc-relate-category") >= 0) {
//                if (!result.d03_relate_C) {
                    treeMenu.addItem(new MenuItem(node, "添加目录", "doc-category-tree?docrelatecategoryid=" + getIdElement(id)));
                    treeMenu.addSeparator();
//                }
//                if (!result.d03_relate_U) {
                    treeMenu.addItem(new MenuItem(node, "重命名", "doc-relate-category!input?id=" + getIdElement(id)));
                    treeMenu.addSeparator();
//                }
//                if (!result.d03_relate_D) {
                    treeMenu.addItem(new MenuItem(node, "删除目录", "doc-relate-category!delete?id=" + getIdElement(id), true));
                    treeMenu.addSeparator();
//                }
//                if (!result.d03_relate_U) {
                    treeMenu.addItem(new MenuItem(node, "上移", uprelate));
                    treeMenu.addSeparator();
                    treeMenu.addItem(new MenuItem(node, "下移", downrelate));
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

        function up(node){
            operation(node, 'doc-client-category!operation?upoperation=true&id=' + getIdElement(node.id));
        }

        function down(node){
            operation(node, 'doc-client-category!operation?upoperation=false&id=' + getIdElement(node.id));
        }

        function uprelate(node){
            operation(node, 'doc-relate-category!operation?upoperation=true&id=' + getIdElement(node.id));
        }

        function downrelate(node){
            operation(node, 'doc-relate-category!operation?upoperation=false&id=' + getIdElement(node.id));
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
            } else if (id.indexOf("doc-client-category") >= 0) {
//                if(!result.d03_client_R){
                    TreeAction("doc-client-category?id=" + getIdElement(id));
//                }
            } else if(id.indexOf("doc-relate-category") >= 0){
//                if(!result.d03_relate_R){
                    TreeAction("doc-relate-category?id=" + getIdElement(id));
//                }
            }
        }
    </script>
</head>

<body onload="initTree();">
<div id="treeDiv"></div>
</body>
</html>