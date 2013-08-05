/**UserFlowconfig
 * @author
 * @createtime
 * @class UserFlowconfigAddForm
 * @extends Ext.Window
 * @description UserFlowconfigAdd表单
 */
UserFlowconfigAddForm = Ext.extend(Ext.Window, {
			// 内嵌FormPanel
			formPanel : null,
			// 构造函数
			constructor : function(_cfg) {
				Ext.applyIf(this, _cfg);
				// 必须先初始化组件
				this.initUIComponents();
				UserFlowconfigAddForm.superclass.constructor.call(this, {
							id : 'UserFlowconfigAddFormWin',
							layout : 'fit',
							items : this.formPanel,
							modal : true,
							height : 300,
							width : 400,
							maximizable : true,
							title : '流程配置详细信息',
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
							url : __ctxPath + '/system/saveUserFlowconfig.do',
							id : 'UserFlowconfigAddForm',
							defaults : {
								anchor : '98%,98%'
							},
							defaultType : 'textfield',
							items : [{
										name : 'userFlowconfig.id',
										id : 'id',
										xtype : 'hidden',
										value : this.id == null ? '' : this.id
									}, {
										name : 'userIds',
										id : 'userIds',
										xtype : 'hidden'
									}, {
										fieldLabel : '姓名',
										name : 'usernames',
										id : 'usernames',
										xtype:'textarea',
										allowBlank : false,	
										listeners : {
											'focus' : function() {
												UserSelector.getView(function(n,l,mobile) {
																			Ext.getCmp("usernames").setValue(l);
																			Ext.getCmp("userIds").setValue(n);
																		}, false)
																.show()
											}
										}
									}, {
										fieldLabel : '是否发送邮件',
										hiddenName : 'userFlowconfig.isEmail',
										id : 'isEmail',
										xtype : "combo",
										mode : "local",
										editable : false,
										allowBlank : false,	
										triggerAction : "all",
										store : [[true, "是"], [false, "否"]],
										value:false
									}, {
										fieldLabel : '是否发送短信',
										hiddenName : 'userFlowconfig.isMsg',
										id : 'isMsg',
										xtype : "combo",
										mode : "local",
										editable : false,
										allowBlank : false,	
										triggerAction : "all",
										store : [[true, "是"], [false, "否"]],
										value:false
									}, {
										fieldLabel : '是否发送ERP',
										hiddenName : 'userFlowconfig.isToERP',
										id : 'isToERP',
										xtype : "combo",
										mode : "local",
										editable : false,
										allowBlank : false,	
										triggerAction : "all",
										store : [[true, "是"], [false, "否"]],
										value:false
									}]
						});
				// 加载表单对应的数据
				if (this.id != null && this.id != 'undefined') {
					this.formPanel.getForm().load({
						deferredRender : false,
						url : __ctxPath + '/system/getUserFlowconfig.do?id='
								+ this.id,
						waitMsg : '正在载入数据...',
						success : function(form, action) {
							Ext.getCmp("userId").setValue(action.result.data.appUser.userId);
							Ext.getCmp("username").setValue(action.result.data.appUser.fullname);
						},
						failure : function(form, action) {
						}
					});
				}
				// 初始化功能按钮
				this.buttons = [{
							text : '保存',
							iconCls : 'btn-save',
							handler : this.save.createCallback(this.formPanel,
									this)
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
									var gridPanel = Ext
											.getCmp('UserFlowconfigGrid');
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