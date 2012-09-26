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
<title>选择文档目录</title>
<link href="${themesPath}/oldcss/style.css" rel="stylesheet" type="text/css">
</head>

<body leftmargin="0" topmargin="0" marginwidth="0" marginheight="0">
<iframe frameborder="0" framespacing="0" width="100%" height="100%" src="doc-client-category!choice?modelname=${modelname}&documentid=${documentid}"></iframe>
<script type="text/javascript">
    function OK(value){
        if(value){
            window.returnValue = value;
        }
        self.close();
    }
</script>
</body>
</html>
