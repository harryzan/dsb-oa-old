/*!
 * Ext JS Library 3.1.0
 * Copyright(c) 2006-2009 Ext JS, LLC
 * licensing@extjs.com
 * http://www.extjs.com/license
 */
/**
 * @class Ext.ux.tree.MyTreeGrid
 * @extends Ext.ux.tree.MyTreeGrid
 *
 * @xtype mytreegrid
 */
Ext.ns('Ext.ux.tree');
Ext.ux.tree.MyTreeGrid = Ext.extend(Ext.ux.tree.TreeGrid, {
    queryButtonHidden:false,
    addUrl:'',
    modifyUrl:'',
    deleteUrl:'',
    myButtons:'',
    contextmenu:"",
    queryJson :"",

    loader:this.loader,
    searchUrl:"",
    dataUrl:'',
    image:["folder.gif","leaf.gif"],
    imagePath:"extjs/resources/images/default/tree/",

    initComponent : function() {
        this.initRoot();
        this.addTbar();
        Ext.ux.tree.MyTreeGrid.superclass.initComponent.call(this);
        this.initLoader();
        this.addEvent();
    },

    initRoot:function() {
        this.root = new Ext.tree.AsyncTreeNode({id:'root',uid:'root',type:'root',text: 'Root',data:'data'});
    },
    initLoader:function() {

        var wait;
        var dataUrl = this.dataUrl;
        this.loader = new Ext.tree.TreeLoader({
            dataUrl:dataUrl,
            requestMethod: this.requestMethod
        });
        this.loader.on("beforeload", function(loader, node) {
            loader.baseParams.uid = node.attributes.uid;
            loader.baseParams.type = node.attributes.type;
//            try {
//                if (node.attributes.uid.indexOf("root") >= 0) {
//                    wait = Ext.MessageBox.wait("正在获取数据...", "请稍等");
//                }
//            }
//            catch(E) {
//            }

        }, this.loader);
        this.loader.on("load", function() {
//            try{
//                wait.hide();
//            }
//            catch(E){}

        });
    },

    addTbar:function() {
        var addButton;
        var treeGrid = this;
        var addHandler = function() {
            treeGrid.createWin(treeGrid.addUrl);
        };
        if (this.addUrl) {
            addButton = new Ext.Button({
                pressed:true,
                text:"新增",
                id:"add",
                handler:addHandler
            });
        }

        var modifyButton;
        var modifyHandler = function() {
            var modifyUrl = treeGrid.modifyUrl;
            var record = treeGrid.getSelectionModel().getSelectedNode();
            if (!record) {
                Ext.Msg.alert("提示", "请选择其中一行！");
                return;
            }
            var id = record.id;
            //得到modifyUrl值
            if (modifyUrl.indexOf("?") > 0) {
                modifyUrl += "&id=" + id;
            } else {
                modifyUrl += "?id=" + id;
            }
            var formWindow = treeGrid.createWin(modifyUrl);
        };
        if (this.modifyUrl) {
            modifyButton = new Ext.Button({
                pressed:true,
                text:"修改",
                id:"modify",
                handler:modifyHandler
            });
        }

        var deleteButton;

        var deleteHandler = function() {
            treeGrid.deleteRecord();
        };
        if (this.deleteUrl) {
            deleteButton = new Ext.Button({
                pressed:true,
                text:"删除",
                id:"delete",
                handler:deleteHandler
            });
        }

        var tbfill = {
            xtype:"tbfill"
        };
        var tbseparator = {
            xtype:"tbseparator"
        };

        var queryFlag = 0;
        //query condition

        var queryJson = treeGrid.queryJson;
        var queryButtonHidden = this.queryButtonHidden;
        if (treeGrid.queryButtonHidden) {
            queryButtonHidden = treeGrid.queryButtonHidden;
        }

        //add query tool
        var queryButton = new Ext.Button({
            hidden:queryButtonHidden,
            pressed:true,
            icon:"skins/extcss/images/icons/title_query.gif",
            handler:function() {
                var tableItem = new Array();
                var gridWidth = treeGrid.getWidth();
                var cols = 1;
                var mult = 2.6;
                if (parseInt(gridWidth) >= 470 && parseInt(gridWidth) < 770) {
                    cols = 1;
                    mult = 2.6;
                } else if (parseInt(gridWidth) >= 770 && parseInt(gridWidth) < 940) {
                    cols = 2;
                    mult = 1.6;
                } else if (parseInt(gridWidth) >= 940) {
                    cols = 3;
                    mult = 1;
                }
                var dataNameIndex = 0;
                for (var i = 0; i < Math.round(queryJson.length / cols + 0.5); i++) {
                    var rowItem = new Object();
                    rowItem.layout = "column";
                    rowItem.border = false;
                    rowItem.height = 25;
                    rowItem.items = new Array();
                    for (var j = 0; j < cols; j++) {
                        var index = i * cols + j;
                        if (index < queryJson.length) {
                            var data = queryJson[index];
                            if (data.type == "String") {
                                var item = {columnWidth:.297 * mult,layout:'form',border:false,labelWidth: 80,
                                    items:{xtype:'textfield',fieldLabel:data.label,anchor:'95%',id:data.name + dataNameIndex} };
                                rowItem.items.push(item);
                            } else if (queryJson[index].type == "Number") {
                                var item = {columnWidth:.297 * mult,layout:'form',border:false,labelWidth: 80,
                                    items:{xtype:'numberfield',fieldLabel:data.label,anchor:'95%',id:data.name + dataNameIndex}};
                                rowItem.items.push(item);
                            } else if (data.type == "Date") {
                                var item1 = {
                                    columnWidth:.165 * mult,layout:'form',border:false,labelWidth: 80,
                                    items:{xtype:'datefield',fieldLabel:data.label,anchor:'95%',id:data.name + "_date1" + dataNameIndex,format:'Y-m-d',editable:false}
                                };
                                var item2 = {
                                    columnWidth:.13 * mult,layout:'form',border:false,labelWidth: 10,
                                    items:{xtype:'datefield',fieldLabel:"~",labelSeparator:"",id:data.name + "_date2" + dataNameIndex,anchor:'90%',format:'Y-m-d',editable:false}
                                };
                                rowItem.items.push(item1);
                                rowItem.items.push(item2);
                            } else if (data.type == "DateTime") {
                                var item1 = {
                                    columnWidth:.165 * mult,layout:'form',border:false,labelWidth: 80,
                                    items:{xtype:'textfield',fieldLabel:data.label,anchor:'95%',id:data.name + "_date1" + dataNameIndex,format:'Y-m-d h:m:s',listeners:{focus : function() {
                                        calendar(this, 'datetime');
                                    }}}
                                };
                                var item2 = {
                                    columnWidth:.13 * mult,layout:'form',border:false,labelWidth: 10,
                                    items:{xtype:'textfield',fieldLabel:"~",labelSeparator:"",id:data.name + "_date2" + dataNameIndex,anchor:'90%',format:'Y-m-d h:m:s',readOnly:true,listeners:{focus : function() {
                                        calendar(this, 'datetime');
                                    }}}
                                };
                                rowItem.items.push(item1);
                                rowItem.items.push(item2);
                            } else if (data.type == "ComboBox") {
                                var item = {columnWidth:.297 * mult,layout:'form',border:false,labelWidth: 80,
                                    items:{xtype:'jocombobox',fieldLabel:data.label,anchor:'95%',hiddenName:data.name + dataNameIndex,loadUrl:data.loadUrl,hiddenField:"id",editable:false}};
                                rowItem.items.push(item);
                            } else if (data.type == "DateField") {
                                var item = {
                                    columnWidth:.297 * mult,layout:'form',border:false,labelWidth: 80,
                                    items:{xtype:'datefield',fieldLabel:data.label,anchor:'95%',id:data.name + dataNameIndex,format:'Y-m-d',editable:false}
                                };
                                rowItem.items.push(item);
                            } else if (data.type == "Numbers") {
                                var item1 = {columnWidth:.165 * mult,layout:'form',border:false,labelWidth: 80,
                                    items:{xtype:'numberfield',fieldLabel:data.label,anchor:'95%',id:data.name + "_1" + dataNameIndex} };
                                var item2 = {
                                    columnWidth:.13 * mult,layout:'form',border:false,labelWidth: 10,
                                    items:{xtype:'numberfield',fieldLabel:"~",labelSeparator:"",id:data.name + "_2" + dataNameIndex,anchor:'90%'}
                                };
                                rowItem.items.push(item1);
                                rowItem.items.push(item2);
                            }
                            dataNameIndex++;
                        }
                    }
                    if (i == Math.round(queryJson.length / cols + 0.5) - 1) {
                        rowItem.items.push({columnWidth:.06,layout:'fit',border:false,items:{xtype:'button',id:"query",text:"查询",listeners:{click:function(button) {
                            query(button)
                        }}},align:'right'});
                        rowItem.items.push({columnWidth:.06,layout:'fit',border:false,items:{xtype:'button',id:"next",text:"下一条",listeners:{click:function(button) {
                            query(button)
                        }}},align:'right'});
                        rowItem.items.push({columnWidth:.06,layout:'form',border:false,items:{xtype:'button',id:"previous",text:"上一条",listeners:{click:function(button) {
                            query(button)
                        }}}});
                        rowItem.items.push({columnWidth:.06,layout:'form',border:false,items:{xtype:'button',text:"重置",handler:reset}});
                    }
                    tableItem.push(rowItem);
                }
                queryFlag++;
                var height = 26 * Math.round(queryJson.length / cols + 0.5);
                var treeGridQueryForm = new Ext.form.FormPanel({
                    layout:"form",
                    id:'treeGridQueryForm',
                    items:tableItem
                });
                if (!this.tbar) {
                    this.tbar = new Ext.Toolbar({
                        layout:"fit",
                        height:height,
                        renderTo : treeGrid.tbar,   //添加多个工具栏的容器
                        items : [
                            {
                                layout:"form",
                                xtype:"form",
                                id:'treeGridQueryForm',
                                items:tableItem
                            }
                        ]
                    });
                } else {

                }
                if (queryFlag % 2 == 0) {
                    treeGrid.setHeight(treeGrid.getHeight() + height);
                    this.tbar.hide();
                } else {
                    this.tbar.show();
                    treeGrid.setHeight(treeGrid.getHeight() - height);
                }
                treeGrid.doLayout();
            }
        });

        var buttonItems = new Array();

        if (this.queryJson) {
            buttonItems.push(queryButton);
            buttonItems.push(tbfill);
        }

        if (addButton && !modifyButton && !deleteButton) {
            buttonItems.push(tbfill);
            buttonItems.push(addButton);
        } else if (!addButton && modifyButton && !deleteButton) {
            buttonItems.push(tbfill);
            buttonItems.push(modifyButton);
        } else if (!addButton && !modifyButton && deleteButton) {
            buttonItems.push(tbfill);
            buttonItems.push(deleteButton);
        } else if (addButton && modifyButton && !deleteButton) {
            buttonItems.push(tbfill);
            buttonItems.push(addButton);
            buttonItems.push(tbseparator);
            buttonItems.push(modifyButton);
        } else if (addButton && !modifyButton && deleteButton) {
            buttonItems.push(tbfill);
            buttonItems.push(addButton);
            buttonItems.push(tbseparator);
            buttonItems.push(deleteButton);
        } else if (!addButton && modifyButton && deleteButton) {
            buttonItems.push(tbfill);
            buttonItems.push(modifyButton);
            buttonItems.push(tbseparator);
            buttonItems.push(deleteButton);
        } else if (addButton && !modifyButton && deleteButton) {
            buttonItems.push(tbfill);
            buttonItems.push(addButton);
            buttonItems.push(tbseparator);
            buttonItems.push(deleteButton);
        } else if (addButton && modifyButton && deleteButton) {
            buttonItems.push(tbfill);
            buttonItems.push(addButton);
            buttonItems.push(tbseparator);
            buttonItems.push(modifyButton);
            buttonItems.push(tbseparator);
            buttonItems.push(deleteButton);
        }

        if (this.myButtons) {
            buttonItems.push(this.myButtons);
        }

        this.tbar = new Ext.Toolbar({
            autoWidth:true,
            items: [buttonItems]
        });

        var searchUrl = this.searchUrl;
        var tree = this;
        var queryIndex = 0;
        var oldValue;
        var path;
        //query
        function query(button) {
            var conditions = new Object();
            var index = 0;
            for (var i = 0; i < queryJson.length; i++) {
                var condition = new Object();
                condition.name = "";
                condition.entity = "t";
                condition.andOr = "and";
                condition.propertyName = queryJson[i].name;
                if (queryJson[i].type == "String" || queryJson[i].type == "Number") {
                    var data = Ext.getCmp(queryJson[i].name + i).getValue();
                    if (Ext.isEmpty(data)) {
                        continue;
                    }
                    condition.operator = "like '%|%'";
                    condition.firstValue = Ext.getCmp(queryJson[i].name + i).getValue();
                    conditions[index + ""] = (condition);
                    index++;
                } else if (queryJson[i].type == "Date") {
                    var date1 = Ext.getCmp(queryJson[i].name + "_date1" + i).getValue();
                    var date2 = Ext.getCmp(queryJson[i].name + "_date2" + i).getValue();
                    if (Ext.isEmpty(date1) && Ext.isEmpty(date2)) {
                        continue;
                    } else if (!Ext.isEmpty(date1) && !Ext.isEmpty(date2)) {
                        condition.operator = "between";
                        condition.firstValue = Ext.util.Format.date(date1, 'Y-m-d');
                        condition.secondValue = Ext.util.Format.date(date2, 'Y-m-d');
                    } else if (!Ext.isEmpty(date1)) {
                        condition.operator = ">=";
                        condition.firstValue = Ext.util.Format.date(date1, 'Y-m-d');
                    } else if (!Ext.isEmpty(date2)) {
                        condition.operator = "<=";
                        condition.secondValue = Ext.util.Format.date(date2, 'Y-m-d');
                    }
                    condition.type = "Datetime";
                    conditions[index + ""] = (condition);
                    index++;
                } else if (queryJson[i].type == "DateTime") {
                    var date1 = Ext.getCmp(queryJson[i].name + "_date1" + i).getValue();
                    var date2 = Ext.getCmp(queryJson[i].name + "_date2" + i).getValue();
                    if (Ext.isEmpty(date1) && Ext.isEmpty(date2)) {
                        continue;
                    } else if (!Ext.isEmpty(date1) && !Ext.isEmpty(date2)) {
                        condition.operator = "between";
                        condition.firstValue = date1;
                        condition.secondValue = date2;
                    } else if (!Ext.isEmpty(date1)) {
                        condition.operator = ">=";
                        condition.firstValue = date1;
                    } else if (!Ext.isEmpty(date2)) {
                        condition.operator = "<=";
                        condition.secondValue = date2;
                    }
                    condition.type = "Datetime";
                    conditions[index + ""] = (condition);
                    index++;
                } else if (queryJson[i].type == "ComboBox") {
                    var data = Ext.get(queryJson[i].name + i).dom.value;
                    if (Ext.isEmpty(data)) {
                        continue;
                    }
                    condition.operator = "=";
                    condition.type = "ComboBox";
                    condition.firstValue = data;
                    conditions[index + ""] = (condition);
                    index++;
                } else if (queryJson[i].type == "DateField") {
                    var data = Ext.getCmp(queryJson[i].name + i).getValue();
                    if (Ext.isEmpty(data)) {
                        continue;
                    }
                    condition.operator = "=";
                    condition.type = "DateField";
                    condition.firstValue = Ext.util.Format.date(data, 'Y-m-d');
                    conditions[index + ""] = (condition);
                    index++;
                } else if (queryJson[i].type == "Numbers") {
                    var date1 = Ext.getCmp(queryJson[i].name + "_1" + i).getValue();
                    var date2 = Ext.getCmp(queryJson[i].name + "_2" + i).getValue();
                    if (Ext.isEmpty(date1) && Ext.isEmpty(date2)) {
                        continue;
                    } else if (!Ext.isEmpty(date1) && !Ext.isEmpty(date2)) {
                        if (date2 < date1) {
                            var date1Obj = Ext.getCmp(queryJson[i].name + "_1" + i);
                            Ext.Msg.alert("提示", "开始日应该小于结束日，请重新输入！", function() {
                                date1Obj.focus();
                            });
                            return;
                        }
                        condition.operator = "between";
                        condition.firstValue = date1;
                        condition.secondValue = date2;
                    } else if (!Ext.isEmpty(date1)) {
                        condition.operator = ">=";
                        condition.firstValue = date1;
                    } else if (!Ext.isEmpty(date2)) {
                        condition.operator = "<=";
                        condition.secondValue = date2;
                    }
                    condition.type = "Numbers";
                    conditions[index + ""] = (condition);
                    index++;
                }
            }

            if (Ext.encode(conditions).length > 2) {
                if (!Ext.isEmpty(searchUrl)) {
                    var newValue = Ext.encode(conditions);
                    if (button.getId() === 'next' || button.getId() === 'query') {
                        if (button.getId() === 'query') {
                            oldValue = "";
                            queryIndex = 0;
                            path = "";
                        }
                        if (newValue != oldValue) {
                            Ext.Ajax.request({
                                url:searchUrl,
                                params: {conditions: Ext.encode(conditions)},
                                success:function(response) {
                                    path = response.responseText;
                                    if (!Ext.isEmpty(path)) {
                                        var paths = path.split(",");
                                        if (paths[queryIndex].length > 0) {
                                            tree.selectPath(paths[queryIndex]);
                                        }
                                        queryIndex ++;
                                    } else {
                                        Ext.Msg.alert('提示', '不存在该节点');
                                    }
                                    oldValue = newValue;
                                    queryIndex = 0;
                                },
                                failure:function() {
                                    Ext.Msg.alert('提示', '不存在该节点');
                                }
                            });
                        } else {
                            if (!Ext.isEmpty(path)) {
                                var paths = path.split(",");
                                queryIndex++;
                                if (queryIndex < paths.length) {
                                    tree.selectPath(paths[queryIndex]);
                                } else {
                                    Ext.Msg.alert('提示', "已到最后一个节点");
                                }
                            }
                        }
                    } else if (button.getId() === 'previous') {
                        if (!Ext.isEmpty(path)) {
                            var paths = path.split(",");
                            if (queryIndex > 0) {
                                queryIndex--;
                                tree.selectPath(paths[queryIndex]);
                            } else {
                                Ext.Msg.alert('提示', "已到第一个节点");
                            }
                        }
                    }
                }
            }
        }

        var reset = function() {
            for (var i = 0; i < queryJson.length; i++) {
                if (queryJson[i].type == "String" || queryJson[i].type == "Number" || queryJson[i].type == "DateField") {
                    Ext.getCmp(queryJson[i].name + i).setValue("");
                } else if (queryJson[i].type == "Date" || queryJson[i].type == "Datetime") {
                    Ext.getCmp(queryJson[i].name + "_date1" + i).setValue("");
                    Ext.getCmp(queryJson[i].name + "_date2" + i).setValue("");
                } else if (queryJson[i].type == "Numbers") {
                    Ext.getCmp(queryJson[i].name + "_1" + i).setValue("");
                    Ext.getCmp(queryJson[i].name + "_2" + i).setValue("");
                } else if (queryJson[i].type == "ComboBox") {
                    var queryForm = Ext.getCmp('treeGridQueryForm');
                    var combo = queryForm.find("hiddenName", queryJson[i].name + i);
                    combo[0].setValue("");
                }
            }
        };
    },

    addEvent:function() {
        //添加菜单
        if (!Ext.isEmpty(this.contextmenu)) {
            this.on("contextmenu", function(node, e) {
                node.select();
                this.contextmenu.forceLayout = true;
                var menu = this.contextmenu.call(this.contextmenu, node);
                //菜单显示的位置控制
                if (!Ext.isEmpty(menu)) {
                    var height = document.body.clientHeight - 136;
                    if (e.getXY()[1] > height / 2) {
                        var count = 0;
                        var items = menu.items.items;
                        for (var i = 0; i < items.length; i++) {
                            var item = items[i];
                            if (!Ext.isEmpty(item) && item.getXType() == "menuitem") {
                                count++;
                            }
                        }
                        menu.showAt([e.getXY()[0],e.getXY()[1] - count * 26]);
                    } else {
                        menu.showAt(e.getXY());
                    }
                }
            });
        }

        //添加树节点图标
        this.on("load", function(node) {
            var childNodes = node.childNodes;
            var image = this.image;
            var imagePath = this.imagePath;
            Ext.each(childNodes, function(node) {
                if (!Ext.isEmpty(image) && !Ext.isEmpty(imagePath)) {
                    node.attributes["icon"] = imagePath + image[node.attributes["icon"]];
                }
            });
        });
    },

    deleteRecord:function() {
        var treeGrid = this;
        var record = treeGrid.getSelectionModel().getSelectedNode();
        if (!record) {
            Ext.Msg.alert("提示", "请选择其中一行！");
            return;
        }
        if (record.hasChildNodes()) {
            Ext.Msg.alert("提示", "请先删除子节点数据！");
            return;
        }
        var id = record.id;
        var deleteUrl = treeGrid.deleteUrl;
        Ext.Ajax.request({
            url: deleteUrl,
            params: {id:id},
            success: function() {
                Ext.Msg.alert("提示", "操作成功!");
                treeGrid.store.reload();
            },
            failure:function() {
                Ext.Msg.alert("提示", "操作失败!");
            },
            waitMsg: '正在处理数据，请稍后……'
        });
    }
});
Ext.reg('mytreegrid', Ext.ux.tree.MyTreeGrid);

function TreeMenuItem(node, text, action, enable) {
    var disabled = Ext.isEmpty(enable) ? enable : true;
    return new Ext.menu.Item({
        text:text,
        disabled :disabled,
        handler:function(item) {
            action.call(action, node, item);
        }
    });
}