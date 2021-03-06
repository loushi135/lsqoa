/**
 * @author:
 * @class DesignProjectFinancialData
 * @extends Ext.Panel
 * @description [DesignProject]管理
 * @company
 */
DesignProjectFinancialData = Ext.extend(Ext.Panel, {
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
		DesignProjectFinancialData.superclass.constructor.call(this, {
					id : 'DesignProjectFinancialData',
					title : '设计项目财务数据',
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
								text : '项目编号:'
							}, {
								name : 'Q_proNo_S_LK',
								xtype : 'textfield'
							}, {
								style : 'margin-top:5px;',
								xtype : 'label',
								text : '项目名称:'
							}, {
								name : 'Q_proName_S_LK',
								xtype : 'textfield'
							}, {
								style : 'margin-top:5px;',
								xtype : 'label',
								text : '部门:'
							}, {
								name : 'Q_dept.depName_S_LK',
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
					url : __ctxPath + "/statistics/listDesignProject.do",
					root : 'result',
					totalProperty : 'totalCounts',
					remoteSort : true,
					fields : [{
								name : 'id',
								type : 'int'
							}, 'proNo', 'proName', 'dept', 'remark']
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
					actions : [
//							{
//								iconCls : 'btn-del',
//								qtip : '删除',
//								style : 'margin:0 3px 0 3px',
//								hide : !isGranted("_DesignProjectDel")
//							}, {
//								iconCls : 'btn-edit',
//								qtip : '编辑',
//								style : 'margin:0 3px 0 3px',
//								hide : !isGranted("_DesignProjectEdit")
//							}
							]
				});

		// 初始化ColumnModel
		var sm = new Ext.grid.CheckboxSelectionModel();
		var cm = new Ext.grid.ColumnModel({
					columns : [sm, new Ext.grid.RowNumberer(), {
								header : 'id',
								dataIndex : 'id',
								hidden : true
							}, {
								header : '项目编号',
								dataIndex : 'proNo'
							}, {
								header : '项目名称',
								dataIndex : 'proName'
							}, {
								header : '部门',
								dataIndex : 'dept',
								renderer:function(value,meta,record){
									if(!Ext.isEmpty(value)){
										return value.depName;
									}
								}
							}, {
								header : '备注',
								dataIndex : 'remark'
							}
//							, this.rowActions
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
					bodyStyle : 'text-align:left',
					items : [
//							{
//								iconCls : 'btn-add',
//								text : '添加[设计项目]',
//								xtype : 'button',
//								handler : this.createRecord,
//								hidden : !isGranted("_DesignProjectAdd")
//							}, {
//								iconCls : 'btn-del',
//								text : '删除[设计项目]',
//								xtype : 'button',
//								handler : this.delRecords,
//								scope : this,
//								hidden : !isGranted("_DesignProjectDel")
//							}
							{
								iconCls : 'assets-type',
								text : '<B>报销管理</B>',
								xtype : 'button',
								hidden : !(isGranted("_DesignProjectRepayQuery")
										|| isGranted("_DesignProjectRepayAdd")
										|| isGranted("_DesignProjectRepayEdit") || isGranted("_DesignProjectRepayDel")),
								handler : this.manageRepay,
								scope : this
							}
							]
				});

		this.gridPanel = new Ext.grid.GridPanel({
					id : 'DesignProjectGrid',
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
//					grid.getSelectionModel().each(function(rec) {
//								new DesignProjectForm({
//											id : rec.data.id
//										}).show();
//							});
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
	manageRepay : function() {
		AppUtil.addTab('DesignProjectRepayView', {
//					proId : record.data.id
				})
	},
	/**
	 * 添加记录
	 */
	createRecord : function() {
		new DesignProjectForm().show();
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
											+ '/statistics/multiDelDesignProject.do',
									params : {
										ids : ids
									},
									method : 'POST',
									success : function(response, options) {
										Ext.ux.Toast.msg('操作信息',
												'成功删除该[设计项目]！');
										Ext.getCmp('DesignProjectGrid')
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
		var gridPanel = Ext.getCmp('DesignProjectGrid');
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

	/**
	 * 编辑记录
	 * 
	 * @param {}
	 *            record
	 */
	editRecord : function(record) {
		new DesignProjectForm({
					id : record.data.id
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
			default :
				break;
		}
	}
});
