/**
 * @author 
 * @createtime 
 * @class StaffEmployapplyForm
 * @extends Ext.Window
 * @description StaffEmployapply表单
 */
StaffEmployapplyForm=Ext.extend(Ext.Window,{
	//内嵌FormPanel
	formPanel:null,
	//构造函数
	constructor:function(_cfg){
		Ext.applyIf(this,_cfg);
		//必须先初始化组件
		this.initUIComponents();
		StaffEmployapplyForm.superclass.constructor.call(this,{
			id:'StaffEmployapplyFormWin',
			layout:'fit',
			items:this.formPanel,
			modal:true,
			width : 750,
			height : 550,
			maximizable:true,
			title:'[员工录用]详细信息',
			buttonAlign : 'center',
			buttons:this.buttons
		});
	},//end of the constructor
	//初始化组件
	initUIComponents:function(){
		var url = __ctxPath + "/system/listDepartment.do?opt=appUser";
		var depTreeSelector = new TreeSelector("staffEmployapply.depTreeSelector", url, "所属部门", "staffEmployapply.dept.depId",false,200,"depTreeSelector");
		Ext.getCmp("staffEmployapply.depTreeSelectorTree").on("click", function(g) {
			Ext.getCmp("staffEmployapply.jobName").setValue("");
			Ext.getCmp("staffEmployapply.jobName").getStore().removeAll();
			var deptId = Ext.getCmp("staffEmployapply.dept.depId").getValue();
			if (!Ext.isEmpty(deptId)) {
				Ext.getCmp("staffEmployapply.jobName").
					getStore().reload( {
								params : {
									depId : deptId
								}
							});
			}
		});
		this.formPanel=new Ext.FormPanel({
				layout : 'form',
				trackResetOnLoad:true,
				bodyStyle: 'padding:10px 10px 10px 10px;overflow-x:hidden;overflow-y:scroll',
				border:false,
				url : __ctxPath + '/statistics/saveStaffEmployapply.do',
				id : 'StaffEmployapplyForm',
				defaults : {
					anchor : '98%,98%'
				},
				defaultType : 'textfield',
				items : [{
							name : 'staffEmployapply.id',
							id : 'id',
							xtype:'hidden',
							value : this.id == null ? '' : this.id
						},{
							   xtype:'hidden',
							   name:'staffEmployapply.processRunId',
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
										text:'员工姓名：',
										width:97
									},
									{
										xtype:'textfield',
										id:'staffName',
										name:'staffEmployapply.staffName',
										maxLength : 64,
										allowBlank :false,
										width:100
									},
									{
										text:'部门：',
										style:'margin-left:10px;',
										width:50
									},
									{
										xtype : "hidden",
										name : "staffEmployapply.dept.depId",
										id : "staffEmployapply.dept.depId"
									},
									depTreeSelector,
									{
										text:'岗位：',
										style:'margin-left:10px;',
										width:50
									},
									{
										id:'staffEmployapply.jobName',
										hiddenName:'jobName',
										xtype : "combo",
										mode : "local",
										width:130,
										editable : false,
										valueField : "jobName",
										displayField : "jobName",
										triggerAction : "all",
										allowBlank:false,
										store : new Ext.data.SimpleStore( {
											url : __ctxPath
													+ "/hrm/comboJob.do",
											fields : [ "jobId", "jobName" ]
										}),
										listeners : {
											focus : function(h) {
												var deptId = Ext.getCmp("staffEmployapply.dept.depId").getValue();
												if (Ext.isEmpty(deptId)) {
													Ext.ux.Toast.msg("操作信息","请先选择部门！");
												}
											},
											select : function(combo,record,index){
												var jobId = record.data.jobId;
												var jobName = record.data.jobName;
												Ext.getCmp("staffEmployapply.job.jobId").setValue(jobId);
											}
										}
									},
									{
										xtype:'hidden',
										id:'staffEmployapply.job.jobId',
										name:'staffEmployapply.job.jobId'
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
										text:'分数：',
										width:97
									},
									{
										xtype:'textfield',
										id:'score',
										name:'staffEmployapply.score',
										maxLength : 11,
										allowBlank :false,
										width:150
									},
									{
										text:'体检情况：',
										style:'margin-left:10px;',
										width:60
									},
									{
										xtype:'textfield',
										id:'medicalCheck',
										name:'staffEmployapply.medicalCheck',
										maxLength : 512,
										allowBlank :false,
										width:335
									}
								]
							},{
										xtype:"container",
										style:"margin-top:10px;",
										width:'100%',
										height: 60,
										layout:"column",
										items:[
											{
												xtype:"label",
												text:"招聘渠道：",
												style:"margin-top:15px;"
											},
											{
												xtype:'container',
												width:'90%',
												layout:'hbox',
												height: 60,
												items:[
													{
														xtype:"radiogroup",
														name:'staffEmployapply.inviteWay',
														id:'inviteWay',
														defaults:{
															style:"margin-top:3px;"
														},
														width:'12%',
														columns: 1,
														items:[
															{
																name:'staffEmployapply.inviteWay',
																boxLabel:"公开招聘",
																inputValue:"公开招聘",
																checked:true
															},{
																name:'staffEmployapply.inviteWay',
																boxLabel:"推荐",
																inputValue:"推荐"
															}
														],
														listeners:{
															change:function(field,newValue,oldValue){
																var inviteWay = newValue.getRawValue();
																if(inviteWay=='公开招聘'){
																	Ext.getCmp("publicWay").enable();
																	Ext.getCmp("publicWay").allowBlank=false;
																	Ext.getCmp("inviteUserName").disable();
																	Ext.getCmp("inviteUserName").reset();
																	Ext.getCmp("staffEmployapply.inviteUser.userId").reset();
																}else if(inviteWay=='推荐'){
																	Ext.getCmp("inviteUserName").enable();
																	Ext.getCmp("inviteUserName").allowBlank=false;
																	Ext.getCmp("publicWay").disable();
																	Ext.getCmp("publicWay").reset();
																}
															}
														}
													}
													,{
														xtype:"container",
														width:'88%',
														layout:"vbox",
														height: 60,
														defaults:{
															style:"margin-top:3px;"
														},
														items:[
															{
																xtype:'container',
																width:'100%',
																height: 30,
																layout:'column',
																items:[
																	{
																		xtype:'label',
																		text:',途径:',
																		style:'margin-right:12px;'
																	},{
																		xtype:'textfield',
																		name:'staffEmployapply.publicWay',
																		id:'publicWay',
																		maxLength:64,
																		allowBlank:false,
																		width:'85%'
																	}
																]
															},
															{
																xtype:'container',
																width:'100%',
																layout:'column',
																height: 30,
																items:[
																	{
																		xtype:'label',
																		text:',推荐人:'
																	},{
																		xtype:'textfield',
																		name:'inviteUserName',
																		id:'inviteUserName',
																		emptyText:'请选择推荐人',
																		disabled:true,
																		width:'85%',
																		listeners:{
																		     focus:function(){
																		     	UserSelector.getView(function(ids, names) {
																						Ext.getCmp("inviteUserName").setValue(names);
																						Ext.getCmp("staffEmployapply.inviteUser.userId").setValue(ids);
																					}, true).show();
																		     }
																		}
																	},{
																		xtype:'hidden',
																		name:'staffEmployapply.inviteUser.userId',
																		id:'staffEmployapply.inviteUser.userId'
																	}
																]
															}
														]
													}
												]
											}
										]
									},{
										fieldLabel:'推荐理由',
										xtype:'textarea',
										name:'staffEmployapply.inviteReason',
										id:'inviteReason',
										allowBlank:false,
										anchor:'91%'
									},{
										xtype : 'panel',
										layout : 'column',
										defaultType : 'label',
										style:'padding-top:3px;margin-buttom:5px',
										height: 70,
										border:false,
										items : [{
													text : '推荐表:',
													width : 105
												},{
													xtype : 'hidden',
													name : 'staffEmployapply.attachIDs',
													id : 'attachIDs'
												},{
													xtype : 'hidden',
													name : 'staffEmployapply.attachFile',
													id : 'attachFile'
												},{
													xtype : 'panel',
													id:'staffEmployapply.displayAttach',
													width : 500,
													height: 60,
													frame:true,
													autoScroll:true,
													html : ''
												},{
													xtype : 'button',
													iconCls : 'btn-upload',
													text : '上传',
													handler : function() {
														var dialog = App.createUploadDialog({
																				file_cat : 'flow/staffEmployapply',
																				callback : function(data) {
																					Ext.getCmp('staffEmployapply.displayAttach').body.update('');
																					Ext.getCmp("attachFile").setValue('');
																					Ext.getCmp('attachIDs').setValue('');
																					var entrantFile = Ext.getCmp("attachFile");
																					var fileIds = Ext.getCmp('attachIDs');
																					var display = Ext.getCmp('staffEmployapply.displayAttach');
																					for (var i = 0; i < data.length; i++) {
																						if (fileIds.getValue() != '') {
																							fileIds.setValue(fileIds.getValue()+ ',');
																							entrantFile.setValue(entrantFile.getValue()+ ',');
																						}
																						entrantFile.setValue(entrantFile.getValue()+data[i].filepath+":"+data[i].filename.replace(/\s+/g,""));
//																						entrantFile.setValue(entrantFile.getValue()+"<a href=javascript:window.location.href=\""+__ctxPath+"/file-Download?filePath=attachFiles/"+data[i].filepath+"&fileName="+data[i].filename.replace(/\s+/g,"")+"\">"+data[i].filename.replace(/\s+/g,"")+"</a>");
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
										},{
											xtype:'panel',
											border:false,
											height:190,
										    layout:'column',
										    items:[
										    		{
										    			xtype:'panel',
										    			border:true,
										    			columnWidth:.6,
										    			items:[{
																xtype : 'panel',
																layout : 'column',
																defaultType : 'label',
																style:'padding-top:5px;margin-buttom:5px;padding-left:5px;',
																height: 30,
																border:false,
																items : [{
																			text:'专业知识:',
																			width : 105
																		},{
																			xtype:'radiogroup',
																			name:'staffEmployapply.professional',
																			id:'professional',
																			width:300,
																			items:[
																					{
																					  name:'staffEmployapply.professional',
																					  boxLabel:'优秀',
																					  inputValue:'优秀'
																					},{
																					  name:'staffEmployapply.professional',
																					  boxLabel:'良好',
																					  inputValue:'良好',
																					  checked:true
																					},{
																					  name:'staffEmployapply.professional',
																					  boxLabel:'一般',
																					  inputValue:'一般'
																					},{
																					  name:'staffEmployapply.professional',
																					  boxLabel:'不能接受',
																					  inputValue:'不能接受'
																					}
																				]
																		}
																		]
																},{
																xtype : 'panel',
																layout : 'column',
																defaultType : 'label',
																style:'padding-top:5px;margin-buttom:5px;padding-left:5px;',
																height: 30,
																border:false,
																items : [{
																			text:'工作经历:',
																			width : 105
																		},{
																			xtype:'radiogroup',
																			name:'staffEmployapply.experience',
																			id:'experience',
																			width:300,
																			items:[
																					{
																					  name:'staffEmployapply.experience',
																					  boxLabel:'优秀',
																					  inputValue:'优秀'
																					},{
																					  name:'staffEmployapply.experience',
																					  boxLabel:'良好',
																					  inputValue:'良好',
																					  checked:true
																					},{
																					  name:'staffEmployapply.experience',
																					  boxLabel:'一般',
																					  inputValue:'一般'
																					},{
																					  name:'staffEmployapply.experience',
																					  boxLabel:'不能接受',
																					  inputValue:'不能接受'
																					}
																				]
																		}
																		]
																},{
																xtype : 'panel',
																layout : 'column',
																defaultType : 'label',
																style:'padding-top:5px;margin-buttom:5px;padding-left:5px;',
																height: 30,
																border:false,
																items : [{
																			text:'自信度:',
																			width : 105
																		},{
																			xtype:'radiogroup',
																			name:'staffEmployapply.confident',
																			id:'confident',
																			width:300,
																			items:[
																					{
																					  name:'staffEmployapply.confident',
																					  boxLabel:'优秀',
																					  inputValue:'优秀'
																					},{
																					  name:'staffEmployapply.confident',
																					  boxLabel:'良好',
																					  inputValue:'良好',
																					  checked:true
																					},{
																					  name:'staffEmployapply.confident',
																					  boxLabel:'一般',
																					  inputValue:'一般'
																					},{
																					  name:'staffEmployapply.confident',
																					  boxLabel:'不能接受',
																					  inputValue:'不能接受'
																					}
																				]
																		}
																		]
																},{
																xtype : 'panel',
																layout : 'column',
																defaultType : 'label',
																style:'padding-top:5px;margin-buttom:5px;padding-left:5px;',
																height: 30,
																border:false,
																items : [{
																			text:'思维能力:',
																			width : 105
																		},{
																			xtype:'radiogroup',
																			name:'staffEmployapply.thinkability',
																			id:'thinkability',
																			width:300,
																			items:[
																					{
																					  name:'staffEmployapply.thinkability',
																					  boxLabel:'优秀',
																					  inputValue:'优秀'
																					},{
																					  name:'staffEmployapply.thinkability',
																					  boxLabel:'良好',
																					  inputValue:'良好',
																					  checked:true
																					},{
																					  name:'staffEmployapply.thinkability',
																					  boxLabel:'一般',
																					  inputValue:'一般'
																					},{
																					  name:'staffEmployapply.thinkability',
																					  boxLabel:'不能接受',
																					  inputValue:'不能接受'
																					}
																				]
																		}
																	]
																},{
																xtype : 'panel',
																layout : 'column',
																defaultType : 'label',
																style:'padding-top:5px;margin-buttom:5px;padding-left:5px;',
																height: 30,
																border:false,
																items : [{
																			text:'表达能力:',
																			width : 105
																		},{
																			xtype:'radiogroup',
																			name:'staffEmployapply.expressability',
																			id:'expressability',
																			width:300,
																			items:[
																					{
																					  name:'staffEmployapply.expressability',
																					  boxLabel:'优秀',
																					  inputValue:'优秀'
																					},{
																					  name:'staffEmployapply.expressability',
																					  boxLabel:'良好',
																					  inputValue:'良好',
																					  checked:true
																					},{
																					  name:'staffEmployapply.expressability',
																					  boxLabel:'一般',
																					  inputValue:'一般'
																					},{
																					  name:'staffEmployapply.expressability',
																					  boxLabel:'不能接受',
																					  inputValue:'不能接受'
																					}
																				]
																		}
																		]
																},{
																xtype : 'panel',
																layout : 'column',
																defaultType : 'label',
																style:'padding-top:5px;margin-buttom:5px;padding-left:5px;',
																height: 30,
																border:false,
																items : [{
																			text:'仪表:',
																			width : 105
																		},{
																			xtype:'radiogroup',
																			name:'staffEmployapply.looks',
																			id:'looks',
																			width:300,
																			items:[
																					{
																					  name:'staffEmployapply.looks',
																					  boxLabel:'优秀',
																					  inputValue:'优秀'
																					},{
																					  name:'staffEmployapply.looks',
																					  boxLabel:'良好',
																					  inputValue:'良好',
																					  checked:true
																					},{
																					  name:'staffEmployapply.looks',
																					  boxLabel:'一般',
																					  inputValue:'一般'
																					},{
																					  name:'staffEmployapply.looks',
																					  boxLabel:'不能接受',
																					  inputValue:'不能接受'
																					}
																				]
																		}
																		]
																}]
										    		},
										    		{
										    			xtype:'panel',
										    			border:true,
										    			columnWidth:.35,
										    			height:182,
										    			style:'margin-buttom:5px;',
										    			defaults:{
										    				style:'padding-top:5px;margin-buttom:5px;padding-left:5px;'
										    			},
										    			items:[
										    				{
										    					xtype:'label',
										    					text:'同意新进：'
										    				},
										    				{
																xtype:'container',
																width:'90%',
																layout:'hbox',
																height: 150,
																items:[
																	{
																		xtype:"radiogroup",
																		name:'staffEmployapply.agreeEnterType',
										    							id:'agreeEnterType',
																		defaults:{
																			style:"margin-top:3px;"
																		},
																		width:'20%',
																		columns: 1,
																		height: 100,
																		items:[
																			{
																				name:'staffEmployapply.agreeEnterType',
										    							  		boxLabel:'A:',
										    							  		inputValue:'A',
										    							  		height: 60,
										    							  		style:'margin-top:20px;',
																				checked:true
																			},{
																				name:'staffEmployapply.agreeEnterType',
																				height: 60,
										    							  		boxLabel:'B:',
										    							  		inputValue:'B',
										    							  		style:'margin-top:20px;'
																			}
																		],
																		listeners:{
														    				change:function(field,newValue,oldValue){
														    				       var employType = newValue.getRawValue();
														    				       if(employType=='A'){
														    				       		Ext.getCmp("inspect").enable();
														    				       		Ext.getCmp("inspectSalary").enable();
														    				       		Ext.getCmp("probation").disable();
														    				       		Ext.getCmp("probationSalary").disable();
														    				       		Ext.getCmp("probation").allowBlank=false;
														    				       		Ext.getCmp("probationSalary").allowBlank=false;
														    				       		Ext.getCmp("probation").reset();
														    				       		Ext.getCmp("probationSalary").reset();
														    				       }else if(employType=='B'){
														    				       		Ext.getCmp("probation").enable();
														    				       		Ext.getCmp("probationSalary").enable();
														    				       		Ext.getCmp("inspect").disable();
														    				       		Ext.getCmp("inspectSalary").disable();
														    				       		Ext.getCmp("inspect").allowBlank=false;
														    				       		Ext.getCmp("inspectSalary").allowBlank=false;
														    				       		Ext.getCmp("inspect").reset();
														    				       		Ext.getCmp("inspectSalary").reset();
														    				       }
														    				}
														    			}
																	},{
																		xtype:"container",
																		width:'88%',
																		layout:"vbox",
																		height: 150,
																		defaults:{
																			style:"margin-top:3px;"
																		},
																		items:[
																			{
																				xtype:'container',
																				width:'100%',
																				height: 30,
																				layout:'column',
																				items:[
																					{
												    							  		xtype:'label',
												    							  		text:'似定考察期:'
												    							  	},{
												    							  		xtype:'numberfield',
												    							  		maxLength:3,
												    							  		name:'staffEmployapply.inspect',
												    							  		id:'inspect',
												    							  		allowBlank:false,
												    							  		width:40
												    							  	},{
												    							  		xtype:'label',
												    							  		text:'月'
												    							  	}
																				]
																			},{
										    							  		xtype:'container',
										    							  		layout:'column',
										    							  		width:'100%',
										    							  		height: 30,
										    							  		items:[
										    							  			{
												    							  		xtype:'label',
												    							  		text:'考察期月薪:'
												    							  	},{
												    							  		xtype:'numberfield',
												    							  		maxLength:10,
												    							  		columnWidth:.7,
												    							  		allowBlank:false,
												    							  		name:'staffEmployapply.inspectSalary',
												    							  		id:'inspectSalary'
												    							  	},{
												    							  		xtype:'label',
												    							  		text:'元/月'
												    							  	}
										    							  		]
										    							  	},{
										    							  		xtype:'container',
										    							  		layout:'column',
										    							  		width:'100%',
										    							  		items:[
										    							  			{
												    							  		xtype:'label',
												    							  		text:'似定试用期:'
												    							  	},{
												    							  		xtype:'numberfield',
												    							  		maxLength:3,
												    							  		name:'staffEmployapply.probation',
												    							  		id:'probation',
												    							  		disabled:true,
												    							  		width:40
												    							  	},{
												    							  		xtype:'label',
												    							  		text:'月'
												    							  	}
										    							  		]
										    							  	},
										    							  	{
										    							  		xtype:'container',
										    							  		layout:'column',
										    							  		width:'100%',
										    							  		style:'margin-top:10px;',
										    							  		items:[
										    							  			{
												    							  		xtype:'label',
												    							  		text:'试用期月薪:'
												    							  	},{
												    							  		xtype:'numberfield',
												    							  		maxLength:10,
												    							  		columnWidth:.7,
												    							  		disabled:true,
												    							  		name:'staffEmployapply.probationSalary',
												    							  		id:'probationSalary'
												    							  	},{
												    							  		xtype:'label',
												    							  		text:'元/月'
												    							  	}
										    							  		]
										    							  	}
																		]
																	}
																]
										    				}
												    			
										    			]
										    		}
										    	]
										}
							]
			});
		//加载表单对应的数据	
		if (this.id != null && this.id != 'undefined') {
			this.formPanel.getForm().load({
				deferredRender : false,
				url : __ctxPath + '/statistics/getStaffEmployapply.do?id='+ this.id,
				waitMsg : '正在载入数据...',
				success : function(form, action) {
					var jsonData = Ext.util.JSON.decode(action.response.responseText);
					var res = jsonData.data;
					if(!Ext.isEmpty(res.dept)){
						Ext.getCmp("staffEmployapply.depTreeSelector").setValue(res.dept.depName);
						Ext.getCmp("staffEmployapply.depTreeSelector").originalValue=res.dept.depName;
						Ext.getCmp("staffEmployapply.dept.depId").setValue(res.dept.depId);
						Ext.getCmp("staffEmployapply.dept.depId").originalValue=res.dept.depId;
						Ext.getCmp("staffEmployapply.jobName").
							getStore().reload({
										params : {
											depId : res.dept.depId
										}
									});
					}
					if(!Ext.isEmpty(res.job)){
						Ext.getCmp("staffEmployapply.jobName").setValue(res.job.jobName);
						Ext.getCmp("staffEmployapply.jobName").originalValue=res.job.jobName;
						Ext.getCmp("staffEmployapply.job.jobId").setValue(res.job.jobId);
						Ext.getCmp("staffEmployapply.job.jobId").originalValue=res.job.jobId;
					}
					if(!Ext.isEmpty(res.inviteUser)){
						Ext.getCmp("inviteUserName").enable();
						Ext.getCmp("inviteUserName").setValue(res.inviteUser.fullname);
						Ext.getCmp("inviteUserName").originalValue=res.inviteUser.fullname;
						Ext.getCmp("staffEmployapply.inviteUser.userId").setValue(res.inviteUser.userId);
						Ext.getCmp("staffEmployapply.inviteUser.userId").originalValue=res.inviteUser.userId;
					}
					Ext.getCmp("inviteWay").originalValue=res.inviteWay;
					Ext.getCmp("professional").originalValue=res.professional;
					Ext.getCmp("experience").originalValue=res.experience;
					Ext.getCmp("confident").originalValue=res.confident;
					Ext.getCmp("thinkability").originalValue=res.thinkability;
					Ext.getCmp("expressability").originalValue=res.expressability;
					Ext.getCmp("looks").originalValue=res.looks;
					Ext.getCmp("agreeEnterType").originalValue=res.agreeEnterType;
					var attachId = res.attachIDs;
					var attachFile = res.attachFile;
					if(attachId!= null && attachId != 'undefined' && attachFile!= null && attachFile != 'undefined'){
							var ids = attachId.split(',');
							var files= attachFile.split(',');
							for(var i=0;i<ids.length;i++){
								if(files[i].lastIndexOf('<a href')!=-1){
									  Ext.DomHelper.append(Ext.getCmp('staffEmployapply.displayAttach').body,'<span><a href="#" onclick="FileAttachDetail.show('+ids[i]+')">'+files[i].replace(/\s+/g,"")+'</a>&nbsp;|&nbsp;</span>');
								}else if(files[i].lastIndexOf(':')!=-1){
									  var fg = files[i].split(':');
									  Ext.DomHelper.append(Ext.getCmp('staffEmployapply.displayAttach').body,'<span><a href="#" onclick="FileAttachDetail.show('+ids[i]+')">'+fg[1].replace(/\s+/g,"")+'</a>&nbsp;|&nbsp;</span>');
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
					var gridPanel=Ext.getCmp('StaffEmployapplyGrid');
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