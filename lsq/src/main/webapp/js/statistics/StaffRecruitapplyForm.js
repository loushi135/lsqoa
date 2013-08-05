/**
 * @author 
 * @createtime 
 * @class StaffRecruitapplyForm
 * @extends Ext.Window
 * @description StaffRecruitapply表单
 */
StaffRecruitapplyForm=Ext.extend(Ext.Window,{
	//内嵌FormPanel
	formPanel:null,
	//构造函数
	constructor:function(_cfg){
		Ext.applyIf(this,_cfg);
		//必须先初始化组件
		this.initUIComponents();
		StaffRecruitapplyForm.superclass.constructor.call(this,{
			id:'StaffRecruitapplyFormWin',
			layout:'fit',
			items:this.formPanel,
			modal:true,
			width : 700,
			height : 550,
			maximizable:true,
			title:'[员工招聘]详细信息',
			buttonAlign : 'center',
			buttons:this.buttons
		});
	},//end of the constructor
	//初始化组件
	initUIComponents:function(){
		
		var url = __ctxPath + "/system/listDepartment.do?opt=appUser";
				var depTreeSelector = new TreeSelector("staffRecruitapply.depTreeSelector", url, "所属部门", "staffRecruitapply.dept.depId",false,247,"depTreeSelector");
				Ext.getCmp("staffRecruitapply.depTreeSelectorTree").on("click", function(g) {
					Ext.getCmp("staffRecruitapply.jobName").setValue("");
					Ext.getCmp("staffRecruitapply.jobName").getStore().removeAll();
					var deptId = Ext.getCmp("staffRecruitapply.dept.depId").getValue();
					if (!Ext.isEmpty(deptId)) {
						Ext.getCmp("staffRecruitapply.jobName").
							getStore().reload( {
										params : {
											depId : deptId
										}
									});
					}
				});
				var mainPositionCombo = new Ext.ux.form.LovCombo({
										  hiddenName:"mainPositionIDs",
										  id:'staffRecruitapply.mainPositionIDs',
										  fieldLabel:"主要工作地点",
										  columnWidth:0.85,
										  store : new Ext.data.JsonStore({
												baseParams:{"Q_itemName_S_EQ":'工作地点'},
												autoLoad:true,
												root : "result",
												totalProperty : "totalCounts",
												url : __ctxPath
														+ "/system/listDictionary.do",
												fields : [ "dicId", "itemName","itemValue" ]
											}),
										  mode:'local',
										  triggerAction:'all',
										  hideTrigger:false,
										  showSelectAll:true,
										  allowBlank:false,
										  displayField:'itemValue',
										  valueField:'dicId',
//										  value: "小学,初中",  
										  emptyText:'请选择主要工作地点',
										  editable:false,
										  listeners:{
										  	select : function(combo,record,index){
//										  		alert(combo.getCheckedDisplay());//显示的值
										  		Ext.getCmp("mainPositions").setValue(combo.getCheckedDisplay());
											}
										  }
										 });
		this.formPanel=new Ext.FormPanel({
				layout : 'form',
				trackResetOnLoad:true,
				bodyStyle: 'padding:10px 10px 10px 10px;overflow-x:hidden;overflow-y:scroll',
				border:false,
				url : __ctxPath + '/statistics/saveStaffRecruitapply.do',
				id : 'StaffRecruitapplyForm',
				defaults : {
					anchor : '98%,98%'
				},
				defaultType : 'textfield',
				items : [{
							name : 'staffRecruitapply.id',
							id : 'id',
							xtype:'hidden',
							value : this.id == null ? '' : this.id
						},{
						   xtype:'hidden',
						   name:'staffRecruitapply.processRunId',
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
										text:'申请公司：',
										width:97
									},
									{
										xtype:'textfield',
										id:'companyName',
										name:'staffRecruitapply.companyName',
										maxLength : 64,
										value:__companyName,
										allowBlank :false,
										style:"background:#F0F0F0;",
										width:200
									},
									{
										text:'部门：',
										style:'margin-left:10px;',
										width:97
									},
									{
										xtype : "hidden",
										name : "staffRecruitapply.dept.depId",
										id : "staffRecruitapply.dept.depId"
									},
									depTreeSelector
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
										text:'空缺职位名称：',
										width:97
									},
									{
										fieldLabel : "职位",
										id:'staffRecruitapply.jobName',
										hiddenName:'jobName',
										xtype : "combo",
										mode : "local",
										width:195,
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
												var deptId = Ext.getCmp("staffRecruitapply.dept.depId").getValue();
												if (Ext.isEmpty(deptId)) {
													 Ext.ux.Toast.msg("操作信息","请先选择部门！");
												}
											},
											select : function(combo,record,index){
												var jobId = record.data.jobId;
												var jobName = record.data.jobName;
												Ext.getCmp("staffRecruitapply.job.jobId").setValue(jobId);
												var url = __ctxPath + "/system/listAppUser.do";
												var paramsStr = "Q_department.depId_L_EQ="+Ext.getCmp("staffRecruitapply.dept.depId").getValue()+"&Q_position_S_EQ="+jobName;
												var totalCounts = ajaxSyncCall(url,paramsStr).totalCounts;
												Ext.getCmp("currentNum").setValue(totalCounts);
											}
										}
									},
									{
										xtype:'hidden',
										id:'staffRecruitapply.job.jobId',
										name:'staffRecruitapply.job.jobId'
									},
									{
										text:'该职位目前人数：',
										style:'margin-left:10px;',
										width:97
									},
									{
										xtype:'numberfield',
										id:'currentNum',
										name:'staffRecruitapply.currentNum',
										maxLength : 10,
										readOnly:true,
										style:"background:#F0F0F0;",
										width:70
									},
									{
										text:'此次申请人数：',
										style:'margin-left:10px;',
										width:97
									},
									{
										xtype:'numberfield',
										id:'applyNum',
										name:'staffRecruitapply.applyNum',
										maxLength : 10,
										allowBlank :false,
										width:83
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
											text : '申请理由：',
											columnWidth:0.15
										}, {
											xtype : 'textarea',
											id:'applyReason',
											name:'staffRecruitapply.applyReason',
											allowBlank : false,
											columnWidth : 0.85
										}]
						   },{
								xtype: 'container',
								layout:'column',
								height:25,
								defaults:{
									xtype:'label'
								},
								items:[
									{
										text:'年龄：',
										columnWidth:0.15
									},
									{
										xtype:'textfield',
										id:'age',
										name:'staffRecruitapply.age',
										allowBlank:false,
										maxLength:64,
										columnWidth:.2
									},
									{
										text:'性别：',
										style:'margin-left:10px;',
										columnWidth:.15
									},
									{
										xtype:'combo',
										id:'sex',
										hiddenName:'staffRecruitapply.sex',
										mode:'local',
										store:['男','女'],
										columnWidth:0.2,
										editable:false,
										allowBlank :false
									},
									{
										text:'专业：',
										style:'margin-left:10px;',
										columnWidth:.1
									},
									{
										xtype:'combo',
										id:'staffRecruitapply.majorDic.itemValue',
										hiddenName:'itemValue',
										mode:'local',
										valueField : "itemValue",
										displayField : "itemValue",
										triggerAction : "all",
										store : new Ext.data.JsonStore({
											baseParams:{"Q_itemName_S_EQ":'专业'},
											autoLoad:true,
											root : "result",
											totalProperty : "totalCounts",
											url : __ctxPath
													+ "/system/listDictionary.do",
											fields : ["dicId","itemName","itemValue" ]
										}),
										columnWidth:0.2,
										editable:false,
										allowBlank :false,
										listeners : {
											select : function(combo,record,index){
												var dicId = record.data.dicId;
												Ext.getCmp("staffRecruitapply.majorDic.dicId").setValue(dicId);
											}
										}
									},{
										xtype:'hidden',
										id:'staffRecruitapply.majorDic.dicId',
										name:'staffRecruitapply.majorDic.dicId'
									}
								]
							},{
								xtype: 'container',
								layout:'column',
								height:25,
								defaults:{
									xtype:'label'
								},
								items:[
									{
										text:'学历：',
										columnWidth:.15
									},
									{
										xtype:'combo',
										id:'staffRecruitapply.educationDic.itemValue',
										hiddenName:'itemValue',
										mode:'local',
										valueField : "itemValue",
										displayField : "itemValue",
										triggerAction : "all",
										store : new Ext.data.JsonStore({
											baseParams:{"Q_itemName_S_EQ":'学历'},
											autoLoad:true,
											root : "result",
											totalProperty : "totalCounts",
											url : __ctxPath
													+ "/system/listDictionary.do",
											fields : [ "dicId", "itemName","itemValue" ]
										}),
										columnWidth:0.2,
										editable:false,
										allowBlank :false,
										listeners : {
											select : function(combo,record,index){
												var dicId = record.data.dicId;
												Ext.getCmp("staffRecruitapply.educationDic.dicId").setValue(dicId);
											}
										}
									},{
										xtype:'hidden',
										id:'staffRecruitapply.educationDic.dicId',
										name:'staffRecruitapply.educationDic.dicId'
									},
									{
										text:'工作年限：',
										style:'margin-left:10px;',
										columnWidth:.15
									},
									{
										xtype:'textfield',
										id:'workYear',
										name:'staffRecruitapply.workYear',
										columnWidth:0.5,
										allowBlank :false
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
											text : '该职位的主要职责：',
											columnWidth:0.15
										}, {
											xtype : 'textarea',
											id:'positionDuty',
											name:'staffRecruitapply.positionDuty',
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
											text : '技能要求：',
											columnWidth:0.15
										}, {
											xtype : 'textarea',
											id:'skillRequirement',
											name:'staffRecruitapply.skillRequirement',
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
											text : '工作经历：',
											columnWidth:0.15
										}, {
											xtype : 'textarea',
											id:'workExperience',
											name:'staffRecruitapply.workExperience',
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
											text : '个性：',
											columnWidth:0.15
										}, {
											xtype : 'textarea',
											id:'personality',
											name:'staffRecruitapply.personality',
											allowBlank : false,
											columnWidth : 0.85
										}]
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
											text : '主要工作地点：',
											columnWidth:0.15
										},mainPositionCombo,
										{
											xtype:'hidden',
											name:'staffRecruitapply.mainPositions',
											id:'mainPositions'
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
											text : '其他要求：',
											columnWidth:0.15
										}, {
											xtype : 'textarea',
											id:'otherRequirement',
											name:'staffRecruitapply.otherRequirement',
											allowBlank : false,
											columnWidth : 0.85
										}]
						   },{
								xtype: 'container',
								layout:'column',
								height:25,
								defaults:{
									xtype:'label'
								},
								items:[
									{
										text:'到岗期限：',
										columnWidth:.15
									},
									{
										xtype:'datefield',
										id:'deadline',
										name:'staffRecruitapply.deadline',
										format:'Y-m-d',
										columnWidth:0.4,
										editable:false,
										allowBlank :false
									},
									{
										text:'人员性质：',
										style:'margin-left:10px;',
										columnWidth:.15
									},
									{
										xtype:'radiogroup',
										id:'personalCharacter',
										name:'staffRecruitapply.personalCharacter',
										columnWidth:.28,
										items:[
											{
												name:'staffRecruitapply.personalCharacter',
												boxLabel:"编内",
												inputValue:'编内'
											},{
												name:'staffRecruitapply.personalCharacter',
												boxLabel:"编外",
												inputValue:'编外'
											}
										],
										allowBlank :false
									}
								]
							}
							]
			});
		//加载表单对应的数据	
		if (this.id != null && this.id != 'undefined') {
			this.formPanel.getForm().load({
				deferredRender : false,
				url : __ctxPath + '/statistics/getStaffRecruitapply.do?id='+ this.id,
				waitMsg : '正在载入数据...',
				success : function(form, action) {
					var jsonData = Ext.util.JSON.decode(action.response.responseText);
					var res = jsonData.data;
					if(!Ext.isEmpty(res.dept)){
						Ext.getCmp("staffRecruitapply.depTreeSelector").setValue(res.dept.depName);
						Ext.getCmp("staffRecruitapply.depTreeSelector").originalValue=res.dept.depName;
						Ext.getCmp("staffRecruitapply.dept.depId").setValue(res.dept.depId);
						Ext.getCmp("staffRecruitapply.dept.depId").originalValue=res.dept.depId;
						Ext.getCmp("staffRecruitapply.jobName").
							getStore().reload( {
										params : {
											depId : res.dept.depId
										}
									});
					}
					if(!Ext.isEmpty(res.job)){
						Ext.getCmp("staffRecruitapply.jobName").setValue(res.job.jobName);
						Ext.getCmp("staffRecruitapply.jobName").originalValue=res.job.jobName;
						Ext.getCmp("staffRecruitapply.job.jobId").setValue(res.job.jobId);
						Ext.getCmp("staffRecruitapply.job.jobId").originalValue=res.job.jobId;
					}
					if(!Ext.isEmpty(res.majorDic)){
						Ext.getCmp("staffRecruitapply.majorDic.itemValue").setValue(res.majorDic.itemValue);
						Ext.getCmp("staffRecruitapply.majorDic.itemValue").originalValue=res.majorDic.itemValue;
						Ext.getCmp("staffRecruitapply.majorDic.dicId").setValue(res.majorDic.dicId);
						Ext.getCmp("staffRecruitapply.majorDic.dicId").originalValue=res.majorDic.dicId;
					}
					if(!Ext.isEmpty(res.educationDic)){
						Ext.getCmp("staffRecruitapply.educationDic.itemValue").setValue(res.educationDic.itemValue);
						Ext.getCmp("staffRecruitapply.educationDic.itemValue").originalValue=res.educationDic.itemValue;
						Ext.getCmp("staffRecruitapply.educationDic.dicId").setValue(res.educationDic.dicId);
						Ext.getCmp("staffRecruitapply.educationDic.dicId").originalValue=res.educationDic.dicId;
					}
					Ext.getCmp("personalCharacter").originalValue=res.personalCharacter;
					if(!Ext.isEmpty(res.mainPositionDics)){
						var ids = "";
						Ext.each(res.mainPositionDics,function(item,i){
							if(ids!=""){
								ids+=",";
							}
							ids+=item.dicId;
						});
						Ext.getCmp("staffRecruitapply.mainPositionIDs").setValue(ids);
						Ext.getCmp("staffRecruitapply.mainPositionIDs").originalValue=ids;
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
					var gridPanel=Ext.getCmp('StaffRecruitapplyGrid');
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