<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2009-5-19
  Time: 10:18:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>设置角色权限</title>
<link href="${themesPath}/oldcss/style.css" rel="stylesheet" type="text/css">
    <%@ include file="/common/metaTree.jsp" %>
    <script type="text/javascript">
        function initTree() {
            //节点图标
            var imageUrl = treeImage(iconPath, [
                "closeread.gif",       //0
                "2_1.gif",       //1
                "1_1.gif"      //2
            ]);

            //定义树的根节点
            var root = new Ext.tree.AsyncTreeNode({id:'root',text:'系统权限信息',icon:iconPath + 'privilege_relate.gif'});

            //构造树面板 ，url="treedata.jsp"定义了异步数据源 .
            var treePanel = new TreePanel(root, null, "sys-privilege-tree!treedata?sysroleid=${sysroleid}", imageUrl, " ");

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
                treeMenu.addItem(new MenuItem(node, "刷新", updateChildren));
            } else if (node.id.indexOf("sys-privilege") >= 0) {
                treeMenu.addItem(new MenuItem(node, "赋予权限", privilege0));
                treeMenu.addItem(new MenuItem(node, "全部赋予", privilege1));
                treeMenu.addItem(new MenuItem(node, "赋予全部查看权限", privilege1R));
                treeMenu.addItem(new MenuItem(node, "赋予全部修改权限", privilege1U));
                treeMenu.addItem(new MenuItem(node, "赋予全部新建权限", privilege1C));
                treeMenu.addItem(new MenuItem(node, "赋予全部删除权限", privilege1D));
                treeMenu.addSeparator();
                treeMenu.addItem(new MenuItem(node, "撤销权限", privilege2));
                treeMenu.addItem(new MenuItem(node, "全部撤销", privilege3));
                treeMenu.addItem(new MenuItem(node, "撤销全部查看权限", privilege3R));
                treeMenu.addItem(new MenuItem(node, "撤销全部修改权限", privilege3U));
                treeMenu.addItem(new MenuItem(node, "撤销全部新建权限", privilege3C));
                treeMenu.addItem(new MenuItem(node, "撤销全部删除权限", privilege3D));
                treeMenu.addSeparator();
                treeMenu.addItem(new MenuItem(node, "刷新", updateChildren));
            }
            else {
                treeMenu.addItem(new MenuItem(node, "刷新", updateChildren));
            }

            //根据节点id定义加载一项菜单
            treeMenu.showAt(e.getXY());
        }

        function privilege0(node){
            changePrivilege("sys-role!saveprivilege?id=${sysroleid}&savetype=0&sysprivilegeid=" + getIdElement(node.id), node);
        }

        function privilege1(node){
            changePrivilege("sys-role!saveprivilege?id=${sysroleid}&savetype=1&sysprivilegeid=" + getIdElement(node.id), node);
        }

        function privilege2(node){
            changePrivilege("sys-role!saveprivilege?id=${sysroleid}&savetype=2&sysprivilegeid=" + getIdElement(node.id), node);
        }

        function privilege3(node){
            changePrivilege("sys-role!saveprivilege?id=${sysroleid}&savetype=3&sysprivilegeid=" + getIdElement(node.id), node);
        }

        function privilege1C(node){
            changePrivilege("sys-role!saveprivilege?id=${sysroleid}&savetype=1&type=C&sysprivilegeid=" + getIdElement(node.id), node);
        }
        function privilege1R(node){
            changePrivilege("sys-role!saveprivilege?id=${sysroleid}&savetype=1&type=R&sysprivilegeid=" + getIdElement(node.id), node);
        }
        function privilege1U(node){
            changePrivilege("sys-role!saveprivilege?id=${sysroleid}&savetype=1&type=U&sysprivilegeid=" + getIdElement(node.id), node);
        }
        function privilege1D(node){
            changePrivilege("sys-role!saveprivilege?id=${sysroleid}&savetype=1&type=D&sysprivilegeid=" + getIdElement(node.id), node);
        }

        function privilege3C(node){
            changePrivilege("sys-role!saveprivilege?id=${sysroleid}&savetype=3&type=C&sysprivilegeid=" + getIdElement(node.id), node);
        }
        function privilege3R(node){
            changePrivilege("sys-role!saveprivilege?id=${sysroleid}&savetype=3&type=R&sysprivilegeid=" + getIdElement(node.id), node);
        }
        function privilege3U(node){
            changePrivilege("sys-role!saveprivilege?id=${sysroleid}&savetype=3&type=U&sysprivilegeid=" + getIdElement(node.id), node);
        }
        function privilege3D(node){
            changePrivilege("sys-role!saveprivilege?id=${sysroleid}&savetype=3&type=D&sysprivilegeid=" + getIdElement(node.id), node);
        }

        function changePrivilege(url, node){
            Ext.Ajax.request({
                url : url,
                method : "POST",
                params : {},
                success : function(response,options){
                    updateParent(node);
                }
            })
        }

        //点击事件
        function onClick(node, e)
        {
            return;
            var id = node.id;
            if (id == null) return;
            if (id == "root") {
                TreeAction("${ctx}/common/blank");
            } else if (id.indexOf("sys-privilege") >= 0) {
                TreeAction("sys-role!input?id=" + getIdElement(id));
            }
        }
    </script>
</head>

<body leftmargin="0" topmargin="0" marginwidth="0" marginheight="0"  onload="initTree();">
<div id="treeDiv"></div>
</body>
</html>
