/**
 * @author
 * @createtime
 * @class DesignContractForm
 * @extends Ext.Window
 * @description DesignContract表单
 */
DesignContractForm = Ext.extend(Ext.Window, {
	// 内嵌FormPanel
	formPanel : null,
	// 构造函数
	constructor : function(_cfg) {
		Ext.applyIf(this, _cfg);
		// 必须先初始化组件
		this.initUIComponents();
		DesignContractForm.superclass.constructor.call(this, {
					id : 'DesignContractFormWin',
					layout : 'fit',
					items : this.formPanel,
					modal : true,
					width:650,
					height:400,
					maximizable : true,
					title : '设计合同详细信息',
					buttonAlign : 'center',
					buttons : this.buttons
				});
	},// end of the constructor
	// 初始化组件
	initUIComponents : function() {
		this.formPanel = new Ext.FormPanel({
			layout : 'form',
			defaults : {
					anchor : '95%,95%'
				},
			trackResetOnLoad:true,
			autoScroll : true,
			bodyStyle: 'padding:10px 10px 10px 10px;overflow-x:hidden;overflow-y:scroll',
			border : false,
			url : __ctxPath + '/statistics/saveDesignContract.do',
			id : 'DesignContractForm',
			defaults : {
				anchor : '98%,98%'
			},
			defaultType : 'textfield',
			items : [{ 
				xtype:'hidden',
				name:'designContract.processRunId',
				value:-1
			},{
				xtype : "container",
				name : "MyContainer",
				autoEl : "div",
				layout : "column",
				width : 600,
				items : [{
					xtype : "label",
					name : "MyLabel",
					text : "",
					html : "a.本表由业务或设计部门申请合同盖章前提请法务部评审时用，由法务部留存；</br>b.本表由法务部填写评审意见后，依据合同金额大小，逐级签字完毕后交由印章管理员盖章；</br>c.合同金额50万以内，法务专员签字；50万—200万（含）；法务经理签字；200万以上，常务副总签字；变更、解除、终止合同，视具体情况适用级别签字制。",
					width : 280
				},
					{
						xtype:"label",
						name:"MyLabel1",
						text:"合同编号：",
						width:75,
						style:"margin-left:5px;margin-top:85px;"
					},
					{
						xtype:'hidden',
						name:'designContract.id',
						value:this.id
					},
					{
						xtype:"textfield",
						name:"designContract.contractNo",
						id:'contractNo',
						style:"margin-top:85px;background:#F0F0F0;",
						readOnly:true,
						emptyText:'系统自动生成!',
						width:214
					}]
			}, {
				xtype : "container",
				name : "MyContainer1",
				autoEl : "div",
				layout : "column",
				style : "margin-top:5px;",
				width : 600,
				items : [{
							xtype : "label",
							name : "MyLabel2",
							text : "甲方单位名称：",
							width : 100,
							style : "margin-top:3px;"
						}, {
							xtype : "textfield",
							name : "designContract.companyName",
							id:'companyName',
							fieldLabel : "Label",
							width : 180
						}, {
							xtype : "label",
							name : "MyLabel3",
							text : "设计部门：",
							width : 75,
							style : "margin-left:10px;margin-top:3px;"
						}, {
							xtype : "textfield",
							name : "designContract.designDept",
							id:'designDept',
							fieldLabel : "Label",
							width : 215
						}]
			}, {
				xtype : "container",
				name : "MyContainer2",
				autoEl : "div",
				layout : "column",
				width : 600,
				style : "",
				items : [{
							xtype : "label",
							name : "MyLabel4",
							text : "甲方单位性质：",
							style : "margin-top:13px;",
							width : 100
						}, {
							xtype : "textfield",
							name : "designContract.companyProperty",
							id:'companyProperty',
							fieldLabel : "Label",
							style : "margin-top:10px;",
							width : 180
						}, {
							xtype : "label",
							text : "",
							html : "有无合作</br>及合作次数：",
							style : "margin-top:5px;margin-left:10px;"
						}, {
							xtype : "container",
							layout : "column",
							width : 90,
							items : [{
										xtype : "container",
										name : "MyContainer4",
										items : [{
												xtype: 'radiogroup',
												id   : 'cooperation',
												name : 'designContract.cooperation',
												items:[{
									                columnWidth: '1',
									                items: [
									                    {name:'designContract.cooperation',inputValue : '有',checked : true,boxLabel : "有"},
									                    {name:'designContract.cooperation',inputValue : '无',boxLabel : "无"}
									                ]
									            }]
											}]
									}, {
										xtype : "numberfield",
										name : "designContract.cooperationNum",
										id:'cooperationNum',
										fieldLabel : "Label",
										width : 30,
										style : "margin-left:5px;margin-top:10px;"
									}, {
										xtype : "label",
										name : "MyLabel6",
										text : "次",
										style : "margin-left:5px;margin-top:15px;"
									}]
						}, {
							xtype : "label",
							name : "MyLabel7",
							text : "甲方资信评价：",
							style : "margin-top:15px;"
						}, {
							xtype : "container",
							items : [{
												xtype: 'radiogroup',
												id   : 'companyCredit',
												name : 'designContract.companyCredit',
												items:[{
									                columnWidth: '1',
									                items: [
									                   {name : "designContract.companyCredit",inputValue : '良好',checked : true,boxLabel : "良好"},
													   {name : "designContract.companyCredit",inputValue : '一般',boxLabel : "一般"}
									                ]
									            }]
									}]
						}]
			}, {
				xtype : "container",
				name : "MyContainer6",
				autoEl : "div",
				width : 600,
				layout : "column",
				style : "margin-top:5px;",
				items : [{
							xtype : "label",
							name : "MyLabel8",
							text : "项目名称：",
							width : 100,
							style : "margin-top:3px;"
						}, {
							xtype : "textfield",
							name : "designContract.projectName",
							id:'projectName',
							fieldLabel : "Label",
							width : 180
						}, {
							xtype : "label",
							name : "MyLabel9",
							text : "项目地点：",
							style : "margin-top:3px;margin-left:10px;",
							width : 75
						}, {
							xtype : "textfield",
							name : "designContract.projectAddr",
							id:'projectAddr',
							fieldLabel : "Label",
							width : 215
						}]
			}, {
				xtype : "container",
				name : "MyContainer7",
				autoEl : "div",
				layout : "column",
				style : "margin-top:5px;",
				width : 600,
				items : [{
							xtype : "label",
							name : "MyLabel10",
							text : "合同总价：",
							width : 100,
							style : "margin-top:15px;"
						}, {
							xtype : "numberfield",
							name : "designContract.contractAmount",
							id:'contractAmount',
							fieldLabel : "Label",
							width : 180,
							style : "margin-top:10px;"
						}, {
							xtype : "container",
							name : "MyContainer8",
							autoEl : "div",
							width : 310,
							layout : "column",
							items : [{
										xtype : "label",
										name : "MyLabel11",
										text : "取费依据：",
										style : "margin-left:10px;margin-top:15px;"
									}, {
										xtype : "container",
										name : "MyContainer11",
										autoEl : "div",
										layout : "column",
										width : 240,
										items : [{
											xtype : "container",
											name : "MyContainer9",
											autoEl : "div",
											width : 240,
											layout : "column",
											items : [{
														xtype : "label",
														name : "MyLabel12",
														text : "A.设计面积",
														width : 65,
														style : "margin-top:3px;"
													}, {
														xtype : "numberfield",
														name : "designContract.designArea",
														id:'designArea',
														fieldLabel : "Label",
														width : 60
													}, {
														xtype : "label",
														name : "MyLabel14",
														text : "㎡",
														width : 20
													}, {
														xtype : "numberfield",
														name : "designContract.singlePrice",
														id:'singlePrice',
														fieldLabel : "Label",
														width : 60
													}, {
														xtype : "label",
														name : "MyLabel15",
														text : "元/㎡"
													}]
										}, {
											xtype : "container",
											name : "MyContainer10",
											autoEl : "div",
											layout : "column",
											width : 240,
											style : "margin-top:5px;",
											items : [{
														xtype : "label",
														name : "MyLabel13",
														text : "B.工程造价",
														width : 65,
														style : "margin-top:3px;"
													}, {
														xtype : "numberfield",
														name : "designContract.projectPrice",
														id:'projectPrice',
														fieldLabel : "Label",
														width : 60
													}, {
														xtype : "label",
														name : "MyLabel16",
														text : "取费率",
														style : "margin-left:20px;"
													}, {
														xtype : "numberfield",
														name : "designContract.chargeRate",
														id:'chargeRate',
														fieldLabel : "Label",
														width : 45
													}, {
														xtype : "label",
														text : "%"
													}]
										}]
									}]
						}]
			}, {
				xtype : "container",
				name : "MyContainer12",
				autoEl : "div",
				width : 600,
				layout : "column",
				style : "margin-top:5px;",
				items : [{
							xtype : "label",
							name : "MyLabel18",
							text : "",
							html : "是否承接</br>后期施工：",
							width : 100
						}, {
							xtype : "radio",
							name : "designContract.isEndWork",
							fieldLabel : "Label",
							boxLabel : "是",
							checked : true,
							inputValue : '是',
							style : "margin-top:5px;"
						}, {
							xtype : "radio",
							name : "designContract.isEndWork",
							fieldLabel : "Label",
							boxLabel : "否",
							inputValue : '否',
							style : "margin-left:10px;margin-top:5px;"
						}, {
							xtype : "label",
							name : "MyLabel19",
							text : "施工跟进区域：",
							style : "margin-top:5px;margin-left:10px;"
						}, {
							xtype : "textfield",
							name : "designContract.workArea",
							id:'workArea',
							fieldLabel : "Label",
							style : "margin-top:5px;",
							width : 115
						}, {
							xtype : "label",
							name : "MyLabel20",
							text : "",
							html : "有无因承接后期施工</br>而让利设计费情况：",
							width : 120,
							style : "margin-left:10px;"
						}, {
							xtype : "textfield",
							name : "designContract.isLetDesignFee",
							id:'isLetDesignFee',
							fieldLabel : "Label",
							width : 75,
							style : "margin-top:5px;"
						}]
			}
			]
		});
		// 加载表单对应的数据
		if (this.id != null && this.id != 'undefined') {
			this.formPanel.getForm().load({
				deferredRender : false,
				url : __ctxPath + '/statistics/getDesignContract.do?id='
						+ this.id,
				waitMsg : '正在载入数据...',
				success : function(form, action) {
					var jsonData = Ext.util.JSON.decode(action.response.responseText);
					var res = jsonData.data;
					Ext.getCmp("cooperation").originalValue = res.cooperation;
					Ext.getCmp("companyCredit").originalValue = res.companyCredit;
				},
				failure : function(form, action) {
				}
			});
		}
		// 初始化功能按钮
		this.buttons = [
			{
					text : '保存',
					iconCls : 'btn-save',
					handler : this.save.createCallback(this.formPanel, this)
				}, {
					text : '重置',
					iconCls : 'btn-reset',
					handler : this.reset.createCallback(this.formPanel)
				},
				{
					text : '关闭',
					iconCls : 'btn-cancel',
					handler : this.cancel.createCallback(this)
				}
		];
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
							var gridPanel = Ext.getCmp('DesignContractGrid');
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