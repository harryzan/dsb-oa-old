<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <title>部门用户树</title>
    <%@ include file="/common/metaTree.jsp" %>
    <script type="text/javascript" src="${scriptsPath}/system/function.js"></script>
    <script type="text/javascript">
//        var privilegecode = "s02_user_D,s02_user_R,s02_user_U,s02_user_C,s02_user_privilege";
//        var result = doPrivilege(privilegecode);
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
                treeMenu.addItem(new MenuItem(node, "刷新", updateChildren));
            } else if (node.id.indexOf("sys-dept") >= 0) {
//                if (!result.s02_user_C) {
                    treeMenu.addItem(new MenuItem(node, "添加用户", "sys-user!input?sysdeptid=" + getIdElement(id)));
                    treeMenu.addSeparator();
//                }
//                if (!result.s02_user_U) {
                    treeMenu.addItem(new MenuItem(node, "粘帖", paste));
                    treeMenu.addSeparator();
//                }
                treeMenu.addItem(new MenuItem(node, "刷新", updateChildren));
            }
            else if (node.id.indexOf("sys-user") >= 0) {
//                if (!result.s02_user_U) {
                    treeMenu.addItem(new MenuItem(node, "更新用户", "sys-user!update?id=" + getIdElement(id)));
                    treeMenu.addSeparator();
//                }
//                if (!result.s02_user_U) {
                    var status = getElementValue(id, "status");
                    if (status == "true") {
                        treeMenu.addItem(new MenuItem(node, "禁用用户", "sys-user!changestatus?status=false&id=" + getIdElement(id)));
                    }
                    else {
                        treeMenu.addItem(new MenuItem(node, "激活用户", "sys-user!changestatus?status=true&id=" + getIdElement(id)));
                    }
                    treeMenu.addSeparator();
//                }
//                if (!result.s02_user_D) {
                    var del = getElementValue(id, "delete");
                    if (del == "true") {
                        treeMenu.addItem(new MenuItem(node, "删除用户", "sys-user!delete?id=" + getIdElement(id), true));
                        treeMenu.addSeparator();
                    }
//                }
//                if (!result.s02_user_U) {
                    treeMenu.addItem(new MenuItem(node, "清除密码", "sys-user!clearpassword?id=" + getIdElement(id)));
                    treeMenu.addSeparator();
                    treeMenu.addItem(new MenuItem(node, "剪切", cut));
                    treeMenu.addSeparator();
//                }
//                if (!result.s02_user_privilege) {
                    treeMenu.addItem(new MenuItem(node, "配置权限", "sys-privilege-tree?sysuserid=" + getIdElement(id)));
                    treeMenu.addSeparator();
//                }
                treeMenu.addItem(new MenuItem(node, "刷新",  updateChildren));
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
            } else if (id.indexOf("sys-user") >= 0) {
//                if(!result.s02_user_R){
                    TreeAction("sys-user?id=" + getIdElement(id));
//                }
            }
        }

        var usernode;

        function cut(node){
            usernode = node;
        }

        function paste(node){
            if(!usernode){
                Ext.MessageBox.alert("提示", "请选择剪切的用户！");
            }
            Ext.Ajax.request({
                url:"sys-user!changeDept",
                params :{
                    id:getIdElement(usernode.id),
                    sysdeptid:getIdElement(node.id)
                },
                success:function (response, options) {
                    try {
                        updateChildren(node);
                        updateChildren(usernode.parentNode);
                    }
                    catch(E) {
                    }

                    usernode =undefined;
                    Ext.MessageBox.alert("提示", "操作成功！");
                },
                failure: function(response, options){
                    usernode =undefined;
                    Ext.MessageBox.alert("提示", "操作失败！");
                }
            });

        }
    </script>
</head>

<body onload="initTree();">
<div id="treeDiv"></div>
</body>
</html>