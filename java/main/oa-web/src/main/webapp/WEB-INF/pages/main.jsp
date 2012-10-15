<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" autoFlush="true" %>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>无标题文档</title>
    <link href="${themesPath}/css/style.css" rel="stylesheet" type="text/css" />
    <style type="text/css">
        <!--
        .STYLE1 {color: #FF0000}
        -->
    </style>
</head>

<body bgcolor="#FFFFFF">
<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="main_title">
    <tr>
        <td width="3%" nowrap="nowrap">&nbsp;&nbsp;<img src="${themesPath}/images/main_2.jpg" width="19" height="20" /></td>
        <td width="97%">欢迎您，${user.displayname}</td>
    </tr>
</table>
<br />
<table width="98%" border="0" align="center">
<tr>
<td width="82%"><table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
    <tr>
        <td width="10%" background="${themesPath}/images/sy_4.jpg"><img src="${themesPath}/images/sy_1.jpg" width="150" height="33" /></td>
        <td width="90%" background="${themesPath}/images/sy_4.jpg">&nbsp;</td>
    </tr>
    <tr>
        <td colspan="2"><div class="sy_line">
            <table width="100%" border="0" cellpadding="0" cellspacing="0">
                <tr>
                    <td width="14%" align="center" class="sy_text_1">星期一</td>
                    <td width="86%"><table width="99%" border="0" align="center" cellpadding="0" cellspacing="0">
                        <c:forEach items="${monarranges}" var="arrange" varStatus="status">
                            <tr>
                                <td><a href="#">${fn:substring(arrange.starttime, 11, 16)}&nbsp;&nbsp;${arrange.content}</a></td>
                            </tr>
                        </c:forEach>
                    </table></td>
                </tr>
                <tr>
                    <td height="1" colspan="2" bgcolor="#dce6ef"></td>
                </tr>
                <tr>
                    <td align="center" class="sy_text_1">星期二</td>
                    <td><table width="99%" border="0" align="center" cellpadding="0" cellspacing="0">
                        <c:forEach items="${tusarranges}" var="arrange" varStatus="status">
                            <tr>
                                <td><a href="#">${fn:substring(arrange.starttime, 11, 16)}&nbsp;&nbsp;${arrange.content}</a></td>
                            </tr>
                        </c:forEach>
                    </table></td>
                </tr>
                <tr>
                    <td height="1" colspan="2" bgcolor="#dce6ef"></td>
                </tr>
                <tr>
                    <td align="center" class="sy_text_1">星期三</td>
                    <td><table width="99%" border="0" align="center" cellpadding="0" cellspacing="0">
                        <c:forEach items="${wedarranges}" var="arrange" varStatus="status">
                            <tr>
                                <td><a href="#">${fn:substring(arrange.starttime, 11, 16)}&nbsp;&nbsp;${arrange.content}</a></td>
                            </tr>
                        </c:forEach>
                    </table></td>
                </tr>
                <tr>
                    <td height="1" colspan="2" bgcolor="#dce6ef"></td>
                </tr>
                <tr>
                    <td align="center" class="sy_text_1">星期四</td>
                    <td><table width="99%" border="0" align="center" cellpadding="0" cellspacing="0">
                        <c:forEach items="${thearranges}" var="arrange" varStatus="status">
                            <tr>
                                <td><a href="#">${fn:substring(arrange.starttime, 11, 16)}&nbsp;&nbsp;${arrange.content}</a></td>
                            </tr>
                        </c:forEach>
                    </table></td>
                </tr>
                <tr>
                    <td height="1" colspan="2" bgcolor="#dce6ef"></td>
                </tr>
                <tr>
                    <td align="center" class="sy_text_1">星期五</td>
                    <td><table width="99%" border="0" align="center" cellpadding="0" cellspacing="0">
                        <c:forEach items="${friarranges}" var="arrange" varStatus="status">
                            <tr>
                                <td><a href="#">${fn:substring(arrange.starttime, 11, 16)}&nbsp;&nbsp;${arrange.content}</a></td>
                            </tr>
                        </c:forEach>
                    </table></td>
                </tr>
                <tr>
                    <td height="1" colspan="2" bgcolor="#dce6ef"></td>
                </tr>
                <tr>
                    <td align="center" class="sy_text_1">星期六</td>
                    <td><table width="99%" border="0" align="center" cellpadding="0" cellspacing="0">
                        <c:forEach items="${satarranges}" var="arrange" varStatus="status">
                            <tr>
                                <td><a href="#">${fn:substring(arrange.starttime, 11, 16)}&nbsp;&nbsp;${arrange.content}</a></td>
                            </tr>
                        </c:forEach>
                    </table></td>
                </tr>
                <tr>
                    <td height="1" colspan="2"  bgcolor="#dce6ef"></td>
                </tr>
                <tr>
                    <td align="center" class="sy_text_1">星期日</td>
                    <td><table width="99%" border="0" align="center" cellpadding="0" cellspacing="0">
                        <c:forEach items="${sunarranges}" var="arrange" varStatus="status">
                            <tr>
                                <td><a href="#">${fn:substring(arrange.starttime, 11, 16)}&nbsp;&nbsp;${arrange.content}</a></td>
                            </tr>
                        </c:forEach>
                    </table></td>
                </tr>
            </table>
        </div>
            <table width="100%" border="0" cellpadding="0" cellspacing="0">
                <tr>
                    <td background="${themesPath}/images/sy_jiao2.jpg"><img src="${themesPath}/images/sy_jiao1.jpg" width="7" height="7" /></td>
                    <td background="${themesPath}/images/sy_jiao2.jpg"></td>
                    <td background="${themesPath}/images/sy_jiao2.jpg" align="right"><img src="${themesPath}/images/sy_jiao3.jpg" width="7" height="7" /></td>
                </tr>
            </table></td>
    </tr>
</table>
    <br />
    <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
            <td width="10%" background="${themesPath}/images/sy_5.jpg"><img src="${themesPath}/images/sy_2.jpg" width="150" height="33" /></td>
            <td width="90%" background="${themesPath}/images/sy_5.jpg">&nbsp;</td>
        </tr>
        <tr>
            <td colspan="2"><div class="sy_line">
                <table width="100%" border="0">
                    <tr>
                        <td><table width="90%" border="0" align="center">
                            <tr>
                                <td>值班人员：姚吉安<br />
                                    最新公告：大风警报大风警报大风警报大风警报大风警报大风警报大风警报大<br />
                                    大风警报大风警报大风警报大风警报大风警报大风警报大风警报大<br />
                                    大风警报大风警报大风警报大风警报大风警报大风警报<br />
                                    催办事务：事务事务事务事务事务事务事务事务事务事务事务事务事务事</td>
                            </tr>
                        </table></td>
                    </tr>
                </table>
            </div>
                <table width="100%" border="0" cellpadding="0" cellspacing="0">
                    <tr>
                        <td background="${themesPath}/images/sy_jiao2.jpg"><img src="${themesPath}/images/sy_jiao1.jpg" width="7" height="7" /></td>
                        <td background="${themesPath}/images/sy_jiao2.jpg"></td>
                        <td background="${themesPath}/images/sy_jiao2.jpg" align="right"><img src="${themesPath}/images/sy_jiao3.jpg" width="7" height="7" /></td>
                    </tr>
                </table></td>
        </tr>
    </table>
    <br />
    <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
            <td width="10%" background="${themesPath}/images/sy_6.jpg"><img src="${themesPath}/images/sy_3.jpg" width="150" height="33" /></td>
            <td width="100%" background="${themesPath}/images/sy_6.jpg">&nbsp;</td>
        </tr>
        <tr>
            <td colspan="2"><div class="sy_line">
                <table width="100%" border="0" cellpadding="0" cellspacing="0">
                    <tr>
                        <td height="80"><table width="80%" border="0" align="center">
                            <tr>
                                <td><table width="160" border="0">
                                    <tr>
                                        <td><img src="${themesPath}/images/sy_icon_1.jpg" width="23" height="25" /><a href="#"></a></td>
                                        <td><a href="#">收文<span class="STYLE1">（1）</span></a></td>
                                    </tr>
                                </table></td>
                                <td><table width="160" border="0">
                                    <tr>
                                        <td><img src="${themesPath}/images/sy_icon_2.jpg" width="41" height="22" /><a href="#"></a></td>
                                        <td><a href="#">用车提醒<span class="STYLE1">（1）</span></a></td>
                                    </tr>
                                </table></td>
                                <td><table width="160" border="0">
                                    <tr>
                                        <td><img src="${themesPath}/images/sy_icon_3.jpg" width="31" height="24" /><a href="#"></a></td>
                                        <td><a href="#">会议通知<span class="STYLE1">（3）</span></a></td>
                                    </tr>
                                </table></td>
                                <td><table width="160" border="0">
                                    <tr>
                                        <td><img src="${themesPath}/images/sy_icon_4.jpg" width="27" height="28" /><a href="#"></a></td>
                                        <td><a href="#">代办事项<span class="STYLE1">（3）</span></a></td>
                                    </tr>
                                </table></td>
                            </tr>
                        </table></td>
                    </tr>
                </table>
            </div>
                <table width="100%" border="0" cellpadding="0" cellspacing="0">
                    <tr>
                        <td background="${themesPath}/images/sy_jiao2.jpg"><img src="${themesPath}/images/sy_jiao1.jpg" width="7" height="7" /></td>
                        <td background="${themesPath}/images/sy_jiao2.jpg"></td>
                        <td background="${themesPath}/images/sy_jiao2.jpg" align="right"><img src="${themesPath}/images/sy_jiao3.jpg" width="7" height="7" /></td>
                    </tr>
                </table></td>
        </tr>
    </table></td>
<td width="280" valign="top" class="sy_right_bg"><table width="280" border="0" cellpadding="0" cellspacing="0">
<tr>
    <td height="30">&nbsp;</td>
</tr>
<tr>
<td><table align="center" cellpadding="0" cellspacing="0" bgcolor="#FFFFFF" id="1">
    <tr>
        <td><style>
            body,td,.p1,.p2,.i{font-family:arial}
            body{margin:6px 0 0 0;background-color:#fff;color:#000;}
            table{border:0}
            #cal{width:260px;border:1px solid #c3d9ff;font-size:12px;margin:0px 0 0 0px}
            #cal #top{height:29px;line-height:29px;background:#e7eef8;color:#003784;padding-left:5px}
            #cal #top select{font-size:12px}
            #cal #top input{padding:0}
            #cal ul#wk{margin:0;padding:0;height:25px}
            #cal ul#wk li{float:left;width:36px;text-align:center;line-height:25px;list-style:none}
            #cal ul#wk li b{font-weight:normal;color:#c60b02}
            #cal #cm{clear:left;border-top:1px solid #ddd;border-bottom:1px dotted #ddd;position:relative}
            #cal #cm .cell{position:absolute;width:30px;height:36px;text-align:center;margin:0 0 0 9px}
            #cal #cm .cell .so{font:bold 12px arial;}
            #cal #bm{text-align:right;height:24px;line-height:24px;padding:0 13px 0 0}
            #cal #bm a{color:7977ce}
            #cal #fd{display:none;position:absolute;border:1px solid #dddddf;background:#feffcd;padding:10px;line-height:21px;width:150px}
            #cal #fd b{font-weight:normal;color:#c60a00}
        </style>
            <!--[if IE]>
            <style>
                #cal #top{padding-top:4px}
                #cal #top input{width:65px}
                #cal #fd{width:170px}
            </style>
            <![endif]-->
            <div id="cal">
                <div id="top">公元
                    <select>
                    </select>
                    年
                    <select>
                    </select>
                    月  农历<span></span>年 [ <span></span>年 ]
                    <input type="button" value="回到今天" title="点击后跳转回今天" style="padding:0px">
                </div>
                <ul id="wk">
                    <li>一</li>
                    <li>二</li>
                    <li>三</li>
                    <li>四</li>
                    <li>五</li>
                    <li><b>六</b></li>
                    <li><b>日</b></li>
                </ul>
                <div id="cm"></div>
                <div id="bm"></div>
            </div></td>
    </tr>
</table>
    <script type="text/javascript" src="${scriptsPath}/system/main.js"></script>
</td>
</tr>
</table></td>
</tr>
</table>
</body>
</html>
