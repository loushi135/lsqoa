/**
 * @author
 * @createtime
 * @class ProjectEndCheckForm
 * @extends Ext.Window
 * @description ProjectEndCheck表单
 */
ProjectEndCheckForm = Ext.extend(Ext.Window, {
			// 内嵌FormPanel
			formPanel : null,
			// 构造函数
			constructor : function(_cfg) {
				Ext.applyIf(this, _cfg);
				// 必须先初始化组件
				this.initUIComponents();
				ProjectEndCheckForm.superclass.constructor.call(this, {
							id : 'ProjectEndCheckFormWin',
							layout : 'fit',
							items : this.formPanel,
							modal : true,
							width : 670,
							height : 320,
							maximizable : true,
							title : '[项目完工内检申请]详细信息',
							buttonAlign : 'center',
							buttons : this.buttons
						});
			},// end of the constructor
			// 初始化组件
			initUIComponents : function() {
				var _url = __ctxPath + '/system/listWorkDepartment.do?opt=appUser';
				var depSelector = new TreeSelector('projectEndCheck.project.areaM', _url, '',
						'projectEndCheck.project.area', false, 212,'projectEndCheck.project.area');
				Ext.getCmp("projectEndCheck.project.areaMTree").on("click", function(g) {
					Ext.getCmp("projectEndCheck.project.areaId").setValue(Ext.getCmp("projectEndCheck.project.areaM").id);
				});
				this.formPanel = new Ext.FormPanel({
							layout : 'form',
							trackResetOnLoad : true,
							bodyStyle : 'padding:10px 10px 10px 10px',
							border : false,
							url : __ctxPath
									+ '/statistics/saveProjectEndCheck.do',
							id : 'ProjectEndCheckForm',
							defaults : {
								anchor : '98%,98%'
							},
							defaultType : 'textfield',
							items : [{
										name : 'projectEndCheck.id',
										id : 'id',
										xtype : 'hidden',
										value : this.id == null ? '' : this.id
									},{
										xtype : 'container',
										layout : 'column',
										height : 25,
										defaults : {
											xtype : 'label',
											style : {
												margin : '0px 0px 3px 0px'
											}
										},
										items : [{
													xtype:'label',
													text:'项目名称：',
													width : 97
												},{
													name:'projectEndCheck.project.proName',
													id:'projectEndCheck.project.proName',
													xtype:'textfield',
													emptyText:'请选择项目',
													editable:true,
													readOnly:false,
													allowBlank:false,
													columnWidth : 1,
													listeners:{
														'focus':function(){
															ProjectNewSelector.getView(function(proId,proName,proNo,contractId,contractNo,contractAmount,proAreaId,proArea,proChargerUserId,proChargerUserName){
																Ext.getCmp("projectEndCheck.project.proName").setValue(proName);
																Ext.getCmp("projectEndCheck.project.id").setValue(proId);
																Ext.getCmp('projectEndCheck.project.proChargerUserName').setValue(proChargerUserName);
																if(!Ext.isEmpty(proArea)&&Ext.isEmpty(proAreaId)){
																	var url = __ctxPath + '/system/getDeptByNameDepartment.do'
																	var params = "depName="+proArea;
																	var data = ajaxSyncCall(url,params).data;
																	if(!Ext.isEmpty(data)){
																		Ext.getCmp("projectEndCheck.project.areaId").setValue(data.depId);
																		Ext.getCmp("projectEndCheck.project.areaM").setValue(data.depName);
																	}
																}else{
																	Ext.getCmp("projectEndCheck.project.areaId").setValue(proAreaId);
																	Ext.getCmp("projectEndCheck.project.areaM").setValue(proArea);
																}
																if(!Ext.isEmpty(proId)){
																	var url = __ctxPath + '/statistics/getProjectNew.do';
																	var params = "id="+proId;
																	var data = ajaxSyncCall(url,params).data;
																	Ext.getCmp('projectEndCheck.project.buildUnit').setValue(data.buildUnit);
																	Ext.getCmp('projectEndCheck.project.proAddr').setValue(data.proAddr);
																}
															},true,true).show();
														}
													}
												},{
													xtype:'hidden',
													name:'projectEndCheck.project.id',
													id:'projectEndCheck.project.id'
												}
												]
									},{
										xtype : 'container',
										layout : 'column',
										height : 25,
										defaults : {
											xtype : 'label',
											style : {
												margin : '0px 0px 3px 0px'
											}
										},
										items : [{
													xtype:'label',
													text:'建设单位：',
													width : 97
												},{
													name:'projectEndCheck.project.buildUnit',
													id:'projectEndCheck.project.buildUnit',
													xtype:'textfield',
//													readOnly:true,
//													style : "background:#F0F0F0;",
													allowBlank:true,
													columnWidth : 1
												}
												]
									},{
										xtype : 'container',
										layout : 'column',
										height : 25,
										defaults : {
											xtype : 'label',
											style : {
												margin : '0px 0px 3px 0px'
											}
										},
										items : [{
													xtype:'label',
													text:'项目地点：',
													width : 97
												},{
													name:'projectEndCheck.project.proAddr',
													id:'projectEndCheck.project.proAddr',
													xtype:'textfield',
//													readOnly:true,
//													style : "background:#F0F0F0;",
													allowBlank:true,
													columnWidth : 1
												}
												]
									},{
										xtype : 'container',
										layout : 'column',
										height : 25,
										defaults : {
											xtype : 'label',
											style : {
												margin : '0px 0px 3px 0px'
											}
										},
										items : [{
													xtype:'label',
													text:'施工区域：',
													width : 97
												},depSelector
//												,{
//													name:'projectEndCheck.project.area',
//													id:'projectEndCheck.project.area',
//													xtype:'textfield',
//													style : "background:#F0F0F0;",
//													readOnly:true,
//													allowBlank:true,
//													columnWidth : .5
//												}
												,{
													xtype:'hidden',
													name:'areaId',
													id:'projectEndCheck.project.areaId'
												}, {
													text : '项目经理：',
													style : 'margin-left:10px;',
													width : 97
												}, {
													xtype:'textfield',
													name : 'proChargerUserName',
													id : 'projectEndCheck.project.proChargerUserName',
													readOnly : true,
													allowBlank:false,
													columnWidth : 1,
													listeners : {
														'focus' : function() {
															UserSelector.getView(function(n,l,mobile) {
																		var projectCharger = Ext.getCmp("projectEndCheck.project.proChargerUserName");
																		projectCharger.setValue(l);
																			},true)
																	.show()
														}
													}
												}
												]
									},{
										xtype : 'container',
										layout : 'column',
										height : 25,
										defaults : {
											xtype : 'label',
											style : {
												margin : '0px 0px 3px 0px'
											}
										},
										items : [{
													xtype:'label',
													text:'质量员：',
													width : 97
												},{
													name:'projectEndCheck.qualityUser.fullname',
													id:'projectEndCheck.qualityUser.fullname',
													xtype:'textfield',
													emptyText:'请选择质量员',
													readOnly:true,
													allowBlank:false,
													columnWidth : .5,
													listeners:{
														'focus' : function() {
															UserSelector.getView(function(n,l,mobile) {
																				var userId = Ext.getCmp("projectEndCheck.qualityUser.userId");
																				var fullname = Ext.getCmp("projectEndCheck.qualityUser.fullname");
																				userId.setValue(n);
																				fullname.setValue(l);
																			},true)
																	.show()
														}
													}
												},{
													xtype:'hidden',
													name:'projectEndCheck.qualityUser.userId',
													id:'projectEndCheck.qualityUser.userId'
												}, {
													text : '施工员：',
													style : 'margin-left:10px;',
													width : 97
												},{
													name:'projectEndCheck.workUser.fullname',
													id:'projectEndCheck.workUser.fullname',
													xtype:'textfield',
													emptyText:'请选择施工员',
													readOnly:true,
													allowBlank:false,
													columnWidth : .5,
													listeners:{
														'focus' : function() {
															UserSelector.getView(function(n,l,mobile) {
																				var userId = Ext.getCmp("projectEndCheck.workUser.userId");
																				var fullname = Ext.getCmp("projectEndCheck.workUser.fullname");
																				userId.setValue(n);
																				fullname.setValue(l);
																			},true)
																	.show()
														}
													}
												},{
													xtype:'hidden',
													name:'projectEndCheck.workUser.userId',
													id:'projectEndCheck.workUser.userId'
												}
												]
									}, {
										xtype : 'container',
										layout : 'column',
										height : 25,
										defaults : {
											xtype : 'label',
											style : {
												margin : '0px 0px 3px 0px'
											}
										},
										items : [{
													text : '安全员：',
													width : 97
												},{
													name:'projectEndCheck.safeUser.fullname',
													id:'projectEndCheck.safeUser.fullname',
													xtype:'textfield',
													emptyText:'请选择安全员',
													readOnly:true,
													allowBlank:false,
													columnWidth : .5,
													listeners:{
														'focus' : function() {
															UserSelector.getView(function(n,l,mobile) {
																				var userId = Ext.getCmp("projectEndCheck.safeUser.userId");
																				var fullname = Ext.getCmp("projectEndCheck.safeUser.fullname");
																				userId.setValue(n);
																				fullname.setValue(l);
																			},true)
																	.show()
														}
													}
												},{
													xtype:'hidden',
													name:'projectEndCheck.safeUser.userId',
													id:'projectEndCheck.safeUser.userId'
												}, {
													text : '预决算员：',
													style : 'margin-left:10px;',
													width : 97
												},{
													name:'projectEndCheck.preCalUser.fullname',
													id:'projectEndCheck.preCalUser.fullname',
													xtype:'textfield',
													emptyText:'请选择预决算员',
													readOnly:true,
													allowBlank:false,
													columnWidth : .5,
													listeners:{
														'focus' : function() {
															UserSelector.getView(function(n,l,mobile) {
																				var userId = Ext.getCmp("projectEndCheck.preCalUser.userId");
																				var fullname = Ext.getCmp("projectEndCheck.preCalUser.fullname");
																				userId.setValue(n);
																				fullname.setValue(l);
																			},true)
																	.show()
														}
													}
												},{
													xtype:'hidden',
													name:'projectEndCheck.preCalUser.userId',
													id:'projectEndCheck.preCalUser.userId'
												}]
									}, {
										xtype : 'container',
										layout : 'column',
										height : 25,
										defaults : {
											xtype : 'label',
											style : {
												margin : '0px 0px 3px 0px'
											}
										},
										items : [{
													text : '工程督导：',
													width : 97
												}, {
													xtype : 'combo',
													hiddenName : 'projectEndCheck.engineeUser.fullname',
													id : 'projectEndCheck.engineeUser.fullnameId',
													mode : 'local',
													valueField : "fullname",
													displayField : "fullname",
													triggerAction : "all",
													store : new Ext.data.JsonStore({
																baseParams : {
																	"fullnames" : "'仲荣','汪鸿'"
																},
																autoLoad : true,
																root : "result",
																totalProperty : "totalCounts",
																url : __ctxPath + "/system/findByFullnamesAppUser.do",
																fields : [{
																		name : "userId",
																		type : "int"
																	}, "username",'staffNo', "password", "fullname",
																	"address", "email", "department", "title",
																	"position", {
																		name : "accessionTime"
																	}, {
																		name : "status",
																		type : "int"
																	}]
															}),
													editable : false,
													allowBlank : false,
													columnWidth:.5,
													listeners:{
														select:function(field,record,index){
															Ext.getCmp('projectEndCheck.engineeUser.userId').setValue(record.data.userId);
														}
													}
												},{
													xtype:'hidden',
													name:'projectEndCheck.engineeUser.userId',
													id:'projectEndCheck.engineeUser.userId'
												}, {
													text : '项目部自检结果：',
													style : 'margin-left:10px;',
													width : 97
												}, {
													xtype:'textfield',
													name : 'projectEndCheck.conclusion',
													id : 'conclusion',
													maxLength:64,
													allowBlank : false,
													columnWidth : .5
												}]
									},{
										xtype : 'container',
										layout : 'column',
										height : 25,
										defaults : {
											xtype : 'label',
											style : {
												margin : '0px 0px 3px 0px'
											}
										},
										items : [{
													xtype:'label',
													text:'实际开工时间：',
													width : 97
												},{
													name:'projectEndCheck.actualWorkDate',
													id:'actualWorkDate',
													xtype:'datefield',
													format:'Y-m-d',
													allowBlank:false,
													columnWidth : .5
												}, {
													text : '实际完工时间：',
													style : 'margin-left:10px;',
													width : 97
												}, {
													xtype : 'datefield',
													name : 'projectEndCheck.actualFinishDate',
													id : 'actualFinishDate',
													format:'Y-m-d',
													allowBlank:false,
													columnWidth : .5
												}
												]
									}, {
										xtype : 'container',
										layout : 'column',
										height : 25,
										defaults : {
											xtype : 'label',
											style : {
												margin : '0px 0px 3px 0px'
											}
										},
										items : [{
													text : '经办人：',
													width : 97
												}, {
													xtype : 'textfield',
													name : 'projectEndCheck.applyUser.fullname',
													id : 'projectEndCheck.applyUser.fullname',
													readOnly : true,
													style : "background:#F0F0F0;",
													columnWidth:1,
													value:__currentUser
												},{
													xtype:'hidden',
													name:'projectEndCheck.applyUser.userId',
													id:'projectEndCheck.applyUser.userId',
													value:__currentUserId
												}]
									}
							]
						});
				// 加载表单对应的数据
				this.initData();
				// 初始化功能按钮
				this.buttons = [{
							text : '保存',
							iconCls : 'btn-save',
							hidden:!(isGranted("_ProjectEndCheckAdd")||isGranted("_ProjectEndCheckEdit")) ,
							handler : this.save.createCallback(this.formPanel,
									this)
						}, {
							text : '重置',
							iconCls : 'btn-reset',
							hidden:!(isGranted("_ProjectEndCheckAdd")||isGranted("_ProjectEndCheckEdit")) ,
							handler : this.reset.createCallback(this.formPanel)
						}, {
							text : '取消',
							iconCls : 'btn-cancel',
							handler : this.cancel.createCallback(this)
						}];
			},// end of the initcomponents
			/**
			 * 初始化数据
			 * 
			 * @param {}
			 *            formPanel
			 */
			initData : function() {
				// 加载表单对应的数据
				if (this.id != null && this.id != 'undefined') {
					this.formPanel.getForm().load({
						deferredRender : false,
						url : __ctxPath
								+ '/statistics/getProjectEndCheck.do?id='
								+ this.id,
						waitMsg : '正在载入数据...',
						success : function(form, action) {
							var jsonData = Ext.util.JSON
									.decode(action.response.responseText);
							var res = jsonData.data;
							if(!Ext.isEmpty(res.project)){
								Ext.getCmp("projectEndCheck.project.proName").setValue(res.project.proName);
								Ext.getCmp("projectEndCheck.project.proName").originalValue=res.project.proName;
								Ext.getCmp("projectEndCheck.project.id").setValue(res.project.id);
								Ext.getCmp("projectEndCheck.project.id").originalValue=res.project.id;
								Ext.getCmp("projectEndCheck.project.buildUnit").setValue(res.project.buildUnit);
								Ext.getCmp("projectEndCheck.project.buildUnit").originalValue=res.project.buildUnit;
								Ext.getCmp("projectEndCheck.project.proAddr").setValue(res.project.proAddr);
								Ext.getCmp("projectEndCheck.project.proAddr").originalValue=res.project.proAddr;
								Ext.getCmp("projectEndCheck.project.areaM").setValue(res.project.area);
								Ext.getCmp("projectEndCheck.project.areaM").originalValue=res.project.area;
								Ext.getCmp("projectEndCheck.project.proChargerUserName").setValue(res.project.proCharger);
								Ext.getCmp("projectEndCheck.project.proChargerUserName").originalValue=res.project.proCharger;
							}
							if(!Ext.isEmpty(res.engineeUser)){
								Ext.getCmp("projectEndCheck.qualityUser.fullname").setValue(res.qualityUser.fullname);
								Ext.getCmp("projectEndCheck.qualityUser.fullname").originalValue=res.qualityUser.fullname;
								Ext.getCmp("projectEndCheck.qualityUser.userId").setValue(res.qualityUser.userId);
								Ext.getCmp("projectEndCheck.qualityUser.userId").originalValue=res.qualityUser.userId;
							}
							if(!Ext.isEmpty(res.workUser)){
								Ext.getCmp("projectEndCheck.workUser.fullname").setValue(res.workUser.fullname);
								Ext.getCmp("projectEndCheck.workUser.fullname").originalValue=res.workUser.fullname;
								Ext.getCmp("projectEndCheck.workUser.userId").setValue(res.workUser.userId);
								Ext.getCmp("projectEndCheck.workUser.userId").originalValue=res.workUser.userId;
							}
							if(!Ext.isEmpty(res.safeUser)){
								Ext.getCmp("projectEndCheck.safeUser.fullname").setValue(res.safeUser.fullname);
								Ext.getCmp("projectEndCheck.safeUser.fullname").originalValue=res.safeUser.fullname;
								Ext.getCmp("projectEndCheck.safeUser.userId").setValue(res.safeUser.userId);
								Ext.getCmp("projectEndCheck.safeUser.userId").originalValue=res.safeUser.userId;
							}
							if(!Ext.isEmpty(res.preCalUser)){
								Ext.getCmp("projectEndCheck.preCalUser.fullname").setValue(res.preCalUser.fullname);
								Ext.getCmp("projectEndCheck.preCalUser.fullname").originalValue=res.preCalUser.fullname;
								Ext.getCmp("projectEndCheck.preCalUser.userId").setValue(res.preCalUser.userId);
								Ext.getCmp("projectEndCheck.preCalUser.userId").originalValue=res.preCalUser.userId;
							}
							if(!Ext.isEmpty(res.engineeUser)){
								Ext.getCmp("projectEndCheck.engineeUser.fullnameId").setValue(res.engineeUser.fullname);
								Ext.getCmp("projectEndCheck.engineeUser.fullnameId").originalValue=res.engineeUser.fullname;
								Ext.getCmp("projectEndCheck.engineeUser.userId").setValue(res.engineeUser.userId);
								Ext.getCmp("projectEndCheck.engineeUser.userId").originalValue=res.engineeUser.userId;
							}
							if(!Ext.isEmpty(res.applyUser)){
								Ext.getCmp("projectEndCheck.applyUser.fullname").setValue(res.applyUser.fullname);
								Ext.getCmp("projectEndCheck.applyUser.fullname").originalValue=res.applyUser.fullname;
								Ext.getCmp("projectEndCheck.applyUser.userId").setValue(res.applyUser.userId);
								Ext.getCmp("projectEndCheck.applyUser.userId").originalValue=res.applyUser.userId;
							}
							// Ext.getCmp("").originalValue=
						},
						failure : function(form, action) {
						}
					});
				}
			},
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
											.getCmp('ProjectEndCheckGrid');
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
									// window.close();
								}
							});
				}
			}// end of save

		});