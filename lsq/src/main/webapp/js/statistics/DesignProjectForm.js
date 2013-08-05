/**
 * @author
 * @createtime
 * @class DesignProjectForm
 * @extends Ext.Window
 * @description DesignProject表单
 */
DesignProjectForm = Ext.extend(Ext.Window, {
	// 内嵌FormPanel
	formPanel : null,
	// 构造函数
	constructor : function(_cfg) {
		Ext.applyIf(this, _cfg);
		// 必须先初始化组件
		this.initUIComponents();
		DesignProjectForm.superclass.constructor.call(this, {
					id : 'DesignProjectFormWin',
					layout : 'fit',
					items : this.formPanel,
					modal : true,
					height : 300,
					width : 500,
					maximizable : true,
					title : '[设计项目]详细信息',
					buttonAlign : 'center',
					buttons : this.buttons
				});
	},// end of the constructor
	// 初始化组件
	initUIComponents : function() {
		var thizId = this.id;
//		var _url = __ctxPath + '/system/listDepartment.do?opt=appUser';
//		var depSelector = new TreeSelector('designProject.dept', _url, '部门',
//				'designProject.dept.depId', false, 147,'designProject.dept.depName');
		this.formPanel = new Ext.FormPanel({
					layout : 'form',
					trackResetOnLoad : true,
					bodyStyle : 'padding:10px 10px 10px 10px',
					border : false,
					url : __ctxPath + '/statistics/saveDesignProject.do',
					id : 'DesignProjectForm',
					defaults : {
						anchor : '98%,98%'
					},
					defaultType : 'textfield',
					items : [{
								name : 'designProject.id',
								id : 'id',
								xtype : 'hidden',
								value : this.id == null ? '' : this.id
							}, {
								fieldLabel : '项目编号',
								name : 'designProject.proNo',
								id : 'proNo',
								value:'系统自动生成!',
								readOnly:true
							}, {
								fieldLabel : '项目名称',
								name : 'designProject.proName',
								id : 'proName',
								allowBlank:false,
								maxLength:64,
								listeners:{
									blur:function(field){
										var proName = field.getValue();
										if(Ext.isEmpty(thizId)){
											if(!Ext.isEmpty(proName)){
												var url = __ctxPath + '/statistics/checkProNameDesignProject.do';
												var params = "proName="+encodeURI(proName);
												var data = ajaxSyncCall(url,params);
												if(!data.success){
													Ext.ux.Toast.msg('操作信息', '信息修改，项目名称为:<font color="red">'+field.getValue()+'</font>已经存在，请修改项目名称！');
													field.setValue('');
												}
											}
										}
									}
								}
							},
//							depSelector,
							{
								fieldLabel : '部门',
								name : 'depNames',
								id : 'depNames',
								allowBlank:false,
								listeners:{
									focus:function(field){
										DepSelector.getView(function(depIds,depNames){
											Ext.getCmp("depIds").setValue(depIds);
											Ext.getCmp("depNames").setValue(depNames);
										},false,true).show();
									}
								}
							},
							{
								xtype:'hidden',
								name : 'depIds',
								id : 'depIds'
							}, {
								fieldLabel : '备注',
								name : 'designProject.remark',
								id : 'remark',
								xtype:'textarea'
							}

					]
				});
		// 加载表单对应的数据
		this.initData();
		// 初始化功能按钮
		this.buttons = [{
			text : '保存',
			iconCls : 'btn-save',
			hidden : !(isGranted("_DesignProjectAdd") || isGranted("_DesignProjectEdit")),
			handler : this.save.createCallback(this.formPanel, this)
		}, {
			text : '重置',
			iconCls : 'btn-reset',
			hidden : !(isGranted("_DesignProjectAdd") || isGranted("_DesignProjectEdit")),
			handler : this.reset.createCallback(this.formPanel)
		}, {
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
		if (this.id != null && this.id != 'undefined') {
			this.formPanel.getForm().load({
				deferredRender : false,
				url : __ctxPath + '/statistics/getDesignProject.do?id='
						+ this.id,
				waitMsg : '正在载入数据...',
				success : function(form, action) {
					var jsonData = Ext.util.JSON
							.decode(action.response.responseText);
					var res = jsonData.data;
					if(!Ext.isEmpty(res.depts)){
						var depNames = "";
						var depIds = "";
						Ext.each(res.depts,function(dept,i){
							if(depNames!=""){
								depNames+=",";
								depIds+=",";
							}
							depNames+=dept.depName;
							depIds+=dept.depId;
						});
						Ext.getCmp('depIds').setValue(depIds);
						Ext.getCmp('depIds').originalValue = depIds;
						Ext.getCmp('depNames').setValue(depNames);
						Ext.getCmp('depNames').originalValue = depNames;
					}
					// Ext.getCmp("").originalValue=
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
							var gridPanel = Ext.getCmp('DesignProjectGrid');
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
							// window.close();
						}
					});
		}
	}// end of save

});