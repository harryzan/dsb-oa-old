<%
    response.setHeader("Pragma","No-cache");        //HTTP     1.1
    response.setHeader("Cache-Control","no-cache");//HTTP     1.0
    response.setHeader("Expires","0");               //防止被proxy
%>
<script type="text/javascript">
    var CONTEXT_NAME = "${ctx}";
</script>
<%--<script type="text/javascript" src="${scriptsPath}/jquery/jquery.layout.js"></script>--%>