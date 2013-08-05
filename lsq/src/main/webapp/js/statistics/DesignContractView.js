/**
 * @author:
 * @class DesignContractView
 * @extends Ext.Panel
 * @description [DesignContract]管理
 * @company
 * @createtime:2010-07-19
 */
DesignContractView = Ext.extend(Ext.Panel, {
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
		DesignContractView.superclass.constructor.call(this, {
					id : 'DesignContractView',
					title : '设计合同管理',
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
					height:50,
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
								text : '合同编号:'
							}, {
								name : 'Q_contractNo_S_LK',
								xtype : 'textfield',
								width:90
							}, {
								style : 'margin-top:5px;',
								xtype : 'label',
								text : '项目名称:'
							}, {
								name : 'Q_projectName_S_LK',
								xtype : 'textfield',
								width:90
							},{
								style : 'margin-top:5px;',
								xtype : 'label',
								text : '合同总价:'
							}, {
								name : 'Q_contractAmount_S_LK',
								xtype : 'textfield',
								width:90
							}, {
								xtype : 'button',
								text : '查询',
								iconCls : 'search',
								handler : this.search.createCallback(this)
							}]
				});// end of the searchPanel

		// 加载数据至store
		this.store = new Ext.data.JsonStore({
					url : __ctxPath + "/statistics/listDesignContract.do",
					root : 'result',
					totalProperty : 'totalCounts',
					remoteSort : true,
					fields : [{
								name : 'id',
								type : 'int'
							}, 'contractNo', 'companyName', 'designDept',
							'companyProperty', 'cooperation', 'cooperationNum',
							'companyCredit', 'projectName', 'projectAddr',
							'contractAmount', 'designArea', 'singlePrice',
							'projectPrice', 'chargeRate', 'isEndWork',
							'workArea', 'isLetDesignFee','processRunId']
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
						{
								iconCls : 'btn-del',
								qtip : '删除',
								style : 'margin:0 3px 0 3px',
								hide:!isGranted("_DesignContractDel")
							},
							{
								iconCls : 'btn-edit',
								qtip : '编辑',
								style : 'margin:0 3px 0 3px',
								hide:!isGranted("_DesignContractEdit")
							},{
								iconCls:'btn-flowView',
								qtip:'查看审批表单',
								style:'margin:0 3px 0 3px'
							}]
				});

		// 初始化ColumnModel
		var sm = new Ext.grid.CheckboxSelectionModel();
		var cm = new Ext.grid.ColumnModel({
					columns : [sm, new Ext.grid.RowNumberer(), {
								header : 'id',
								dataIndex : 'id',
								hidden : true
							}, {
								header : '合同编号',
								dataIndex : 'contractNo'
							}, {
								header : '甲方单位名称',
								dataIndex : 'companyName'
							}, {
								header : '设计部门',
								dataIndex : 'designDept'
							}, {
								header : '甲方单位性质',
								dataIndex : 'companyProperty'
							}, {
								header : '有无合作',
								dataIndex : 'cooperation'
							}, {
								header : '合作次数',
								dataIndex : 'cooperationNum',
								renderer:function(value){
									if(!Ext.isEmpty(value)){
										return value+"次";
									}
								}
							}, {
								header : '甲方资信评价',
								dataIndex : 'companyCredit'
							}, {
								header : '项目名称',
								dataIndex : 'projectName'
							}, {
								header : '项目地点',
								dataIndex : 'projectAddr'
							}, {
								header : '合同总价',
								dataIndex : 'contractAmount',
								renderer:function(value){
									if(!Ext.isEmpty(value)){
										return value+"元";
									}
								}
							}, {
								header : '设计面积',
								dataIndex : 'designArea',
								renderer:function(value){
									if(!Ext.isEmpty(value)){
										return value+"㎡";
									}
								}
							}, {
								header : '单价',
								dataIndex : 'singlePrice',
								renderer:function(value){
									if(!Ext.isEmpty(value)){
										return value+"元/㎡";
									}
								}
							}, {
								header : '工程造价',
								dataIndex : 'projectPrice',
								renderer:function(value){
									if(!Ext.isEmpty(value)){
										return value+"元";
									}
								}
							}, {
								header : '取费率',
								dataIndex : 'chargeRate',
								renderer:function(value){
									if(!Ext.isEmpty(value)){
										return value+"%";
									}
								}
							}, {
								header : '是否承接后期施工',
								dataIndex : 'isEndWork'
							}, {
								header : '施工跟进区域',
								dataIndex : 'workArea'
							}, {
								header : '有无因承接后期施工而让利设计费情况',
								dataIndex : 'isLetDesignFee'
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
						{
								iconCls : 'btn-add',
								text : '添加[设计合同]',
								xtype : 'button',
								hidden:!isGranted("_DesignContractAdd"),
								handler : this.createRecord
							}, {
								iconCls : 'btn-del',
								text : '删除[设计合同]',
								xtype : 'button',
								hidden:!isGranted("_DesignContractDel"),
								handler : this.delRecords,
								scope : this
							}]
				});

		this.gridPanel = new Ext.grid.GridPanel({
					id : 'DesignContractGrid',
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
					sm : sm,
					plugins : this.rowActions,
					viewConfig : {
						forceFit : false
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
						if(isGranted("_DesignContractEdit")){
								new DesignContractForm({id : rec.data.id}).show();
					 	}
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
		if(self.searchPanel.getForm().isValid()){//如果合法
			var params = self.gridPanel.getStore().baseParams; 
            Ext.apply(params,self.searchPanel.getForm().getValues());
            self.gridPanel.getStore().load(params);
		}
	},

	/**
	 * 添加记录
	 */
	createRecord : function() {
		new DesignContractForm().show();
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
											+ '/statistics/multiDelDesignContract.do',
									params : {
										ids : ids
									},
									method : 'POST',
									success : function(response, options) {
										Ext.ux.Toast.msg('操作信息',
												'成功删除该[设计合同]！');
										Ext.getCmp('DesignContractGrid')
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
		var gridPanel = Ext.getCmp('DesignContractGrid');
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
		new DesignContractForm({
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
			case 'btn-flowView':
				AppUtil.selectProcessForm(record.data.processRunId);
				break;
			default :
				break;
		}
	}
});
