/**
 * @author
 * @createtime
 * @class TeamInStockForm
 * @extends Ext.Window
 * @description TeamInStock表单
 */
TeamInStockForm = Ext.extend(Ext.Window, {
	// 内嵌FormPanel
	formPanel : null,
	// 构造函数
	constructor : function(_cfg) {
		Ext.applyIf(this, _cfg);
		// 必须先初始化组件
		this.initUIComponents();
		TeamInStockForm.superclass.constructor.call(this, {
					id : 'TeamInStockFormWin',
					layout : 'fit',
					items : this.formPanel,
					modal : true,
					width : 650,
					autoHeight : true,
					maximizable : true,
					title : '[班组]详细信息',
					buttonAlign : 'center',
					buttons : this.buttons
				});
	},// end of the constructor
	// 初始化组件
	initUIComponents : function() {
		this.formPanel = new Ext.FormPanel({
					layout : 'form',
					trackResetOnLoad : true,
					bodyStyle : 'padding:10px 10px 10px 10px',
					border : false,
					url : __ctxPath + '/customer/saveSuppliersAssess.do',
					id : 'TeamInStockForm',
					defaults : {
						anchor : '98%,98%'
					},
					width : 600,
					autoHeight : true,
					padding : "10px",
					defaultType : 'textfield',
					items : [{
								name : 'suppliersAssess.suppliersId',
								id : 'suppliersId',
								xtype : 'hidden',
								value : this.suppliersId == null ? '' : this.suppliersId
							},
							{
								xtype:'hidden',
								name:'suppliersAssess.delFlag',
								id:'delFlag',
								value:0
							},
							{
								xtype:'hidden',
								name:'suppliersAssess.type',
								id:'type',
								value:1
							},
							{
								xtype:'hidden',
								name:'suppliersAssess.suppliersNo',
								id:'suppliersNo'
							},{
							xtype : "container",
							layout : "column",
							height : 25,
							defaults : {
								xtype : 'label',
								style : {
									margin : '0px 0px 3px 0px'
								}
							},
							items : [{
										text : '单位名称：',
										width : 85
									}, {
										xtype : 'textfield',
										name : 'suppliersAssess.suppliersName',
										id:'suppliersName',
										allowBlank : false,
										width : 200
									}, {
										text : '推荐人：',
										width : 80
									}, {
										xtype : 'textfield',
										name : 'suppliersAssess.inviteUserName',
										id:'inviteUserName',
										allowBlank : false,
										width : 200
									}]
						}, {
							xtype : "container",
							layout : "column",
							height : 25,
							defaults : {
								xtype : 'label',
								style : {
									margin : '0px 0px 3px 0px'
								}
							},
							items : [{
										text : '法定代表人：',
										width : 85
									}, {
										xtype : 'textfield',
										name : 'suppliersAssess.legalRepresentative',
										id:'legalRepresentative',
										allowBlank : false,
										width : 200
									},{
										text : '联系人：',
										width : 80
									}, {
										xtype : 'textfield',
										name : 'suppliersAssess.supplierContacter',
										id:'supplierContacter',
										allowBlank : false,
										width : 200
									}]
						}, {
							xtype : "panel",
							layout : "column",
							border : false,
							unstyled : false,
							defaults : {
								xtype : 'label',
								style : {
									margin : '0px 0px 3px 0px'
								}
							},
							items : [{
										text : '通讯地址：',
										width : 85
									},{
										xtype : 'textfield',
										name : 'suppliersAssess.supplierAddress',
										id:'supplierAddress',
										allowBlank : false,
										width : 200
									},{
										text : '联系人电话：',
										width : 80
									}, {
										xtype : 'textfield',
										name : 'suppliersAssess.supplierPhone',
										id:'supplierPhone',
										allowBlank : false,
										width : 200
									}]
						}, {
							xtype : "panel",
							layout : "column",
							border : false,
							unstyled : false,
							defaults : {
								xtype : 'label',
								style : {
									margin : '0px 0px 3px 0px'
								}
							},
							items : [{
										text : '注册资金：',
										width : 85
									},{
										xtype : 'textfield',
										name : 'suppliersAssess.registeredCapital',
										id:'registeredCapital',
										allowBlank : false,
										width : 200
									}, {
										text : '邮      编：',
										width : 80
									},{
										xtype : 'textfield',
										name : 'suppliersAssess.zip',
										id:'zip',
										allowBlank : false,
										width : 200
									}]
						},{xtype : "panel",
							layout : "column",
							border : false,
							unstyled : false,
							defaults : {
								xtype : 'label',
								style : {
									margin : '0px 0px 3px 0px'
								}
							},
							items : [{
										text : '开户银行：',
										width : 85
									},{
										xtype : 'textfield',
										name : 'suppliersAssess.suppliersbank',
										id:'suppliersbank',
										allowBlank : true,
										width : 200
									}, {
										text : '开户账号：',
										width : 80
									},{
										xtype : 'textfield',
										name : 'suppliersAssess.bankAccount',
										id:'bankAccount',
										allowBlank : true,
										width : 200
									}]
						}, {
							xtype : "panel",
							layout : "column",
							border : false,
							unstyled : false,
							defaults : {
								xtype : 'label',
								style : {
									margin : '0px 0px 3px 0px'
								}
							},
							items : [{
										text : '合作类型：',
										width : 85
									}, {
										xtype : 'combo',
										name : 'suppliersAssess.cooperateType',
										id : 'cooperateType',
										allowBlank : false,
										width : 195,
										editable : false,
										triggerAction : 'all',
										mode : 'local',
										store : ['战略','长期','临时','待考察'],
										listeners:{
											select:function(field){
											      var cooperateType = field.getValue();
											      if(cooperateType=='临时'){
											      	 Ext.getCmp('suppliersAssess.projectContainer').show();
											      	 Ext.getCmp('suppliersAssess.project.proName').allowBlank=false;
											      }else{
											      	 Ext.getCmp('suppliersAssess.projectContainer').hide();
											      	 Ext.getCmp('suppliersAssess.project.proName').setValue('');
											      	 Ext.getCmp('suppliersAssess.project.proName').allowBlank=true;
											      	 Ext.getCmp('suppliersAssess.project.id').setValue('');
											      }
											}
										}
									}, {
										text : '所在省市：',
										width : 80
									}, {
										xtype : 'textfield',
										id : 'provinceCity',
										name : 'provinceCity',
										allowBlank : false,
										width : 200,
										emptyText:'请选择省市',
										editable : false,
										listeners : {
											'focus' : function() {
												CitySelector.getView(function(cityId, cityName, provinceId, provinceName) {
													if(!Ext.isEmpty(cityId)&&!Ext.isEmpty(provinceId)){
														Ext.getCmp('provinceCity').setValue(provinceName + '/' + cityName);
														Ext.getCmp('suppliersAssess.province.provinceId').setValue(provinceId);
														Ext.getCmp('suppliersAssess.city.cityId').setValue(cityId);
													}
												}, true).show()
											}
										}
									},
									{
										xtype:'hidden',
										name:'suppliersAssess.province.provinceId',
										id:'suppliersAssess.province.provinceId'
									},
									{
										xtype:'hidden',
										name:'suppliersAssess.city.cityId',
										id:'suppliersAssess.city.cityId'
									}]
						}, {
							xtype : "container",
							layout : "column",
							id:'suppliersAssess.projectContainer',
							defaults : {
								xtype : 'label',
								style : {
									margin : '0px 0px 3px 0px'
								}
							},
							items : [{
										text : '项目名称：',
										width : 85
									},{
										xtype : 'textfield',
										name : 'suppliersAssess.project.proName',
										id:'suppliersAssess.project.proName',
										width : 475, 
										emptyText:'请选择项目',
										listeners:{
											'focus':function(){
												ProjectNewSelector.getView(function(proId,proName,proNo,contractId,contractNo,contractAmount,proAreaId,proArea){
													Ext.getCmp("suppliersAssess.project.id").setValue(proId);
													Ext.getCmp("suppliersAssess.project.proName").setValue(proName);
												},true,true).show();
											}
										}
									},{
										xtype : 'hidden',
										name : 'suppliersAssess.project.id',
										id:'suppliersAssess.project.id',
										listeners:{
											afterrender:function(){
												Ext.getCmp('suppliersAssess.projectContainer').hide();
											}
										}
									}]
						}, {
							xtype : "panel",
							layout : "column",
							border : false,
							unstyled : false,
							defaults : {
								xtype : 'label',
								style : {
									margin : '0px 0px 3px 0px'
								}
							},
							items : [{
										text : '办公电话：',
										width : 85
									},{
										xtype : 'textfield',
										name : 'suppliersAssess.officeTel',
										id:'officeTel',
										allowBlank : false,
										width : 130
									}, {
										text : '传真：',
										width : 40
									},{
										xtype : 'textfield',
										name : 'suppliersAssess.fax',
										id:'fax',
										allowBlank : false,
										width : 130
									}, {
										text : 'email：',
										width : 40
									},{
										xtype : 'textfield',
										name : 'suppliersAssess.email',
										id:'email',
										allowBlank : false,
										width : 145
									}]
						},{
							xtype : "panel",
							layout : "column",
							border : false,
							unstyled : false,
							defaults : {
								xtype : 'label',
								style : {
									margin : '0px 0px 3px 0px'
								}
							},
							items : [{
										text : '固定员工人数：',
										width : 85
									},{
										xtype : 'numberfield',
										name : 'suppliersAssess.fixedStaffNum',
										id:'fixedStaffNum',
										allowBlank : false,
										width : 70
									}, {
										text : '其中专业技术安装调试人员数：',
										width : 169
									},{
										xtype : 'numberfield',
										name : 'suppliersAssess.techDebugNum',
										id:'techDebugNum',
										allowBlank : false,
										width : 63
									}, {
										text : '其中班组长人数：',
										width : 98
									},{
										xtype : 'numberfield',
										name : 'suppliersAssess.leaderNum',
										id:'leaderNum',
										allowBlank : false,
										width : 86
									}]
						},{
							xtype : "panel",
							layout : "column",
							border : false,
							unstyled : false,
							defaults : {
								xtype : 'label',
								style : {
									margin : '0px 0px 3px 0px'
								}
							},
							items : [{
										text : '持有效电工证工人数：',
										width : 85
									},{
										xtype : 'numberfield',
										name : 'suppliersAssess.validElecNum',
										id:'validElecNum',
										allowBlank : false,
										width : 70
									}, {
										text : '持有效焊工证工人数：',
										width : 120
									},{
										xtype : 'numberfield',
										name : 'suppliersAssess.validWelderNum',
										id:'validWelderNum',
										allowBlank : false,
										width : 113
									}, {
										text : '持资格证书人数：',
										width :98
									},{
										xtype : 'numberfield',
										name : 'suppliersAssess.validQualifyNum',
										id:'validQualifyNum',
										allowBlank : false,
										width : 85
									}]
						},{
							xtype : "panel",
							layout : "column",
							border : false,
							unstyled : false,
							defaults : {
								xtype : 'label',
								style : {
									margin : '0px 0px 3px 0px'
								}
							},
							items : [{
										text : '高峰可上人员：',
										width : 85
									},{
										xtype : 'numberfield',
										name : 'suppliersAssess.peakNum',
										id:'peakNum',
										allowBlank : false,
										width : 200
									}, {
										text : '工人主要来自地区：',
										width : 110
									},{
										xtype : 'textfield',
										name : 'suppliersAssess.mainArea',
										id:'mainArea',
										allowBlank : false,
										width : 170
									}]
						},{
							xtype : "panel",
							layout : "column",
							border : false,
							unstyled : false,
							defaults : {
								xtype : 'label',
								style : {
									margin : '0px 0px 3px 0px'
								}
							},
							items : [{
										text : '工程产值',
										width : 80
									},{
										text : '前年：',
										width : 40
									},{
										xtype : 'textfield',
										name : 'suppliersAssess.previousYearOutput',
										id:'previousYearOutput',
										allowBlank : false,
										width : 200
									}, {
										text : '去年：',
										width : 45
									},{
										xtype : 'textfield',
										name : 'suppliersAssess.lastYearOutput',
										id:'lastYearOutput',
										allowBlank : false,
										width : 200
									}]
						},{
							border : true,
							autoScroll : true,
							//bodyStyle: 'padding:10px 10px 10px 10px;',
							autoHeight : true,
							padding : "10px",
							xtype : "container",
							layout : "column",
							defaults : {
								xtype : 'label',
								anchor : '98%,98%'
							},
							items : [{
										text : '主要骨干姓名及联系电话擅长系统信息:',
										width : 105
									},{
										xtype : 'textarea',
										name : 'suppliersAssess.mainInfo',
										id:'mainInfo',
										width : 450,
										allowBlank : false,
										autoScroll : true
									}]
						},{
										xtype : 'container',
										autoHeight : true,
										layout : 'column',
										autoWidth : true,
										defaultType : 'label',
										style : 'padding-left:0px;padding-bottom:5px;margin-top:3px;',
										items : [{
													text : '附件:',
													width:95,
													style : "margin-top:15px;"
												},{
													xtype : 'hidden',
													name : 'suppliersAssess.attachIds',
													id : 'suppliersAssess.attachIds'
												},{
													xtype : 'hidden',
													name : 'suppliersAssess.attachFiles',
													id : 'suppliersAssess.attachFiles'
												},{
													xtype : 'panel',
													id:'suppliersAssess.displayAttach',
													columnWidth:.95,
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
																				file_cat : 'flow/suppliersAssess',
																				callback : function(data) {
																					var attachFiles = Ext.getCmp("suppliersAssess.attachFiles");
																					var fileIds = Ext.getCmp('suppliersAssess.attachIds');
																					var display = Ext.getCmp('suppliersAssess.displayAttach');
																					display.body.update('');
																					attachFiles.setValue('');
																					fileIds.setValue('');
																					for (var i = 0; i < data.length; i++) {
																						if (fileIds.getValue() != '') {
																							fileIds.setValue(fileIds.getValue()+ ',');
																							attachFiles.setValue(attachFiles.getValue()+ ',');
																						}
																						attachFiles.setValue(attachFiles.getValue()+data[i].filepath+":"+data[i].filename.replace(/\s+/g,""));
																						fileIds.setValue(fileIds.getValue()+data[i].fileId);
																						Ext.DomHelper.append(display.body,'<span><a href="#" onclick="FileAttachDetail.show('+data[i].fileId+')">'+data[i].filename.replace(/\s+/g,"")+'</a>&nbsp;|&nbsp;</span>');
																					}
																				},
																				permitted_max_size :1024*1024*20
																			});
															dialog.show(this);
													}
												}]
									},{
						border : true,
							autoScroll : true,
							//bodyStyle: 'padding:10px 10px 10px 10px;',
							autoHeight : true,
							padding : "10px",
							xtype : "container",
							layout : "column",
							defaults : {
								xtype : 'label',
								anchor : '98%,98%'
							},
							items : [{
										text : '推荐原因：',
										width : 105
									},{
										xtype : 'textarea',
										name : 'suppliersAssess.recommendReason',
										id:'recommendReason',
										allowBlank : false,
										autoScroll : true,
										columnWidth:.98
									}]
						},{
							xtype : "panel",
							layout : "column",
							border : false,
							unstyled : false,
							defaults : {
								xtype : 'label',
								style : {
									margin : '5px 0px 0px 0px'
								}
							},
							items : [
									{
										text : '经办人：',
										width : 105
									},{
										xtype : 'textfield',
										name : 'suppliersAssess.applyUser.fullname',
										id:'suppliersAssess.applyUser.fullname',
										allowBlank : false,
										readOnly:true,
										width : 455,
										value:__currentUser
									},
									{
										xtype : 'hidden',
										name : 'suppliersAssess.applyUser.userId',
										id:'suppliersAssess.applyUser.userId',
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
					handler : this.save.createCallback(this.formPanel, this)
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
	 * 初始化数据
	 * 
	 * @param {}
	 *            formPanel
	 */
	initData : function() {
		// 加载表单对应的数据
		if (this.suppliersId != null && this.suppliersId != 'undefined') {
			this.formPanel.getForm().load({
				deferredRender : false,
				url : __ctxPath + '/customer/getSuppliersAssess.do?suppliersId='+ this.suppliersId,
				waitMsg : '正在载入数据...',
				success : function(form, action) {
					var jsonData = Ext.util.JSON
							.decode(action.response.responseText);
					var res = jsonData.data;
					
					if(res.province!=null&&res.city!=null){
						Ext.getCmp("provinceCity").setValue(res.province.provinceName+"/"+res.city.cityName);
						Ext.getCmp("provinceCity").originalValue = res.province.provinceName+"/"+res.city.cityName;
						Ext.getCmp("suppliersAssess.province.provinceId").setValue(res.province.provinceId);
						Ext.getCmp("suppliersAssess.province.provinceId").originalValue = res.province.provinceId;
						Ext.getCmp("suppliersAssess.city.cityId").setValue(res.city.cityId);
						Ext.getCmp("suppliersAssess.city.cityId").originalValue = res.city.cityId;
					}
					Ext.getCmp('suppliersName').el.dom.readOnly = true;
					Ext.getCmp('suppliersName').purgeListeners();
					if(!Ext.isEmpty(res.project)){
						Ext.getCmp('suppliersAssess.projectContainer').show();
						Ext.getCmp("suppliersAssess.project.proName").setValue(res.project.proName);
						Ext.getCmp("suppliersAssess.project.proName").originalValue = res.project.proName;
						Ext.getCmp("suppliersAssess.project.id").setValue(res.project.id);
						Ext.getCmp("suppliersAssess.project.id").originalValue = res.project.id;
					}
					
					if(!Ext.isEmpty(res.applyUser)){
						Ext.getCmp('suppliersAssess.applyUser.userId').setValue(res.applyUser.userId);
						Ext.getCmp('suppliersAssess.applyUser.userId').originalValue = res.applyUser.userId;
						Ext.getCmp('suppliersAssess.applyUser.fullname').setValue(res.applyUser.fullname);
						Ext.getCmp('suppliersAssess.applyUser.fullname').originalValue = res.applyUser.fullname;
					}
					var af = res.fileAttachs;
					var filePanel = Ext.getCmp('suppliersAssess.displayAttach');
					var fileIds = Ext.getCmp("suppliersAssess.attachIds");
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
							var gridPanel = Ext.getCmp('TeamInStockGrid');
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