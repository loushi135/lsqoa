/**
 * @author 
 * @createtime 
 * @class AnnounceForm
 * @extends Ext.Window
 * @description Announce表单
 */
AnnounceForm=Ext.extend(Ext.Window,{
	//内嵌FormPanel
	formPanel:null,
	//构造函数
	constructor:function(_cfg){
		Ext.applyIf(this,_cfg);
		//必须先初始化组件
		this.initUIComponents();
		AnnounceForm.superclass.constructor.call(this,{
			id:'AnnounceFormWin',
			layout:'fit',
			items:this.formPanel,
			modal:true,
			height:460,
			width:700,
			maximizable:true,
			title:'通知详细信息',
			buttonAlign : 'center',
			buttons:this.buttons
		});
	},//end of the constructor
	//初始化组件
	initUIComponents:function(){
		this.formPanel=new Ext.FormPanel({
				layout : 'form',
				bodyStyle: 'padding:10px 10px 10px 10px',
				border:false,
				url : __ctxPath + '/system/saveAnnounce.do',
				id : 'AnnounceForm',
				defaults : {
					anchor : '98%,98%'
				},
				defaultType : 'textfield',
				labelWidth : 75,
				items : [{
							name : 'announce.id',
							id : 'id',
							xtype:'hidden',
							value : this.id == null ? '' : this.id
						}
																																										,{
												fieldLabel : '公告内容',	
												xtype:'htmleditor',
												name : 'announce.context',
						id : 'context'
							}
																																				,{
												fieldLabel : '创建时间',	
												xtype:'hidden',
												name : 'announce.createtime',
						id : 'createtime'
							}
																																				,{
												fieldLabel : '创建人',	
												xtype:'hidden',
												name : 'announce.createUser.userId',
						id : 'createUser'
							},{
							xtype : 'container',
							autoHeight : true,
							layout : 'column',
							autoWidth : true,
							defaultType : 'label',
							style : 'padding-left:0px;padding-top:5px;',
							items : [{
										columnWidth : .1,
										text : '附件:'
									},{
										xtype : 'hidden',
										name : 'attachIDs',
										id : 'contractAttachIDs'
									},{
										xtype : 'hidden',
										name : 'attachFile',
										id : 'attachFile'
									},{
										xtype : 'panel',
										id:'displayAttach',
										columnWidth : .7,
										height: 65,
										frame:true,
										autoScroll:true,
										html : ''
									},{
										xtype : 'button',
										columnWidth : .15,
										style : 'margin-left:10px',
										iconCls : 'btn-upload',
										text : '上传',
										handler : function() {
										var dialog = App.createUploadDialog({
														file_cat : 'csutomer/contract',
														callback : function(data) {
															var attachFile = Ext.getCmp("attachFile");
															var fileIds = Ext.getCmp('contractAttachIDs');
															var display = Ext.getCmp('displayAttach');
															for (var i = 0; i < data.length; i++) {
																if (fileIds.getValue() != '') {
																	fileIds.setValue(fileIds.getValue()+ ',');
																	attachFile.setValue(attachFile.getValue()+',');
																}
																attachFile.setValue(attachFile.getValue() + "<a href=javascript:window.location.href='"+__ctxPath+"/file-Download?filePath=attachFiles/"+data[i].filepath+"&fileName="+data[i].filename+"'>"+data[i].filename+"</a>");
																fileIds.setValue(fileIds.getValue()+data[i].fileId);
																Ext.DomHelper.append(display.body,'<span><a href="#" onclick="FileAttachDetail.show('+data[i].fileId+')">'+data[i].filename+'</a><img class="img-delete" src="'+__ctxPath+'/images/system/delete.gif" onclick="FileAttachDetail.removeContractAttach(this,'+data[i].fileId+')"/>&nbsp;|&nbsp;</span>');
															}
														}
													});
										dialog.show('queryBtn');
									}
							}]}
																								
												]
			});
		//加载表单对应的数据	
		if (this.id != null && this.id != 'undefined') {
			this.formPanel.getForm().load({
				deferredRender : false,
				url : __ctxPath + '/system/getAnnounce.do?id='+ this.id,
				waitMsg : '正在载入数据...',
				success : function(form, action) {
					var data = action.result.data;
					Ext.getCmp("createUser").setValue(data.createUser.userId);
					
					var fileAttachs = data.fileAttachs;
					var display = Ext.getCmp('displayAttach');
					var fileIds = Ext.getCmp('contractAttachIDs');
					if(fileAttachs.length > 0){
						for(var i = 0 ; i < fileAttachs.length ; i ++){
							if (fileIds.getValue() != '') {
								fileIds.setValue(fileIds.getValue()+ ',');
							}
							fileIds.setValue(fileIds.getValue()+fileAttachs[i].fileId);
							Ext.DomHelper.append(display.body,'<span><a href="#" onclick="FileAttachDetail.show('+fileAttachs[i].fileId+')">'+fileAttachs[i].fileName+'</a><img class="img-delete" src="'+__ctxPath+'/images/system/delete.gif" onclick="FileAttachDetail.removeContractAttach(this,'+fileAttachs[i].fileId+')"/>&nbsp;|&nbsp;</span>');
						}
					}
					
				},
				failure : function(form, action) {
				}
			});
		}
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
					var gridPanel=Ext.getCmp('AnnounceGrid');
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