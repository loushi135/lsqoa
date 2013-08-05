var ErrandsRegisterOutForm = function(c) {
	this.dateId = c;
	var a = this.setup();
	var b = new Ext.Window(
			{
				id : "ErrandsRegisterOutFormWin",
				title : "外出单详细信息",
				iconCls : "menu-errands",
				width : 400,
				height : 250,
				modal : true,
				layout : "fit",
				buttonAlign : "center",
				items : [ this.setup() ],
				buttons : [
						{
							text : "保存",
							iconCls : "btn-save",
							handler : function() {
								var d = Ext.getCmp("ErrandsRegisterOutForm");
								if (d.getForm().isValid()) {
									d
											.getForm()
											.submit(
													{
														method : "post",
														waitMsg : "正在提交数据...",
														success : function(e, f) {
															Ext.ux.Toast.msg(
																	"操作信息",
																	"成功保存信息！");
															Ext
																	.getCmp(
																			"ErrandsRegisterOutGrid")
																	.getStore()
																	.reload();
															b.close();
														},
														failure : function(e, f) {
															Ext.MessageBox
																	.show({
																		title : "操作信息",
																		msg : "信息保存出错，请联系管理员！",
																		buttons : Ext.MessageBox.OK,
																		icon : "ext-mb-error"
																	});
															b.close();
														}
													});
								}
							}
						}, {
							text : "取消",
							iconCls : "btn-cancel",
							handler : function() {
								b.close();
							}
						} ]
			});
	b.show();
};
ErrandsRegisterOutForm.prototype.setup = function() {
	var a = new Ext.FormPanel({
		url : __ctxPath + "/personal/saveErrandsRegister.do",
		layout : "form",
		id : "ErrandsRegisterOutForm",
		bodyStyle : "padding:5px;",
		border : false,
		defaults : {
			width : 400,
			anchor : "98%,98%"
		},
		formId : "ErrandsRegisterOutFormId",
		defaultType : "textfield",
		items : [ {
			name : "errandsRegister.dateId",
			id : "dateId",
			xtype : "hidden",
			value : this.dateId == null ? "" : this.dateId
		}, {
			xtype : "hidden",
			name : "errandsRegister.userId",
			id : "userId"
		}, {
			xtype : "hidden",
			name : "errandsRegister.status",
			id : "status"
		}, {
			xtype : "hidden",
			name : "errandsRegister.approvalOption",
			id : "approvalOption"
		}, {
			xtype : "hidden",
			name : "errandsRegister.approvalName",
			id : "approvalName"
		}, {
			name : "errandsRegister.flag",
			id : "flag",
			xtype : "hidden",
			value : 2
		}, {
			fieldLabel : "描述",
			xtype : "textarea",
			name : "errandsRegister.descp",
			allowBlank : false,
			id : "descp"
		}, {
			fieldLabel : "开始时间",
			name : "errandsRegister.startTime",
			xtype : "datetimefield",
			format : "Y-m-d H:i:s",
			allowBlank : false,
			id : "startTime"
		}, {
			fieldLabel : "结束时间",
			name : "errandsRegister.endTime",
			xtype : "datetimefield",
			format : "Y-m-d H:i:s",
			allowBlank : false,
			id : "endTime"
		}, {
			fieldLabel : "审批人",
			hiddenName : "errandsRegister.approvalId",
			id : "approvalId",
			emptyText : "请选择审批人",
			xtype : "combo",
			mode : "local",
			anchor : "98%",
			allowBlank : false,
			editable : false,
			valueField : "id",
			displayField : "name",
			triggerAction : "all",
			store : new Ext.data.SimpleStore({
				autoLoad : true,
				url : __ctxPath + "/system/upUserAppUser.do",
				fields : [ "id", "name" ]
			}),
			listeners : {
				select : function(d, b, c) {
					Ext.getCmp("approvalName").setValue(b.data.name);
				}
			}
		} ]
	});
	if (this.dateId != null && this.dateId != "undefined") {
		a.getForm().load(
				{
					deferredRender : false,
					url : __ctxPath + "/personal/getErrandsRegister.do?dateId="
							+ this.dateId,
					waitMsg : "正在载入数据...",
					success : function(e, f) {
						var b = f.result.data;
						var d = getDateFromFormat(b.startTime,
								"yyyy-MM-dd H:mm:ss");
						var c = getDateFromFormat(b.endTime,
								"yyyy-MM-dd H:mm:ss");
						Ext.getCmp("startTime").setValue(new Date(d));
						Ext.getCmp("endTime").setValue(new Date(c));
					},
					failure : function(b, c) {
						Ext.ux.Toast.msg("编辑", "载入失败");
					}
				});
	}
	return a;
};