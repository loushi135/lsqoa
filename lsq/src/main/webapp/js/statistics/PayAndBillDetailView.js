PayAndBillDetailView = Ext.extend(Ext.Panel, {
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
		PayAndBillDetailView.superclass.constructor.call(this, {
			id : 'PayAndBillDetailView',
			title : '供应商发票、付款明细统计',
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
					id : 'pNames',
					width: 800,
					listeners : {
						'focus' : function() {
							ProjectNewSelector.getView(function(proId, proName,proNo) {
								Ext.getCmp("pIds").setValue(proId);
								Ext.getCmp("pNames").setValue(proName);
							}, false, true).show();
						},
						afterrender : function(field) {
							// Ext.getCmp("expressApplyProContainer").hide();
						}
					},
					xtype : 'textfield'
				}, {
					name : 'pIds',
					id : 'pIds',
					hidden:true,
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
			url : __ctxPath + "/statistics/listPayAndBillDetailBankpayapply.do",
			root : 'result',
			totalProperty : 'totalCounts',
			remoteSort : true,
			fields : ['proNO', 'proName', 'supplierName', 'billCount', 'payCount', 'billBalance']
		});
		// 加载数据
		this.store.load({
			params : {
				start : 0,
				limit : 25
			}
		});

		// 初始化ColumnModel
		var cm = new Ext.grid.ColumnModel({
			columns : [ new Ext.grid.RowNumberer(),{
					header : '项目编号',
					sortable : false,
					dataIndex : 'proNO',
					renderer : function(value,metadata,record ,rowIndex,colIndex ,store ) {
							if(rowIndex-1<=-1){
								return value;
							}else{
//								return store.getAt(rowIndex-1).data.proNO;
								if(value == store.getAt(rowIndex-1).data.proNO){
									return "";
								}else{
									return value
								}
							}
						
					}
				}, {
					header : '项目名称',
					dataIndex : 'proName',
					sortable : false,
					width : 200,
					renderer : function(value,metadata,record ,rowIndex,colIndex ,store ) {
						if(rowIndex-1<=-1){
								return value;
							}else{
//								return store.getAt(rowIndex-1).data.proName;
								if(value == store.getAt(rowIndex-1).data.proName){
									return "";
								}else{
									return value
								}
							}
						
					}
				}, {
					header : '供应商',
					dataIndex : 'supplierName',
					sortable : false,
					width : 350
				}, {
					header : '收到发票金额',
					dataIndex : 'billCount',
					sortable : false,
					width : 200
				}, {
					header : '已付款',
					dataIndex : 'payCount',
					sortable : false,
					width : 200
				}, {
					header : '发票余额',
					dataIndex : 'billBalance',
					sortable : false,
					width : 200,
					renderer : function(f) {
//						if (f != 0) {
//							return "<font color='red'>"+f+"</font>";//excle不显示，暂不标红
//						} else {
							return f;
//						}
					}
				}],
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
		});

		this.gridPanel = new Ext.grid.GridPanel({

			id : 'PayAndBillDetailGrid',
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
		if (isGranted("_PayAndBillDetailtExport")) {
			AppUtil.addPrintExport(this.gridPanel, '导出[供应商发票、付款明细报表]', '供应商发票、付款明细报表',true);
		}
	},// end of the initComponents()

	search : function(self) {
		if (self.searchPanel.getForm().isValid()) {// 如果合法
			var params = self.gridPanel.getStore().baseParams;
			Ext.apply(params, self.searchPanel.getForm().getValues());
			self.gridPanel.getStore().load(params);
		}
	}
});
