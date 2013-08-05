/**
 * @author
 * @createtime
 * @class SalesProjectForm
 * @extends Ext.Window
 * @description SalesProject表单
 */
SalesProjectForm = Ext.extend(Ext.Window, {
	// 内嵌FormPanel
	formPanel : null,
	// 构造函数
	constructor : function(_cfg) {
		Ext.applyIf(this, _cfg);
		// 必须先初始化组件
		this.initUIComponents();
		SalesProjectForm.superclass.constructor.call(this, {
			id : 'SalesProjectFormWin',
			layout : 'fit',
			items : this.formPanel,
			modal : true,
			height : 550,
			width : 700,
			maximizable : true,
			title : '[营销项目]详细信息',
			buttonAlign : 'center',
			buttons : this.buttons
		});
	},// end of the constructor
	// 初始化组件
	initUIComponents : function() {
		var thizId = this.id;
		var _url = __ctxPath + '/system/listOtherDepartment.do?opt=appUser&depName=团队';
		var depSelector = new TreeSelector('salesProject.teamDep', _url, '部门', 'salesProject.teamDep.depId', false, 147, 'salesProject.teamDep.depName');
		this.formPanel = new Ext.FormPanel({
			autoScroll : true,
			layout : 'form',
			trackResetOnLoad : true,
			bodyStyle : 'padding:10px 10px 10px 10px',
			border : false,
			url : __ctxPath + '/statistics/saveSalesProject.do',
			id : 'SalesProjectForm',
			defaults : {
				anchor : '98%,98%'
			},
			defaultType : 'textfield',
			items : [{
					name : 'salesProject.id',
					id : 'id',
					xtype : 'hidden',
					value : this.id == null ? '' : this.id
				},{
					name : 'salesProject.processRunId',
					id : 'processRunId',
					xtype : 'hidden',
					value:-1
				},{
					name : 'salesProject.proNo',
					id : 'proNo',
					xtype : 'hidden'
				}, {
					xtype : 'panel',
					layout : 'column',
					border : false,
					defaultType : 'label',
					style : "padding-top:3px;",
					items : [{
							style : 'margin-left:15px',
							text : '业务人员:',
							width : 60
						}, {
							xtype : 'textfield',
							style : 'margin-left:15px',
							id : 'salesProject.businessUser.fullname',
							name : 'salesProject.businessUser.fullname',
							allowBlank : false,
							width : 120,
							readOnly : true,
							listeners : {
								focus : function() {
									UserSelector.getView(function(ids, names) {
										Ext.getCmp("salesProject.businessUser.fullname").setValue(names);
										Ext.getCmp("salesProject.businessUser.userId").setValue(ids);
									}, true).show();
								}
							}
						}, {
							xtype : 'hidden',
							name : 'salesProject.businessUser.userId',
							id : 'salesProject.businessUser.userId'
						}, {
							style : 'margin-left:15px',
							text : '所属团队:',
							width : 60
						}, depSelector, {
							xtype : 'hidden',
							name : 'salesProject.teamDep.depId',
							id : 'salesProject.teamDep.depId'
						}, {
							style : 'margin-left:15px',
							text : '报告时间:',
							width : 60
						}, {
							xtype : 'datefield',
							triggerAction : "all",
							editable : false,
							id : 'applyDate',
//							readOnly:true,
							name : 'salesProject.applyDate',
							format : "Y-m-d",
//							style : "background:#F0F0F0;margin-left:15px",
							width : 120
						}]
				}, {
					xtype : 'panel',
					layout : 'column',
					border : false,
					defaultType : 'label',
					style : "padding-top:3px;",
					items : [{
							style : 'margin-left:15px',
							text : '项目名称:',
							width : 60
						}, {
							style : 'margin-left:15px',
							xtype : 'textfield',
							id : 'proName',
							name : 'salesProject.proName',
							allowBlank : false,
							width : 558,
							listeners : {
								blur : function(field) {
									var proName = field.getValue();
									if (Ext.isEmpty(thizId)) {
										if (!Ext.isEmpty(proName)) {
											var url = __ctxPath + '/statistics/checkProNameSalesProject.do';
											var params = "proName=" + encodeURI(proName);
											var data = ajaxSyncCall(url, params);
											if (!data.success) {
												Ext.ux.Toast.msg('操作信息', '信息修改，项目名称为:<font color="red">' + field.getValue() + '</font>已经存在，请修改项目名称！');
												field.setValue('');
											}
										}
									}
								}
							}
						}]
				}, {
					xtype : 'panel',
					layout : 'column',
					border : false,
					defaultType : 'label',
					style : "padding-top:3px;",
					items : [{
							style : 'margin-left:15px',
							text : '工程规模:',
							width : 60
						}, {
							style : 'margin-left:15px',
							text : '（甲方造价预算',
							width : 100
						}, {
							xtype : 'numberfield',
							id : 'buildPrice',
							name : 'salesProject.buildPrice',
							allowBlank : false,
							width : 60
						}, {
							style : 'margin-left:15px',
							text : '万元）、（面积',
							width : 90
						}, {
							xtype : 'numberfield',
							id : 'buildArea',
							name : 'salesProject.buildArea',
							allowBlank : false,
							width : 60
						}, {
							style : 'margin-left:15px',
							text : '平方米）',
							width : 60
						}]
				}, {
					xtype : 'panel',
					layout : 'column',
					border : false,
					defaultType : 'label',
					style : "padding-top:3px;",
					items : [{
							style : 'margin-left:15px',
							text : '项目信息来源:',
							width : 110
						}, {
							style : 'margin-left:15px',
							xtype : 'textfield',
							id : 'proInfoSource',
							name : 'salesProject.proInfoSource',
							allowBlank : false,
							width : 508
						}]
				}, {
					xtype : 'panel',
					layout : 'column',
					border : false,
					defaultType : 'label',
					style : "padding-top:3px;",
					items : [{
							style : 'margin-left:15px',
							text : '装饰施工跟踪部门:',
							width : 110
						}, {
							style : 'margin-left:15px',
							xtype : 'textfield',
							id : 'constructFollowDept',
							name : 'salesProject.constructFollowDept',
							width : 508
						}]
				}, {
					xtype : 'panel',
					layout : 'column',
					border : false,
					defaultType : 'label',
					style : "padding-top:3px;",
					items : [{
							style : 'margin-left:15px',
							text : '项目地址:',
							width : 110
						}, {
							style : 'margin-left:15px',
							xtype : 'textfield',
							id : 'proAddr',
							name : 'salesProject.proAddr',
							allowBlank : false,
							width : 508
						}]
				}, {
					xtype : 'panel',
					layout : 'column',
					border : false,
					defaultType : 'label',
					style : "padding-top:3px;",
					items : [{
							style : 'margin-left:15px',
							text : '项目性质及用途:',
							width : 110
						}, {
							style : 'margin-left:15px',
							id : 'proUseful',
							name : 'salesProject.proUseful',
							allowBlank : false,
							width : 508,
							xtype : 'radiogroup',
							items : [{
									name : 'salesProject.proUseful',
									boxLabel : '政府',
									inputValue : '政府'
								}, {
									name : 'salesProject.proUseful',
									boxLabel : '国企',
									inputValue : '国企'
								}, {
									name : 'salesProject.proUseful',
									boxLabel : '民营',
									inputValue : '民营'
								}, {
									name : 'salesProject.proUseful',
									boxLabel : '外企',
									inputValue : '外企'
								}]
						}]
				}, {
					xtype : 'panel',
					layout : 'column',
					border : false,
					defaultType : 'label',
					style : "padding-top:3px;",
					items : [{
							style : 'margin-left:15px',
							text : '项目投资主体单位:',
							width : 110
						}, {
							style : 'margin-left:15px',
							xtype : 'textfield',
							id : 'proMainUnit',
							name : 'salesProject.proMainUnit',
							allowBlank : false,
							width : 508
						}]
				}, {
					xtype : 'panel',
					layout : 'column',
					border : false,
					defaultType : 'label',
					style : "padding-top:3px;",
					items : [{
							style : 'margin-left:15px',
							text : '与业主关系:',
							width : 110
						}, {
							style : 'margin-left:15px',
							xtype : 'textfield',
							id : 'owerRelation',
							name : 'salesProject.owerRelation',
							width : 508
						}]
				}, {
					xtype : 'panel',
					layout : 'column',
					border : false,
					defaultType : 'label',
					style : "padding-top:3px;",
					items : [{
							style : 'margin-left:15px',
							text : '投标的大概时间:',
							width : 110
						}, {
							style : 'margin-left:15px',
							xtype : 'textfield',
							id : 'bidDuringTime',
							name : 'salesProject.bidDuringTime',
							allowBlank : false,
							width : 508
						}]
				}, {
					xtype : 'panel',
					layout : 'column',
					border : false,
					defaultType : 'label',
					style : "padding-top:3px;",
					items : [{
							style : 'margin-left:15px',
							text : '目前项目整体进展情况:',
							width : 110
						}, {
							style : 'margin-left:15px',
							xtype : 'textfield',
							id : 'progressInfo',
							name : 'salesProject.progressInfo',
							allowBlank : false,
							width : 508
						}]
				}, {
					xtype : 'panel',
					layout : 'column',
					border : false,
					defaultType : 'label',
					style : "padding-top:3px;",
					items : [{
							style : 'margin-left:15px',
							text : '设计是否单独收费:',
							width : 110
						}, {
							style : 'margin-left:15px',
							id : 'chargeDesign',
							name : 'salesProject.chargeDesign',
							allowBlank : false,
							width : 150,
							xtype : 'radiogroup',
							items : [{
									name : 'salesProject.chargeDesign',
									boxLabel : '是',
									inputValue : '是'
								}, {
									name : 'salesProject.chargeDesign',
									boxLabel : '否',
									inputValue : '否'
								}]
						}, {
							style : 'margin-left:15px',
							text : '是否做过评审:',
							width : 110
						}, {
							style : 'margin-left:15px',
							id : 'doReview',
							name : 'salesProject.doReview',
							allowBlank : false,
							width : 150,
							xtype : 'radiogroup',
							items : [{
									name : 'salesProject.doReview',
									boxLabel : '是',
									inputValue : '是'
								}, {
									name : 'salesProject.doReview',
									boxLabel : '否',
									inputValue : '否'
								}]
						}]
				}, {
					xtype : 'panel',
					layout : 'column',
					border : false,
					defaultType : 'label',
					style : "padding-top:3px;",
					items : [{
							style : 'margin-left:15px',
							text : '投标适用的定额及地方法规:',
							width : 110
						}, {
							style : 'margin-left:15px',
							xtype : 'textfield',
							id : 'bidQuotaLocalLaws',
							name : 'salesProject.bidQuotaLocalLaws',
							width : 508
						}]
				}, {
					xtype : 'panel',
					layout : 'column',
					border : false,
					defaultType : 'label',
					style : "padding-top:3px;",
					items : [{
							style : 'margin-left:15px',
							text : '竞争对手公司的名称、与业主关系、优劣势:',
							width : 110
						}, {
							style : 'margin-left:15px',
							xtype : 'textfield',
							id : 'competitionCompany',
							name : 'salesProject.competitionCompany',
							width : 508
						}]
				}, {
					xtype : 'panel',
					layout : 'column',
					border : false,
					defaultType : 'label',
					style : "padding-top:3px;",
					items : [{
							style : 'margin-left:15px',
							text : '推荐设计部门:',
							width : 110
						}, {
							style : 'margin-left:15px',
							xtype : 'textfield',
							id : 'recommendDesignDept',
							name : 'salesProject.recommendDesignDept',
							width : 508
						}]
				}, {
					xtype : 'panel',
					layout : 'column',
					border : false,
					defaultType : 'label',
					style : "padding-top:3px;",
					items : [{
							style : 'margin-left:15px',
							text : '项目分类:',
							width : 110
						}, {
							style : 'margin-left:15px',
							id : 'proClassify',
							name : 'salesProject.proClassify',
							allowBlank : false,
							width : 150,
							xtype : 'radiogroup',
							items : [{
									name : 'salesProject.proClassify',
									boxLabel : 'A',
									inputValue : 'A'
								}, {
									name : 'salesProject.proClassify',
									boxLabel : 'B',
									inputValue : 'B'
								}, {
									name : 'salesProject.proClassify',
									boxLabel : 'C',
									inputValue : 'C'
								}]
						}]
				}, {
					xtype : 'panel',
					layout : 'column',
					border : false,
					defaultType : 'label',
					style : "padding-top:3px;",
					items : [{
							style : 'margin-left:15px',
							text : '备注:',
							width : 110
						}, {
							name : 'salesProject.remark',
							style : 'margin-left:15px',
							width : 500,
							id : 'remark',
							xtype : 'textarea'
						}]
				}]
		});
		// 加载表单对应的数据
		this.initData();
		// 初始化功能按钮
		this.buttons = [{
				text : '保存',
				iconCls : 'btn-save',
				hidden : !(isGranted("_SalesProjectAdd") || isGranted("_SalesProjectEdit")),
				handler : this.save.createCallback(this.formPanel, this)
			}, {
				text : '重置',
				iconCls : 'btn-reset',
				hidden : !(isGranted("_SalesProjectAdd") || isGranted("_SalesProjectEdit")),
				handler : this.reset.createCallback(this.formPanel)
			}, {
				text : '取消',
				iconCls : 'btn-cancel',
				handler : this.cancel.createCallback(this)
			}];
	},// end of the initcomponents
	/**
	 * 初始化数据
	 * 
	 * @param {}
	 *            formPanel
	 */
	initData : function() {
		// 加载表单对应的数据
		if (this.id != null && this.id != 'undefined') {
			this.formPanel.getForm().load({
				deferredRender : false,
				url : __ctxPath + '/statistics/getSalesProject.do?id=' + this.id,
				waitMsg : '正在载入数据...',
				success : function(form, action) {
					var jsonData = Ext.util.JSON.decode(action.response.responseText);
					var res = jsonData.data;
					if (!Ext.isEmpty(res.teamDep)) {
						Ext.getCmp('salesProject.teamDep.depId').setValue(res.teamDep.depId);
						Ext.getCmp('salesProject.teamDep.depId').originalValue = res.teamDep.depId;
						Ext.getCmp('salesProject.teamDep').setValue(res.teamDep.depName);
						Ext.getCmp('salesProject.teamDep').originalValue = res.teamDep.depName;
					}
					if (!Ext.isEmpty(res.businessUser)) {
						Ext.getCmp('salesProject.businessUser.userId').setValue(res.businessUser.userId);
						Ext.getCmp('salesProject.businessUser.userId').originalValue = res.businessUser.userId;
						Ext.getCmp('salesProject.businessUser.fullname').setValue(res.businessUser.fullname);
						Ext.getCmp('salesProject.businessUser.fullname').originalValue = res.businessUser.fullname;
					}
					//					Ext.getCmp("salesProject.applyDate").setValue(res.applyDate);
				},
				failure : function(form, action) {
				}
			});
		}
	},
	/**
	 * 重置
	 * 
	 * @param {}
	 *            formPanel
	 */
	reset : function(formPanel) {
		formPanel.getForm().reset();
	},
	/**
	 * 取消
	 * 
	 * @param {}
	 *            window
	 */
	cancel : function(window) {
		window.close();
	},
	/**
	 * 保存记录
	 */
	save : function(formPanel, window) {
		if (formPanel.getForm().isValid()) {
			formPanel.getForm().submit({
				method : 'POST',
				waitMsg : '正在提交数据...',
				success : function(fp, action) {
					Ext.ux.Toast.msg('操作信息', '成功保存信息！');
					var gridPanel = Ext.getCmp('SalesProjectGrid');
					if (gridPanel != null) {
						gridPanel.getStore().reload();
					}
					window.close();
				},
				failure : function(fp, action) {
					Ext.MessageBox.show({
						title : '操作信息',
						msg : '信息保存出错，请联系管理员！',
						buttons : Ext.MessageBox.OK,
						icon : Ext.MessageBox.ERROR
					});
					// window.close();
				}
			});
		}
	}// end of save

});