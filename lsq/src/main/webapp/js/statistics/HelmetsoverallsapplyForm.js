/**
 * @author
 * @createtime
 * @class HelmetsoverallsapplyForm
 * @extends Ext.Window
 * @description Helmetsoverallsapply表单
 */
HelmetsoverallsapplyForm = Ext.extend(Ext.Window, {
	// 内嵌FormPanel
	formPanel : null,
	// 构造函数
	constructor : function(_cfg) {
		Ext.applyIf(this, _cfg);
		// 必须先初始化组件
		this.initUIComponents();
		HelmetsoverallsapplyForm.superclass.constructor.call(this, {
					id : 'HelmetsoverallsapplyFormWin',
					layout : 'fit',
					items : this.formPanel,
					modal : true,
					layout : "form",
					bodyStyle : 'padding:10px 10px 10px 10px',
					border : true,
					autoScroll : true,
					bodyStyle : 'padding:10px 10px 10px 10px;',
					defaults : {
						anchor : '98%,98%'
					},
					defaultType : 'textfield',
					width : 650,
					autoHeight : true,
					padding : "10px",
					maximizable : true,
					title : '安全帽工作服详细信息',
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
			url : __ctxPath + '/statistics/saveHelmetsoverallsapply.do',
			id : 'HelmetsoverallsapplyForm',
			defaults : {
				anchor : '98%,98%'
			},
			defaultType : 'textfield',
			items : [{
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
							id : 'id',
							name : 'helmetsoverallsapply.id',
							value : this.id == null ? '' : this.id
						}, {
							xtype : 'hidden',
							name : 'helmetsoverallsapply.areaId',
							id : "areaId"
						}, {
							text : '工程区域：',
							width : 85
						}, {
							xtype : 'textfield',
							name : 'helmetsoverallsapply.areaName',
							id : "areaName",
							emptyText : '请选择项目',
							allowBlank : false,
							editable : false,
							width : 200,
							listeners : {
								'focus' : function() {
									ProjectNewSelector.getView(
											function(proId, proName, proNo,
													contractId, contractNo,
													contractAmount, proAreaId,
													proArea, proChargerUserId,
													proChargerUserName,
													proChargerUserTel,
													proAdress) {
												Ext.getCmp("proID")
														.setValue(proId);
												Ext.getCmp("proName")
														.setValue(proName);
												Ext
														.getCmp("designChargerId")
														.setValue(proChargerUserId);
												Ext
														.getCmp("designChargerName")
														.setValue(proChargerUserName);
												// Ext.getCmp("areaId")
												// .setValue(proAreaId);
												Ext.getCmp("areaName")
														.setValue(proArea);
												if (proAdress == null
														|| proAdress == '') {
													var a = Ext
															.getCmp("address");
													a.emptyText = "请填写工程详细地址";
													a.setRawValue(a.emptyText);

													a.show();
												} else {
													Ext
															.getCmp("address")
															.setValue(proAdress);
												}

												if (proChargerUserTel == null
														|| proChargerUserTel == '') {

													var t = Ext
															.getCmp("designChargerTel");
													t.emptyText = "请填写项目负责人联系电话";
													t.setRawValue(t.emptyText);

													t.show();

												} else {
													Ext
															.getCmp("designChargerTel")
															.setValue(proChargerUserTel);
												}

												if (!Ext.isEmpty(proArea)
														&& Ext
																.isEmpty(proAreaId)) {
													var url = __ctxPath
															+ '/system/getDeptByNameDepartment.do'
													var params = "depName="
															+ proArea;
													var data = ajaxSyncCall(
															url, params).data;
													if (!Ext.isEmpty(data)) {
														Ext
																.getCmp("areaId")
																.setValue(data.depId);
													}
												} else {
													Ext
															.getCmp("areaId")
															.setValue(proAreaId);
												}

											}, true, true).show();
								}
							}
						}, {
							name : "helmetsoverallsapply.proID",
							id : "proID",
							xtype : 'hidden'
						}, {
							text : '项目名称：',
							width : 80
						}, {
							xtype : 'textfield',
							allowBlank : false,
							width : 200,
							emptyText : '请选择项目',
							name : 'helmetsoverallsapply.proName',
							id : "proName",
							editable : false,
							style : 'margin-left:3px;',
							allowBlank : false,
							listeners : {
								'focus' : function() {
									ProjectNewSelector.getView(
											function(proId, proName, proNo,
													contractId, contractNo,
													contractAmount, proAreaId,
													proArea, proChargerUserId,
													proChargerUserName,
													proChargerUserTel,
													proAdress) {
												Ext.getCmp("proID")
														.setValue(proId);
												Ext.getCmp("proName")
														.setValue(proName);
												Ext
														.getCmp("designChargerId")
														.setValue(proChargerUserId);
												Ext
														.getCmp("designChargerName")
														.setValue(proChargerUserName);
												// Ext.getCmp("areaId")
												// .setValue(proAreaId);
												Ext.getCmp("areaName")
														.setValue(proArea);

												Ext.getCmp("areaName")
														.setValue(proArea);
												if (proAdress == null
														|| proAdress == '') {
													var a = Ext
															.getCmp("address");
													a.emptyText = "请填写工程详细地址";
													a.setRawValue(a.emptyText);

													a.show();
												} else {
													Ext
															.getCmp("address")
															.setValue(proAdress);
												}

												if (proChargerUserTel == null
														|| proChargerUserTel == '') {

													var t = Ext
															.getCmp("designChargerTel");
													t.emptyText = "请填写项目负责人联系电话";
													t.setRawValue(t.emptyText);

													t.show();
												} else {
													Ext
															.getCmp("designChargerTel")
															.setValue(proChargerUserTel);
												}
												if (!Ext.isEmpty(proArea)
														&& Ext
																.isEmpty(proAreaId)) {
													var url = __ctxPath
															+ '/system/getDeptByNameDepartment.do'
													var params = "depName="
															+ proArea;
													var data = ajaxSyncCall(
															url, params).data;
													if (!Ext.isEmpty(data)) {
														Ext
																.getCmp("areaId")
																.setValue(data.depId);
													}
												} else {
													Ext
															.getCmp("areaId")
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
							text : '工程详细地址：',
							width : 85
						}, {
							xtype : 'textfield',
							name : 'helmetsoverallsapply.address',
							id : "address",
							allowBlank : false,
							emptyText : '请选择项目',
							width : 475
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
							xtype : 'hidden',
							name : 'helmetsoverallsapply.designChargerId',
							id : "designChargerId"
						}, {
							text : '项目负责人：',
							width : 85
						}, {
							xtype : 'textfield',
							name : 'helmetsoverallsapply.designChargerName',
							editable : false,
							id : "designChargerName",
							allowBlank : false,
							emptyText : '请选择项目',
							width : 200,
							listeners : {
								'focus' : function() {
									ProjectNewSelector.getView(
											function(proId, proName, proNo,
													contractId, contractNo,
													contractAmount, proAreaId,
													proArea, proChargerUserId,
													proChargerUserName,
													proChargerUserTel,
													proAdress) {
												Ext.getCmp("proID")
														.setValue(proId);
												Ext.getCmp("proName")
														.setValue(proName);
												Ext
														.getCmp("designChargerId")
														.setValue(proChargerUserId);
												Ext
														.getCmp("designChargerName")
														.setValue(proChargerUserName);
												// Ext.getCmp("areaId")
												// .setValue(proAreaId);
												Ext.getCmp("areaName")
														.setValue(proArea);

												Ext.getCmp("areaName")
														.setValue(proArea);
												if (proAdress == null
														|| proAdress == '') {
													var a = Ext
															.getCmp("address");
													a.emptyText = "请填写工程详细地址";
													a.setRawValue(a.emptyText);

													a.show();
												} else {
													Ext
															.getCmp("address")
															.setValue(proAdress);
												}

												if (proChargerUserTel == null
														|| proChargerUserTel == '') {

													var t = Ext
															.getCmp("designChargerTel");
													t.emptyText = "请填写项目负责人联系电话";
													t.setRawValue(t.emptyText);

													t.show();
												} else {
													Ext
															.getCmp("designChargerTel")
															.setValue(proChargerUserTel);
												}
												if (!Ext.isEmpty(proArea)
														&& Ext
																.isEmpty(proAreaId)) {
													var url = __ctxPath
															+ '/system/getDeptByNameDepartment.do'
													var params = "depName="
															+ proArea;
													var data = ajaxSyncCall(
															url, params).data;
													if (!Ext.isEmpty(data)) {
														Ext
																.getCmp("areaId")
																.setValue(data.depId);
													}
												} else {
													Ext
															.getCmp("areaId")
															.setValue(proAreaId);
												}
											}, true, true).show();
								}
							}
						}, {
							text : '项目负责人联系电话：',
							width : 120
						}, {
							xtype : 'textfield',
							emptyText : '请选择项目',
							name : 'helmetsoverallsapply.designChargerTel',
							id : "designChargerTel",
							allowBlank : false,
							width : 160
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
							text : '收货人：',
							width : 85
						}, {
							xtype : 'textfield',
							name : 'helmetsoverallsapply.takeUserName',
							id : "takeUserName",
							allowBlank : false,
							width : 200,
							emptyText : '请选择一个收货人',
							listeners : {
								'focus' : function() {
									UserSelector.getView(
											function(id, name, mo) {
												if (id.indexOf(",") != -1) {
													Ext.getCmp("takeUserId")
															.setValue("");
													return;
												}
												Ext.getCmp("takeUserId")
														.setValue(id);
												Ext.getCmp("takeUserName")
														.setValue(name);
												if (mo == null || mo == '') {
													Ext.getCmp("takeUserTel").emptyText = "请填写收货人联系电话"
												} else {
													Ext.getCmp("takeUserTel")
															.setValue(mo);
												}

											}, false).show();
								}
							}
						}, {
							xtype : 'hidden',
							name : 'helmetsoverallsapply.takeUserId',
							id : "takeUserId",
							allowBlank : false
						}, {
							text : '收货人联系电话：',
							width : 120
						}, {
							xtype : 'textfield',
							name : 'helmetsoverallsapply.takeUserTel',
							id : "takeUserTel",
							emptyText : '请选择收货人',
							allowBlank : false,
							width : 160
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
					xtype : "panel",
					layout : "form",
					border : false,
					unstyled : false,
					defaults : {
						xtype : 'label',
						style : {
							margin : '0px 0px 3px 0px'
						}
					},
					items : [{
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
									text : '安全帽颜色:',
									width : 100
								}, {
									width : 300,
									xtype : "panel",
									layout : "form",
									border : false,
									unstyled : false,
									defaults : {
										style : {
											margin : '0px 0px 3px 0px'
										}
									},
									defaultType : "textfield",
									items : [{
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
													style : "padding-left:35px",
													width : 100,
													text : "红色:"
												}, {
													xtype : 'numberfield',
													name : 'helmetsoverallsapply.redNum',
													id : "redNum",
													allowBlank : false,
													width : 160
												}, {
													text : "顶"
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
													style : "padding-left:35px",
													width : 100,
													text : "黄色:"
												}, {
													xtype : 'numberfield',
													name : 'helmetsoverallsapply.yellowNum',
													id : "yellowNum",
													allowBlank : false,
													width : 160
												}, {
													text : "顶"
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
													style : "padding-left:35px",
													width : 100,
													text : "白色:"
												}, {
													xtype : 'numberfield',
													name : 'helmetsoverallsapply.whiteNum',
													id : "whiteNum",
													allowBlank : false,
													width : 160
												}, {
													text : "顶"
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
													style : "padding-left:35px",
													width : 100,
													text : "蓝色:"
												}, {
													xtype : 'numberfield',
													name : 'helmetsoverallsapply.blueNum',
													id : "blueNum",
													allowBlank : false,
													width : 160
												}, {
													text : "顶"
												}]
									}]
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
									text : '工作服类别:',
									width : 100
								}, {
									width : 300,
									xtype : "panel",
									layout : "form",
									border : false,
									unstyled : false,
									defaults : {
										// xtype : 'label',
										style : {
											margin : '0px 0px 3px 0px'
										}
									},
									defaultType : "textfield",
									items : [{
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
													width : 100,
													text : "长袖：XL:"
												}, {
													xtype : 'numberfield',
													name : 'helmetsoverallsapply.longSleeveXL',
													id : "longSleeveXL",
													allowBlank : false,
													width : 160
												}, {
													text : "件"
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
													style : "padding-left:35px",
													width : 100,
													text : "XXL :"
												}, {
													xtype : 'numberfield',
													name : 'helmetsoverallsapply.longSleeveXXL',
													id : "longSleeveXXL",
													allowBlank : false,
													width : 160
												}, {
													text : "件"
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
													style : "padding-left:35px",
													width : 100,
													text : "XXXL :"
												}, {
													xtype : 'numberfield',
													name : 'helmetsoverallsapply.longSleeveXXXL',
													id : "longSleeveXXXL",
													allowBlank : false,
													width : 160
												}, {
													text : "件"
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
													width : 100,
													text : "短袖：XL:"
												}, {
													xtype : 'numberfield',
													name : 'helmetsoverallsapply.shortSleeveXL',
													id : "shortSleeveXL",
													allowBlank : false,
													width : 160
												}, {
													text : "件"
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
													style : "padding-left:35px",
													width : 100,
													text : "XXL :"
												}, {
													xtype : 'textfield',
													name : 'helmetsoverallsapply.shortSleeveXXL',
													id : "shortSleeveXXL",
													allowBlank : false,
													width : 160
												}, {
													text : "件"
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
													style : "padding-left:35px",
													width : 100,
													text : "XXXL :"
												}, {
													xtype : 'textfield',
													name : 'helmetsoverallsapply.shortSleeveXXXL',
													id : "shortSleeveXXXL",
													allowBlank : false,
													width : 160
												}, {
													text : "件"
												}]
									}]
								}]
					}]
				}, {
					text : '备注:',
					width : 35
				}, {
					xtype : 'textarea',
					name : 'helmetsoverallsapply.remark',
					id : "remark",
					width : 250,
					height : 280,
					allowBlank : false,
					autoScroll : true
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
				url : __ctxPath + '/statistics/getHelmetsoverallsapply.do?id='
						+ this.id,
				waitMsg : '正在载入数据...',
				success : function(form, action) {
					var jsonData = Ext.util.JSON
							.decode(action.response.responseText);
					var res = jsonData.data;
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
							var gridPanel = Ext
									.getCmp('HelmetsoverallsapplyGrid');
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