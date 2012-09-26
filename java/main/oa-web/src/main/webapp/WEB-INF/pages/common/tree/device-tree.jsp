<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <title>设备树</title>
    <%@ include file="/common/metaTree.jsp" %>
    <script type="text/javascript">
        function initTree() {
            //节点图标
            var imageUrl = treeImage(iconPath, [
                "office_supplies.gif",       //0
                "qbs_subbid1.gif",       //1
                "tree_ssjc_td.gif"      //2
            ]);

            //定义树的根节点
            var root = new Ext.tree.AsyncTreeNode({id:'root',text:'设备信息',icon:iconPath + 'root.gif'});

            //构造树面板 ，url="treedata.jsp"定义了异步数据源 .
            var treePanel = new TreePanel(root, null, "device-tree!treedata", imageUrl, " ");

            //默认加载第一层节点
            root.expand(1);

            //添加菜单显示函数，根据自己需求在要显示菜单的节点上显示菜单
            //treePanel.setMenuStatus(menuStatus);

            //添加点击节点触发的函数onClick
            treePanel.setClickNodeFunction(onClick);
            regist();
        }


        //根据条件限定在节点上显示何种菜单
        function menuStatus(node, e) {
            var treeMenu = new TreeMenu();

              treeMenu.addItem(new MenuItem(node, "刷新", updateChildren));
            //根据节点id定义加载一项菜单
            treeMenu.showAt(e.getXY());
        }

        var result;
        //点击事件
        function onClick(node, e)
        {
            var id = node.id;
            if (id == null) return;
            <%--if (id == "root") {--%>
                <%--TreeAction("${ctx}/common/blank");--%>
            <%--} else if (id.indexOf("logic-system") >= 0) {--%>
                <%--TreeAction("device-page?logicsystemid=" + getIdElement(id));--%>
            <%--}--%>
            if(id == "root"){
                result = undefined;
            }
            else if(id.indexOf("device-type") >= 0){
                 result = undefined;
            }
            else if(id.indexOf("device-sub-type") >= 0){
                 result = undefined;
            }
            else if(id.indexOf("device") >= 0){
                 result = getIdElement(id) + "," + node.text;
            }
            else {                                                                 
                result = undefined;
            }
        }

     function OK(){
         if(result){
             window.returnValue = result;
             self.close();
         }
         else { 
             Ext.MessageBox.alert("提示", "请选择设备！");
         }
    }

    function EXIT(){
        window.returnValue = undefined;
        self.close();
    }
    </script>
</head>

<body style="overflow:hidden;" onload="initTree();">
<center><input type="button"  class="button_bc" onclick="OK()" value="确定" ><input type="button"  class="button_bc" onclick="EXIT()" value="取消" ></center>
<div id="treeDiv"></div>
</body>
</html>