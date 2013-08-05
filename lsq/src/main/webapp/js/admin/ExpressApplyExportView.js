/**
 * @author:
 * @class ExpressApplyExportView
 * @extends Ext.Panel
 * @description [ExpressApply]管理
 * @company
 */
ExpressApplyExportView = Ext.extend(Ext.Panel, {
	//条件搜索Panel
	searchPanel : null,
	//数据展示Panel
	gridPanel : null,
	//GridPanel的数据Store
	store : null,
	//头部工具栏
	topbar : null,
	//构造函数
	constructor : function(_cfg) {
		Ext.applyIf(this, _cfg);
		//初始化组件
		this.initUIComponents();
		//调用父类构造
		ExpressApplyExportView.superclass.constructor.call(this, {
			id : 'ExpressApplyExportView',
			title : '快递分摊统计',
			iconCls : 'menu-express',
			region : 'center',
			layout : 'border',
			items : [this.searchPanel, this.gridPanel]
		});
	},//end of constructor

	//初始化组件
	initUIComponents : function() {
		//初始化搜索条件Panel
		this.searchPanel = new Ext.FormPanel({
			layout : 'column',
			region : 'north',
			bodyStyle : 'padding:6px 10px 6px 10px',
			border : false,
			height : 40,
			frame : false,
			defaults : {
				border : false,
				anchor : '98%,98%'
			},
			items : [{
						style : 'margin-top:3px;',
						xtype : 'label',
						text : '请输入查询条件:'
					}, {
						style : 'margin-top:3px;margin-left:5px;',
						xtype : 'label',
						text : '开始月份:'
					}, {
						name : 'beginMonth',
						id:'beginMonth',
						xtype : 'textfield',
						width : 100,
						regex:/(^(1[89]\d\d|2[01][01]\d)-(1[0-2]|0\d)$)|^$/,
						emptyText:'格式：2013-04',
						regexText:'格式：2013-04'
					}, {
						style : 'margin-top:3px;margin-left:5px;',
						xtype : 'label',
						text : '结束月份:'
					}, {
						name : 'endMonth',
						id:'endMonth',
						xtype : 'textfield',
						width : 100,
						regex:/(^(1[89]\d\d|2[01][01]\d)-(1[0-2]|0\d)$)|^$/,
						emptyText:'格式：2013-04',
						regexText:'格式：2013-04'
					}, {
						style : 'margin-top:3px;margin-left:5px;',
						xtype : 'label',
						text : '申请人:'
					}, {
						name : 'Q_applyer_S_LK',
						xtype : 'textfield',
						width : 100
					},{
						style : 'margin-top:3px;margin-left:5px;',
						xtype : 'label',
						text : '申请日期:'
					}, {
						name : 'Q_applyDate_D_EQ',
						xtype : 'datefield',
						format:'Y-m-d',
						width : 100
					}, {
						style : 'margin-top:3px;margin-left:5px;',
						xtype : 'label',
						text : '部门:'
					}, {
						name : 'Q_dept.depName_S_LK',
						xtype : 'textfield',
						width : 100
					}, {
						style : 'margin-top:3px;margin-left:5px;',
						xtype : 'label',
						text : '项目:'
					}, {
						name : 'Q_projectNew.proName_S_LK',
						xtype : 'textfield',
						width : 100
					}, {
						xtype : 'button',
						text : '查询',
						iconCls : 'search',
						style : 'margin-left:5px;',
						handler : this.search.createCallback(this)
					}]
		});//end of the searchPanel

		//加载数据至store
		this.store = new Ext.data.JsonStore({
			url : __ctxPath + "/admin/listForExportExpressApply.do",
			root : 'result',
			totalProperty : 'totalCounts',
			remoteSort : true,
			fields : ['applyer', 'applyDate', 'expressType', 'province', 'city', 'toWhereName', 'expressName', 'expressNo', 'dispatchType', 'projectNew',
					'dept']
		});
		this.store.setDefaultSort('id', 'desc');
		//加载数据
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
					{
						iconCls : 'btn-del',
						qtip : '删除',
						style : 'margin:0 3px 0 3px'
					}, {
						iconCls : 'btn-edit',
						qtip : '编辑',
						style : 'margin:0 3px 0 3px'
					}, {
						iconCls : 'btn-flowView',
						qtip : '查看审批表单',
						style : 'margin:0 3px 0 3px'
					}]
		});

		//初始化ColumnModel
		var sm = new Ext.grid.CheckboxSelectionModel();
		var cm = new Ext.grid.ColumnModel({
			columns : [sm, new Ext.grid.RowNumberer()
//					, {
//						header : '申请类型',
//						dataIndex : 'expressType',
//						renderer : function(value) {
//							if (value == 1) {
//								return '客户';
//							} else if (value == 2) {
//								return '供应商';
//							} else if (value == 3) {
//								return '其他';
//							}
//						}
//					}, {
//						header : '目的地城市',
//						dataIndex : 'city'
//					}, {
//						header : '目的单位',
//						dataIndex : 'toWhereName'
//					}
					, {
						header : '快递单号',
						dataIndex : 'expressNo'
					}
					, {
						header : '部门/项目',
						dataIndex:'projectNew',
						renderer : function(value, metadata, record) {
							var str = "";
							if (!Ext.isEmpty(record.data.dept)) {
								str = "部门";
							} else if (!Ext.isEmpty(record.data.projectNew)) {
								str = "项目";
							}
							return str;
						}
					}, {
						header : '部门名/项目名',
						dataIndex:'dept',
						renderer : function(value,meta,record) {
							if (!Ext.isEmpty(record.data.dept)) {
								return record.data.dept.depName;
							}else if (!Ext.isEmpty(record.data.projectNew)) {
								return record.data.projectNew.proName;
							}
						}
					}, {
						header : '经办人',
						dataIndex : 'applyer'
					}, {
						header : '时间',
						dataIndex : 'applyDate',
						renderer : function(value) {
							return value.substring(0, 10);
						}
					}
//					, {
//						header : '区域',
//						dataIndex : 'projectNew',
//						renderer : function(value) {
//							if (!Ext.isEmpty(value)) {
//								return value.area;
//							}
//						}
//					}
//					, {
//						header : '快递公司',
//						dataIndex : 'expressName'
//					}
//					, this.rowActions
					],
			defaults : {
				sortable : true,
				menuDisabled : false,
				width : 100
			}
		});
		//初始化工具栏
		this.topbar = new Ext.Toolbar({
			height : 30,
			bodyStyle : 'text-align:left',
			items : [
//					{
//						iconCls : 'btn-add',
//						text : '添加快递信息',
//						xtype : 'button',
//						handler : this.createRecord
//					}, {
//						iconCls : 'btn-del',
//						text : '删除快递信息',
//						xtype : 'button',
//						handler : this.delRecords,
//						scope : this
//					},
					{
						iconCls : 'btn-xls',
						text : '导出',
						xtype : 'button',
						handler : this.toExllist.createCallback(this),
						scope : this
					}]
		});

		this.gridPanel = new Ext.grid.GridPanel({
			region : 'center',
			stripeRows : true,
			tbar : this.topbar,
			store : this.store,
			trackMouseOver : true,
			disableSelection : false,
			loadMask : true,
			cm : cm,
			sm : sm,
			plugins : this.rowActions,
			viewConfig : {
				forceFit : true,
				autoFill : true, //自动填充
				forceFit : true
				//showPreview : false
			},
			bbar : new Ext.PagingToolbar({
				pageSize : 25,
				id:'bbarP',
				store : this.store,
				plugins : [new Ext.ux.PageSizePlugin()],
				displayInfo : true,
				displayMsg : '当前页记录索引{0}-{1}， 共{2}条记录',
				emptyMsg : "当前没有记录"
			})
		});

		this.gridPanel.addListener('rowdblclick', function(grid, rowindex, e) {
			grid.getSelectionModel().each(function(rec) {
				new ExpressApplyForm({
					id : rec.data.id
				}).show();
			});
		});
		this.rowActions.on('action', this.onRowAction, this);
	},//end of the initComponents()

	/**
	 * 
	 * @param {}
	 *            self 当前窗体对象
	 */
	search : function(self) {
		if (self.searchPanel.getForm().isValid()) {//如果合法
			self.submitEmptyText();
			var params = self.gridPanel.getStore().baseParams;
			Ext.apply(params, self.searchPanel.getForm().getValues());
			self.gridPanel.getStore().load(params);
		}
	},
	submitEmptyText: function(){//解决 emptyText传值到后台
		var form = this.searchPanel.getForm();
		var submitValues = form.getValues();
		for (var param in submitValues) {
			if (form.findField(param).emptyText == submitValues[param]) {
				form.findField(param).emptyText = '';
				form.findField(param).setValue('');
			}
		}
	},
	toExllist : function(self) {
		var limit = 25;
		var start = 0;
		var params = self.gridPanel.getStore().baseParams;
		Ext.apply(params, self.searchPanel.getForm().getValues());
		limit = Ext.getCmp('bbarP').pageSize;
		start = Ext.getCmp('bbarP').cursor;
		var beginMonth = Ext.getCmp('beginMonth').getValue();
		var endMonth = Ext.getCmp('endMonth').getValue();
		var jsonStr = Ext.util.JSON.encode(params);
		location = __ctxPath + '/admin/exportExpressApply.do?jsonStr=' + jsonStr+'&limit='+limit+'&start='+start+'&dir=DESC&sort=id&beginMonth='+beginMonth+'&endMonth='+endMonth;
//		Ext.ux.Printer.print(self.gridPanel);
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
			case 'btn-flowView' :
				AppUtil.selectProcessForm(record.data.processRunId);
				break;
			default :
				break;
		}
	}

});
