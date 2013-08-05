/**
 * @author
 * @createtime
 * @class ProjectSealForm
 * @extends Ext.Window
 * @description ProjectSeal表单
 */
ProjectSealForm = Ext.extend(Ext.Window, {
	// 内嵌FormPanel
	formPanel : null,
	// 构造函数
	constructor : function(_cfg) {
		Ext.applyIf(this, _cfg);
		// 必须先初始化组件
		this.initUIComponents();
		ProjectSealForm.superclass.constructor.call(this, {
					id : 'ProjectSealFormWin',
					layout : 'fit',
					items : this.formPanel,
					modal : true,
					width : 550,
					maximizable : true,
					title : '项目印章申请详细信息',
					buttonAlign : 'center',
					buttons : this.buttons
				});
	},// end of the constructor
	// 初始化组件
	initUIComponents : function() {
		this.formPanel = new Ext.FormPanel({
			layout : 'form',
			trackResetOnLoad : true,
			bodyStyle : 'padding:10px 10px 10px 10px',
			border : false,
			url : __ctxPath + '/statistics/saveProjectSeal.do',
			id : 'ProjectSealForm',
			defaults : {
				anchor : '98%,98%'
			},
			autoScroll : true,
			width : 500,
			autoHeight : true,
			padding : "10px",
			defaultType : 'textfield',
			items : [{
						name : 'projectSeal.id',
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
									name : 'bpaDeptId',
									id : 'bpaDeptId'
								}, {
									xtype : 'hidden',
									name : 'projectSeal.proId',
									id : 'proId'
								}, {
									text : '项目名称：',
									width : 100
								}, {
									xtype : 'textfield',
									name : 'projectSeal.proName',
									id : 'proName',
									allowBlank : false,
									emptyText : '请选择项目',
									width : 350,
									listeners : {
										'focus' : function() {
											ProjectNewSelector.getView(
													function(proId, proName,
															proNo, contractId,
															contractNo,
															contractAmount,
															proAreaId, proArea,
															proChargerUserId,
															proChargerUserName,
															proChargerUserTel,
															proAdress) {
														Ext
																.getCmp("proId")
																.setValue(proId);
														Ext
																.getCmp("proName")
																.setValue(proName);
														if (!Ext
																.isEmpty(proArea)
																&& Ext
																		.isEmpty(proAreaId)) {
															var url = __ctxPath
																	+ '/system/getDeptByNameDepartment.do'
															var params = "depName="
																	+ proArea;
															var data = ajaxSyncCall(
																	url, params).data;
															if (!Ext
																	.isEmpty(data)) {
																Ext
																		.getCmp("bpaDeptId")
																		.setValue(data.depId);
															}
														} else {
															Ext
																	.getCmp("bpaDeptId")
																	.setValue(proAreaId);
														}

													}, true, true).show();
										}
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
									width : 100
								}, {
									xtype : 'textfield',
									name : 'projectSeal.content',
									id : 'content',
									allowBlank : false,
									width : 350
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
									style : 'margin-top:15px',
									text : '保管责任人：',
									width : 100
								}, {
									xtype : 'hidden',
									name : 'projectSeal.keeper.userId',
									id : 'keeperId'
								}, {
									style : 'margin-top:15px',
									xtype : 'textfield',
									name : 'projectSeal.keeper.fullname',
									id : 'keeperName',
									allowBlank : false,
									width : 150,
									emptyText : '请选择一个保管责任人',
									listeners : {
										'focus' : function() {
											UserSelector.getView(
													function(id, name, mo) {
														if (id.indexOf(",") != -1) {
															Ext
																	.getCmp("keeperName")
																	.setValue("");
															return;
														}
														Ext.getCmp("keeperId")
																.setValue(id);
														Ext
																.getCmp("keeperName")
																.setValue(name);

													}, false).show();
										}
									}
								}, {
									xtype : "container",
									layout : "form",
									border : false,
									width : 220,
									labelWidth : 60,
									height : 60,
									unstyled : false,
									defaults : {
										xtype : 'datefield',
										style : {
											margin : '0px 0px 3px 0px'
										}
									},
									items : [{
										fieldLabel : "申领时间",
										labelStyle : 'text-align:right;width:60;',
										id : "applyDate",
										name : "projectSeal.applyDate",
										width : 135,
										format : 'Y-m-d'
									}, {
										fieldLabel : "归还时间",
										labelStyle : 'text-align:right;width:60;',
										id : "returnDate",
										name : "projectSeal.returnDate",
										format : 'Y-m-d',
										width : 135
									}

									]
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
									text : '经办人：',
									width : 100
								}, {
									xtype : 'hidden',
									name : 'projectSeal.applyUser.userId',
									id : 'applyUserId',
									value :this.id == null ?  __currentUserId:''
								}, {
									xtype : 'textfield',
									name : 'projectSeal.applyUser.fullname',
									id : 'applyUserName',
									allowBlank : false,
									readOnly : true,
									width : 350,
									value :this.id == null ?  __currentUser:''
								}]
					}]
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
				url : __ctxPath + '/statistics/getProjectSeal.do?id=' + this.id,
				waitMsg : '正在载入数据...',
				success : function(form, action) {
					var jsonData = Ext.util.JSON
							.decode(action.response.responseText);
					var res = jsonData.data;
					
					Ext.getCmp("keeperName").originalValue=res.keeper.fullname;
					Ext.getCmp("keeperName").setValue(res.keeper.fullname);
					Ext.getCmp("keeperId").originalValue=res.keeper.userId;
					Ext.getCmp("keeperId").setValue(res.keeper.userId);
					
					Ext.getCmp("applyUserId").originalValue=res.applyUser.userId;
					Ext.getCmp("applyUserId").setValue(res.applyUser.userId);
					Ext.getCmp("applyUserName").originalValue=res.applyUser.fullname;
					Ext.getCmp("applyUserName").setValue(res.applyUser.fullname);
					
					// Ext.getCmp("").originalValue=
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
							var gridPanel = Ext.getCmp('ProjectSealGrid');
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