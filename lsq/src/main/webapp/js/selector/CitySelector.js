var CitySelector = {
    getView: function(i, c) {
        var f = new Ext.tree.TreePanel({
            title: "省市选择器",
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
                url: __ctxPath + "/system/treeProvince.do",
                baseParams:{start:0,limit:100}
            }),
            root: new Ext.tree.AsyncTreeNode({
                expanded: true
            }),
            rootVisible: false,
            listeners: {
                "click": function(k) {
                    if (k != null) {
                        var l = Ext.getCmp("CitySelectorGrid");
                        var j = l.getStore();
                        j.proxy.conn.url = __ctxPath + "/system/listCity.do";
                        j.baseParams = {
                            "Q_province.provinceId_L_EQ": k.id == 0 ? '0': k.id
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
                header: "cityId",
                dataIndex: "cityId",
                hidden: true
            },{
                header: "省份",
                dataIndex: "province",
                width: 60
            },
            {
                header: "城市",
                dataIndex: "cityName",
                width: 60
            }]
        });
        var g = new Ext.data.Store({
            proxy: new Ext.data.HttpProxy({
                url: __ctxPath + "/system/listCity.do"
            }),
            reader: new Ext.data.JsonReader({
                root: "result",
                totalProperty: "totalCounts",
                id: "cityId",                
                fields: [{
                    name: "cityId",
                    type: "int"
                },
                "cityName",{name:"provinceId",mapping:"province.provinceId"},{name:"province",mapping:"province.provinceName"}]
            }),
            sortInfo :{field: "sort", direction: "ASC"},
            remoteSort: true
        });
        var d = new Ext.grid.GridPanel({
            id: "CitySelectorGrid",
            width: 400,
            height: 300,
            region: "center",
            title: "城市列表",
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
                limit: 12
            }
        });
        var a = new Ext.FormPanel({
            width: 400,
            region: "north",
            id: "CityForm",
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
                text: "城市名称"
            },
            {
                xtype: "textfield",
                name: "Q_cityName_S_LK"
            },
            {
                xtype: "button",
                text: "查询",
                iconCls: "search",
                handler: function() {
                    var j = Ext.getCmp("CityForm");
                    var k = Ext.getCmp("CitySelectorGrid");
                    if (j.getForm().isValid()) {
                        j.getForm().submit({
                            waitMsg: "正在提交查询",
                            url: __ctxPath + "/system/listCity.do",
                            success: function(m, n) {
                                var l = Ext.util.JSON.decode(n.response.responseText);
                                k.getStore().loadData(l);
                            }
                        });
                    }
                }
            }]
        });
        var e = new Ext.Window({
            title: "选择城市",
            iconCls: "menu-City-manage",
            width: 630,
            height: 380,
            layout: "border",
            border: false,
            items: [f, a, d],
            modal: true,
            buttonAlign: "center",
            buttons: [{
                iconCls: "btn-ok",
                text: "确定",
                handler: function() {
                    var l = Ext.getCmp("CitySelectorGrid");
                    var m = l.getSelectionModel().getSelections();
                    var cityId = "";
                    var cityName = "";
                    var provinceId="";
                    var provinceName="";
                   
                    for (var k = 0; k < m.length; k++) {
                        if (k > 0) {
                            cityId += ",";
                            cityName += ",";
                            provinceId=",";
		                    provinceName=",";
		                   
                        }
                        cityId += m[k].data.cityId;
                        cityName += m[k].data.cityName;
                        provinceId += m[k].data.provinceId;
                        provinceName += m[k].data.province;
                    }
                    if (i != null) {
                        i.call(this, cityId, cityName,provinceId,provinceName);
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