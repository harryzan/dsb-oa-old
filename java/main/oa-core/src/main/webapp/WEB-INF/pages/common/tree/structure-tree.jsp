<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <title>区段结构树</title>
    <%@ include file="/common/metaTree.jsp" %>
    <script type="text/javascript">
        function initTree() {

            //节点图标
            var imageUrl = treeImage(iconPath, [
                    "tree_doc.gif",
                "componenttypeblue.gif",    //0       structure
                "contract.gif",             //1       structure
                "office_supplies.gif",       //2 device-type
                "qbs_subbid1.gif",       //3 device-subtype
                "tree_ssjc_td.gif",      //4 device
                "flow.gif"             //5       channel
            ]);

            //定义树的根节点
            var root = new Ext.tree.AsyncTreeNode({id:'root',text:'区段信息',icon:iconPath + 'root.gif',async:false});


            //构造树面板 ，url="treedata.jsp"定义了异步数据源 .
            var treePanel = new TreePanel(root, null, "structure-tree!treedata?rootonly=${rootonly}", imageUrl, " ");

            //默认加载第一层节点
            root.expand(1);

            //添加菜单显示函数，根据自己需求在要显示菜单的节点上显示菜单
            //            treePanel.setMenuStatus(menuStatus);

            //添加点击节点触发的函数onClick
            treePanel.setClickNodeFunction(onClick);
        }

        var result;
        //点击事件
        function onClick(node, e)
        {
            var id = node.id;
            if (id == null) return;
            if (id == "root") {
                result = undefined;
            } else if (id.indexOf("structure") >= 0) {
                result = getIdValue(id) + "," + node.text;
            }
            else {
                result = undefined;
            }
        }

        function OK() {
            if (result) {
                window.returnValue = result;
                self.close();
            }
            else {
                Ext.MessageBox.alert("提示", "请选择区段！");
            }
        }

        function EXIT() {
            window.returnValue = undefined;
            self.close();
        }

    </script>
</head>

<body style="overflow:hidden;" onload="initTree();">
<center><input type="button" class="button_bc" onclick="OK();" value="确定"><input type="button" class="button_bc"
                                                                                 onclick="EXIT();" value="取消"></center>

<div id="treeDiv"></div>
</body>
</html>