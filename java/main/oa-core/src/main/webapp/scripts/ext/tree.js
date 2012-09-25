var lastSelectedNode;
function TreePanel(root,treeDiv,url,imageUrl,title,checked){
    this.init(root,treeDiv,url,imageUrl,checked);
    this.tree=new Ext.tree.TreePanel({
        renderTo:this.div,
        id:"tree",
        split: true,
        height :document.body.clientHeight - 40,
        width :document.body.clientWidth,
        autoScroll: true,
        animate : false,
        root:this.root,
        loader:this.loader,
        title:title,
        frame:false

    });

    this.tree.on("load",function(node){
        var childNodes = node.childNodes;
        Ext.each(childNodes,function(node){
            if(typeof addCheckedBox=="function")
            {
                addCheckedBox(node);
            }
        });
    });
    window.onresize = function() {
        var tree = Ext.getCmp("tree");
        tree.setHeight(0);
        tree.setWidth(0);
        tree.setWidth(document.body.clientWidth);
        tree.setHeight(document.body.clientHeight - 40);
    };
}
/**
 * treepanel 初始化
 * @param root
 * @param div
 * @param url
 */
TreePanel.prototype.init =function(root,div,url,imageUrl,checked){
    this.initRoot(root);
    this.initRender(div);
    this.initimageUrl(imageUrl);
    this.initLoader(url,checked);
};
/**
 * @param root
 */
TreePanel.prototype.initRoot = function(root){
    if(root)
    {
        this.root=root;
    }
    else
    {
        this.root=new Ext.tree.AsyncTreeNode({
            id:"root",
            icon:"../../themes/sea/system/icons/s.gif",
            text:"根节点"});
    }
};

TreePanel.prototype.initRender = function(div){
    if(div)
    {
        this.div=div;
    }
    else
    {
        this.div="treeDiv";
    }
};

TreePanel.prototype.initLoader =function(url,checked){
    if(url)
    {
        this.loader=new Ext.tree.TreeLoader({
            url:url
        });
        var imageUrl = this.imageUrl;
        this.loader.on("beforeload", function(loader,node){
            loader.baseParams.id = node.attributes.id;
            loader.baseParams.imageUrl = imageUrl;
            if(checked&&checked==true){
                //                loader.baseAttrs={checked:false};
            }
        }, this.loader);
    }
};




TreePanel.prototype.initimageUrl =function(imageUrl){
    if(imageUrl)
    {
        this.imageUrl = imageUrl;
    }
};

TreePanel.prototype.getTree = function(){
    return this.tree;
};

TreePanel.prototype.showMenu = function(){
    var contextmenu = this.contextmenu;
    this.tree.on("contextmenu",function(node,e){
        contextmenu(node,e);
    });
};

TreePanel.prototype.setMenuStatus = function(contextmenu){
    if(contextmenu)
    {
        this.tree.on("contextmenu",function(node,e){
            contextmenu(node,e);
        });
    }
};

TreePanel.prototype.setClickNodeFunction = function(clickFunction){
    this.tree.on("click",function(node,e){
        clickFunction(node,e);
    });
};

TreePanel.prototype.setExpandNodeFunction = function(doExpand){
     this.tree.on("beforeexpandnode", function(node){
//         alert($("tree"));
//         Ext.MessageBox.alert("kkk",node.id);
         doExpand(node);

     });
};

function treeImage(url,imageName){
    var imageUrl = new Array();
    if(typeof imageName=="object"){
        for(var i=0;i<imageName.length;i++){
            imageUrl[i] = url + imageName[i];
        }
    }
    return imageUrl;
}

//树菜单
function TreeMenu(){
    return new Ext.menu.Menu({});
}

//菜单节点
function MenuItem(node,text,treeAction,confirm){
    var item = new Ext.menu.Item({text:text,handler:function(){
        lastSelectedNode=node;
        if(typeof treeAction=="function"){
            treeAction(node);
            return;
        }
        if(typeof TreeAction=="function"&&treeAction){
            if((typeof confirm=="boolean")&&confirm){
                Ext.MessageBox.confirm('提示框', '您确定要进行'+text+'操作？', function(btn) {
                    if(text.indexOf("删除")==0  && node.hasChildNodes())
                    {
                        Ext.MessageBox.minWidth = 150;
                        Ext.MessageBox.alert("提示框", "该节点下已经存在子节点，不能删除！");
                        return;
                    }
                    if (btn == 'yes')
                    {
                        TreeAction(treeAction);
                    }
                });
            }else{
                TreeAction(treeAction);
            }
        }
    }});
    return item;
}

//刷新当前节点下的所有子节点
function updateChildren(node){
    try{
    if(!node.isLeaf()){
        node.reload();
    }else{
        //解决新增时无法刷新节点问题
        if(node.parentNode){
           node.parentNode.reload();
        }
    }
    }catch(E){}
}

//刷新当前节点及其同层节点
function updateParent(node){try{
    var id = node.id;
    if(node.parentNode)
    {
        var parent = node.parentNode;
        var nextNode = node.nextSibling;
        var preNode = node.previousSibling;
        parent.reload(function(){
            var childNode=parent.findChild("id",id);
            if(childNode){
            }else if(nextNode){
                childNode=parent.findChild("id",nextNode.id);
            }else if(preNode){
                childNode=parent.findChild("id",preNode.id);
            }else{
                childNode = parent;
            }
             //不要选中和触发点击事件，否则影响页面跳转
            childNode.select();
//            if(onClick&&typeof onClick=="function"){
//                onClick(childNode);
//            }
        });
    }
    else
    {
        node.reload();
        node.select();
    }
}catch(E){}
}

//右边页面跳转
function TreeAction(url){
    if(parent.main_frame){
        parent.main_frame.location=url;
    }
    else if(parent.parent.main_frame){
        parent.parent.main_frame.location=url;
    }
}



