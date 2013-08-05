/**
 * @author
 * @createtime
 * @class SalesProjectRepayForm
 * @extends Ext.Window
 * @description ProjectRepay表单
 */
SalesProjectRepayForm = Ext.extend(Ext.Window, {
	// 内嵌FormPanel
	formPanel : null,
	// 构造函数
	constructor : function(_cfg) {
		Ext.applyIf(this, _cfg);
		// 必须先初始化组件
		this.initUIComponents();
		SalesProjectRepayForm.superclass.constructor.call(this, {
					id : 'SalesProjectRepayFormWin',
					layout : 'fit',
					items : this.formPanel,
					modal : true,
					height : 220,
					width : 400,
					maximizable : true,
					title : '[报销]详细信息',
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
			url : __ctxPath + '/statistics/saveProjectRepay.do',
			id : 'SalesProjectRepayForm',
			defaults : {
				anchor : '98%,98%'
			},
			defaultType : 'textfield',
			items : [{
						name : 'projectRepay.id',
						id : 'id',
						xtype : 'hidden',
						value : this.id == null ? '' : this.id
					}, {
						name : 'projectRepay.salesProject.id',
						id : 'projectRepay.salesProject.id',
						xtype : 'hidden'
					}, {
						name : 'projectRepay.proId',
						id : 'proId',
						xtype : 'hidden'
					}, {
						name : 'projectRepay.repayType',
						id : 'repayType',
						xtype : 'hidden',
						value:3
					}, {
						fieldLabel : '项目名称',
						allowBlank : false,
						name : 'projectRepay.salesProject.proName',
						id : 'projectRepay.salesProject.proName',
						xtype : 'textfield',
						listeners : {
							'focus' : function() {
								SalesProjectSelector.getView(
										function(proId, proName) {
											Ext.getCmp("projectRepay.salesProject.id").setValue(proId);
											Ext.getCmp("proId").setValue(proId);
											Ext.getCmp("projectRepay.salesProject.proName").setValue(proName);
										}, true, true).show();
							}
						}
					}, {
						name : 'projectRepay.repayUser.userId',
						id : 'projectRepay.repayUser.userId',
						xtype : 'hidden'
					}, {
						fieldLabel : '报销人',
						allowBlank : false,
						name : 'repayUserName',
						id : 'repayUserName',
						readOnly : true,
						listeners : {
							focus : function() {
								UserSelector.getView(function(ids, names) {
									Ext.getCmp("repayUserName").setValue(names);
									Ext.getCmp("projectRepay.repayUser.userId")
											.setValue(ids);
								}, true).show();
							}
						}
					}, {
						fieldLabel : '报销时间',
						allowBlank : false,
						name : 'projectRepay.repayTime',
						id : 'repayTime',
						xtype : 'datefield',
						format : 'Y-m-d'
					}, {
						fieldLabel : '报销金额',
						allowBlank : false,
						name : 'projectRepay.amount',
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
						fieldLabel : '报销金额大写',
						name : 'projectRepay.amountBig',
						id : 'amountBig',
						readOnly : true
					}]
		});
		// 加载表单对应的数据
		if (this.id != null && this.id != 'undefined') {
			this.formPanel.getForm().load({
				deferredRender : false,
				url : __ctxPath + '/statistics/getProjectRepay.do?id='
						+ this.id,
				waitMsg : '正在载入数据...',
				success : function(form, action) {
					var record = Ext.util.JSON.decode(action.response.responseText);
					Ext.getCmp("projectRepay.salesProject.id").setValue(record.data.salesProject.id);
					Ext.getCmp("projectRepay.salesProject.id").originalValue = record.data.salesProject.id;
					
					Ext.getCmp("projectRepay.salesProject.proName").setValue(record.data.salesProject.proName);
					Ext.getCmp("projectRepay.salesProject.proName").originalValue = record.data.salesProject.proName;
					
					
					if (!Ext.isEmpty(record.data.repayUser)) {
						Ext.getCmp("projectRepay.repayUser.userId")
								.setValue(record.data.repayUser.userId);
						Ext.getCmp("projectRepay.repayUser.userId").originalValue = record.data.repayUser.userId;
						Ext.getCmp("repayUserName")
								.setValue(record.data.repayUser.fullname);
						Ext.getCmp("repayUserName").originalValue = record.data.repayUser.fullname;
					}
				},
				failure : function(form, action) {
				}
			});
		}
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
							var projectRepayView = Ext.getCmp('SalesProjectRepayView');
							projectRepayView.initData();
							projectRepayView.store.reload();
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