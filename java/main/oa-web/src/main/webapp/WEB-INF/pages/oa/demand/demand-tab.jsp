<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
        "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title></title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <link href="${themesPath}/css/style.css" rel="stylesheet" type="text/css" />
    <script type="text/javascript">
        function openFunction(url) {
            window.parent.frames.main_frame.location = url;
        }
    </script>
</head>
<body>
<table width="98%" border="0" align="center" cellpadding="0" cellspacing="0">
    <tr>
        <td>
            <input onclick="openFunction('demand-use-grid')" type="button" name="button2" id="button2" value="需求申请" class="tab" />
            <input onclick="openFunction('demand-check-grid')" type="button" name="button3" id="button3" value="申请审核" class="tab" />
            <input onclick="openFunction('demand-complete-grid')" type="button" name="button4" id="button4" value="历史审核" class="tab" />
        </td>
    </tr>
</table>
</body>
</html>