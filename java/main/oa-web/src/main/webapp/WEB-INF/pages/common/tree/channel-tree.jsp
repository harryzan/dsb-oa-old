<%--
  Created by IntelliJ IDEA.
  User: cxs
  Date: 2009-7-22
  Time: 19:04:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <title>通道树</title>
    <%@ include file="/common/metaTree.jsp" %>
    <link href="${themesPath}/oldcss/style.css" rel="stylesheet" type="text/css">
    <script type="text/javascript">
        var channelCode = "${channelCode}";
        var flag = true;
        function initTree() {
            //节点图标
            var imageUrl = treeImage(iconPath, [
                "office_supplies.gif",       //2 device-type
                "qbs_subbid1.gif",       //3 device-subtype
                "tree_ssjc_td.gif",      //4 device
                "flow.gif"
            ]);

            //定义树的根节点
            var root = new Ext.tree.AsyncTreeNode({id:'root',text:'通道编码',icon:iconPath + 'root.gif'});

            //构造树面板 ，url="treedata.jsp"定义了异步数据源 .
            var treePanel = new TreePanel(root, null, "channel-tree!treedata", imageUrl, " ");

            //默认加载第一层节点
            root.expand(1);

            treePanel.getTree().on("checkChange", function(node) {
                onCheckChange(node);
            });   

            //添加点击节点触发的函数onClick
            //treePanel.setClickNodeFunction(onClick);
            treePanel.setExpandNodeFunction(onExpand);
            regist();
        }


        /////////////////////////////////////////////////////////////////////

        function onExpand(node){
             var id = node.id;
            if(id.indexOf("device-type") > -1){}
            else if(id.indexOf("device-sub-type") > -1){
                var children = node.childNodes;
                var list = channelCode.split(",");
                    for (var i=0; i < children.length;i++){
                         children[i].getUI().toggleCheck(false);
                         for(var j=0;j < list.length;j++){
                             if(children[i].text == list[j]){
                                 children[i].getUI().toggleCheck(true);
                                 break;
                             }
                         }
                        //alert(children[i].getUI().isChecked());
                    }
            }
            else if(id.indexOf("device") > -1){

                var children = node.childNodes;
                // device is ckecked
                if(node.getUI().isChecked()){
                    //alert("check");

                    for(var i=0;i<children.length;i++){
                        if(!children[i].getUI().isChecked()){
                            children[i].getUI().toggleCheck(true);
                            //addNodeToList(children[i].text);
                        }
                    }
                }
                //device is not checked
                else{
                     //alert(children.length);
                    var list = channelCode.split(",");
                    for (var i=0; i < children.length;i++){
                         children[i].getUI().toggleCheck(false);
                         for(var j=0;j < list.length;j++){
                             if(children[i].text == list[j]){
                                 children[i].getUI().toggleCheck(true);
                                 break;
                             }
                         }

                    }
                }

            }

        }

            //加载checkbox，设置了attr就会显示checkbox
            function addCheckedBox(node) {
                var id = node.id;
                if(id.indexOf("device-type") > -1){}
                else if(id.indexOf("device-sub-type") > -1){}
                else if(id.indexOf("device") > -1){
                    //var value = node.text;
                    //alert("value: "+value);
                    if(channelCode != "" && channelCode.indexOf(node.text) > -1){
                        if(channelCode.indexOf(",") > -1){
                            if(channelCode.indexOf(node.text+",") > -1 || (channelCode.indexOf("," + node.text) > -1 && (channelCode.indexOf("," + node.text) + node.text.length + 1) == channelCode.length)){
                                node.attributes["checked"] = true;
                            }
                            else{
                                node.attributes["checked"] = false;
                            }
                        }
                        else{
                            //alert(node.text);
                            if(channelCode.indexOf(node.text + "-") < 0){
                                node.attributes["checked"] = true;
                            }
                            else{
                                node.attributes["checked"] = false;
                            }

                        }

                        
                    }else{
                        node.attributes["checked"] = false;
                    }
                    //node.attributes["checked"] = false;
                }
                if(id.indexOf("channel") > -1){
                    if(channelCode != "" && channelCode.indexOf(node.text) > -1){
                        node.attributes["checked"] = true;

                    }else if(node.parentNode.getUI().isChecked()){
                            node.attributes["checked"] = true;
                    }else{
                        node.attributes["checked"] = false;
                    }
                }
            }


        //点击checkbox事件
        function onCheckChange(node) {
            var id = node.id;
            if (id == null) return;
            //judge if it has been existed
            
            
            //alert(node.getUI().isChecked())
            //add item
            if(node.getUI().isChecked()){

                //选中节点层次判断 -- select.js中启用
                
                //choose device
                if(node.text.indexOf("-")<0){
                    var children = node.childNodes;
                    for(var i=0;i<children.length;i++){
                        if(!children[i].getUI().isChecked()){
                            children[i].getUI().toggleCheck(true);
                            //addNodeToList(children[i].text);
                            /*if(channelCode.length > 0){
                                 //通道设备 个数大于零
                                 //channelCode+=","+node.id.substring(9,node.id.length-1);
                                 channelCode+=","+children[i].text;
                             }
                            else{
                                  //通道设备个数为零
                                  //channelCode+=node.id.substring(9,node.id.length-1);
                                  channelCode+=children[i].text;
                             }*/
                        }
                    }


                }
                //alert(channelCode);
                if(channelCode.indexOf(node.text) > -1){
                    //alert(node.text);
                    return;
                }
                else{
                     if(channelCode.length > 0){
                           //通道设备 个数大于零
                           //channelCode+=","+node.id.substring(9,node.id.length-1);
                           channelCode+=","+node.text;
                     }
                     else{
                           //通道设备个数为零
                           //channelCode+=node.id.substring(9,node.id.length-1);
                           channelCode+=node.text;
                     }
                }

                    //alert(channelCode);
            }
            //remove item
            else{

                if(node.text.indexOf("-") < 0 && flag){
                    var children = node.childNodes;
                    //remove all children under the device
                    for(var i=0;i<children.length;i++){
                        if(children[i].getUI().isChecked()){
                            children[i].getUI().toggleCheck(false);
                            
                        }
                    }

                }

                //channelCode.contains()
                var list = channelCode.split(",");
                var result="";
                for(var i=0;i < list.length;i++){
                    if(node.text == list[i]){
                        continue;
                    }
                    if(list[i].length > 0){
                        result += list[i]+",";
                    }

                }
                channelCode = result.substring(0,result.length-1);
                //channelCode = channelCode.replace(node.text,"");

                if(node.text.indexOf("-") > -1){
                    if(node.parentNode.getUI().isChecked()){
                        flag = false;
                        node.parentNode.getUI().toggleCheck(false);
                    }

                }
            }
            flag = true;
        }

        

        /////////////////////////////////////////////////////////////////////


    function OK(){
         

        if(channelCode.length>0){
            var list = channelCode.split(",");
            var code = "";
            for(var i=0;i < list.length;i++){

                 if(list[i].length > 0){
                     if(i == list.length-1){
                        code += list[i];
                      }
                     else{
                        code += list[i]+","; 
                     }

                 }
            }
            
            //alert(code);
           window.returnValue = code;
        }
        else{
           Ext.MessageBox.alert("提示", "请选择设备信息！");
           return;
        }
        window.close();
/*
        var selectedNodes = Ext.getCmp("tree").getChecked();
            var channelCode = "";
            var names = "";
//            Ext.each(selectedNodes, function(node) {
//                ids += "," + getIdElement(node.id);
//                names += "," + node.text;
//            });
            var divObj = document.getElementById("selectedDiv");
            var a = divObj.innerHTML;
            var select = a.split("<br>");
            for(var i = 1; i < select.length-1; i++){
                channelCode += "," + getIdElement(select[i]);
                //names += "," + select[i].substring(select[i].indexOf("span>")+5,select[i].indexOf("<INPUT"));
            }
            if (channelCode != "")
            {
                window.returnValue = "<id>" + channelCode.substring(1, channelCode.length) + "</id>";
            } else
            {
                Ext.MessageBox.alert("提示", "请选择设备信息！");
                return;
            }
            window.close();*/
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