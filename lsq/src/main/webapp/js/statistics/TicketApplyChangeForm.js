/**
 * @author
 * @createtime
 * @class TicketApplyChangeForm
 * @extends Ext.Window
 * @description TicketApply表单
 */
TicketApplyChangeForm = Ext.extend(Ext.Window, {
	// 内嵌FormPanel
	formPanel : null,
	// 构造函数
	constructor : function(_cfg) {
		Ext.applyIf(this, _cfg);
		// 必须先初始化组件
		this.initUIComponents();
		TicketApplyChangeForm.superclass.constructor.call(this, {
			id : 'TicketApplyChangeFormWin',
			layout : 'fit',
			items : this.formPanel,
			modal : true,
			width : 670,
			height : 250,
			maximizable : true,
			title : '票务申请退改签操作',
			buttonAlign : 'center',
			buttons : this.buttons
		});
	},// end of the constructor
	// 初始化组件
	initUIComponents : function() {
		this.formPanel = new Ext.FormPanel({
			layout : 'form',
			trackResetOnLoad : true,
			bodyStyle : 'padding:10px 10px 10px 10px;',
			border : false,
			url : __ctxPath + '/statistics/changeTicketApply.do',
			id : 'TicketApplyChangeForm',
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
						xtype : 'container',
						layout : 'column',
						defaults : {
							xtype : 'label',
							style : {
								margin : '0px 0px 3px 0px'
							}
						},
						items : [{
									text : '退改签：',
									columnWidth : 0.15
								}, {
									xtype : 'combo',
									id : 'ticketApplyStatus',
									hiddenName : 'ticketApply.status',
									mode : 'local',
									editable:false,
									triggerAction : 'all',
									store : [[0,'改签'],[1,'退签']],
									allowBlank : false,
									value:0,
									columnWidth : 0.85,
									listeners:{
										select:function(field){
											var status = field.getValue();
											if(status == 1 ){
												Ext.getCmp('departure').disable();
												Ext.getCmp('destination').disable();
												Ext.getCmp('ticketType').disable();
												Ext.getCmp('departureTime').disable();
												Ext.getCmp('departureDetail').disable();
												Ext.getCmp('backOrNot').disable();
												Ext.getCmp('backTime').disable();
												Ext.getCmp('backDetail').disable();
												Ext.getCmp('amount').disable();
											}else{
												var backOrNot = Ext.getCmp('backOrNot').getValue();
												if (backOrNot == '是') {
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
												Ext.getCmp('departure').enable();
												Ext.getCmp('destination').enable();
												Ext.getCmp('ticketType').enable();
												Ext.getCmp('departureTime').enable();
												Ext.getCmp('departureDetail').enable();
												Ext.getCmp('backOrNot').enable();
												Ext.getCmp('amount').enable();
											}
										}
									}
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
				},
					{
						xtype : "panel",
						layout : "column",
						border : false,
						unstyled : false,
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