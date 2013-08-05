Ext.ns('AppUserRoleForm');

AppUserRoleForm = Ext.extend(Ext.Window, {
	formPanel : null,
	constructor : function(_cfg) {
		Ext.applyIf(this, _cfg);
		//必须先初始化组件
		this.initUIComponents();
		AppUserRoleForm.superclass.constructor.call(this, {
			width : 550,
			height : 460,
			title : '详细',
			layout : 'form',
			labelWidth : 60,
			modal : true,
			items : this.formPanel,
			buttonAlign : "center",
			buttons : [{
					text : '保存',
					iconCls : 'btn-save',
					hidden : !(isGranted("_AppUserRoleEdit")),
					handler : this.save.createCallback(this.formPanel, this)
				}, {
					text : "关闭",
					iconCls : "btn-cancel",
					handler : this.cancel.createCallback(this)
				}],
			defaults : {
				anchor : "100%,100%"
			}
		})
	},
	initUIComponents : function() {
		this.formPanel = new Ext.FormPanel({
			layout : 'form',
			bodyStyle : 'padding:10px 10px 10px 10px',
			border : false,
			trackResetOnLoad : true,
			url : __ctxPath + '/system/updateRoleAppUser.do',
			id : 'AppUserRoleForm',
			defaults : {
				anchor : '98%,98%'
			},
			defaultType : 'textfield',
			items : [{
					name : 'appUser.userId',
					value : this.userId,
					hidden : true
				}, {
					xtype : 'textfield',
					fieldLabel : '姓名',
					value : this.fullname,
					readOnly : true
				}, {
					xtype : 'textfield',
					fieldLabel : '部门',
					value : this.department,
					readOnly : true
					//				}, {
				//					xtype : 'textarea',
				//					fieldLabel : '角色',
				//					height : 100,
				//					value : this.haveRole
			}	, {
					xtype : "panel",
					title : "用户角色",
					width : 530,
					height : 317,
					colspan : 2,
					items : [{
							xtype : "itemselector",
							id : "AppUserRoles2",
							name : "AppUserRoles",
							fromLegend : "",
							imagePath : __ctxPath + "/ext3/ux/images/",
							multiselects : [{
									id : "chooseRoles2",
									title : "可选角色",
									width : 247,
									height : 290,
									store : new Ext.data.SimpleStore({
										autoLoad : true,
										baseParams : {
											userId : this.userId
										},
										url : __ctxPath + "/system/chooseRolesAppUser.do",
										fields : ["roleId", "roleName"]
									}),
									tbar : [{
											width : 250,
											id:'searchRole',
											xtype : 'textfield',
											emptyText : '筛选角色',
											enableKeyEvents:true,
											listeners : {
												'keyup' : function() {
													var store=Ext.getCmp("chooseRoles2").store;
													var key=Ext.getCmp('searchRole').getValue();
													var regex=new RegExp(key);
													store.filter('roleName',regex);
												}
											}
										}],
									displayField : "roleName",
									valueField : "roleId"
								}, {
									//									id : "selectedRoles",
									//									name : "selectedRoles",
									title : "已有角色",
									width : 247,
									height : 290,
									store : new Ext.data.SimpleStore({
										autoLoad : true,
										baseParams : {
											userId : this.userId
										},
										url : __ctxPath + "/system/selectedRolesAppUser.do",
										fields : ["roleId", "roleName"]
									}),
									tbar : [{
											text : "清除所选",
											handler : function() {
												Ext.getCmp("AppUserRoles2").reset();
											}
										}],
									displayField : "roleName",
									valueField : "roleId"
								}]
						}]
				}]
		});
		//初始化功能按钮
		this.buttons = [{
				text : '保存',
				iconCls : 'btn-save',
				handler : this.save.createCallback(this.formPanel, this)
			}, {
				text : '取消',
				iconCls : 'btn-cancel',
				handler : this.cancel.createCallback(this)
			}];
	},//end of the initcomponents
	cancel : function(window) {
		window.close();
	},
	save : function(formPanel, window) {
		if (formPanel.getForm().isValid()) {
			formPanel.getForm().submit({
				method : 'POST',
				waitMsg : '正在提交数据...',
				success : function(fp, action) {
					Ext.ux.Toast.msg('操作信息', '成功保存信息！');
					var gridPanel = Ext.getCmp('appUserRoleView');
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
	}
})
