/**
 * @author:
 * @class ProjectRelatedDataView
 * @extends Ext.Panel
 * @description [ProjectRelatedDataView]管理
 * @company
 */
ProjectRelatedDataView = Ext.extend(Ext.Panel, {
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
		ProjectRelatedDataView.superclass.constructor.call(this, {
					id : 'ProjectRelatedDataView',
					title : '资金结余统计',
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
								text : '施工区域:'
							}, {
								name : 'area',
								xtype : 'textfield'
							}, {
								style : 'margin-top:5px;',
								xtype : 'label',
								text : '项目名称:'
							}, {
								name : 'proName',
								xtype : 'textfield'
							}, {
								style : 'margin-top:5px;',
								xtype : 'label',
								text : '项目编号:'
							}, {
								name : 'proNo',
								xtype : 'textfield'
							}, {
								style : 'margin-top:5px;',
								xtype : 'label',
								text : '项目负责人:'
							}, {
								name : 'proChargerName',
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
					url : __ctxPath + "/statistics/listAllDataProjectNew.do",
					root : 'result',
					totalProperty : 'totalCounts',
					remoteSort : true,
					fields : ['id', 'area', 'proNo', 'proName',
							'proChargerName', 'contractAmount', 'auditAmount',
							'receiveAmount', 'managePercentage',
							'manageAmount', 'bankPayAmount', 'cashRePayAmount',
							'totalPayAmount', 'leftAmount']
				});
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
								qtip : '银行付款详细',
								style : 'margin:0 3px 0 3px'
							}]
				});

		// 初始化ColumnModel
		var sm = new Ext.grid.CheckboxSelectionModel();
		var cm = new Ext.grid.ColumnModel({
					columns : [sm, new Ext.grid.RowNumberer(), {
								header : '',
								dataIndex : 'id',
								hidden : true
							}, {
								header : '区域',
								dataIndex : 'area'
							}, {
								header : '项目编号',
								dataIndex : 'proNo'
							}, {
								header : '项目名称',
								dataIndex : 'proName',
								width:200
							}, {
								header : '项目负责人',
								dataIndex : 'proChargerName'
							}, {
								header : '合同金额',
								dataIndex : 'contractAmount',
								align :'right',
								renderer : function(value) {
									if (!Ext.isEmpty(value)) {
										return value == 0
												? value
												: 
//												(value + "元");
												commafy(value)
									} else {
										return 0;
									}
								}
							}, {
								header : '审定金额',
								dataIndex : 'auditAmount',
								align :'right',
								renderer : function(value) {
									if (!Ext.isEmpty(value)) {
										return value == 0
												? value
												: 
//												(value + "元");
												commafy(value)
									} else {
										return 0;
									}
								}
							}, {
								header : '收款',
								dataIndex : 'receiveAmount',
								align :'right',
								renderer : function(value,meta,record) {
									if (!Ext.isEmpty(value)) {
										return value == 0
												? value
												: 
//												(value + "元");
												"<a href='javascript:void(0)' onClick='showReceiveDetail("+record.data.id+");' title='点击查看收款详细';'>"+commafy(value)+"</a>"
									} else {
										return 0;
									}
								}
							}, {
								header : '管理费率（%）',
								dataIndex : 'managePercentage',
								align :'right',
								renderer : function(value) {
									if (!Ext.isEmpty(value)) {
										return value + "%";
									}
								}
							}, {
								header : '管理费',
								dataIndex : 'manageAmount',
								align :'right',
								renderer : function(value) {
									if (!Ext.isEmpty(value)) {
										return value == 0
												? value
												: 
//												(value + "元");
												commafy(value)
									} else {
										return 0;
									}
								}
							}, {
								header : '银行付款',
								dataIndex : 'bankPayAmount',
								align :'right',
								renderer : function(value,meta,record) {
									if (!Ext.isEmpty(value)) {
										return value == 0
												? value
												: 
//												(value + "元");
												"<a href='javascript:void(0)' onClick='showBankPayDetail(\""+record.data.proName+"\");' title='点击查看银行付款详细';'>"+commafy(value)+"</a>"
									} else {
										return 0;
									}
								}
							}, {
								header : '现金报销',
								dataIndex : 'cashRePayAmount',
								align :'right',
								renderer : function(value,meta,record) {
									if (!Ext.isEmpty(value)) {
										return value == 0
												? value
												: 
//												(value + "元");
												"<a href='javascript:void(0)' onClick='showRePayDetail("+record.data.id+");' title='点击查看报销详细';'>"+commafy(value)+"</a>"
									} else {
										return 0;
									}
								}
							}, {
								header : '合计',
								dataIndex : 'totalPayAmount',
								align :'right',
								renderer : function(value) {
									if (!Ext.isEmpty(value)) {
										return value == 0
												? value
												: 
//												(value + "元");
												commafy(value)
									} else {
										return 0;
									}
								}
							}, {
								header : '资金结余',
								dataIndex : 'leftAmount',
								align :'right',
								renderer : function(value) {
									if (!Ext.isEmpty(value)) {
										return value == 0
												? value
												: 
//												(value + "元");
												commafy(value)
									} else {
										return 0;
									}
								}
							}, this.rowActions],
					defaults : {
						sortable : true,
						menuDisabled : false,
						width : 110
					}
				});
		// 初始化工具栏
		this.topbar = new Ext.Toolbar({
					height : 30,
					bodyStyle : 'text-align:left',
					items : [
							// {
							// iconCls : 'btn-add',
							// text : '添加[工程项目银行付款]',
							// xtype : 'button',
							// hidden:!isGranted("_BankPayApplyAdd"),
							// handler:this.createRecord
							// }, {
							// iconCls : 'btn-del',
							// text : '删除[工程项目银行付款]',
							// xtype : 'button',
							// hidden:!isGranted("_BankPayApplyDel"),
							// handler :this.delRecords,
							// scope: this
							// }
							{
						iconCls : 'btn-xls',
						text : '导出[资金结余统计]',
						xtype : 'button',
						hidden : !isGranted("_ProjectRelatedDataExport"),
						handler : this.exportData,
						scope : this
					}]
				});

		this.gridPanel = new Ext.grid.GridPanel({
			id : 'ProjectRelatedDataGrid',
			region : 'center',
			stripeRows : true,
			tbar : this.topbar,
			store : this.store,
			trackMouseOver : true,
			disableSelection : false,
		    autoScroll : true,
			loadMask : true,
			cm : cm,
			sm : sm,
			plugins : [this.rowActions, new Ext.ux.grid.ColumnHeaderGroup({
						rows : [[{
									colspan : 12
								}, {
									header : '<span style="color:green;">付款</span>',
									colspan : 3,
									align : 'center'
								}, {
									colspan : 2
								}]]
					})
			// new Ext.ux.grid.GridSummary()
			],
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

		this.gridPanel.addListener('rowdblclick', function(grid, rowindex, e) {
					grid.getSelectionModel().each(function(rec) {
								new BankpayReportForm({
											proNo : rec.data.proNo
										}).show();
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
	exportData : function() {
		var params = this.gridPanel.getStore().baseParams;
		Ext.apply(params, this.searchPanel.getForm().getValues());
		var start = Ext.isEmpty(this.store.lastOptions.params)
				? 0
				: this.store.lastOptions.params.start;
		var limit = Ext.isEmpty(this.store.lastOptions.params)
				? 25
				: this.store.lastOptions.params.limit;
		var proName = Ext.isEmpty(params.proName)
				? params.proName
				: encodeURI(params.proName);
		var proNo = Ext.isEmpty(params.proNo)
				? params.proNo
				: encodeURI(params.proNo);
		var proChargerName = Ext.isEmpty(params.proChargerName)
				? params.proChargerName
				: encodeURI(params.proChargerName);
		var area = Ext.isEmpty(params.area)
				? params.area
				: encodeURI(params.area);
		location = __ctxPath
				+ '/statistics/reportAllDataProjectNew.do?format=xls&jasperName=ProjectRelatedData'
				+ "&start=" + start + "&limit=" + limit + "&proName=" + proName
				+ "&proNo=" + proNo + "&proChargerName=" + proChargerName
				+ "&area=" + area;
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

	/**
	 * 编辑记录
	 * 
	 * @param {}
	 *            record
	 */
	editRecord : function(record) {
		new BankpayReportForm({
					proNo : record.data.proNo
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
//收款
	showReceiveDetail = function(id){
		new ProjectReceiveDetail({
					proId : id
				}).show();
	},
	// 报销
	showRePayDetail = function(id){
		new ProjectRePayDetail({
					proId : id
				}).show();
	},
	//银行付款
	showBankPayDetail = function(projectName){
		new ProjectBankPayDetail({
					projectName : projectName
				}).show();
	}