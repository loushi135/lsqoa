/**
 * @author 
 * @createtime 
 * @class SignApplyForm
 * @extends Ext.Window
 * @description SignApply表单
 */
SignApplyForm=Ext.extend(Ext.Window,{
	//内嵌FormPanel
	formPanel:null,
	//构造函数
	constructor:function(_cfg){
		Ext.applyIf(this,_cfg);
		//必须先初始化组件
		this.initUIComponents();
		SignApplyForm.superclass.constructor.call(this,{
			id:'SignApplyFormWin',
			layout:'fit',
			items:this.formPanel,
			modal:true,
			width : 700,
			height : 470,
			maximizable:true,
			title:'[签证变更]详细信息',
			buttonAlign : 'center',
			buttons:this.buttons
		});
	},//end of the constructor
	//初始化组件
	initUIComponents:function(){
		var thiz = this;
		this.formPanel=new Ext.FormPanel({
				layout : 'form',
				trackResetOnLoad:true,
				bodyStyle: 'padding:10px 10px 10px 10px',
				border:false,
				url : __ctxPath + '/statistics/saveSignApply.do',
				id : 'SignApplyForm',
				defaults : {
					anchor : '98%,98%'
				},
				defaultType : 'textfield',
				items : [{
							name : 'signApply.id',
							id : 'id',
							xtype:'hidden',
							value : this.id == null ? '' : this.id
						},{
							   xtype:'hidden',
							   name:'signApply.processRunId',
							   value:-1
						},{
								xtype: 'container',
								layout:'column',
								height:25,
								defaults:{
									xtype:'label',
									style:{
										margin:'0px 0px 3px 0px'
									}
								},
								items:[
									{
										text:'项目名称：',
										columnWidth:.15
									},{
										xtype:'hidden',
										id:'signApply.projectChargerUserId',
										name:'projectChargerUserId'
									},{
										xtype:'hidden',
										id:'signApply.signApplyDepartmentId',
										name:'signApplyDepartmentId'
									},
									{
										xtype:'textfield',
										id:'signApply.proName',
										name:'proName',
										maxLength : 64,
										allowBlank :false,
										emptyText:'请选择材料发包合同',
										columnWidth:.4,
										listeners:{
											focus:thiz.selectMaterialContract
										}
									},
									{
										text:'签证编号：',
										style:'margin-left:10px;',
										columnWidth:.15
									},{
										xtype:'textfield',
										id:'signNo',
										name:'signApply.signNo',
										maxLength : 64,
										readOnly:true,
										style:"background:#F0F0F0;",
										columnWidth:.3,
										value:'系统自动生成!'
									}
								]
							},{
								xtype: 'container',
								layout:'column',
								height:25,
								defaults:{
									xtype:'label',
									style:{
										margin:'0px 0px 3px 0px'
									}
								},
								items:[
									{
										text:'施工单位：',
										columnWidth:.15
									},
									{
										xtype:'textfield',
										id:'signApply.workUnit',
										name:'workUnit',
										maxLength : 64,
										style:"background:#F0F0F0;",
										emptyText:'请选择材料发包合同',
										columnWidth:.4,
										listeners:{
											focus:thiz.selectMaterialContract
										}
									},
									{
										text:'合同编号：',
										style:'margin-left:10px;',
										columnWidth:.15
									},{
										xtype:'textfield',
										id:'signApply.contract.contractNo',
										name:'contractNo',
										maxLength : 64,
										readOnly:true,
										columnWidth:.3,
										emptyText:'请选择材料发包合同',
										listeners:{
											focus:thiz.selectMaterialContract
										}
									},{
										xtype:'hidden',
										id:'signApply.contract.id',
										name:'signApply.contract.id'
									}
								]
							},{
								xtype : 'container',
								layout : 'column',
								height:25,
								defaults:{
									xtype:'label',
									style:{
										margin:'0px 0px 3px 0px'
									}
								},
								items : [ {
											text : '承担费用单位：',
											columnWidth:0.15
										}, {
											xtype : 'textfield',
											id:'feeSupplierName',
											name:'signApply.feeSupplierName',
											allowBlank : false,
											readOnly:true,
											emptyText:'请选择承担费用单位',
											columnWidth : 0.85,
											listeners:{
												focus:function(field){
													new SuppliersAssessSelector(function(suppliersId,supplierName,supplierNo){
														field.setValue(supplierName);
														Ext.getCmp("signApply.feeSupplier.suppliersId").setValue(suppliersId);
													},true).show();
												}
											}
										},{
											xtype:'hidden',
											id:'signApply.feeSupplier.suppliersId',
											name:'signApply.feeSupplier.suppliersId'
										}
									]
							},{
								xtype : 'container',
								layout : 'column',
								height:80,
								defaults:{
									xtype:'label',
									style:{
										margin:'0px 0px 3px 0px'
									}
								},
								items : [ {
											text : '签证原因：',
											columnWidth:0.15
										}, {
											xtype : 'textarea',
											id:'signReason',
											name:'signApply.signReason',
											allowBlank : false,
											columnWidth : 0.85
										}]
							},{
								xtype : 'container',
								layout : 'column',
								height:80,
								defaults:{
									xtype:'label',
									style:{
										margin:'0px 0px 3px 0px'
									}
								},
								items : [ {
											text : '签证内容：',
											columnWidth:0.15
										}, {
											xtype : 'textarea',
											id:'signContent',
											name:'signApply.signContent',
											allowBlank : false,
											columnWidth : 0.85
										}]
							},{
								xtype : 'container',
								layout : 'column',
								height:80,
								defaults:{
									xtype:'label',
									style:{
										margin:'0px 0px 3px 0px'
									}
								},
								items : [ {
											text : '备注说明：',
											columnWidth:0.15
										}, {
											xtype : 'textarea',
											id:'remark',
											name:'signApply.remark',
											allowBlank : false,
											columnWidth : 0.85
										}]
							},{
								xtype : 'panel',
								layout : 'column',
								defaultType : 'label',
								style:'padding-top:3px;margin-buttom:5px',
								height: 70,
								border:false,
								items : [{
											text : '附件：',
											columnWidth:0.15
										},{
											xtype : 'hidden',
											name : 'signApply.attachIds',
											id : 'attachIds'
										},{
											xtype : 'hidden',
											name : 'signApply.attachFiles',
											id : 'attachFiles'
										},{
											xtype : 'panel',
											id:'signApply.displayAttach',
											columnWidth : 0.75,
											height: 60,
											frame:true,
											autoScroll:true,
											html : ''
										},{
											xtype : 'button',
											iconCls : 'btn-upload',
											text : '上传',
											columnWidth : 0.1,
											handler : function() {
												var dialog = App.createUploadDialog({
																		file_cat : 'flow/signApply',
																		callback : function(data) {
																			Ext.getCmp('signApply.displayAttach').body.update('');
																			Ext.getCmp("attachFiles").setValue('');
																			Ext.getCmp('attachIds').setValue('');
																			var entrantFile = Ext.getCmp("attachFiles");
																			var fileIds = Ext.getCmp('attachIds');
																			var display = Ext.getCmp('signApply.displayAttach');
																			for (var i = 0; i < data.length; i++) {
																				if (fileIds.getValue() != '') {
																					fileIds.setValue(fileIds.getValue()+ ',');
																					entrantFile.setValue(entrantFile.getValue()+ ',');
																				}
																				entrantFile.setValue(entrantFile.getValue()+data[i].filepath+":"+data[i].filename.replace(/\s+/g,""));
//																				entrantFile.setValue(entrantFile.getValue()+"<a href=javascript:window.location.href=\""+__ctxPath+"/file-Download?filePath=attachFiles/"+data[i].filepath+"&fileName="+data[i].filename.replace(/\s+/g,"")+"\">"+data[i].filename.replace(/\s+/g,"")+"</a>");
																				fileIds.setValue(fileIds.getValue()+data[i].fileId);
																				Ext.DomHelper.append(display.body,'<span><a href="#" onclick="FileAttachDetail.show('+data[i].fileId+')">'+data[i].filename.replace(/\s+/g,"")+'</a>&nbsp;|&nbsp;</span>');
																			}
																		},
																		permitted_max_size :1024*1024*20
																	});
													dialog.show(this);
											}
										}
										]
								}
						]
			});
		//加载表单对应的数据	
		if (this.id != null && this.id != 'undefined') {
			this.formPanel.getForm().load({
				deferredRender : false,
				url : __ctxPath + '/statistics/getSignApply.do?id='+ this.id,
				waitMsg : '正在载入数据...',
				success : function(form, action) {
					var jsonData = Ext.util.JSON.decode(action.response.responseText);
					var res = jsonData.data;
					
					if(!Ext.isEmpty(res.contract)){
						if(!Ext.isEmpty(res.contract.project)){
							Ext.getCmp("signApply.proName").setValue(res.contract.project.proName);
							Ext.getCmp("signApply.proName").originalValue=res.contract.project.proName;
						}
						if(!Ext.isEmpty(res.contract.suppliersAssess)){
							Ext.getCmp("signApply.workUnit").setValue(res.contract.suppliersAssess.suppliersName);
							Ext.getCmp("signApply.workUnit").originalValue=res.contract.suppliersAssess.suppliersName;
						}
						Ext.getCmp("signApply.contract.contractNo").setValue(res.contract.contractNo);
						Ext.getCmp("signApply.contract.contractNo").originalValue=res.contract.contractNo;
						Ext.getCmp("signApply.contract.id").setValue(res.contract.id);
						Ext.getCmp("signApply.contract.id").originalValue=res.contract.id;
					}
					
					if(!Ext.isEmpty(res.feeSupplier)){
						Ext.getCmp("feeSupplierName").setValue(res.feeSupplier.suppliersName);
						Ext.getCmp("feeSupplierName").originalValue=res.feeSupplier.suppliersName;
						Ext.getCmp("signApply.feeSupplier.suppliersId").setValue(res.feeSupplier.suppliersId);
						Ext.getCmp("signApply.feeSupplier.suppliersId").originalValue=res.feeSupplier.suppliersId;
					}
					
					var attachId = res.attachIds;
					var attachFile = res.attachFiles;
					if(attachId!= null && attachId != 'undefined' && attachFile!= null && attachFile != 'undefined'){
							var ids = attachId.split(',');
							var files= attachFile.split(',');
							for(var i=0;i<ids.length;i++){
								if(files[i].lastIndexOf('<a href')!=-1){
									  Ext.DomHelper.append(Ext.getCmp('signApply.displayAttach').body,'<span><a href="#" onclick="FileAttachDetail.show('+ids[i]+')">'+files[i].replace(/\s+/g,"")+'</a>&nbsp;|&nbsp;</span>');
								}else if(files[i].lastIndexOf(':')!=-1){
									  var fg = files[i].split(':');
									  Ext.DomHelper.append(Ext.getCmp('signApply.displayAttach').body,'<span><a href="#" onclick="FileAttachDetail.show('+ids[i]+')">'+fg[1].replace(/\s+/g,"")+'</a>&nbsp;|&nbsp;</span>');
								}
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
					var gridPanel=Ext.getCmp('SignApplyGrid');
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
	,
	selectMaterialContract:function(){
		new MaterialContractSelector(function(id,contractNo,contractAmount,proName,proNo,suppliersName,suppliersId,projectId,departmentId,departmentIdName,projectChargerId,projectChargerName){
					Ext.getCmp("signApply.proName").setValue(proName);			
					Ext.getCmp("signApply.workUnit").setValue(suppliersName);			
					Ext.getCmp("signApply.contract.contractNo").setValue(contractNo);			
					Ext.getCmp("signApply.contract.id").setValue(id);
					Ext.getCmp("signApply.projectChargerUserId").setValue(projectChargerId);
					Ext.getCmp("signApply.signApplyDepartmentId").setValue(departmentId);
			},true).show();
	}
});