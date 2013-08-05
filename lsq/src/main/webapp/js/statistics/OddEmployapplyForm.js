/**
 * @author 
 * @createtime 
 * @class OddEmployapplyForm
 * @extends Ext.Window
 * @description OddEmployapply表单
 */
OddEmployapplyForm=Ext.extend(Ext.Window,{
	//内嵌FormPanel
	formPanel:null,
	//构造函数
	constructor:function(_cfg){
		Ext.applyIf(this,_cfg);
		//必须先初始化组件
		this.initUIComponents();
		OddEmployapplyForm.superclass.constructor.call(this,{
			id:'OddEmployapplyFormWin',
			layout:'fit',
			items:this.formPanel,
			modal:true,
			width : 700,
			height : 470,
			maximizable:true,
			title:'[零星用工]详细信息',
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
				url : __ctxPath + '/statistics/saveOddEmployapply.do',
				id : 'OddEmployapplyForm',
				defaults : {
					anchor : '98%,98%'
				},
				defaultType : 'textfield',
				items : [{
							name : 'oddEmployapply.id',
							id : 'id',
							xtype:'hidden',
							value : this.id == null ? '' : this.id
						},{
							   xtype:'hidden',
							   name:'oddEmployapply.processRunId',
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
										id:'oddEmployapply.projectChargerUserId',
										name:'projectChargerUserId'
									},{
										xtype:'hidden',
										id:'oddEmployapply.oddEmployapplyDepartmentId',
										name:'oddEmployapplyDepartmentId'
									},
									{
										xtype:'textfield',
										id:'oddEmployapply.proName',
										name:'proName',
										maxLength : 64,
										allowBlank :false,
										emptyText:'请选择材料发包合同',
										columnWidth:.3,
										listeners:{
											focus:thiz.selectMaterialContract
										}
									},
									{
										text:'合同编号：',
										style:'margin-left:10px;',
										columnWidth:.16
									},{
										xtype:'textfield',
										id:'oddEmployapply.contract.contractNo',
										name:'oddEmployapply.contract.contractNo',
										maxLength : 64,
										readOnly:true,
										columnWidth:.39,
										emptyText:'请选择材料发包合同',
										listeners:{
											focus:thiz.selectMaterialContract
										}
									},
									{
										xtype:'hidden',
										id:'oddEmployapply.contract.id',
										name:'oddEmployapply.contract.id'
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
										text:'类别：',
										columnWidth:.15
									},{
										xtype:'combo',
										id:'employType',
										hiddenName:'oddEmployapply.employType',
										editable:false,
										triggerAction : 'all',
										store:['施工单位','人员'],
										columnWidth:.3,
										value:'施工单位',
										listeners:{
											select:function(combo){
												if(combo.getValue()=='人员'){
													Ext.getCmp("oddEmployapply.workUnit").disable();
													Ext.getCmp("num").allowBlank=false;
													Ext.getCmp("contact").allowBlank=false;
												}else{
													Ext.getCmp("oddEmployapply.workUnit").enable();
													Ext.getCmp("num").allowBlank=true;
													Ext.getCmp("contact").allowBlank=true;
												}
											}
										}
									},{
										text:'施工单位：',
										style:'margin-left:10px;',
										columnWidth:.16
									},
									{
										xtype:'textfield',
										id:'oddEmployapply.workUnit',
										name:'workUnit',
										maxLength : 64,
										style:"background:#F0F0F0;",
										emptyText:'请选择材料发包合同',
										columnWidth:.39,
										listeners:{
											focus:thiz.selectMaterialContract
										}
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
										text:'人数：',
										columnWidth:.15
									},{
										xtype:'numberfield',
										id:'num',
										name:'oddEmployapply.num',
										maxLength:9,
										columnWidth:.3
									},{
										text:'姓名/联系方式：',
										style:'margin-left:10px;',
										columnWidth:.16
									},
									{
										xtype:'textfield',
										id:'contact',
										name:'oddEmployapply.contact',
										maxLength : 100,
										columnWidth:.39
									}
								]
							},{
								xtype: 'container',
								layout:'column',
								height:35,
								style:'margin-buttom:10px',
								defaults:{
									xtype:'label',
									style:{
										margin:'0px 0px 3px 0px'
									}
								},
								items:[
									{
										text:'用工时间：',
										columnWidth:.15
									},{
										xtype:'textfield',
										id:'employTime',
										name:'oddEmployapply.employTime',
										allowBlank:false,
										maxLength:64,
										columnWidth:.3
									},{
										text:'用工费用：',
										style:'margin-left:10px;',
										columnWidth:.16
									},
									{
										xtype:'textfield',
										id:'employFee',
										name:'oddEmployapply.employFee',
										maxLength : 10,
										columnWidth:.12,
										allowBlank:false,
										regex:/^(([1-9]\d{0,9})|0)(\.\d{1,2})?$/,
										enableKeyEvents :true,
										listeners:{
											keyup : function(field){
												  		var value = Ext.getCmp("employFee").getValue();
												  	    var employFee = AmountInWords(value);
												  	    Ext.getCmp("employFeeLabel").setText(employFee);
												  	    Ext.getCmp("employFeeBig").setValue(employFee);
													}
										}
									},{
										id:'employFeeLabel',
										columnWidth:.27,
										style:'color:red'
									},{
										xtype:'hidden',
										id:'employFeeBig',
										name:'employFeeBig'
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
											text : '用工原因：',
											columnWidth:0.15
										}, {
											xtype : 'textarea',
											id:'employReason',
											name:'oddEmployapply.employReason',
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
											text : '用工内容：',
											columnWidth:0.15
										}, {
											xtype : 'textarea',
											id:'employContent',
											name:'oddEmployapply.employContent',
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
											name : 'oddEmployapply.attachIds',
											id : 'attachIds'
										},{
											xtype : 'hidden',
											name : 'oddEmployapply.attachFiles',
											id : 'attachFiles'
										},{
											xtype : 'panel',
											id:'oddEmployapply.displayAttach',
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
																		file_cat : 'flow/oddEmployapply',
																		callback : function(data) {
																			Ext.getCmp('oddEmployapply.displayAttach').body.update('');
																			Ext.getCmp("attachFiles").setValue('');
																			Ext.getCmp('attachIds').setValue('');
																			var entrantFile = Ext.getCmp("attachFiles");
																			var fileIds = Ext.getCmp('attachIds');
																			var display = Ext.getCmp('oddEmployapply.displayAttach');
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
				url : __ctxPath + '/statistics/getOddEmployapply.do?id='+ this.id,
				waitMsg : '正在载入数据...',
				success : function(form, action) {
					var jsonData = Ext.util.JSON.decode(action.response.responseText);
					var res = jsonData.data;
					
					if(!Ext.isEmpty(res.contract)){
						if(!Ext.isEmpty(res.contract.project)){
							Ext.getCmp("oddEmployapply.proName").setValue(res.contract.project.proName);
							Ext.getCmp("oddEmployapply.proName").originalValue=res.contract.project.proName;
						}
						if(!Ext.isEmpty(res.contract.suppliersAssess)){
							Ext.getCmp("oddEmployapply.workUnit").setValue(res.contract.suppliersAssess.suppliersName);
							Ext.getCmp("oddEmployapply.workUnit").originalValue=res.contract.suppliersAssess.suppliersName;
						}
						Ext.getCmp("oddEmployapply.contract.contractNo").setValue(res.contract.contractNo);
						Ext.getCmp("oddEmployapply.contract.contractNo").originalValue=res.contract.contractNo;
						Ext.getCmp("oddEmployapply.contract.id").setValue(res.contract.id);
						Ext.getCmp("oddEmployapply.contract.id").originalValue=res.contract.id;
					}
					
					var attachId = res.attachIds;
					var attachFile = res.attachFiles;
					if(attachId!= null && attachId != 'undefined' && attachFile!= null && attachFile != 'undefined'){
							var ids = attachId.split(',');
							var files= attachFile.split(',');
							for(var i=0;i<ids.length;i++){
								if(files[i].lastIndexOf('<a href')!=-1){
									  Ext.DomHelper.append(Ext.getCmp('oddEmployapply.displayAttach').body,'<span><a href="#" onclick="FileAttachDetail.show('+ids[i]+')">'+files[i].replace(/\s+/g,"")+'</a>&nbsp;|&nbsp;</span>');
								}else if(files[i].lastIndexOf(':')!=-1){
									  var fg = files[i].split(':');
									  Ext.DomHelper.append(Ext.getCmp('oddEmployapply.displayAttach').body,'<span><a href="#" onclick="FileAttachDetail.show('+ids[i]+')">'+fg[1].replace(/\s+/g,"")+'</a>&nbsp;|&nbsp;</span>');
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
					var gridPanel=Ext.getCmp('OddEmployapplyGrid');
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
					Ext.getCmp("oddEmployapply.proName").setValue(proName);			
					Ext.getCmp("oddEmployapply.workUnit").setValue(suppliersName);			
					Ext.getCmp("oddEmployapply.contract.contractNo").setValue(contractNo);			
					Ext.getCmp("oddEmployapply.contract.id").setValue(id);		
					Ext.getCmp("oddEmployapply.projectChargerUserId").setValue(projectChargerId);
					Ext.getCmp("oddEmployapply.oddEmployapplyDepartmentId").setValue(departmentId);
			},true).show();
	}
});