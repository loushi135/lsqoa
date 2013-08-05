/**
 * @author:
 * @class ProjectReceiveExportView
 * @extends Ext.Panel
 * @description [ProjectReceiveExprot]管理
 * @company
 * @createtime:2010-07-19
 */
ProjectReceiveExportView = Ext.extend(Ext.Panel, {
	// 条件搜索Panel
	searchPanel : null,
	// mainPanel:null,
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
		ProjectReceiveExportView.superclass.constructor.call(this, {
			id : 'ProjectReceiveExportView',
			title : '[收款]统计',
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
					name : 'Q_project.proName_S_LK',
					xtype : 'textfield'
					// }, {
				// style : 'margin-top:5px;',
				// xtype : 'label',
				// text : '凭证字号:'
				// }, {
				// name : 'Q_pzzh_S_LK',
				// xtype : 'textfield'
			}	, {
					style : 'margin-top:5px;',
					xtype : 'label',
					text : '收款开始时间:'
				}, {
					name : 'Q_receiveTime_D_GE',
					xtype : 'datefield',
					format : 'Y-m-d'
				}, {
					style : 'margin-top:5px;',
					xtype : 'label',
					text : '收款截止时间:'
				}, {
					name : 'Q_receiveTime_D_LE',
					format : 'Y-m-d',
					xtype : 'datefield'
				}, {
					xtype : 'button',
					text : '查询',
					iconCls : 'search',
					handler : this.search.createCallback(this)
				}]
		});// end of the searchPanel
		var baseParams = {};
		//		if (!isGranted("_ALLProjectReceiveQuery")) {
		//			baseParams = {
		//				'Q_project.area_S_EQ' : __currentUserDept
		//			};
		//		}
		this.store = new Ext.data.JsonStore({
			url : __ctxPath + "/statistics/listForExportProjectReceive.do",
			root : 'result',
			totalProperty : 'totalCounts',
			baseParams : baseParams,
			remoteSort : true,
			fields : [{
					name : 'id',
					type : 'int'
				}, 'amountBig', 'amount', 'project', 'project.proName', 'project.proNo', 'createtime', 'createUser', 'receiveTime', 'zy', 'pzzh']
		});
		this.store.setDefaultSort('id', 'desc');
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
					header : 'id',
					dataIndex : 'id',
					hidden : true
				}, {
					header : '项目名称',
					dataIndex : 'project.proName'
//					renderer : function(value) {
//						return value.proName
//					}
				}, {
					header : '项目编号',
					dataIndex : 'project.proNo'
//					renderer : function(value) {
//						return value.proNo
//					}
				}, {
					header : '收款时间',
					dataIndex : 'receiveTime'
				}, {
					header : '收款金额',
					dataIndex : 'amount'
				}, {
					header : '收款金额大写',
					dataIndex : 'amountBig'
					// }, {
				// header : '凭证字号',
				// dataIndex : 'pzzh'
				// }, {
				// header : '摘要',
				// dataIndex : 'zy'
			}
			// , this.rowActions
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
			items : [{
					iconCls : 'btn-xls',
					text : '导出报表',
					xtype : 'button',
					handler : this.exportRecord,
					scope : this,
					hidden : true
				}]
		});

		this.gridPanel = new Ext.grid.GridPanel({
			id : 'ProjectReceiveExprotGrid',
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

		if (isGranted("_ALLProjectReceiveExportExport")) {
			AppUtil.addPrintExport(this.gridPanel, '导出[收款统计]','收款统计');
		}

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
