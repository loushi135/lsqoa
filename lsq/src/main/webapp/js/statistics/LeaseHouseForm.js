/**
 * @author
 * @createtime
 * @class LeaseHouseForm
 * @extends Ext.Window
 * @description LeaseHouse表单
 */
LeaseHouseForm = Ext.extend(Ext.Window, {
			// 内嵌FormPanel
			formPanel : null,
			// 构造函数
			constructor : function(_cfg) {
				Ext.applyIf(this, _cfg);
				// 必须先初始化组件
				this.initUIComponents();
				LeaseHouseForm.superclass.constructor.call(this, {
							id : 'LeaseHouseFormWin',
							layout : 'fit',
							items : this.formPanel,
							modal : true,
							width : 600,
							height : 420,
							frame : true,
							title : '租房详细信息',
							buttonAlign : 'center',
							buttons : this.buttons
						});
			},// end of the constructor
			// 初始化组件
			initUIComponents : function() {
				this.formPanel = new Ext.FormPanel({
							layout : 'form',
							width:50,
							trackResetOnLoad:true,
							bodyStyle : 'padding:10px 10px 10px 10px',
							border : false,
							url : __ctxPath + '/statistics/saveLeaseHouse.do',
							id : 'LeaseHouseForm',
							defaults : {
								anchor : '98%,98%'
							},
							defaultType : 'textfield',
							items : [{
							xtype : 'container',
							style : 'padding-left:0px;margin-bottom:4px',
							layout : 'column',
					items : [{
								xtype : 'label',
								text : '报告人:'
							},{
							   xtype:'hidden',
							   name:'leaseHouse.id',
							   value:this.id,
							   id:'id'
							 },{
								xtype:'hidden',
								name:'leaseHouse.processRunId',
								value:-1
							},{
								xtype : 'textfield',
								width : '45%',
								name : 'leaseHouse.reporter',
								allowBlank : false,
								readOnly : true,
								value : __currentUser,
								style:"background:#F0F0F0;margin-left:12px;"
							}, {
								xtype : 'label',
								text : '所属部门:',
								style : 'padding-left:3px'
							}, {
								xtype : 'textfield',
								readOnly : true,
								style:"background:#F0F0F0;",
								width : '25%',
								allowBlank : false,
								name : 'leaseHouse.reporterDepatment',
								value : __currentUserDept
							}]
				}, {
					xtype : 'container',
					style : 'padding-left:0px;margin-bottom:4px',
					layout : 'column',
					items : [{
								xtype : 'label',
								text : '所在公司:'
							}, {
								xtype : 'textfield',
								readOnly : true,
								style:"background:#F0F0F0;",
								width : '45%',
								name : 'leaseHouse.reporterCompanyName',
								allowBlank : false,
								value : __companyName
							}, {
								xtype : 'label',
								text : '类别:',
								style : 'padding-left:3px;margin-right:5px;'
							}, {
								xtype : "combo",
								width : '26%',
								id:'sort',
								selectOnFocus : true,
								emptyText : '---请选择---',
								forceSelection : true,
								hiddenName : "leaseHouse.sort",
								allowBlank : false,
								triggerAction : 'all',
								editable : false,
								store : ['项目','其他'],
								listeners : {
									select : function() {
										if (Ext.getCmp("sort").getValue() == '项目') {
											Ext.getCmp("itemName").enable();
											Ext.getCmp("projectItem").show();
										} else {
											Ext.getCmp("projectId").setValue("");
											Ext.getCmp("itemName").reset();
											Ext.getCmp("itemName").disable();
											Ext.getCmp("projectItem").hide();
										}
									}
									
								}
							}]
				}, {
					xtype : 'container',
					layout : 'column',
					id:'projectItem',
					style:'margin-bottom:4px;',
					items : [{
								xtype : 'textfield',
								hidden:true,
								id : 'projectId',
								name : 'projectId'
								
							}, {
								xtype : 'label',
								text : '项目名称:'
							}, {
								xtype : 'textfield',
								width : '80%',
								readOnly:true,
							    disabled:true,
								maxLength : 20,
								allowBlank:false,
								id:'itemName',
								name : 'itemName',
								listeners : {
									
									'focus' : function() {
										ProjectNewSelector.getView(
												function(proId, proName, proNo,
														contractId, contractNo,
														contractAmount) {
													Ext.getCmp("itemName")
															.setValue(proName);
													Ext.getCmp("projectId")
															.setValue(proId);
												}, true).show();
									}
								}
							}]

				},{
					xtype : 'container',
					layout : 'column',
					style : 'padding-left:0px;margin-bottom:4px',
					items : [{
								xtype : 'label',
								text : '租期:',
								width : 54
							}, {
								xtype : 'datefield',
								id:'leaseStart',
								name :'leaseStart',
								format : 'Y-m',
								editable : false,
								columnWidth:.3,
								listeners:{
									select:function(field,date){
										var startDate = field.getValue();
										var endDate = Ext.getCmp("leaseEnd").getValue();
										if(endDate!=''){
											var months = months_between(endDate,startDate);
											Ext.getCmp("leaseLen").setValue(months+"个月");
											var monthlyRent = Ext.getCmp("monthlyRent").getValue();
											if(monthlyRent!=''){
												Ext.getCmp("yearRent").setValue(monthlyRent*months);;
											}
										}
									}
								}
							}, {
								xtype : 'label',
								style : 'padding-top:4px;',
								text : '--'
							}, {
								xtype : 'datefield',
								id : 'leaseEnd',
								name : 'leaseEnd',
								allowBlank : false,
								format : 'Y-m',
								editable : false,
								columnWidth:.3,
								listeners:{
									select:function(field,date){
										var endDate = field.getValue();
										var startDate = Ext.getCmp("leaseStart").getValue();
										if(startDate!=''){
											var months = months_between(endDate,startDate);
											Ext.getCmp("leaseLen").setValue(months+"个月");
											var monthlyRent = Ext.getCmp("monthlyRent").getValue();
											if(monthlyRent!=''){
												Ext.getCmp("yearRent").setValue(monthlyRent*months);;
											}
										}
									}
								}
							}, {
								xtype : 'label',
								text : '租期时长:',
								style : 'padding-left:3px;'
							}, {
								xtype : 'textfield',
								readOnly:true,
								id : 'leaseLen',
								name : 'leaseHouse.leaseLen',
								columnWidth:.275
							}]

				},{
					xtype : 'container',
					layout : 'column',
					style : 'padding-left:0px;margin-bottom:4px',
					items : [ {
								xtype : 'label',
								text : '月租金:',
								width : 54
							}, {
								xtype : 'numberfield',
								columnWidth:.4,
								allowBlank : false,
								id : 'monthlyRent',
								name:'leaseHouse.monthlyRent',
								regex:/^(([1-9]\d{0,9})|0)(\.\d{1,2})?$/,
								enableKeyEvents : true,
								listeners : {
									keyup : function() {
										var startDate = Ext.getCmp("leaseStart").getValue();
										var endDate = Ext.getCmp("leaseEnd").getValue();
										if(startDate!=''&&endDate!=''){
											var months = months_between(endDate,startDate);
											var monthlyRent = Ext.getCmp("monthlyRent").getValue();
											if(monthlyRent!=''){
												Ext.getCmp("yearRent").setValue(monthlyRent*months);
											}
										}
									}
								}
							}, {
								xtype : 'label',
								text : '租金总额:',
								columnWidth:.13,
								style : 'margin-left:3px'
							}, {
								xtype : 'numberfield',
								width : 97,
								columnWidth:.37,
								emptyText:'请填写月租金和租期',
								name : 'leaseHouse.yearRent',
								id : 'yearRent',
								readOnly : true
							}]

				}, {
					xtype : 'container',
					layout : 'column',
					style : 'padding-left:0px;margin-bottom:4px',
					border : false,
					items : [{
								xtype : 'label',
								text : '申请原因:'
							}, {
								xtype : 'textarea',
								id:'cause',
								name : 'leaseHouse.cause',
								allowBlank : false,
								maxLength : 200,
								height : 135,
								width : '80%'
							}]
				},{
					xtype : 'container',
					layout : 'column',
					defaultType : 'label',
					style : 'padding-left:0px;margin-bottom:4px',
					items : [{
								text : '附件:',
								width : 54
							},{
								xtype : 'hidden',
								name : 'leaseHouse.attachIds',
								id : 'leaseHouse.attachIds'
							},{
								xtype : 'hidden',
								name : 'leaseHouse.attachFiles',
								id : 'leaseHouse.attachFiles'
							},{
								xtype : 'panel',
								id:'leaseHouse.displayAttach',
								width : '72%',
								height: 65,
								frame:false,
								autoScroll:true,
								html : ''
							},{
								xtype : 'button',
								iconCls : 'btn-upload',
								text : '上传',
								handler : function() {
									var dialog = App.createUploadDialog({
															file_cat : 'flow/leaseHouse',
															callback : function(data) {
																Ext.getCmp('leaseHouse.displayAttach').body.update('');
																Ext.getCmp("leaseHouse.attachFiles").setValue('');
																Ext.getCmp('leaseHouse.attachIds').setValue('');
																var contractFile = Ext.getCmp("leaseHouse.attachFiles");
																var fileIds = Ext.getCmp('leaseHouse.attachIds');
																var display = Ext.getCmp('leaseHouse.displayAttach');
																for (var i = 0; i < data.length; i++) {
																	if (fileIds.getValue() != '') {
																		fileIds.setValue(fileIds.getValue()+ ',');
																		contractFile.setValue(contractFile.getValue()+ ',');
																	}
																	contractFile.setValue(contractFile.getValue()+data[i].filepath+":"+data[i].filename.replace(/\s+/g,""));
//																	contractFile.setValue(contractFile.getValue()+"<a href=javascript:window.location.href=\""+__ctxPath+"/file-Download?filePath=attachFiles/"+data[i].filepath+"&fileName="+data[i].filename.replace(/\s+/g,"")+"\">"+data[i].filename.replace(/\s+/g,"")+"</a>");
																	fileIds.setValue(fileIds.getValue()+data[i].fileId);
																	Ext.DomHelper.append(display.body,'<span><a href="#" onclick="FileAttachDetail.show('+data[i].fileId+')">'+data[i].filename.replace(/\s+/g,"")+'</a>&nbsp;|&nbsp;</span>');
																}
															},
															permitted_max_size :1024*1024*20
														});
										dialog.show(this);
								}
							}]
				}
				]
						});
				// 加载表单对应的数据
				if (this.id != null && this.id != 'undefined') {
					this.formPanel.getForm().load({
						deferredRender : false,
						url : __ctxPath + '/statistics/getLeaseHouse.do?id='
								+ this.id,
						waitMsg : '正在载入数据...',
						success : function(form, action) {
							var res = Ext.util.JSON.decode(action.response.responseText).data
							var project=res.project;
							if(project.length!=0||project.length!='undefined'){
							     Ext.getCmp("projectId").setValue(project.id);
							     Ext.getCmp("projectId").originalValue = project.id;
							     Ext.getCmp("itemName").setValue(project.proName);
							     Ext.getCmp("itemName").originalValue = project.proName;
							     Ext.getCmp("itemName").enable();
							}
							var attachId = res.attachIds;
							var attachFile = res.attachFiles;
							if(attachId!= null && attachId != 'undefined' && attachFile!= null && attachFile != 'undefined'){
								Ext.getCmp("leaseHouse.attachIds").setValue(res.attachIds);
								Ext.getCmp("leaseHouse.attachIds").originalValue = res.attachIds;
								Ext.getCmp("leaseHouse.attachFiles").setValue(res.attachFiles);
								Ext.getCmp("leaseHouse.attachFiles").originalValue = res.attachFiles;
								var ids = attachId.split(',');
								var files= attachFile.split(',');
								for(var i=0;i<ids.length;i++){
									if(files[i].lastIndexOf('<a href')!=-1){
										  Ext.DomHelper.append(Ext.getCmp('leaseHouse.displayAttach').body,'<span><a href="#" onclick="FileAttachDetail.show('+ids[i]+')">'+files[i].replace(/\s+/g,"")+'</a>&nbsp;|&nbsp;</span>');
									}else if(files[i].lastIndexOf(':')!=-1){
										  var fg = files[i].split(':');
										  Ext.DomHelper.append(Ext.getCmp('leaseHouse.displayAttach').body,'<span><a href="#" onclick="FileAttachDetail.show('+ids[i]+')">'+fg[1].replace(/\s+/g,"")+'</a>&nbsp;|&nbsp;</span>');
									}
								}
							}
						},
						failure : function(form, action) {
						}
								});
				}
				// 初始化功能按钮
				this.buttons = [{
									text : '保存',
									iconCls : 'btn-save',
									handler :this.save.createCallback(this.formPanel,this)
				
				                },{
									 text : '重置',
									 iconCls : 'btn-reset',
									 handler : this.reset.createCallback(this.formPanel)
				                },{
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
									var gridPanel = Ext.getCmp('LeaseHouseGrid');
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