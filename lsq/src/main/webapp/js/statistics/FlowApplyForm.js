/**
 * @author 
 * @createtime 
 * @class FlowApplyForm
 * @extends Ext.Window
 * @description FlowApply表单
 */
FlowApplyForm=Ext.extend(Ext.Window,{
	//内嵌FormPanel
	formPanel:null,
	//构造函数
	constructor:function(_cfg){
		Ext.applyIf(this,_cfg);
		//必须先初始化组件
		this.initUIComponents();
		FlowApplyForm.superclass.constructor.call(this,{
			id:'FlowApplyFormWin',
			layout:'fit',
			items:this.formPanel,
			modal:true,
			height:520,
			width:600,
			maximizable:true,
			title:'[OA新增、变更流程申请]详细信息',
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
				url : __ctxPath + '/statistics/saveFlowApply.do',
				id : 'FlowApplyForm',
				defaults : {
					anchor : '98%,98%'
				},
				defaultType : 'textfield',
				items : [{
							name : 'flowApply.id',
							id : 'id',
							xtype:'hidden',
							value : this.id == null ? '' : this.id
						},{
							xtype:'hidden',
							name:'flowApply.processRunId',
							value:-1
						},		
						{
							fieldLabel : '流程名称',
							xtype:'combo',
							allowBlank:false,
							hiddenName : 'flowApply.flowName',
							id : 'flowName',
							maxLength:64,
							mode:'remote',
							emptyText:'新增流程请填写，变更流程请选择。',
							triggerAction:'all',
							store:new Ext.data.Store({
									proxy : new Ext.data.HttpProxy({
										url : __ctxPath + "/flow/listProDefinition.do",
										params : {
											typeId : this.typeId == 0
										}
									}),
									reader : new Ext.data.JsonReader({
										root : "result",
										totalProperty : "totalCounts",
										id : "id",
										fields : [ {
											name : "defId",
											type : "int"
										}, "proType", "name", "description", "createtime", "deployId" ]
									}),
									remoteSort : true
								}),
							valueField:'name',
							displayField:'name'
						}, {
							fieldLabel : '需求背景',
							xtype:'textarea',
							name : 'flowApply.background',
							id : 'background',
							allowBlank : false
						}, {
							fieldLabel : '流程描述',
							xtype:'textarea',
							name : 'flowApply.flowDesc',
							id : 'flowDesc',
							allowBlank : false
						}, {
							fieldLabel : '流程节点',
							xtype:'textarea',
							name : 'flowApply.flowNode',
							id : 'flowNode',
							allowBlank : false
						}, {
							fieldLabel : '备注',
							xtype:'textarea',
							name : 'flowApply.remark',
							id : 'remark'
						},{
							xtype : 'container',
							autoHeight : true,
							layout : 'column',
							autoWidth : true,
							defaultType : 'label',
							style : 'padding-left:0px;padding-bottom:5px;',
							items : [{
										text : '附件:',
										width:95,
										style : "margin-top:15px;"
									},{
										xtype : 'hidden',
										name : 'attachIds',
										id : 'flowApply.attachIds'
									},{
										xtype : 'hidden',
										name : 'attachFiles',
										id : 'flowApply.attachFiles'
									},{
										xtype : 'panel',
										id:'flowApply.displayAttach',
										columnWidth:.95,
										height: 65,
										frame:false,
										autoScroll:true,
										style : 'padding-left:10px;padding-top:0px;',
										html : ''
									},{
										xtype : 'button',
										iconCls : 'btn-upload',
										text : '上传',
										handler : function() {
											var dialog = App.createUploadDialog({
																	file_cat : 'flow/flowApply',
																	callback : function(data) {
																		var attachFiles = Ext.getCmp("flowApply.attachFiles");
																		var fileIds = Ext.getCmp('flowApply.attachIds');
																		var display = Ext.getCmp('flowApply.displayAttach');
																		display.body.update('');
																		attachFiles.setValue('');
																		fileIds.setValue('');
																		for (var i = 0; i < data.length; i++) {
																			if (fileIds.getValue() != '') {
																				fileIds.setValue(fileIds.getValue()+ ',');
																				attachFiles.setValue(attachFiles.getValue()+ ',');
																			}
																			attachFiles.setValue(attachFiles.getValue()+data[i].filepath+":"+data[i].filename.replace(/\s+/g,""));
																			fileIds.setValue(fileIds.getValue()+data[i].fileId);
																			Ext.DomHelper.append(display.body,'<span><a href="#" onclick="FileAttachDetail.show('+data[i].fileId+')">'+data[i].filename.replace(/\s+/g,"")+'</a>&nbsp;|&nbsp;</span>');
																		}
																	},
																	permitted_max_size :1024*1024*20
																});
												dialog.show(this);
										}
									}]
						}, {
							fieldLabel : '经办人',
							readOnly:true,
							name : 'flowApply.applyUser.fullname',
							id : 'flowApply.applyUser.fullname',
							value:__currentUser
						}, {
							xtype : 'hidden',
							name : 'flowApply.applyUser.userId',
							id : 'flowApply.applyUser.userId',
							value:__currentUserId
						}, {
							fieldLabel : '相关部门',
							name : 'flowApply.relatedUser.fullname',
							id : 'flowApply.relatedUser.fullname',
							allowBlank : false,
							listeners : {
								focus : function() {
									UserSelector.getView(function(ids, names) {
										Ext.getCmp("flowApply.relatedUser.fullname").setValue(names);
										Ext.getCmp("flowApply.relatedUser.userId").setValue(ids);
									}, true).show();
								}
							}
						}, {
							xtype : 'hidden',
							name : 'flowApply.relatedUser.userId',
							id : 'flowApply.relatedUser.userId'
						}]
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
		if (this.id != null && this.id != 'undefined') {
			this.formPanel.getForm().load({
				deferredRender : false,
				url : __ctxPath + '/statistics/getFlowApply.do?id='+ this.id,
				waitMsg : '正在载入数据...',
				success : function(form, action) {
						var jsonData = Ext.util.JSON.decode(action.response.responseText);
						var res = jsonData.data;
						//Ext.getCmp("").originalValue=
						if(!Ext.isEmpty(res.applyUser)){
							Ext.getCmp('flowApply.applyUser.fullname').setValue(res.applyUser.fullname);
							Ext.getCmp('flowApply.applyUser.fullname').originalValue=res.applyUser.fullname;
							Ext.getCmp('flowApply.applyUser.userId').setValue(res.applyUser.userId);
							Ext.getCmp('flowApply.applyUser.userId').originalValue=res.applyUser.userId;
						}
						if(!Ext.isEmpty(res.relatedUser)){
							Ext.getCmp('flowApply.relatedUser.fullname').setValue(res.relatedUser.fullname);
							Ext.getCmp('flowApply.relatedUser.fullname').originalValue=res.relatedUser.fullname;
							Ext.getCmp('flowApply.relatedUser.userId').setValue(res.relatedUser.userId);
							Ext.getCmp('flowApply.relatedUser.userId').originalValue=res.relatedUser.userId;
						}
						var af = res.fileAttachs;
						var filePanel = Ext.getCmp('flowApply.displayAttach');
						var fileIds = Ext.getCmp("flowApply.attachIds");
						for (var i = 0; i < af.length; i++) {
							if (fileIds.getValue() != '') {
								fileIds.setValue(fileIds.getValue() + ',');
							}
							fileIds.setValue(fileIds.getValue() + af[i].fileId);
							Ext.DomHelper
									.append(
											filePanel.body,
											'<span><a href="#" onclick="FileAttachDetail.show('
													+ af[i].fileId
													+ ')">'
													+ af[i].fileName.replace(/\s+/g,"")+'</a>&nbsp;|&nbsp;</span>');
						}
						fileIds.originalValue = fileIds.getValue();
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
					var gridPanel=Ext.getCmp('FlowApplyGrid');
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