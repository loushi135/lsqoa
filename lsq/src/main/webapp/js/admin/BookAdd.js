/**
 * @author juql
 * @createtime 
 * @class BookAdd
 * @extends Ext.Window
 * @description Attendance表单
 */
BookAdd=Ext.extend(Ext.Window,{
	//内嵌FormPanel
	formPanel:null,
	//构造函数
	constructor:function(_cfg){
		Ext.applyIf(this,_cfg);
		//必须先初始化组件
		this.initUIComponents();
		BookAdd.superclass.constructor.call(this,{
			id:'BookAddWin',
			layout:'fit',
			items:this.formPanel,
			modal:true,
			height:200,
			width:400,
			maximizable:true,
			title:'导入档案',
			buttonAlign : 'center',
			buttons:this.buttons
		});
	},//end of the constructor
	//初始化组件
	initUIComponents:function(){
		this.formPanel=new Ext.FormPanel({
				layout : 'form',
				bodyStyle: 'padding:10px 10px 10px 10px',
				baseCls: 'x-plain',
				border:false,
				url : __ctxPath + '/admin/uploadBook.do',
				id : 'BookAdd',
				defaults : {
					anchor : '98%,98%'
				},
				items : [{
							xtype : 'panel',
							id : 'filePanel',
							height : 20,
							autoScroll : true
						}, {
							xtype : 'button',
							text : '选择文件',
							iconCls : 'menu-attachment',
								handler : function() {
									var dialog = App.createUploadDialog({
										file_cat : 'hrm',
										callback : function(data) {
											var fileIds = Ext.getCmp("fileIds");
											var filePanel = Ext.getCmp('filePanel');
											for (var i = 0; i < data.length; i++) {
												if (fileIds.getValue() != '') {
													fileIds.setValue(fileIds.getValue()+ ',');
												}
												fileIds.setValue(fileIds.getValue()+ data[i].fileId);
											}
										}
									});
									dialog.show(this);
								}
						}, {
							xtype : 'hidden',
							id : 'fileIds',
							name : 'fileIds'
						}]
			});
		//初始化功能按钮
		this.buttons=[{
				text : '导入',
				iconCls : 'btn-save',
				handler : this.save.createCallback(this.formPanel,this)
			}, {
				text : '取消',
				iconCls : 'btn-cancel',
				handler : this.cancel.createCallback(this)
			}];
	},//end of the initcomponents
	
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
				waitMsg : '正在数据，请勿关闭窗口和移动或者删除数据文件...',
				success : function(fp, action) {
					Ext.ux.Toast.msg('操作信息', '导入成功！');
					var gridPanel=Ext.getCmp('BookGrid');
					if(gridPanel!=null){
						gridPanel.getStore().reload();
					}
					window.close();
				},
				failure : function(fp, action) {
					Ext.MessageBox.show({
								title : '操作信息',
								msg : '导入出错，请联系管理员！',
								buttons : Ext.MessageBox.OK,
								icon : Ext.MessageBox.ERROR
							});
					window.close();
				}
			});
		}
	}//end of save
	
});