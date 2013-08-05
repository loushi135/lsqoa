/**
 * @author
 * @createtime
 * @class StaffEntryapplyForm
 * @extends Ext.Window
 * @description StaffEntryapply表单
 */
StaffEntryapplyForm = Ext.extend(Ext.Window, {
			// 内嵌FormPanel
			formPanel : null,
			// 构造函数
			constructor : function(_cfg) {
				Ext.applyIf(this, _cfg);
				// 必须先初始化组件
				this.initUIComponents();
				StaffEntryapplyForm.superclass.constructor.call(this, {
							id : 'StaffEntryapplyFormWin',
							layout : 'fit',
							items : this.formPanel,
							modal : true,
							height : 220,
							labelAlign : "left",
							width : 700,
							maximizable : true,
							title : '[员工入职]详细信息',
							buttonAlign : 'center',
							buttons : this.buttons
						});
			},// end of the constructor
			// 初始化组件
			initUIComponents : function() {
				var url = __ctxPath + "/system/listDepartment.do?opt=appUser";
				var depTreeSelector = new TreeSelector("depTreeSelector", url, "所属部门", "depId",null,247,"staffEntryapply.depTreeSelector");
				Ext.getCmp("depTreeSelectorTree").on("click", function(g) {
					Ext.getCmp("jobName").setValue("");
					Ext.getCmp("jobName").getStore().removeAll();
					var depId = Ext.getCmp("depId").getValue();
					if (!Ext.isEmpty(depId)) {
						Ext.getCmp("jobName").
							getStore().reload({
										params : {
											depId : depId
										}
									});
					}
				});
				this.formPanel = new Ext.FormPanel({
							layout : 'form',
							trackResetOnLoad : true,
							bodyStyle : 'padding:10px 10px 10px 10px',
							border : false,
							url : __ctxPath
									+ '/statistics/saveStaffEntryapply.do',
							id : 'StaffEntryapplyForm',
							defaults : {
								anchor : '98%,98%'
							},
							defaultType : 'textfield',
							items : [
							{
								xtype:'hidden',
								name:'staffEntryapply.id',
								value:this.id								
							},{
							   xtype:'hidden',
							   name:'staffEntryapply.processRunId',
							   value:-1
							},
							{
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
										text:'入职公司：',
										width:97
									},
									{
										xtype:'textfield',
										id:'companyName',
										name:'staffEntryapply.companyName',
										maxLength : 64,
										value:__companyName,
										allowBlank :false,
										style:"background:#F0F0F0;",
										width:200
									},
									{
										text:'入职部门：',
										style:'margin-left:10px;',
										width:97
									},
									{
										xtype : "hidden",
										name : "staffEntryapply.dept.depId",
										id : "depId"
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
										text:'姓名：',
										width:97
									},
									{
										xtype:'textfield',
										id:'entryUserName',
										name:'staffEntryapply.entryUserName',
										maxLength : 64,
										allowBlank :false,
										width:200
									},
									{
										text:'担任岗位：',
										style:'margin-left:10px;',
										width:97
									},
									{
										id:'jobName',
										hiddenName:'staffEntryapply.jobName',
										xtype : "combo",
										mode : "local",
										width:247,
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
												var deptId = Ext.getCmp("depId").getValue();
												if (Ext.isEmpty(deptId)) {
													Ext.ux.Toast.msg("操作信息","请先选择部门！");
												}
											},
											select : function(combo,record,index){
												var jobId = record.data.jobId;
												var jobName = record.data.jobName;
												Ext.getCmp("jobId").setValue(jobId);
											}
										}
									},
									{
										xtype:'hidden',
										id:'jobId',
										name:'staffEntryapply.job.jobId'
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
										text:'联系方式：',
										columnWidth:.15
									},
									{
										xtype:'textfield',
										id:'contact',
										name:'staffEntryapply.contact',
										columnWidth:0.3,
										maxLength:250,
										editable:false,
										allowBlank :false
									},
									{
										text:'拟报到日期：',
										style:'margin-left:10px;',
										columnWidth:.165
									},
									{
										xtype:'datefield',
										id:'entryDate',
										name:'staffEntryapply.entryDate',
										format:'Y-m-d',
										columnWidth:0.385,
										editable:false,
										allowBlank :false
									}
								]
							}
							]
						});
				// 加载表单对应的数据
				if (this.id != null && this.id != 'undefined') {
					this.formPanel.getForm().load({
						deferredRender : false,
						url : __ctxPath
								+ '/statistics/getStaffEntryapply.do?id='
								+ this.id,
						waitMsg : '正在载入数据...',
						success : function(form, action) {
							var jsonData = Ext.util.JSON.decode(action.response.responseText).data;
						    var depId=jsonData.dept.depId;
						    var depName=jsonData.dept.depName;
						    Ext.getCmp("depTreeSelector").setValue(depName)
						    Ext.getCmp("depTreeSelector").originalValue=depName;
						    Ext.getCmp("depId").setValue(depId);
						    Ext.getCmp("depId").originalValue =depId;
						    if(!Ext.isEmpty(jsonData.job)){
						    	var jobName=jsonData.job.jobName;
						    	Ext.getCmp("jobName").setValue(jobName);
						    	Ext.getCmp("jobName").originalValue =jobName;
								Ext.getCmp("jobId").setValue(jsonData.job.jobId);
								Ext.getCmp("jobId").originalValue=jsonData.job.jobId;
							}
//						    var entryDate=jsonData.entryDate
//						    Ext.getCmp("entryDate").setValue(Date.parseDate(entryDate,"Y-m-d H:i:s"));
//						    Ext.getCmp("entryDate").originalValue =Date.parseDate(entryDate,"Y-m-d H:i:s");
						},
						failure : function(form, action) {
						}
					});
				}
				// 初始化功能按钮
				this.buttons = [{
							text : '保存',
							iconCls : 'btn-save',
							handler : this.save.createCallback(this.formPanel,
									this)
						}, {
							text : '重置',
							iconCls : 'btn-reset',
							handler : this.reset.createCallback(this.formPanel)
						}, {
							text : '取消',
							iconCls : 'btn-cancel',
							handler : this.cancel.createCallback(this)
						}];
			},// end of the initcomponents

			/**
			 * 重置
			 * 
			 * @param {}
			 *            formPanel
			 */
			reset : function(formPanel) {
				formPanel.getForm().reset();
			},
			/**
			 * 取消
			 * 
			 * @param {}
			 *            window
			 */
			cancel : function(window) {
				window.close();
			},
			/**
			 * 保存记录
			 */
			save : function(formPanel, window) {
				if (formPanel.getForm().isValid()) {
					formPanel.getForm().submit({
								method : 'POST',
								waitMsg : '正在提交数据...',
								success : function(fp, action) {
									Ext.ux.Toast.msg('操作信息', '成功保存信息！');
									var gridPanel = Ext
											.getCmp('StaffEntryapplyGrid');
									if (gridPanel != null) {
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
			}// end of save

		});