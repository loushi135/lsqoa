ProjectBankPayDetail = Ext.extend(Ext.Window, {
	// 内嵌FormPanel
	formPanel : null,
	store : null,
	// 构造函数
	constructor : function(_cfg) {
		Ext.applyIf(this, _cfg);
		// 必须先初始化组件
		this.initUIComponents();
		ProjectBankPayDetail.superclass.constructor.call(this, {
					id : 'ProjectBankPayDetailWin',
					layout : 'fit',
					items : this.formPanel,
					modal : true,
					autoScroll : true,
					width : 1015,
					height : 350,
					maximizable : true,
					title : '银行付款详细记录',
					buttonAlign : 'center',
					buttons : this.buttons
				});
	},// end of the constructor
	// 初始化组件
	initUIComponents : function() {
		this.store = new Ext.data.JsonStore({
					url : __ctxPath + "/statistics/listDetailBankpayapply.do",
					baseParams : {
						'Q_bpaProjectName_S_EQ' : this.projectName
					},
					root : 'result',
					totalProperty : 'totalCounts',
					remoteSort : true,
					autoLoad : false,
					fields : [ 'bpaProjectName', 'bpaProjectNo', 'bpaPayType',
							'bpaReceiptDept', 'bpaReceiptReason', 'bpaApplyMoneyXX',
							'bpaApplyMoneyDX', 'bpaRemark', 'bpaApplyFirstMoneyXX', 'bpaApplyFirstMoneyDX']
				})
		this.store.setDefaultSort('id', 'desc');
		this.formPanel = new Ext.FormPanel({
			layout : 'form',
			bodyStyle : 'padding:10px 10px 10px 10px;',
			border : false,
			id : 'ProjectBankPayDetail',
			defaults : {
				anchor : '98%,98%'
			},
			items : [{
						xtype : 'grid',
						id : 'repayGrid',
						height : 250,
						width : 600,
						trackMouseOver : true,
						disableSelection : false,
						autoScroll : true,
						loadMask : true,
						viewConfig : {
							forceFit : false
						},
						store : this.store,
						cm : new Ext.grid.ColumnModel({
									columns : [new Ext.grid.RowNumberer(), {
												header : '项目名称',
												dataIndex : 'bpaProjectName',
												width : 100
											}, {
												header : '项目编号',
												dataIndex : 'bpaProjectNo',
												width : 100
											}, {
												header : '付款类别',
												dataIndex : 'bpaPayType',
												width : 100
											}, {
												header : '收款单位',
												dataIndex : 'bpaReceiptDept',
												width : 200
											}, {
												header : '收款事由',
												dataIndex : 'bpaReceiptReason',
												width : 100
											},{
												header : '本次实际用款(小写)',
												dataIndex : 'bpaApplyMoneyXX',
												renderer : function(value) {
													if (!Ext.isEmpty(value)) {
														return commafy(value);
													}
												},
												width : 100
											}, {
												header : '本次申请用款(小写)',
												dataIndex : 'bpaApplyFirstMoneyXX',
												renderer : function(value) {
													if (!Ext.isEmpty(value)) {
														return commafy(value);
													}
												},
												width : 100
											}, {
												header : '经办人员',
												dataIndex : 'bpaRemark',
												width : 100
											}],
									defaults : {
										sortable : true,
										menuDisabled : false,
										width : 100
									}
								}),
						bbar : new Ext.PagingToolbar({
									pageSize : 25,
									store : this.store,
									displayInfo : true,
									displayMsg : '当前页记录索引{0}-{1}， 共{2}条记录',
									emptyMsg : "当前没有记录"
								})
					}]
		});
		// 加载数据
		this.store.load({
					params : {
						start : 0,
						limit : 25
					}
				});

		// 初始化功能按钮
		this.buttons = [{
					text : '关闭',
					iconCls : 'btn-cancel',
					handler : this.cancel.createCallback(this)
				}];
	},// end of the initcomponents
	cancel : function(window) {
		window.close();
	}
});