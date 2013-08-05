ContractForm = Ext.extend(Ext.Window, {
	formPanel : null,
	constructor : function(a) {
		Ext.applyIf(this, a);
		this.initUIComponents();
		ContractForm.superclass.constructor.call(this, {
					layout : "fit",
					id : "ContractFormWin",
					title : "合同详细信息",
					iconCls : "menu-contract",
					width : 780,
					height : 530,
					minWidth : 779,
					minHeight : 529,
					items : this.formPanel,
					border : false,
					modal : true,
					plain : true,
					buttonAlign : "center",
					buttons : this.buttons
				});
	},
	initUIComponents : function() {
		var b = __ctxPath + "/system/listDepartment.do";
		var a = new TreeSelector("serviceDepSelector", b, "维护部门", null);
		a.addListener("change", function() {
					Ext.getCmp("serviceDep").setValue(a.getValue());
				});
		var c = new ContractConfigView();
		this.formPanel = new Ext.FormPanel({
			url : __ctxPath + "/customer/saveContract.do",
			layout : "hbox",
			frame : false,
			layoutConfig : {
				padding : "5",
				pack : "start",
				align : "middle"
			},
			defaults : {
				margins : "0 5 0 0"
			},
			id : "ContractForm",
			frame : false,
			formId : "ContractFormId",
			defaultType : "textfield",
			items : [{
				xtype : "fieldset",
				title : "合同信息(带 * 号为必填)",
				layout : "form",
				rowspan : 2,
				labelWidth : 60,
				defaultType : "textfield",
				autoWidth : true,
				autoHeight : true,
				defaults : {
					width : 280
				},
				items : [{
							name : "contract.contractId",
							id : "contractId",
							xtype : "hidden",
							value : this.contractId == null
									? ""
									: this.contractId
						}, {
							fieldLabel : "合同编号*",
							name : "contract.contractNo",
							id : "contractNo",
							allowBlank : false,
							blankText : "合同编号不可为空!"
						}, {
							fieldLabel : "合同标题*",
							name : "contract.subject",
							id : "subject",
							allowBlank : false,
							blankText : "合同标题不可为空!"
						}, {
							xtype : "container",
							layout : "column",
							height : 26,
							autoWidth : true,
							style : "padding-left:0px;padding-bottom:0px",
							defaultType : "label",
							items : [{
										text : "合同金额*:",
										width : 64,
										style : "padding-left:0px;padding-top:3px;"
									}, {
										xtype : "numberfield",
										width : 250,
										name : "contract.contractAmount",
										id : "contractAmount",
										allowBlank : false,
										blankText : "合同金额不可为空!"
									}, {
										text : "元",
										width : 10,
										style : "padding-left:0px;padding-top:3px;"
									}]
						}, {
							fieldLabel : "主要条款",
							xtype : "textarea",
							name : "contract.mainItem",
							id : "mainItem"
						}, {
							fieldLabel : "售后条款",
							xtype : "textarea",
							name : "contract.salesAfterItem",
							id : "salesAfterItem"
						}, {
							xtype : "container",
							height : 26,
							layout : "column",
							autoWidth : true,
							defaultType : "label",
							style : "padding-left:0px;",
							items : [{
										text : "有效日期*:",
										width : 63,
										style : "padding-left:0px;padding-top:3px;"
									}, {
										xtype : "datefield",
										width : 110,
										format : "Y-m-d",
										editable : false,
										name : "contract.validDate",
										id : "validDate",
										allowBlank : false,
										blankText : "合同生效日期不可为空!"
									}, {
										text : "至",
										width : 10,
										style : "padding-left:0px;padding-top:3px;"
									}, {
										xtype : "datefield",
										width : 110,
										format : "Y-m-d",
										editable : false,
										name : "contract.expireDate",
										id : "expireDate",
										allowBlank : false,
										blankText : "合同到期日期不可为空!"
									}]
						}, {
							xtype : "container",
							layout : "column",
							height : 26,
							autoWidth : true,
							style : "padding-left:0px;",
							items : [{
										xtype : "label",
										text : "签约人*:",
										style : "padding-left:0px;padding-top:3px;",
										width : 64
									}, {
										xtype : "textfield",
										width : 180,
										readOnly : true,
										name : "contract.signupUser",
										id : "signupUser",
										allowBlank : false,
										blankText : "签约人不可为空!"
									}, {
										xtype : "button",
										text : "签约人",
										iconCls : "btn-mail_recipient",
										handler : function() {
											UserSelector.getView(
													function(e, d) {
														Ext
																.getCmp("signupUser")
																.setValue(d);
													}, true).show();
										}
									}]
						}, {
							fieldLabel : "签约时间",
							xtype : "datefield",
							format : "Y-m-d",
							editable : false,
							name : "contract.signupTime",
							id : "signupTime",
							allowBlank : false,
							blankText : "签约时间不可为空!"
						}, {
							fieldLabel : "维护部门",
							name : "contract.serviceDep",
							xtype : "hidden",
							id : "serviceDep"
						}, a, {
							xtype : "container",
							layout : "column",
							height : 26,
							autoWidth : true,
							style : "padding-left:0px;",
							items : [{
										xtype : "label",
										text : "维护人:",
										style : "padding-left:0px;padding-top:3px;",
										width : 64
									}, {
										xtype : "textfield",
										width : 180,
										readOnly : true,
										name : "contract.serviceMan",
										id : "serviceMan"
									}, {
										xtype : "button",
										text : "维护人",
										iconCls : "btn-mail_recipient",
										handler : function() {
											UserSelector.getView(
													function(e, d) {
														Ext
																.getCmp("serviceMan")
																.setValue(d);
													}, true).show();
										}
									}]
						}, {
							xtype : "container",
							autoHeight : true,
							layout : "column",
							autoWidth : true,
							defaultType : "label",
							style : "padding-left:0px;",
							items : [{
										text : "合同附件:",
										width : 64,
										style : "padding-left:0px;padding-top:3px;"
									}, {
										xtype : "hidden",
										name : "contractAttachIDs",
										id : "contractAttachIDs"
									}, {
										xtype : "panel",
										id : "displayContractAttach",
										width : 215,
										height : 65,
										frame : false,
										autoScroll : true,
										style : "padding-left:0px;padding-top:0px;",
										html : ""
									}, {
										xtype : "button",
										iconCls : "btn-upload",
										text : "上传",
										handler : function() {
											var d = App.createUploadDialog({
														file_cat : "csutomer/contract",
														callback : uploadContractAttach
													});
											d.show("queryBtn");
										}
									}]
						}]
			}, {
				xtype : "container",
				style : "padding:5px 0px 0px 0px;",
				items : [{
					xtype : "fieldset",
					title : "项目信息",
					layout : "form",
					labelWidth : 55,
					width : 380,
					autoHeight : true,
					style : "padding-bottom:0px;bottom:0px;",
					defaultType : "textfield",
					items : [{
								fieldLabel : "所属项目",
								xtype : "hidden",
								name : "contract.projectId",
								id : "projectId"
							}, {
								xtype : "button",
								iconCls : "menu-project",
								text : "选择项目",
								handler : function() {
									ProjectSelector.getView(function(f, e, d) {
												Ext.getCmp("projectId")
														.setValue(f);
												ContractForm.getProject(f);
											}, true).show();
								}
							}, {
								xtype : "panel",
								id : "ProjectDisplay",
								autoWidth : true,
								height : 130,
								autoScroll : true,
								html : "项目编号: <br> 项目名称: <br> 所属客户: <br>"
										+ "联系人员: <br> 项目描述: <br>"
							}]
				}, {
					xtype : "fieldset",
					title : "配置单",
					layout : "form",
					width : 380,
					style : "padding-top:0px;top:0px;",
					autoHeight : true,
					labelWidth : 58,
					defaultType : "textfield",
					items : [{
								fieldLabel : "收货地址",
								name : "contract.consignAddress",
								id : "consignAddress",
								width : 280
							}, {
								fieldLabel : "收货人",
								name : "contract.consignee",
								id : "consignee",
								width : 280
							}, c]
				}]
			}]
		});
		if (this.contractId != null && this.contractId != "undefined") {
			this.formPanel.getForm().load({
				deferredRender : false,
				url : __ctxPath + "/customer/getContract.do?contractId="
						+ this.contractId,
				waitMsg : "正在载入数据...",
				success : function(e, g) {
					var p = g.result.data;
					var h = getDateFromFormat(p.validDate,
							"yyyy-MM-dd HH:mm:ss");
					var d = getDateFromFormat(p.expireDate,
							"yyyy-MM-dd HH:mm:ss");
					var k = getDateFromFormat(p.signupTime,
							"yyyy-MM-dd HH:mm:ss");
					Ext.getCmp("validDate").setValue(new Date(h));
					Ext.getCmp("expireDate").setValue(new Date(d));
					Ext.getCmp("signupTime").setValue(new Date(k));
					Ext.getCmp("serviceDepSelector").setValue(p.serviceDep);
					Ext.getCmp("projectId").setValue(g.result.projectId);
					ContractForm.getProject(g.result.projectId);
					var f = Ext.getCmp("contractId").value;
					if (f != null && f != "" && f != "undefined") {
						var n = Ext.getCmp("ContractConfigGrid").getStore();
						n.reload({
									params : {
										"Q_contract.contractId_L_EQ" : f
									}
								});
					}
					var m = g.result.data.contractFiles;
					var l = Ext.getCmp("displayContractAttach");
					var o = Ext.getCmp("contractAttachIDs");
					for (var j = 0; j < m.length; j++) {
						if (o.getValue() != "") {
							o.setValue(o.getValue() + ",");
						}
						o.setValue(o.getValue() + m[j].fileId);
						Ext.DomHelper
								.append(
										l.body,
										'<span><a href="#" onclick="FileAttachDetail.show('
												+ m[j].fileId
												+ ')">'
												+ m[j].fileName
												+ '</a><img class="img-delete" src="'
												+ __ctxPath
												+ '/images/system/delete.gif" onclick="removeContractAttach(this,'
												+ m[j].fileId
												+ ')"/>&nbsp;|&nbsp;</span>');
					}
				},
				failure : function(d, e) {
					Ext.ux.Toast.msg("编辑", "载入失败");
				}
			});
		}
		this.buttons = [{
					text : "保存",
					iconCls : "btn-save",
					handler : function() {
						ContractForm.saveContract();
					}
				}, {
					text : "取消",
					iconCls : "btn-cancel",
					handler : function() {
						Ext.getCmp("ContractFormWin").close();
					}
				}];
	}
});
ContractForm.getProject = function(a) {
	if (a != null && a != "" && a != "undefined") {
		Ext.Ajax.request({
					url : __ctxPath + "/customer/getProject.do",
					params : {
						projectId : a
					},
					method : "post",
					success : function(c) {
						var b = Ext.util.JSON.decode(c.responseText);
						Ext.getCmp("ProjectDisplay").body.update("项目编号: "
								+ b.data.projectNo + "<br> 项目名称: "
								+ b.data.projectName + "<br> 所属客户: "
								+ b.customerName + "<br> 联系人员: "
								+ b.data.fullname + "<br> 项目描述: "
								+ b.data.reqDesc);
					}
				});
	}
};
ContractForm.saveContract = function() {
	var c = Ext.getCmp("ContractForm");
	var b = Ext.getCmp("ContractConfigGrid").getStore();
	var d = [];
	for (i = 0, cnt = b.getCount(); i < cnt; i += 1) {
		var a = b.getAt(i);
		if (a.data.itemName == null || a.data.itemName == ""
				|| a.data.itemName == "undefined") {
			Ext.ux.Toast.msg("提示信息", "设备名称为必填!");
			return;
		}
		if (a.data.itemSpec == null || a.data.itemSpec == ""
				|| a.data.itemSpec == "undefined") {
			Ext.ux.Toast.msg("提示信息", "设备规格为必填!");
			return;
		}
		if (a.data.amount == null || a.data.amount == ""
				|| a.data.amount == "undefined") {
			Ext.ux.Toast.msg("提示信息", "设备数量为必填!");
			return;
		}
		if (a.data.assignId == "" || a.data.assignId == null) {
			a.set("configId", -1);
		}
		if (a.dirty) {
			d.push(a.data);
		}
	}
	if (c.getForm().isValid()) {
		c.getForm().submit({
					method : "post",
					params : {
						data : Ext.encode(d)
					},
					waitMsg : "正在提交数据...",
					success : function(e, f) {
						Ext.ux.Toast.msg("操作信息", "成功保存信息！");
						Ext.getCmp("ContractGrid").getStore().reload();
						Ext.getCmp("ContractFormWin").close();
					},
					failure : function(e, f) {
						Ext.MessageBox.show({
									title : "操作信息",
									msg : f.result.msg,
									buttons : Ext.MessageBox.OK,
									icon : "ext-mb-error"
								});
					}
				});
	}
};
function uploadContractAttach(c) {
	var a = Ext.getCmp("contractAttachIDs");
	var d = Ext.getCmp("displayContractAttach");
	for (var b = 0; b < c.length; b++) {
		if (a.getValue() != "") {
			a.setValue(a.getValue() + ",");
		}
		a.setValue(a.getValue() + c[b].fileId);
		Ext.DomHelper
				.append(
						d.body,
						'<span><a href="#" onclick="FileAttachDetail.show('
								+ c[b].fileId
								+ ')">'
								+ c[b].filename
								+ '</a><img class="img-delete" src="'
								+ __ctxPath
								+ '/images/system/delete.gif" onclick="removeContractAttach(this,'
								+ c[b].fileId + ')"/>&nbsp;|&nbsp;</span>');
	}
}
function removeContractAttach(f, d) {
	var b = Ext.getCmp("contractAttachIDs");
	var e = b.getValue();
	if (e.indexOf(",") < 0) {
		b.setValue("");
	} else {
		e = e.replace("," + d, "").replace(d + ",", "");
		b.setValue(e);
	}
	var c = Ext.get(f.parentNode);
	c.remove();
	var a = Ext.getCmp("contractId").value;
	if (a != null && a != "" && a != "undefined") {
		Ext.Ajax.request({
					url : __ctxPath + "/customer/removeFileContract.do",
					params : {
						contractId : a,
						contractAttachIDs : d
					},
					method : "post",
					success : function() {
						Ext.Ajax.request({
									url : __ctxPath
											+ "/system/multiDelFileAttach.do",
									params : {
										ids : d
									},
									method : "post",
									success : function() {
										Ext.ux.Toast.msg("信息提示", "成功删除所选记录！");
									}
								});
					}
				});
	} else {
		Ext.Ajax.request({
					url : __ctxPath + "/system/multiDelFileAttach.do",
					params : {
						ids : d
					},
					method : "post",
					success : function() {
						Ext.ux.Toast.msg("信息提示", "成功删除所选记录！");
					}
				});
	}
}