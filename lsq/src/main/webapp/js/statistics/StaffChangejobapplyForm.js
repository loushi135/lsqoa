/**
 * @author 
 * @createtime 
 * @class StaffChangejobapplyForm
 * @extends Ext.Window
 * @description StaffChangejobapply表单
 */
StaffChangejobapplyForm=Ext.extend(Ext.Window,{
	//内嵌FormPanel
	formPanel:null,
	//构造函数
	constructor:function(_cfg){
		Ext.applyIf(this,_cfg);
		//必须先初始化组件
		this.initUIComponents();
		StaffChangejobapplyForm.superclass.constructor.call(this,{
			id:'StaffChangejobapplyFormWin',
			layout:'fit',
			items:this.formPanel,
			modal:true,
			width : 750,
			height : 250,
			maximizable:true,
			title:'[员工转岗]详细信息',
			buttonAlign : 'center',
			buttons:this.buttons
		});
	},//end of the constructor
	//初始化组件
	initUIComponents:function(){
		var url = __ctxPath + "/system/listDepartment.do?opt=appUser";
		var newDepTreeSelector = new TreeSelector("staffChangejobapply.newDepTreeSelector", url, "所属部门", "staffChangejobapply.newDept.depId",false,165,"newDepTreeSelector");
				Ext.getCmp("staffChangejobapply.newDepTreeSelectorTree").on("click", function(g) {
					Ext.getCmp("staffChangejobapply.newJobName").setValue("");
					Ext.getCmp("staffChangejobapply.newJobName").getStore().removeAll();
					var depId = Ext.getCmp("staffChangejobapply.newDept.depId").getValue();
					if (!Ext.isEmpty(depId)) {
						Ext.getCmp("staffChangejobapply.newJobName").
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
				url : __ctxPath + '/statistics/saveStaffChangejobapply.do',
				id : 'StaffChangejobapplyForm',
				defaults : {
					anchor : '98%,98%'
				},
				defaultType : 'textfield',
				items : [{
							name : 'staffChangejobapply.id',
							id : 'id',
							xtype:'hidden',
							value : this.id == null ? '' : this.id
						},{
							   xtype:'hidden',
							   name:'staffChangejobapply.processRunId',
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
										text:'公司：',
										width:60
									},
									{
										xtype:'textfield',
										name:'staffChangejobapply.companyName',
										id:'companyName',
										readOnly:true,
										style:"background:#F0F0F0;",
										value:__companyName,
										width:170
									},
									{
										text:'员工姓名：',
										style:'margin-left:10px;',
										width:70
									},
									{
										xtype:'textfield',
										name:'staffChangejobapply.changeJobUserName',
										id:'changeJobUserName',
										readOnly:true,
										style:"background:#F0F0F0;",
										value:__currentUser,
										width:70
									},
									{
										xtype:'hidden',
										name:'staffChangejobapply.changeJobUser.userId',
										id:'staffChangejobapply.changeJobUser.userId',
										value:__currentUserId
									},
									{
										text:'原部门：',
										style:'margin-left:10px;',
										width:50
									},
									{
										xtype : "hidden",
										name : "staffChangejobapply.oldDept.depId",
										id : "staffChangejobapply.oldDept.depId",
										value:__currentUserDeptId
									},
									{
										xtype:'textfield',
										name:'oldDepTreeSelector',
										id:'staffChangejobapply.oldDepTreeSelector',
										readOnly:true,
										style:"background:#F0F0F0;",
										value:__currentUserDept,
										width:100
									},
									{
										text:'原任职位：',
										style:'margin-left:10px;',
										width:60
									},{
										xtype:'textfield',
										name:'oldJobName',
										id:'staffChangejobapply.oldJobName',
										readOnly:true,
										style:"background:#F0F0F0;",
										value:__currentUserPosition,
										width:110
									},
									{
										xtype : "hidden",
										name : "staffChangejobapply.oldJob.jobId",
										id : "staffChangejobapply.oldJob.jobId",
										value:__currentUserDeptId
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
										text:'新部门：',
										width:60
									},
									{
										xtype : "hidden",
										name : "staffChangejobapply.newDept.depId",
										id : "staffChangejobapply.newDept.depId"
									},
									newDepTreeSelector,
									{
										text:'新任职位：',
										style:'margin-left:10px;',
										width:70
									},
									{
										hiddenName:'newJobName',
										id:'staffChangejobapply.newJobName',
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
												var depId = Ext.getCmp("staffChangejobapply.newDept.depId").getValue();
												if (Ext.isEmpty(depId)) {
													Ext.ux.Toast.msg("操作信息","请先选择新部门！");
												}
											},
											select : function(combo,record,index){
												var jobId = record.data.jobId;
												var jobName = record.data.jobName;
												Ext.getCmp("staffChangejobapply.newJob.jobId").setValue(jobId);
											}
										}
									},
									{
										xtype:'hidden',
										name:'staffChangejobapply.newJob.jobId',
										id:'staffChangejobapply.newJob.jobId'
									},{
										text:'转岗日期：',
										style:'margin-left:10px;',
										width:70
									},{
										xtype:'datefield',
										name:'staffChangejobapply.changeJobDate',
										id:'changeJobDate',
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
											text : "转岗原因:",
											height : 50,
											width : 60
										}, {
											xtype : "textarea",
											name:'staffChangejobapply.changeJobReason',
											id:'changeJobReason',
											width: 640,
											height:70,
											allowBlank:false
										}]
							}
							]
			});
		//加载表单对应的数据	
		if (this.id != null && this.id != 'undefined') {
			this.formPanel.getForm().load({
				deferredRender : false,
				url : __ctxPath + '/statistics/getStaffChangejobapply.do?id='+ this.id,
				waitMsg : '正在载入数据...',
				success : function(form, action) {
					var jsonData = Ext.util.JSON.decode(action.response.responseText);
					var res = jsonData.data;
					if(!Ext.isEmpty(res.oldDept)){
						Ext.getCmp("staffChangejobapply.oldDepTreeSelector").setValue(res.oldDept.depName);
						Ext.getCmp("staffChangejobapply.oldDepTreeSelector").originalValue=res.oldDept.depName;
						Ext.getCmp("staffChangejobapply.oldDept.depId").setValue(res.oldDept.depId);
						Ext.getCmp("staffChangejobapply.oldDept.depId").originalValue=res.oldDept.depId;
					}
					if(!Ext.isEmpty(res.oldJob)){
						Ext.getCmp("staffChangejobapply.oldJobName").setValue(res.oldJob.jobName);
						Ext.getCmp("staffChangejobapply.oldJobName").originalValue=res.oldJob.jobName;
						Ext.getCmp("staffChangejobapply.oldJob.jobId").setValue(res.oldJob.jobId);
						Ext.getCmp("staffChangejobapply.oldJob.jobId").originalValue=res.oldJob.jobId;
					}
					if(!Ext.isEmpty(res.newDept)){
						Ext.getCmp("staffChangejobapply.newDepTreeSelector").setValue(res.newDept.depName);
						Ext.getCmp("staffChangejobapply.newDepTreeSelector").originalValue=res.newDept.depName;
						Ext.getCmp("staffChangejobapply.newDept.depId").setValue(res.newDept.depId);
						Ext.getCmp("staffChangejobapply.newDept.depId").originalValue=res.newDept.depId;
						Ext.getCmp("staffChangejobapply.newJobName").
							getStore().reload({
										params : {
											depId : res.newDept.depId
										}
									});
					}
					if(!Ext.isEmpty(res.newJob)){
						Ext.getCmp("staffChangejobapply.newJobName").setValue(res.newJob.jobName);
						Ext.getCmp("staffChangejobapply.newJobName").originalValue=res.newJob.jobName;
						Ext.getCmp("staffChangejobapply.newJob.jobId").setValue(res.newJob.jobId);
						Ext.getCmp("staffChangejobapply.newJob.jobId").originalValue=res.newJob.jobId;
					}
					if(!Ext.isEmpty(res.changeJobUser)){
						Ext.getCmp("changeJobUserName").setValue(res.changeJobUser.fullname);
						Ext.getCmp("changeJobUserName").originalValue=res.changeJobUser.fullname;
						Ext.getCmp("staffChangejobapply.changeJobUser.userId").setValue(res.changeJobUser.userId);
						Ext.getCmp("staffChangejobapply.changeJobUser.userId").originalValue=res.changeJobUser.userId;
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
					var gridPanel=Ext.getCmp('StaffChangejobapplyGrid');
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