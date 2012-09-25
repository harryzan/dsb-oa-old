<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <title>页面文档类型树</title>
    <%@ include file="/common/metaTree.jsp" %>
    <script type="text/javascript">
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

            treeMenu.addItem(new MenuItem(node, "刷新", updateChildren));

            //根据节点id定义加载一项菜单
            treeMenu.showAt(e.getXY());
        }

        var returnvalue = undefined;

        //点击事件
        function onClick(node, e)
        {
            var id = node.id;
            if (id == null) return;
            if (id == "root") {
                returnvalue = undefined;
            } else if (id.indexOf("doc-client-category") >= 0) {
                TreeAction("doc-client-category!view?id=" + getIdElement(id));
                returnvalue = node.text + "," + getIdElement(id);
            }
        }

        function OK(){
            if(returnvalue == undefined){
                Ext.MessageBox.alert("提示", "请选择业务文档类型！");
            }
            else {
                window.returnValue = returnvalue;
                self.close();
            }

        }
        function EXIT(){
            window.returnValue = undefined;
            self.close();
            
        }
    </script>
</head>

<body style="overflow:hidden;" onload="initTree();">
<center><input type ="button" value="确 定" onclick="OK();">&nbsp;<input type ="button" value="取 消" onclick="EXIT();">  </center>
<div id="treeDiv"></div>
</body>
</html>