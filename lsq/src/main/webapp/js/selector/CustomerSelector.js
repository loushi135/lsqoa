var CustomerSelector = {
    getView: function(i, d) {
        var c = new Ext.tree.TreePanel({
            title: "客户地区",
            region: "west",
            width: 180,
            height: 300,
            split: true,
            collapsible: true,
            autoScroll: true,
            bbar: new Ext.Toolbar({
                items: [{
                    xtype: "button",
                    text: "展开",
                    iconCls: "btn-expand",
                    handler: function() {
                        c.expandAll();
                    }
                },
                {
                    xtype: "button",
                    text: "收起",
                    iconCls: "btn-collapse",
                    handler: function() {
                        c.collapseAll();
                    }
                }]
            }),
            loader: new Ext.tree.TreeLoader({
                url: __ctxPath + "/system/treeRegion.do"
            }),
            root: new Ext.tree.AsyncTreeNode({
                expanded: true
            }),
            rootVisible: false,
            listeners: {
                "click": function(l) {
                    if (l != null) {
                        var k = Ext.getCmp("CustomerSelectorGrid");
                        var j = k.getStore();
                        j.proxy.conn.url = __ctxPath + "/customer/listCustomerNew.do";
                        
                        if(l.text=='中国'){
                        	j.baseParams = {};
                        }else if(!l.leaf){
                        	j.baseParams = {
                                "Q_province.provinceName_S_LK": l.text
                            };
                        }else if(l.leaf){
                        	j.baseParams = {
                                "Q_city.cityName_S_LK": l.text
                            };
                        }
                      	
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
        if (d) {
            var b = new Ext.grid.CheckboxSelectionModel({
                singleSelect: true
            });
        } else {
            b = new Ext.grid.CheckboxSelectionModel();
        }
        var h = new Ext.grid.ColumnModel({
            columns: [b, new Ext.grid.RowNumberer(), {
                header: "id",
                dataIndex: "id",
                hidden: true
            },
            {
                header: "客户编号",
                dataIndex: "code",
                width: 60
            },
            {
                header: "客户名称",
                dataIndex: "name",
                width: 60
            }]
        });
        var g = new Ext.data.Store({
            proxy: new Ext.data.HttpProxy({
                url: __ctxPath + "/customer/listCustomerNew.do"
            }),
            reader: new Ext.data.JsonReader({
                root: "result",
                totalProperty: "totalCounts",
                id: "id",
                fields: [{
                    name: "id",
                    type: "int"
                },
                'code', 'name']
            })
        });
        var e = new Ext.grid.GridPanel({
            id: "CustomerSelectorGrid",
            width: 400,
            height: 300,
            region: "center",
            title: "客户列表",
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
            id: "CustomerForm",
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
                text: "客户编号"
            },
            {
                xtype: "textfield",
                name: "Q_code_S_LK"
            },
            {
                text: "客户名称"
            },
            {
                xtype: "textfield",
                name: "Q_name_S_LK"
            },
            {
                xtype: "button",
                text: "查询",
                iconCls: "search",
                handler: function() {
                    var j = Ext.getCmp("CustomerForm");
                    var k = Ext.getCmp("CustomerSelectorGrid");
                    if (j.getForm().isValid()) {
                        j.getForm().submit({
                            waitMsg: "正在提交查询",
                            url: __ctxPath + "/customer/listCustomerNew.do",
                            success: function(m, n) {
                                var l = Ext.util.JSON.decode(n.response.responseText);
                                k.getStore().loadData(l);
                            }
                        });
                    }
                }
            }]
        });
        var f = new Ext.Window({
            title: "客户选择器",
            iconCls: "menu-customerView",
            width: 630,
            height: 380,
            layout: "border",
            border: false,
            items: [c, a, e],
            modal: true,
            buttonAlign: "center",
            buttons: [{
                iconCls: "btn-ok",
                text: "确定",
                handler: function() {
                    var k = Ext.getCmp("CustomerSelectorGrid");
                    var n = k.getSelectionModel().getSelections();
                    var m = "";
                    var l = "";
                    for (var j = 0; j < n.length; j++) {
                        if (j > 0) {
                            m += ",";
                            l += ",";
                        }
                        m += n[j].data.code;
                        l += n[j].data.name;
                    }
                    if (i != null) {
                        i.call(this, m, l);
                    }
                    f.close();
                }
            },
            {
                text: "取消",
                iconCls: "btn-cancel",
                handler: function() {
                    f.close();
                }
            }]
        });
        return f;
    }
};