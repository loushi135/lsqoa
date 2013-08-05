Ext.ns("CalendarPlanView");
var CalendarPlanView = function() {
    return new Ext.Panel({
        id: "CalendarPlanView",
        layout: "anchor",
        iconCls: "menu-cal-plan-view",
        title: "日程列表",
        items: [new Ext.FormPanel({
            frame: false,
            defaults: {
                border: false
            },
            id: "CalendarPlanSearchForm",
            bodyStyle: "padding:5px",
            items: [{
                layout: "column",
                defaults: {
                    border: false
                },
                items: [{
                    columnWidth: 0.325,
                    layout: "form",
                    defaults: {
                        border: false
                    },
                    items: [{
                        xtype: "datetimefield",
                        fieldLabel: "开始时间",
                        name: "Q_startTime_D_GT",
                        format: "Y-m-d H:i:s"
                    },
                    {
                        xtype: "textfield",
                        fieldLabel: "内容",
                        name: "Q_content_S_LK"
                    }]
                },
                {
                    columnWidth: 0.325,
                    layout: "form",
                    defaults: {
                        border: false
                    },
                    items: [{
                        xtype: "datetimefield",
                        fieldLabel: "结束时间",
                        format: "Y-m-d H:i:s",
                        name: "Q_endTime_D_GT"
                    },
                    {
                        xtype: "textfield",
                        fieldLabel: "分配人名",
                        name: "Q_assignerName_S_LK"
                    }]
                },
                {
                    columnWidth: 0.2,
                    layout: "form",
                    defaults: {
                        border: false
                    },
                    items: [{
                        xtype: "combo",
                        fieldLabel: "紧急程度",
                        triggerAction: "all",
                        hiddenName: "Q_urgent_SN_EQ",
                        editable: false,
                        store: [["0", "一般"], ["1", "重要"], ["2", "紧急"]],
                        anchor: "95%"
                    },
                    {
                        xtype: "combo",
                        fieldLabel: "状态",
                        triggerAction: "all",
                        hiddenName: "Q_status_SN_EQ",
                        editable: false,
                        store: [["0", "未完成 "], ["1", "完成"]],
                        anchor: "95%"
                    }]
                },
                {
                    columnWidth: 0.15,
                    defaults: {
                        border: false
                    },
                    items: [{
                        xtype: "button",
                        text: "查询",
                        iconCls: "search",
                        handler: function() {
                            var a = Ext.getCmp("CalendarPlanSearchForm");
                            var b = Ext.getCmp("CalendarPlanGrid");
                            if (a.getForm().isValid()) {
                                a.getForm().submit({
                                    waitMsg: "正在提交查询",
                                    url: __ctxPath + "/task/listCalendarPlan.do",
                                    success: function(d, e) {
                                        var c = Ext.util.JSON.decode(e.response.responseText);
                                        b.getStore().loadData(c);
                                    }
                                });
                            }
                        }
                    },
                    {
                        xtype: "button",
                        text: "重置",
                        iconCls: "btn-reseted",
                        handler: function() {
                            var a = Ext.getCmp("CalendarPlanSearchForm");
                            a.getForm().reset();
                        }
                    }]
                }]
            }]
        }), this.setup()]
    });
};
CalendarPlanView.prototype.setup = function() {
    return this.grid();
};
CalendarPlanView.prototype.grid = function() {
    var d = new Ext.grid.CheckboxSelectionModel();
    var a = new Ext.grid.ColumnModel({
        columns: [d, new Ext.grid.RowNumberer(), {
            header: "planId",
            dataIndex: "planId",
            hidden: true
        },
        {
            header: "状态",
            width: 50,
            dataIndex: "status",
            renderer: function(e) {
                if (e == 1) {
                    return '<img src="' + __ctxPath + '/images/flag/task/finish.png" title="完成"/>';
                } else {
                    return '<img src="' + __ctxPath + '/images/flag/task/go.png" title="未完成"/>';
                }
            }
        },
        {
            header: "开始时间",
            width: 120,
            dataIndex: "startTime"
        },
        {
            header: "结束时间",
            width: 120,
            dataIndex: "endTime"
        },
        {
            header: "紧急程度",
            width: 60,
            dataIndex: "urgent",
            renderer: function(e) {
                if (e == 0) {
                    return "一般";
                } else {
                    if (e == 1) {
                        return "<font color='green'>重要</font>";
                    } else {
                        return "<font color='red'>紧急</font>";
                    }
                }
            }
        },
        {
            width: 250,
            header: "内容",
            dataIndex: "content",
            renderer: function(h, g, f) {
                var e = f.data.status;
                if (e == 1) {
                    return '<font style="text-decoration:line-through;color:red;">' + h + "</font>";
                } else {
                    return h;
                }
            }
        },
        {
            header: "执行人",
            width: 60,
            dataIndex: "fullname"
        },
        {
            header: "分配人",
            width: 60,
            dataIndex: "assignerName"
        },
        {
            header: "任务类型",
            width: 60,
            dataIndex: "taskType",
            renderer: function(e) {
                if (e == 1) {
                    return "<font color='red'>限期任务</font>";
                } else {
                    return "<font color='green'>非限期任务</font>";
                }
            }
        },
        {
            header: "管理",
            dataIndex: "planId",
            width: 80,
            sortable: false,
            renderer: function(l, k, g, j, m) {
                var e = g.data.planId;
                var f = g.data.status;
                var h = g.data.assignerId;
                var i = '<button title="删除" value=" " class="btn-del" onclick="CalendarPlanView.remove(' + e + ')"></button>';
                if (f == 0) {
                    if (h == curUserInfo.userId) {
                        i += '&nbsp;<button title="编辑" value=" " class="btn-edit" onclick="CalendarPlanView.edit(' + e + ')"></button>';
                    }
                    i += '&nbsp;<button title="完成任务" value="" class="btn-task" onclick="CalendarPlanView.finished(' + e + ')"></button>';
                }
                return i;
            }
        }],
        defaults: {
            sortable: true,
            menuDisabled: false,
            width: 100
        }
    });
    var b = this.store();
    b.load({
        params: {
            start: 0,
            limit: 25
        }
    });
    var c = new Ext.grid.GridPanel({
        id: "CalendarPlanGrid",
        tbar: this.topbar(),
        autoHeight: true,
        store: b,
        trackMouseOver: true,
        disableSelection: false,
        loadMask: true,
        cm: a,
        sm: d,
        viewConfig: {
            forceFit: true,
            enableRowBody: false,
            showPreview: false
        },
        bbar: new Ext.PagingToolbar({
            pageSize: 25,
            store: b,
            displayInfo: true,
            displayMsg: "当前显示从{0}至{1}， 共{2}条记录",
            emptyMsg: "当前没有记录"
        })
    });
    return c;
};
CalendarPlanView.prototype.store = function() {
    var a = new Ext.data.Store({
        proxy: new Ext.data.HttpProxy({
            url: __ctxPath + "/task/listCalendarPlan.do"
        }),
        reader: new Ext.data.JsonReader({
            root: "result",
            totalProperty: "totalCounts",
            id: "id",
            fields: [{
                name: "planId",
                type: "int"
            },
            "startTime", "endTime", "urgent", "content", "status", "userId", "fullname", "assignerId", "assignerName", "feedback", "showStyle", "taskType"]
        }),
        remoteSort: true
    });
    a.setDefaultSort("planId", "desc");
    return a;
};
CalendarPlanView.prototype.topbar = function() {
    var a = new Ext.Toolbar({
        id: "CalendarPlanFootBar",
        height: 30,
        bodyStyle: "text-align:left",
        items: [{
            iconCls: "btn-add",
            text: "添加日程",
            xtype: "button",
            handler: function() {
                new CalendarPlanForm();
            }
        },
        {
            iconCls: "btn-del",
            text: "删除日程",
            xtype: "button",
            handler: function() {
                var d = Ext.getCmp("CalendarPlanGrid");
                var b = d.getSelectionModel().getSelections();
                if (b.length == 0) {
                    Ext.ux.Toast.msg("信息", "请选择要删除的记录！");
                    return;
                }
                var e = Array();
                for (var c = 0; c < b.length; c++) {
                    e.push(b[c].data.planId);
                }
                CalendarPlanView.remove(e);
            }
        },
        {
            text: "我分配的任务",
            xtype: "button",
            iconCls: "btn-myAssign",
            handler: function() {
                var b = Ext.getCmp("CalendarPlanSearchForm").getForm();
                b.submit({
                    waitMsg: "正在提交查询",
                    url: __ctxPath + "/task/listCalendarPlan.do",
                    params: {
                        "Q_assignerId_L_EQ": curUserInfo.userId
                    },
                    success: function(d, e) {
                        var c = Ext.util.JSON.decode(e.response.responseText);
                        Ext.getCmp("CalendarPlanGrid").getStore().loadData(c);
                    }
                });
            }
        },
        {
            text: "今日常务",
            xtype: "button",
            iconCls: "menu-cal-plan",
            handler: function() {
                App.clickTopTab("MyPlanTaskView");
            }
        }]
    });
    return a;
};
CalendarPlanView.remove = function(b) {
    var a = Ext.getCmp("CalendarPlanGrid");
    Ext.Msg.confirm("信息确认", "您确认要删除该记录吗？",
    function(c) {
        if (c == "yes") {
            Ext.Ajax.request({
                url: __ctxPath + "/task/multiDelCalendarPlan.do",
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
CalendarPlanView.edit = function(a) {
    new CalendarPlanForm(a);
};
CalendarPlanView.finished = function(a) {
    new CalendarPlanFinishForm(a);
};