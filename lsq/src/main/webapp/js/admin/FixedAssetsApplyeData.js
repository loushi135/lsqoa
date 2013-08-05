FixedAssetsApplyeData = Ext.extend(Ext.Panel, {
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
		FixedAssetsApplyeData.superclass.constructor.call(this, {
			id : 'FixedAssetsApplyeData',
			title : '公司固定资产',
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
			frame : false,
			height : 35,
			defaults : {
				border : false,
				anchor : '98%,98%',
				xtype : 'label'
			},
			items : [{
					style : 'margin-top:5px;',
					text : '请输入查询条件:',
					xtype : 'label'
				}, {
					style : 'margin-top:5px;',
					text : '申请部门：',
					xtype : 'label'
				}, {
					name : 'Q_ef.dept_S_LK',
					xtype : 'textfield'
				}, {
					style : 'margin-top:5px;',
					text : '申请人：',
					xtype : 'label'
				}, {
					name : 'Q_ef.name_S_LK',
					xtype : 'textfield'
				}, {
					style : 'margin-top:5px;',
					text : '项目名称：',
					xtype : 'label'
				}, {
					name : 'Q_ef.useProject.proName_S_LK',
					xtype : 'textfield'
				}, {
					style : 'margin-top:5px;',
					text : '请购类型：',
					xtype : 'label'
				}, {
					hiddenName : 'Q_ef.applyType_N_EQ',
					xtype : 'combo',
					editable : true,
					triggerAction : 'all',
					store : [['0', '电子设备'], ['1', '运输设备'], ['2', '其他'], ['3', '办公家具']]
				}, {
					xtype : 'button',
					text : '查询',
					iconCls : 'search',
					handler : this.search.createCallback(this)
				}]
		});//end of the searchPanel

		//加载数据至store
		this.store = new Ext.data.JsonStore({
			url : __ctxPath + "/admin/list2000AssetsApply.do",
			root : 'result',
			totalProperty : 'totalCounts',
			remoteSort : true,
			fields : [{
					name : 'id',
					type : 'int'
				}, 'name', 'dept', 'applyDate', 'applyType', 'useProject', 
				'assetsApplycontents', 'useProjectManager', 'applyDescription', 'processRunId', 'isSubsidyOrNot']
		});
		this.store.setDefaultSort('ef.id', 'desc');
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
			actions : [{
					iconCls : 'btn-flowView',
					qtip : '查看审批表单',
					style : 'margin:0 3px 0 3px'
				}]
		});
		var expander = new Ext.grid.RowExpander({
			tpl : new Ext.XTemplate(
			//品名、规格型号、品牌、单位、到货日期、数量、单价
			'<div width=100%  style=margin-left:70px; >',
			'<table width=100%  cellspacing="0" cellpadding="0" >',
			'<tr style="color:gray"><td style="margin-left:70px;" width=20%  >品名</td><td style="margin-left:0px;" width=20% >规格型号</td><td style="margin-left:0px;" width=10% >品牌</td><td style="margin-left:0px;" width=20% >单位</td><td style="margin-left:0px;" width=10% >到货日期</td><td style="margin-left:0px;" width=10% >数量</td><td style="margin-left:0px;" width=10% >单价</td> </tr>',
			'<tpl for="assetsApplycontents">', '<tr><td style=margin-left:70px;>{name}</td><td style="margin-left:0px;">', '{model}',
			'</td><td style="margin-left:0px;">{brand}</td><td style="margin-left:0px;">{unit}</td><td style="margin-left:0px;">{arrivalDate}</td><td style="margin-left:0px;">{num}</td><td style="margin-left:0px;">{price}</td> </tr>', '</tpl>',
			'<tpl if="this.isEmpty(assetsApplycontents)"><td style=margin-left:70px;>无</td></tpl>', '</table>', '</div>', {
				isEmpty : function(list) {
					return list == ''
				}
			}),
			// 设置行双击是否展开
			// expandOnDblClick : false,
			lazyRender : true,
			enableCaching : false
		});
		//初始化ColumnModel
		var sm = new Ext.grid.CheckboxSelectionModel();
		var cm = new Ext.grid.ColumnModel({
			columns : [sm, new Ext.grid.RowNumberer(), {
					header : 'id',
					dataIndex : 'id',
					hidden : true
				}, expander, {
					header : '申请人',
					dataIndex : 'name',
					sortable:false
				}, {
					header : '申请部门',
					dataIndex : 'dept'
				}, {
					header : '申请日期',
					dataIndex : 'applyDate',
					renderer : function(value) {
						return value.substring(0, 10);
					}
				}, {
					header : '项目名称',
					dataIndex : 'useProject',
					sortable:false,
					renderer : function(value) {
						if (Ext.isEmpty(value)) {
							return '';
						} else {
							return value.proName;
						}
					}
				}, {
					header : '项目经理',
					dataIndex : 'useProjectManager',
					sortable:false,
					renderer : function(value) {
						if (Ext.isEmpty(value)) {
							return '';
						} else {
							return value.fullname;
						}
					}
				}, {
					header : '资产类型',
					dataIndex : 'applyType',
					renderer : function(value) {
						var result;
						switch (value) {
							case 0 :
								result = '电子设备';
								break;
							case 1 :
								result = '运输设备';
								break;
							case 2 :
								result = '其他';
								break;
							case 3 :
								result = '办公家具';
								break;
						}
						return result;
					}
				}, {
					header : '请购说明',
					dataIndex : 'applyDescription'
				}, {
					header : '是否补贴',
					dataIndex : 'isSubsidyOrNot'
				}, this.rowActions],
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
			items : []
		});

		this.gridPanel = new Ext.grid.GridPanel({
			id : 'AssetsApplyDataGrid',
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
			plugins : [this.rowActions, expander],
			viewConfig : {
				forceFit : true,
				autoFill : true, //自动填充
				forceFit : true
				//showPreview : false
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

		this.rowActions.on('action', this.onRowAction, this);
	},//end of the initComponents()

	search : function(self) {
		if (self.searchPanel.getForm().isValid()) {//如果合法
			self.searchPanel.getForm().submit({
				waitMsg : '正在提交查询',
				url : __ctxPath + '/admin/list2000AssetsApply.do',
				success : function(formPanel, action) {
					var result = Ext.util.JSON.decode(action.response.responseText);
					var params = self.gridPanel.getStore().baseParams;
					Ext.apply(params, self.searchPanel.getForm().getValues());
					self.gridPanel.getStore().loadData(result);
				}
			});
		}
	},

	onRowAction : function(gridPanel, record, action, row, col) {
		switch (action) {
			case 'btn-flowView' :
				AppUtil.selectProcessForm(record.data.processRunId);
				break;
			default :
				break;
		}
	}
});
