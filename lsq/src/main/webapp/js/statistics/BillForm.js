/**
 * @author
 * @createtime
 * @class BillForm
 * @extends Ext.Window
 * @description Bill表单
 */
BillForm = Ext.extend(Ext.Window, {
	// 内嵌FormPanel
	formPanel : null,
	// 构造函数
	constructor : function(_cfg,_cfg1,_cfg2) {
		Ext.applyIf(this, _cfg,_cfg1,_cfg2);
		// 必须先初始化组件
		this.initUIComponents();
		BillForm.superclass.constructor.call(this, {
					id : 'BillFormWin',
					layout : 'fit',
					items : this.formPanel,
					modal : true,
					height : 250,
					width : 400,
					maximizable : true,
					title : '[发票]详细信息',
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
			url : __ctxPath + '/statistics/saveBill.do',
			id : 'BillForm',
			defaults : {
				anchor : '98%,98%'
			},
			defaultType : 'textfield',
			items : [{
						name : 'bill.id',
						id : 'id',
						xtype : 'hidden',
						value : this.id == null ? '' : this.id
					}, {
						xtype : 'hidden',
						id : 'bill.projectNew.id',
						name : 'bill.projectNew.id'
					}, {
						fieldLabel : '项目名称',
						xtype : 'textfield',
						width : 200,
						readOnly : false,
						maxLength : 128,
						allowBlank : false,
						id : 'bill.projectNew.proName',
						name : 'bill.projectNew.proName',
						listeners : {
							'focus' : function() {
								ProjectNewSelector.getView(
										function(proId, proName) {
											Ext.getCmp("bill.projectNew.id")
													.setValue(proId);
											Ext.getCmp("bill.projectNew.proName")
													.setValue(proName);
										}, true, true).show();
							},
							afterrender : function(field) {
								// Ext.getCmp("expressApplyProContainer").hide();
							}
						}
					}, {
						name : 'bill.suppliersAssess.suppliersId',
						id : 'bill.suppliersAssess.suppliersId',
						xtype : 'hidden'
					}, {
						fieldLabel : '供应商',
						xtype : "textfield",
						name : "bill.suppliersAssess.suppliersName",
						id : 'bill.suppliersAssess.suppliersName',
						width : 250,
						allowBlank : false,
						readOnly : true,
						listeners : {
							focus : function() {
								new SuppliersAssessSelector(function(
										suppliersId, supplierName, supplierNo) {
									Ext
											.getCmp("bill.suppliersAssess.suppliersName")
											.setValue(supplierName);
									Ext
											.getCmp("bill.suppliersAssess.suppliersId")
											.setValue(suppliersId);
										// }
								}, true).show();
							}
						}
					}, {
						fieldLabel : '发票类型',
						name : 'bill.invoiceType',
						id : 'bill.invoiceType',
						xtype : 'combo',
						width : 200,
						editable : false,
						emptyText : '请选择发票类型',
						allowBlank : false,
						triggerAction : 'all',
						store : [],
						listeners : {
							afterrender : function(d) {
								var c = Ext.getCmp("bill.invoiceType")
										.getStore();
								if (c.getCount() <= 0) {
									Ext.Ajax.request({
												url : __ctxPath
														+ "/system/loadDictionary.do",
												method : "post",
												params : {
													itemName : "发票类型"
												},
												success : function(f) {
													var e = Ext.util.JSON
															.decode(f.responseText);
													c.loadData(e);
												}
											});
								}
							}
						}
					}, {
						fieldLabel : '发票时间',
						allowBlank : false,
						editable : false,
						name : 'bill.billTime',
						id : 'billTime',
						xtype : 'datefield',
						format : 'Y-m-d'
					}, {
						fieldLabel : '发票金额',
						allowBlank : false,
						name : 'bill.amount',
						id : 'amount',
						enableKeyEvents : true,
						xtype : 'numberfield',
						regex : /^(([1-9]\d{0,9})|0)(\.\d{1,2})?$/,
						regexText : '请输入正确格式的金额',
						listeners : {
							keyup : function(field) {
								var paymentSumBig = AmountInWords(field
										.getValue());
								Ext.getCmp('amountBig').setValue(paymentSumBig);
							}
						}
					}, {
						fieldLabel : '发票金额大写',
						name : 'bill.amountBig',
						id : 'amountBig',
						readOnly : true
					}]
		});
		// 加载表单对应的数据
		if (this.id != null && this.id != 'undefined') {
			this.formPanel.getForm().load({
				deferredRender : false,
				url : __ctxPath + '/statistics/getBill.do?id=' + this.id,
				waitMsg : '正在载入数据...',
				success : function(form, action) {
					var record = Ext.util.JSON
							.decode(action.response.responseText);
//					Ext.getCmp("bill.materialContract.id")
//							.setValue(record.data.materialContract.id);
//					Ext.getCmp("bill.materialContract.id").originalValue = record.data.materialContract.id;
					
							
					Ext.getCmp("bill.invoiceType")
							.setValue(record.data.invoiceType);
					Ext.getCmp("bill.invoiceType").originalValue = record.data.invoiceType;
							
					Ext.getCmp("bill.projectNew.proName")
							.setValue(record.data.projectNew.proName);
					Ext.getCmp("bill.projectNew.proName").originalValue = record.data.projectNew.proName;
					Ext.getCmp("bill.projectNew.id")
							.setValue(record.data.projectNew.id);
					Ext.getCmp("bill.projectNew.id").originalValue = record.data.projectNew.id;
					
					Ext.getCmp("bill.suppliersAssess.suppliersId")
							.setValue(record.data.suppliersAssess.suppliersId);
					Ext.getCmp("bill.suppliersAssess.suppliersId").originalValue = record.data.suppliersAssess.suppliersId;
					
					Ext.getCmp("bill.suppliersAssess.suppliersName")
							.setValue(record.data.suppliersAssess.suppliersName);
					Ext.getCmp("bill.suppliersAssess.suppliersName").originalValue = record.data.suppliersAssess.suppliersName;
				},
				failure : function(form, action) {
				}
			});
		}
		// 初始化功能按钮
		this.buttons = [{
					text : '保存',
					iconCls : 'btn-save',
					hidden:!(isGranted("_BillAdd")||isGranted("_BillEdit")) ,
					handler : this.save.createCallback(this.formPanel, this)
				}, {
					text : '重置',
					iconCls : 'btn-reset',
					hidden:!(isGranted("_BillAdd")||isGranted("_BillEdit")) ,
					handler : this.reset.createCallback(this.formPanel)
				}, {
					text : '取消',
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
							var gridPanel = Ext.getCmp('BillGrid');
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