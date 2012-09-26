<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp" %><!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>办公管理信息系统</title>
    <link href="${themesPath}/css/style.css" rel="stylesheet" type="text/css" />

    <script type="text/javascript">
        function openFunction(url) {
            window.parent.frames.mainFrame.location = url;
        }
    </script>
</head>

<body bgcolor="#e8eef8">
<table width="231" border="0" cellpadding="0" cellspacing="0">
    <tr>
        <td><img src="${themesPath}/images/left_1.jpg" width="231" height="31" /></td>
    </tr>
    <tr>
        <td></td>
    </tr>
    <tr>
        <td><table width="231" border="0" cellpadding="0" cellspacing="0">
            <tr>
                <td class="Left_cd">系统管理</td>
            </tr>
            <tr>
                <td align="center"><table width="200" border="0" cellpadding="0" cellspacing="0">
                    <tr>
                        <td width="19"><img src="${themesPath}/images/left_3.png" width="5" height="9" /></td>
                        <td width="165" class="Left_cd1"><a href="#" onclick="openFunction('${ctx}/system/sysdept/sys-dept-main')" class="cd">单位部门管理</a></td>
                    </tr>
                    <tr>
                        <td><img src="${themesPath}/images/left_3.png" width="5" height="9" /></td>
                        <td class="Left_cd1"><a href="#" onclick="openFunction('${ctx}/system/sysuser/sys-user-main')" class="cd">人事管理</a></td>
                    </tr>
                    <tr>
                        <td><img src="${themesPath}/images/left_3.png" alt="" width="5" height="9" /></td>
                        <td class="Left_cd1"><a href="#" onclick="openFunction('${ctx}/system/sysrole/sys-role-main')" class="cd">角色管理</a></td>
                    </tr>
                    <tr>
                        <td><img src="${themesPath}/images/left_3.png" alt="" width="5" height="9" /></td>
                        <td class="Left_cd1"><a href="#" onclick="openFunction('${ctx}/system/sysprivilege/sys-privilege-main')" class="cd">权限管理</a></td>
                    </tr>
                    <tr>
                        <td><img src="${themesPath}/images/left_3.png" alt="" width="5" height="9" /></td>
                        <td class="Left_cd1"><a href="#" onclick="openFunction('${ctx}/system/syscode/sys-code-main')" class="cd">系统代码</a></td>
                    </tr>
                    <tr>
                        <td><img src="${themesPath}/images/left_3.png" alt="" width="5" height="9" /></td>
                        <td class="Left_cd1"><a href="#" onclick="openFunction('${ctx}/system/syslog/sys-log-grid')" class="cd">系统日志</a></td>
                    </tr>
                </table></td>
            </tr>
        </table></td>
    </tr>
    <tr>
        <td><table width="231" border="0" cellpadding="0" cellspacing="0">
            <tr>
                <td class="Left_cd">行政办公管理</td>
            </tr>
            <tr>
                <td align="center"><table width="200" border="0" cellpadding="0" cellspacing="0">
                    <tr>
                        <td width="19"><img src="${themesPath}/images/left_3.png" width="5" height="9" /></td>
                        <td width="165" class="Left_cd1"><a href="*" class="cd">考勤管理</a></td>
                    </tr>
                    <tr>
                        <td><img src="${themesPath}/images/left_3.png" width="5" height="9" /></td>
                        <td class="Left_cd1"><a href="*" class="cd">车辆管理</a></td>
                    </tr>
                    <tr>
                        <td><img src="${themesPath}/images/left_3.png" alt="" width="5" height="9" /></td>
                        <td class="Left_cd1"><a href="*" class="cd">日程领用管理</a></td>
                    </tr>
                    <tr>
                        <td><img src="${themesPath}/images/left_3.png" alt="" width="5" height="9" /></td>
                        <td class="Left_cd1"><a href="*" class="cd">需求申请</a></td>
                    </tr>
                </table></td>
            </tr>
        </table></td>
    </tr>
    <tr>
        <td><table width="231" border="0" cellpadding="0" cellspacing="0">
            <tr>
                <td class="Left_cd">信息管理</td>
            </tr>
            <tr>
                <td align="center"><table width="200" border="0" cellpadding="0" cellspacing="0">
                    <tr>
                        <td width="19"><img src="${themesPath}/images/left_3.png" width="5" height="9" /></td>
                        <td width="165" class="Left_cd1"><a href="*" class="cd">全室一周工作安排</a></td>
                    </tr>
                    <tr>
                        <td><img src="${themesPath}/images/left_3.png" width="5" height="9" /></td>
                        <td class="Left_cd1"><a href="*" class="cd">通知公告管理</a></td>
                    </tr>

                </table></td>
            </tr>
        </table></td>
    </tr>
    <tr>
        <td><table width="231" border="0" cellpadding="0" cellspacing="0">
            <tr>
                <td class="Left_cd">个人工作管理</td>
            </tr>
            <tr>
                <td align="center"><table width="200" border="0" cellpadding="0" cellspacing="0">
                    <tr>
                        <td width="19"><img src="${themesPath}/images/left_3.png" width="5" height="9" /></td>
                        <td width="165" class="Left_cd1"><a href="*" class="cd">日历管理</a></td>
                    </tr>
                    <tr>
                        <td><img src="${themesPath}/images/left_3.png" width="5" height="9" /></td>
                        <td class="Left_cd1"><a href="*" class="cd">个人文件夹</a></td>
                    </tr>
                    <tr>
                        <td><img src="${themesPath}/images/left_3.png" alt="" width="5" height="9" /></td>
                        <td class="Left_cd1"><a href="*" class="cd">工作日志</a></td>
                    </tr>
                    <tr>
                        <td><img src="${themesPath}/images/left_3.png" alt="" width="5" height="9" /></td>
                        <td class="Left_cd1"><a href="*" class="cd">OA小精灵</a></td>
                    </tr>
                </table></td>
            </tr>
        </table>
            <table width="231" border="0" cellpadding="0" cellspacing="0">
                <tr>
                    <td class="Left_cd">文书管理</td>
                </tr>
                <tr>
                    <td align="center"><table width="200" border="0" cellpadding="0" cellspacing="0">
                        <tr>
                            <td width="19"><img src="${themesPath}/images/left_3.png" width="5" height="9" /></td>
                            <td width="165" class="Left_cd1"><a href="*" class="cd">办文管理</a></td>
                        </tr>
                        <tr>
                            <td><img src="${themesPath}/images/left_3.png" width="5" height="9" /></td>
                            <td class="Left_cd1"><a href="*" class="cd">发文管理</a></td>
                        </tr>
                        <tr>
                            <td><img src="${themesPath}/images/left_3.png" alt="" width="5" height="9" /></td>
                            <td class="Left_cd1"><a href="*" class="cd">内部文书</a></td>
                        </tr>

                    </table></td>
                </tr>
            </table>
            <table width="231" border="0" cellpadding="0" cellspacing="0">
                <tr>
                    <td class="Left_cd">规章制度</td>
                </tr>
                <tr>
                    <td align="center"><table width="200" border="0" cellpadding="0" cellspacing="0">
                        <tr>
                            <td width="19"><img src="${themesPath}/images/left_3.png" width="5" height="9" /></td>
                            <td width="165" class="Left_cd1"><a href="*" class="cd">规章制度</a></td>
                        </tr>
                        <tr>
                            <td><img src="${themesPath}/images/left_3.png" width="5" height="9" /></td>
                            <td class="Left_cd1"><a href="*" class="cd">内部通讯录</a></td>
                        </tr>
                        <tr>
                            <td><img src="${themesPath}/images/left_3.png" alt="" width="5" height="9" /></td>
                            <td class="Left_cd1"><a href="*" class="cd">表格下载</a></td>
                        </tr>
                    </table></td>
                </tr>
            </table></td>
    </tr>
    <tr>
        <td><table width="231" border="0" cellpadding="0" cellspacing="0">
            <tr>
                <td class="Left_cd">视频点播</td>
            </tr>
            <tr>
                <td align="center"><table width="200" border="0" cellpadding="0" cellspacing="0">
                    <tr>
                        <td width="19"><img src="${themesPath}/images/left_3.png" width="5" height="9" /></td>
                        <td width="165" class="Left_cd1"><a href="*" class="cd">视频点播</a></td>
                    </tr>

                </table></td>
            </tr>
        </table></td>
    </tr>
</table>
</body>
</html>
