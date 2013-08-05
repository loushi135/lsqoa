/**
 * @author 
 * @createtime 
 * @class AnnounceRemindForm
 * @extends Ext.Window
 * @description AnnounceRemind表单
 */
AnnounceRemindForm=Ext.extend(Ext.Window,{
	//内嵌FormPanel
	formPanel:null,
	//构造函数
	constructor:function(_cfg){
		Ext.applyIf(this,_cfg);
		//必须先初始化组件
		this.initUIComponents();
		AnnounceRemindForm.superclass.constructor.call(this,{
			id:'AnnounceRemindFormWin',
			layout:'fit',
			items:this.formPanel,
			modal:true,
			width : 400,
			iconCls : 'menu-job-check',
			height : 300,
			maximizable:true,
			title:'[通告]详细信息',
			buttonAlign : 'center',
			buttons:this.buttons
		});
	},//end of the constructor
	//初始化组件
	initUIComponents:function(){
		this.formPanel=new Ext.Panel({
				id:'context',
				height:220,
				frame:false,
				html:''
			});
		//加载表单对应的数据	
		if (this.id != null && this.id != 'undefined') {
			Ext.Ajax.request({
				url : __ctxPath + '/system/getAnnounceRemind.do?id='+ this.id,
				waitMsg : '正在载入数据...',
				success:function(response,options){
					var data = Ext.util.JSON.decode(response.responseText).data;
					Ext.DomHelper.overwrite(Ext.getCmp("context").body,
					'<html><body><div style="width:366px;height:212px;OVERFLOW-y:auto;OVERFLOW-x:hidden;padding:3px">' +
					'&nbsp;&nbsp;&nbsp;&nbsp;'+data.announce.context+
					'</div></body></html>');

				},
				failure : function(form, action) {
				}
			});
		}
		//初始化功能按钮
		this.buttons=[{
				text : '关闭',
				iconCls : 'btn-cancel',
				handler : function(){
					Ext.getCmp("AnnounceRemindFormWin").close();
				}
			}];
	},//end of the initcomponents
	
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
					var gridPanel=Ext.getCmp('AnnounceRemindGrid');
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