/**
 * @author
 * @createtime
 * @class CardApplyForm
 * @extends Ext.Window
 * @description CardApply表单
 */
CardApplyForm = Ext.extend(Ext.Window, {
			// 内嵌FormPanel
			formPanel : null,
			// 构造函数
			constructor : function(_cfg) {
				Ext.applyIf(this, _cfg);
				// 必须先初始化组件
				this.initUIComponents();
				CardApplyForm.superclass.constructor.call(this, {
							id : 'CardApplyFormWin',
							layout : 'fit',
							items : this.formPanel,
							modal : true,
							width : 600,
			                height : 470,
							maximizable : true,
							title : '[名片申请]详细信息',
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
							url : __ctxPath + '/statistics/saveCardApply.do',
							id : 'CardApplyForm',
							defaults : {
								anchor : '98%,98%'
							},
							defaultType : 'textfield',
							items : [{ 
								        xtype:'hidden',
								        name:'cardApply.cardId',
								        value:this.cardId
							         },{ 
								        xtype:'hidden',
								        name:'cardApply.processRunId',
								        value:-1
							         },
				                    {
												xtype:'panel',
												border:false,
												layout:'column',
												style : 'padding-left:0px;margin-bottom:14px',
												items:[{
											              xtype:'label',
											              text:'印制数量:',
											              width:67
					                               },{
															xtype:"numberfield",
															name:"cardApply.amount",
															maxLength:10 ,
															id:"amount"
					                               },{
															xtype: 'radiogroup',
															id   : 'type',
															name: 'cardApply.type',
															style:'margin-left:15px;',
															width:100,
														  items:[{
															 	checked : true,
															 	name:'cardApply.type',
																boxLabel:'新印',
																inputValue:'新印'
													},{
															name:'cardApply.type',
															boxLabel:'重印',
															inputValue:'重印'
													}]
												 },{
														xtype:'label',
													 	style:'margin-top:5px;',
														text:'（请上传一张原有名片扫描件或照片） '
												}
												]
				},
				    {
					   xtype:"container",
					   layout:'column',
					   style : 'padding-left:0px;margin-bottom:14px',
					   items:[	{
					              xtype:'label',
					              text:'是否高印:',
					              width:67
					            },
					      {
							xtype : "combo",
							width:124,
							allowBlank : false,
							hiddenName :'cardApply.isType',
							id : "isType",
							mode : "local",
							editable : false,
							triggerAction : "all",
							store :[["0", "否"], ["1", "是"]]
						 },
					    {
				              xtype:'label',
				              text:'姓名:',
				              width:67,
				              style:'margin-left:92px'
					     },
					    {
							xtype:"textfield",
							name:"cardApply.cardProposer",
							maxLength:30 ,
							id:"cardProposer",
							value:__currentUser
									
							  }
						]
					
					     },
	                 {
					   xtype:'container',
					   layout:'column',
					   style : 'margin-bottom:14px',
					   items:[  
					   	      {
					              xtype:'label',
					              text:'Email:',
					              width:67,
					              style:'margin-left:0px'
					            },
								{
								    xtype:'textfield',
									name:'cardApply.Email',
									maxLength:40,
									id:"Email",
									value:__currentUserEmail
							  }
					   	     ,{
					              xtype:'label',
					              text:'电话:',
					              width:67,
					              style:'margin-left:95px'
					            },
								{
								    xtype:'textfield',
									name:'cardApply.phone',
									maxLength:14 ,
									id:"phone",
									value: __currentUserPhone
							  }
								
					        ]
			},{
					   xtype:'container',
					   layout:'column',
					   style : 'margin-bottom:14px',
					   items:[  
					   	      {
					              xtype:'label',
					              text:'公司:',
					              width:67
					            },
							    {
									xtype:"textfield",
									name:"cardApply.companyName",
									maxLength:30 ,
									id:"companyName",
									value:__companyName
									
							  },{
					              xtype:'label',
					              text:'传真:',
					              width:67,
					              style:'margin-left:95px'
					            },
								{
								    xtype:'textfield',
									name:'cardApply.fax',
									maxLength:14 ,
									id:"fax",
									value: __currentUserFax
							  }
					        ]
			       },{
					   xtype:'container',
					   layout:'column',
					   style : 'margin-bottom:14px',
					   items:[  
					   	      {
					              xtype:'label',
					              text:'所属部门:',
					              width:67,
					              style:'margin-left:0px'
					            },
								{
								    xtype:'textfield',
									name:'cardApply.deptName',
									maxLength:14 ,
									id:"deptName",
									value:__currentUserDept
							  },{
					                xtype:'hidden',
					                name:'cardApply.dept.depId',
					                id:"deptId",
					                value:__currentUserDeptId
					          },{
					              xtype:'label',
					              text:'手机:',
					              width:67,
					              style:'margin-left:95px'
					            },
								{
								    xtype:'textfield',
									name:'cardApply.mobile',
									maxLength:14 ,
									id:"mobile",
									value: __currentUserMobile
							  }
					        ]
			       },{
					   xtype:"container",
					   layout:'column',
					   style : 'padding-left:0px;margin-bottom:14px',
					   items:[ {
					              xtype:'label',
					              text:'职位:',
					              width:67,
					              style:'margin-left:0px'
					            },
								{
								    xtype:'textfield',
									name:'cardApply.position',
									maxLength:14 ,
									id:"position",
									value:__currentUserPosition
							  },{
					              xtype:'label',
					              text:'邮政编码:',
					              width:67,
					              style:'margin-left:95px'
					             },
								 {
								    xtype:"numberfield",
									name:"cardApply.postalCode",
									maxLength:10,
									allowBlank:false,
									value:__currentUserZip,
									id:"postalCode"
								}
								
					         ]
							},
						    {
										 xtype:'panel',
										 layout:'column',
										 border:false,
										 style : 'padding-left:0px;margin-bottom:14px',
										 items:[  {
										              xtype:'label',
										              text:'其他:',
										              width:67
										          },{
										 				xtype:"textarea",
										 				allowBlank:false,
														name:"cardApply.other",
														id:"other",
														height:110,
														width:430
											   }]
									
							 },
					    {
							xtype : 'container',
							autoHeight : true,
							layout : 'column',
							autoWidth : true,
							defaultType : 'label',
							style : 'padding-left:0px;padding-bottom:15px;',
							items : [{
										text : '附件:',
										width : 59,
										style : 'padding-left:0px;padding-top:3px;'
									},{
										xtype : 'hidden',
										name : 'cardAttachIDs',
										id : 'cardAttachIDs'
									},{
										xtype : 'hidden',
										name : 'cardAttachFile',
										id : 'cardAttachFile'
									},{
										xtype : 'panel',
										id:'cardDisplayAttach',
										width : 434,
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
																	file_cat : 'flow/materialContract',
																	callback : function(data) {
																		Ext.getCmp('cardDisplayAttach').body.update('');
																		Ext.getCmp("cardAttachFile").setValue('');
																		Ext.getCmp('cardAttachIDs').setValue('');
																		var contractFile = Ext.getCmp("cardAttachFile");
																		var fileIds = Ext.getCmp('cardAttachIDs');
																		var display = Ext.getCmp('cardDisplayAttach');
																		for (var i = 0; i < data.length; i++) {
																			if (fileIds.getValue() != '') {
																				fileIds.setValue(fileIds.getValue()+ ',');
																				contractFile.setValue(contractFile.getValue()+ ',');
																			}
																			contractFile.setValue(contractFile.getValue()+data[i].filepath+":"+data[i].filename.replace(/\s+/g,""));
//																			contractFile.setValue(contractFile.getValue()+"<a href=javascript:window.location.href=\""+__ctxPath+"/file-Download?filePath=attachFiles/"+data[i].filepath+"&fileName="+data[i].filename.replace(/\s+/g,"")+"\">"+data[i].filename.replace(/\s+/g,"")+"</a>");
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
				if (this.cardId != null && this.cardId != 'undefined') {
					this.formPanel.getForm().load({
						deferredRender : false,
						url : __ctxPath + '/statistics/getCardApply.do?cardId='
								+ this.cardId,
						waitMsg : '正在载入数据...',
						success : function(form, action) {
						var data = Ext.util.JSON.decode(action.response.responseText).data;
						Ext.getCmp("deptName").setValue(data.dept.depName);
						Ext.getCmp("deptName").originalValue = data.dept.depName;
						Ext.getCmp("deptId").setValue(data.dept.depId);
						Ext.getCmp("type").setValue(data.type);
						Ext.getCmp("type").originalValue = data.type;
						Ext.getCmp("deptId").originalValue = data.dept.depId;
						var af = data.CardFiles;
						var filePanel = Ext.getCmp('cardDisplayAttach');
						var fileIds = Ext.getCmp("cardAttachIDs");
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
													+ af[i].fileName.replace(/\s+/g,"")+'</a><img class="img-delete" src="'+__ctxPath+'/images/system/delete.gif" onclick="removeFile(this,'+af[i].fileId+')"/>&nbsp;|&nbsp;</span>');
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
									var gridPanel = Ext.getCmp('CardApplyGrid');
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
	function removeFile(obj, fileId) {
	var fileIds = Ext.getCmp("cardAttachIDs");
	var value = fileIds.getValue();
	if (value.indexOf(',') < 0) {// 仅有一个附件
		fileIds.setValue('');
	} else {
		value = value.replace(',' + fileId, '').replace(fileId + ',', '');
		fileIds.setValue(value);
	}

	var el = Ext.get(obj.parentNode);
	el.remove();
};