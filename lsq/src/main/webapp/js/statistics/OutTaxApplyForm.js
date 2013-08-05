/**
 * @author
 * @createtime
 * @class OutTaxApplyForm
 * @extends Ext.Window
 * @description OutTaxApply表单
 */
OutTaxApplyForm = Ext.extend(Ext.Window, {
	//内嵌FormPanel
	formPanel : null,
	//构造函数
	constructor : function(_cfg) {
		Ext.applyIf(this, _cfg);
		//必须先初始化组件
		this.initUIComponents();
		OutTaxApplyForm.superclass.constructor.call(this, {
			id : 'OutTaxApplyFormWin',
			layout : 'fit',
			items : this.formPanel,
			modal : true,
			height : 300,
			width : 550,
			padding : "10px",
			maximizable : true,
			title : '[外经证申请]详细信息',
			buttonAlign : 'center',
			buttons : this.buttons
		});
	},//end of the constructor
	//初始化组件
	initUIComponents : function() {
		this.formPanel = new Ext.FormPanel({
			layout : 'form',
			trackResetOnLoad : true,
			bodyStyle : 'padding:10px 10px 10px 10px',
			border : false,
			url : __ctxPath + '/statistics/saveOutTaxApply.do',
			id : 'OutTaxApplyForm',
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
							name : 'outTaxApply.id',
							id : 'id',
							xtype : 'hidden',
							value : this.id == null ? '' : this.id
						}, {
							xtype : 'hidden',
							name : 'outTaxApply.projectNew.id',
							id : 'outTaxApply.proId'
						}, {
							text : '项目名称：',
							width : 100
						}, {
							xtype : 'textfield',
							id : 'outTaxApply.proName',
							allowBlank : false,
							emptyText : '请选择项目',
							width : 130,
							listeners : {
								'focus' : function() {
									ProjectNewSelector.getView(function(proId, proName, proNo, contractId, contractNo, contractAmount, proAreaId, proArea, proChargerUserId, proChargerUserName, proChargerUserTel, proAdress) {
										Ext.getCmp("outTaxApply.proId").setValue(proId);
										Ext.getCmp("outTaxApply.proName").setValue(proName);
										Ext.getCmp("outTaxApply.proNo").setValue(proNo);
										Ext.getCmp("outTaxApply.proAddress").setValue(proAdress);
									}, true, true).show();
								}
							}
						}, {
							text : '项目编号：',
							width : 100
						}, {
							xtype : 'textfield',
							id : 'outTaxApply.proNo',
							allowBlank : false,
							emptyText : '请选择项目',
							width : 130,
							listeners : {
								'focus' : function() {
									ProjectNewSelector.getView(function(proId, proName, proNo, contractId, contractNo, contractAmount, proAreaId, proArea, proChargerUserId, proChargerUserName, proChargerUserTel, proAdress) {
										Ext.getCmp("outTaxApply.proId").setValue(proId);
										Ext.getCmp("outTaxApply.proName").setValue(proName);
										Ext.getCmp("outTaxApply.proNo").setValue(proNo);
										Ext.getCmp("outTaxApply.proAddress").setValue(proAdress);
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
							text : '项目地点：',
							width : 100
						}, {
							xtype : 'textfield',
							id : 'outTaxApply.proAddress',
							allowBlank : false,
							emptyText : '请选择项目',
							width : 130,
							listeners : {
								'focus' : function() {
									ProjectNewSelector.getView(function(proId, proName, proNo, contractId, contractNo, contractAmount, proAreaId, proArea, proChargerUserId, proChargerUserName, proChargerUserTel, proAdress) {
										Ext.getCmp("outTaxApply.proId").setValue(proId);
										Ext.getCmp("outTaxApply.proName").setValue(proName);
										Ext.getCmp("outTaxApply.proNo").setValue(proNo);
										Ext.getCmp("outTaxApply.proAddress").setValue(proAdress);
									}, true, true).show();
								}
							}

						}, {
							text : '附件类型：',
							width : 100
						}, {
							xtype : 'combo',
							triggerAction : 'all',
							editable : false,
							name : 'outTaxApply.attathType',
							id : 'attathType',
							store : ["合同", "审定报告"],
							allowBlank : false,
							width : 125
						}]
				}, {
					xtype : "container",
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
							text : '金额小写(元)：',
							width : 100
						}, {
							name : 'outTaxApply.amountXX',
							id : 'amountXX',
							allowBlank : false,
							width : 354,
							xtype : 'numberfield',
							regex : /^(([1-9]\d{0,9})|0)(\.\d{1,2})?$/,
							maxLength : 12,
							enableKeyEvents : true,
							listeners : {
								keyup : function(field) {
									var value = Ext.getCmp("amountXX").getValue();
									var loanMoneyBig = AmountInWords(value);
									Ext.getCmp("amountDX").setValue(loanMoneyBig);
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
							text : '金额大写(元)：',
							width : 100
						}, {
							xtype : 'textfield',
							name : 'outTaxApply.amountDX',
							id : 'amountDX',
							allowBlank : false,
							readOnly : true,
							style : "background:#F0F0F0;",
							width : 354
						}]
				}, {
					xtype : 'container',
					autoHeight : true,
					layout : 'column',
					autoWidth : true,
					defaultType : 'label',
					style : 'padding-left:0px;padding-bottom:5px;',
					items : [{
							text : '附件:',
							width : 95,
							style : "margin-top:15px;"
						}, {
							xtype : 'hidden',
							name : 'attachIds',
							id : 'outTaxApply.attachIds'
						}, {
							xtype : 'hidden',
							id : 'outTaxApply.attachFiles'
						}, {
							xtype : 'panel',
							id : 'outTaxApply.displayAttach',
							columnWidth : .95,
							height : 65,
							frame : false,
							autoScroll : true,
							style : 'padding-left:10px;padding-top:0px;',
							html : ''
						}, {
							xtype : 'button',
							iconCls : 'btn-upload',
							text : '上传',
							handler : function() {
								var dialog = App.createUploadDialog({
									file_cat : 'flow/outTaxApply',
									callback : function(data) {
										var attachFiles = Ext.getCmp("outTaxApply.attachFiles");
										var fileIds = Ext.getCmp('outTaxApply.attachIds');
										var display = Ext.getCmp('outTaxApply.displayAttach');
										display.body.update('');
										attachFiles.setValue('');
										fileIds.setValue('');
										for (var i = 0; i < data.length; i++) {
											if (fileIds.getValue() != '') {
												fileIds.setValue(fileIds.getValue() + ',');
												attachFiles.setValue(attachFiles.getValue() + ',');
											}
											attachFiles.setValue(attachFiles.getValue() + data[i].filepath + ":" + data[i].filename.replace(/\s+/g, ""));
											fileIds.setValue(fileIds.getValue() + data[i].fileId);
											Ext.DomHelper.append(display.body, '<span><a href="#" onclick="FileAttachDetail.show(' + data[i].fileId + ')">' + data[i].filename.replace(/\s+/g, "") + '</a>&nbsp;|&nbsp;</span>');
										}
									},
									permitted_max_size : 1024 * 1024 * 20
								});
								dialog.show(this);
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
					items : [{
							text : '经办人：',
							width : 100
						}, {
							xtype : 'textfield',
							id : 'outTaxApply.userCreate',
							allowBlank : false,
							readOnly : true,
							width : 354,
							style : "background:#F0F0F0;"
						}]
				}]
		});
		//加载表单对应的数据	
		this.initData();
		//初始化功能按钮
		this.buttons = [{
				text : '保存',
				iconCls : 'btn-save',
				hidden : !(isGranted("_OutTaxApplyAdd") || isGranted("_OutTaxApplyEdit")),
				handler : this.save.createCallback(this.formPanel, this)
			}, {
				text : '重置',
				iconCls : 'btn-reset',
				hidden : !(isGranted("_OutTaxApplyAdd") || isGranted("_OutTaxApplyEdit")),
				handler : this.reset.createCallback(this.formPanel)
			}, {
				text : '取消',
				iconCls : 'btn-cancel',
				handler : this.cancel.createCallback(this)
			}];
	},//end of the initcomponents
	/**
	 * 初始化数据
	 * 
	 * @param {}
	 *            formPanel
	 */
	initData : function() {
		//加载表单对应的数据	
		if (this.id != null && this.id != 'undefined') {
			this.formPanel.getForm().load({
				deferredRender : false,
				url : __ctxPath + '/statistics/getOutTaxApply.do?id=' + this.id,
				waitMsg : '正在载入数据...',
				success : function(form, action) {
					var jsonData = Ext.util.JSON.decode(action.response.responseText);
					var res = jsonData.data;
					//Ext.getCmp("").originalValue=
					Ext.getCmp("outTaxApply.userCreate").setValue(res.userCreate.fullname);

					Ext.getCmp("outTaxApply.proName").setValue(res.projectNew.proName);
					Ext.getCmp("outTaxApply.proNo").setValue(res.projectNew.proNo);
					Ext.getCmp("outTaxApply.proAddress").setValue(res.projectNew.proAddr);

					Ext.getCmp("outTaxApply.proId").setValue(res.projectNew.proId);
					Ext.getCmp("outTaxApply.proId").originalValue = res.projectNew.proId;

					var af = res.fileAttachs;
					var filePanel = Ext.getCmp('outTaxApply.displayAttach');
					var fileIds = Ext.getCmp("outTaxApply.attachIds");
					for (var i = 0; i < af.length; i++) {
						if (fileIds.getValue() != '') {
							fileIds.setValue(fileIds.getValue() + ',');
						}
						fileIds.setValue(fileIds.getValue() + af[i].fileId);
						Ext.DomHelper.append(filePanel.body, '<span><a href="#" onclick="FileAttachDetail.show(' + af[i].fileId + ')">' + af[i].fileName.replace(/\s+/g, "") + '</a>&nbsp;|&nbsp;</span>');
					}
					fileIds.originalValue = fileIds.getValue();

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
					var gridPanel = Ext.getCmp('OutTaxApplyGrid');
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
					//window.close();
				}
			});
		}
	}//end of save

});