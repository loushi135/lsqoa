/**
 * @author
 * @createtime
 * @class BillAdjustForm
 * @extends Ext.Window
 * @description BillAdjust表单
 */
BillAdjustForm = Ext.extend(Ext.Window, {
	//内嵌FormPanel
	formPanel : null,
	//构造函数
	constructor : function(_cfg) {
		Ext.applyIf(this, _cfg);
		//必须先初始化组件
		this.initUIComponents();
		BillAdjustForm.superclass.constructor.call(this, {
			id : 'BillAdjustFormWin',
			layout : 'fit',
			items : this.formPanel,
			modal : true,
			width : 680,
			height : 350,
			maximizable : true,
			title : '[发票额调整]详细信息',
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
			url : __ctxPath + '/statistics/saveBillAdjust.do',
			id : 'BillAdjustForm',
			defaults : {
				anchor : '98%,98%'
			},
			defaultType : 'textfield',
			items : [{
					name : 'billAdjust.id',
					id : 'id',
					xtype : 'hidden',
					value : this.id == null ? '' : this.id
				}, {
					xtype : 'hidden',
					name : 'billAdjust.bill.id',
					id : 'billId',
					value : this.billId
				}, {
					xtype : "container",
					layout : "column",
					style : 'margin-top:5px;',
					anchor : "100%",
					items : [{
								xtype : "label",
								text : "项目名称:",
								width : 90
							}, {
								xtype : "textfield",
								id:'bill.projectNew.proName',
								width : 528,
								readOnly : true,
								style : "background:#F0F0F0;",
								value : this.proName
							}]
				}, {
					xtype : "container",
					layout : "column",
					style : 'margin-top:5px;',
					anchor : "100%",
					items : [{
								xtype : "label",
								text : "供应商:",
								width : 90
							}, {
								xtype : "textfield",
								id:'bill.suppliersAssess.suppliersName',
								width : 528,
								readOnly : true,
								style : "background:#F0F0F0;",
								value : this.suppliersName
							}]
				}, {
					xtype : "container",
					layout : "column",
					style : 'margin-top:5px;',
					anchor : "100%",
					items : [{
								xtype : "label",
								text : "发票类型:",
								width : 90
							}, {
								xtype : "textfield",
								id:'bill.invoiceType',
								width : 528,
								readOnly : true,
								style : "background:#F0F0F0;",
								value : this.invoiceType
							}]
				}, {
					xtype : "container",
					layout : "column",
					style : 'padding-top:5px;',
					items : [{
								xtype : "label",
								text : "原发票金额:",
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
												width : 490,
												value : this.amount,
												style : "background:#F0F0F0;",
												readOnly : true
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
												width : 490,
												readOnly : true,
												style : "background:#F0F0F0;",
												value : this.amountBig
											}]
								}]
							}]
				}, {
					xtype:'hidden',
					name : 'billAdjust.oldAmount',
					id : 'oldAmount',
					value:this.amount
				},{
					xtype : "container",
					layout : "column",
					style : 'padding-top:5px;',
					items : [{
								xtype : "label",
								text : "调整金额:",
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
												id : 'newAmoumt',
												name :"billAdjust.newAmoumt",
												allowBlank : false,
												width : 490,
												enableKeyEvents:true,
												regex:/^(([1-9]\d{0,9})|0)(\.\d{1,2})?$/,
												regexText:'请输入正确格式的金额',
												listeners : {
													keyup: function(field) {	
														var amountBig = AmountInWords(field.getValue());
														Ext.getCmp('newAmountBig').setValue(amountBig); 
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
												id : 'newAmountBig',
												name :"billAdjust.newAmountBig",
												width : 490,
												allowBlank : false,
												readOnly : true,
												style : "background:#F0F0F0;"
											}]
								}]
							}]
				}, {
					xtype : "container",
					layout : "column",
					style : 'margin-top:5px;',
					items : [{
								xtype : "label",
								text : "调整原因:",
								height : 50,
								width : 90
							}, {
								xtype : "textarea",
								name : 'billAdjust.adjustReason',
								id : 'adjustReason',
								allowBlank : false,
								width : 522,
								height : 50
							}]
				}
			]
		});
		//加载表单对应的数据	
		this.initData();
		//初始化功能按钮
		this.buttons = [{
				text : '保存',
				iconCls : 'btn-save',
				hidden : !(isGranted("_BillAdjustAdd") || isGranted("_BillAdjustEdit")),
				handler : this.save.createCallback(this.formPanel, this)
			}, {
				text : '重置',
				iconCls : 'btn-reset',
				hidden : !(isGranted("_BillAdjustAdd") || isGranted("_BillAdjustEdit")),
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
				url : __ctxPath + '/statistics/getBillAdjust.do?id=' + this.id,
				waitMsg : '正在载入数据...',
				success : function(form, action) {
					var jsonData = Ext.util.JSON.decode(action.response.responseText);
					var res = jsonData.data;
					//Ext.getCmp("").originalValue=
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
					//window.close();
				}
			});
		}
	}//end of save

});