Ext.ns("PhotoUploadWin");
PhotoUploadWin = Ext.extend(Ext.Window,{
	//内嵌FormPanel
	formPanel:null,
	//构造函数
	constructor:function(_cfg){
		Ext.applyIf(this,_cfg);
		//必须先初始化组件
		this.initUIComponents();
		PhotoUploadWin.superclass.constructor.call(this,{
			id:'PhotoUploadWin',
			layout:'fit',
			items:this.formPanel,
			modal:true,
			width : 500,
			height : 200,
			maximizable:true,
			title:'上传图片',
			buttonAlign : 'center',
			buttons:this.buttons
		});
	},//end of the constructor
	//初始化组件
	initUIComponents:function(){
		
		var url = __ctxPath + "/hrm/listPhotoFolder.do";
		var photoTreeSelector = new TreeSelector("photoTreeSelector", url, "请选择上传文件夹", "photofolderId",false,250,'folderName',false);
		this.formPanel=new Ext.FormPanel({
				layout : 'form',
				autoScroll : true,
				trackResetOnLoad:true,
			    bodyStyle: 'padding:10px 10px 10px 10px;',
				border:false,
				url : __ctxPath + '/hrm/savePhoto.do',
				id : 'PhotoUploadWinForm',
				defaultType : 'textfield',
				items:[
						{
							xtype:'container',
							layout:'column',
							items:[
									{xtype:'button',iconCls : 'btn-add',text: "添加图片", handler: this.insertImages.createCallback(this)},
									{xtype:'label',text:' 请选择上传文件夹:',style:'margin-left:5px;'},photoTreeSelector
								]
						},
						{xtype:'label',style:'color:red;',text:'使用说明：先添加图片，然后选择上传文件夹，再保存'},
						{xtype:'hidden',id:'photoFileIds',name:'fileIds'},
						{xtype:'hidden',id:'photofolderId',name:'folderId'}
				]
		});
		//初始化功能按钮
		this.buttons=[
				{
					text : '保存',
					iconCls : 'btn-save',
					handler :this.save.createCallback(this.formPanel,this)
				}, {
					text : '重置',
					iconCls : 'btn-reset',
					handler :this.reset.createCallback(this.formPanel)
				},
				{
					text : '关闭',
					iconCls : 'btn-cancel',
					handler : this.cancel.createCallback(this)
			}];
	    if(!Ext.isEmpty(this.folderId)&&this.folderId!="-1"){
	    	Ext.getCmp("photofolderId").setValue(this.folderId);
	    	Ext.getCmp("photoTreeSelector").setValue(this.folderName);
	    }
	},
	insertImages:function(panel){
		var dialog = App.createUploadDialog({
							file_cat : 'hrm/photo',
							callback : function(data) {
								Ext.getCmp('photoFileIds').setValue('');
								var fileIds = Ext.getCmp('photoFileIds');
								for (var i = 0; i < data.length; i++) {
									if (fileIds.getValue() != '') {
										fileIds.setValue(fileIds.getValue()+ ',');
									}
									fileIds.setValue(fileIds.getValue()+data[i].fileId);
							}
			},
			permitted_extensions : ['jpg','JPG','jpeg','JPEG','BMP','bmp','PNG','png','GIF','gif'],
			permitted_max_size : 1024*800
		});
		dialog.show(this);
	},
	saveImages:function(panel){
			Ext.Ajax.request({
				url:__ctxPath+'/hrm/savePhoto.do?fileIds='+fileIds,
				params:{'folderId':folderId},
				success:function(response,options){
					var respText = Ext.util.JSON.decode(response.responseText);
					outStore.removeAll();
					panel.idArr.splice(0,panel.idArr.length);
					Ext.MessageBox.alert('成功' ,'保存成功!')
				},
				failure: function(response,options) {    
                     var respText = Ext.util.JSON.decode(response.responseText);    
                     Ext.Msg.alert('错误', response.error);    
                }
			})
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
					var gridPanel=Ext.getCmp('PhotoFolderView');
					if(gridPanel!=null){
						gridPanel.store.reload({params : {
												folderId:Ext.getCmp("photofolderId").getValue()
											}});
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
})
