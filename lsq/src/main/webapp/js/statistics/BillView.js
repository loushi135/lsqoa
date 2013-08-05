/**
 * @author:
 * @class BillView
 * @extends Ext.Panel
 * @description [Bill]管理
 * @company
 * @createtime:2010-07-19
 */
BillView = Ext.extend(Ext.Panel, {
	// 条件搜索Panel
	searchPanel : null,
	// mainPanel : null,
	// 数据展示Panel
	gridPanel : null,
	// GridPanel的数据Store
	store : null,
	// 头部工具栏
	topbar : null,
	// 构造函数
	constructor : function(_cfg, _cfg1) {
		Ext.applyIf(this, _cfg, _cfg1);
		// 初始化组件
		this.initUIComponents();
		// 调用父类构造
		BillView.superclass.constructor.call(this, {
					id : 'BillView',
					title : '[发票]管理',
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
						name : 'Q_projectNew.proName_S_LK',
						xtype : 'textfield'
					}, {
						style : 'margin-top:5px;',
						xtype : 'label',
						text : '供应商:'
					}, {
						name : 'Q_suppliersAssess.suppliersName_S_LK',
						xtype : 'textfield'
					}, {
						style : 'margin-top:5px;',
						xtype : 'label',
						text : '发票类型:'
					}, {
						name : 'Q_invoiceType_S_LK',
						xtype : 'combo',
						width : 100,
						id : 'billView.invoiceType',
						triggerAction : 'all',
						store : [],
						listeners : {
							afterrender : function(d) {
								var c = Ext.getCmp("billView.invoiceType")
										.getStore();
								if (c.getCount() <= 0) {
									Ext.Ajax.request({
												url : __ctxPath
														+ "/system/loadDictionary.do",
												method : "post",
												params : {
													itemName : "发票类型"
												},
												success : function(f) {
													var e = Ext.util.JSON
															.decode(f.responseText);
													c.loadData(e);
												}
											});
								}
							}
						}
					}, {
						style : 'margin-top:5px;',
						xtype : 'label',
						text : '发票开始时间:'
					}, {
						name : 'Q_billTime_D_GE',
						xtype : 'datefield',
						format : 'Y-m-d'
					}, {
						style : 'margin-top:5px;',
						xtype : 'label',
						text : '发票截止时间:'
					}, {
						name : 'Q_billTime_D_LE',
						format : 'Y-m-d',
						xtype : 'datefield'
					}, {
						xtype : 'button',
						text : '查询',
						iconCls : 'search',
						handler : this.search.createCallback(this)
					}]
		});// end of the searchPanel

		// this.mainPanel = new Ext.FormPanel({
		// layout : 'column',
		// region : 'north',
		// bodyStyle : 'padding:6px 10px 6px 10px',
		// border : false,
		// frame : true,
		// height : 50,
		// defaults : {
		// border : false,
		// anchor : '98%,98%',
		// xtype : 'label',
		// style : 'margin-left:5px;margin-top:5px;'
		// },
		// items : [{
		// text : '项目名称:'
		// }, {
		// id : 'proNameLabel'
		// }, {
		// text : '项目编号:'
		// }, {
		// id : 'proNOLabel'
		// }]
		// });// end of the mainPanel

		// 加载数据至store
		this.store = new Ext.data.JsonStore({
					url : __ctxPath + "/statistics/listBill.do",
					root : 'result',
					totalProperty : 'totalCounts',
					baseParams : {
						'Q_projectNew.id_L_EQ' : this.proId
					},
					remoteSort : true,
					fields : [{
								name : 'id',
								type : 'int'
							}, 'amountBig', 'amount', 'createtime',
							'createUser', 'billTime', 'projectNew',
							'suppliersAssess', 'invoiceType', 'processRunId','billAdjusts']
				});
		this.store.setDefaultSort('id', 'desc');
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
								hide : !isGranted("_BillDel")
							}, {
								iconCls : 'btn-edit',
								qtip : '编辑',
								style : 'margin:0 3px 0 3px',
								hide : !isGranted("_BillEdit")
							}, {
								iconCls : 'btn-reset',
								qtip : '发票额调整',
								style : 'margin:0 3px 0 3px',
								hide : !isGranted("_BillAdjust")
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
					'<tr><td style="margin-left:70px;" width=10%  >调整时间</td><td style="margin-left:0px;" width=10% >调整人</td><td style="margin-left:0px;" width=10% >调整发票金额</td><td style="margin-left:0px;" width=10% >原发票金额</td><td style="margin-left:0px;" width=90% >调整原因</td> </tr>',
					'<tpl for="billAdjusts">',
					'<tr style="margin-top:3px;"><td style="margin-left:70px;">{adjustDate}</td><td style="margin-left:0px;">',
					'<tpl for="adjustUser">',
					'{fullname}',
					'</tpl>',
					'</td><td style="margin-left:0px;">{newAmoumt}</td><td style="margin-left:0px;">{oldAmount}</td><td style="margin-left:0px;">{adjustReason}</td> </tr>',
					'</tpl>',
					'<tpl if="this.isEmpty(billAdjusts)"><td style=margin-left:70px;>无</td></tpl>',
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
					columns : [sm, new Ext.grid.RowNumberer(), expander, {
								header : 'id',
								dataIndex : 'id',
								hidden : true
							}, {
								header : '项目名称',
								dataIndex : 'projectNew',
								renderer : function(value) {
									return value.proName
								}
							}, {
								header : '供应商',
								dataIndex : 'suppliersAssess',
								renderer : function(value) {
									return value.suppliersName
								}
							}, {
								header : '发票类型',
								dataIndex : 'invoiceType'
							}, {
								header : '发票时间',
								dataIndex : 'billTime'
							}, {
								header : '发票金额',
								dataIndex : 'amount'
							}, {
								header : '发票金额大写',
								dataIndex : 'amountBig'
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
								text : '添加[发票]',
								xtype : 'button',
								hidden : !isGranted("_BillAdd"),
								handler : this.createRecord,
								scope : this
							}, {
								iconCls : 'btn-del',
								text : '删除[发票]',
								xtype : 'button',
								hidden : !isGranted("_BillDel"),
								handler : this.delRecords,
								scope : this
							}, {
								iconCls : 'btn-reset',
								text : '发票额调整',
								xtype : 'button',
								hidden : !isGranted("_BillAdjust"),
								handler : this.adjustBill,
								scope : this
							}]
				});

		this.gridPanel = new Ext.grid.GridPanel({
					id : 'BillGrid',
					region : 'center',
					bodyStyle:'margin-bottom:3px;',
					stripeRows : true,
					tbar : this.topbar,
					store : this.store,
					trackMouseOver : true,
					disableSelection : false,
					loadMask : true,
					cm : cm,
					sm : sm,
					plugins : [this.rowActions, expander],
					viewConfig : {
						forceFit : true,
						autoFill : true, // 自动填充
						forceFit : true
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
								new BillForm({
											id : rec.data.id
										}).show();
							});
				});
		this.rowActions.on('action', this.onRowAction, this);
		this.initData();
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
	initData : function() {
		// if (this.proId != null && this.proId != 'undefined') {
		// this.searchPanel.getForm().load({
		// deferredRender : false,
		// url : __ctxPath + '/statistics/getProjectNew.do?id='
		// + this.proId,
		// waitMsg : '正在载入数据...',
		// success : function(form, action) {
		// var jsonData = Ext.util.JSON
		// .decode(action.response.responseText);
		// var res = jsonData.data;
		// Ext.getCmp("proNameLabel").setText(
		// "<font color='green'>" + res.proName
		// + "</font>", false);
		// Ext.getCmp("proNOLabel").setText(
		// "<font color='green'>" + res.proNo
		// + "</font>", false);
		// },
		// failure : function(form, action) {
		// }
		// });
		// }
	},
	/**
	 * 添加记录
	 */
	createRecord : function() {
		new BillForm({
					proId : this.proId,
					proName : this.proName
				}).show();
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
											+ '/statistics/multiDelBill.do',
									params : {
										ids : ids
									},
									method : 'POST',
									success : function(response, options) {
										Ext.ux.Toast.msg('操作信息', '成功删除该[发票]！');
										Ext.getCmp('BillGrid').getStore()
												.reload();
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
		var gridPanel = Ext.getCmp('BillGrid');
		var selectRecords = gridPanel.getSelectionModel().getSelections();
		if (selectRecords.length == 0) {
			Ext.ux.Toast.msg("信息", "请选择要删除的记录！");
			return;
		}
		var ids = Array();
		for (var i = 0; i < selectRecords.length; i++) {
			ids.push(selectRecords[i].data.id);
		}
		this.delByIds(ids);
	},
	adjustBill :function(){
		var gridPanel = Ext.getCmp('BillGrid');
		var selectRecords = gridPanel.getSelectionModel().getSelections();
		if (selectRecords.length != 1) {
			Ext.ux.Toast.msg("信息", "请选择一条要变更的记录！");
			return;
		}
		var record = selectRecords[0];
		this.adjustBillByRecord(record);
	},
	adjustBillByRecord : function(record){
		new BillAdjustForm({
					billId : record.data.id,
					proName : record.data.projectNew.proName,
					suppliersName : record.data.suppliersAssess.suppliersName,
					amount : record.data.amount,
					amountBig : record.data.amountBig,
					invoiceType : record.data.invoiceType
				}).show();
	},
	/**
	 * 编辑记录
	 * 
	 * @param {}
	 *            record
	 */
	editRecord : function(record) {
		new BillForm({
					id : record.data.id,
					proId : this.id,
					proName : this.proName
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
				this.delByIds(record.data.id);
				break;
			case 'btn-edit' :
				this.editRecord(record);
				break;
			case 'btn-reset' :
				this.adjustBillByRecord(record);
				break;
			case 'btn-flowView' :
				AppUtil.selectProcessForm(record.data.processRunId);
				break;
			default :
				break;
		}
	}
});
