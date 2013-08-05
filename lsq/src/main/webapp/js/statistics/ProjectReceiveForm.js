/**
 * @author
 * @createtime
 * @class ProjectReceiveForm
 * @extends Ext.Window
 * @description ProjectReceive表单
 */
ProjectReceiveForm = Ext.extend(Ext.Window, {
	// 内嵌FormPanel
	formPanel : null,
	// 构造函数
	constructor : function(_cfg) {
		Ext.applyIf(this, _cfg);
		// 必须先初始化组件
		this.initUIComponents();
		ProjectReceiveForm.superclass.constructor.call(this, {
					id : 'ProjectReceiveFormWin',
					layout : 'fit',
					items : this.formPanel,
					modal : true,
					height : 250,
					width : 400,
					maximizable : true,
					title : '[收款]详细信息',
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
			url : __ctxPath + '/statistics/saveProjectReceive.do',
			id : 'ProjectReceiveForm',
			defaults : {
				anchor : '98%,98%'
			},
			defaultType : 'textfield',
			items : [{
						name : 'projectReceive.id',
						id : 'id',
						xtype : 'hidden',
						value : this.id == null ? '' : this.id
					}, {
						name : 'projectReceive.project.id',
						id : 'projectReceive.project.id',
						xtype : 'hidden'
					}, {
						fieldLabel : '项目名称',
						allowBlank : false,
						name : 'projectReceive.project.proName',
						id : 'projectReceive.project.proName',
						xtype : 'textfield',
						listeners : {
							'focus' : function() {
								ProjectNewSelector.getView(
										function(proId, proName) {
											Ext.getCmp("projectReceive.project.id")
													.setValue(proId);
											Ext
													.getCmp("projectReceive.project.proName")
													.setValue(proName);
										}, true, true).show();
							},
							afterrender : function(field) {
								// Ext.getCmp("expressApplyProContainer").hide();
							}
						}
					}, {
						fieldLabel : '收款时间',
						allowBlank : false,
						name : 'projectReceive.receiveTime',
						id : 'receiveTime',
						xtype : 'datefield',
						format : 'Y-m-d'
					}, {
						fieldLabel : '收款金额',
						allowBlank : false,
						name : 'projectReceive.amount',
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
						fieldLabel : '收款金额大写',
						name : 'projectReceive.amountBig',
						id : 'amountBig',
						readOnly : true
					}, {
						fieldLabel : '凭证字号',
						name : 'projectReceive.pzzh',
						id : 'pzzh',
						maxLength : 64
					}, {
						fieldLabel : '摘要',
						name : 'projectReceive.zy',
						id : 'zy',
						maxLength : 64
					}]
		});
		// 加载表单对应的数据
		if (this.id != null && this.id != 'undefined') {
			this.formPanel.getForm().load({
				deferredRender : false,
				url : __ctxPath + '/statistics/getProjectReceive.do?id='
						+ this.id,
				waitMsg : '正在载入数据...',
				success : function(form, action) {
					var record = Ext.util.JSON
							.decode(action.response.responseText);
					Ext.getCmp("projectReceive.project.id")
							.setValue(record.data.project.id);
					Ext.getCmp("projectReceive.project.id").originalValue = record.data.project.id;
					
					Ext.getCmp("projectReceive.project.proName")
							.setValue(record.data.project.proName);
					Ext.getCmp("projectReceive.project.proName").originalValue = record.data.project.proName;
					
					
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
							var projectReceiveView = Ext
									.getCmp('ProjectReceiveView');
							projectReceiveView.initData();
							projectReceiveView.store.reload();
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