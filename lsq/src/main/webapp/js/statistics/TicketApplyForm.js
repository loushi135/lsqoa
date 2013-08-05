/**
 * @author
 * @createtime
 * @class TicketApplyForm
 * @extends Ext.Window
 * @description TicketApply表单
 */
TicketApplyForm = Ext.extend(Ext.Window, {
	// 内嵌FormPanel
	formPanel : null,
	// 构造函数
	constructor : function(_cfg) {
		Ext.applyIf(this, _cfg);
		// 必须先初始化组件
		this.initUIComponents();
		TicketApplyForm.superclass.constructor.call(this, {
			id : 'TicketApplyFormWin',
			layout : 'fit',
			items : this.formPanel,
			modal : true,
			width : 670,
			height : 600,
			maximizable : true,
			title : '票务申请详细信息',
			buttonAlign : 'center',
			buttons : this.buttons
		});
	},// end of the constructor
	// 初始化组件
	initUIComponents : function() {
		this.formPanel = new Ext.FormPanel({
			layout : 'form',
			trackResetOnLoad : true,
			bodyStyle : 'padding:10px 10px 10px 10px;overflow-x:hidden;overflow-y:scroll',
			border : false,
			url : __ctxPath + '/statistics/saveTicketApply.do',
			id : 'TicketApplyForm',
			defaults : {
				anchor : '98%,98%'
			},
			defaultType : 'textfield',
			items : [{
						name : 'ticketApply.id',
						id : 'id',
						xtype : 'hidden',
						value : this.id == null ? '' : this.id
					}, {
						xtype : 'hidden',
						name : 'ticketApply.processRunId',
						value : -1
					}, {
						xtype : 'hidden',
						name : 'ticketApply.status',
						id:'status',
						value : 0
					}, {
						xtype : 'container',
						layout : 'column',
						defaults : {
							xtype : 'label',
							style : {
								margin : '0px 0px 3px 0px'
							}
						},
						items : [{
									text : '报告人：',
									columnWidth : 0.15
								}, {
									xtype : 'textfield',
									id : 'reporter',
									name : 'ticketApply.reporter',
									maxLength : 64,
									allowBlank : false,
									columnWidth : 0.3,
									value : __currentUser

								}, {
									text : '所属部门：',
									style : 'margin-left:10px;',
									columnWidth : 0.15
								}, {
									xtype : 'hidden',
									id : 'deptId',
									name : 'ticketApply.dept.depId',
									value : __currentUserDeptId
								}, {
									xtype : 'textfield',
									id : 'deptName',
									value : __currentUserDept,
									maxLength : 64,
									columnWidth : 0.4,
									allowBlank : false
								}]
					}, {
						xtype : 'container',
						layout : 'column',
						defaults : {
							xtype : 'label',
							style : {
								margin : '0px 0px 3px 0px'
							}
						},
						items : [{
									text : '所在公司：',
									columnWidth : 0.15
								}, {
									xtype : 'textfield',
									id : 'company',
									name : 'ticketApply.company',
									allowBlank : false,
									value : __companyName,
									// readOnly:true,
									columnWidth : 0.85
								}]
					}, {
						xtype : 'container',
						layout : 'column',
						defaults : {
							xtype : 'label',
							style : {
								margin : '0px 0px 3px 0px'
							}
						},
						items : [
								// {
								// text:'出行人员：',
								// columnWidth:0.15
								// },
								// {
								// xtype:'textfield',
								// id:'bookUsers',
								// name:'ticketApply.bookUsers',
								// maxLength : 64,
								// allowBlank :false,
								// readOnly:true,
								// columnWidth:0.3,
								// listeners:{
								// 'focus' : function() {
								// UserSelector.getView(function(n,l,mobile) {
								// Ext.getCmp("bookUsers").setValue(l);
								// Ext.getCmp("bookUserIds").setValue(n);
								// if(l!=''){
								// var userArray = l.split(",");
								// Ext.getCmp("ticketNum").setValue(userArray.length);
								// }else{
								// Ext.getCmp("ticketNum").setValue('');
								// }
								// }, false)
								// .show()
								// }
								// }
								// },
								// {
								// xtype:'hidden',
								// id:'bookUserIds',
								// name:'ticketApply.bookUserIds'
								// },
								{
							text : '需要申请票数：',
							style : 'margin-left:0px;',
							columnWidth : 0.152
						}, {
							xtype : 'textfield',
							id : 'ticketNum',
							name : 'ticketApply.ticketNum',
							maxLength : 64,
							columnWidth : 0.85,
							readOnly : true,
							allowBlank : false
						}]
					}, {
						xtype : 'container',
						layout : 'column',
						defaults : {
							xtype : 'label',
							style : {
								margin : '0px 0px 3px 0px'
							}
						},
						items : [{
									text : '出差类型：',
									columnWidth : 0.15
								}, {
									xtype : 'combo',
									id : 'businessType',
									hiddenName : 'ticketApply.businessType',
									mode : 'local',
									triggerAction : 'all',
									store : ['朗捷通项目','前期项目','装饰项目', '其他'],
									allowBlank : false,
									columnWidth : 0.3,
									listeners : {
										select : function(combo, record, index) {
											var businessType = combo.getValue();
											var flow_projectName = Ext.getCmp("projectName");
											var flow_projectId = Ext.getCmp("projectId");
											if (businessType=='朗捷通项目') {
												flow_projectName.enable();
												flow_projectName.allowBlank = false;
												flow_projectName.setValue('');
												flow_projectId.setValue('');
												flow_projectName.addListener('focus',focusEvent);
											} else if (businessType=='前期项目'||businessType=='装饰项目') {
												flow_projectName.enable();
												flow_projectName.setValue('');
												flow_projectId.setValue('');
												flow_projectName.allowBlank = false;
												flow_projectName.purgeListeners();
											} else {
												flow_projectName.disable();
												flow_projectName.allowBlank = true;
											}
											function focusEvent() {
												ProjectNewSelector.getView(function(proId, proName, proNo, contractId, contractNo, contractAmount, proAreaId, proArea) {
													Ext.getCmp("projectName").setValue(proName);
													Ext.getCmp("projectId").setValue(proId);
												}, true, true).show();
											}
										}
									}
								}, {
									text : '项目：',
									style : 'margin-left:10px;',
									columnWidth : 0.15
								}, {
									xtype : 'textfield',
									id : 'projectName',
									maxLength : 64,
									columnWidth : 0.4,
									disabled : true
								}, {
									xtype : 'hidden',
									id : 'projectId',
									name : 'ticketApply.project.id'
								}]
					}, {
						xtype : 'container',
						layout : 'column',
						defaults : {
							xtype : 'label',
							style : {
								margin : '0px 0px 3px 0px'
							}
						},
						items : [{
									text : '出发地：',
									columnWidth : .15
								}, {
									xtype : 'textfield',
									id : 'departure',
									name : 'ticketApply.departure',
									maxLength : 64,
									columnWidth : .2
								}, {
									text : '目的地：',
									style : 'margin-left:10px;',
									columnWidth : .1
								}, {
									xtype : 'textfield',
									id : 'destination',
									name : 'ticketApply.destination',
									maxLength : 64,
									columnWidth : 0.2
								}, {
									text : '票务类型：',
									style : 'margin-left:10px;',
									columnWidth : 0.15
								}, {
									xtype : 'combo',
									id : 'ticketType',
									hiddenName : 'ticketApply.ticketType',
									mode : 'local',
									triggerAction : 'all',
									store : ['飞机', '火车软卧'],
									allowBlank : false,
									columnWidth : 0.2
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
								text : '出发时间：',
								columnWidth : .15
							}, {
								xtype : 'datefield',
								id : 'departureTime',
								name : 'ticketApply.departureTime',
								emptyText : '---请选择日期---',
								format : 'Y-m-d',
								editable : false,
								allowBlank : false,
								maxLength : 64,
								columnWidth : .25,
								listeners:{
									select:function(field,date){
										var hour = date.format('Y-m-d H:i').substring(11,13);
										if(parseInt(hour)<12){
											Ext.getCmp('departureDetail').setValue('上午');
										}else if(parseInt(hour)==12){
											Ext.getCmp('departureDetail').setValue('中午');
										}else if(parseInt(hour)>12&&parseInt(hour)<18){
											Ext.getCmp('departureDetail').setValue('下午');
										}else if(parseInt(hour)>=18){
											Ext.getCmp('departureDetail').setValue('晚上');
										}
									}
								}
							}, {
								xtype : 'combo',
								id : 'departureDetail',
								hiddenName : 'ticketApply.departureDetail',
								editable : false,
								mode : 'local',
								triggerAction : 'all',
								store : ['上午', '中午', '下午', '晚上'],
								allowBlank : false,
								columnWidth : 0.1
							}, {
								text : '预订返程票：',
								style : 'margin-left:10px;',
								columnWidth : 0.15
							}, {
								xtype : 'combo',
								id : 'backOrNot',
								hiddenName : 'ticketApply.backOrNot',
								editable : false,
								mode : 'local',
								triggerAction : 'all',
								store : ['是', '否'],
								allowBlank : false,
								columnWidth : 0.25,
								listeners : {
									select : function(combo, record, index) {
										if (combo.getValue() == '是') {
											Ext.getCmp("backTime").enable();
											Ext.getCmp("backTime").allowBlank = false;
											Ext.getCmp("backDetail").enable();
											Ext.getCmp("backDetail").allowBlank = false;
										} else {
											Ext.getCmp("backTime").disable();
											Ext.getCmp("backTime").allowBlank = true;
											Ext.getCmp("backTime").reset();
											Ext.getCmp("backDetail").disable();
											Ext.getCmp("backDetail").allowBlank = true;
											Ext.getCmp("backDetail").reset();
										}
									}
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
								text : '返程时间：',
								columnWidth : .15
							}, {
								xtype : 'datefield',
								id : 'backTime',
								name : 'ticketApply.backTime',
								format : 'Y-m-d',
								editable : false,
								disabled : true,
								columnWidth : 0.25,
								listeners:{
									select:function(field,date){
										var hour = date.format('Y-m-d H:i').substring(11,13);
										if(parseInt(hour)<12){
											Ext.getCmp('backDetail').setValue('上午');
										}else if(parseInt(hour)==12){
											Ext.getCmp('backDetail').setValue('中午');
										}else if(parseInt(hour)>12&&parseInt(hour)<18){
											Ext.getCmp('backDetail').setValue('下午');
										}else if(parseInt(hour)>=18){
											Ext.getCmp('backDetail').setValue('晚上');
										}
									}
								}
							}, {
								xtype : 'combo',
								id : 'backDetail',
								hiddenName : 'ticketApply.backDetail',
								editable : false,
								disabled : true,
								mode : 'local',
								triggerAction : 'all',
								store : ['上午', '中午', '下午', '晚上'],
								allowBlank : false,
								columnWidth : 0.1
							}]
				}, {
						xtype : 'container',
						layout : 'column',
						defaults : {
							xtype : 'label',
							style : {
								margin : '0px 0px 3px 0px'
							}
						},
						items : [{
									text : '申请原因：',
									columnWidth : 0.15
								}, {
									xtype : 'textarea',
									id : 'applyReason',
									name : 'ticketApply.applyReason',
									allowBlank : false,
									// readOnly:true,
									columnWidth : 0.85
								}]
					}, {

						xtype : 'container',
						layout : 'column',
						height : 230,
						id : 'ticketContainer',
						items : [{
									name : 'ticketGridData',
									xtype : 'hidden',
									id : 'ticketGridData',
									allowBlank : false,
									width : 150,
									editable : true,
									readOnly : false
								}, {
									name : 'ticketDataList',
									xtype : 'hidden',
									id : 'ticketDataList'
								},
								{
									xtype : 'hidden',
									id : 'bookUserIds',
									name : 'bookUserIds'
								}, {
									xtype : 'label',
									style : 'padding-left:0px;padding-top:3px;',
									text : '出行人员列表:',
									width : 80
								}, {
									xtype : 'label',
									style : 'padding-left:0px;padding-top:3px;color:red',
									text : '(*必填)',
									width : 60
								}, {
									xtype : 'label',
									id : 'ticketLabelId',
									text : '',
									style : 'padding-left:0px;padding-top:3px;color:red'
								}, {
									xtype : 'grid',
									id : 'ticketGridDataGrid',
									height : 200,
									width : 650,
									stripeRows : true,
									trackMouseOver : true,
									clicksToEdit : 1,
									plugins : [new Ext.ux.grid.RowEditor({
										saveText : '保存',
										cancelText : '取消',
										errorText : '请输入完整信息',
										errorSummary : false
									})],
									store : new Ext.data.ArrayStore({
										url : '',
										remoteSort : false,
										fields : ['id', 'userId', 'userName', 'idCard'],
										listeners : {
											'update' : function(value) {
												var r = this.getRange();
												var resultArray = [];
												var rlen = r.length;
												var jsonArr = new Array();
												for (var i = 0; i < rlen; i++) {
													resultArray.push([]);
													if (r[i].fields) {
														var ticketIdno = {
															id : null,
															userId : '',
															userName : '',
															idCard : ''
														}
														var fields = r[i].fields;
														var flen = fields.length;
														for (var z = 0; z < flen; z++) {
															var fieldValue = r[i].get(fields.keys[z]);
															if (fieldValue == '请双击选择' || fieldValue == '' || typeof(fieldValue) == 'undefined') {
																continue;
															}
															if (fields.keys[z] == 'id') {
																if (!Ext.isEmpty(fieldValue)) {
																	ticketIdno.id = fieldValue;
																}
															}
															if (fields.keys[z] == 'userId') {
																ticketIdno.userId = fieldValue;
															} else if (fields.keys[z] == 'userName') {
																resultArray[i].push('出行人员:' + fieldValue);
																ticketIdno.userName = fieldValue;
															} else if (fields.keys[z] == 'idCard') {
																resultArray[i].push('身份证号码:' + fieldValue+";");
																ticketIdno.idCard = fieldValue;
															}
														}
														jsonArr.push(ticketIdno);
													}

												}
												// if (rlen == 0) {
												// Ext.getCmp('flow_totalPrice').setValue(0);
												// }
												// 删除空元素
												if (jsonArr != '') {
													Ext.getCmp("ticketNum").setValue(jsonArr.length);
												} else {
													Ext.getCmp("ticketNum").setValue('');
												}
												for (var i = 0; i < resultArray.length; i++) {
													if (resultArray[i].length == 0) {
														resultArray.splice(i, 1);
														i--;
													}
												}
												Ext.getCmp('ticketGridData').setValue(resultArray);
												Ext.getCmp('ticketDataList').setValue(Ext.util.JSON.encode(jsonArr));
											}
										}
									}),
									cm : new Ext.grid.ColumnModel({
										columns : [new Ext.grid.RowNumberer(), {
													dataIndex : 'id',
													hidden : true,
													editor : new Ext.grid.GridEditor(new Ext.form.TextField({
														id : 'id',
														readOnly : true
													}))
												}, {
													dataIndex : 'userId',
													hidden : true,
													editor : new Ext.grid.GridEditor(new Ext.form.TextField({
														id : 'userId',
														name : 'userId',
														allowBlank : false,
														blankText : '员工编号不能为空',
														readOnly : true
													}))
												}, {
													header : '出行人员',
													dataIndex : 'userName',
													width : 280,
													editor : new Ext.grid.GridEditor(new Ext.form.TextField({
														id : 'userName',
														name : 'userName',
														allowBlank : false,
														blankText : '员工姓名不能为空',
														readOnly : true,
														listeners : {
															'focus' : function() {
																UserSelector.getView(function(userId, userName, mobile) {
																	Ext.getCmp('userId').setValue(userId);
																	Ext.getCmp('userName').setValue(userName);
																	Ext.getCmp('idCard').setValue("");
																	Ext.Ajax.request({
																		url :  __ctxPath + "/system/geIdCardAppUser.do?userId="+userId,
																		method : "GET",
																		success : function(e, f) {
																			var d = e.responseText;
																			Ext.getCmp('idCard').setValue(d);
																		},
																		failure : function(d, e) {
																		},
																		scope : this
																	});
																}, true).show();
															}
														}
													}))
												}, {
													header : '身份证号码',
													dataIndex : 'idCard',
													width : 337,
													editor : new Ext.grid.GridEditor(new Ext.form.TextField({
														id : 'idCard',
														name : 'idCard',
														allowBlank : false,
														blankText : '身份证号码不能为空'
														// readOnly : true
												}	))
												}],
										defaults : {
											sortable : false,
											menuDisabled : false,
											width : 100
										}
									}),
									tbar : new Ext.Toolbar({
										height : 30,
										items : [{
													iconCls : 'btn-add',
													text : '添加出行人员',
													xtype : 'button',
													handler : function() {
														var DataRecord = Ext.data.Record.create([{
																	name : 'userId'
																}, {
																	name : 'userName'
																}, {
																	name : 'idCard'
																}, {
																	name : 'id'
																}]);
														var store = Ext.getCmp('ticketGridDataGrid').store;
														var grid = Ext.getCmp('ticketGridDataGrid');
														var n1 = '';
														var n2 = '请双击选择';
														var n3 = '';
														var n4 = '';
														var newRowData = {
															id : n4,
															userId : n1,
															name : n2,
															idCard : n3
														};
														var newRecord = new DataRecord(newRowData);
														store.add([newRecord]);
														grid.getSelectionModel().on('selectionchange', function(sm) {
															Ext.getCmp('ticketremoveBtn').setDisabled(sm.getCount() < 1);
														});
													}
												}, {
													iconCls : 'btn-del',
													text : '删除出行人员',
													id : 'ticketremoveBtn',
													xtype : 'button',
													handler : function() {
														var store = Ext.getCmp('ticketGridDataGrid').store;
														var grid = Ext.getCmp('ticketGridDataGrid');
														var s = grid.getSelectionModel().getSelections();
														for (var i = 0, r; r = s[i]; i++) {
															store.remove(r);
														}
														store.fireEvent("update");// 更新事件
													}
												}]
									})
								}]
					},
					{
						xtype : "panel",
						layout : "column",
						border : false,
						unstyled : false,
						style:'margin-top:5px;margin-bottom:5px;',
						items : [{
									xtype : "label",
									text : "总费用(元):",
									width : 90,
									style : "margin-top:5px;margin-bottom:5px;"
								}, {
									xtype : 'container',
									style : 'padding-left:0px;padding-bottom:5px;',
									items : [{
										xtype : 'container',
										layout : 'column',
										style : 'padding-left:15px;padding-bottom:5px;',
										items : [{
											xtype : "label",
											text : "小写:",
											width : 40
										}, {
											xtype : "numberfield",
											cls:'textfieldBG',
											id:'amount',
											name : "ticketApply.amount",
											allowBlank:false,
											width:520,
											enableKeyEvents:true,
											regex:/^(([1-9]\d{0,10})|0)(\.\d{1,2})?$/,
											regexText:'请输入正确格式的金额',
											listeners : { 
												keyup: function(field) {
													var amountBig = AmountInWords(field.getValue());
													Ext.getCmp('amountBig').setValue(amountBig); 
												}
											}
										}]
									},{
										xtype : 'container',
										layout : 'column',
										style : 'padding-left:15px;padding-bottom:5px;',
										items : [{
											xtype : "label",
											text : "大写:",
											width : 40
										}, {
											xtype : "textfield",
											id:'amountBig',
											name : "ticketApply.amountBig",
											width: 520,
											allowBlank:false,
											readOnly:true,
											editable:false,
											style:"background:#F0F0F0;"
										}]
									}]
								}]
					}
					]
		});
		// 加载表单对应的数据
		if (this.id != null && this.id != 'undefined') {
			this.formPanel.getForm().load({
				deferredRender : false,
				url : __ctxPath + '/statistics/getTicketApply.do?id=' + this.id,
				waitMsg : '正在载入数据...',
				success : function(form, action) {
					var res = Ext.util.JSON.decode(action.response.responseText).data;
					if (res.businessType.indexOf('项目')!=-1) {
						Ext.getCmp("projectName").enable();
						Ext.getCmp("projectName").allowBlank = false;
					} else {
						Ext.getCmp("projectName").disable();
						Ext.getCmp("projectName").allowBlank = true;
					}
					if (res.backOrNot == '是') {
						Ext.getCmp("backTime").enable();
						Ext.getCmp("backTime").allowBlank = false;
						Ext.getCmp("backDetail").enable();
						Ext.getCmp("backDetail").allowBlank = false;
					} else {
						Ext.getCmp("backTime").disable();
						Ext.getCmp("backTime").allowBlank = true;
						Ext.getCmp("backTime").reset();
						Ext.getCmp("backDetail").disable();
						Ext.getCmp("backDetail").allowBlank = true;
						Ext.getCmp("backDetail").reset();
					}
					if (res.project != null) {
						Ext.getCmp("projectId").setValue(res.project.id);
						Ext.getCmp("projectId").originalValue = res.project.id;
						Ext.getCmp("projectName").setValue(res.project.proName);
						Ext.getCmp("projectName").originalValue = res.project.proName;
					}
					if (res.dept != null) {
						Ext.getCmp("deptId").setValue(res.dept.depId);
						Ext.getCmp("deptId").originalValue = res.dept.depId;
						Ext.getCmp("deptName").setValue(res.dept.depName);
						Ext.getCmp("deptName").originalValue = res.dept.depName;
					}
					var ticketId = res.id;
					Ext.Ajax.request({
						url : __ctxPath + '/statistics/listTicketIdno.do',
						params : {ticketId:ticketId},
						success : function(response, opts){
							var obj = Ext.decode(response.responseText);
							var modelGrid = Ext.getCmp("ticketGridDataGrid");
							var modelStroe = modelGrid.getStore();
							var jsonArr = new Array();
							for(var i=0 ; i < obj.result.length ; i++){
								var tempField = {
									id : obj.result[i].id,
									userId : obj.result[i].userId,
									userName: obj.result[i].userName,
									idCard : obj.result[i].idCard
								};
								var tempRecord = new modelStroe.recordType(tempField);
								modelStroe.add(tempRecord);
								jsonArr.push(tempField);
							}
							Ext.getCmp('ticketDataList').setValue(Ext.util.JSON.encode(jsonArr));
						},
						failure : function(response, opts){}
					})
				},
				failure : function(form, action) {
				}
			});
		}
		// 初始化功能按钮
		this.buttons = [{
					text : '保存',
					iconCls : 'btn-save',
					// hidden:true,
					handler : this.save.createCallback(this.formPanel, this)
				}, {
					text : '重置',
					iconCls : 'btn-reset',
					// hidden:true,
					handler : this.reset.createCallback(this.formPanel)
				}, {
					text : '关闭',
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
					var gridPanel = Ext.getCmp('TicketApplyGrid');
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