<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <title>状态通道树</title>
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
            var root = new Ext.tree.AsyncTreeNode({id:'root',text:'状态通道信息',icon:iconPath + 'root.gif',async:false});


            //构造树面板 ，url="treedata.jsp"定义了异步数据源 .
            var treePanel = new TreePanel(root, null, "status-channel-tree!treedata?treetype=${treetype}", imageUrl, " ");

            //默认加载第一层节点
            root.expand(1);

            //添加菜单显示函数，根据自己需求在要显示菜单的节点上显示菜单
            //            treePanel.setMenuStatus(menuStatus);

            //添加点击节点触发的函数onClick
            treePanel.setClickNodeFunction(onClick);

            treePanel.setExpandNodeFunction(doExpand);
            regist();
        }
        var nodeid;
        function doExpand(node){
            if (nodeid && document.getElementById(nodeid)) {
                var wh = document.getElementById("ext-gen8").style.height;
                wh = wh.substring(0, wh.indexOf("px"));
                var top = document.getElementById(nodeid).offsetTop;
                document.getElementById("ext-gen8").scrollTop = top + wh / 3;
            }

        }

        function query(position) {
            var tree = Ext.getCmp("tree");
            //查询条件
            var name = document.all.condition.value;
            if (!name) {
                Ext.MessageBox.alert("提示", "请输入查询条件！");
                return;
            }else if(name.indexOf("%") > -1){
                Ext.MessageBox.alert("提示", "该节点不存在！");
                return;
            }
            //查询结果的序列号
            var index = document.all.index.value;
            //默认开始取第一条数据
            if (!index || position == 0) {
                index = position;
            }
            else {
                index = parseInt(index) + position;
            }
            var treetype = "${treetype}";
            Ext.Ajax.request({
                url:"status-channel-tree!query",
                params:{
                    name:name,
                    index:index,
                    treetype:treetype
                },
                success:function(response, options) {
                    var revalue = response.responseText;
                    if (revalue.length == 7) {
                        Ext.MessageBox.alert("提示", "没有该节点！");
                        document.all.index.value = -1;
                        document.all.totalCount.value = 0;
                    }
                    else {
                        var path = revalue.substring(0, revalue.indexOf('index:'));
                        var index = parseInt(revalue.substring(revalue.indexOf('index:') +
                                                               6, revalue.indexOf('totalCount:')));
                        var totalCount = parseInt(revalue.substring(revalue.indexOf('totalCount:') +
                                                                    11, revalue.length));
                        //                        tree.expandPath(path);
                        var nodes = path.split("/");
                        nodeid = nodes[nodes.length - 1];
                        tree.expandPath(path);
                        tree.selectPath(path);
                        document.all.index.value = index;
                        document.all.totalCount.value = totalCount;
                    }
                    queryControl();
                }
            });

        }

        function queryControl() {
            var index = document.all.index.value;
            var totalCount = document.all.totalCount.value;
            var indexChecker = (!index || index <= 0);
            var totalCountChecker = (!totalCount || totalCount <= 0);
            if (indexChecker || totalCountChecker) {
                document.all.up.style.display = "none";
            }
            else {
                document.all.up.style.display = "";
            }
            if (totalCountChecker || index >= totalCount - 1) {
                document.all.down.style.display = "none";
            }
            else {
                document.all.down.style.display = "";
            }
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
            if (id == "root") {
                result = undefined;
            }
            else if (id.indexOf("device-type") >= 0) {
                result = undefined;
            }
            else if (id.indexOf("device-sub-type") >= 0) {
                    result = undefined;
                }
                else if (id.indexOf("device") >= 0) {
                        result = undefined;
                    }
                    else if (id.indexOf("channel") >= 0) {
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
                Ext.MessageBox.alert("提示", "请选选择通道！");
            }
        }

        function EXIT() {
            window.returnValue = undefined;
            self.close();
        }

        function chaxun(){
            if(event.keyCode == 13) query(0);
        }
    </script>
</head>

<body style="overflow:hidden;" onload="initTree();">
<center><input type="button" class="button_bc" onclick="OK();" value="确定"><input type="button" class="button_bc"
                                                                                 onclick="EXIT();" value="取消"></center>
关键词：<input type="text" class="input_zhongchaxun" name="condition" onkeydown="chaxun();">
<a onclick="query(0);" style="cursor:pointer">
    <image title="查找" src="${themesPath}/css/web_icon_021.gif"></image>
</a>
<%--<input type="button" value="查询" class="button_bc" onclick="query(0);" >--%>

<a name="up" onclick="query(-1);" style="cursor:pointer;display:none;">
    <image title="上一个" src="${themesPath}/images/icons/back.gif"></image>
</a>
&nbsp;
<a name="down" onclick="query(1);" style="cursor:pointer;display:none;">
    <image title="下一个" src="${themesPath}/images/icons/next.gif"></image>
</a>
<input type="hidden" value="" name="index">
<input type="hidden" value="" name="totalCount">

<div id="treeDiv"></div>
</body>
</html>