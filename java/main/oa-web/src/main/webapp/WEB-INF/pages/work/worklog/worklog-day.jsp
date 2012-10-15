<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
        "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title></title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <link href="${themesPath}/css/style.css" rel="stylesheet" type="text/css" />
    <link href="${themesPath}/oldcss/style.css" rel="stylesheet" type="text/css">
    <script type="text/javascript" src="${scriptsPath}/system/calendar.js"></script>
    <script type="text/javascript" src="${scriptsPath}/system/function.js"></script>
    <script type="text/javascript">
        function changeday() {
            var value = document.getElementById("day").value;
            window.location = "worklog!day?day=" + value;
        }
    </script>
</head>
<body>
<table width="98%" border="0" align="center" cellpadding="0" cellspacing="0" class="table_line">
    <tr>
        <td colspan="4" class="line_td_title" align="center">工作日志</td>
    </tr>
    <form action="worklog!input" method="post">
    <tr>
        <td colspan="4" class="line_td_search" align="center">
                <a href="worklog!day?day=${beforeday}">←${beforeday}</a>
            日期：
            <input name="day" id="day" class="input_one2" type="text" value="${day}"/>&nbsp;
            <img src="${themesPath}/oldimages/calendar.gif"  width="13" height="12" onClick="calendar(day,'date');" style="cursor:pointer">
            <input type="button" name="search" id="search" value="搜索" class="search_but" onclick="changeday();"/>
            <a href="worklog!day?day=${afterday}">${afterday}→</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            <input type="submit" name="add" id="add" value="添加" class="confirm_but"/>
        </td>
    </tr>
    <tr>
        <td class="line_td_head">开始时间</td>
        <td class="line_td_head">结束时间</td>
        <td class="line_td_head">持续时间</td>
        <td class="line_td_head">工作内容</td>
    </tr>
    <c:forEach items="${worklogs}" var="worklog" varStatus="status">
        <tr>
            <input type="hidden" name="attid" value="${attendance.id}"/>
            <td class="line_td_light" width="20%">&nbsp;&nbsp;&nbsp;&nbsp;${worklog.starttime}</td>
            <td class="line_td_light" width="20%">&nbsp;&nbsp;&nbsp;&nbsp;${worklog.endtime}</td>
            <td class="line_td_light" width="20%">&nbsp;&nbsp;&nbsp;&nbsp;${worklog.period}</td>
            <td class="line_td_light" width="40%">&nbsp;&nbsp;&nbsp;&nbsp;${worklog.content}
            </td>
        </tr>
    </c:forEach>
    </form>
</table>
</body>
</html>