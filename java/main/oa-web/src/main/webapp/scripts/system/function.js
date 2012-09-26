function InputSignNumber(decimal, nega)
{
    /**********************************************************
     允许输入-
     **********************************************************/
    var input_value = new String;
    input_value = this.event.srcElement.value;
    if ((this.event.keyCode < 45 )
            || (this.event.keyCode > 57 )
            || (this.event.keyCode == 47 && (typeof(nega) == "undefined" || !nega)))
    {
        this.event.returnValue = false;
        return;
    }
    var maxlength;
    if (isNaN(parseInt(this.event.srcElement.maxLength)))
        maxlength = 8;
    else
        maxlength = parseInt(this.event.srcElement.maxLength);
    if (input_value.length == 0)
    {
        //第一个字符不能为小数点
        if (this.event.keyCode == 46)
        {
            this.event.returnValue = false;
            return;
        }
    }
    else {
        if (input_value.length == 1)  //当输入第二位时 如果第一位是 负号,则不能输入小数点
        {
            var value = input_value.charAt(0);
            if (value == "-")
            {
                if (this.event.keyCode == 46)
                {
                    this.event.returnValue = false;
                    return;
                }
            }
        }

        //if(input_value.substring)
        //除了第一个字符以外都不允许出现 负号
        if (this.event.keyCode == 45)
        {
            this.event.returnValue = false;
            return;
        }
    }

    var first_pos = input_value.indexOf(".");
    var last_pos = input_value.lastIndexOf(".");
    //alert(first_pos);
    if (first_pos == -1)
    {
        if (null != maxlength)
        {
            if ((input_value.length + 1) > maxlength)
            {
                if (this.event.keyCode != 46)
                {
                    this.event.returnValue = false;
                    return;
                }
            }
        }
    }
    else
    {


        if (last_pos == first_pos)
        {
            //不能出现第二个小数点
            if (this.event.keyCode == 46)
            {
                this.event.returnValue = false;
                return;
            }
            if ((null != decimal && ! isNaN(decimal)))
            {
                //alert( (input_value.length > maxlength) ||( (input_value.length-first_pos)>decimal))
                if ((input_value.length > maxlength) || ( (input_value.length - first_pos) > decimal))
                {
                    this.event.returnValue = false;
                    return;
                }
            }
        }
    }

    //
    if (first_pos == -1 && (this.event.keyCode != 46 )) {
        if (input_value.length >= (maxlength - decimal)) {
            this.event.returnValue = false;
            return;
        }
    }

    if ((null != decimal && ! isNaN(decimal)))
    {
        if (decimal == 0 && this.event.keyCode == 46) {
            this.event.returnValue = false;
        }
    }
}

/****************************************************
 描述：从类似XML文档的字符串中读取指定元素的值
 返回：字符串
 没有找到返回空字符串
 例如：var doc="<id>123</id><name>ABC</name>"
 var tag="id"
 var ret=getElementValue(doc, tag)
 结果：ret="123"
 ****************************************************/
function getElementValue(doc, tag)
{
    var startTag = "<" + tag + ">";
    var endTag = "</" + tag + ">";
    var pStart = doc.indexOf(startTag);
    var pEnd = doc.indexOf(endTag);
    var ret = "";
    //	alert(pStart);
    //	alert(pEnd);
    if (pStart >= 0 && pEnd > pStart) {
        ret = doc.substring(pStart + startTag.length, pEnd);

    }
    return ret;
}

//增加项目标识到context中
function addProjectIdToContext(context)
{
    if (context != null && context != "")
    {
        var s = window.location + "";
        var pattern = /\/([0-9]{1,10})\//;
        var retStr = pattern.exec(s);
        //        alert(retStr[1]);
        if (retStr != null) {
            return context + "/" + retStr[1];
        }
    }
    return context;
}

function getIdElement(doc)
{
    return getElementValue(doc, "id");
}

//add by cxs
function getIdValue(doc) {
    var startTag = "<";
    var endTag = ">";
    var pStart = doc.indexOf(startTag);
    var pEnd = doc.indexOf(endTag);
    var ret = "";
    //	alert(pStart);
    //	alert(pEnd);
    if (pStart >= 0 && pEnd > pStart) {
        ret = doc.substring(pStart + startTag.length, pEnd);

    }
    return ret;
}

// 获取http request
function getHttpRequest()
{
    if (window.ActiveXObject) {
        return new ActiveXObject("Microsoft.XMLHTTP");
    }
    else if (window.XMLHttpRequest) {
        return new XMLHttpRequest();
    }

//         =========old start======    
//    // mozilla
//    if (window.XMLHttpRequest)
//    {
//        return new XMLHttpRequest();
//    }
//    // IE
//    else
//    {
//        return new ActiveXObject("Microsoft.XMLHTTP");
//        //            return new ActiveXObject("MSXML2.XmlHttp");
//    }
//          ========old end========

    //    // mozilla, netscape.....
    //    if (typeof XMLHttpRequest != "undefined")
    //    {
    //        return new XMLHttpRequest();
    //    }
    //    // IE
    //    else if (typeof ActiveXObject != "undefined")
    //    {
    //        var xmlHttp_ver = false;
    //        var xmlHttp_vers = ["MSXML2.XmlHttp.5.0","MSXML2.XmlHttp.4.0","MSXML2.XmlHttp.3.0","MSXML2.XmlHttp","Microsoft.XmlHttp"];
    //        if (!xmlHttp_ver)
    //        {
    //            for (var i = 0; i < xmlHttp_vers.length; i++)
    //            {
    //                try
    //                {
    //                    new ActiveXObject(xmlHttp_vers[i]);
    //                    xmlHttp_ver = xmlHttp_vers[i];
    //                    break;
    //                }
    //                catch(oError)
    //                {
    //                    ;
    //                }
    //            }
    //        }
    //        if (xmlHttp_ver)
    //        {
    //            return new ActiveXObject(xmlHttp_ver);
    //        }
    //        else
    //        {
    //            throw new Error("Could not create XML HTTP Request.");
    //        }
    //    }
    //    else
    //    {
    //        throw new Error("Your browser doesn't support an XML HTTP Request.");
    //    }
}

// 通过调用xmlhttp方式异步执行jsp动态页面，callback方法通过事件响应对返回结果进行处理.
function doRequest(url, method, callback, param)
{
    var xmlhttpRequest = getHttpRequest();
    if (!method)
        method = "POST";
    xmlhttpRequest.open(method, url, true);
    if (callback)
    {
        xmlhttpRequest.onreadystatechange = function()
        {
            if (xmlhttpRequest.readyState == 4)
            {
                ret = xmlhttpRequest.responseText;
                callback(ret);
            }
        };
    }
    xmlhttpRequest.setRequestHeader("Content-Type", "application/x-www-form-urlencoded;");
    xmlhttpRequest.send(param);
}
// Example:
//        doRequest(url, "POST", function(ret) {
//
//        //step2 得到返回的xml字符串 //
//        var xmlStr  = ret;
//        if(""!=xmlStr)
//
//        {
//            // step3   将字符串解析为distortionData对象数组
//            var distortions =  parseXml(xmlStr);
//            setAll(distortions);
//            isRefreshing=false;
//        }
//        else
//        {
//            alert("没有得到服务器端的数据");
//        }
//    });


function doPostRequest(url, callback, param)
{
    var method = "POST";
    doRequest(url, method, callback, param);
}

function doGetRequest(url, callback)
{
    var method = "GET";
    doRequest(url, method, callback, null);
}

// 通过调用xmlhttp方式同步执行jsp动态页面，并返回结果
function getRequest(url, method, param)
{
    var xmlhttpRequest = getHttpRequest();
    if (!method)
        method = "POST";
    xmlhttpRequest.open(method, url, false);
    xmlhttpRequest.setRequestHeader("Content-Type", "application/x-www-form-urlencoded;");
    xmlhttpRequest.send(param);
    return xmlhttpRequest.responseText;
}

function getPostRequest(url, param)
{
    var method = "POST";
    return getRequest(url, method, param);
}

function getGetRequest(url)
{
    var method = "GET";
    return getRequest(url, method, null);
}

// 对以前的同步调用方法程序兼容
// request http url, and return text content
function getUrlContent(url, method, param)
{

    ///////////////////////////
    var xmlhttpRequest = getHttpRequest();
    var retStr = "";
    if (!method){
        method = "POST";
    }
    xmlhttpRequest.open(method, url, false);
    xmlhttpRequest.setRequestHeader("Content-Type", "application/x-www-form-urlencoded;");
    xmlhttpRequest.send(param);
    retStr = xmlhttpRequest.responseText;
    return retStr;
}


/*******************************************************************
 描述：获取一个Select 的所有value的字符串
 ********************************************************************/
function getSelectValues(select)
{
    var result = "";
    for (var i = 0; i < select.options.length; i++)
    {
        if (i == 0)
        {
            result = select.options[i].value;
        }
        else
        {
            result = result + "," + select.options[i].value;
        }
    }
    return result;
}

/******************************************************************
 描述：获取一个Select 的所有name的字符串
 *******************************************************************/
function getSelectNames(select)
{
    var result = "";
    for (var i = 0; i < select.options.length; i++)
    {
        if (i == 0)
        {
            result = select.options[i].text;
        }
        else
        {
            result = result + "," + select.options[i].text;
        }
    }
    return result;
}

/******************************************************************
 描述：获取当前选中的名称
 *******************************************************************/
function getSelectedName(select)
{
    var ret = "";
    var options = select.options;
    for (var i = 0; i < options.length; i++)
    {
        if (options[i].selected)
        {
            if (options[i].value != "")
            {
                ret = options[i].text;
                break;
            }
        }
    }
    return ret;
}

/******************************************************************
 描述：给指定Select 添加一条option
 *******************************************************************/
function addSelectOption(code, name, select)
{
    var options = select.options;
    var index = getSelectOptionIndex(code, select);
    if (index < options.length)
    {
        options[index].text = name;
    }
    else
    {
        //名称和值重复则不再加入
        var f = false;
        for (var i = 0; i < options.length; i++)
        {
            if (options[i].text == name && options[i].value == code)
            {
                f = true;
                break;
            }
        }
        if (!f)
        {
            var option = new Option();
            option.value = code;
            option.text = name;
            options[index] = option;
        }
    }
}

/******************************************************************
 描述：将select中选中的option上移
 *******************************************************************/
function upSelectOption(select, inputid, inputname)
{
    var index = select.selectedIndex;
    if (index == -1)
        return;
    exchangeSelectOption(select, index, index - 1);
    if (inputid != null)
    {
        inputid.value = getSelectValues(select);
    }
    if (inputname != null)
    {
        inputname.value = getSelectNames(select);
    }
}

/******************************************************************
 描述：将select中选中的option下移
 *******************************************************************/
function downSelectOption(select, inputid, inputname)
{
    var index = select.selectedIndex;
    if (index == -1)
        return;
    exchangeSelectOption(select, index, index + 1);
    if (inputid != null)
    {
        inputid.value = getSelectValues(select);
    }
    if (inputname != null)
    {
        inputname.value = getSelectNames(select);
    }
}

/******************************************************************
 描述：给指定Select中的两个option交换先后位置
 *******************************************************************/
function exchangeSelectOption(select, index1, index2)
{
    var options = select.options;
    if (index1 >= options.length || index1 < 0 || index2 >= options.length || index2 < 0)
        return;
    if (index1 == index2)
        return;

    var index1Option = new Option();
    index1Option.value = select.options[index1].value;
    index1Option.text = select.options[index1].text;
    var index2Option = new Option();
    index2Option.value = select.options[index2].value;
    index2Option.text = select.options[index2].text;
    select.options[index1] = index2Option;
    select.options[index2] = index1Option;
    select.options[index2].selected = true;
}

/*********************function description*******************************************
 Fun Desc:   根据code获取在Select列表框中的序号，如果code不存在，则为select的length。
 **************************************************************************************/
function getSelectOptionIndex(code, select)
{
    var options = select.options;
    var result = options.length;
    for (var i = 0; i < options.length; i++)
    {
        if (options[i].value == code && code != "-1")
        {
            result = i;
            break;
        }
    }
    return result;
}

/*********************function description*******************************************
 Fun Desc:删除所有选定的Option
 **************************************************************************************/
function removeSelectedOption(select, inputValues, inputNames)
{
    var options = select.options;
    if (options.length > 0)
        for (var i = options.length - 1; i >= 0; i--)
        {
            if (options[i].selected)
            {
                select.remove(i);
            }
        }

    if (inputValues != null)
    {
        inputValues.value = getSelectValues(select);
    }
    if (inputNames != null)
    {
        inputNames.value = getSelectNames(select);
    }
}
/*********************function description*******************************************
 Fun Desc:清空指定选择框的所有Option
 **************************************************************************************/
function removeOption(select, inputValues, inputNames)
{
    var options = select.options;
    if (options.length > 0)
        for (var i = options.length - 1; i >= 0; i--)
        {
            select.remove(i);
        }

    if (inputValues != null)
    {
        inputValues.value = "";
    }
    if (inputNames != null)
    {
        inputNames.value = "";
    }
}
/*********************function description*******************************************
 Fun Desc:   判断Select列表框是否有记录选中
 **************************************************************************************/
function isOptionSelected(select)
{
    var options = select.options;

    for (var i = 0; i < options.length; i++)
    {
        if (options[i].selected)
        {
            return true;
        }
    }
    return false;
}

/*********************function description*******************************************
 *根据radio名称及缺省值设置选中状态
 **************************************************************************************/
function initRadio(objName, defaultValue) {
    var radioObj = eval("document.all." + objName);
    for (var i = 0; i < getArrayLength(radioObj); i++) {
        var check_obj = getArrayElement(radioObj, i);
        check_obj.checked = check_obj.value == defaultValue;
    }
}

/*********************function description*******************************************
 *如果有小数则四舍五入(小数点后指定位数len)
 **************************************************************************************/
function roundNumber(oldNum, len)
{
    if (null == len) len = 2;
    var add = 0;
    var s;
    var s1 = oldNum + "";
    var start = s1.indexOf(".");
    if (start == -1) return oldNum;
    if (s1.substr(start + len + 1, 1) >= 5)add = 1;
    var temp = Math.pow(10, len);
    s = Math.floor(oldNum * temp) + add;
    return (s / temp);
}

/***************设置图片大小********************/
var defaultWidth = 700; //默认显示图片宽度为500象素
function DrawImage(ImgD, picwidth) {
    if (picwidth == null) picwidth = defaultWidth;
    var image = new Image();
    image.src = ImgD.src;
    if (image.width > 0 && image.height > 0) {

        if (picwidth == 0) picwidth = image.width;
        if (image.width / image.height >= 1) {
            if (image.width > picwidth) {
                ImgD.width = picwidth;
                ImgD.height = (image.height * picwidth) / image.width;
            }
            else {
                ImgD.width = image.width;
                ImgD.height = image.height;
            }
        }
        else {
            if (image.height > picwidth) {
                ImgD.height = picwidth;
                ImgD.width = (image.width * picwidth) / image.height;
            }
            else {
                ImgD.width = image.width;
                ImgD.height = image.height;
            }
        }
    }
}

//取得对象数组的长度
function getArrayLength(eobj) {
    var len = 0;
    if (eobj) {
        len = 1;
        if (eobj.length) len = eobj.length;
    }
    return len;
}

//取得对象数组的元素
function getArrayElement(eobj, ei) {
    if (eobj && eobj.length) {
        var len = 1;
        len = eobj.length;
        if (ei >= len) ei = len - 1;
        return eobj[ei];
    }
    else {
        return eobj;
    }
}

/*********************function description***begin****************************************
 *js控制动态增加删除行，无须指定tableId和firstRow，仅需要设置一个隐藏的标准行id=standarditem
 *********************function description***begin****************************************/
/***增加行***
 *srcitem可选，为可指定ID的标准行，若无该参数则取id=standarditem的所在行
 *****/
function addRow(srcitem, currentRow)
{
    var srcObj;
    if (srcitem) {
        srcObj = srcitem;
    }
    else {
        if (!standarditem) {
            alert("未设置标准行，请在'Block} -->'后设置标准行！");
            return;
        }
        srcObj = standarditem;
    }
    var curRow = getRowTr();


    if (currentRow)
    {
        curRow = currentRow;
    }
    var row = curRow.parentElement.insertRow(curRow.rowIndex + 1);
    var cellcount = srcObj.cells.length;
    for (var i = 0; i < cellcount; i++) {
        var cell = row.insertCell(i);
        cell.align = srcObj.cells[i].align;
        cell.innerHTML = srcObj.cells[i].innerHTML;
        cell.style.display = srcObj.cells[i].style.display;

        //增加行的默认样式
        cell.className = "td_light";
    }
}
/**
 ***删除行***
 */
function delRow()
{
    if (confirm("确定要删除吗!"))
    {
        var trObj = getRowTr();
        trObj.parentElement.deleteRow(trObj.rowIndex);
    }
}
//获取当前obj动作触发时所在的tr
//搜索过程：event-->obj-->td-->tr
function getRowTr()
{
    return event.srcElement.parentElement.parentElement;
}
//提交表单时需要删除标准行
//srcitem可选
function standarditemDisable(srcitem)
{
    var srcObj;
    if (srcitem)
        srcObj = srcitem;
    else {
        srcObj = standarditem;
    }
    if (srcObj)
        srcObj.parentElement.deleteRow(srcObj.rowIndex);
}
/*********************function description***end****************************************/


/*********************function description*******************************************
 Fun Name: trimquotes()
 Fun Desc:  去除字符串两边的引号
 creator: LFC
 date:
 **************************************************************************************/
function trimquotes(action)
{
    if (null == action || "" == action) return "";
    var newAction = action.substring(1, action.length);
    newAction = newAction.substring(0, newAction.length - 1);
    return newAction;
}

function trimLeft(s) {
    while (s.substring(0, 1) == ' ') {
        s = s.substring(1, s.length);
    }
    return s;
}

function trimRight(s) {
    while (s.substring(s.length - 1, s.length) == ' ') {
        s = s.substring(0, s.length - 1);
    }
    return s;
}


function trim(s) {
    s = trimLeft(s);
    s = trimRight(s);
    return s;
}

/********如果有小数则四舍五入(小数点后指定位数len)******/
function splitString(oldNum, len)
{
    if (null == len) len = 2;
    var add = 0;
    var s;
    var s1 = oldNum + "";
    var start = s1.indexOf(".");
    if (start == -1) return oldNum;
    if (s1.substr(start + len + 1, 1) >= 5)add = 1;
    var temp = Math.pow(10, len);
    s = Math.floor(oldNum * temp) + add;
    return s / temp;
}
var focusId = 0;
var dataArray = new Array();
function getFocusId(obj)
{
    for (var i = 0; i < dataArray.length; i++)
    {
        if (dataArray[i] == obj) {
            focusId = i;
            break;
        }
    }
}

function stringToDate(sDate)
{
    if (sDate.length == 0)
        return null;
    else
    {
        var year = (sDate.substring(0, 4));
        var month = (sDate.substring(5, 7)) - 1;
        var day = (sDate.substring(8, 10));
        return new Date(year, month, day);
    }

}

function calcDays(startDate, endDate)
{
    if ((startDate == "") || (endDate == "")) {
        return "";
    }

    var iScrap = (Date.parse(stringToDate(endDate)) - Date.parse(stringToDate(startDate))) / 86400000 + 1;
    iScrap = iScrap + .1;
    return parseInt(iScrap);
}

function calcHours(startDate, endDate)
{
    if ((startDate == "") || (endDate == "")) {
        return "";
    }

    var iScrap = (Date.parse(StringToDate(endDate)) - Date.parse(stringToDate(startDate))) / 3600000;
    return parseInt(iScrap);
}

/**********************************************************
 *函数名称：messageDlg(title, msg, buttons)
 **********************************************************/
function messageDlg(title, msg, buttons)
{
    var url = CONTEXT_NAME + "/common/dialog/Dialog.jsp?title=" + title + "&msg=" + msg + "&buttons=" + buttons;
    var strFeatures = "dialogWidth=400px;dialogHeight=140px;center=yes;help=no;status=no";
    //alert(url);
    return showModalDialog(url, "", strFeatures);
}

/**
 * 系统记住当前tab页
 * @param code
 * @param value
 */
function recordTab(code, value)
{
    doRequest(CONTEXT_NAME + "/common/RecordTab.jsp?code=" + code + "&value=" + value, "GET");
}

/*********************function description*******************************************
 Fun Desc:   设置表单输入框变灰，不可写状态
 **************************************************************************************/
function setFormDisabled(form)
{
    var e = document.dataForm.elements;
    for (var i = 0; i < e.length; i++)
    {
        e(i).disabled = true;
    }
}
//置表单输入框恢复可写状态
function setFormEnable(form)
{
    var e = form.elements;
    for (var i = 0; i < e.length; i++)
    {
        e(i).disabled = false;
    }
}

function setElementHidden(form, liketag)
{
    var elements = form.elements;
    for (var i = 0; i < elements.length; i++)
    {
        var e = elements[i];
        if (e.name.indexOf(liketag) >= 0)
        {
            e.style.display = "none";
        }
    }

}

/**********************************************************
 *函数名称：messageDlg(title, msg, buttons)
 **********************************************************/
function messageDlg(title, msg, buttons)
{
    var url = CONTEXT_NAME + "/common/dialog/Dialog.jsp?title=" + title + "&msg=" + msg + "&buttons=" + buttons;
    var strFeatures = "dialogWidth=400px;dialogHeight=140px;center=yes;help=no;status=no";
    //	alert(url);
    //    document.all.description.value = url;
    return showModalDialog(url, "", strFeatures);
}

/*********************function description*******************************************
 Fun Desc:   日期比较
 **************************************************************************************/
function dayCompare(startDate, endDate)
{
    if ((startDate == "") || (endDate == "")) {
        return false;
    }
    //年份比较
    var startS = startDate.indexOf('-');
    var yearS = startDate.substring(0, startS);
    var startE = endDate.indexOf('-');
    var yearE = endDate.substring(0, startE);
    if ((yearS - yearE) > 0)
        return false;

    //月份比较
    startS ++;
    var startS2 = startDate.indexOf('-', startS);
    var monthS = startDate.substring(startS, startS2);

    startE ++;
    var startE2 = endDate.indexOf('-', startE);
    var monthE = endDate.substring(startE, startE2);
    if ((monthS - monthE) > 0)
        return false;

    //日期比较
    var dayS = startDate.substring(startS2 + 1);
    var dayE = endDate.substring(startE2 + 1);
    return (dayS - dayE) <= 0;

}

//时间比较
function datetimeCompare(startTime, endTime)
{
    if (startTime == "" || endTime == "") return false;

    return Date.parse(endTime.replace(/-/g, "/")) > Date.parse(startTime.replace(/-/g, "/"));
}

//判断输入框的内容是否合法<!--- test_element_valid用于判断输入框的内容是否合法,请不要删除! -->
document.writeln("<div id='test_element_valid' style='display:none'></div>");
function check_valid(form)
{
    var counttemp,strtemp;
    var strarray = new Array();
    var elements = form.elements;
    for (var i = 0; i < elements.length; i++)
    {
        counttemp = 0;
        strtemp = "";
        var element = elements[i];
        if (element.type == 'text' || element.type == 'textarea') {
            try
            {
                var v = element.value;
                if (v.indexOf("\"") >= 0)
                {
                    counttemp = 0;
                    strtemp = "";
                    //alert("请不要输入双引号\"");
                    //return false;
                    if (confirm("您输入的数据中存在[半角]双引号，您是否希望系统自动将[半角]双引号转换成中文[全角]的双引号?")) {
                        strarray = v.split("\"");
                        for (j = 0; j < strarray.length - 1; j++)
                        {
                            if (counttemp == 0)
                            {
                                strtemp = strtemp + strarray[j] + "“";
                                counttemp = 1;
                            }
                            else {
                                strtemp = strtemp + strarray[j] + "”";
                                counttemp = 0;
                            }
                        }
                        strtemp = strtemp + strarray[j];
                    }
                    else {
                        element.focus();
                        return false;

                    }
                    v = strtemp;
                }
                if (v.indexOf("'") >= 0)
                {
                    counttemp = 0;
                    strtemp = "";
                    if (confirm("您输入的数据中存在[半角]单引号，您是否希望系统自动将[半角]单引号转换成中文[全角]的单引号?")) {
                        strarray = v.split("'");
                        for (var j = 0; j < strarray.length - 1; j++)
                        {
                            if (counttemp == 0)
                            {
                                strtemp = strtemp + strarray[j] + "‘";
                                counttemp = 1;
                            }
                            else {
                                strtemp = strtemp + strarray[j] + "’";
                                counttemp = 0;
                            }
                        }
                        strtemp = strtemp + strarray[j];
                    }
                    else {
                        element.focus();
                        return false;

                    }
                    v = strtemp;
                }
                if (v != "" && (v.indexOf("<") >= 0 || v.indexOf(">") >= 0))
                {
                    v = v.replace(/</g, "〈");
                    v = v.replace(/>/g, "〉");
                    if (window.test_element_valid)
                    {
                        window.test_element_valid.innerHTML = v;
                        if (window.test_element_valid.innerText != v)
                        {
                            v = window.test_element_valid.innerText;
                            //								return false;
                        }
                    }
                }
                element.value = v;
            }
            catch(e)
            {

            }
        }
    }
    return true;
}
//将流程表单中无css样式的全部定义成12号字体，避免出现字体太大或者太小的情况
function initFlowCss()
{
    if (!document.all) return;
    if (!document.getElementsByTagName("td"))return;
    var count = document.getElementsByTagName("td").length;
    for (var i = 0; i < count; i++)
    {
        var tdElement = document.getElementsByTagName("td")[i];
        if (tdElement.className == "")
        {
            tdElement.className = "flow_fontsize";
        }
    }
}
/**
 * 动态加入事件监听
 * @param obj 所在object
 * @param eventType 事件类型
 * @param fnHandler  时间动作
 */
function addJsEvent(obj, eventType, fnHandler) {
    if (obj)
        obj.setAttribute(eventType, document.all ? function() {
            eval(fnHandler);
        } : fnHandler);
}

function HtmlCodeOperate() {
    this.i = 0;
}

HtmlCodeOperate.prototype.addRow = function(srcitem, currentRow) {
    this.i++;
    var srcObj;
    if (srcitem) {
        srcObj = srcitem;
    }
    else {
        if (!standarditem) {
            alert("未设置标准行，请在'Block} -->'后设置标准行！");
            return;
        }
        srcObj = standarditem;
    }
    var curRow = getRowTr();


    if (currentRow)
    {
        curRow = currentRow;
    }
    var row = curRow.parentElement.insertRow(curRow.rowIndex + 1);
    var cellcount = srcObj.cells.length;
    for (var i = 0; i < cellcount; i++) {
        var cell = row.insertCell(i);
        cell.align = srcObj.cells[i].align;
        cell.innerHTML = srcObj.cells[i].innerHTML.replace("{0}", this.i);
        cell.style.display = srcObj.cells[i].style.display;

        //增加行的默认样式
        cell.className = "td_light";
    }
};
/**
 * 弹出窗口--居中
 * @param url
 * @param width
 * @param height
 */
function openWindow(url, width, height) {
    var left = (screen.width - width) / 2;
    var top = (screen.height - height) / 2;
    var args = 'width=' + width + ', height=' + height + ', top=' + top + ', left=' + left;
    args += ', toolbar=no, scrollbars=no, menubar=no, location=no, resizable=no';
    window.open(url, 'oWin', args);
}

/**
 *
 */
//判断输入的日期是否正确
function CheckDate(INDate)
{
    if (INDate == "")
    {
        return false;
    }
    var subYY = INDate.substr(0, 4);
    if (isNaN(subYY) || subYY <= 0) {
        return false;
    }
    //转换月份
    if (INDate.indexOf('-', 0) != -1) {
        var separate = "-";
    }
    else {
        if (INDate.indexOf('/', 0) != -1) {
            separate = "/";
        }
        else {
            return false;
        }
    }
    var area = INDate.indexOf(separate, 0);
    var subMM = INDate.substr(area + 1, INDate.indexOf(separate, area + 1) - (area + 1));
    if (isNaN(subMM) || subMM <= 0) {
        return false;
    }
    if (subMM.length < 2) {
        subMM = "0" + subMM;
    }
    //转换日
    area = INDate.lastIndexOf(separate);
    var subDD = INDate.substr(area + 1, INDate.length - area - 1);
    if (isNaN(subDD) || subDD <= 0) {
        return false;
    }
    if (eval(subDD) < 10) {
        subDD = "0" + eval(subDD);
    }
    var NewDate = subYY + "-" + subMM + "-" + subDD;
    if (NewDate.length != 10) {
        return false;
    }
    if (NewDate.substr(4, 1) != "-") {
        return false;
    }
    if (NewDate.substr(7, 1) != "-") {
        return false;
    }
    var MM = NewDate.substr(5, 2);
    var DD = NewDate.substr(8, 2);
    if ((subYY % 4 == 0 && subYY % 100 != 0) || subYY % 400 == 0) { //判断是否为闰年
        if (parseInt(MM) == 2) {
            if (DD > 29) {
                return false;
            }
        }
    }
    else {
        if (parseInt(MM) == 2) {
            if (DD > 28) {
                return false;
            }
        }
    }
    var mm = new Array(1, 3, 5, 7, 8, 10, 12); //判断每月中的最大天数
    var mmmm = false;
    for (var i = 0; i < mm.length; i++) {
        if (parseInt(MM) == mm[i]) {
            mmmm = true;
            break;
        }
    }

    if (mmmm) {
        if (parseInt(DD) > 31) {
            return false;
        }
    }
    else {
        if (parseInt(DD) > 30) {
            return false;
        }
    }
    if (parseInt(MM) > 12) {
        return false;
    }
    return true;
}

function checkdatetime(value, type){
    var time = "time";
    var date = "date";
    var datetime = "datetime";

    var regtime = /^(\d{1,2})(:)?(\d{1,2})\2(\d{1,2})(\.\d{1,3}){0,1}$/;
    var regdate = /^(\d{1,4})(-|\/)(\d{1,2})\2(\d{1,2})$/;
    var regdatetime = /^(\d{1,4})(-|\/)(\d{1,2})\2(\d{1,2}) (\d{1,2}):(\d{1,2}):(\d{1,2})(\.\d{1,3}){0,1}$/;

    if(type == time) {
        return regtime.test(value);
    }
    else if(type == date){
        return regdate.test(value);
    }
    else if(type == datetime){
        return regdatetime.test(value);
    }
    return false;
}

function checkdatetime(value, type){
    var time = "time";
    var date = "date";
    var datetime = "datetime";

    var regtime = /^(\d{1,2})(:)?(\d{1,2})\2(\d{1,2})(\.\d{1,3}){0,1}$/;
    var regdate = /^(\d{1,4})(-|\/)(\d{1,2})\2(\d{1,2})$/;
    var regdatetime = /^(\d{1,4})(-|\/)(\d{1,2})\2(\d{1,2}) (\d{1,2}):(\d{1,2}):(\d{1,2})(\.\d{1,3}){0,1}$/;

    if(type == time) {
        return regtime.test(value);
    }
    else if(type == date){
        return regdate.test(value);
    }
    else if(type == datetime){
        return regdatetime.test(value);
    }
    return false;
}

function doPrivilege(privilegecode) {
    var result = getUrlContent('/web/common/util/ajax-util!hasPrivileges?privilegecode=' + privilegecode + "&tail=" + Math.random(), "POST", null);
    result = eval("(" + result + ")").Results;
    return result
}