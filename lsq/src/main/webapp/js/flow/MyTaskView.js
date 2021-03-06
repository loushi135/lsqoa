Ext.ns("MyTaskView");
var MyTaskView = function() {
    var a = new Ext.Panel({
        id: "MyTaskView",
        iconCls: "menu-flowWait",
        bodyStyle: "padding:2px 2px 2px 2px",
        layout: "fit",
        title: "待办事项",
        autoScroll: true,
        items: [this.setup()]
    });
    return a;
};
MyTaskView.prototype.setup = function() {
    var b = this.initData();
    b.load({
        params: {
            start: 0,
            limit: 25
        }
    });
    var a = new Ext.grid.ColumnModel({
        columns: [new Ext.grid.RowNumberer(), {
            header: "userId",
            dataIndex: "userId",
            width: 20,
            hidden: true,
            sortable: true
        },
        {
            header: "事项名称",
            dataIndex: "taskName",
            width: 120
        },
        {
            header: "执行人",
            dataIndex: "assignee",
            width: 140,
            renderer: function(h, g, d, i, f) {
                var e = d.data.assignee;
                if (e == null || e == "") {
                    return '<font color="red">暂无执行人</font>';
                } else {
                    return e;
                }
            }
        },
        {
            header: "开始时间",
            dataIndex: "createTime",
            width: 100
        },
        {
            header: "到期时间",
            dataIndex: "dueDate",
            width: 100,
            renderer: function(d) {
                if (d == "") {
                    return "无限制";
                } else {
                    return d;
                }
            }
        },
        {
            hidden: true,
            dataIndex: "executionId"
        },
        {
            hidden: true,
            dataIndex: "taskId"
        },
        {
            hidden: "true",
            dataIndex: "isMultipleTask"
        },
        {
            header: "管理",
            dataIndex: "taskdbid",
            width: 50,
            renderer: function(m, l, g, k, n) {
                var i = g.data.taskId;
                var d = g.data.executionId;
                var h = g.data.assignee;
                var e = g.data.activityName;
                var taskName=g.data.taskName;
                var f = g.data.isMultipleTask;
                var j = "";
                if (h == "") {
                    j += '<button title="锁定任务" class="btn-lockTask" onclick="MyTaskView.lockTask(' + i + ')"></button>';
                }else if(d==""){
                	j += '<button title="审核任务" class="btn-approvalTask" onclick="MyTaskView.nextStepYhoa(\'' + i + "','" + e + "','"+taskName +"')\"></button>";
                } else {
                    j += '<button title="审核任务" class="btn-approvalTask" onclick="MyTaskView.nextStep(\'' + i + "','" + e + "','"+taskName +"')\"></button>";
                    j += '&nbsp;<button title="任务代办" class="btn-changeTask" onclick="MyTaskView.changeTask(' + i + ",'" + e + "')\"></button>";
                    if (f == 1) {
                        j += '&nbsp;<button title="解锁任务" class="btn-unlockTask" onclick="MyTaskView.unlockTask(' + i + ')"></button>';
                    }
                }
                return j;
            }
        }],
        defaults: {
            sortable: true,
            menuDisabled: true,
            width: 100
        }
    });
    var c = new Ext.grid.GridPanel({
        id: "MyTaskGrid",
        closable: true,
        store: b,
        shim: true,
        trackMouseOver: true,
        loadMask: true,
        tbar: new Ext.Toolbar({
            height: 28,
            items: [{
                text: "刷新",
                iconCls: "btn-refresh",
                handler: function() {
                    Ext.getCmp("MyTaskGrid").getStore().reload();
                }
            }, 
            {xtype:'tbseparator'},
            {
				text : '处理方式',
				iconCls : 'btn-update',
				handler : function() {
					new DoTaskModeForm(__currentUserId);
				}
			}]
        }),
        cm: a,
        viewConfig: {
            forceFit: true,
            showPreview: false
        },
        bbar: new Ext.PagingToolbar({
            pageSize: 25,
            store: b,
            displayInfo: true,
            displayMsg: "显示{0}-{1}， 共{2}条记录。",
            emptyMsg: "当前没有记录。"
        })
    });
    return c;
};
MyTaskView.prototype.initData = function() {
    var a = new Ext.data.Store({
        proxy: new Ext.data.HttpProxy({
            url: __ctxPath + "/flow/listTask.do"
        }),
        reader: new Ext.data.JsonReader({
            root: "result",
            totalProperty: "totalCounts",
            fields: ["taskName", "activityName", "assignee", "createTime", "dueDate", "executionId", "pdId", "taskId", "isMultipleTask"]
        }),
        remoteSort: true
    });
    a.setDefaultSort("dbId", "desc");
    return a;
};
MyTaskView.lockTask = function(a) {
    Ext.Ajax.request({
        url: __ctxPath + "/flow/lockTask.do",
        params: {
            taskId: a
        },
        method: "post",
        success: function(b, c) {
            var e = Ext.getCmp("MyTaskGrid");
            var d = Ext.util.JSON.decode(b.responseText);
            if (d.hasAssigned == true) {
                Ext.ux.Toast.msg("操作提示", "该任务已经被其他用户锁定执行！");
            } else {
                Ext.ux.Toast.msg("操作提示", "该任务已经成功锁定，请执行下一步操作！");
            }
            e.getStore().reload();
        }
    });
};
MyTaskView.changeTask = function(b, a) {
    new ChangeTaskView(b, a);
};
MyTaskView.unlockTask = function(a) {
    Ext.Ajax.request({
        url: __ctxPath + "/flow/unlockTask.do",
        params: {
            taskId: a
        },
        method: "post",
        success: function(b, c) {
            var e = Ext.getCmp("MyTaskGrid");
            var d = Ext.util.JSON.decode(b.responseText);
            if (d.unlocked == true) {
                Ext.ux.Toast.msg("操作提示", "该任务已经成功解锁！");
            } else {
                Ext.ux.Toast.msg("操作提示", "该任务解锁失败(任务已经由其他人员执行完成)！");
            }
            e.getStore().reload();
        }
    });
};
MyTaskView.nextStep = function(b, d,taskName) {
    var c = App.getContentPanel();
    var a = c.getItem("ProcessNextForm" + b);
    if (a == null) {
        a = new ProcessNextForm({
            taskId: b,
            activityName: d,
            taskName:taskName
        });
        c.add(a);
    }
    c.activate(a);
};

MyTaskView.nextStepYhoa = function(b, d,taskName) {
    var c = App.getContentPanel();
    var a = c.getItem("ProcessNextFormForYhoa" + b);
    if (a == null) {
        a = new ProcessNextFormForYhoa({
            taskId: b,
            url: d,
            taskName:taskName
        });
        c.add(a);
    }
    c.activate(a);
};