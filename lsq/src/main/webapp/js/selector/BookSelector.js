var BookSelector = {
    getView: function(i, c) {
        var f = new Ext.tree.TreePanel({
            title: "图书显示",
            region: "west",
            width: 180,
            height: 300,
            split: true,
            collapsible: true,
            autoScroll: true,
            bbar: new Ext.Toolbar({
                items: [{
                    xtype: "button",
                    iconCls: "btn-refresh",
                    text: "刷新",
                    handler: function() {
                        f.root.reload();
                    }
                },
                {
                    xtype: "button",
                    text: "展开",
                    iconCls: "btn-expand",
                    handler: function() {
                        f.expandAll();
                    }
                },
                {
                    xtype: "button",
                    text: "收起",
                    iconCls: "btn-collapse",
                    handler: function() {
                        f.collapseAll();
                    }
                }]
            }),
            loader: new Ext.tree.TreeLoader({
                url: __ctxPath + "/admin/treeBookType.do"
            }),
            root: new Ext.tree.AsyncTreeNode({
                expanded: true
            }),
            rootVisible: false,
            listeners: {
                "click": function(k) {
                    if (k != null) {
                        var l = Ext.getCmp("BookSelectorGrid");
                        var j = l.getStore();
                        j.proxy.conn.url = __ctxPath + "/admin/listBook.do";
                        j.baseParams = {
                            "Q_bookType.typeId_L_EQ": k.id == 0 ? null: k.id
                        };
                        j.load({
                            params: {
                                start: 0,
                                limit: 12
                            }
                        });
                    }
                }
            }
        });
        var b = null;
        if (c) {
            var b = new Ext.grid.CheckboxSelectionModel({
                singleSelect: true
            });
        } else {
            b = new Ext.grid.CheckboxSelectionModel();
        }
        var h = new Ext.grid.ColumnModel({
            columns: [b, new Ext.grid.RowNumberer(), {
                header: "typeId",
                dataIndex: "typeId",
                hidden: true
            },{
                header: "档案编号",
                dataIndex: "isbn",
                width: 60
            },
            {
                header: "档案名称",
                dataIndex: "bookName",
                width: 60
            },{
                header: "库存",
                dataIndex: "leftAmount",
                width: 60,
                renderer:function(value){
                	return value==0?'<span style="color:red;">'+value+'</span>':value;
                
                }
            }]
        });
        var g = new Ext.data.Store({
            proxy: new Ext.data.HttpProxy({
                url: __ctxPath + "/admin/listBook.do"
            }),
            baseParams:{
           			"Q_state_N_EQ":0          		
           	},
            reader: new Ext.data.JsonReader({
                root: "result",
                totalProperty: "totalCounts",
                id: "bookId",
                fields: [{
                    name: "bookId",
                    type: "int"
                },
                "bookName","isbn",{name:"bookTypeName",mapping:"bookType.typeName"},"location","department","leftAmount","filePath","fileName"]
            }),
            remoteSort: true
        });
        var d = new Ext.grid.GridPanel({
            id: "BookSelectorGrid",
            width: 400,
            height: 300,
            region: "center",
            title: "图书列表",
            store: g,
            shim: true,
            trackMouseOver: true,
            disableSelection: false,
            loadMask: true,
            cm: h,
            sm: b,
            viewConfig: {
                forceFit: true,
                enableRowBody: false,
                showPreview: false
            },
            bbar: new Ext.PagingToolbar({
                pageSize: 25,
                store: g,
                displayInfo: true,
                displayMsg: "当前显示从{0}至{1}， 共{2}条记录",
                emptyMsg: "当前没有记录"
            })
        });
        g.load({
            params: {
                start: 0,
                limit: 25
            }
        });
        var a = new Ext.FormPanel({
            width: 400,
            region: "north",
            id: "BookForm",
            height: 40,
            frame: false,
            border: false,
            layout: "hbox",
            layoutConfig: {
                padding: "5",
                align: "middle"
            },
            defaults: {
                xtype: "label",
                margins: {
                    top: 0,
                    right: 4,
                    bottom: 4,
                    left: 4
                }
            },
            items: [{
                text: "请输入查询条件:"
            },
            {
                text: "图书名称"
            },
            {
                xtype: "textfield",
                name: "Q_bookName_S_LK"
            },
            {
                xtype: "textfield",
                name: "Q_state_N_EQ",
                hidden:true,
                value:0
            },
            {
                xtype: "button",
                text: "查询",
                iconCls: "search",
                handler: function() {
                    var j = Ext.getCmp("BookForm");
                    var k = Ext.getCmp("BookSelectorGrid");
                    if (j.getForm().isValid()) {
                        j.getForm().submit({
                            waitMsg: "正在提交查询",
                            url: __ctxPath + "/admin/listBook.do",
                            success: function(m, n) {
                                var l = Ext.util.JSON.decode(n.response.responseText);
                                k.getStore().loadData(l);
                            }
                        });
                    }
                }
            }]
        });
        var tab1GroupMgr = new Ext.WindowGroup();
		//前置窗口
		tab1GroupMgr.zseed=99999;
        var e = new Ext.Window({
            title: "选择图书",
            iconCls: "menu-book-manage",
            width: 630,
            height: 380,
            layout: "border",
            manager: tab1GroupMgr,
            border: false,
            items: [f, a, d],
            modal: true,
            buttonAlign: "center",
            buttons: [{
                iconCls: "btn-ok",
                text: "确定",
                handler: function() {
                    var l = Ext.getCmp("BookSelectorGrid");
                    var m = l.getSelectionModel().getSelections();
                    var bookId = "";
                    var bookName = "";
                    var isbn="";
                    var bookType="";
                    var location="";
                    var department="";
                    var leftAmount="";
                    var filePath="";
                    var fileName="";
                    for (var k = 0; k < m.length; k++) {
                    	if(m[k].data.leftAmount<=0){
                    		Ext.ux.Toast.msg("信息提示", "您想借阅的档案库存不足！");
                    		return;
                    		
                    	}
                        if (k > 0) {
                            bookId += ",";
                            bookName += ",";
                            isbn=",";
		                    bookType=",";
		                    location=",";
		                    department=",";
		                    leftAmount=",";
		                    filePath=",";
		                    fileName=",";
                        }
                        bookId += m[k].data.bookId;
                        bookName += m[k].data.bookName;
                        isbn += m[k].data.isbn;
                        bookType += m[k].data.bookTypeName;
                        location += m[k].data.location;
                        department += m[k].data.department;
                        leftAmount += m[k].data.leftAmount;
                        filePath += m[k].data.filePath;
                        fileName += m[k].data.fileName;
                    }
                    if (i != null) {
                        i.call(this, bookId, bookName,isbn,bookType,location,department,leftAmount,filePath,fileName);
                    }
                    e.close();
                }
            },
            {
                text: "取消",
                iconCls: "btn-cancel",
                handler: function() {
                    e.close();
                }
            }]
        });
        return e;
    }
};