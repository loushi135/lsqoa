/**
 * @author
 * @createtime
 * @class SmsGroupUserForm
 * @extends Ext.Window
 * @description SmsGroup表单
 */
SmsGroupUserForm = Ext.extend(Ext.Window, {
	//内嵌FormPanel
	formPanel : null,
	//构造函数
	constructor : function(_cfg) {
		Ext.applyIf(this, _cfg);
		//必须先初始化组件
		this.initUIComponents();
		SmsGroupUserForm.superclass.constructor.call(this, {
			id : 'SmsGroupUserFormWin',
			layout : 'fit',
			items : this.formPanel,
			modal : true,
			height : 200,
			width : 400,
			maximizable : true,
			title : '增加组成员',
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
			url : __ctxPath + '/system/addGroupUserSmsGroup.do',
			id : 'SmsGroupUserForm',
			defaults : {
				anchor : '98%,98%'
			},
			defaultType : 'textfield',
			items : [{
						xtype:'combo',
						fieldLabel : '分组',
						hiddenName:'smsGroup.id',
						name:'groupName',
						id:'groupId',
						mode:'local',
						triggerAction:'all',
						allowBlank:false,
						editable:false,
						valueField : 'id',
						displayField : 'groupName',
						store : new Ext.data.JsonStore({
								autoLoad:true,
								url : __ctxPath + '/system/listSmsGroup.do',
							    baseParams:{start:0,limit:100},
								root : 'result',
								totalProperty : 'totalCounts',
								remoteSort : true,
								fields : [{name:'id',type:'int'},'groupName']
							})
					}, 
					{
						style:'margin-top:5px;',
						fieldLabel : '增加成员',
						xtype:'textarea',
						name : 'smsGroupUserNames',
						id : 'smsGroupUserNames',
						allowBlank:false,
						readOnly:true,
						emptyText:'请选择成员',
						listeners : {
							'focus' : function() {
								UserSelector.getView(function(ids,fullnames,mobile) {
													Ext.getCmp('smsGroupUserIds').setValue(ids);
													Ext.getCmp('smsGroupUserNames').setValue(fullnames);
												},false)
										.show()
							}
						}
					},{
						xtype:'hidden',
						name:'smsGroupUserIds',
						id:'smsGroupUserIds'
					}
			]
		});
		//加载表单对应的数据	
		this.initData();
		//初始化功能按钮
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
	},//end of the initcomponents
	/**
	 * 初始化数据
	 * 
	 * @param {}
	 *            formPanel
	 */
	initData : function() {
		//加载表单对应的数据	
		if (!Ext.isEmpty(this.id)&&this.id!=-1) {
			this.formPanel.getForm().load({
				deferredRender : false,
				url : __ctxPath + '/system/getSmsGroup.do?id=' + this.id,
				waitMsg : '正在载入数据...',
				success : function(form, action) {
					var jsonData = Ext.util.JSON.decode(action.response.responseText);
					var res = jsonData.data;
					Ext.getCmp("groupId").setValue(res.id);
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
					var gridPanel = Ext.getCmp('SmsGroupGrid');
					if (gridPanel != null) {
						gridPanel.store.reload();
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