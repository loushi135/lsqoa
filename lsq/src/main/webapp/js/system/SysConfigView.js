Ext.ns("SysConfigView");
var SysConfigView = function() {
	return new Ext.Panel({
				id : "SysConfigView",
				iconCls : "menu-system-setting",
				title : "系统配置",
				tbar : this.tbar(),
				autoScroll : true,
				items : [this.setup()]
			});
};
SysConfigView.prototype.tbar = function() {
	var a = new Ext.Toolbar();
	a.add(new Ext.Button({
				text : "保存",
				iconCls : "btn-save",
				handler : function() {
					var b = Ext.getCmp("mailConfigForm");
					if (b.getForm().isValid()) {
						b.getForm().submit({
									method : "post",
									waitMsg : "正在提交数据...",
									success : function(c, e) {
										Ext.ux.Toast.msg("操作信息", "成功信息保存！");
										var d = Ext.getCmp("centerTabPanel");
										d.remove("SysConfigView");
									},
									failure : function(c, d) {
										Ext.MessageBox.show({
													title : "操作信息",
													msg : "信息保存出错，请联系管理员！",
													buttons : Ext.MessageBox.OK,
													icon : "ext-mb-error"
												});
									}
								});
					}
				}
			}));
	a.add(new Ext.Button({
				text : "重置",
				iconCls : "btn-reseted",
				handler : function() {
					var b = Ext.getCmp("mailConfigForm");
					Ext.Ajax.request({
								url : __ctxPath + "/system/loadSysConfig.do",
								success : function(c, e) {
									b.removeAll();
									var d = Ext.util.JSON
											.decode(c.responseText);
									b.add(d.data);
									b.doLayout();
								}
							});
				}
			}));
	return a;
};
SysConfigView.prototype.setup = function() {
	var a = new Ext.FormPanel({
				id : "mailConfigForm",
				url : __ctxPath + "/system/saveSysConfig.do",
				defaultType : "textfield",
				bodyStyle : "padding-left:10%;",
				frame : false,
				border : false,
				layout : "form",
				items : []
			});
	Ext.Ajax.request({
				url : __ctxPath + "/system/loadSysConfig.do",
				success : function(b, d) {
					var c = Ext.util.JSON.decode(b.responseText);
					a.add(c.data);
					a.add({
							xtype:'fieldset',
							title:'同步菜单',
							layout:'form',
							width:650,
							items:[new Ext.Button({
								text : "同步",
								iconCls : "btn-reseted",
								handler : function() {
									Ext.Ajax.request({
												url : __ctxPath + "/system/synMenuSysConfig.do",
												success : function(response, options) {
													location.reload();
												}
											});
								}
							})]
					})
					a.doLayout();
				}
			});
	return a;
};