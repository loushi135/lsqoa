/**
 * @author
 * @createtime
 * @class EndInnerCheckForm
 * @extends Ext.Window
 * @description EndInnerCheck表单
 */
EndInnerCheckForm = Ext.extend(Ext.Window, {
			// 内嵌FormPanel
			formPanel : null,
			// 构造函数
			constructor : function(_cfg) {
				Ext.applyIf(this, _cfg);
				// 必须先初始化组件
				this.initUIComponents();
				EndInnerCheckForm.superclass.constructor.call(this, {
							id : 'EndInnerCheckFormWin',
							layout : 'fit',
							items : this.formPanel,
							modal : true,
							width : 670,
							height : 420,
							maximizable : true,
							title : '[完工内检评审表]详细信息',
							buttonAlign : 'center',
							buttons : this.buttons
						});
			},// end of the constructor
			// 初始化组件
			initUIComponents : function() {
				var _url = __ctxPath + '/system/listWorkDepartment.do?opt=appUser';
				var depSelector = new TreeSelector('endInnerCheck.project.areaM', _url, '',
						'endInnerCheck.project.area', false, 222,'endInnerCheck.project.area');
				Ext.getCmp("endInnerCheck.project.areaMTree").on("click", function(g) {
					Ext.getCmp("endInnerCheck.project.areaId").setValue(Ext.getCmp("endInnerCheck.project.areaM").id);
				});
				this.formPanel = new Ext.FormPanel({
							layout : 'form',
							trackResetOnLoad : true,
							bodyStyle : 'padding:10px 10px 10px 10px',
							border : false,
							url : __ctxPath
									+ '/statistics/saveEndInnerCheck.do',
							id : 'EndInnerCheckForm',
							defaults : {
								anchor : '98%,98%'
							},
							defaultType : 'textfield',
							items : [{
										name : 'endInnerCheck.id',
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
													name:'endInnerCheck.project.proName',
													id:'endInnerCheck.project.proName',
													xtype:'textfield',
													emptyText:'请选择项目',
													editable:true,
													readOnly:false,
													allowBlank:false,
													columnWidth : 1,
													listeners:{
														'focus':function(){
															ProjectNewSelector.getView(function(proId,proName,proNo,contractId,contractNo,contractAmount,proAreaId,proArea,proChargerUserId,proChargerUserName){
																Ext.getCmp("endInnerCheck.project.proName").setValue(proName);
																Ext.getCmp("endInnerCheck.project.id").setValue(proId);
																Ext.getCmp("endInnerCheck.contract.contractAmount").setValue(contractAmount);
																Ext.getCmp('endInnerCheck.project.proChargerUserName').setValue(proChargerUserName);
																if(!Ext.isEmpty(proArea)&&Ext.isEmpty(proAreaId)){
																	var url = __ctxPath + '/system/getDeptByNameDepartment.do'
																	var params = "depName="+proArea;
																	var data = ajaxSyncCall(url,params).data;
																	if(!Ext.isEmpty(data)){
																		Ext.getCmp("endInnerCheck.project.areaId").setValue(data.depId);
																		Ext.getCmp("endInnerCheck.project.areaM").setValue(data.depName);
																	}
																}else{
																	Ext.getCmp("endInnerCheck.project.areaId").setValue(proAreaId);
																	Ext.getCmp("endInnerCheck.project.areaM").setValue(proArea);
																}
																if(!Ext.isEmpty(proId)){
																	var url = __ctxPath + '/statistics/getProjectNew.do';
																	var params = "id="+proId;
																	var data = ajaxSyncCall(url,params).data;
																	Ext.getCmp('endInnerCheck.project.buildUnit').setValue(data.buildUnit);
																	Ext.getCmp('endInnerCheck.project.proAddr').setValue(data.proAddr);
																}
															},true,true).show();
														}
													}
												},{
													xtype:'hidden',
													name:'endInnerCheck.project.id',
													id:'endInnerCheck.project.id'
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
													name:'endInnerCheck.project.buildUnit',
													id:'endInnerCheck.project.buildUnit',
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
													name:'endInnerCheck.project.proAddr',
													id:'endInnerCheck.project.proAddr',
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
//													name:'endInnerCheck.project.area',
//													id:'endInnerCheck.project.area',
//													xtype:'textfield',
//													style : "background:#F0F0F0;",
//													readOnly:true,
//													allowBlank:true,
//													columnWidth : .5
//												}
												,{
													xtype:'hidden',
													name:'areaId',
													id:'endInnerCheck.project.areaId'
												}, {
													text : '项目经理：',
													style : 'margin-left:10px;',
													width : 97
												}, {
													xtype:'textfield',
													name : 'proChargerUserName',
													id : 'endInnerCheck.project.proChargerUserName',
													readOnly : true,
													allowBlank:false,
													columnWidth : 1,
													listeners : {
														'focus' : function() {
															UserSelector.getView(function(n,l,mobile) {
																		var projectCharger = Ext.getCmp("endInnerCheck.project.proChargerUserName");
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
													text:'合同造价：',
													width : 97
												},{
													name:'endInnerCheck.contract.contractAmount',
													id:'endInnerCheck.contract.contractAmount',
													xtype:'textfield',
													readOnly:true,
													style : "background:#F0F0F0;",
													allowBlank:true,
													columnWidth : .5
												}, {
													text : '内检时间：',
													style : 'margin-left:10px;',
													width : 97
												}, {
													xtype : 'datefield',
													id : 'checkTime',
													name : 'endInnerCheck.checkTime',
													format:'Y-m-d',
													allowBlank:false,
													columnWidth : .5
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
													text:'检查人：',
													width : 97
												},{
													name:'endInnerCheck.checkUser.fullname',
													id:'endInnerCheck.checkUser.fullname',
													xtype:'textfield',
													emptyText:'请选择检查人',
													readOnly:true,
													allowBlank:false,
													columnWidth : .5,
													listeners:{
														'focus' : function() {
															UserSelector.getView(function(n,l,mobile) {
																				var userId = Ext.getCmp("endInnerCheck.checkUser.userId");
																				var fullname = Ext.getCmp("endInnerCheck.checkUser.fullname");
																				userId.setValue(n);
																				fullname.setValue(l);
																			},true)
																	.show()
														}
													}
												},{
													xtype:'hidden',
													name:'endInnerCheck.checkUser.userId',
													id:'endInnerCheck.checkUser.userId'
												}, {
													text : '内检评份：',
													style : 'margin-left:10px;',
													width : 97
												}, {
													xtype : 'numberfield',
													name : 'endInnerCheck.score',
													id : 'score',
													allowBlank:false,
													maxLength:9,
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
													text : '客户满意度：',
													width : 97
												}, {
													xtype : 'textfield',
													name : 'endInnerCheck.customerSatisfy',
													id : 'customerSatisfy',
													maxLength : 64,
													allowBlank:false,
													columnWidth:.5
												}, {
													text : '内检结论：',
													style : 'margin-left:10px;',
													width : 97
												}, {
													xtype : 'combo',
													hiddenName : 'endInnerCheck.conclusion',
													id : 'conclusion',
													mode : 'local',
													valueField : "itemValue",
													displayField : "itemValue",
													triggerAction : "all",
													store : new Ext.data.JsonStore({
																baseParams : {
																	"Q_itemName_S_EQ" : '内检结论'
																},
																autoLoad : true,
																root : "result",
																totalProperty : "totalCounts",
																url : __ctxPath
																		+ "/system/listDictionary.do",
																fields : ["dicId", "itemName",
																		"itemValue"]
															}),
													editable : false,
													allowBlank : false,
													columnWidth:.5
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
													text : '奖罚建议：',
													width : 97
												}, {
													xtype : 'combo',
													hiddenName : 'endInnerCheck.rewardOrPunish',
													id : 'rewardOrPunish',
													mode : 'local',
													valueField : "itemValue",
													displayField : "itemValue",
													triggerAction : "all",
													store : new Ext.data.JsonStore({
																baseParams : {
																	"Q_itemName_S_EQ" : '奖罚建议'
																},
																autoLoad : true,
																root : "result",
																totalProperty : "totalCounts",
																url : __ctxPath
																		+ "/system/listDictionary.do",
																fields : ["dicId", "itemName",
																		"itemValue"]
															}),
													editable : false,
													allowBlank : false,
													columnWidth:1
												}]
									}, {
										xtype : 'container',
										autoHeight : true,
										layout : 'column',
										autoWidth : true,
										defaultType : 'label',
										style : 'padding-left:0px;padding-bottom:5px;',
										items : [{
													text : '附件:',
													width : 87,
													style : "margin-top:15px;"
												}, {
													xtype : 'hidden',
													name : 'attachIds',
													id : 'endInnerCheck.attachIds'
												}, {
													xtype : 'hidden',
													name : 'attachFiles',
													id : 'endInnerCheck.attachFiles'
												}, {
													xtype : 'panel',
													id : 'endInnerCheck.displayAttach',
													columnWidth : 1,
													height : 65,
													frame : false,
													autoScroll : true,
													style : 'padding-left:10px;padding-top:0px;',
													html : ''
												}, {
													xtype : 'button',
													iconCls : 'btn-upload',
													text : '上传',
													handler : function() {
														var dialog = App.createUploadDialog({
															file_cat : 'flow/endInnerCheck',
															callback : function(data) {
																var attachFiles = Ext.getCmp("endInnerCheck.attachFiles");
																var fileIds = Ext.getCmp('endInnerCheck.attachIds');
																var display = Ext.getCmp('endInnerCheck.displayAttach');
																display.body.update('');
																attachFiles.setValue('');
																fileIds.setValue('');
																for (var i = 0; i < data.length; i++) {
																	if (fileIds.getValue() != '') {
																		fileIds.setValue(fileIds.getValue() + ',');
																		attachFiles.setValue(attachFiles.getValue() + ',');
																	}
																	attachFiles.setValue(attachFiles.getValue() + data[i].filepath + ":" + data[i].filename.replace(/\s+/g, ""));
																	fileIds.setValue(fileIds.getValue() + data[i].fileId);
																	Ext.DomHelper.append(display.body, '<span><a href="#" onclick="FileAttachDetail.show(' + data[i].fileId + ')">'
																	+ data[i].filename.replace(/\s+/g, "") + '</a>&nbsp;|&nbsp;</span>');
																}
															},
															permitted_max_size : 1024 * 1024 * 20,
															permitted_extensions_size : [{'type':['jpg','png','gif','bmp','jpeg'],'MaxSize':1024*800}]
														});
														dialog.show(this);
													}
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
													text : '经办人：',
													width : 97
												}, {
													xtype : 'textfield',
													name : 'endInnerCheck.applyUser.fullname',
													id : 'endInnerCheck.applyUser.fullname',
													readOnly : true,
													style : "background:#F0F0F0;",
													columnWidth:1,
													value:__currentUser
												},{
													xtype:'hidden',
													name:'endInnerCheck.applyUser.userId',
													id:'endInnerCheck.applyUser.userId',
													value:__currentUserId
												}]
									}]}
			);
				// 加载表单对应的数据
				this.initData();
				// 初始化功能按钮
				this.buttons = [{
							text : '保存',
							iconCls : 'btn-save',
							hidden:!(isGranted("_EndInnerCheckAdd")||isGranted("_EndInnerCheckEdit")) ,
							handler : this.save.createCallback(this.formPanel,
									this)
						}, {
							text : '重置',
							iconCls : 'btn-reset',
							hidden:!(isGranted("_EndInnerCheckAdd")||isGranted("_EndInnerCheckEdit")) ,
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
						url : __ctxPath + '/statistics/getEndInnerCheck.do?id='
								+ this.id,
						waitMsg : '正在载入数据...',
						success : function(form, action) {
							var jsonData = Ext.util.JSON
									.decode(action.response.responseText);
							var res = jsonData.data;
							if(!Ext.isEmpty(res.project)){
								Ext.getCmp("endInnerCheck.project.proName").setValue(res.project.proName);
								Ext.getCmp("endInnerCheck.project.proName").originalValue=res.project.proName;
								Ext.getCmp("endInnerCheck.project.id").setValue(res.project.id);
								Ext.getCmp("endInnerCheck.project.id").originalValue=res.project.id;
								Ext.getCmp("endInnerCheck.project.buildUnit").setValue(res.project.buildUnit);
								Ext.getCmp("endInnerCheck.project.buildUnit").originalValue=res.project.buildUnit;
								Ext.getCmp("endInnerCheck.project.proAddr").setValue(res.project.proAddr);
								Ext.getCmp("endInnerCheck.project.proAddr").originalValue=res.project.proAddr;
								Ext.getCmp("endInnerCheck.project.areaM").setValue(res.project.area);
								Ext.getCmp("endInnerCheck.project.areaM").originalValue=res.project.area;
								Ext.getCmp("endInnerCheck.project.proChargerUserName").setValue(res.project.proCharger);
								Ext.getCmp("endInnerCheck.project.proChargerUserName").originalValue=res.project.proCharger;
								if(!Ext.isEmpty(res.project.contract)){
									Ext.getCmp("endInnerCheck.contract.contractAmount").setValue(res.project.contract.sumPrice);
									Ext.getCmp("endInnerCheck.contract.contractAmount").originalValue=res.project.contract.sumPrice;
								}
							}
							if(!Ext.isEmpty(res.checkUser)){
								Ext.getCmp("endInnerCheck.checkUser.fullname").setValue(res.checkUser.fullname);
								Ext.getCmp("endInnerCheck.checkUser.fullname").originalValue=res.checkUser.fullname;
								Ext.getCmp("endInnerCheck.checkUser.userId").setValue(res.checkUser.userId);
								Ext.getCmp("endInnerCheck.checkUser.userId").originalValue=res.checkUser.userId;
							}
							var af = res.fileAttachs;
							var filePanel = Ext.getCmp('endInnerCheck.displayAttach');
							var fileIds = Ext.getCmp("endInnerCheck.attachIds");
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
											.getCmp('EndInnerCheckGrid');
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