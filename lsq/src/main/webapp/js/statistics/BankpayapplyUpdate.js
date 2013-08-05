/**
 * @author
 * @createtime
 * @class BankpayapplyUpdate
 * @extends Ext.Window
 * @description Bankpayapply表单
 */
BankpayapplyUpdate = Ext.extend(Ext.Window, {
	// 内嵌FormPanel
	formPanel : null,
	// 构造函数
	constructor : function(_cfg) {
		Ext.applyIf(this, _cfg);
		// 必须先初始化组件
		this.initUIComponents();
		BankpayapplyUpdate.superclass.constructor.call(this, {
					id : 'BankpayapplyUpdateWin',
					layout : 'fit',
					items : this.formPanel,
					modal : true,
					width : 700,
					height : 500,
					maximizable : true,
					title : '变更工程项目银行付款',
					buttonAlign : 'center',
					buttons : this.buttons
				});
	},// end of the constructor
	// 初始化组件
	initUIComponents : function() {
		this.formPanel = new Ext.FormPanel({
			layout : 'form',
			autoScroll : true,
			trackResetOnLoad : true,
			bodyStyle : 'padding:10px 10px 10px 10px;',
			border : false,
			url : __ctxPath + '/statistics/updateBankpayapply.do',
			id : 'BankpayapplyUpdate',
			defaults : {
				anchor : '95%,95%'
			},
			defaultType : 'textfield',
			items : [{
						xtype : "panel",
						layout : "column",
						border : false,
						unstyled : false,
						style : 'margin-top:20px;',
						items : [{
									xtype : "label",
									name : "MyLabel1",
									text : "项目名称:",
									width : 90
								}, {
									xtype : 'hidden',
									name : 'bankpayapply.bankPayApplyId',
									value : this.bankPayApplyId
								},{
													xtype : "hidden",
													name :"bankpayapply_oldApplyMoneyXX",
													value : this.bpaApplyMoneyXX
												}, {
									xtype : "textfield",
									// name : "bankpayapply.bpaProjectName",
									width : 140,
									allowBlank : false,
									readOnly : true,
									style : "background:#F0F0F0;",
									value : this.bpaProjectName
								}, {
									xtype : "label",
									name : "MyLabel2",
									text : "项目编号:",
									style : "padding-left:5px;"
								}, {
									xtype : "textfield",
									// name : "bankpayapply.bpaProjectNo",
									width : 160,
									allowBlank : false,
									readOnly : true,
									style : "background:#F0F0F0;",
									value : this.bpaProjectNo
								}]
					}, {
						xtype : "panel",
						layout : "column",
						border : false,
						unstyled : false,
						style : 'margin-top:5px;',
						anchor : "100%",
						items : [{
									xtype : "label",
									text : "收款单位:",
									width : 90
								}, {
									xtype : "textfield",
									// name : "bankpayapply.bpaReceiptDept",
									width : 528,
									allowBlank : false,
									readOnly : true,
									readOnly : true,
									value : this.bpaReceiptDept
								}]
					}, {
						xtype : "panel",
						layout : "column",
						border : false,
						unstyled : false,
						style : 'margin-top:5px;',
						items : [{
									xtype : "label",
									name : "MyLabel1",
									text : "付款事由:",
									height : 50,
									width : 90
								}, {
									xtype : "textarea",
									// name : "bankpayapply.bpaReceiptReason",
									maxLength : 190,
									allowBlank : false,
									readOnly : true,
									width : 522,
									height : 50,
									value : this.bpaReceiptReason
								}]
					}, {
						xtype : "panel",
						layout : "column",
						border : false,
						unstyled : false,
						style : 'padding-top:5px;',
						items : [{
									xtype : "label",
									text : "本次申请用款:",
									width : 90,
									style : "margin-top:15px;"
								}, {
									xtype : 'container',
									style : 'padding-left:0px;padding-bottom:5px;',
									items : [{
										xtype : 'container',
										layout : 'column',
										style : 'padding-left:0px;padding-bottom:5px;',
										items : [{
													xtype : "label",
													text : "小写:",
													width : 40
												}, {
													xtype : "numberfield",
													cls : 'textfieldBG',
//													id : 'bpaApplyMoneyXX',
//													name :"bankpayapply_nowApplyMoneyXX",
													allowBlank : false,
													width : 490,
													value : this.bpaApplyFirstMoneyXX,
													readOnly : true
//													enableKeyEvents:true,
//													regex:/^(([1-9]\d{0,9})|0)(\.\d{1,2})?$/,
//													regexText:'请输入正确格式的金额',
//													listeners : {
//														keyup: function(field) {	
//															var paymentSumBig = AmountInWords(field.getValue());
//															Ext.getCmp('bankpayapply_nowApplyMoneyDX').setValue(paymentSumBig); 
//														} 
//													}
												}]
									}, {
										xtype : 'container',
										layout : 'column',
										style : 'padding-left:0px;padding-bottom:5px;',
										items : [{
													xtype : "label",
													text : "大写:",
													width : 40
												}, {
													xtype : "textfield",
													id : 'bankpayapply_nowApplyMoneyDX',
//													name : "bankpayapply_nowApplyMoneyDX",
													width : 490,
													allowBlank : false,
													readOnly : true,
													editable : false,
													style : "background:#F0F0F0;",
													value : this.bpaApplyFirstMoneyDX
												}]
									}]
								}]
					},{
						xtype : "panel",
						layout : "column",
						border : false,
						unstyled : false,
						style : 'padding-top:5px;',
						items : [{
									xtype : "label",
									text : "本次实际用款:",
									width : 90,
									style : "margin-top:15px;"
								}, {
									xtype : 'container',
									style : 'padding-left:0px;padding-bottom:5px;',
									items : [{
										xtype : 'container',
										layout : 'column',
										style : 'padding-left:0px;padding-bottom:5px;',
										items : [{
													xtype : "label",
													text : "小写:",
													width : 40
												}, {
													xtype : "numberfield",
													cls : 'textfieldBG',
//													id : 'bpaApplyMoneyXX',
													name :"bankpayapply_nowApplyMoneyXX",
													allowBlank : false,
													width : 490,
													value : this.bpaApplyMoneyXX,
													enableKeyEvents:true,
													regex:/^(([1-9]\d{0,9})|0)(\.\d{1,2})?$/,
													regexText:'请输入正确格式的金额',
													listeners : {
														keyup: function(field) {	
															var paymentSumBig = AmountInWords(field.getValue());
															Ext.getCmp('bankpayapply_nowApplyMoneyDX').setValue(paymentSumBig); 
														} 
													}
												}]
									}, {
										xtype : 'container',
										layout : 'column',
										style : 'padding-left:0px;padding-bottom:5px;',
										items : [{
													xtype : "label",
													text : "大写:",
													width : 40
												}, {
													xtype : "textfield",
													id : 'bankpayapply_nowApplyMoneyDX',
													name : "bankpayapply_nowApplyMoneyDX",
													width : 490,
													allowBlank : false,
													readOnly : true,
													editable : false,
													style : "background:#F0F0F0;",
													value : this.bpaApplyMoneyDX
												}]
									}]
								}]
					}, {
						xtype : 'panel',
						layout : 'column',
						defaultType : 'label',
						border : false,
						items : [{
									text : '经办人员:',
									width : 90
								}, {
									xtype : 'textfield',
									// name : 'bankpayapply.bpaRemark',
									// id : 'bpaRemark',
									allowBlank : false,
									readOnly : true,
									value : this.bpaRemark,
									width : 530,
									style : "background:#F0F0F0;"
								}]

					}, {
						xtype : "panel",
						layout : "column",
						border : false,
						unstyled : false,
						style : 'margin-top:5px;',
						items : [{
									xtype : "label",
									name : "MyLabel1",
									text : "变更理由:",
									height : 50,
									width : 90
								}, {
									xtype : "textarea",
									name : "bankpayapply_updateReason",
									maxLength : 500,
									allowBlank : false,
									width : 522,
									autoScroll : true,
									height : 60
								}]
					}]
		});
		// 初始化功能按钮
		this.buttons = [{
					text : '确认',
					iconCls : 'btn-save',
					handler : this.updateFH
							.createCallback(this.formPanel, this)
				}, {
					text : '重置',
					iconCls : 'btn-reset',
					handler : this.reset.createCallback(this.formPanel)
				}, {
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
	updateFH : function(formPanel, window) {
		if (formPanel.getForm().isValid()) {

			Ext.Msg.confirm('系统提示', '确定要变更银行付款？', function(btn) {
						if (btn == 'yes') {
							formPanel.getForm().submit({
								method : 'POST',
								waitMsg : '正在提交数据...',
								success : function(fp, action) {
									Ext.ux.Toast.msg('操作信息', '成功变更银行付款！');
									var gridPanel = Ext
											.getCmp('BankpayapplyGrid');
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
					}, this);
		}
	}
});