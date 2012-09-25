//查询窗口宽度
var queryWindowWidth = 500;
var gridparam;
var pageSize;
var initPageSize;

var pagingBar;
var displayMsg;
function changePageSize(obj){
    pageSize = obj.value;
    if(pagingBar){
        pageSize = pagingBar.changePageSize(pageSize,makeDisplayMsg());
    }
}

function makeSelect(array, pagesize){
    var result = '<select onchange="changePageSize(this)">';

    for(var i = 0; i < array.length; i++){
        if(array[i] == pagesize){
            result += '<option value='+ array[i] + ' selected>' + array[i] + '</option>';
        }
        else{
            result += '<option value=' + array[i] +'>' + array[i] + '</option>';
        }
    }
    if(result.indexOf('selected') <= 0){
        result += '<option value=-1 selected>全部</option>';
    }
    else {
        result += '<option value=-1>全部</option>';
    }

    return result += '</select>';
}

function makeDisplayMsg() {
    var pageSizes = [10,20,50,100];
    displayMsg = '第 {0} 条到 {1} 条记录--共 {2} 条  显示 ';
    if(initPageSize && initPageSize > 0 && initPageSize != 10 && initPageSize != 20 && initPageSize != 50 && initPageSize != 100){
        pageSizes[4] = initPageSize;//alert(pageSizes[3] > pageSizes[4]);
        for(var i = 0; i < pageSizes.length; i++){
            var flag = true;
            for(var j = 0; j < pageSizes.length - i - 1; j++){
                if(pageSizes[j] > pageSizes[j + 1]){
                    pageSizes[j] = pageSizes[j] + pageSizes[j + 1];
                    pageSizes[j + 1] = pageSizes[j] - pageSizes[j + 1];
                    pageSizes[j] = pageSizes[j] - pageSizes[j + 1];

                    flag = false;
                }
            }
            if(flag){
                break;
            }
        }
    }

    displayMsg += makeSelect(pageSizes, pageSize);
    return displayMsg;
}

function gridinit(params){
    //初始化参数
    var url = params["url"];
    var div = params["div"];
    var addUrl = params["addUrl"];
    //**************** add by xiejiao ******************//
    var customUrl = params["customUrl"];
    var customButtonname = params["customButtonname"];
    var customFunction = params["customFunction"];

    var headUrl = params["headUrl"];
    var headButtonname = params["headButtonname"];
    var headFunction = params["headFunction"];

    var queryVisible = params["queryVisible"];
    var allVisible = params["allVisible"];
    //**************************************************//
    var havecheckbox = params["havecheckbox"];   //judge ishave check box

    var addFunction = params["addFunction"];
    pageSize = params["pageSize"];
    initPageSize = params["pageSize"];
    if(typeof gridParam=="object" && gridParam.value.indexOf("limit")>=0){
        pageSize = Ext.decode(gridParam.value).params.limit;
    }
    var title = params["title"];
    var gridParams = params["gridParams"];
    var buttonParams = params["buttonParams"];
    var frame = params["frame"];
    var gridConfig = new GridConfig(gridParams, buttonParams);

    var readyCoditions = params["conditions"];

    //添加查询条件
    var queryCondition=params["queryCondition"];
    //保存查询条件
    var conditions="";
    if(readyCoditions){
        conditions = readyCoditions;
    }

    var columnNames = gridConfig.getColumnNames();
    var keys = gridConfig.getKeys();
    var list = Ext.get(div);
    Ext.onReady(function() {
        //获取并存储数据
        var store = new Ext.data.Store({
            url:url,
            reader:new Ext.data.XmlReader({record:"row",totalRecords:"totalCount"}, keys)
        });

        //获取列
        var sm = new Ext.grid.CheckboxSelectionModel({singleSelect:false});
        var colM = gridConfig.getColumn(havecheckbox,sm);
        //页脚工具条
        pagingBar = new Ext.PagingToolbar({
            pageSize:pageSize,
            store:store,
            displayInfo:true,
            beforePageText:"第",
            afterPageText:"页 共 {0} 页",
            displayMsg: makeDisplayMsg(),
            emptyMsg: "没有记录",
            paramNames:{start:"start",limit:"limit",columns:"columns",conditions:"conditions"},

            doLoad:function(C) {
                var B = {},A = this.paramNames;
                B[A.start] = C;
                B[A.limit] = this.pageSize;
                B[A.columns] = columnNames;
                B[A.conditions] = this.store.lastOptions.params.conditions;
                if (this.fireEvent("beforechange", this, B) !== false) {
                    this.store.load({params:B});
                    gridparam = {params:B};
                    gridParam.value = Ext.encode(gridparam);
                }
            }
        });
        //
        var grid = new Ext.grid.GridPanel({
            renderTo:div,
            stripeRows:true,
            autoHeight: true,
            width:list.getComputedWidth() * 1,
            cm:colM,
            sm:sm,
            title:title,    // for grid title panel
            iconCls:'icon-grid',
            frame:frame,     // for grid surrounding line
            id:'grid',
            store:store,
            bbar:pagingBar,
            autoExpandColumn:2,
            viewConfig:{forceFit:true},
            tbar:[
                    {pressed:true,text:headButtonname,id:"head",iconCls:'title_head',handler:function() {     //add by xiejiao
                    if(headFunction){
                        eval(headFunction+"('"+"?gridParam="+Ext.encode(gridparam)+"')");
                    }else{
                        if(headUrl.indexOf("?")>=0){
                            window.location = headUrl +"&gridParam="+Ext.encode(gridparam);
                        }else{
                            window.location = headUrl +"?gridParam="+Ext.encode(gridparam);
                        }
                    }
                }},
                {xtype:"tbfill"},
                {pressed:true,text:'查询',id:"query",iconCls:'title_query',handler:function() {
                    queryRecord(store,queryCondition);
                }},
                {xtype:"tbseparator"},
                {pressed:true,text:"全部",id:"all",iconCls:'title_all',handler:function() {
                    gridparam = {params:{start:0,limit:pageSize,columns:columnNames,conditions:""}};
                    store.load(gridparam);
                    gridParam.value = Ext.encode(gridparam);

                    try {
                        resolveCondition('');
                    }

                    catch(E) {

                    }
                }},
                {xtype:"tbseparator"},
                {pressed:true,text:'添加',id:"add",iconCls:'title_add',handler:function() {
                    if(addFunction){
                        eval(addFunction+"('"+"?gridParam="+Ext.encode(gridparam)+"')");
                    }else{
                        if(addUrl.indexOf("?")>=0){
                            window.location = addUrl +"&gridParam="+Ext.encode(gridparam);
                        }else{
                            window.location = addUrl +"?gridParam="+Ext.encode(gridparam);
                        }
                    }
                }},
            {xtype:"tbseparator"},
            {pressed:true,text:customButtonname,id:"custom",iconCls:'title_custom',handler:function() {     //add by xiejiao
                    if(customFunction){
                        eval(customFunction+"('"+"?gridParam="+Ext.encode(gridparam)+"')");
                    }else{
                        if(customUrl.indexOf("?")>=0){
                            window.location = customUrl +"&gridParam="+Ext.encode(gridparam);
                        }else{
                            window.location = customUrl +"?gridParam="+Ext.encode(gridparam);
                        }
                    }
                }}]
        });

        if ((addUrl == null || addUrl == "")&&(addFunction == null || addFunction == "")) {
            Ext.getCmp("add").hide();
        }
        //************************ add by xiejiao ********//
        if ((customUrl == null || customUrl == "")&&(customFunction == null || customFunction == "")) {
            Ext.getCmp("custom").hide();
        }
        if ((headUrl == null || headUrl == "")&&(headFunction == null || headFunction == "")) {
            Ext.getCmp("head").hide();
        }
        if(queryVisible == "false"){
            Ext.getCmp("query").hide();
        }
        if(allVisible == "false"){
            Ext.getCmp("all").hide();
        }
        //**********************************************//
        window.onresize = function() {
            grid.setWidth(0);
            grid.setWidth(list.getComputedWidth() * 0.98);
            grid.setHeight(0);
            grid.setHeight(document.body.clientHeight * 0.98);
        };
        gridparam = {params:{start:0,limit:pageSize,columns:columnNames,conditions:conditions}};
        gridparam = getGridParams(gridparam);

        store.load(gridparam);

        //grid行双击事件
        grid.on("rowdblclick",function(grid){
            var record = grid.getSelectionModel().getSelected();
            var rowId = gridParams [0].name.replace(/\./g,"_");
            if(params["viewUrl"]) {
                if(params["viewUrl"].indexOf("?") > 0){
                    window.location = params["viewUrl"]+"&id="+record.data[rowId]+"&gridParam="+Ext.encode(gridparam);
                }
                else{
                    window.location = params["viewUrl"]+"?id="+record.data[rowId]+"&gridParam="+Ext.encode(gridparam);
                }
            }
        });

        //查询
        var querywin;
        function queryRecord(store,queryCondition)
        {
            if (!querywin)
            {
                var queryConfig=new QueryConfig(queryCondition);
                var querygrid=queryConfig.getQueryGrid();
                querywin = new Ext.Window({
                    layout:'fit',
                    resize:true,
                    defaults:{width:queryWindowWidth},
                    width:queryWindowWidth,
                    autoHeight:true,
                    closeAction:'hide',
                    plain:true,
                    buttonAlign:'center',
                    buttons:[
                        {
                            text:'确定',
                            type:'submit',
                            disabled:false,
                            handler:function() {
                                var dataLength=querygrid.store.data.length;
                                var queryGridRds=querygrid.getStore().getRange(0,dataLength-1);
                                var conditions="{";
                                for(var i=0;i<queryGridRds.length;i++){
                                    if(queryGridRds[i].get("operator")){
                                        conditions += i+":"+Ext.encode(queryGridRds[i].data)+",";
                                    }
                                }
                                if(conditions!="{"){
                                    conditions=conditions.substring(0,conditions.length-1)+"}";
                                }else{
                                    conditions="";
                                }
                                querywin.hide();
                                gridparam = {params:{start:0,limit:pageSize,columns:columnNames,conditions:conditions}};
                                store.load(gridparam);
                                gridParam.value = Ext.encode(gridparam);

                                try {
                                    resolveCondition(conditions);
                                }

                                catch(E) {
                                    
                                }
                                //conditions = "";
                            }
                        },
                        {
                            text:'关闭',
                            handler:function()
                            {
                                querywin.hide();
                            }
                        },
                        {
                            text:'重设',
                            handler:function()
                            {

                                var queryConfig=new QueryConfig(queryCondition);
                                querygrid=queryConfig.getQueryGrid();
                                querywin.remove("querygrid");
                                querywin.hide();
                                querywin.add(querygrid);
                                querywin.show();
                            }
                        }]
                });
                querywin.add(querygrid);
            }
            querywin.show();
        }
    });
}
/**
 * gridParameters 生成grid需要的参数 key，header
 * @param gridParameters
 */

function GridConfig(gridParameters, buttonParames)
{
    this.key = new Array();
    this.buttonRenderer = new Array();
    this.type=new Array();
    this.header = new Array();
    this.buttonheader = new Array();
    this.renderer = new Array();
    this.width = new Array();
    if (typeof gridParameters == "object")
    {
        for (var i = 0; i < gridParameters.length; i++)
        {
            this.key[i] = gridParameters[i].name.replace(/\./g,"_");

            if(gridParameters[i].type)
            {
                this.type[i]=gridParameters[i].type;
            }else{
                this.type[i]="";
            }

            if(gridParameters[i].width)
            {
                this.width[i]=gridParameters[i].width;
            }else{
                this.width[i]="";
            }
            //renderer  添加外部渲染
            if(gridParameters[i].renderer)
            {
                this.renderer[i]=gridParameters[i].renderer;
            }else{
                this.renderer[i]="";
            }

            this.header[i] = gridParameters[i].header;
        }
    }
    else
    {
        Ext.Msg.alert("Error,the parameters is error");
    }

    if (typeof buttonParames == "object")
    {
        for (i = 0; i < buttonParames.length; i++)
        {
            this.buttonheader[i] = buttonParames[i].header;
            if(buttonParames[i].renderer)
            {
                this.buttonRenderer[i] = buttonParames[i].renderer;
            }else{
                this.buttonRenderer[i]="";
            }

            if(buttonParames[i].name)
            {
                this.key[gridParameters.length+i] = buttonParames[i].name;
            }else{
                this.key[gridParameters.length+i]="";
            }
        }
    }
}


/**
 * define the column of the grid
 * @param Key
 */
GridConfig.prototype.getColumn = function(havecheckbox,sm) {
    var key = this.key;
    var type=this.type;
    var header = this.header;
    var buttonheader = this.buttonheader;
    var buttonRenderer = this.buttonRenderer;
    var renderer = this.renderer;
    //    var renderer = this.renderer;
    var column = new Array();
    column[0] = new Ext.grid.RowNumberer();

    var j ;
    if(havecheckbox == "true"){
        j = 2;
        column[1] = sm;
    }else{
        j = 1;
    }
    var list = Ext.get("list");
    //    column[1] = new Ext.grid.CheckboxSelectionModel();
    for (var i = 0; i < header.length; i++)
    {
        if (header[i] == "")
        {
        }
        else
        {
            if(type[i] == "DateTime")
            {
                //                column[j] = {header:header[i],dataIndex:key[i],sortable:true,width:160,renderer:new Ext.util.Format.dateRenderer('Y年m月d日')};
                column[j] = {header:header[i],dataIndex:key[i],sortable:true,width:160,editor: new Ext.form.DatetimeField({})}
            }
            else
            {
                var width=(eval(this.width)[i].replace("%","")/100)*list.getComputedWidth();
                if(renderer[i] != "")
                {
                    column[j] = {header:header[i],dataIndex:key[i],sortable:true,align:String(header[i]).indexOf("附件列表") > -1? "left" : "center",width:width||list.getComputedWidth()/(key.length+2),renderer:eval(renderer[i])}
                }
                else
                {
                    column[j] = {header:header[i],dataIndex:key[i],sortable:true,align:"center",width:width||list.getComputedWidth()/(key.length+2)}
                }
            }
            j++;
        }
    }
    for (i = 0; i < buttonheader.length; i++)
    {
        if (buttonheader[i] == "")
        {
        }
        else
        {
            column[j] = {header:buttonheader[i],dataIndex:key[this.header.length+i],align:"center",renderer:eval(buttonRenderer[i])};
            j++;
        }
    }
    return  new Ext.grid.ColumnModel(column);
}


GridConfig.prototype.getKeys = function() {
    return this.key;
}

GridConfig.prototype.getColumnNames = function() {
    var key = this.key;
    var columnNames = "";
    for (var i = 0; i < this.header.length; i++)
    {
        columnNames += key[i].replace(/\_/g,".") + ",";
    }
    return columnNames.substring(0, columnNames.length - 1);
}

function ButtonController() {
    this.query = true;
    this.all = true;
    this.add = true;
}

ButtonController.prototype.setQueryHide = function(hide) {
    if (typeof hide == "boolean") {
        this.query = hide;
    }
}

ButtonController.prototype.getQueryHide = function() {
    return this.query;
}

ButtonController.prototype.setAllHide = function(hide) {
    if (typeof hide == "boolean") {
        this.all = hide;
    }
}

ButtonController.prototype.getAllHide = function() {
    return this.all;
}

ButtonController.prototype.setAddHide = function(hide) {
    if (typeof hide == "boolean") {
        this.add = hide;
    }
}

ButtonController.prototype.getAddHide = function() {
    return this.add;
}



function clickEvent() {
    var record = Ext.getCmp("grid").getSelectionModel().getSelected();
    var rowId = params.gridParams[0].name.replace(/\./g,"_");
    var gridParam = getGridParams(gridparam);
    if(params.modifyUrl&&params.modifyUrl.indexOf("?")>=0){
        window.location = params.modifyUrl+"&id=" + record.data[rowId]+"&gridParam="+Ext.encode(gridParam);
    }else if(params.modifyUrl){
        window.location = params.modifyUrl+"?id=" + record.data[rowId]+"&gridParam="+Ext.encode(gridParam);
    }

}

function displayButton()
{
    var html="";
    if(params["modifyUrl"]){
        html = "<input type='button' value=' ' title='更新' class='button_update' onClick='clickEvent()'>";
    }
    if(params["deleteUrl"]){
        html += "&nbsp;&nbsp;<input type='button' title='删除' value=' ' class='button_delete' onClick='deleteEvent();'>";
    }
    //用户自定义按钮
    if(params["customButtons"]){
        var buttons = params["customButtons"];
        for(var i=0;i<buttons.length;i++){
            var button = buttons[i];
            if(button.title){
                html += "&nbsp;&nbsp;<input type='button' name='"+button.name+"' title='"+button.title+"' value='"+button.value+"' class='"+button.css+"' onClick='"+button.event+"();'>";
            }
            else{
                html += "&nbsp;&nbsp;<input type='button' name='"+button.name+"' value='"+button.value+"' class='"+button.css+"' onClick='"+button.event+"();'>";
            }
        }
    }
    return html;
}

function bssdetailclickEvent(){//add by xiejiao 2009/7/2 18:46
    var record = Ext.getCmp("grid").getSelectionModel().getSelected();
    var rowId = params.gridParams[0].name.replace(/\./g,"_");
    var gridParam = getGridParams(gridparam);
    if(params.bssdetailUrl&&params.bssdetailUrl.indexOf("?")>=0){
        window.location = params.bssdetailUrl+"&id=" + record.data[rowId]+"&gridParam="+Ext.encode(gridParam);
    }else if(params.bssdetailUrl){
        window.location = params.bssdetailUrl+"?id=" + record.data[rowId]+"&gridParam="+Ext.encode(gridParam);
    }
}

function deleteEvent() {
    var record = Ext.getCmp("grid").getSelectionModel().getSelected();
    var rowId = params.gridParams [0].name.replace(/\./g,"_");
    Ext.MessageBox.confirm('提示框', '您确定要进行删除操作？', function(btn) {
        if (btn == 'yes')
        {
            Ext.Ajax.request({
                url: params["deleteUrl"],
                params: { id:record.data[rowId]},
                success: function() {
                    Ext.getCmp("grid").getStore().reload();
                    Ext.Msg.alert("提示信息", "删除已成功！");
                    if(window.parent.tree_frame){
                        if(parent.tree_frame.lastSelectedNode){
                            parent.tree_frame.updateParent(parent.tree_frame.lastSelectedNode);
                        }
                    }
                },
                failure: function() {
                    Ext.Msg.alert("提示信息", "删除失败  ");
                }
            });
        }
    });
}


/**
 * class  query
 */
function QueryConfig(data)
{
    this.data = data;
    this.stringOp = new Ext.data.SimpleStore({
        fields: ['value','text'],
        data : [["","选择关系运算符"],['=','等于'],['<>','不等于'],
            ["like '|%'",'以...开头'],["like '%|'",'以...结尾'],["like '%|%'",'包含字符'],["not like '%|%'",'不包含字符'],
            ['is null','空白'],['is not null','非空白']]
    });
    this.datetimeOp = new Ext.data.SimpleStore({
        fields: ['value','text'],
        data : [["","选择关系运算符"],['=','等于'],['<','小于'],['<=','小于等于'],['>','大于'],['>=','大于等于'],["between","在...之间"]]
    });
    this.numberOp = this.datetimeOp;

}

QueryConfig.prototype.getOpStore = function(type) {
    var _store;
    if (type == "String")
    {
        _store = this.stringOp;
    } else if (type == "Number")
    {
        _store = this.numberOp;
    } else if (type = "Datetime")
        {
            _store = this.datetimeOp;
        }
    return _store;
}
/**
 * get the Editor for each column
 * @param type
 */
QueryConfig.prototype.getEditor = function(type) {
    var editor;
    if (type == "String")
    {
        editor = new Ext.form.TextField({});
    }
    else if (type == "Number")
    {
        editor = new Ext.form.NumberField({});
    }
    else if (type = "Datetime")
        {
//            editor = new Ext.form.DateField({fomat:"Y-m-d"});
            editor = new Ext.form.DatetimeField({});

        }
    return editor;
}
/**
 * init the query grid
 */

QueryConfig.prototype.getQueryGrid = function() {
    var qeuryConfig = this;
    var stringeditor = new Ext.form.TextField({});

    var fields=["name","entity","type","propertyName","operator","firstValue","secondValue","andOr","bracket"];
    for(var i=0;i<qeuryConfig.data.length;i++){
        for(var j=0;j<fields.length;j++){
            if(!(qeuryConfig.data)[i][j]){
                (qeuryConfig.data)[i][j]="";
            }
        }
    }
    var store = new Ext.data.SimpleStore({data:qeuryConfig.data,fields:fields});
    var colM = new Ext.grid.ColumnModel([
        {header:"字段名称",dataIndex:"name",sortable:true},
        {header:"运算符",dataIndex:"operator",editor:stringeditor},
        {header:"数值1",dataIndex:"firstValue",width:150,editor:stringeditor,renderer:displayValue},
        {header:"数值2",dataIndex:"secondValue",width:150,editor:stringeditor,renderer:displayValue}
        //    {header:"关系",dataIndex:"andOr",editor: new Ext.form.ComboBox({
        //        store: [["","请选择括号.."],["and","and"],["or","or"]],
        //        mode: 'local',
        //        triggerAction: 'all',
        //        valueField: 'value',
        //        displayField: 'text',
        //        editable: false
        //    })},
        //    {header:"括号",dataIndex:"bracket", hidden:true,editor: new Ext.form.ComboBox({
        //        store:[["","请选择括号.."],["(","("],[")",")"]],
        //        mode: 'local',
        //        triggerAction: 'all',
        //        valueField: 'value',
        //        displayField: 'text',
        //        editable: false
        //    })}
    ]);

    var grid = new Ext.grid.EditorGridPanel({
        id:"querygrid",
        title:"查询",
        autoHeight:true,
        width:700,
        cm:colM,
        clicksToEdit:1,
        store:store,
        selModel: new Ext.grid.RowSelectionModel({singleSelect:false}),
        autoExpandColumn:2
    });
    grid.on("beforeedit", function(e) {
        var record = grid.getSelectionModel().getSelected();
        if (e.column == 1){
            var _store = qeuryConfig.getOpStore(record.get("type"));
            var Opcombox = new Ext.form.ComboBox({
                store: _store,
                mode: 'local',
                triggerAction: 'all',
                valueField: 'value',
                displayField: 'text',
                editable: false
            });
            grid.colModel.setEditor(e.column, new Ext.grid.GridEditor(Opcombox));
        }
        if (e.column == 2) {
            var gridEditor2 = qeuryConfig.getEditor(record.get("type"));
            if (getParamsNum(record.get("operator")) == 0) {
                grid.colModel.setEditor(e.column, new Ext.grid.GridEditor(null));
            }
            if (getParamsNum(record.get("operator")) == 1 || getParamsNum(record.get("operator")) == 2) {
                grid.colModel.setEditor(e.column, new Ext.grid.GridEditor(gridEditor2));
            }
        }
        if (e.column == 3){
            var gridEditor3 = qeuryConfig.getEditor(record.get("type"));
            if (getParamsNum(record.get("operator")) == 0 || getParamsNum(record.get("operator")) == 1){
                grid.colModel.setEditor(e.column, new Ext.grid.GridEditor(null));
            }
            if (getParamsNum(record.get("operator")) == 2){
                grid.colModel.setEditor(e.column, new Ext.grid.GridEditor(gridEditor3));
            }
        }
    });

    grid.on("afteredit", function(e) {
        var record = grid.getSelectionModel().getSelected();
        if (e.column == 1){
            if (getParamsNum(record.get("operator")) == 0){
                record.set("firstValue", "");
                record.set("secondValue", "");
            }
            if (getParamsNum(record.get("operator")) == 1){
                record.set("secondValue", "");
            }
        }
    });
    return grid;
};
/**
 * according the operator's type,we can get the amount of the params that used to query
 * @param operator
 */
function getParamsNum(operator)
{
    var ParamsNum;
    if (operator == "is null" || operator == "is not null" || operator == ""){
        ParamsNum = 0;
    }
    else if (operator == "between"){
        ParamsNum = 2;
    }
    else{
        ParamsNum = 1;
    }
    return ParamsNum;
}



/**
 * format the date.
 * @param value
 */
function displayValue(value){
    var formatValue = value;
    if (formatValue.toString().indexOf("UTC") >= 0||formatValue.toString().indexOf("GMT") >= 0){
        var dt = new Date(formatValue);
        formatValue = dt.format('Y-m-d');
    }
    return formatValue;
}

/**
 * 判断gridParam是取历史记录还是当前页面生成
 */

function getGridParams(passParams){
    var gridparam;
    if(typeof gridParam=="object" && gridParam.value.indexOf("start")>=0)
    {
        gridparam = Ext.decode(gridParam.value);
    } else{
        gridparam= passParams;
    }
    return gridparam;
}

/**
 * grid中日期时间格式化显示
 * @param value
 */
function datetimeFormat(value){
    var reDate = /\d{4}\-\d{2}\-\d{2}/gi;
    //    var reTime = /\d{2}:\d{2}:\d{2}/gi;
    var reTime = /\d{2}:\d{2}/gi;
    return value.match(reDate) + " " + value.match(reTime);
}

/**
 * grid中日期格式化显示
 * @param value
 */
function dateFormat(value){
    var reDate = /\d{4}\-\d{2}\-\d{2}/gi;
    return value.match(reDate);
}


Ext.form.DatetimeField = Ext.extend(Ext.form.TriggerField, {
    format:"m/d/Y",
    altFormats:"m/d/Y|n/j/Y|n/j/y|m/j/y|n/d/y|m/j/Y|n/d/Y|m-d-y|m-d-Y|m/d|m-d|md|mdy|mdY|d|Y-m-d",
    disabledDaysText:"Disabled",
    disabledDatesText:"Disabled",
    minText:"The date in this field must be equal to or after {0}",
    maxText:"The date in this field must be equal to or before {0}",
    invalidText:"{0} is not a valid date - it must be in the format {1}",
    triggerClass:"x-form-date-trigger",
    showToday:true,
    defaultAutoCreate:{
        tag:"input",
        type:"text",
        size:"10",
        autocomplete:"off"
    },initComponent:function() {
        Ext.form.DatetimeField.superclass.initComponent.call(this);
        if (typeof this.minValue == "string") {
            this.minValue = this.parseDate(this.minValue)
        }
        if (typeof this.maxValue == "string") {
            this.maxValue = this.parseDate(this.maxValue)
        }
        this.ddMatch = null;
        this.initDisabledDays()
    },initDisabledDays:function() {
    if (this.disabledDates) {
        var A = this.disabledDates;
        var C = "(?:";
        for (var B = 0; B < A.length; B++) {
            C += A[B];
            if (B != A.length - 1) {
                C += "|"
            }
        }
        this.disabledDatesRE = new RegExp(C + ")")
    }
},setDisabledDates:function(A) {
    this.disabledDates = A;
    this.initDisabledDays();
//    if (this.menu) {
//        this.menu.picker.setDisabledDates(this.disabledDatesRE)
//    }
},setDisabledDays:function(A) {
    this.disabledDays = A;
//    if (this.menu) {
//        this.menu.picker.setDisabledDays(A)
//    }
},setMinValue:function(A) {
    this.minValue = (typeof A == "string" ? this.parseDate(A) : A);
//    if (this.menu) {
//        this.menu.picker.setMinDate(this.minValue)
//    }
},setMaxValue:function(A) {
    this.maxValue = (typeof A == "string" ? this.parseDate(A) : A);
//    if (this.menu) {
//        this.menu.picker.setMaxDate(this.maxValue)
//    }
},validateValue:function(E) {
//    E = this.formatDate(E);
//    if (!Ext.form.DatetimeField.superclass.validateValue.call(this, E)) {
//        return false
//    }
//    if (E.length < 1) {
//        return true
//    }
//    var C = E;
//    E = this.parseDate(E);
//    if (!E) {
//        this.markInvalid(String.format(this.invalidText, C, this.format));
//        return false
//    }
//    var F = E.getTime();
//    if (this.minValue && F < this.minValue.getTime()) {
//        this.markInvalid(String.format(this.minText, this.formatDate(this.minValue)));
//        return false
//    }
//    if (this.maxValue && F > this.maxValue.getTime()) {
//        this.markInvalid(String.format(this.maxText, this.formatDate(this.maxValue)));
//        return false
//    }
//    if (this.disabledDays) {
//        var A = E.getDay();
//        for (var B = 0; B < this.disabledDays.length; B++) {
//            if (A === this.disabledDays[B]) {
//                this.markInvalid(this.disabledDaysText);
//                return false
//            }
//        }
//    }
//    var D = this.formatDate(E);
//    if (this.ddMatch && this.ddMatch.test(D)) {
//        this.markInvalid(String.format(this.disabledDatesText, D));
//        return false
//    }
        return checkdatetime(E, "datetime");
//        return true;
},validateBlur:function() {
//    return !this.menu || !this.menu.isVisible()
        return true;
},getValue:function() {
    return this.parseDate(Ext.form.DatetimeField.superclass.getValue.call(this)) || ""
},setValue:function(A) {
    Ext.form.DatetimeField.superclass.setValue.call(this, this.formatDate(this.parseDate(A)))
},parseDate:function(D) {
        if(checkdatetime(D, "datetime")){
            return D;
        }
        else {
            return "";
        }
//    if (!D || Ext.isDate(D)) {
//        return D
//    }
//    var B = Date.parseDate(D, this.format);
//    if (!B && this.altFormats) {
//        if (!this.altFormatsArray) {
//            this.altFormatsArray = this.altFormats.split("|")
//        }
//        for (var C = 0,A = this.altFormatsArray.length; C < A && !B; C++) {
//            B = Date.parseDate(D, this.altFormatsArray[C])
//        }
//    }
//    return B
},onDestroy:function() {
//    if (this.menu) {
//        this.menu.destroy()
//    }
    if (this.wrap) {
        this.wrap.remove()
    }
    Ext.form.DatetimeField.superclass.onDestroy.call(this)
},formatDate:function(A) {
    return Ext.isDate(A) ? A.dateFormat(this.format) : A
},menuListeners:{select:function(A, B) {
    this.setValue(B)
},show:function() {
    this.onFocus()
},hide:function() {
    this.focus.defer(10, this);
    var A = this.menuListeners;
    this.menu.un("select", A.select, this);
    this.menu.un("show", A.show, this);
    this.menu.un("hide", A.hide, this)
}},onTriggerClick:function() {
//    if (this.disabled) {
//        return
//    }
//    if (this.menu == null) {
//        this.menu = new Ext.menu.DateMenu()
//    }
//    Ext.apply(this.menu.picker, {minDate:this.minValue,maxDate:this.maxValue,disabledDatesRE:this.ddMatch,disabledDatesText:this.disabledDatesText,disabledDays:this.disabledDays,disabledDaysText:this.disabledDaysText,format:this.format,showToday:this.showToday,minText:String.format(this.minText, this.formatDate(this.minValue)),maxText:String.format(this.maxText, this.formatDate(this.maxValue))});
//    this.menu.on(Ext.apply({}, this.menuListeners, {scope:this}));
//    this.menu.picker.setValue(this.getValue() || new Date());
//    this.menu.show(this.el, "tl-bl?")
        calendar(this, "datetime");
},beforeBlur:function() {
    var A = this.parseDate(this.getRawValue());
    if (A) {
        this.setValue(A)
    }
}});
Ext.reg("datetimefield", Ext.form.DatetimeField);
