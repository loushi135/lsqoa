/**
 * @author 
 * @createtime 
 * @class StaffPromoteapplyForm
 * @extends Ext.Window
 * @description StaffPromoteapply表单
 */
StaffPromoteapplyForm=Ext.extend(Ext.Window,{
	//内嵌FormPanel
	formPanel:null,
	//构造函数
	constructor:function(_cfg){
		Ext.applyIf(this,_cfg);
		//必须先初始化组件
		this.initUIComponents();
		StaffPromoteapplyForm.superclass.constructor.call(this,{
			id:'StaffPromoteapplyFormWin',
			layout:'fit',
			items:this.formPanel,
			modal:true,
			width : 730,
			height : 450,
			maximizable:true,
			title:'[员工晋升]详细信息',
			buttonAlign : 'center',
			buttons:this.buttons
		});
	},//end of the constructor
	//初始化组件
	initUIComponents:function(){
		var url = __ctxPath + "/system/listDepartment.do?opt=appUser";
		var newDepTreeSelector = new TreeSelector("staffPromoteapply.newDepTreeSelector", url, "所属部门", "staffPromoteapply.newDept.depId",false,165,"newDepTreeSelector");
				Ext.getCmp("staffPromoteapply.newDepTreeSelectorTree").on("click", function(g) {
					Ext.getCmp("staffPromoteapply.newJobName").setValue("");
					Ext.getCmp("staffPromoteapply.newJobName").getStore().removeAll();
					var depId = Ext.getCmp("staffPromoteapply.newDept.depId").getValue();
					if (!Ext.isEmpty(depId)) {
						Ext.getCmp("staffPromoteapply.newJobName").
							getStore().reload({
										params : {
											depId : depId
										}
									});
					}
				});
		this.formPanel=new Ext.FormPanel({
				layout : 'form',
				trackResetOnLoad:true,
				bodyStyle: 'padding:10px 10px 10px 10px',
				border:false,
				url : __ctxPath + '/statistics/saveStaffPromoteapply.do',
				id : 'StaffPromoteapplyForm',
				defaults : {
					anchor : '98%,98%'
				},
				defaultType : 'textfield',
				items : [{
							name : 'staffPromoteapply.id',
							id : 'id',
							xtype:'hidden',
							value : this.id == null ? '' : this.id
						},{
						   xtype:'hidden',
						   name:'staffPromoteapply.processRunId',
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
										width:80
									},
									{
										xtype:'textfield',
										name:'promoteUserName',
										id:'promoteUserName',
										readOnly:true,
										allowBlank:false,
										emptyText:'请选择员工',
										width:170,
										listeners:{
											focus:function(){
												UserSelector.getView(function(ids, names) {
														Ext.getCmp("promoteUserName").setValue(names);
														Ext.getCmp("staffPromoteapply.promoteUser.userId").setValue(ids);
														Ext.getCmp("staffPromoteapply.oldJobName").setValue('');
														Ext.getCmp("staffPromoteapply.oldDept.depId").setValue('');
														Ext.getCmp("staffPromoteapply.oldDepTreeSelector").setValue('');
														if(!Ext.isEmpty(ids)){
															Ext.Ajax.request({
																url : __ctxPath
																		+ "/system/getAppUser.do",
																params : {
																	userId : ids
																},
																method : "post",
																success : function(response) {
																	var e = Ext.util.JSON
																			.decode(response.responseText).data[0];
																	Ext.getCmp("staffPromoteapply.oldJobName").setValue(e.position);
																	Ext.getCmp("staffPromoteapply.oldDept.depId").setValue(e.department.depId);
																	Ext.getCmp("staffPromoteapply.oldDepTreeSelector").setValue(e.department.depName);
																}
															});
														}
													}, true).show();
											}
										}
									},
									{
										xtype:'hidden',
										name:'staffPromoteapply.promoteUser.userId',
										id:'staffPromoteapply.promoteUser.userId'
									},
									{
										text:'原部门：',
										style:'margin-left:10px;',
										width:80
									},
									{
										xtype : "hidden",
										name : "staffPromoteapply.oldDept.depId",
										id : "staffPromoteapply.oldDept.depId"
									},
									{
										xtype:'textfield',
										name:'oldDepTreeSelector',
										id:'staffPromoteapply.oldDepTreeSelector',
										readOnly:true,
										style:"background:#F0F0F0;",
										width:160
									},
									{
										text:'原职位：',
										style:'margin-left:10px;',
										width:60
									},{
										xtype:'textfield',
										name:'oldJobName',
										id:'staffPromoteapply.oldJobName',
										readOnly:true,
										style:"background:#F0F0F0;",
										width:110
									},
									{
										xtype : "hidden",
										name : "staffPromoteapply.oldJob.jobId",
										id : "staffPromoteapply.oldJob.jobId"
									}
								]
							},{
								xtype: 'container',
								layout:'column',
								height:25,
								style:'margin-top:10px;',
								defaults:{
									xtype:'label',
									style:{
										margin:'0px 0px 3px 0px'
									}
								},
								items:[
									{
										text:'拟晋升部门：',
										width:80
									},
									{
										xtype : "hidden",
										name : "staffPromoteapply.newDept.depId",
										id : "staffPromoteapply.newDept.depId"
									},
									newDepTreeSelector,
									{
										text:'拟晋升职位：',
										style:'margin-left:10px;',
										width:80
									},
									{
										hiddenName:'newJobName',
										id:'staffPromoteapply.newJobName',
										xtype : "combo",
										mode : "local",
										width:150,
										editable : false,
										allowBlank:false,
										valueField : "jobName",
										displayField : "jobName",
										triggerAction : "all",
										store : new Ext.data.SimpleStore( {
											url : __ctxPath
													+ "/hrm/comboJob.do",
											fields : [ "jobId", "jobName" ]
										}),
										listeners : {
											focus : function(h) {
												var depId = Ext.getCmp("staffPromoteapply.newDept.depId").getValue();
												if (Ext.isEmpty(depId)) {
													Ext.ux.Toast.msg("操作信息","请先选择新部门！");
												}
											},
											select : function(combo,record,index){
												var jobId = record.data.jobId;
												var jobName = record.data.jobName;
												Ext.getCmp("staffPromoteapply.newJob.jobId").setValue(jobId);
											}
										}
									},
									{
										xtype:'hidden',
										id:'staffPromoteapply.newJob.jobId',
										name:'staffPromoteapply.newJob.jobId'
									},{
										text:'生效日期：',
										style:'margin-left:10px;',
										width:70
									},{
										xtype:'datefield',
										name:'staffPromoteapply.activeDate',
										id:'activeDate',
										format:'Y-m-d',
										editable:false,
										allowBlank:false
									}
								]
							}, {
								xtype : "panel",
								layout : "column",
								style:'padding-top:5px;',
								border : false,
								unstyled : false,
								height : 80,
								items : [{
											xtype : "label",
											text : "晋升理由:",
											height : 50,
											width : 80
										}, {
											xtype : "textarea",
											name:'staffPromoteapply.promoteReason',
											id:'promoteReason',
											width: 580,
											height:70,
											allowBlank:false
										}]
							}, {
								xtype : "panel",
								layout : "column",
								style:'padding-top:5px;',
								border : false,
								unstyled : false,
								height : 80,
								items : [{
											xtype : "label",
											text : "个人优点和需改进的方面:",
											height : 50,
											width : 80
										}, {
											xtype : "textarea",
											name:'staffPromoteapply.advantageOrChange',
											id:'advantageOrChange',
											width: 580,
											height:70,
											allowBlank:false
										}]
							}, {
								xtype : "panel",
								layout : "column",
								style:'padding-top:5px;',
								border : false,
								unstyled : false,
								height : 80,
								items : [{
											xtype : "label",
											text : "综合评价:",
											height : 50,
											columnWidth : .12
										},{
											xtype:'panel',
											border:false,
											columnWidth:.85,
											height:75,
											layout:'hbox',
											items:[
												{
													xtype:'panel',
													width:'50%',
													layout:'vbox',
													defaults:{
														style:'margin:5px;'
													},
													height:75,
													items:[
														{
															xtype:'label',
															text:'专业能力',
															height:30
														},
														{
															xtype:'radiogroup',
															name:'staffPromoteapply.professional',
															id:'professional',
															allowBlank:false,
															items:[
																{
																	name:'staffPromoteapply.professional',
																	boxLabel:'A',
																	inputValue:'A'
																},{
																	name:'staffPromoteapply.professional',
																	boxLabel:'B',
																	inputValue:'B'
																},{
																	name:'staffPromoteapply.professional',
																	boxLabel:'C',
																	inputValue:'C'
																}
															]
														}
													]
												},
												{
													xtype:'panel',
													width:'50%',
													layout:'vbox',
													defaults:{
														style:'margin:5px;'
													},
													height:75,
													items:[
														{
															xtype:'label',
															text:'综合能力',
															height:30
														},
														{
															xtype:'radiogroup',
															name:'staffPromoteapply.mixedAbility',
															id:'mixedAbility',
															allowBlank:false,
															items:[
																{
																	name:'staffPromoteapply.mixedAbility',
																	boxLabel:'A',
																	inputValue:'A'
																},{
																	name:'staffPromoteapply.mixedAbility',
																	boxLabel:'B',
																	inputValue:'B'
																},{
																	name:'staffPromoteapply.mixedAbility',
																	boxLabel:'C',
																	inputValue:'C'
																}
															]
														}
													]
												}
											]
										}
										]
							}, {
								xtype : "panel",
								layout : "column",
								style:'padding-top:5px;',
								border : true,
								unstyled : false,
								height : 70,
								items : [
										{
											xtype:'label',
											text:'注：',
											columnWidth:.05
										},
										{
										  xtype:'panel',
										  layout:'vbox',
										  border:false,
										  columnWidth:.95,
										  height : 65,
										  defaults:{
												style:'padding-top:3px;'
										  },
										  items:[
											  	{
													xtype : "label",
													text : "专业能力：A为从事本专业岗位5年以上；B为从事本专业岗位3-5年；C为从事本专业岗位3年以下"
												},{
													xtype : "label",
													text : "综合能力：A为沟通、协调、业务、责任心、创新等方面能力较强；B为沟通、协调、业务、责任心、创新等方面能力良好；C为沟通、协调、业务、责任心、创新等方面能力一般；"
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
				url : __ctxPath + '/statistics/getStaffPromoteapply.do?id='+ this.id,
				waitMsg : '正在载入数据...',
				success : function(form, action) {
					var jsonData = Ext.util.JSON.decode(action.response.responseText);
					var res = jsonData.data;
					if(!Ext.isEmpty(res.oldDept)){
						Ext.getCmp("staffPromoteapply.oldDepTreeSelector").setValue(res.oldDept.depName);
						Ext.getCmp("staffPromoteapply.oldDepTreeSelector").originalValue=res.oldDept.depName;
						Ext.getCmp("staffPromoteapply.oldDept.depId").setValue(res.oldDept.depId);
						Ext.getCmp("staffPromoteapply.oldDept.depId").originalValue=res.oldDept.depId;
					}
					if(!Ext.isEmpty(res.oldJob)){
						Ext.getCmp("staffPromoteapply.oldJobName").setValue(res.oldJob.jobName);
						Ext.getCmp("staffPromoteapply.oldJobName").originalValue=res.oldJob.jobName;
						Ext.getCmp("staffPromoteapply.oldJob.jobId").setValue(res.oldJob.jobId);
						Ext.getCmp("staffPromoteapply.oldJob.jobId").originalValue=res.oldJob.jobId;
					}
					if(!Ext.isEmpty(res.newDept)){
						Ext.getCmp("staffPromoteapply.newDepTreeSelector").setValue(res.newDept.depName);
						Ext.getCmp("staffPromoteapply.newDepTreeSelector").originalValue=res.newDept.depName;
						Ext.getCmp("staffPromoteapply.newDept.depId").setValue(res.newDept.depId);
						Ext.getCmp("staffPromoteapply.newDept.depId").originalValue=res.newDept.depId;
						Ext.getCmp("staffPromoteapply.newJobName").
							getStore().reload({
										params : {
											depId : res.newDept.depId
										}
									});
					}
					if(!Ext.isEmpty(res.newJob)){
						Ext.getCmp("staffPromoteapply.newJobName").setValue(res.newJob.jobName);
						Ext.getCmp("staffPromoteapply.newJobName").originalValue=res.newJob.jobName;
						Ext.getCmp("staffPromoteapply.newJob.jobId").setValue(res.newJob.jobId);
						Ext.getCmp("staffPromoteapply.newJob.jobId").originalValue=res.newJob.jobId;
					}
					if(!Ext.isEmpty(res.promoteUser)){
						Ext.getCmp("promoteUserName").setValue(res.promoteUser.fullname);
						Ext.getCmp("promoteUserName").originalValue=res.promoteUser.fullname;
						Ext.getCmp("staffPromoteapply.promoteUser.userId").setValue(res.promoteUser.userId);
						Ext.getCmp("staffPromoteapply.promoteUser.userId").originalValue=res.promoteUser.userId;
					}
					Ext.getCmp("mixedAbility").originalValue=res.mixedAbility;
					Ext.getCmp("professional").originalValue=res.professional;
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
					var gridPanel=Ext.getCmp('StaffPromoteapplyGrid');
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