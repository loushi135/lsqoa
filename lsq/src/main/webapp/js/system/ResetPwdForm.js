/**
 * @author
 * @createtime
 * @class ResetPwdForm
 * @extends Ext.Window
 * @description ResetPwd表单
 */
ResetPwdForm = Ext.extend(Ext.Window, {
	// 内嵌FormPanel
	formPanel : null,
	// 构造函数
	constructor : function(_cfg) {
		Ext.applyIf(this, _cfg);
		// 必须先初始化组件
		this.initUIComponents();
		ResetPwdForm.superclass.constructor.call(this, {
					id : 'ResetPwdFormWin',
					layout : 'fit',
					items : this.formPanel,
					modal : true,
					height : 200,
					width : 400,
					maximizable : true,
					title : '重置密码',
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
			url : __ctxPath + '/system/saveResetPwd.do',
			id : 'ResetPwdForm',
			defaults : {
				anchor : '98%,98%'
			},
			defaultType : 'textfield',
			items : [{
				name :'resetPwd.id',
				id:'resetPwd.id',
				xtype : 'hidden',
				value:null==this.resetId?null:this.resetId
			},{
						name : 'resetPwd.userId',
						id : 'resetPwd.userId',
						xtype : 'hidden'
					},{
						name : 'own',
						id : 'resetPwd.own',
						xtype : 'hidden'
					}, {
						fieldLabel : '用户',
						id : 'userFullName',
						emptyText : '请选择重置人员',
						readOnly : true,
						allowBlank : false,
						listeners : {
							'focus' : function() {
								UserSelector.getView(function(id, name) {
									Ext.getCmp("resetPwd.userId").setValue(id);
									Ext.getCmp("userFullName").setValue(name);
									Ext.getCmp("resetPwd.id").setValue("");
									Ext.getCmp("resetPWD").setValue(generateChar(5)+generateNumber(5));
									
								}, false).show();
							}
						}
					}, {
						fieldLabel : '重置密码',
						readOnly : false,
						allowBlank : false,
						name : 'resetPwd.resetPWD',
						id : "resetPWD"
					}]
		});
		// 加载表单对应的数据
		 this.initData();
		// 初始化功能按钮
		this.buttons = [{
			text : '确定',
			iconCls : 'btn-save',
			hidden : !(isGranted("_ResetPwdAdd") || isGranted("_ResetPwdEdit")),
			handler : this.save.createCallback(this.formPanel, this)
		}, {
			// text : '重置',
			// iconCls : 'btn-reset',
			// hidden : !(isGranted("_ResetPwdAdd") ||
			// isGranted("_ResetPwdEdit")),
			// handler : this.reset.createCallback(this.formPanel)
			// }, {
			text : '取消',
			iconCls : 'btn-cancel',
			handler : this.cancel.createCallback(this)
		}];
	},// end of the initcomponents
	/**
	 * 初始化数据
	 * 
	 * @param {}
	 *            formPanel
	 */
	initData : function() {
		// 加载表单对应的数据
		if (this.username != null && this.username != 'undefined') {
			this.formPanel.getForm().load({
				deferredRender : false,
				url : __ctxPath + '/system/getByUsernameAppUser.do?Q_username_S_EQ=' + this.username,
				waitMsg : '正在载入数据...',
				success : function(form, action) {
					var jsonData = Ext.util.JSON
							.decode(action.response.responseText);
					var res = jsonData.data;
					// Ext.getCmp("").originalValue=
					Ext.getCmp("resetPwd.userId").originalValue=res.userId;
					Ext.getCmp("userFullName").originalValue=res.fullname;
					Ext.getCmp("resetPwd.own").originalValue="yes";
					Ext.getCmp("userFullName").setValue(res.fullname);
					Ext.getCmp("resetPwd.userId").setValue(res.userId);
					Ext.getCmp("resetPwd.own").setValue("yes");
					Ext.getCmp("resetPWD").setValue(generateChar(5)+generateNumber(5));
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
							Ext.ux.Toast.msg('操作信息', '成功重置密码！');
							var gridPanel = Ext.getCmp('ResetPwdGrid');
							if (gridPanel != null) {
								gridPanel.getStore().reload();
							}
							window.close();
						},
						failure : function(fp, action) {
							var jsonData = Ext.util.JSON
								.decode(action.response.responseText);
							var res = jsonData.data;
							Ext.MessageBox.show({
										title : '操作信息',
										msg : res==null?'信息保存出错，请联系管理员！':res,
										buttons : Ext.MessageBox.OK,
										icon : Ext.MessageBox.ERROR
									});
							// window.close();
						}
					});
		}
	}// end of save

});
var number=['0','1','2','3','4','5','6','7','8','9'];
var chars = [
//			'0','1','2','3','4','5','6','7','8','9',
			'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'
//			,
//			'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'
			];

function generateNumber(n) {
     var res = "";
     for(var i = 0; i < n ; i ++) {
         var id = Math.ceil(Math.random()*9);
         res += number[id];
     }
     return res;
}
function generateChar(n) {
     var res = "";
     for(var i = 0; i < n ; i ++) {
         var id = Math.ceil(Math.random()*25);
         res += chars[id];
     }
     return res;
}