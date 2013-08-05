/**
 * @author 
 * @createtime 
 * @class BookDelForm
 * @extends Ext.Window
 * @description BookDel表单
 */
BookDelForm=Ext.extend(Ext.Window,{
	//内嵌FormPanel
	formPanel:null,
	//构造函数
	constructor:function(_cfg){
		Ext.applyIf(this,_cfg);
		//必须先初始化组件
		this.initUIComponents();
		BookDelForm.superclass.constructor.call(this,{
			id:'BookDelFormWin',
			layout:'fit',
			items:this.formPanel,
			modal:true,
			height:200,
			width:400,
			maximizable:true,
			title:'删除档案填写原因',
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
				url :  __ctxPath + '/admin/multiDelBook.do',
				id : 'BookDelForm',
				defaults : {
					anchor : '98%,98%'
				},
				defaultType : 'textfield',
				items : [
//						{
//							name : 'bookDel.id',
//							id : 'id',
//							xtype:'hidden',
//							value : this.id == null ? '' : this.id
//						}
						{
							xtype:'hidden',
							name:'ids',
							value:this.ids == null ? '' : this.ids
						},{
							fieldLabel : '原因',	
							xtype:'textarea',
							allowBlank:false,
							name : 'delReason',
							id : 'delReason'
						}
						]
			});
		//加载表单对应的数据	
//		if (this.id != null && this.id != 'undefined') {
//			this.formPanel.getForm().load({
//				deferredRender : false,
//				url : __ctxPath + '/admin/getBookDel.do?id='+ this.id,
//				waitMsg : '正在载入数据...',
//				success : function(form, action) {
//				},
//				failure : function(form, action) {
//				}
//			});
//		}
		//初始化功能按钮
		this.buttons=[{
				text : '保存',
				iconCls : 'btn-save',
				handler :this.save.createCallback(this.formPanel,this)
			}
//			, {
//				text : '重置',
//				iconCls : 'btn-reset',
//				handler :this.reset.createCallback(this.formPanel)
//			}
			,{
				text : '取消',
				iconCls : 'btn-cancel',
				handler : this.cancel.createCallback(this)
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
					Ext.ux.Toast.msg("信息提示", "成功删除所选记录！");
					var gridPanel=Ext.getCmp('BookGrid');
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