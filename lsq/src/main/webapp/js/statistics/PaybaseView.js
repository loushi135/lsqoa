/**
 * @author:
 * @class PaybaseView
 * @extends Ext.Panel
 * @description [Paybase]管理
 * @company
 * @createtime:2010-07-19
 */
PaybaseView = Ext.extend(Ext.Panel, {
	// 数据展示Panel
	gridPanel : null,
	// GridPanel的数据Store
	store : null,
	// 头部工具栏
	topbar : null,
	searchPanel : null,
	// 构造函数
	constructor : function(_cfg) {
		Ext.applyIf(this, _cfg);
		// 初始化组件
		this.initUIComponents();
		// 调用父类构造
		PaybaseView.superclass.constructor.call(this, {
					id : 'PaybaseView',
					title : '付款基数管理',
					region : 'center',
					layout : 'border',
					items : [this.searchPanel, this.gridPanel]
				});
	},// end of constructor

	// 初始化组件
	initUIComponents : function() {
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
								// }, {
							// style : 'margin-top:5px;',
							// xtype : 'label',
							// text : '收款开始时间:'
							// }, {
							// name : 'Q_receiveTime_D_GE',
							// xtype : 'datefield',
							// format : 'Y-m-d'
							// }, {
							// style : 'margin-top:5px;',
							// xtype : 'label',
							// text : '收款截止时间:'
							// }, {
							// name : 'Q_receiveTime_D_LE',
							// format : 'Y-m-d',
							// xtype : 'datefield'
						}	, {
								xtype : 'button',
								text : '查询',
								iconCls : 'search',
								handler : this.search.createCallback(this)
							}]
				});// end of the searchPanel
		// 加载数据至store
		this.store = new Ext.data.JsonStore({
					url : __ctxPath + "/statistics/listPaybase.do",
					root : 'result',
					totalProperty : 'totalCounts',
					baseParams : {
						'Q_projectNew.id_L_EQ' : this.proId
					},
					remoteSort : true,
					fields : [{
								name : 'paymentBaseId',
								type : 'int'
							}, 'paymentBase', 'paymentTime', 'projectNew',
							'suppliersAssess', 'createUser']
				});
		this.store.setDefaultSort('paymentBaseId', 'desc');
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
								hide : !isGranted("_PaybaseDel")
							}, {
								iconCls : 'btn-edit',
								qtip : '编辑',
								style : 'margin:0 3px 0 3px',
								hide : !isGranted("_PaybaseEdit")
							}]
				});

		// 初始化ColumnModel
		var sm = new Ext.grid.CheckboxSelectionModel();
		var cm = new Ext.grid.ColumnModel({
					columns : [sm, new Ext.grid.RowNumberer(), {
								header : 'paymentBaseId',
								dataIndex : 'paymentBaseId',
								hidden : true
							}, {
								header : '项目',
								dataIndex : 'projectNew',
								renderer : function(value) {
									if (!Ext.isEmpty(value)) {
										return value.proName;
									}
								}
							}, {
								header : '供应商',
								dataIndex : 'suppliersAssess',
								renderer : function(value) {
									if (!Ext.isEmpty(value)) {
										return value.suppliersName;
									}
								}
							}, {
								header : '付款时间',
								dataIndex : 'paymentTime'
							}, {
								header : '已付款金额',
								dataIndex : 'paymentBase'
							}, {
								header : '操作员',
								dataIndex : 'createUser',
								renderer : function(value) {
									if (!Ext.isEmpty(value)) {
										return value.fullname;
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
					items : [{
								iconCls : 'btn-add',
								text : '添加',
								xtype : 'button',
								handler : this.createRecord
										.createCallback(this.proId),
								hidden : !isGranted("_PaybaseAdd")
							}, {
								iconCls : 'btn-del',
								text : '删除',
								xtype : 'button',
								handler : this.delRecords,
								scope : this,
								hidden : !isGranted("_PaybaseDel")
							}]
				});

		this.gridPanel = new Ext.grid.GridPanel({
					id : 'PaybaseGrid',
					region : 'center',
					stripeRows : true,
					tbar : this.topbar,
					store : this.store,
					trackMouseOver : true,
					disableSelection : false,
					loadMask : true,
					autoHeight : true,
					cm : cm,
					sm : sm,
					plugins : this.rowActions,
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
								if (isGranted("_PaybaseEdit")) {
									new PaybaseForm({
												paymentBaseId : rec.data.paymentBaseId
											}).show();
								}
							});
				});
		this.rowActions.on('action', this.onRowAction, this);

		//this.initData();
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
		if (this.proId != null && this.proId != 'undefined') {
			this.searchPanel.getForm().load({
				deferredRender : false,
				url : __ctxPath + '/statistics/getProjectNew.do?id='
						+ this.proId,
				waitMsg : '正在载入数据...',
				success : function(form, action) {
					var jsonData = Ext.util.JSON
							.decode(action.response.responseText);
					var res = jsonData.data;
					Ext.getCmp("proNameLabel").setText(
							"<font color='green'>" + res.proName + "</font>",
							false);
				},
				failure : function(form, action) {
				}
			});
		}
	},
	/**
	 * 添加记录
	 */
	createRecord : function(proId) {
		new PaybaseForm({
					proId : proId
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
											+ '/statistics/multiDelPaybase.do',
									params : {
										ids : ids
									},
									method : 'POST',
									success : function(response, options) {
										Ext.ux.Toast.msg('操作信息',
												'成功删除该[Paybase]！');
										Ext.getCmp('PaybaseGrid').getStore()
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
		var gridPanel = Ext.getCmp('PaybaseGrid');
		var selectRecords = gridPanel.getSelectionModel().getSelections();
		if (selectRecords.length == 0) {
			Ext.ux.Toast.msg("信息", "请选择要删除的记录！");
			return;
		}
		var ids = Array();
		for (var i = 0; i < selectRecords.length; i++) {
			ids.push(selectRecords[i].data.paymentBaseId);
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
		new PaybaseForm({
					paymentBaseId : record.data.paymentBaseId
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
				this.delByIds(record.data.paymentBaseId);
				break;
			case 'btn-edit' :
				this.editRecord(record);
				break;
			default :
				break;
		}
	}
});
