<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <title>文档目录树</title>
    <%@ include file="/common/metaTree.jsp" %>
    <script type="text/javascript" src="${scriptsPath}/system/function.js"></script>
    <script type="text/javascript">
//        var privilegecode = "d01_category_D,d01_category_R,d01_category_U,d01_category_C,d01_category_privilege";
//        var result = doPrivilege(privilegecode);
        function initTree() {
            //节点图标
            var imageUrl = treeImage(iconPath, [
                "closeread.gif",       //0
                "choose_status.gif",       //1
                "boq.gif"      //2
            ]);

            //定义树的根节点
            var root = new Ext.tree.AsyncTreeNode({id:'root',text:'文档目录',icon:iconPath + 'biaoduan.gif'});

            //构造树面板 ，url="treedata.jsp"定义了异步数据源 .
            var treePanel = new TreePanel(root, null, "doc-category-tree!treedata", imageUrl, " ");

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
//                if (!result.d01_category_C) {
                    treeMenu.addItem(new MenuItem(node, "添加目录", "doc-category!input"));
                    treeMenu.addSeparator();
//                }
//                if (!result.d01_category_privilege) {
                    treeMenu.addItem(new MenuItem(node, "权限维护", parentlocation));
                    treeMenu.addSeparator();
//                }
                treeMenu.addItem(new MenuItem(node, "刷新", updateChildren));
            } else if (node.id.indexOf("doc-category") >= 0) {
//                if (!result.d01_category_C) {
                    treeMenu.addItem(new MenuItem(node, "添加目录", "doc-category!input?parentid=" + getIdElement(id)));
                    treeMenu.addSeparator();
//                }
//                if (!result.d01_category_U) {
                    treeMenu.addItem(new MenuItem(node, "更新目录", "doc-category!input?id=" + getIdElement(id)));
                    treeMenu.addSeparator();
//                }
//                if (!result.d01_category_D) {
                    treeMenu.addItem(new MenuItem(node, "删除目录", "doc-category!delete?id=" + getIdElement(id), true));
                    treeMenu.addSeparator();
//                }
//                if (!result.d01_category_U) {
                    treeMenu.addItem(new MenuItem(node, "上移", up));
                    treeMenu.addSeparator();
                    treeMenu.addItem(new MenuItem(node, "下移", down));
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
            } else if (id.indexOf("doc-category") >= 0) {
//                if(!result.d01_category_R){
                    TreeAction("doc-category?id=" + getIdElement(id));
//                }
            }
        }

        function up(node){
            moveOperation(node, 'doc-category!operation?upoperation=true&id=' + getIdElement(node.id));
        }
        function down(node){
            moveOperation(node, 'doc-category!operation?upoperation=false&id=' + getIdElement(node.id));
        }

        function moveOperation(node, url){
            getUrlContent(url, "POST", null);
            updateParent(node);
        }

        function parentlocation(){
            parent.location = "doc-privilege-list";
        }
    </script>
</head>

<body onload="initTree();">
<div id="treeDiv"></div>
</body>
</html>