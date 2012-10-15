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
            window.location = "work-arrange!week?day=" + value;
        }
    </script>
</head>
<body>
<table width="98%" border="0" align="center" cellpadding="0" cellspacing="0" class="table_line">
    <tr>
        <td colspan="4" class="line_td_title" align="center">全室一周工作安排</td>
    </tr>
    <form action="work-arrange!input?year=${nyear}&week=${nweek}" method="post">
    <tr>
        <td colspan="4" class="line_td_search" align="center">
                <a href="work-arrange!week?nweek=${beforeweek}&nyear=${beforeyear}">←${beforeyear}年&nbsp;第${beforeweek}周&nbsp;</a>
            日期：
            <input name="day" id="day" class="input_one2" type="text" value="${day}"/>&nbsp;
            <img src="${themesPath}/oldimages/calendar.gif"  width="13" height="12" onClick="calendar(day,'date');" style="cursor:pointer">
            <input type="button" name="search" id="search" value="搜索" class="search_but" onclick="changeday();"/>
            <a href="work-arrange!week?nweek=${afterweek}&nyear=${afteryear}">&nbsp;${afteryear}年&nbsp;第${afterweek}周→</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            <input type="submit" name="add" id="add" value="添加" class="confirm_but"/>
        </td>
    </tr>
    <tr>
        <td class="line_td_head"></td>
        <td class="line_td_head">开始时间</td>
        <td class="line_td_head">结束时间</td>
        <%--<td class="line_td_head">持续时间</td>--%>
        <td class="line_td_head">工作内容</td>
    </tr>
    <c:forEach items="${arranges}" var="arrange" varStatus="status">
        <tr>
            <input type="hidden" name="arrid" value="${arrange.id}"/>
            <td class="line_td_light" width="20%">&nbsp;&nbsp;&nbsp;&nbsp;
            <c:if test='${arrange.dow == 1}'>星期日</c:if>
            <c:if test='${arrange.dow == 2}'>星期一</c:if>
            <c:if test='${arrange.dow == 3}'>星期二</c:if>
            <c:if test='${arrange.dow == 4}'>星期三</c:if>
            <c:if test='${arrange.dow == 5}'>星期四</c:if>
            <c:if test='${arrange.dow == 6}'>星期五</c:if>
            <c:if test='${arrange.dow == 7}'>星期六</c:if>
            </td>
            <td class="line_td_light" width="20%">&nbsp;&nbsp;&nbsp;&nbsp;${arrange.starttime}</td>
            <td class="line_td_light" width="20%">&nbsp;&nbsp;&nbsp;&nbsp;${arrange.endtime}</td>
            <%--<td class="line_td_light" width="20%">&nbsp;&nbsp;&nbsp;&nbsp;${arrange.period}</td>--%>
            <td class="line_td_light" width="40%">&nbsp;&nbsp;&nbsp;&nbsp;${arrange.content}
            </td>
        </tr>
    </c:forEach>
    </form>
</table>
</body>
</html>