/**
 * @author 
 * @createtime 
 * @class ProOtherDefForm
 * @extends Ext.Window
 * @description ProOtherDef表单
 */
ProOtherDefForm=Ext.extend(Ext.Window,{
	//内嵌FormPanel
	formPanel:null,
	//构造函数
	constructor:function(_cfg){
		Ext.applyIf(this,_cfg);
		//必须先初始化组件
		this.initUIComponents();
		ProOtherDefForm.superclass.constructor.call(this,{
			id:'ProOtherDefFormWin',
			layout:'fit',
			items:this.formPanel,
			modal:true,
			height:200,
			width:400,
			maximizable:true,
			title:'[ProOtherDef]详细信息',
			buttonAlign : 'center',
			buttons:this.buttons
		});
	},//end of the constructor
	//初始化组件
	initUIComponents:function(){
		this.formPanel=new Ext.FormPanel({
				layout : 'form',
				trackResetOnLoad:true,
				bodyStyle: 'padding:10px 10px 10px 10px',
				border:false,
				url : __ctxPath + '/flow/saveProOtherDef.do',
				id : 'ProOtherDefForm',
				defaults : {
					anchor : '98%,98%'
				},
				defaultType : 'textfield',
				items : [{
							name : 'proOtherDef.proid',
							id : 'proid',
							xtype:'hidden',
							value : this.proid == null ? '' : this.proid
						}
																																										,{
												fieldLabel : '打印人ID列表',	
												name : 'proOtherDef.printUserIds',
						id : 'printUserIds'
							}
																																				,{
												fieldLabel : '打印人姓名列表',	
												name : 'proOtherDef.printUserNames',
						id : 'printUserNames'
							}
																								
												]
			});
		//加载表单对应的数据	
		this.initData();
		//初始化功能按钮
		this.buttons=[{
				text : '保存',
				iconCls : 'btn-save',
				handler :this.save.createCallback(this.formPanel,this)
			}, {
				text : '重置',
				iconCls : 'btn-reset',
				handler :this.reset.createCallback(this.formPanel)
			},{
				text : '取消',
				iconCls : 'btn-cancel',
				handler : this.cancel.createCallback(this)
			}];
	},//end of the initcomponents
	/**
	 * 初始化数据
	 * @param {} formPanel
	 */
	initData:function(){
		//加载表单对应的数据	
		if (this.proid != null && this.proid != 'undefined') {
			this.formPanel.getForm().load({
				deferredRender : false,
				url : __ctxPath + '/flow/getProOtherDef.do?proid='+ this.proid,
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
	 * @param {} formPanel
	 */
	reset:function(formPanel){
		formPanel.getForm().reset();
	},
	/**
	 * 取消
	 * @param {} window
	 */
	cancel:function(window){
		window.close();
	},
	/**
	 * 保存记录
	 */
	save:function(formPanel,window){
		if (formPanel.getForm().isValid()) {
			formPanel.getForm().submit({
				method : 'POST',
				waitMsg : '正在提交数据...',
				success : function(fp, action) {
					Ext.ux.Toast.msg('操作信息', '成功保存信息！');
					var gridPanel=Ext.getCmp('ProOtherDefGrid');
					if(gridPanel!=null){
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