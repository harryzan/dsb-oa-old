<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%
    response.setHeader("Pragma", "No-cache");        //HTTP     1.1
    response.setHeader("Cache-Control", "no-cache");//HTTP     1.0
    response.setHeader("Expires", "0");               //防止被proxy
%>
<link rel="stylesheet" type="text/css" href="${themesPath}/ext/treegrid.css"/>
<link rel="stylesheet" type="text/css" href="${themesPath}/ext/ext-all.css"/>
<link rel="stylesheet" type="text/css" href="${themesPath}/css/style.css"/>

<script type="text/javascript" src="${scriptsPath}/ext/ext-base-3.js"></script>
<script type="text/javascript" src="${scriptsPath}/ext/ext-all-3.js"></script>

<script type="text/javascript" >
    var iconPath = "${themesPath}/images/icons/";
    var blank_gif = iconPath+"s.gif";     //modify by chenjp 修改ext-base缺省空白gif，避免指向http://extjs.com/s.gif
</script>

<script type="text/javascript" src="${scriptsPath}/ext/treegrid/TreeGridSorter.js"></script>
<script type="text/javascript" src="${scriptsPath}/ext/treegrid/TreeGridColumnResizer.js"></script>
<script type="text/javascript" src="${scriptsPath}/ext/treegrid/TreeGridNodeUI.js"></script>
<script type="text/javascript" src="${scriptsPath}/ext/treegrid/TreeGridLoader.js"></script>
<script type="text/javascript" src="${scriptsPath}/ext/treegrid/TreeGridColumns.js"></script>
<script type="text/javascript" src="${scriptsPath}/ext/treegrid/TreeGrid.js"></script>
<script type="text/javascript" src="${scriptsPath}/ext/treegrid/MyTreeGrid.js"></script>
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
</script>

<%--<script type="text/javascript" src="${scriptsPath}/jquery/jquery.layout.js"></script>--%>