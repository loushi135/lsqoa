/**
 * @author:
 * @class BankpayReportView
 * @extends Ext.Panel
 * @description [Bankpayapply]管理
 * @company
 * @createtime:2010-07-19
 */
BankpayReportView = Ext.extend(Ext.Panel, {
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
		BankpayReportView.superclass.constructor.call(this, {
			id : 'BankpayReportView',
			title : '工程项目银行付款报表统计',
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
					text : '项目编号:'
				}, {
					name : 'bpaProjectNo',
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
			url : __ctxPath + "/statistics/listForReportBankpayapply.do",
			root : 'result',
			totalProperty : 'totalCounts',
			remoteSort : true,
			fields : ['bpaProjectName', 'bpaProjectNo', 'sumMoney']
		});
		this.store.setDefaultSort('bpaProjectNo', 'desc');
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
					iconCls : 'btn-edit',
					qtip : '详细',
					style : 'margin:0 3px 0 3px',
					hide : !isGranted("_BankpayReportQuery")
				}]
		});

		// 初始化ColumnModel
		var sm = new Ext.grid.CheckboxSelectionModel();
		var cm = new Ext.grid.ColumnModel({
			columns : [sm, new Ext.grid.RowNumberer(), {
					header : '项目名称',
					dataIndex : 'bpaProjectName'
				}, {
					header : '项目编号',
					dataIndex : 'bpaProjectNo'
				}, {
					header : '累计已付款',
					dataIndex : 'sumMoney',
					renderer : function(value) {
						if (!Ext.isEmpty(value)) {
							return value + "元";
						}
					}
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
			items : [
			//					 {
			//						 iconCls : 'btn-add',
			//						 text : '添加[工程项目银行付款]',
			//						 xtype : 'button',
			//						 hidden:!isGranted("_BankPayApplyAdd"),
			//						 handler:this.createRecord
			//					 }, {
			//						 iconCls : 'btn-del',
			//						 text : '删除[工程项目银行付款]',
			//						 xtype : 'button',
			//						 hidden:!isGranted("_BankPayApplyDel"),
			//						 handler :this.delRecords,
			//						 scope: this
			//					 }
			//					{
			//						 iconCls : 'btn-xls',
			//						 text : '导出[工程项目银行付款]',
			//						 xtype : 'button',
			//						 hidden:!isGranted("_BankpayReport"),
			//						 handler :this.exportData,
			//						 scope: this
			//					 }
			]
		});

		this.gridPanel = new Ext.grid.GridPanel({
			id : 'BankpayapplyGrid',
			region : 'center',
			stripeRows : true,
			tbar : this.topbar,
			store : this.store,
			trackMouseOver : true,
			disableSelection : false,
			//					autoScroll : true,
			loadMask : true,
			height : 100,
			cm : cm,
			sm : sm,
			plugins : this.rowActions,
			viewConfig : {
				forceFit : true
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
				if (isGranted("_BankpayReportQuery")) {
					new BankpayReportForm({
						proNo : rec.data.bpaProjectNo
					}).show();
				}
			});
		});

		if (isGranted("_BankpayReportExport")) {
			AppUtil.addPrintExport(this.gridPanel, '导出[工程银行付款报表统计]','工程银行付款报表统计');
		}
		this.rowActions.on('action', this.onRowAction, this);
	},// end of the initComponents()

	/**
	 * 
	 * @param {}
	 *            self 当前窗体对象
	 */
	search : function(self) {
		if (self.searchPanel.getForm().isValid()) {//如果合法
			var params = self.gridPanel.getStore().baseParams;
			Ext.apply(params, self.searchPanel.getForm().getValues());
			self.gridPanel.getStore().load(params);
		}
	},
	exportData : function() {
		var exportIframe = document.createElement('iframe');
		exportIframe.src = __ctxPath + '/admin/ExportReport.do?format=html&jasperName=report2';
		exportIframe.style.display = "none";
		document.body.appendChild(exportIframe);
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
					url : __ctxPath + '/statistics/multiDelBankpayapply.do',
					params : {
						ids : ids
					},
					method : 'POST',
					success : function(response, options) {
						Ext.ux.Toast.msg('操作信息', '成功删除该[工程项目银行付款]！');
						Ext.getCmp('BankpayapplyGrid').getStore().reload();
					},
					failure : function(response, options) {
						Ext.ux.Toast.msg('操作信息', '操作出错，请联系管理员！');
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

	/**
	 * 编辑记录
	 * 
	 * @param {}
	 *            record
	 */
	editRecord : function(record) {
		new BankpayReportForm({
			proNo : record.data.bpaProjectNo
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
	}
});
