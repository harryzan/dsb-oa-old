<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <title>文档目录树</title>
    <%@ include file="/common/metaTree.jsp" %>
    <script type="text/javascript">
        function initTree() {
            //节点图标
            var imageUrl = treeImage(iconPath, [
                "closeread.gif",       //0
                "2_3.gif",       //1
                "2_1.gif"        //2
            ]);

            //定义树的根节点
            var root = new Ext.tree.AsyncTreeNode({id:'root',text:'个人文件夹目录',icon:iconPath + 'biaoduan.gif'});

            //构造树面板 ，url="treedata.jsp"定义了异步数据源 .
            var treePanel = new TreePanel(root, null, "doc-category-tree!treedata?docrelatecategoryid=${docrelatecategoryid}&docclientcategoryid=${docclientcategoryid}", imageUrl, " ");

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

        //点击事件
        var doccategoryid = null;
        function onClick(node, e)
        {
            var id = node.id;
            if (id == null) {
                doccategoryid = null;
            }
            if (id == "root") {
                doccategoryid = null;
            } else if (id.indexOf("doc-category") >= 0) {
                if(id.indexOf("doc-category2") >= 0){
                    doccategoryid = null;
                    Ext.MessageBox.alert("提示", "请选择未关联的文档目录！");
                }
                else {
                    doccategoryid = getIdElement(id);
                }
            }
        }

        function demo(){
            if(doccategoryid != null){
                var url = "doc-relate-category!save?docclientcategoryid=${docclientcategoryid}&parentid=${docrelatecategoryid}&doccategoryid=" + doccategoryid;
                location = url;
            }
            else {
                Ext.MessageBox.alert("提示", "至少选择一个文档目录！");
            }
        }

        function repage(){
            var url1 = "doc-relate-category!view?id=${docrelatecategoryid}";
            var url2 = "doc-client-category!view?id=${docclientcategoryid}";
            if(url1.indexOf("=") == url1.length - 1) {
                location = url2;
            }
            else {
                location = url1;
            }
        }
    </script>
</head>

<body style="overflow:hidden;" onload="initTree();">
<center><input type="button" class="button_cc" value="关联" onclick="demo();" >&nbsp;<input type ="button" class="button_cc" value="返回" onclick="repage();" > </center>
<div id="treeDiv"></div>
</body>
</html>