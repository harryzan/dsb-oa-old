<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%
    response.setHeader("Pragma","No-cache");        //HTTP     1.1
    response.setHeader("Cache-Control","no-cache");//HTTP     1.0
    response.setHeader("Expires","0");               //防止被proxy
%>
<link rel="stylesheet" type="text/css" href="${themesPath}/ext/ext-all.css"/>
<link rel="stylesheet" type="text/css" href="${themesPath}/css/style.css"/>
<script type="text/javascript" >
    var blank_gif = "${themesPath}/images/icons/s.gif";     //modify by chenjp 修改ext-base缺省空白gif，避免指向http://extjs.com/s.gif
</script>
<script type="text/javascript" src="${scriptsPath}/ext/ext-base.js"></script>
<script type="text/javascript" src="${scriptsPath}/ext/ext-all.js"></script>
<script type="text/javascript" src="${scriptsPath}/ext/grid.js"></script>
<script type="text/javascript">
    function regist() {
        Ext.QuickTips.init();

        // Apply a set of config properties to the singleton
        Ext.apply(Ext.QuickTips.getQuickTip(), {
            maxWidth: 200,
            minWidth: 100,
            showDelay: 50,
            trackMouse: false
        });
    }

    function healthPosting(msg){
        if(!msg || msg == ""){
            msg = "正在提交保存...";
        }
        Ext.MessageBox.wait(msg, "请稍等<img src=\"${themesPath}/images/loading.gif\">");
    }

    function healthBacking(msg){
        if(!msg || msg == ""){
            msg = "正在返回上一层...";
        }
        Ext.MessageBox.wait(msg, "请稍等<img src=\"${themesPath}/images/loading.gif\">");
    }

    function healthWaiting(msg){
        if(!msg || msg == ""){
            msg = "正在处理，请稍等...<img src=\"${themesPath}/images/loading.gif\">";
        }
        Ext.MessageBox.wait(msg, "提示");
    }

    window.onbeforeunload = function(){
        healthWaiting(false);
    };

    function closeWaiting(){
        Ext.MessageBox.hide();
    }
</script>
<%--<script type="text/javascript" src="${scriptsPath}/jquery/jquery.layout.js"></script>--%>