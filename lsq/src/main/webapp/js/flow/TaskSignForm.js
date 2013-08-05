TaskSignForm = Ext.extend(Ext.Window, {
    constructor: function(a) {
        Ext.applyIf(this, a);
       
        this.initUIComponents();
        TaskSignForm.superclass.constructor.call(this, {
            id: "TaskSignFormWin",
            layout: "fit",
            iconCls: "menu-taskSign",
            items: this.formPanel,
            modal: true,
            height: 250,
            minHeight: 250,
            width: 500,
            minWidth: 500,
            maximizable: true,
            keys: {
                key: Ext.EventObject.ENTER,
                fn: this.save,
                scope: this
            },
            title: "查看/编辑任务[" + this.activityName + "]会签信息",
            buttonAlign: "center",
            buttons: [{
                text: "保存",
                iconCls: "btn-save",
                scope: this,
                handler: this.save
            },
            {
                text: "取消",
                iconCls: "btn-cancel",
                scope: this,
                handler: this.cancel
            }]
        });
    },
    initUIComponents: function() {
        this.formPanel = new Ext.FormPanel({
        	url: __ctxPath + "/flow/saveTaskSign.do",
            id: "taskSignFormPanel",
            layout: "form",
            bodyStyle: "padding:10px",
            border: false,
            autoScroll: true,
            defaults: {
                anchor: "96%,96%"
            },
            defaultType: "textfield",
            items: [{
                name: "taskSign.signId",
                id:"signId",
                xtype: "hidden",
                value: this.signId == null ? "": this.signId
            },
            {
                name: "assignId",
                xtype: "hidden",
                value: this.assignId == null ? "": this.assignId
            },
            {
                fieldLabel: "任务名称",
                name: "taskSign.assignName",
                allowBlank: false,
                xtype: "textfield",
                readOnly: true,
                value: this.activityName == null ? "": this.activityName
            },
            {
                xtype: "radiogroup",
                fieldLabel: "票数类型",
                id:"taskSignType",
                items: [{
                    boxLabel: "绝对票数",
                    name: "taskSignType",
                    inputValue: 1,
                    checked: true
                },
                {
                    boxLabel: "百分比票数",
                    name: "taskSignType",
                    inputValue: 2
                }],
                listeners: {
                    "change": function(g, b, f) {
            			
                        var taskSignFormPanel = Ext.getCmp("taskSignFormPanel");
                        var taskSignType = Ext.getCmp("taskSignType").getValue().getGroupValue ();
                        var voteCounts = Ext.getCmp("voteCounts");
                        var votePercents = Ext.getCmp("votePercents");
                        
                        
                        if (taskSignType==1) {
                            voteCounts.enable();
                            votePercents.disable();
                        } else {
                            voteCounts.disable();
                            votePercents.enable();
                        }
                    }
                }
            },
            {
                fieldLabel: "绝对票数",
                name: "taskSign.voteCounts",
                id:"voteCounts",
                xtype: "numberfield",
                maxLength: 11,
                minValue: 0,
                regexText: "绝对票数只能输入数字！"
            },
            {
                fieldLabel: "百分比票数",
                name: "taskSign.votePercents",
                id:"votePercents",
                xtype: "numberfield",
                minValue: 0,
                maxValue: 100,
                maxLength: 11,
                disabled: true
            },
            {
                fieldLabel: "决策方式",
                hiddenName: "taskSign.decideType",
                id:"decideType",
                allowBlank: false,
                valueField: "id",
                displayField: "name",
                xtype: "combo",
                store: [["0", "拒绝"], ["1", "通过"]],
                emptyText: "--请选择决策方式--",
                triggerAction: "all",
                editable: false,
                width: " 96%"
            }]
        });
        if (this.assignId != null && this.assignId != "undefined") {
            this.formPanel.load({
                url: __ctxPath + "/flow/findTaskSign.do?assignId=" + this.assignId,
                root: "data",
                preName: "taskSign",
                success : function(form, action) {
                	
				},
				failure : function(form, action) {
				}
            });
        }
    },
    cancel: function() {
        this.close();
    },
    save: function() {
    	var fp = Ext.getCmp('taskSignFormPanel');
			if (fp.getForm().isValid()) {
					fp.getForm().submit({
						method : 'post',
						waitMsg : '正在提交数据...',
						success : function(fp, action) {
							Ext.ux.Toast.msg('操作信息', '成功信息保存！');
							//Ext.getCmp('TaskSignGrid').getStore().reload();
							Ext.getCmp('TaskSignFormWin').close();
						},
						failure : function(fp, action) {
							
							Ext.ux.Toast.msg('操作信息', '信息保存出错，请联系管理员！');
							Ext.getCmp('TaskSignFormWin').close();
						}
			});
		}
    }
});