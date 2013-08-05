Ext.ns("CustomerView");
CustomerView = Ext.extend(Ext.Panel, {
    searchPanel: null,
    gridPanel: null,
    store: null,
    topbar: null,
    constructor: function(a) {
        Ext.applyIf(this, a);
        this.initUIComponents();
        CustomerView.superclass.constructor.call(this, {
            id: "CustomerView",
            title: "客户列表",
            iconCls: "menu-customerView",
            region: "center",
            layout: "border",
            items: [this.searchPanel, this.gridPanel]
        });
    },
    initUIComponents: function() {
        this.searchPanel = new Ext.FormPanel({
            id: "CustomerSearchForm",
            height: 35,
            region: "north",
            frame: false,
            border: false,
            layout: "hbox",
            layoutConfig: {
                padding: "5",
                align: "middle"
            },
            defaults: {
                style: "padding:0px 5px 0px 5px;",
                border: false,
                anchor: "98%,98%",
                xtype: "label"
            },
            items: [{
                text: "查询条件:"
            },
            {
                text: "行业 "
            },
            {
                xtype: "textfield",
                name: "Q_industryType_S_LK",
                width: 80
            },
            {
                text: "客户类型"
            },
            {
                hiddenName: "Q_customerType_SN_EQ",
                width: 80,
                xtype: "combo",
                mode: "local",
                editable: false,
                triggerAction: "all",
                store: [["", "　"], ["1", "正式客户"], ["2", "重要客户"], ["3", "潜在客户"], ["4", "无效客户"]]
            },
            {
                text: "客户名称 "
            },
            {
                xtype: "textfield",
                name: "Q_customerName_S_LK",
                width: 80
            },
            {
                text: "负责人"
            },
            {
                xtype: "textfield",
                name: "Q_customerManager_S_LK",
                width: 80
            },
            {
                xtype: "button",
                text: "查询",
                iconCls: "search",
                handler: function() {
                    var c = Ext.getCmp("CustomerSearchForm");
                    var d = Ext.getCmp("CustomerGrid");
                    if (c.getForm().isValid()) {
                        c.getForm().submit({
                            waitMsg: "正在提交查询",
                            url: __ctxPath + "/customer/listCustomer.do",
                            success: function(f, g) {
                                var e = Ext.util.JSON.decode(g.response.responseText);
                                d.getStore().loadData(e);
                            }
                        });
                    }
                }
            }]
        });
        this.store = new Ext.data.Store({
            proxy: new Ext.data.HttpProxy({
                url: __ctxPath + "/customer/listCustomer.do"
            }),
            reader: new Ext.data.JsonReader({
                root: "result",
                totalProperty: "totalCounts",
                id: "id",
                fields: [{
                    name: "customerId",
                    type: "int"
                },
                "customerNo", "customerType", "companyScale", "customerName", "customerManager", "phone", "fax", "site", "email", "state", "city", "zip", "address", "registerFun", "turnOver", "currencyUnit", "otherDesc", "principal", "openBank", "accountsNo", "taxNo", "notes"]
            }),
            remoteSort: true
        });
        this.store.setDefaultSort("customerId", "desc");
        this.store.load({
            params: {
                start: 0,
                limit: 25
            }
        });
        var b = new Ext.grid.CheckboxSelectionModel();
        var a = new Ext.grid.ColumnModel({
            columns: [b, new Ext.grid.RowNumberer(), {
                header: "customerId",
                dataIndex: "customerId",
                hidden: true
            },
            {
                header: "客户类型",
                width: 55,
                dataIndex: "customerType",
                renderer: function(c) {
                    var d = "";
                    if (c == "1") {
                        d += '<img title="正式客户" src="' + __ctxPath + '/images/flag/customer/effective.png"/>';
                    } else {
                        if (c == "2") {
                            d += '<img title="重要客户" src="' + __ctxPath + '/images/flag/mail/important.png"/>';
                        } else {
                            if (c == "3") {
                                d += '<img title="潜在客户" src="' + __ctxPath + '/images/flag/customer/potential.png"/>';
                            } else {
                                d += '<img title="无效客户" src="' + __ctxPath + '/images/flag/customer/invalid.png"/>';
                            }
                        }
                    }
                    return d;
                }
            },
            {
                header: "客户号",
                width: 130,
                dataIndex: "customerNo"
            },
            {
                header: "客户名称",
                width: 130,
                dataIndex: "customerName"
            },
            {
                header: "负责人",
                width: 60,
                dataIndex: "customerManager"
            },
            {
                header: "电话",
                width: 80,
                dataIndex: "phone"
            },
            {
                header: "邮箱",
                width: 140,
                dataIndex: "email"
            },
            {
                header: "管理",
                dataIndex: "customerId",
                width: 150,
                sortable: false,
                renderer: function(g, f, c, j, e) {
                    var i = c.data.customerId;
                    var d = c.data.customerName;
                    var h = "";
                    if (isGranted("_CustomerDel")) {
                        h = '<button title="删除" value=" " class="btn-del" onclick="CustomerView.remove(' + i + ')">&nbsp</button>';
                    }
                    if (isGranted("_CustomerEdit")) {
                        h += '&nbsp;<button title="编辑" value=" " class="btn-edit" onclick="CustomerView.edit(' + i + ')">&nbsp</button>';
                    }
                    if (isGranted("_CusLinkmanAdd")) {
                        h += '&nbsp;<button title="添加联系人" value="" class="btn-addCusLinkman" onclick="CustomerView.addLinkman(' + i + ')">&nbsp</button>';
                    }
                    if (isGranted("_CusLinkmanQuery")) {
                        h += '&nbsp;<button title="管理联系人" value="" class="btn-editCusLinkman" onclick="CustomerView.editLinkman(' + i + "," + j + ')">&nbsp</button>';
                    }
                    return h;
                }
            }],
            defaults: {
                sortable: true,
                menuDisabled: false,
                width: 100
            }
        });
        this.topbar = new Ext.Toolbar({
            id: "CustomerFootBar",
            height: 30,
            bodyStyle: "text-align:left",
            items: []
        });
        if (isGranted("_CustomerAdd")) {
            this.topbar.add(new Ext.Button({
                iconCls: "btn-add",
                text: "添加客户",
                handler: function() {
                    new CustomerForm().show();
                }
            }));
        }
        if (isGranted("_CustomerDel")) {
            this.topbar.add(new Ext.Button({
                iconCls: "btn-del",
                text: "删除客户",
                handler: function() {
                    var e = Ext.getCmp("CustomerGrid");
                    var c = e.getSelectionModel().getSelections();
                    if (c.length == 0) {
                        Ext.ux.Toast.msg("信息", "请选择要删除的记录！");
                        return;
                    }
                    var f = Array();
                    for (var d = 0; d < c.length; d++) {
                        f.push(c[d].data.customerId);
                    }
                    CustomerView.remove(f);
                }
            }));
        }
        if (isGranted("_CustomerSendMail")) {
            this.topbar.add(new Ext.Button({
                xtype: "button",
                text: "发送邮件",
                hidden:true,
                iconCls: "btn-mail_send",
                handler: function() {
                    var e = Ext.getCmp("CustomerGrid");
                    var c = e.getSelectionModel().getSelections();
                    if (c.length == 0) {
                        Ext.ux.Toast.msg("信息", "请选择要发送邮件的客户！");
                        return;
                    }
                    var f = Array();
                    var g = Array();
                    for (var d = 0; d < c.length; d++) {
                        f.push(c[d].data.customerId);
                        g.push(c[d].data.customerName);
                    }
                    new SendMailForm({
                        ids: f,
                        names: g,
                        type: 0
                    }).show();
                }
            }));
        }
        this.gridPanel = new Ext.grid.GridPanel({
            id: "CustomerGrid",
            region: "center",
            tbar: this.topbar,
            store: this.store,
            trackMouseOver: true,
            disableSelection: false,
            loadMask: true,
            autoHeight: true,
            cm: a,
            sm: b,
            viewConfig: {
                forceFit: true,
                enableRowBody: false,
                showPreview: false
            },
            bbar: new Ext.PagingToolbar({
                pageSize: 25,
                store: this.store,
                displayInfo: true,
                displayMsg: "当前显示从{0}至{1}， 共{2}条记录",
                emptyMsg: "当前没有记录"
            })
        });
        this.gridPanel.addListener("rowdblclick",
        function(d, c, f) {
            d.getSelectionModel().each(function(e) {
                if (isGranted("_CustomerEdit")) {
                    CustomerView.edit(e.data.customerId);
                }
            });
        });
    }
});
CustomerView.remove = function(b) {
    var a = Ext.getCmp("CustomerGrid");
    Ext.Msg.confirm("信息确认", '删除客户,则该客户下的<font color="red">联系人、</font><font color="red">交往信息</font>和<font color="red">项目</font>也将删除,您确认要删除该记录吗？',
    function(c) {
        if (c == "yes") {
            Ext.Ajax.request({
                url: __ctxPath + "/customer/multiDelCustomer.do",
                params: {
                    ids: b
                },
                method: "post",
                success: function() {
                    Ext.ux.Toast.msg("信息提示", "成功删除所选记录！");
                    a.getStore().reload({
                        params: {
                            start: 0,
                            limit: 25
                        }
                    });
                }
            });
        }
    });
};
CustomerView.edit = function(a) {
    new CustomerForm({
        customerId: a
    }).show();
};
CustomerView.addLinkman = function(a) {
    new CusLinkmanForm().show();
    Ext.getCmp("customerId").setValue(a);
    Ext.getCmp("custoemrSelect").hide();
    Ext.getCmp("CusLinkmanForm").doLayout();
};
CustomerView.editLinkman = function(h, d) {
    var a = Ext.getCmp("CustomerGrid").getStore().getAt(d).data.customerName;
    var b = new Ext.grid.CheckboxSelectionModel();
    var g = new Ext.grid.ColumnModel({
        columns: [b, new Ext.grid.RowNumberer(), {
            header: "linkmanId",
            dataIndex: "linkmanId",
            hidden: true
        },
        {
            header: "主联系人",
            dataIndex: "isPrimary",
            width: 45,
            renderer: function(j) {
                if (j == "1") {
                    return '<img title="主要联系人" src="' + __ctxPath + '/images/flag/mail/important.png"/>';
                }
            }
        },
        {
            header: "姓名",
            width: 100,
            dataIndex: "fullname"
        },
        {
            header: "管理",
            dataIndex: "linkmanId",
            width: 100,
            sortable: false,
            renderer: function(m, l, j, p, k) {
                var o = j.data.linkmanId;
                var n = "";
                if (isGranted("_CusLinkmanDel")) {
                    n = '<button title="删除" value=" " class="btn-del" onclick="CustomerView.removeCusLinkman(' + o + ')">&nbsp;</button>';
                }
                if (isGranted("_CusLinkmanEdit")) {
                    n += '&nbsp;<button title="编辑" value=" " class="btn-edit" onclick="CusLinkmanView.edit(' + o + ')">&nbsp</button>';
                }
                return n;
            }
        }],
        defaults: {
            sortable: true,
            menuDisabled: false,
            width: 100
        }
    });
    var f = new Ext.data.Store({
        proxy: new Ext.data.HttpProxy({
            url: __ctxPath + "/customer/listCusLinkman.do"
        }),
        baseParams: {
            "Q_customer.customerId_L_EQ": h
        },
        reader: new Ext.data.JsonReader({
            root: "result",
            totalProperty: "totalCounts",
            id: "id",
            fields: [{
                name: "linkmanId",
                type: "int"
            },
            "fullname", "isPrimary"]
        }),
        remoteSort: true
    });
    f.setDefaultSort("isPrimary", "desc");
    f.load({
        params: {
            start: 0,
            limit: 25
        }
    });
    var e = new Ext.Toolbar({
        id: "CusLinkmanFootBar",
        height: 30,
        bodyStyle: "text-align:left",
        items: []
    });
    if (isGranted("_CusLinkmanAdd")) {
        e.add(new Ext.Button({
            iconCls: "btn-add",
            text: "添加联系人",
            handler: function() {
                CustomerView.addLinkman(h);
            }
        }));
    }
    if (isGranted("_CusLinkmanDel")) {
        e.add(new Ext.Button({
            iconCls: "btn-del",
            text: "删除联系人",
            handler: function() {
                CustomerView.deleteCusLinkman();
            }
        }));
    }
    var i = new Ext.grid.GridPanel({
        id: "ManageLinkmanGrid",
        tbar: e,
        store: f,
        trackMouseOver: true,
        disableSelection: false,
        loadMask: true,
        height: 299,
        width: 500,
        cm: g,
        sm: b,
        viewConfig: {
            forceFit: true,
            enableRowBody: false,
            showPreview: false
        },
        bbar: new Ext.PagingToolbar({
            pageSize: 25,
            store: f,
            displayInfo: true,
            displayMsg: "当前显示从{0}至{1}， 共{2}条记录",
            emptyMsg: "当前没有记录"
        }),
        listeners: {
            rowdblclick: function(k, j, l) {
                k.getSelectionModel().each(function(m) {
                    if (isGranted("_CusLinkmanEdit")) {
                        new CusLinkmanForm({
                            linkmanId: m.data.linkmanId
                        }).show();
                    }
                });
            }
        }
    });
    var c = new Ext.Window({
        id: "ManageCusLinkmanWin",
        title: a + "-联系人管理",
        iconCls: "menu-cusLinkman",
        border: false,
        modal: true,
        maximizable: true,
        layout: "anchor",
        plain: true,
        bodyStyle: "padding:5px;",
        buttonAlign: "center",
        items: [i],
        buttons: [{
            text: "关闭",
            iconCls: "btn-del",
            handler: function() {
                c.close();
            }
        }]
    });
    c.show();
};
CustomerView.deleteCusLinkman = function() {
    var c = Ext.getCmp("ManageLinkmanGrid");
    var a = c.getSelectionModel().getSelections();
    if (a.length == 0) {
        Ext.ux.Toast.msg("信息", "请选择要删除的记录！");
        return;
    }
    var d = Array();
    for (var b = 0; b < a.length; b++) {
        d.push(a[b].data.linkmanId);
    }
    CustomerView.removeCusLinkman(d);
};
CustomerView.removeCusLinkman = function(b) {
    var a = Ext.getCmp("ManageLinkmanGrid");
    Ext.Msg.confirm("信息确认", "您确认要删除该记录吗？",
    function(c) {
        if (c == "yes") {
            Ext.Ajax.request({
                url: __ctxPath + "/customer/multiDelCusLinkman.do",
                params: {
                    ids: b
                },
                method: "post",
                success: function() {
                    Ext.ux.Toast.msg("信息提示", "成功删除所选记录！");
                    if (a != null) {
                        a.getStore().reload({
                            params: {
                                start: 0,
                                limit: 25
                            }
                        });
                    }
                }
            });
        }
    });
};