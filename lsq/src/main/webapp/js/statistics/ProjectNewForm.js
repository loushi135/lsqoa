/**
 * @author
 * @createtime
 * @class ProjectNewForm
 * @extends Ext.Window
 * @description ProjectNew表单
 */
ProjectNewForm = Ext.extend(Ext.Window, {
	// 内嵌FormPanel
	formPanel : null,
	// 构造函数
	constructor : function(_cfg) {
		Ext.applyIf(this, _cfg);
		// 必须先初始化组件
		this.initUIComponents();
		ProjectNewForm.superclass.constructor.call(this, {
					id : 'ProjectNewFormWin',
					layout : 'fit',
					items : this.formPanel,
					modal : true,
					autoScroll : true,
					width : 720,
					height : 500,
					maximizable : true,
					title : '开工备案书详细信息',
					buttonAlign : 'center',
					buttons : this.buttons
				});
	},// end of the constructor
	// 初始化组件
	initUIComponents : function() {
//		var _url = __ctxPath + '/system/listWorkDepartment.do?opt=appUser';
//		var depSelector = new TreeSelector('depWorkTreeSelector', _url, '',
//				'area', false, 130);
//		Ext.getCmp("depWorkTreeSelectorTree").on("click", function(g) {
//			Ext.getCmp("projectNewArea").setValue(Ext
//					.getCmp("depWorkTreeSelector").getValue());
//			Ext.getCmp("projectNewDeptId").setValue(Ext
//					.getCmp("depWorkTreeSelector").id);
//		});
		this.formPanel = new Ext.FormPanel({
			layout : 'form',
			bodyStyle : 'padding:10px 10px 10px 10px',
			border : false,
			url : __ctxPath + '/statistics/saveProjectNew.do',
			id : 'ProjectNewForm',
			defaults : {
				anchor : '98%,98%'
			},
			defaultType : 'textfield',
			items : [{
						xtype : 'hidden',
						name : 'projectNew.processRunId',
						value : -1
					}, {
						xtype : 'panel',
						layout : 'column',
						border : false,
						defaultType : 'label',
						style : "padding-top:3px;",
						items : [{
									text : '合同编号:',
									width : 90
								}, {
									xtype : 'textfield',
									name : 'projectNew.contract.contractNo',
									id:'projectNew.contract.contractNo',
									allowBlank : false,
									emptyText : '请选择合同',
									width : 570,
									style:"background:#F0F0F0;",
									listeners : {
										focus : function() {
											new ContractSelector(function(id,contractNo,contractAmount,
															projectName,proAreaId,proArea,proChargerName,proChargerTel,businessCharger,proChargerId) {
														Ext.getCmp("contractAmount").setValue(contractAmount);
														Ext.getCmp("projectNewContractId").setValue(id);
														Ext.getCmp("projectNew.contract.contractNo").setValue(contractNo);
														Ext.getCmp("proName").setValue(projectName);
														Ext.getCmp("proCharger").setValue(proChargerName);
														Ext.getCmp("proChargerTel").setValue(proChargerTel);
														Ext.getCmp("projectNew.proChargerUser.userId").setValue(proChargerId);
														Ext.getCmp("projectNewArea").setValue(proArea);
														Ext.getCmp("projectNewDeptId").setValue(proAreaId);
														Ext.getCmp("businessMain").setValue(businessCharger);
													}, true).show();
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
									text : '项目名称:',
									width : 90
								}, {
									xtype : 'hidden',
									name : 'projectNew.id',
									id : 'id'
								}, {
									xtype : 'hidden',
									name : 'projectNew.status',
									id : 'status'
								}, {
									xtype : 'textfield',
									name : 'projectNew.proName',
									id : 'proName',
									readOnly:true,
									allowBlank:false,
									style:"background:#F0F0F0;",
									width : 300
								}, {
									text : '项目编号:',
									style : "padding-left:5px;",
									width : 90
								}, {
									xtype : 'textfield',
									name : 'projectNew.proNo',
									id : 'proNo',
									readOnly : true,
									style:"background:#F0F0F0;",
									emptyText : '系统自动生成',
									width : 185
								}]
					}, {
						xtype : 'panel',
						layout : 'column',
						border : false,
						defaultType : 'label',
						style : "padding-top:3px;",
						items : [{
									text : '工程地点:',
									width : 90
								}, {
									xtype : 'textfield',
									name : 'projectNew.proAddr',
									id : 'proAddr',
									allowBlank:false,
									width : 570
								}]
					}, {
						xtype : "panel",
						layout : "column",
						border : false,
						unstyled : false,
						style : 'padding-top:5px;',
						height : 60,
						items : [{
									xtype : "label",
									text : "所属区域:",
									width : 90
								}, {
									xtype : 'hidden',
									name : 'deptId',
									id : 'projectNewDeptId'
								}, {
									xtype : 'textfield',
									name : 'projectNew.area',
									id : 'projectNewArea',
									allowBlank:false,
									readOnly:true,
									style:"background:#F0F0F0;",
									width:130
								}, {
									xtype : 'container',
									layout : 'form',
									style : 'padding-left:5px;',
									items : [{
										xtype : 'container',
										layout : 'column',
										defaults : {
											xtype : 'label'
										},
										items : [{
													text : '项目负责人:',
													width : 90
												}, {
													xtype : 'textfield',
													name : 'projectNew.proCharger',
													id : 'proCharger',
													readOnly:true,
													style:"background:#F0F0F0;",
													width : 130
												},{
													xtype:'hidden',
													id:'projectNew.proChargerUser.userId',
													name:'projectNew.proChargerUser.userId'
												}, {
													text : '联系电话:',
													style : 'padding-left:5px',
													width : 90
												}, {
													xtype : 'textfield',
													name : 'projectNew.proChargerTel',
													id : 'proChargerTel',
													readOnly:true,
													style:"background:#F0F0F0;",
													width : 130
												}]
									}, {
										xtype : 'container',
										layout : 'column',
										style : "padding-top:5px;",
										defaults : {
											xtype : 'label'
										},
										items : [{
													text : '跟踪预算员:',
													width : 90
												}, {
													xtype : 'textfield',
													name : 'projectNew.proFollower',
													id : 'proFollower',
													allowBlank : false,
													width : 130,
													listeners : {
														'focus' : function() {
															UserSelector.getView(function(n,l,mobile) {
																				var proFollowerId = Ext.getCmp("proFollower");
																				var proFollowerTelId = Ext.getCmp("proFollowerTel");
																				proFollowerId.setValue(l);
																				proFollowerTelId.setValue(mobile);
																			},false)
																	.show()
														}
													}
												}, {
													text : '联系电话:',
													style : 'padding-left:5px',
													width : 90
												}, {
													xtype : 'textfield',
													name : 'projectNew.proFollowerTel',
													id : 'proFollowerTel',
													width : 130
												}]
									}]
								}]
					}, {
						xtype : "panel",
						layout : "column",
						border : false,
						unstyled : false,
						style : 'padding-top:5px;',
						height : 40,
						items : [{
									xtype : "label",
									text : "设计单位:",
									width : 90
								}, {
									xtype : "textfield",
									name : "projectNew.designUnit",
									id : 'designUnit',
									allowBlank:false,
									width : 135
								}, {
									xtype : "label",
									text : "项目负责人:",
									style : "margin-left:5px;",
									width : 90
								}, {
									xtype : "textfield",
									name : "projectNew.designCharger",
									id : 'designCharger',
									allowBlank:false,
									width : 130
								}, {
									xtype : "label",
									text : "联系电话:",
									width : 90,
									style : "padding-left:5px;"
								}, {
									xtype : "textfield",
									name : "projectNew.designChargerTel",
									id : 'designChargerTel',
									allowBlank:false,
									width : 130
								}]
					}, {
						xtype : "panel",
						layout : "column",
						border : false,
						unstyled : false,
						style : 'padding-top:5px;',
						height : 40,
						items : [{
									xtype : "label",
									text : "建设单位:",
									width : 90
								}, {
									xtype : "textfield",
									name : "projectNew.buildUnit",
									id : 'buildUnit',
									allowBlank:false,
									width : 135
								}, {
									xtype : "label",
									text : "现场负责人:",
									style : "margin-left:5px;",
									width : 90
								}, {
									xtype : "textfield",
									name : "projectNew.buildCharger",
									id : 'buildCharger',
									allowBlank:false,
									width : 130
								}, {
									xtype : "label",
									text : "联系电话:",
									width : 90,
									style : "padding-left:5px;"
								}, {
									xtype : "textfield",
									name : "projectNew.buildChargerTel",
									id : 'buildChargerTel',
									allowBlank:false,
									width : 130
								}]
					}, {
						xtype : "panel",
						layout : "column",
						border : false,
						unstyled : false,
						style : 'padding-top:5px;',
						height : 40,
						items : [{
									xtype : "label",
									text : "监理单位:",
									width : 90
								}, {
									xtype : "textfield",
									name : "projectNew.watchUnit",
									id : 'watchUnit',
									width : 135
								}, {
									xtype : "label",
									text : "现场负责人:",
									style : "margin-left:5px;",
									width : 90
								}, {
									xtype : "textfield",
									name : "projectNew.watchCharger",
									id : 'watchCharger',
									width : 130
								}, {
									xtype : "label",
									text : "联系电话:",
									width : 90,
									style : "padding-left:5px;"
								}, {
									xtype : "textfield",
									name : "projectNew.watchChargerTel",
									id : 'watchChargerTel',
									width : 130
								}]
					}, {
						xtype : "panel",
						layout : "column",
						border : false,
						unstyled : false,
						style : 'padding-top:5px;',
						height : 40,
						items : [{
									xtype : "label",
									text : "总包单位:",
									width : 90
								}, {
									xtype : "textfield",
									name : "projectNew.totalUnit",
									id : 'totalUnit',
									width : 135
								}, {
									xtype : "label",
									text : "现场负责人:",
									style : "margin-left:5px;",
									width : 90
								}, {
									xtype : "textfield",
									name : "projectNew.totalCharger",
									id : 'totalCharger',
									width : 130
								}, {
									xtype : "label",
									text : "联系电话:",
									width : 90,
									style : "padding-left:5px;"
								}, {
									xtype : "textfield",
									name : "projectNew.totalChargerTel",
									id : 'totalChargerTel',
									width : 130
								}]
					}, {
						xtype : "panel",
						layout : "column",
						border : false,
						unstyled : false,
						style : 'padding-top:5px;',
						height : 40,
						items : [{
									xtype : "label",
									text : "合同价:",
									width : 90
								}, {
									xtype : "textfield",
									name : 'projectNew.contractAmount',
									id : 'contractAmount',
									width : 115,
									allowBlank : false,
									readOnly:true,
									style:"background:#F0F0F0;"
								}, {
									xtype : "hidden",
									name : "projectNew.contract.contractId",
									id : 'projectNewContractId'
								}, {
									xtype : "label",
									text : "元",
									width : 20
								}, {
									xtype : "label",
									text : "开工日期:",
									style : "margin-left:5px;",
									width : 90
								}, {
									xtype : "datefield",
									name : "projectNew.startDate",
									id : 'startDate',
									format : 'Y-m-d',
									allowBlank : false,
									width : 125
								}, {
									xtype : "label",
									text : "竣工日期:",
									width : 90,
									style : "padding-left:5px;"
								}, {
									xtype : "datefield",
									name : "projectNew.endDate",
									id : 'endDate',
									format : 'Y-m-d',
									allowBlank : false,
									width : 125
								}]
					}, {
						xtype : "panel",
						layout : "column",
						border : false,
						unstyled : false,
						style : 'padding-top:5px;',
						height : 40,
						items : [{
									xtype : "label",
									text : "业务主办:",
									width : 90
								}, {
									xtype : "textfield",
									name : "projectNew.businessMain",
									id : 'businessMain',
									readOnly:true,
									style:"background:#F0F0F0;",
									width : 135
								}, {
									xtype : "label",
									text : "进场日期:",
									style : "margin-left:5px;",
									width : 90
								}, {
									xtype : "datefield",
									name : "projectNew.enterDate",
									id : 'enterDate',
									format : 'Y-m-d',
									allowBlank : false,
									width : 125
								}]
					}, {
						xtype : 'panel',
						layout : 'column',
						border : false,
						defaultType : 'label',
						style : "padding-top:3px;",
						items : [{
									text : '经办人:',
									width : 90
								}, {
									xtype : 'textfield',
									name : 'projectNew.manager',
									id : 'manager',
									readOnly:true,
									style:"background:#F0F0F0;",
									width : 570,
									value : __currentUser
								}]
					}]
		});
		// 加载表单对应的数据
		if (this.id != null && this.id != 'undefined') {
			this.formPanel.getForm().load({
				deferredRender : false,
				url : __ctxPath + '/statistics/getProjectNew.do?id=' + this.id,
				waitMsg : '正在载入数据...',
				success : function(form, action) {
					var jsonData = Ext.util.JSON
							.decode(action.response.responseText);
					var res = jsonData.data;
					if (res.contract != null) {
						var contractAmount = res.contract.sumPrice;
						var contractId = res.contract.contractId;
						Ext.getCmp("contractAmount").setValue(contractAmount);
						Ext.getCmp("projectNew.contract.contractNo").setValue(res.contract.contractNo);
						Ext.getCmp("projectNewContractId").setValue(contractId);
					}
					if (res.proChargerUser != null) {
						Ext.getCmp("projectNew.proChargerUser.userId").setValue(res.proChargerUser.userId);
					}
					
					var area = res.area;
//					Ext.getCmp("depWorkTreeSelector").setValue(area);
					Ext.getCmp("projectNewArea").setValue(area);
				},
				failure : function(form, action) {
				}
			});
		}
		// 初始化功能按钮
		this.buttons = [{
			text : '保存',
			iconCls : 'btn-save',
			hidden : !(isGranted("_ProjectNewAdd") || isGranted("_ProjectNewEdit")),
			handler : this.save.createCallback(this.formPanel, this)
		},
				// {
				// text : '重置',
				// iconCls : 'btn-reset',
				// handler :this.reset.createCallback(this.formPanel)
				// }
				{
					text : '关闭',
					iconCls : 'btn-cancel',
					handler : this.cancel.createCallback(this)
				}];
	},// end of the initcomponents

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
							var gridPanel = Ext.getCmp('ProjectNewGrid');
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
							window.close();
						}
					});
		}
	}// end of save

});