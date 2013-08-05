/**
 * @author 
 * @createtime 
 * @class PaymentListForm
 * @extends Ext.Window
 * @description PaymentList表单
 */
PaymentListForm=Ext.extend(Ext.Window,{
	//内嵌FormPanel
	formPanel:null,
	//构造函数
	constructor:function(_cfg){
		Ext.applyIf(this,_cfg);
		//必须先初始化组件
		this.initUIComponents();
		PaymentListForm.superclass.constructor.call(this,{
			id:'PaymentListFormWin',
			layout:'fit',
			items:this.formPanel,
			modal:true,
			width : 750,
			height : 400,
			maximizable:true,
			title:'暂支单详细信息',
			buttonAlign : 'center',
			buttons:this.buttons
		});
	},//end of the constructor
	//初始化组件
	initUIComponents:function(){
		this.formPanel=new Ext.FormPanel({
				layout : 'form',
				autoScroll : true,
			    bodyStyle: 'padding:10px 10px 10px 10px;overflow-x:hidden;overflow-y:scroll',
				border:false,
				trackResetOnLoad:true,
				labelWidth : 30,
				url : __ctxPath + '/statistics/savePaymentList.do',
				id : 'PaymentListForm',
				defaults : {
					anchor : '98%,98%'
				},
				defaultType : 'textfield',
				items : [{
					xtype : "container",
					style : "padding-left:0px;margin-bottom:10px;",
					layout : "column",
					//			hidden:true,
					id : 'proContainer',
					items : [{
								xtype : 'label',
								text : '项目名称：'
							}, {
								xtype : 'hidden',
								name : 'paymentList.id',
								value:this.id
							},{
								xtype:'hidden',
								name:'paymentList.processRunId',
								value:-1
							}, {
								xtype : 'hidden',
								name : 'paymentList.project.id',
								id : 'paymentList.project.id'
							}, {
								xtype : 'hidden',
								name : 'paymentList.user.userId',
								id : 'paymentList.user.userId'
							}, {
								xtype : 'textfield',
							    allowBlank : false,
								id : 'project.proName',
								name : 'proName',
								emptyText : '请选择项目',
								width : 250,
								listeners : {
									'focus' : function() {
										ProjectNewSelector.getView(
												function(proId, proName, proNo,contractId, contractNo,contractAmount) {
													Ext.getCmp("project.proName").setValue(proName);
													Ext.getCmp("project.proNo").setValue(proNo);
													Ext.getCmp("paymentList.project.id").setValue(proId);
													var url = __ctxPath + '/statistics/getOwedSumPaymentList.do';
													var params = "proId="+proId+"&userId="+__currentUserId;
													var owedSum = ajaxSyncCall(url,params).owedSum;
													var owedSumBig = AmountInWords(owedSum);
												    Ext.getCmp("owedSum").setValue(owedSum);
												    Ext.getCmp("owedSumBig").setText("("+ owedSumBig+ ")");
												}, true).show();
									}
								}
							}, {
								xtype : 'label',
								text : '项目编号：',
								style : 'margin-left:20px;padding-right:12px;'
							}, {
								xtype : 'textfield',
								allowBlank : false,
								id : 'project.proNo',
								emptyText : '请选择项目',
								name : 'proNo',
								width : 250,
								listeners : {
									'focus' : function() {
										ProjectNewSelector.getView(
												function(proId, proName, proNo,contractId, contractNo,contractAmount) {
										                        Ext.getCmp("project.proName").setValue(proName);
																Ext.getCmp("project.proNo").setValue(proNo);
																Ext.getCmp("paymentList.project.id").setValue(proId);
																var url = __ctxPath + '/statistics/getOwedSumPaymentList.do';
																var params = "proId="+proId+"&userId="+__currentUserId;
																var owedSum = ajaxSyncCall(url,params).owedSum;
																var owedSumBig = AmountInWords(owedSum);
															    Ext.getCmp("owedSum").setValue(owedSum);
															    Ext.getCmp("owedSumBig").setText("("+ owedSumBig+ ")");
												}, true).show();
									}
								}
							}
					]
		},{
		    xtype : 'panel',
			style:'margin-bottom:10px',
			border : false,
			layout : 'column',
		    items:[{
		           xtype:'label',
		           text:'类别选择：'
		        },
		        {
		        	xtype : "combo",
					hiddenName : "paymentList.payOption",
					id:'payOption',
					allowBlank : false,
					mode : 'local',
					store : ['工程', '其他'],
					value:'工程',
					triggerAction : 'all',
					width : 125,
					editable : false,
		        	listeners:{
		        		select : function(combo,record,index){
		        			if(combo.getValue()=='工程'){
								Ext.getCmp("project.proName").enable();
								Ext.getCmp("project.proNo").enable();
								Ext.getCmp("owedSum").enable();
								Ext.getCmp("proContainer").show();
								var data = ['备用金', '税金'];
							    Ext.getCmp("paymentType").getStore().loadData(data);
							    Ext.getCmp("owedSum").setValue('');
								Ext.getCmp("owedSumBig").setText('');
		        			}else{
		        				Ext.getCmp("proContainer").hide();
		        				Ext.getCmp("project.proName").reset();
		        				Ext.getCmp("project.proNo").reset();
		        				Ext.getCmp("owedSum").reset();
								Ext.getCmp("project.proName").disable();
								Ext.getCmp("project.proNo").disable();
								Ext.getCmp("owedSum").disable();
								var data = ['备用金', '税金', '个人借款'];
								Ext.getCmp("paymentType").getStore().loadData(data);
								var url = __ctxPath + '/statistics/getOwedSumPaymentList.do';
								var params = "userId="+__currentUserId;
								var owedSum = ajaxSyncCall(url,params).owedSum;
								var owedSumBig = AmountInWords(owedSum);
							    Ext.getCmp("owedSum").setValue(owedSum);
							    Ext.getCmp("owedSumBig").setText("("+ owedSumBig+ ")");
		        			}
		        		}
		        		
		        	}
		        	
		        },{
						xtype : 'label',
						style:'margin-left:20px;',
						text : '暂支类别：'
					}, {
						xtype : "combo",
						hiddenName : "paymentList.paymentType",
						id : "paymentType",
						allowBlank : false,
						mode : 'local',
						store : ['备用金', '税金'],
						triggerAction : 'all',
						width : 125,
						editable : false
					}, {
						xtype : 'label',
						text : '目前欠款金额：',
						style : 'padding-right:3px;margin-left:20px;'
					}, {
						xtype : 'numberfield',
						name : 'paymentList.owedSum',
						id:'owedSum',
						allowBlank:false,
						emptyText : '系统自动生成',
						style : "background:#F0F0F0;",
						readOnly : true,
						width : 130
					}, {
						xtype : 'label',
						text : '元',
						width : 15
					}, {
						xtype : 'label',
						style : 'color:red',
						id : 'owedSumBig'
					}
		        ]}, {
						xtype : 'panel',
						border : false,
						layout : 'column',
						height : 60,
						items : [{
									xtype : 'label',
									text : '本次借款金额：',
									width : 90
								}, {
									xtype : 'container',
									width : 350,
									style : 'padding-left:3px;',
									defaults : {
										xtype : 'textfield'
									},
									layout : 'form',
									items : [{
										xtype : 'container',
										width : 300,
										layout : 'column',
										items : [{
													xtype : 'label',
													width : 30,
													text : '小写:',
													style : 'margin-right:5px;'
												}, {
													xtype : 'numberfield',
													regex : /^(([1-9]\d{0,9})|0)(\.\d{1,2})?$/,
													name : 'paymentList.paymentSumSmall',
													id : 'paymentSumSmall',
													maxLength : 12,
													width : 110,
													enableKeyEvents : true,
													listeners : {
																	keyup : function(field) {
																		var value = Ext.getCmp("paymentSumSmall").getValue();
																		var paymentSumBig = AmountInWords(value);
																		Ext.getCmp("paymentSumBig").setValue(paymentSumBig);
																}
													}
												}, {
													xtype : 'label',
													text : '元'
												}, {
													xtype : 'label',
													width : 140,
													text : '(填写此项,大写自动补全)',
													style : 'color:red'
												}]
									}, {
										fieldLabel : '大写',
										name : 'paymentList.paymentSumBig',
										labelStyle : 'width:30px;',
										style : 'margin-top:5px;',
										readOnly : true,
										id : 'paymentSumBig',
										width : 300
									}]
								},{
										xtype : 'label',
										text : '预计归还时间：',
										width:90
									},{
										xtype : 'datefield',
										editable:false,
										format:'Y-m-d',
										name:'paymentList.preNowReturnTime',
										id:'preNowReturnTime'
								}
								]
					}, {
						xtype : 'container',
						layout : 'column',
						style : 'padding-left:0px;margin-bottom:4px',
						border : false,
						items : [{
									xtype : 'label',
									text : '暂支原因:'
								}, {
									xtype : 'textarea',
									name : 'paymentList.paymentCause',
									allowBlank : false,
									maxLength : 200,
									id : 'paymentCause',
									height : 115,
									width : 586
								}]
			
					},{
						xtype : 'container',
						autoHeight : true,
						layout : 'column',
						autoWidth : true,
						defaultType : 'label',
						style : 'padding-left:0px;padding-bottom:5px;',
						items : [{
									text : '附件:',
									width : 43,
									style : "margin-top:15px;"
								},{
									xtype : 'hidden',
									name : 'paymentList.attachIds',
									id : 'paymentList.attachIds'
								},{
									xtype : 'hidden',
									name : 'paymentList.attachFiles',
									id : 'paymentList.attachFiles'
								},{
									xtype : 'panel',
									id:'paymentList.displayAttach',
									width : 550,
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
																file_cat : 'flow/payment',
																callback : function(data) {
																	Ext.getCmp('paymentList.displayAttach').body.update('');
																	Ext.getCmp("paymentList.attachFiles").setValue('');
																	Ext.getCmp('paymentList.attachIds').setValue('');
																	var contractFile = Ext.getCmp("paymentList.attachFiles");
																	var fileIds = Ext.getCmp('paymentList.attachIds');
																	var display = Ext.getCmp('paymentList.displayAttach');
																	for (var i = 0; i < data.length; i++) {
																		if (fileIds.getValue() != '') {
																			fileIds.setValue(fileIds.getValue()+ ',');
																			contractFile.setValue(contractFile.getValue()+ ',');
																		}
																		contractFile.setValue(contractFile.getValue()+data[i].filepath+":"+data[i].filename.replace(/\s+/g,""));
//																		contractFile.setValue(contractFile.getValue()+"<a href=javascript:window.location.href=\""+__ctxPath+"/file-Download?filePath=attachFiles/"+data[i].filepath+"&fileName="+data[i].filename.replace(/\s+/g,"")+"\">"+data[i].filename.replace(/\s+/g,"")+"</a>");
																		fileIds.setValue(fileIds.getValue()+data[i].fileId);
																		Ext.DomHelper.append(display.body,'<span><a href="#" onclick="FileAttachDetail.show('+data[i].fileId+')">'+data[i].filename.replace(/\s+/g,"")+'</a>&nbsp;|&nbsp;</span>');
																	}
																},
																permitted_max_size :1024*1024*20
															});
											dialog.show(this);
									}
								}]
					},
					{
						xtype : 'panel',
						border : false,
						layout : 'column',
						items : [{
									xtype : 'label',
									text : '部门：',
									width : 53
								}, {
									xtype : 'textfield',
									name : 'paymentList.deptName',
									id:'deptName',
									fieldLabel : '部门',
									allowBlank : false,
									style : "background:#F0F0F0;",
									value : __currentUserDept,
									readOnly : true,
									width : 594
								}]
					}, {
						xtype : 'panel',
						border : false,
						style:'padding-top:10px',
						layout : 'column',
						items : [{
									xtype : 'label',
									text : '借款人：',
									width : 53
								}, {
									xtype : 'textfield',
									name : 'paymentList.borrower',
									id:'borrower',
									fieldLabel : '借款人',
									allowBlank : false,
									style : "background:#F0F0F0;",
									value : __currentUser,
									readOnly : true,
									width : 594
								}]
					}
		]
			});
		//加载表单对应的数据	
		if (this.id != null && this.id != 'undefined') {
			this.formPanel.getForm().load({
				deferredRender : false,
				url : __ctxPath + '/statistics/getPaymentList.do?id='+ this.id,
				waitMsg : '正在载入数据...',
				success : function(form, action) {
					var jsonData = Ext.util.JSON.decode(action.response.responseText);
					var res = jsonData.data;
					if(!Ext.isEmpty(res.project)){
						var project = res.project;
						Ext.getCmp("project.proName").setValue(project.proName);
						Ext.getCmp("project.proName").originalValue = project.proName;
						Ext.getCmp("project.proNo").setValue(project.proNo);
						Ext.getCmp("project.proNo").originalValue = project.proNo;
						Ext.getCmp("paymentList.project.id").setValue(project.id);
						Ext.getCmp("paymentList.project.id").originalValue = project.id;
					}else{
						Ext.getCmp("project.proName").allowBlank=true;
						Ext.getCmp("project.proNo").allowBlank=true;
					}
					if(!Ext.isEmpty(res.user)){
						var user = res.user;
						Ext.getCmp("paymentList.user.userId").setValue(user.userId);
						Ext.getCmp("paymentList.user.userId").originalValue = user.userId;
					}
					var attachId = res.attachIds;
					var attachFile = res.attachFiles;
					if(attachId!= null && attachId != 'undefined' && attachFile!= null && attachFile != 'undefined'){
						Ext.getCmp("paymentList.attachIds").setValue(res.attachIds);
						Ext.getCmp("paymentList.attachIds").originalValue = res.attachIds;
						Ext.getCmp("paymentList.attachFiles").setValue(res.attachFiles);
						Ext.getCmp("paymentList.attachFiles").originalValue = res.attachFiles;
						var ids = attachId.split(',');
						var files= attachFile.split(',');
						for(var i=0;i<ids.length;i++){
							if(files[i].lastIndexOf('<a href')!=-1){
								  Ext.DomHelper.append(Ext.getCmp('paymentList.displayAttach').body,'<span><a href="#" onclick="FileAttachDetail.show('+ids[i]+')">'+files[i].replace(/\s+/g,"")+'</a>&nbsp;|&nbsp;</span>');
							}else if(files[i].lastIndexOf(':')!=-1){
								  var fg = files[i].split(':');
								  Ext.DomHelper.append(Ext.getCmp('paymentList.displayAttach').body,'<span><a href="#" onclick="FileAttachDetail.show('+ids[i]+')">'+fg[1].replace(/\s+/g,"")+'</a>&nbsp;|&nbsp;</span>');
							}
						}
					}
				},
				failure : function(form, action) {
				}
			});
		}
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
					var gridPanel=Ext.getCmp('PaymentListGrid');
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