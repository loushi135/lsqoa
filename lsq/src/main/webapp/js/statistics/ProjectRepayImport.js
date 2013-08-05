/**
 * @author 
 * @createtime 
 * @class projectRepayForm
 * @extends Ext.Window
 * @description projectRepay表单
 */
ProjectRepayImport=Ext.extend(Ext.Window,{
	//内嵌FormPanel
	formPanel:null,
	//构造函数
	constructor:function(_cfg){
		Ext.applyIf(this,_cfg);
		//必须先初始化组件
		this.initUIComponents();
		ProjectRepayImport.superclass.constructor.call(this,{
			id:'ProjectRepayImportWin',
			layout:'fit',
			items:this.formPanel,
			modal:true,
			height:200,
			width:400,
			maximizable:true,
			title:'导入[现金报销]',
			buttonAlign : 'center',
			buttons:this.buttons
		});
	},//end of the constructor
	//初始化组件
	initUIComponents:function(){
		this.formPanel=new Ext.FormPanel({
				enctype:'multipart/form-data',  
        		fileUpload: true,  
				layout : 'form',
				trackResetOnLoad:true,
				bodyStyle: 'padding:10px 10px 10px 10px',
				border:false,
				url : __ctxPath + '/statistics/importsProjectRepay.do',
				id : 'ProjectRepayImport',
				defaults : {
					anchor : '98%,98%'
				},
				defaultType : 'textfield',
				items : [{
								name : 'id',
								id : 'repay_id',
								xtype : 'hidden',
								value : this.proId
//							}, {
//								height: 30,  
//           						anchor:'100%',  
//								fieldLabel : '选择本地文件',
//								blankText:"所选文件为空,请确认", 
//								allowBlank:false,
//								name : 'importFileExcle',
//								id : 'importFileExcle',
//								inputType:'file',
//								
							},{
							xtype : 'container',
					autoHeight : true,
					layout : 'column',
					autoWidth : true,
					defaultType : 'label',
					style : 'padding-left:0px;padding-bottom:5px;',
					items : [{
								text : '附件:',
								width : 85,
								style : "margin-top:15px;"
							}, {
								xtype : 'hidden',
								name : 'attachIds',
								id : 'project_repay.attachIds'
							}, {
								xtype : 'hidden',
								//name : 'suppliersAssess.attachFiles',
								id : 'project_repay.attachFiles'
							}, {
								xtype : 'panel',
								id : 'project_repay.displayAttach',
								columnWidth : .95,
								height : 65,
								frame : false,
								autoScroll : true,
								style : 'padding-left:10px;padding-top:0px;',
								html : ''
							}, {
								xtype : 'button',
								iconCls : 'btn-upload',
								text : '上传',
								handler : function() {
									var dialog = App.createUploadDialog({
										file_cat : 'flow/projectRepay',
										permitted_extensions:['xls','xlsx'],
										callback : function(data) {
											var attachFiles = Ext.getCmp("project_repay.attachFiles");
											var fileIds = Ext.getCmp('project_repay.attachIds');
											var display = Ext.getCmp('project_repay.displayAttach');
											display.body.update('');
											attachFiles.setValue('');
											fileIds.setValue('');
											for (var i = 0; i < data.length; i++) {
												if (fileIds.getValue() != '') {
													fileIds.setValue(fileIds.getValue() + ',');
													attachFiles.setValue(attachFiles.getValue() + ',');
												}
												attachFiles.setValue(attachFiles.getValue() + data[i].filepath + ":" + data[i].filename.replace(/\s+/g, ""));
												fileIds.setValue(fileIds.getValue() + data[i].fileId);
												Ext.DomHelper.append(display.body, '<span><a href="#" onclick="FileAttachDetail.show(' + data[i].fileId + ')">'
												+ data[i].filename.replace(/\s+/g, "") + '</a>&nbsp;|&nbsp;</span>');
											}
										},
										permitted_max_size : 1024 * 1024 * 20
									});
									dialog.show(this);
								}
							}]
						}
							]
			});
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
					var record = Ext.util.JSON.decode(action.response.responseText);
					Ext.ux.Toast.msg('操作信息', record.data);
					var	projectRepayView=Ext.getCmp('projectRepayView');
					if(!Ext.isEmpty(projectRepayView)){
						projectRepayView.initData();
						projectRepayView.store.reload();
					}
					window.close();
				},
				failure : function(fp, action) {
					var record = Ext.util.JSON.decode(action.response.responseText);
					Ext.MessageBox.show({
								title : '操作信息',
								msg : record.data,
								buttons : Ext.MessageBox.OK,
								icon : Ext.MessageBox.ERROR
							});
					//window.close();
				}
			});
		}
	}//end of save
	
});