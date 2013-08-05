var UserSubForm = function(b) {
	this.subId = b;
	var a = this.setup();
	var c = new Ext.Window({
				id : "UserSubFormWin",
				title : "选择添加的下属",
				iconCls : "btn-mail_recipient",
				width : 500,
				height : 200,
				modal : true,
				layout : "anchor",
				plain : true,
				border : false,
				bodyStyle : "padding:5px;",
				buttonAlign : "center",
				items : [this.setup()],
				buttons : [{
					text : "保存",
					iconCls : "btn-save",
					handler : function() {
						var d = Ext.getCmp("UserSubForm");
						if (d.getForm().isValid()) {
							d.getForm().submit({
								method : "post",
								waitMsg : "正在提交数据...",
								success : function(e, f) {
									Ext.ux.Toast.msg("操作信息", "成功保存信息！");
									Ext.getCmp("UserSubGrid").getStore()
											.reload();
									c.close();
								},
								failure : function(e, f) {
									Ext.MessageBox.show({
												title : "操作信息",
												msg : "信息保存出错，请联系管理员！",
												buttons : Ext.MessageBox.OK,
												icon : "ext-mb-error"
											});
									c.close();
								}
							});
						}
					}
				}, {
					text : "取消",
					iconCls : "btn-cancel",
					handler : function() {
						c.close();
					}
				}]
			});
	c.show();
};
UserSubForm.prototype.setup = function() {
	var a = new Ext.FormPanel({
				url : __ctxPath + "/system/saveUserSub.do",
				layout : "column",
				id : "UserSubForm",
				frame : false,
				defaults : {
					width : 400,
					anchor : "98%,98%"
				},
				formId : "UserSubFormId",
				items : [{
							name : "userSub.subId",
							id : "subId",
							xtype : "hidden",
							value : this.subId == null ? "" : this.subId
						}, {
							xtype : "hidden",
							name : "subUserIds",
							id : "subUserIds"
						}, {
							text : "下属",
							xtype : "label",
							width : 30
						}, {
							name : "subUsers",
							id : "subUsers",
							xtype : "textarea",
							readOnly : true,
							editable : false,
							allowBlank : false,
							width : 300,
							height : 100
						}, {
							xtype : "container",
							layout : "form",
							width : 100,
							items : [{
								width : 80,
								xtype : "button",
								text : "选择下属",
								iconCls : "btn-mail_recipient",
								handler : function() {
									UserSelector.getView(function(n, l) {
										var m = Ext.getCmp("subUsers");
										var k = Ext
												.getCmp("subUserIds");
										m.setValue(l);
										k.setValue(n);
									}).show();
								}
							}]
						}]
			});
	if (this.subId != null && this.subId != "undefined") {
		a.getForm().load({
					deferredRender : false,
					url : __ctxPath + "/system/getUserSub.do?subId="
							+ this.subId,
					waitMsg : "正在载入数据...",
					success : function(b, c) {
					},
					failure : function(b, c) {
						Ext.ux.Toast.msg("编辑", "载入失败");
					}
				});
	}
	return a;
};