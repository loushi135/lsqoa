DictionaryForm = Ext.extend(Ext.Window, {
	formPanel : null,
	constructor : function(a) {
		Ext.applyIf(this, a);
		this.initUIComponents();
		DictionaryForm.superclass.constructor.call(this, {
					layout : "fit",
					id : "DictionaryFormWin",
					iconCls : "menu-dictionary",
					items : this.formPanel,
					title : "字典详细信息",
					width : 380,
					border : false,
					height : 220,
					modal : true,
					plain : true,
					bodyStyle : "padding:5px;",
					buttonAlign : "center",
					buttons : this.buttons
				});
	},
	initUIComponents : function() {
		this.formPanel = new Ext.FormPanel({
			url : __ctxPath + "/system/saveDictionary.do",
			layout : "form",
			id : "DictionaryForm",
			frame : false,
			defaults : {
				width : 300,
				anchor : "98%,98%",
				labelWidth : 60
			},
			formId : "DictionaryFormId",
			defaultType : "textfield",
			items : [{
						name : "dictionary.dicId",
						id : "dicId",
						xtype : "hidden",
						value : this.dicId == null ? "" : this.dicId
					}, {
						fieldLabel : "条目",
						name : "dictionary.itemName",
						id : "itemName",
						maxHeight : 200,
						xtype : "combo",
						mode : "local",
						editable : true,
						triggerAction : "all",
						store : [],
						listeners : {
							focus : function(b) {
								var a = Ext.getCmp("itemName").getStore();
								if (a.getCount() <= 0) {
									Ext.Ajax.request({
												url : __ctxPath
														+ "/system/itemsDictionary.do",
												method : "post",
												success : function(d) {
													var c = Ext.util.JSON
															.decode(d.responseText);
													a.loadData(c);
												}
											});
								}
							}
						}
					}, {
						fieldLabel : "数值",
						name : "dictionary.itemValue",
						id : "itemValue"
					}, {
						fieldLabel : "金螳螂主键",
						name : "dictionary.glodMantisId",
						id : "glodMantisId"
					}, {
						fieldLabel : "备注",
						name : "dictionary.descp",
						id : "descp",
						xtype : "textarea"
					}]
		});
		if (this.dicId != null && this.dicId != "undefined") {
			this.formPanel.getForm().load({
				deferredRender : false,
				url : __ctxPath + "/system/getDictionary.do?dicId="
						+ this.dicId,
				waitMsg : "正在载入数据...",
				success : function(a, b) {
				},
				failure : function(a, b) {
				}
			});
		}
		this.buttons = [{
					text : "保存",
					iconCls : "btn-save",
					handler : this.save.createCallback(this.formPanel, this)
				}, {
					text : "重置",
					iconCls : "btn-reset",
					handler : this.reset.createCallback(this.formPanel)
				}, {
					text : "取消",
					iconCls : "btn-cancel",
					handler : this.cancel.createCallback(this)
				}];
	},
	reset : function(a) {
		a.getForm().reset();
	},
	cancel : function(a) {
		a.close();
	},
	save : function(a, b) {
		if (a.getForm().isValid()) {
			a.getForm().submit({
						method : "POST",
						waitMsg : "正在提交数据...",
						success : function(c, e) {
							Ext.ux.Toast.msg("操作信息", "成功保存信息！");
							var d = Ext.getCmp("DictionaryGrid");
							if (d != null) {
								d.getStore().reload();
							}
							b.close();
						},
						failure : function(c, d) {
							Ext.MessageBox.show({
										title : "操作信息",
										msg : "信息保存出错，请联系管理员！",
										buttons : Ext.MessageBox.OK,
										icon : Ext.MessageBox.ERROR
									});
							b.close();
						}
					});
		}
	}
});