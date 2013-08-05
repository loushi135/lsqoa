ProcessRunStart = Ext
		.extend(
				Ext.Panel,
				{
					formPanel : null,
					constructor : function(a) {
						Ext.applyIf(this, a);
						this.buttonsArr = [ {
							text : "提交并启动流程",
							iconCls : "btn-ok",
							scope : this,
							id:'startFlowBtn',
							handler : this.saveAndStart
						}, {
							text : "重置表单",
							scope : this,
							iconCls : "btn-reset",
							handler : this.reset
						} ];
						ProcessRunStart.superclass.constructor.call(this, {
							autoScroll : true,
							layout : "hbox",
							tbar : new Ext.Toolbar({
								height : 26,
								items : this.buttonsArr
							}),
							layoutConfig : {
								padding : "5",
								pack : "center",
								align : "middle"
							},
							defaults : {
								margins : "0 5 10 0"
							},
							title : "流程启动-" + this.flowName,
							iconCls : "btn-flow-start"
						});
					},
					saveAndStart : function() {
						var b = this;
						var a = b.formPanel;
						if (a.getForm().isValid()) {
							b.submitEmptyText();
							a.getForm().submit(
											{
												url : __ctxPath
														+ "/flow/saveProcessActivity.do",
												waitMsg : "正在提交流程表单信息...",
												params : {
													defId : this.defId,
													runId : this.runId,
													activityName : this.activityName,
													startFlow : true
												},
												success : function(c, d) {
													Ext.ux.Toast.msg("操作信息",
															"成功保存信息！");
													AppUtil.removeTab(b.getId());
													var e = Ext.getCmp("MyProcessRunGrid");
													var processRunGrid = Ext.getCmp("ProcessRunGrid");
													setTimeout(function(){
														if (e != null) {
															e.getStore().reload();
														}
														
														if (processRunGrid != null) {
															processRunGrid.getStore().reload();
														}
													},1000);
												}
											});
						}
					},
					reset : function() {
						this.formPanel.getForm().reset();
					},
					initComponent : function() {
						ProcessRunStart.superclass.initComponent.call(this);
						var topPanel = this;
						var activityName = this.activityName;
						var defId = this.defId;
						var tempValue=this.paramsValue;
						$request({
							url : __ctxPath + "/flow/getProcessActivity.do",
							params : {
								activityName : activityName,
								defId : this.defId,
								runId : this.runId
							},
							success : function(response, options) {								
								var isFormPanel = true;
								if (response.responseText.trim().indexOf("[") == 0) {
									if (activityName == ""
											|| activityName == "undefined"
											|| activityName == null) {
										activityName = "开始";
									}
									eval('var tempValue='+tempValue+';topPanel.formPanel = new Ext.FormPanel({title:"任务表单-'
											+ activityName
											+ '",defaults:{border:false},bodyStyle:"padding:8px 8px 8px 8px;",layout:"form",width:750,autoHeight:true,autoScroll:true,items:'
											+ response.responseText + "});");
								} else {
									if (response.responseText
											.indexOf("Ext.extend(Ext.Panel") != -1) {
										isFormPanel = false;
									}
									eval('var tempValue='+tempValue+";topPanel.formPanel= new ("
											+ response.responseText + ")();");
								}
								if (!isFormPanel) {
									topPanel.formPanel.setDefId(defId);
									topPanel.getTopToolbar().removeAll();
									topPanel.getTopToolbar().setHeight(0);
								}
								topPanel.add(topPanel.formPanel);
								topPanel.doLayout();
							}
						});
					},
					submitEmptyText: function(){//解决 emptyText传值到后台
						var form = this.formPanel.getForm();
						var submitValues = form.getValues();
						for (var param in submitValues) {
							if (form.findField(param).emptyText == submitValues[param]) {
								form.findField(param).emptyText = '';
								form.findField(param).setValue('');
							}
						}
					}
				});