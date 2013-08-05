forgotPWD = Ext.extend(Ext.Window, {
	// 内嵌FormPanel
	formPanel : null,
	// 构造函数
	constructor : function(_cfg) {
		Ext.applyIf(this, _cfg);
		// 必须先初始化组件
		this.initUIComponents();
		forgotPWD.superclass.constructor.call(this, {
					id : 'forgotPWDWin',
					layout : 'fit',
					items : this.formPanel,
					modal : true,
					height : 220,
					width : 400,
					maximizable : true,
					title : '找回密码',
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
					url : __ctxPath + '/system/forgotResetPwd.do',
					id : 'forgotPWD',
					defaults : {
						anchor : '98%,98%'
					},
					defaultType : 'textfield',
					items : [{
								fieldLabel : '姓名',
								name : 'resetPwd.userFullName',
								id : 'userFullName',
								allowBlank : false
							}, {
								fieldLabel : '登录名',
								allowBlank : false,
								name : 'resetPwd.userLoginName',
								id : "userLoginName"
							}, {
								fieldLabel : '邮箱地址',
								allowBlank : false,
								name : 'email',
								id : "email",
								vtype : "email",
								blankText : "邮箱不能为空!",
								vtypeText : "邮箱格式不正确!"
							}, {
								fieldLabel : '身份证号',
								allowBlank : false,
								name : 'idCardNO',
								id : "idCardNO"
							}, {
								fieldLabel : '工号',
								allowBlank : false,
								name : 'workNO',
								id : "workNO"
							}]
				});
		// 加载表单对应的数据
		// this.initData();
		// 初始化功能按钮
		this.buttons = [{
			text : '确定',
			iconCls : 'btn-save',
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
							Ext.Msg.alert("操作信息", '找回密码信息已提交,请等待管理员回复！');
							window.close();
				},
				failure : function(fp, action) {
					var jsonData = Ext.util.JSON
							.decode(action.response.responseText);
					var res = jsonData.data;
					Ext.MessageBox.show({
								title : '操作信息',
								msg : res == null ? '系统异常，请联系管理员！' : res,
								buttons : Ext.MessageBox.OK,
								icon : Ext.MessageBox.ERROR
							});
					window.close();
				}
			});
		}
	}// end of save

});