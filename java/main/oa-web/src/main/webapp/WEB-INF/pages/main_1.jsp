<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<link href="${themesPath}/css/style.css" rel="stylesheet" type="text/css" />
<style type="text/css">  
.shadow{
	border:1px solid #bec6d3;
	background:#fff;
	font-size:12px;
	padding:10px;
	-moz-box-shadow:2px 2px 4px #d7e2ee;
	-webkit-box-shadow:2px 2px 4px #d7e2ee;
	box-shadow:2px 2px 4px #d7e2ee;
    *filter: progid:DXImageTransform.Microsoft.Shadow(Strength=4, Direction=135, Color="#d7e2ee");  
	height: 160px;
	width: 75%;
	text-align: center;

}  
.shadow .main{
	border:1px solid #bec6d3;
	background:#fff;
	font-size:12px;
	-moz-box-shadow:2px 2px 4px #d7e2ee;
	-webkit-box-shadow:2px 2px 4px #d7e2ee;
	box-shadow:2px 2px 4px #d7e2ee;
    *filter: progid:DXImageTransform.Microsoft.Shadow(Strength=4, Direction=135, Color="#d7e2ee");  
	height: 88px;
	width: 118px;
    margin-left: auto;
    margin-right: auto;

} 
.shadow .txt{
	padding-top: 40px;
	font-size: 12px;
	color: #3d4e6a;
} 

</style>  
</head>

<body>
<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="main_title">
  <tr>
    <td width="3%" nowrap="nowrap">&nbsp;&nbsp;<img src="${themesPath}/images/main_2.jpg" width="19" height="20" /></td>
    <td width="97%">欢迎您，admin</td>
  </tr>
</table>
<br />
<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td><table width="98%" border="0" align="center" cellpadding="0" cellspacing="0">
      <tr>
        <td><input type="submit" name="button8" id="button8" value="提交" class="tab_xz" />
          <input type="submit" name="button9" id="button9" value="提交" class="tab" /></td>
      </tr>
    </table></td>
  </tr>
  <tr>
    <td class="line_hx"></td>
  </tr>
  <tr>
    <td>&nbsp;</td>
  </tr>
</table>
<table width="98%" border="0" align="center" cellpadding="0" cellspacing="0" class="table_line">
  <tr>
    <td colspan="3" class="line_td_title">序号发文字号状态</td>
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
  <tr>
    <td class="line_td_dark">001002</td>
    <td class="line_td_dark">发文</td>
    <td class="line_td_dark">发文</td>
  </tr>
</table>
<br />
<table width="98%" border="0" align="center" cellpadding="0" cellspacing="0" class="table_line">
  <tr>
    <td class="line_td_head">序号</td>
    <td class="line_td_head">发文字号</td>
    <td class="line_td_head">状态</td>
  </tr>
  <tr>
    <td colspan="3" class="line_td_search">工作：
    <input type="text" name="textfield" id="textfield" class="input_1" />
    &nbsp;&nbsp;工作：  <input type="text" name="textfield" id="textfield"  class="input_1"/>&nbsp;&nbsp;工作：
    
    <select name="select" id="select" class="select_1">
      <option>---时间---</option>
    </select>
    
    <input type="submit" name="button" id="button" value="搜索" class="search_but" />
    </td>
  </tr>
  <tr>
    <td class="line_td_light">001002</td>
    <td class="line_td_light">发文字</td>
    <td class="line_td_light">发文</td>
  </tr>
  <tr>
    <td class="line_td_dark">001002</td>
    <td class="line_td_dark">发文</td>
    <td class="line_td_dark">发文</td>
  </tr>
  <tr>
    <td colspan="3" class="line_td_dark" align="right"><input type="submit" name="button13" id="button13" value="返回" class="back_but" />
    <input type="submit" name="button12" id="button12" value="刷新" class="shuaxin_but" />
    <input type="submit" name="button7" id="button7" value="上传文件" class="sc_but" />
    <input type="submit" name="button6" id="button6" value="添加文件" class="addfiel_but" />
    <input type="submit" name="button5" id="button5" value="通用" class="universal_but" />
    <input type="submit" name="button4" id="button4" value="删除" class="cancel_but" />
    <input type="submit" name="button3" id="button3" value="确认" class="confirm_but" />
    <input type="submit" name="button2" id="button2" value="添加" class="add_but" /></td>
  </tr>
</table>
<br />
<table width="98%" border="0" align="center" cellpadding="0" cellspacing="0" class="table_line">
  <tr>
    <td colspan="2" class="line_td_title">序号发文字号状态</td>
  </tr>
  <tr>
    <td width="33%" class="line_td_dark" align="right">001002发文字:</td>
    <td width="67%" class="line_td_dark"><input type="text" name="textfield2" id="textfield2" class="input_2" />
    <input type="submit" name="button11" id="button11" value="添加文件" class="addfiel_but" /></td>
  </tr>
  <tr>
    <td class="line_td_light"  align="right">001002发文字:</td>
    <td class="line_td_light">
      <input type="text" name="textfield3" id="textfield3" class="input_2" />
   </td>
  </tr>
  <tr>
    <td class="line_td_dark"  align="right">001002发文:</td>
    <td class="line_td_dark"><input type="text" name="textfield4" id="textfield4" class="input_2" /></td>
  </tr>
  <tr>
    <td class="line_td_dark"  align="right">&nbsp;</td>
    <td class="line_td_dark"><input type="submit" name="button10" id="button10" value="确认" class="confirm_but" />
    <input type="submit" name="button14" id="button14" value="返回" class="back_but" /></td>
  </tr>
</table>
<p>&nbsp;</p>
</body>
</html>
