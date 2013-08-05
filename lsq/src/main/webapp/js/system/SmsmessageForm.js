/**
 * @author
 * @createtime
 * @class SmsmessageForm
 * @extends Ext.Window
 * @description Smsmessage表单
 */
SmsmessageForm = Ext.extend(Ext.Window, {
	//内嵌FormPanel
	formPanel : null,
	//构造函数
	constructor : function(_cfg) {
		Ext.applyIf(this, _cfg);
		//必须先初始化组件
		this.initUIComponents();
		SmsmessageForm.superclass.constructor.call(this, {
			id : 'SmsmessageFormWin',
			layout : 'fit',
			items : this.formPanel,
			modal : true,
			height : 500,
			width : 450,
			maximizable : true,
			title : '短信详细信息',
			buttonAlign : 'center',
			buttons : this.buttons
		});
	},//end of the constructor
	//初始化组件
	initUIComponents : function() {
		this.formPanel = new Ext.FormPanel({
			layout : 'form',
			bodyStyle : 'padding:10px 10px 10px 10px',
			border : false,
			url : __ctxPath + '/system/saveSmsmessage.do',
			id : 'SmsmessageForm',
			defaults : {
				anchor : '98%,98%'
			},
			//defaultType : 'textfield',
			items : [{
						name : 'smsmassage.smsId',
						id : 'smsId',
						xtype : 'hidden',
						value : this.smsId == null ? '' : this.smsId
					}, {
						xtype : "container",
						style : "padding-left:0px;margin-bottom:4px;",
						id : "depContainer",
						layout : "column",
						items : [{
									id : 'id',
									xtype : 'hidden'
								}, {
									xtype : "label",
									style : "padding-left:0px;",
									text : '员工姓名:',
									width : 100
								}, {
									xtype : 'textfield',
									name : 'smsmessage.name',
									allowBlank : false,
									id : 'name'
								}, {
									xtype : 'button',
									text : '选择员工',
									iconCls : 'btn-user-sel',
									width : 80,
									listeners : {
										'click' : function() {
											Ext.getCmp('name').setValue('');
											Ext.getCmp('id').setValue('');
											UserSelector.getView(function(id, name) {
												Ext.getCmp('name').setValue(name);
												Ext.getCmp('id').setValue(id);
											}, false).show();
										}
									}
								}, {
									xtype : 'button',
									text : '选择短信组',
									iconCls : 'btn-user-sel',
									width : 80,
									listeners : {
										'click' : function() {
											Ext.getCmp('name').setValue('');
											Ext.getCmp('id').setValue('');
											UserGroupSelector.getView(function(id, name) {
												Ext.getCmp('name').setValue(name);
												Ext.getCmp('id').setValue(id);
											}, false).show();
										}
									}
								}]
					}, {
						fieldLabel : '发送内容',
						name : 'smsmessage.massage',
						id : 'massage',
						height : 300,
						xtype : 'textarea'
					}, {
						boxLabel : '是否署名',
						xtype : 'checkbox',
						name : 'isAddName',
						id : 'isAddName'
					}, {
						fieldLabel : '发送人',
						name : 'smsmessage.sender',
						id : 'sender',
						readOnly : true,
						xtype : 'textfield',
						value : __currentUser
					}]
		});
		//初始化功能按钮
		this.buttons = [{
					text : '发送',
					id : 'buttonId',
					iconCls : 'btn-save',
					hidden : (this.smsId != null),
					handler : this.save.createCallback(this.formPanel, this)

				}, {
					text : '关闭',
					iconCls : 'btn-cancel',
					handler : this.cancel.createCallback(this)
				}];
		//加载表单对应的数据	
		if (this.smsId != null && this.smsId != 'undefined') {
			this.formPanel.getForm().load({
				deferredRender : false,
				url : __ctxPath + '/system/getSmsmessage.do?smsId=' + this.smsId,
				waitMsg : '正在载入数据...',
				success : function(form, action) {
				},
				failure : function(form, action) {
				}
			});
		}
	},//end of the initcomponents

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
					var gridPanel = Ext.getCmp('SmsmessageGrid');
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
	}//end of save

});