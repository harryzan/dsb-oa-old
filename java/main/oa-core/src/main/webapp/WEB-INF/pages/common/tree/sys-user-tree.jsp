<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <title>部门用户树</title>
    <%@ include file="/common/metaTree.jsp" %>
    <script type="text/javascript">
        var type = window.dialogArguments;

        function initTree() {
            //节点图标
            var imageUrl = treeImage(iconPath, [
                "closeread.gif",       //0
                "unit.gif",       //1
                "user.gif"      //2
            ]);

            //定义树的根节点
            var root = new Ext.tree.AsyncTreeNode({id:'root',text:'部门用户信息',icon:iconPath + 'departments.gif'});

            //构造树面板 ，url="treedata.jsp"定义了异步数据源 .
            var treePanel = new TreePanel(root, null, "sys-user-tree!treedata", imageUrl, " ");

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
            } else if (id.indexOf("sys-dept") >= 0) {
                result = 0 + "," + node.text + "," + getIdElement(id);
            }
            else if (id.indexOf("sys-user") >= 0) {
                result = 1 + "," + node.text + "," + getIdElement(id);
            }
        }
        function OK(){
            if(result == undefined){
                if(type == 0){
                    Ext.MessageBox.alert("提示", "没有选择任何部门！");
                    return;
                }
                else {
                    Ext.MessageBox.alert("提示", "没有选择任何用户！");
                    return;
                }
            }
            if(type == 0){
                if(result.split(",")[0] != 0){
                    Ext.MessageBox.alert("提示", "请选择单位/部门");
                }
                else {
                   window.returnValue = result.split(",")[1] + "," + result.split(",")[2];
                    self.close();
                }
            }
            else {
               if(result.split(",")[0] != 1){
                   Ext.MessageBox.alert("提示", "请选择系统用户！");
                }
                else {
                    window.returnValue = result.split(",")[1] + "," + result.split(",")[2];
                    self.close();
                }
            }
        }
        function EXIT(){
            window.returnValue = undefined;
            self.close();
        }
    </script>
</head>

<body style="overflow:hidden;" onload="initTree();">
<center><input type ="button"  class="button_bc" value="确 定" onclick="OK()">&nbsp;<input type ="button" value="取 消"  class="button_bc" onclick="EXIT()"> </center>
<div id="treeDiv"></div>
</body>
</html>