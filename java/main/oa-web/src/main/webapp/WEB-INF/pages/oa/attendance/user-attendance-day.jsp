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
</head>
<body>
<table width="98%" border="0" align="center" cellpadding="0" cellspacing="0" class="table_line">
    <tr>
        <td colspan="3" class="line_td_title" align="center">个人考勤</td>
    </tr>
    <tr>
        <td colspan="3" class="line_td_search" align="center">
            日期：
            <input name="changetime" class="input_one2" type="text" value="${day}"/>&nbsp;
            <img src="${themesPath}/oldimages/calendar.gif"  width="13" height="12" onClick="calendar(changetime,'date');" style="cursor:pointer">
            <input type="submit" name="button" id="button" value="搜索" class="search_but" />
        </td>
    </tr>
    <tr>
        <td class="line_td_head">001002</td>
        <td class="line_td_head">发文字</td>
        <td class="line_td_head">发文</td>
    </tr>
    <tr>
        <td class="line_td_dark">&nbsp;</td>
        <td class="line_td_dark">&nbsp;</td>
        <td class="line_td_dark">&nbsp;</td>
    </tr>
    <tr>
        <td class="line_td_light">001002</td>
        <td class="line_td_light">发文字</td>
        <td class="line_td_light">发文</td>
    </tr>
</table>
</body>
</html>