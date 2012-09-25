<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <title>型号信息树</title>
    <link href="${themesPath}/css/style.css" rel="stylesheet" type="text/css">
    <%@ include file="/common/metaTree.jsp" %>
    <script type="text/javascript">
        function initTree() {
            //节点图标
            var imageUrl = treeImage(iconPath, [
                "qbs_subcheck2.gif",       //0
                "sch_part1.gif",       //1
                "qbs_subcheck2.gif"      //2
            ]);

            //定义树的根节点
            var root = new Ext.tree.AsyncTreeNode({id:'root',text:'设备型号',icon:iconPath + 'qbs_subcheck2.gif"'});

            //构造树面板 ，url="treedata.jsp"定义了异步数据源 .
            var treePanel = new TreePanel(root, null, "model-property-tree!treedata", imageUrl, " ");

            //默认加载第一层节点
            root.expand(1);

            //添加点击节点触发的函数onClick
            treePanel.setClickNodeFunction(onClick);
        }

        //点击事件
        var result;
        function onClick(node, e)
        {
            var id = node.id;
            if (id == null) return;
            if (id == "root") {
                result = undefined;
            } else {
                result = getIdElement(id) + "," + node.text;
            }
        }


    function OK(){
         if(result == null){
             Ext.MessageBox.alert("提示", "未选择任何设备型号！");
         }  else {
             window.returnValue = result;
             self.close();
         }
    }

    function EXITW(){
        window.returnValue = undefined;
        self.close();
    }
    </script>
</head>

<body style="overflow:hidden;" onload="initTree();">
<center><input type="button" onclick="OK()" class="button_bc" value="确定" >
    <input class="button_cc" type="button" onclick="EXITW()" value="取消" ></center>
<div id="treeDiv"></div>
</body>
</html>