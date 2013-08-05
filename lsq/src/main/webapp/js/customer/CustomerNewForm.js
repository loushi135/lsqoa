/**
 * @author
 * @createtime
 * @class CustomerNewForm
 * @extends Ext.Window
 * @description CustomerNew表单
 */
CustomerNewForm = Ext.extend(Ext.Window, {
			// 内嵌FormPanel
			formPanel : null,
			// 构造函数
			constructor : function(_cfg) {
				Ext.applyIf(this, _cfg);
				// 必须先初始化组件
				this.initUIComponents();
				CustomerNewForm.superclass.constructor.call(this, {
							id : 'CustomerNewFormWin',
							layout : 'fit',
							items : this.formPanel,
							modal : true,
							height : 250,
							width : 400,
							maximizable : true,
							title : '客户详细信息',
							buttonAlign : 'center',
							buttons : this.buttons
						});
			},// end of the constructor
			// 初始化组件
			initUIComponents : function() {
				this.formPanel = new Ext.FormPanel({
							layout : 'form',
							bodyStyle : 'padding:10px 10px 10px 10px',
							border : false,
							url : __ctxPath + '/customer/saveCustomerNew.do',
							id : 'CustomerNewForm',
							defaults : {
								anchor : '98%,98%'
							},
							defaultType : 'textfield',
							items : [{
										fieldLabel : '代码',
										name : 'customerNew.code',
										id : 'code',
										readOnly:true,
										emptyText:'系统自动生成'
									},{
										name : 'customerNew.id',
										id : 'id',
										xtype : 'hidden',
										value : this.id == null ? '' : this.id
									}, {
										fieldLabel : '客户名称',
										name : 'customerNew.name',
										id : 'name'
									}, {
										fieldLabel : '客户全名',
										name : 'customerNew.fullname',
										id : 'fullname'
//										regex :/^([\u4e00-\u9fa5])+\u5730\u533a_([\u4e00-\u9fa5])+$/
									},{
										xtype : 'hidden',
										name : 'suppliersAssess.delFlag',
										value : 0
									},{
												fieldLabel : '省份',	
												hiddenName : 'customerNew.province.provinceId',
												id : 'provinceId',
												xtype : 'combo',
												mode : 'remote',
												editable : false,
												allowBlank:false,
												valueField : 'provinceId',
												displayField : 'provinceName',
												triggerAction : 'all',
												store : new Ext.data.JsonStore({
														autoLoad:false,
														url : __ctxPath
																+ '/system/listProvince.do',
													    baseParams:{start:0,limit:100},
														root : 'result',
														totalProperty : 'totalCounts',
														remoteSort : true,
														sortInfo :{field: "sort", direction: "ASC"},
														fields : [{name:'provinceId',type:'int'},'provinceName']
													}),
												listeners: {
													focus: function(){
//														Ext.getCmp('provinceId').getStore().reload();
													},
													select : function(combo, record,index) {
														var provinceId = combo.value;
														Ext.getCmp('cityId').setValue("");
														Ext.getCmp('cityId').getStore().reload({
																	params : {'Q_province.provinceId_L_EQ' : provinceId}
																	});
													}
												}
							}
																																				,{
												fieldLabel : '城市',	
												hiddenName : 'customerNew.city.cityId',
												id : 'cityId',
												xtype : 'combo',
												mode : 'local',
												editable : false,
												valueField : 'cityId',
												displayField : 'cityName',
												triggerAction : 'all',
												store :new Ext.data.JsonStore({
															autoLoad:false,
															url : __ctxPath
																	+ '/system/listCity.do',
															baseParams:{start:0,limit:100,'Q_province.provinceId_L_EQ' : '0'},
															root : 'result',
															totalProperty : 'totalCounts',
															remoteSort : true,
															sortInfo :{field: "sort", direction: "ASC"},
															fields : [{name:'cityId',type:'int'},'cityName']
														})
							}

							]
						});
				// 加载表单对应的数据
				if (this.id != null && this.id != 'undefined') {
					this.formPanel.getForm().load({
						deferredRender : false,
						url : __ctxPath + '/customer/getCustomerNew.do?id='
								+ this.id,
						waitMsg : '正在载入数据...',
						success : function(form, action) {
							var res = Ext.util.JSON.decode(action.response.responseText).data;
							if(res.province!=null){
								Ext.getCmp("provinceId").getStore().load({callback:function(){
									Ext.getCmp("provinceId").setValue(res.province.provinceId);
									Ext.getCmp('cityId').getStore().load({//这里是手动Load的所以那个store不用 加autoLoad:true
																			params : {'Q_province.provinceId_L_EQ' : res.province.provinceId},
																			callback:function(){
																					if(res.city!=null){
																						Ext.getCmp("cityId").setValue(res.city.cityId);
																					}
																				}
																			}
																		);
							}})
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
						}
//						, {
//							text : '重置',
//							iconCls : 'btn-reset',
//							handler : this.reset.createCallback(this.formPanel)
//						}
						, {
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
											.getCmp('CustomerNewGrid');
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