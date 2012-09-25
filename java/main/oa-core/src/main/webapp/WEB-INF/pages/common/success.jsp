<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<html>
<head>
    <title>系统提示</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <%@ include file="/common/meta.jsp" %>
    <script language="javascript">
        function doReturn() {
            var nextUrl = "${msg.url}";
            var isRefreshOpener = ${msg.refreshOpener};
            var isNeedClose = ${msg.needClose};
            var gridParam = '${gridParam}';
            var loadOpener =${msg.loadOpener} ;
            var returnValue = "${msg.returnValue}";
            var customEvent = "${msg.customEvent}";
            if (isRefreshOpener) {
                opener.window.location.reload();
            }
            if(customEvent != ""){
                eval(customEvent);
            }
            if (isNeedClose) {
                window.close();
            }
            if (nextUrl != "") {
                if (loadOpener) {
                    parent.window.returnValue = returnValue;
                    if (returnValue.indexOf("window") >= 0) {
                        eval(returnValue);
                    }
                    window.close();
                } else if (nextUrl.indexOf("?") >= 0) {
                    window.location = nextUrl + "&gridParam=" + gridParam;
                } else {
                    window.location = nextUrl + "?gridParam=" + gridParam;
                }
            }
        }
        function init() {
            var isRefreshTree = ${msg.refreshTree};
            var isRefreshTreeChildren = ${msg.refreshTreeChildren};
            if (!isRefreshTree) return;

            //            alert(parent.tree_frame.lastSelectedNode.parentNode.reload())
            if (parent.tree_frame) {
                if (parent.tree_frame.lastSelectedNode) {
                    if (isRefreshTreeChildren) {
                        parent.tree_frame.updateChildren(parent.tree_frame.lastSelectedNode);
                    } else {
                        parent.tree_frame.updateParent(parent.tree_frame.lastSelectedNode);
                    }
                }
            }
        }

    </script>
</head>

<body onload="init()">
<br>
<table width="450" height="300" border="0" align="center" cellpadding="0" cellspacing="0">
    <tr>
        <td background="${themesPath}/system/common/success.jpg">
            <table width="330" height="230" border="0" align="center" cellpadding="0" cellspacing="0">
                <tr>
                    <td colspan="2" height="40"></td>
                </tr>
                <tr>
                    <td width="140">&nbsp;</td>
                    <td height="130" style="word-break:break-all" class="msg_hints">
                        ${msg.msg}&nbsp;
                    </td>
                </tr>
                <c:if test="${msg.url != null && msg.url != ''}">
                    <tr>
                        <td height="30" align="center">&nbsp; </td>
                        <td>
                            <input type="button" value="确定" onClick="doReturn();" class="button_confirm">
                        </td>
                    </tr>
                </c:if>
                <tr>
                    <td colspan="2" height="20"></td>
                </tr>
            </table>
        </td>
    </tr>
</table>
</body>
<script>
    var isAutoJump = ${msg.autoJump};
    if (isAutoJump) {
        //5秒后自动跳转
        setTimeout("doReturn()", 5000);
    }
</script>
</html>