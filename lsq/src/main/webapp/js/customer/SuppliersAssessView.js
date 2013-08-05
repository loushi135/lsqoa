/**
 * @author:
 * @class SuppliersAssessView
 * @extends Ext.Panel
 * @description [SuppliersAssess]管理
 * @company
 * @createtime:2010-07-19
 */
SuppliersAssessView = Ext.extend(Ext.Panel, {
	//条件搜索Panel
	searchPanel : null,
	//数据展示Panel
	gridPanel : null,
	//GridPanel的数据Store
	store : null,
	treePanel : null,
	mainPanel : null,
	selectedNode : null,
	//头部工具栏
	topbar : null,
	//构造函数
	constructor : function(_cfg) {
		Ext.applyIf(this, _cfg);
		//初始化组件
		this.initUIComponents();
		//调用父类构造
		SuppliersAssessView.superclass.constructor.call(this, {
			id : 'SuppliersAssessView',
			title : '[供应商]管理',
			region : 'center',
			layout : 'border',
			items : [this.treePanel, this.mainPanel]
		});
	},//end of constructor

	//初始化组件
	initUIComponents : function() {
		//初始化treePanel
		var thiz = this;
		var provinceTreePanel = new ProvinceTreePanel();
		provinceTreePanel.type = '1';
		this.treePanel = provinceTreePanel.getTree();
		//初始化搜索条件Panel
		this.searchPanel = new Ext.FormPanel({
			layout : 'column',
			height : 50,
			region : 'north',
			bodyStyle : 'padding:6px 10px 6px 10px',
			border : false,
			frame : true,
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
						text : '编号:'
					}, {
						name : 'Q_suppliersNo_S_LK',
						xtype : 'textfield'
					}, {
						style : 'margin-top:5px;',
						xtype : 'label',
						text : '公司名称:'
					}, {
						name : 'Q_suppliersName_S_LK',
						xtype : 'textfield'
					}, {
						style : 'margin-top:5px;',
						xtype : 'label',
						text : '业务联系人:'
					}, {
						name : 'Q_supplierContacter_S_LK',
						xtype : 'textfield'
					}, {
						xtype : 'button',
						text : '查询',
						iconCls : 'search',
						handler : this.search.createCallback(this)
					}]
		});//end of the searchPanel

		//加载数据至store
		this.store = new Ext.data.JsonStore({
			url : __ctxPath + "/customer/listSuppliersAssess.do",
			root : 'result',
			totalProperty : 'totalCounts',
			baseParams:{'Q_type_N_EQ':0},
			remoteSort : true,
			fields : [{
						name : 'suppliersId',
						type : 'int'
					}, 'suppliersNo', 'suppliersName', 'cooperateType','project', 'suppliersNature', 'mainProduct', 'supplierAddress', 'legalRepresentative',
					'registeredCapital', 'establishDate', 'supplierContacter', 'supplierPhone', 'companyNature', 'suppliersbank', 'bankAccount', 'staffNum',
					'managerNum', 'salesNum', 'techNum', 'institutionNature', 'businessArea', 'brand', 'systemType', 'delFlag', 'processRunId',
					'province', 'city']
		});
		this.store.setDefaultSort('suppliersId', 'desc');
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
						iconCls : 'btn-del',
						qtip : '删除',
						style : 'margin:0 3px 0 3px',
						hide:!isGranted("_SuppliersAssessDel")
					}, {
						iconCls : 'btn-edit',
						qtip : '编辑',
						style : 'margin:0 3px 0 3px',
						hide:!isGranted("_SuppliersAssessEdit")
					},{
						 iconCls:'btn-flowView',
						 qtip:'查看审批表单',
						 style:'margin:0 3px 0 3px'
					}]
		});

		//初始化ColumnModel
		var sm = new Ext.grid.CheckboxSelectionModel();
		var cm = new Ext.grid.ColumnModel({
			columns : [sm, new Ext.grid.RowNumberer(), {
						header : 'suppliersId',
						dataIndex : 'suppliersId',
						hidden : true
					}, {
						header : '编号',
						dataIndex : 'suppliersNo'
					}, {
						header : '公司名称',
						dataIndex : 'suppliersName'
					}, {
						header : '合作类型',
						dataIndex : 'cooperateType'
					}, {
						header : '项目名称',
						dataIndex : 'project',
						width:150,
						renderer:function(value,meta,record){
							if(!Ext.isEmpty(value)){
								return value.proName;
							}
						}
					}, {
						header : '供应商性质',
						dataIndex : 'suppliersNature'
					}, {
						header : '主要产品/业务范围',
						dataIndex : 'mainProduct'
					}, {
						header : '注册地址',
						dataIndex : 'supplierAddress'
					}, {
						header : '法人代表',
						dataIndex : 'legalRepresentative'
					}, {
						header : '注册资金',
						dataIndex : 'registeredCapital'
					}, {
						header : '公司创立日期',
						dataIndex : 'establishDate'
					}, {
						header : '业务联系人',
						dataIndex : 'supplierContacter'
					}, {
						header : '联系电话',
						dataIndex : 'supplierPhone'
					}, {
						header : '公司所有制',
						dataIndex : 'companyNature'
					}, {
						header : '开户银行',
						dataIndex : 'suppliersbank'
					}, {
						header : '开户账号',
						dataIndex : 'bankAccount'
					}, {
						header : '雇员总人数',
						dataIndex : 'staffNum'
					}, {
						header : '管理人员',
						dataIndex : 'managerNum'
					}, {
						header : '营销人员',
						dataIndex : 'salesNum'
					}, {
						header : '技术人员',
						dataIndex : 'techNum'
					}, {
						header : '机构性质',
						dataIndex : 'institutionNature'
					}, {
						header : '营业范围',
						dataIndex : 'businessArea'
					}, {
						header : '经营(代理) 品牌',
						dataIndex : 'brand'
					}, {
						header : '系统类别',
						dataIndex : 'systemType'
					}, {
						header : '地区',
						renderer : function(val, meta, record, rowIndex, colIndex, store) {
							var province = record.data.province;
							var city = record.data.city;
							var str = "";
							if (province != '' && province != null) {
								str += province.provinceName;
								if (city != '' && city != null) {
									str += "-" + city.cityName;
								}
							} else if (city != '' && city != null) {
								str += city.cityName;
							}
							return str;
						}
					}, {
						header : '是否删除',
						dataIndex : 'delFlag',
						renderer : function(value) {
							if (value == 1) {
								return '不可用'
							} else if (value == 0) {
								return '可用'
							}
						}
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
			items : [{
						iconCls : 'btn-add',
						text : '添加[供应商]',
						xtype : 'button',
						hidden : !isGranted('_SuppliersAssessAdd'),
						handler : this.createRecord
					}, {
						iconCls : 'btn-del',
						text : '删除[供应商]',
						xtype : 'button',
						hidden : !isGranted('_SuppliersAssessDel'),
						handler : this.delRecords,
						scope : this
					}, {
						iconCls : 'btn-edit',
						text : '更新关联省市',
						//								hidden:!isGranted('_updateCity'),
						hidden : true,
						xtype : 'button',
						handler : this.updateCity,
						scope : this
					}]
		});

		this.gridPanel = new Ext.grid.GridPanel({
			id : 'SuppliersAssessGrid',
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
				forceFit : false,
				autoFill : false
				//自动填充
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

		this.gridPanel.addListener('rowdblclick', function(grid, rowindex, e) {
			grid.getSelectionModel().each(function(rec) {
				new SuppliersAssessForm({
					suppliersId : rec.data.suppliersId
				}).show();
			});
		});
		this.rowActions.on('action', this.onRowAction, this);

		this.mainPanel = new Ext.Panel({
			region : 'center',
			title : '供应商列表',
			layout : 'border',
			items : [this.searchPanel, this.gridPanel]
		});
	},//end of the initComponents()

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

	/**
	 * 添加记录
	 */
	createRecord : function() {
		new SuppliersAssessForm().show();
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
					url : __ctxPath + '/customer/multiDelSuppliersAssess.do',
					params : {
						ids : ids
					},
					method : 'POST',
					success : function(response, options) {
						Ext.ux.Toast.msg('操作信息', '成功删除该[供应商]！');
						Ext.getCmp('SuppliersAssessGrid').getStore().reload();
					},
					failure : function(response, options) {
						Ext.ux.Toast.msg('操作信息', '操作出错，请联系管理员！');
					}
				});
			}
		});//end of comfirm
	},
	/**
	 * 删除多条记录
	 */
	delRecords : function() {
		var gridPanel = Ext.getCmp('SuppliersAssessGrid');
		var selectRecords = gridPanel.getSelectionModel().getSelections();
		if (selectRecords.length == 0) {
			Ext.ux.Toast.msg("信息", "请选择要删除的记录！");
			return;
		}
		var ids = Array();
		for (var i = 0; i < selectRecords.length; i++) {
			ids.push(selectRecords[i].data.suppliersId);
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
		new SuppliersAssessForm({
			suppliersId : record.data.suppliersId
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
				this.delByIds(record.data.suppliersId);
				break;
			case 'btn-edit' :
				this.editRecord(record);
				break;
			case 'btn-flowView':
				AppUtil.selectProcessForm(record.data.processRunId);
				break;
			default :
				break;
		}
	},
	updateCity : function() {
		Ext.Ajax.request({
			url : __ctxPath + '/customer/updateCitySuppliersAssess.do',
			method : 'POST',
			success : function(response, options) {
				Ext.ux.Toast.msg('操作信息', '操作成功！');
				Ext.getCmp('SuppliersAssessGrid').getStore().reload();
			},
			failure : function(response, options) {
				Ext.ux.Toast.msg('操作信息', '操作出错，请联系管理员！');
			}
		});
	}
});
