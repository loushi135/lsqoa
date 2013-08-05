/**
 * @author:
 * @class BankpayapplyView
 * @extends Ext.Panel
 * @description [Bankpayapply]管理
 * @company
 * @createtime:2010-07-19
 */
BankpayapplyView = Ext.extend(Ext.Panel, {
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
		BankpayapplyView.superclass.constructor.call(this, {
					id : 'BankpayapplyView',
					title : '工程项目银行付款管理',
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
								name : 'Q_bpaProjectName_S_LK',
								xtype : 'textfield'
							}, {
								style : 'margin-top:5px;',
								xtype : 'label',
								text : '项目编号:'
							}, {
								name : 'Q_bpaProjectNo_S_LK',
								xtype : 'textfield'
							}, {
								style : 'margin-top:5px;',
								xtype : 'label',
								text : '付款类别:'
							}, {
								name : 'Q_bpaPayType_S_LK',
								xtype : 'textfield'
							}, {
								style : 'margin-top:5px;',
								xtype : 'label',
								text : '收款单位:'
							}, {
								name : 'Q_bpaReceiptDept_S_LK',
								xtype : 'textfield'
							}, {
								xtype : 'button',
								text : '查询',
								iconCls : 'search',
								handler : this.search.createCallback(this)
							}]
				});// end of the searchPanel

		// 加载数据至store
		this.store = new Ext.data.JsonStore({
					url : __ctxPath + "/statistics/listBankpayapply.do",
					root : 'result',
					totalProperty : 'totalCounts',
					remoteSort : true,
					fields : [{
								name : 'bankPayApplyId',
								type : 'int'
							}, 'bpaProjectName', 'bpaProjectNo', 'bpaPayType',
							'bpaReceiptDept', 'bpaReceiptReason',
							'bpaContract', 'bpaSumMoney', 'bpaSumMoneyRatio',
							'bpaFundBalance', 'bpaPayRatio',
							'bpaInvoiceBalance', 'bpaApplyMoneyXX',
							'bpaApplyMoneyDX', 'bpaGTFiveMillion', 'bpaRemark',
							'processRunId', 'bankpayapplyUpdates',
							'bpaApplyFirstMoneyXX', 'bpaApplyFirstMoneyDX','payMoneyDate']
				});
		this.store.setDefaultSort('bankPayApplyId', 'desc');
		// 加载数据
		this.store.load({
					params : {
						start : 0,
						limit : 25
					}
				});

		this.rowActions = new Ext.ux.grid.RowActions({
					header : '管理',
					width : 80,
					actions : [{
								iconCls : 'btn-del',
								qtip : '删除',
								style : 'margin:0 3px 0 3px',
								hide : !isGranted("_BankPayApplyDel")
							}, {
								iconCls : 'btn-edit',
								qtip : '编辑',
								style : 'margin:0 3px 0 3px',
								hide : !isGranted("_BankPayApplyEdit")

							}, {
								iconCls : 'btn-flowView',
								qtip : '查看审批表单',
								style : 'margin:0 3px 0 3px'
							}]
				});
		var expander = new Ext.grid.RowExpander({
			tpl : new Ext.XTemplate(
					'<div width=100%  style=margin-left:70px; >',
					'<table width=100%  cellspacing="0" cellpadding="0" >',
					'<tr><td style="margin-left:70px;" width=10%  >变更时间</td><td style="margin-left:0px;" width=10% >变更人</td><td style="margin-left:0px;" width=10% >变更金额</td><td style="margin-left:0px;" width=10% >原金额</td><td style="margin-left:0px;" width=90% >变更原因</td> </tr>',
					'<tpl for="bankpayapplyUpdates">',
					'<tr><td style=margin-left:70px;>{timeCreate}</td><td style="margin-left:0px;">',
					'<tpl for="userCreate">',
					'{fullname}',
					'</tpl>',
					'</td><td style="margin-left:0px;">{nowApplyMoneyXX}</td><td style="margin-left:0px;">{oldApplyMoneyXX}</td><td style="margin-left:0px;">{updateReason}</td> </tr>',
					'</tpl>',
					'<tpl if="this.isEmpty(bankpayapplyUpdates)"><td style=margin-left:70px;>无</td></tpl>',
					'</table>', '</div>', {
						isEmpty : function(list) {
							return list == ''
						}
					}),
			// 设置行双击是否展开
			// expandOnDblClick : false,
			lazyRender : true,
			enableCaching : false
		});
		// 初始化ColumnModel
		var sm = new Ext.grid.CheckboxSelectionModel();
		var cm = new Ext.grid.ColumnModel({
					columns : [sm, new Ext.grid.RowNumberer(), {
								header : 'bankPayApplyId',
								dataIndex : 'bankPayApplyId',
								hidden : true
							}, expander, {
								header : '项目名称',
								dataIndex : 'bpaProjectName',
								width:200
							}, {
								header : '项目编号',
								dataIndex : 'bpaProjectNo'
							}, {
								header : '付款类别',
								dataIndex : 'bpaPayType'
							}, {
								header : '收款单位',
								dataIndex : 'bpaReceiptDept',
								width:200
							}, {
								header : '收款事由',
								dataIndex : 'bpaReceiptReason'
							}, {
								header : '合同/结算金额',
								dataIndex : 'bpaContract',
								renderer : function(value) {
									if (!Ext.isEmpty(value)) {
										return value + "元";
									}
								}
							}
//							, {
//								header : '累计已付款',
//								dataIndex : 'bpaSumMoney',
//								renderer : function(value) {
//									if (!Ext.isEmpty(value)) {
//										return value + "元";
//									}
//								}
//							}, {
//								header : '已付款比例',
//								dataIndex : 'bpaSumMoneyRatio',
//								renderer : function(value) {
//									if (!Ext.isEmpty(value)) {
//										return value + "%";
//									}
//								}
//							}, {
//								header : '资金结余',
//								dataIndex : 'bpaFundBalance',
//								renderer : function(value) {
//									if (!Ext.isEmpty(value)) {
//										return value + "元";
//									}
//								}
//							}, {
//								header : '垫资审批额度',
//								dataIndex : 'bpaPayRatio'
//							}
							, {
								header : '发票结余',
								dataIndex : 'bpaInvoiceBalance',
								renderer : function(value) {
									if (!Ext.isEmpty(value)) {
										return value + "元";
									}
								}
							}, {
								header : '本次实际付款(小写)',
								dataIndex : 'bpaApplyMoneyXX',
								renderer : function(value) {
									if (!Ext.isEmpty(value)) {
										return value + "元";
									}
								}
							}, {
								header : '本次实际付款(大写)',
								dataIndex : 'bpaApplyMoneyDX',
								width:200
							}, {
								header : '付款日期',
								dataIndex : 'payMoneyDate'
							}, {
								header : '本次申请用款(小写)',
								dataIndex : 'bpaApplyFirstMoneyXX',
								renderer : function(value) {
									if (!Ext.isEmpty(value)) {
										return value + "元";
									}
								}
							}
//							, {
//								header : '本次申请用款(大写)',
//								dataIndex : 'bpaApplyFirstMoneyDX'
//							}
							, {
								header : '经办人员',
								dataIndex : 'bpaRemark'
							}, this.rowActions],
					defaults : {
						sortable : true,
						menuDisabled : false,
						width : 100
					}
				});
		// 初始化工具栏
		this.topbar = new Ext.Toolbar({
					height : 30,
					bodyStyle : 'text-align:left',
					items : [{
								iconCls : 'btn-add',
								text : '添加[工程项目银行付款]',
								xtype : 'button',
								hidden : !isGranted("_BankPayApplyAdd"),
								handler : this.createRecord
							}, {
								iconCls : 'btn-del',
								text : '删除[工程项目银行付款]',
								xtype : 'button',
								hidden : !isGranted("_BankPayApplyDel"),
								handler : this.delRecords,
								scope : this
							}, {
								iconCls : 'btn-reset',
								text : '付款变更[工程项目银行付款]',
								xtype : 'button',
								hidden : !isGranted("_BankPayApplyUpdate"),
								handler : this.updateRecords,
								scope : this
							}]
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
					plugins : [this.rowActions, expander],
					viewConfig : {
						forceFit : false
						// showPreview : false
					},
					bbar : new Ext.PagingToolbar({
								pageSize : 25,
								store : this.store,
								displayInfo : true,
								displayMsg : '当前页记录索引{0}-{1}， 共{2}条记录',
								emptyMsg : "当前没有记录"
							})
				});

		this.gridPanel.addListener('rowdblclick', function(grid, rowindex, e) {
			grid.getSelectionModel().each(function(rec) {
						if (isGranted("_BankPayApplyEdit")) {
							new BankpayapplyForm({
										bankPayApplyId : rec.data.bankPayApplyId
									}).show();
						}
					});
		});
		this.rowActions.on('action', this.onRowAction, this);
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
	},

	/**
	 * 添加记录
	 */
	createRecord : function() {
		new BankpayapplyForm().show();
	},
	/**
	 * 按IDS删除记录
	 * 
	 * @param {}
	 *            ids
	 */
	delByIds : function(ids) {
		Ext.Msg.confirm('信息确认', '您确认要删除所选记录吗？', function(btn) {
					if (btn == 'yes') {
						Ext.Ajax.request({
									url : __ctxPath
											+ '/statistics/multiDelBankpayapply.do',
									params : {
										ids : ids
									},
									method : 'POST',
									success : function(response, options) {
										Ext.ux.Toast.msg('操作信息',
												'成功删除该[工程项目银行付款]！');
										Ext.getCmp('BankpayapplyGrid')
												.getStore().reload();
									},
									failure : function(response, options) {
										Ext.ux.Toast
												.msg('操作信息', '操作出错，请联系管理员！');
									}
								});
					}
				});// end of comfirm
	},
	/**
	 * 删除多条记录
	 */
	delRecords : function() {
		var gridPanel = Ext.getCmp('BankpayapplyGrid');
		var selectRecords = gridPanel.getSelectionModel().getSelections();
		if (selectRecords.length == 0) {
			Ext.ux.Toast.msg("信息", "请选择要删除的记录！");
			return;
		}
		var ids = Array();
		for (var i = 0; i < selectRecords.length; i++) {
			ids.push(selectRecords[i].data.bankPayApplyId);
		}
		this.delByIds(ids);
	},

	updateRecords : function() {
		var gridPanel = Ext.getCmp('BankpayapplyGrid');
		var selectRecords = gridPanel.getSelectionModel().getSelections();
		if (selectRecords.length != 1) {
			Ext.ux.Toast.msg("信息", "请选择一条要变更的记录！");
			return;
		}
		var record = selectRecords[0];
		// for (var i = 0; i < selectRecords.length; i++) {
		// ids.push(selectRecords[i].data.bankPayApplyId);
		// }

		new BankpayapplyUpdate({
					bankPayApplyId : record.data.bankPayApplyId,
					bpaProjectName : record.data.bpaProjectName,
					bpaProjectNo : record.data.bpaProjectNo,
					bpaReceiptDept : record.data.bpaReceiptDept,
					bankPayApplyId : record.data.bankPayApplyId,
					bpaReceiptReason : record.data.bpaReceiptReason,
					bpaApplyMoneyXX : record.data.bpaApplyMoneyXX,
					bpaApplyMoneyDX : record.data.bpaApplyMoneyDX,
					bpaApplyFirstMoneyXX : record.data.bpaApplyFirstMoneyXX,
					bpaApplyFirstMoneyDX : record.data.bpaApplyFirstMoneyDX,
					bpaRemark : record.data.bpaRemark
				}).show();
	},

	/**
	 * 编辑记录
	 * 
	 * @param {}
	 *            record
	 */
	editRecord : function(record) {
		new BankpayapplyForm({
					bankPayApplyId : record.data.bankPayApplyId
				}).show();
	},
	/**
	 * 管理列中的事件处理
	 * 
	 * @param {}
	 *            grid
	 * @param {}
	 *            record
	 * @param {}
	 *            action
	 * @param {}
	 *            row
	 * @param {}
	 *            col
	 */
	onRowAction : function(gridPanel, record, action, row, col) {
		switch (action) {
			case 'btn-del' :
				this.delByIds(record.data.bankPayApplyId);
				break;
			case 'btn-edit' :
				this.editRecord(record);
				break;
			case 'btn-flowView' :
				AppUtil.selectProcessForm(record.data.processRunId);
				break;
			default :
				break;
		}
	},
	payBase : function() {
		App.clickTopTab('PaybaseView', '', function() {
					AppUtil.removeTab('PaybaseView');
				});
	}
});
