<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<html>
<head>
    <title>系统提示</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <%@ include file="/common/meta.jsp" %>
    <link href="${themesPath}/css/style.css" rel="stylesheet" type="text/css">
    <script language="javascript">
        try{
        if (parent.tree_frame.childFrame != null)
            parent.tree_frame.childFrame.updateParent(parent.tree_frame.childFrame.lastSelectedNode);
        else if (parent.tree_frame != null){
            parent.tree_frame.updateParent(parent.tree_frame.lastSelectedNode);
        }
        }catch(E){}
    </script>
</head>
</html>