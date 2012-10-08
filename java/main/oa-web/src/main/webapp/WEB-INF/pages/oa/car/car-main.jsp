<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>

<html>
<head>
    <title>考勤管理</title>
    <script type="text/javascript">
        var gridParams = new Array();
    </script>
</head>
<frameset rows="38,*" name="parentFrame" border="0" framespacing="1" bordercolor="white" height="auto" frameborder="0">
    <frame name="tab_frame" scrolling="no" src="car-tab"/>
    <frame name="main_frame" scrolling="auto" src="${ctx}/common/blank"/>
</frameset>
</html>