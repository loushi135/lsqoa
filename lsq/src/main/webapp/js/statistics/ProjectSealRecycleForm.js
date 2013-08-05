/**
 * @author
 * @createtime
 * @class ProjectSealRecycleForm
 * @extends Ext.Window
 * @description ProjectSealRecycle表单
 */
ProjectSealRecycleForm = Ext.extend(Ext.Window, {
	// 内嵌FormPanel
	formPanel : null,
	// 构造函数
	constructor : function(_cfg) {
		Ext.applyIf(this, _cfg);
		// 必须先初始化组件
		this.initUIComponents();
		ProjectSealRecycleForm.superclass.constructor.call(this, {
					id : 'ProjectSealRecycleFormWin',
					layout : 'fit',
					items : this.formPanel,
					modal : true,
					height : 250,
					width : 550,
					maximizable : true,
					title : '项目印章回收详细信息',
					buttonAlign : 'center',
					buttons : this.buttons
				});
	},// end of the constructor
	// 初始化组件
	projectSealFocus : function() {
				ProjectSealSelector.getView(
						function(sealId, proName, content, applyUserName,
								applyUserId, keeperUserName, keeperUserId,
								proArea, proAreaId, proChargerUserName,
								proChargerUserId) {
							Ext.getCmp("projectSealRecycle.sealId")
									.setValue(sealId);
							Ext.getCmp("projectSealRecycle.proName")
									.setValue(proName);
							Ext.getCmp("projectSealRecycle.content")
									.setValue(content);
							Ext.getCmp("projectSealRecycle.applyUserName")
									.setValue(applyUserName);
							Ext.getCmp("projectSealRecycle.keeperUserName")
									.setValue(keeperUserName);

						}, true, true).show();
			},
	initUIComponents : function() {
		this.formPanel = new Ext.FormPanel({
					layout : 'form',
					trackResetOnLoad : true,
					bodyStyle : 'padding:10px 10px 10px 10px',
					border : false,
					url : __ctxPath + '/statistics/saveProjectSealRecycle.do',
					id : 'ProjectSealRecycleForm',
					defaults : {
						anchor : '98%,98%'
					},
					defaultType : 'textfield',
					items : [{
								name : 'projectSealRecycle.id',
								id : 'id',
								xtype : 'hidden',
								value : this.id == null ? '' : this.id
							}, {
								xtype : "container",
								layout : "column",
								height : 25,
								defaults : {
									xtype : 'label',
									style : {
										margin : '0px 0px 3px 0px'
									}
								},
								items : [{
											xtype : 'hidden',
											name : 'projectSealRecycle.projectSeal.id',
											id : 'projectSealRecycle.sealId'
										}, {
											text : '项目名称：',
											width : 120
										}, {
											xtype : 'textfield',
											id : 'projectSealRecycle.proName',
											allowBlank : false,
											emptyText : '请选择项目印章申请',
											width : 350,
											listeners : {
												'focus' : this.projectSealFocus
														.createCallback()
											}
										}]
							}, {
								xtype : "container",
								layout : "column",
								height : 25,
								defaults : {
									xtype : 'label',
									style : {
										margin : '0px 0px 3px 0px'
									}
								},
								items : [{
											text : '项目章刻印内容：',
											width : 120
										}, {
											xtype : 'textfield',
											id : 'projectSealRecycle.content',
											allowBlank : false,
											readOnly : true,
											width : 350,
											emptyText : '请选择项目印章申请',
											listeners : {
												'focus' : this.projectSealFocus
														.createCallback()
											}
										}]
							}, {
								xtype : "panel",
								layout : "column",
								border : false,
								unstyled : false,
								defaults : {
									xtype : 'label',
									style : {
										margin : '0px 0px 3px 0px'
									}
								},
								items : [

								{
											style : 'margin-top:5px',
											text : '项目印章申请人：',
											width : 120
										}, {
											style : 'margin-top:5px',
											xtype : 'textfield',
											id : 'projectSealRecycle.applyUserName',
											allowBlank : false,
											readOnly : true,
											width : 115,
											emptyText : '请选择项目印章申请',
											listeners : {
												'focus' : this.projectSealFocus
														.createCallback()
											}
										}, {
											style : 'margin-top:5px;margin-left:0px',
											text : '项目印章保管人：',
											width : 120
										}, {
											style : 'margin-top:5px',
											xtype : 'textfield',
											id : 'projectSealRecycle.keeperUserName',
											allowBlank : false,
											width : 120,
											readOnly : true,
											emptyText : '请选择项目印章申请',
											listeners : {
												'focus' : this.projectSealFocus
														.createCallback()
											}
										}]
							}, {
								xtype : "panel",
								layout : "column",
								border : false,
								unstyled : false,
								style : {
									margin : '5px 0px 3px 0px'
								},
								defaults : {
									xtype : 'label'
								},
								items : [{
											text : '项目完工内检时间：',
											width : 120
										}, {
											xtype : 'datefield',
											format : 'Y-m-d',
											name : 'projectSealRecycle.innerCheckTime',
											id : 'innerCheckTime',
											allowBlank : false,
											width : 110
										}, {
											text : '最晚归还时间：',
											width : 120
										}, {
											xtype : 'datefield',
											format : 'Y-m-d',
											name : 'projectSealRecycle.lastReturnTime',
											id : 'lastReturnTime',
											allowBlank : false,
											width : 115
										}]
							}, {
								xtype : "panel",
								layout : "column",
								border : false,
								unstyled : false,
								defaults : {
									xtype : 'label',
									style : {
										margin : '0px 0px 3px 0px'
									}
								},
								items : [{
											style : 'margin-top:5px',
											text : '经办人：',
											width : 120
										}, {
											style : 'margin-top:5px',
											xtype : 'hidden',
											name : 'projectSealRecycle.applyUser.userId',
											id : 'projectSealRecycle.applyUser.userId',
											value : this.id == null ? __currentUserId:'' 
										}, {
											style : 'margin-top:5px',
											xtype : 'textfield',
											name : 'projectSealRecycle.applyUser.fullname',
											id : 'projectSealRecycle.applyUser.fullname',
											allowBlank : false,
											readOnly : true,
											width : 350,
											value : this.id == null ? __currentUser:'' 
										}]
							}

					]
				});
		// 加载表单对应的数据
		this.initData();
		// 初始化功能按钮
		this.buttons = [{
					text : '保存',
					iconCls : 'btn-save',
					handler : this.save.createCallback(this.formPanel, this)
				}, {
					text : '重置',
					iconCls : 'btn-reset',
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
				url : __ctxPath + '/statistics/getProjectSealRecycle.do?id='
						+ this.id,
				waitMsg : '正在载入数据...',
				success : function(form, action) {
					var jsonData = Ext.util.JSON
							.decode(action.response.responseText);
					var res = jsonData.data;
					// Ext.getCmp("").originalValue=
					
					Ext.getCmp("projectSealRecycle.sealId").originalValue=res.projectSeal.id;
					Ext.getCmp("projectSealRecycle.sealId").setValue(res.projectSeal.id);
					
					Ext.getCmp("projectSealRecycle.proName").originalValue=res.projectSeal.proName;
					Ext.getCmp("projectSealRecycle.proName").setValue(res.projectSeal.proName);
					
					Ext.getCmp("projectSealRecycle.content").originalValue=res.projectSeal.content;
					Ext.getCmp("projectSealRecycle.content").setValue(res.projectSeal.content);
					
					Ext.getCmp("projectSealRecycle.applyUserName").originalValue=res.projectSeal.applyUser.fullname;
					Ext.getCmp("projectSealRecycle.applyUserName").setValue(res.projectSeal.applyUser.fullname);
					
					Ext.getCmp("projectSealRecycle.keeperUserName").originalValue=res.projectSeal.keeper.fullname;
					Ext.getCmp("projectSealRecycle.keeperUserName").setValue(res.projectSeal.keeper.fullname);
					
					Ext.getCmp("projectSealRecycle.applyUser.fullname").originalValue=res.applyUser.fullname;
					Ext.getCmp("projectSealRecycle.applyUser.fullname").setValue(res.applyUser.fullname);
					
					Ext.getCmp("projectSealRecycle.applyUser.userId").originalValue=res.applyUser.userId;
					Ext.getCmp("projectSealRecycle.applyUser.userId").setValue(res.applyUser.userId);
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
							var gridPanel = Ext
									.getCmp('ProjectSealRecycleGrid');
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