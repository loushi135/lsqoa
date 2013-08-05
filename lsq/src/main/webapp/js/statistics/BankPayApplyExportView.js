/**
 * @author:
 * @class BankPayApplyExportView
 * @extends Ext.Panel
 * @description [Bankpayapply]管理
 * @company
 * @createtime:2010-07-19
 */
BankPayApplyExportView = Ext.extend(Ext.Panel, {
	// 条件搜索Panel
	searchPanel : null,
	// 数据展示Panel
	gridPanel : null,
	// GridPanel的数据Store
	store : null,
	// 头部工具栏
	topbar : null,
	// 构造函数
	constructor : function(_cfg) {
		Ext.applyIf(this, _cfg);
		// 初始化组件
		this.initUIComponents();
		// 调用父类构造
		BankPayApplyExportView.superclass.constructor.call(this, {
					id : 'BankPayApplyExportView',
					title : '付款统计',
					region : 'center',
					layout : 'border',
					items : [this.searchPanel, this.gridPanel]
				});
	},// end of constructor

	// 初始化组件
	initUIComponents : function() {
		// 初始化搜索条件Panel
		this.searchPanel = new Ext.FormPanel({
					layout : 'column',
					region : 'north',
					bodyStyle : 'padding:6px 10px 6px 10px',
					border : false,
					frame : true,
					height : 50,
					defaults : {
						border : false,
						anchor : '98%,98%'
					},
					items : [{
								style : 'margin-top:5px;',
								xtype : 'label',
								text : '请输入查询条件:'
							}, {
								style : 'margin-top:5px;',
								xtype : 'label',
								text : '项目名称:'
							}, {
								name : 'bpaProjectName',
								xtype : 'textfield'
							}, {
								style : 'margin-top:5px;',
								xtype : 'label',
								text : '付款开始时间:'
							}, {
								name : 'Q_payMoneyDate_D_GE',
								xtype : 'datefield',
								format : 'Y-m-d'
							}, {
								style : 'margin-top:5px;',
								xtype : 'label',
								text : '付款截止时间:'
							}, {
								name : 'Q_payMoneyDate_D_LE',
								format : 'Y-m-d',
								xtype : 'datefield'
							}, {
								xtype : 'button',
								text : '查询',
								iconCls : 'search',
								handler : this.search.createCallback(this)
							}]
				});// end of the searchPanel

		// 加载数据至store
		this.store = new Ext.data.JsonStore({
					url : __ctxPath + "/statistics/listForExportBankpayapply.do",
					root : 'result',
					totalProperty : 'totalCounts',
					remoteSort : true,
					fields : [
//							{
//								name : 'bankPayApplyId',
//								type : 'int'
//							}, 
							'bpaProjectName', 'bpaProjectNo',
//							'bpaPayType','bpaReceiptDept', 'bpaReceiptReason',
//							'bpaContract', 'bpaSumMoney', 'bpaSumMoneyRatio',
//							'bpaFundBalance', 'bpaPayRatio','bpaInvoiceBalance',
							'bpaApplyMoneyXX','bpaApplyMoneyDX', 
//							'bpaGTFiveMillion', 'bpaRemark',
//							'processRunId', 'bankpayapplyUpdates',
//							'bpaApplyFirstMoneyXX', 'bpaApplyFirstMoneyDX',
							'payMoneyDate'
							]
				});
		//this.store.setDefaultSort('bankPayApplyId', 'desc');
		// 加载数据
		this.store.load({
					params : {
						start : 0,
						limit : 25
					}
				});

		// 初始化ColumnModel
		var sm = new Ext.grid.CheckboxSelectionModel();
		var cm = new Ext.grid.ColumnModel({
					columns : [sm, new Ext.grid.RowNumberer(), {
								header : 'bankPayApplyId',
								dataIndex : 'bankPayApplyId',
								hidden : true
							},{
								header : '项目名称',
								dataIndex : 'bpaProjectName',
								sortable : false,
								width:200
							}, {
								header : '项目编号',
								sortable : false,
								dataIndex : 'bpaProjectNo'
//							}, {
//								header : '付款类别',
//								dataIndex : 'bpaPayType'
//							}, {
//								header : '收款单位',
//								dataIndex : 'bpaReceiptDept',
//								width:200
//							}, {
//								header : '收款事由',
//								dataIndex : 'bpaReceiptReason'
//							}, {
//								header : '合同/结算金额',
//								dataIndex : 'bpaContract',
//								renderer : function(value) {
//									if (!Ext.isEmpty(value)) {
//										return value + "元";
//									}
//								}
//							}
//							, {
//								header : '发票结余',
//								dataIndex : 'bpaInvoiceBalance',
//								renderer : function(value) {
//									if (!Ext.isEmpty(value)) {
//										return value + "元";
//									}
//								}
							}, {
								header : '本次实际付款(小写)',
								sortable : false,
								dataIndex : 'bpaApplyMoneyXX',
								renderer : function(value) {
									if (!Ext.isEmpty(value)) {
										return value + "元";
									}
								}
							}, {
								header : '本次实际付款(大写)',
								sortable : false,
								dataIndex : 'bpaApplyMoneyDX',
								width:200
							}, {
								header : '付款日期',
								sortable : false,
								dataIndex : 'payMoneyDate',
								renderer : function(value) {
									if(!Ext.isEmpty(value)){
										return  value.substr(0,10); 
									}else{
										return  value; 
									}
								}
//							}, {
//								header : '本次申请用款(小写)',
//								dataIndex : 'bpaApplyFirstMoneyXX',
//								renderer : function(value) {
//									if (!Ext.isEmpty(value)) {
//										return value + "元";
//									}
//								}
							}
//							, {
//								header : '本次申请用款(大写)',
//								dataIndex : 'bpaApplyFirstMoneyDX'
//							}
//							, {
//								header : '经办人员',
//								dataIndex : 'bpaRemark'
//							}, this.rowActions
							],
					defaults : {
						sortable : true,
						menuDisabled : false,
						width : 100
					}
				});
		// 初始化工具栏
		this.topbar = new Ext.Toolbar({
					height : 30,
					bodyStyle : 'text-align:left'
//					items : [{
//								iconCls : 'btn-add',
//								text : '添加[工程项目银行付款]',
//								xtype : 'button',
//								hidden : !isGranted("_BankPayApplyAdd"),
//								handler : this.createRecord
//							}, {
//								iconCls : 'btn-del',
//								text : '删除[工程项目银行付款]',
//								xtype : 'button',
//								hidden : !isGranted("_BankPayApplyDel"),
//								handler : this.delRecords,
//								scope : this
//							}, {
//								iconCls : 'btn-reset',
//								text : '付款变更[工程项目银行付款]',
//								xtype : 'button',
//								hidden : !isGranted("_BankPayApplyUpdate"),
//								handler : this.updateRecords,
//								scope : this
//							}]
				});

		this.gridPanel = new Ext.grid.GridPanel({

					id : 'BankpayapplyGrid',
					region : 'center',
					stripeRows : true,
					tbar : this.topbar,
					store : this.store,
					trackMouseOver : true,
					disableSelection : false,
					autoScroll : true,
					loadMask : true,
					height : 100,
					cm : cm,
					sm : sm,
					//plugins : [this.rowActions, expander],
					viewConfig : {
						forceFit : false
						// showPreview : false
					},
					bbar : new Ext.PagingToolbar({
								pageSize : 25,
								store : this.store,
								plugins : [new Ext.ux.PageSizePlugin()],
								displayInfo : true,
								displayMsg : '当前页记录索引{0}-{1}， 共{2}条记录',
								emptyMsg : "当前没有记录"
							})
				});
 		if (isGranted("_ALLBankPayApplyExportExport")) {
		 	AppUtil.addPrintExport(this.gridPanel,'导出[付款统计]','付款统计');
		 }
//		this.gridPanel.addListener('rowdblclick', function(grid, rowindex, e) {
//			grid.getSelectionModel().each(function(rec) {
//						if (isGranted("_BankPayApplyEdit")) {
//							new BankpayapplyForm({
//										bankPayApplyId : rec.data.bankPayApplyId
//									}).show();
//						}
//					});
//		});
//		this.rowActions.on('action', this.onRowAction, this);
	},// end of the initComponents()

	/**
	 * 
	 * @param {}
	 *            self 当前窗体对象
	 */
	search : function(self) {
		if (self.searchPanel.getForm().isValid()) {// 如果合法
			var params = self.gridPanel.getStore().baseParams;
			Ext.apply(params, self.searchPanel.getForm().getValues());
			self.gridPanel.getStore().load(params);
		}
	}
});
