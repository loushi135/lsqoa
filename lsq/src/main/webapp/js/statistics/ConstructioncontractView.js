/**
 * @author:
 * @class ConstructioncontractView
 * @extends Ext.Panel
 * @description [Constructioncontract]管理
 * @company
 * @createtime:2010-07-19
 */
ConstructioncontractView = Ext.extend(Ext.Panel, {
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
		ConstructioncontractView.superclass.constructor.call(this, {
					id : 'ConstructioncontractView',
					title : '施工合同管理',
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
								style : 'margin-top:5px;margin-left:5px;',
								xtype : 'label',
								text : '合同编号:'
							}, {
								name : 'Q_contractNo_S_LK',
								xtype : 'textfield',
								width:90
							}, {
								style : 'margin-top:5px;margin-left:5px;',
								xtype : 'label',
								text : '发包人:'
							}, {
								name : 'Q_contractor_S_LK',
								xtype : 'textfield',
								width:90
							}, {
								style : 'margin-top:5px;margin-left:5px;',
								xtype : 'label',
								text : '项目名称:'
							}, {
								name : 'Q_projectName_S_LK',
								xtype : 'textfield',
								width:90
							}, {
								style : 'margin-top:5px;margin-left:5px;',
								xtype : 'label',
								text : '项目负责人:'
							}, {
								name : 'Q_projectCharger_S_LK',
								xtype : 'textfield',
								width:90
							}, {
								xtype : 'button',
								text : '查询',
								style : 'margin-left:5px;',
								iconCls : 'search',
								handler : this.search.createCallback(this)
							}]
				});// end of the searchPanel

		// 加载数据至store
		this.store = new Ext.data.JsonStore({
					url : __ctxPath + "/statistics/listConstructioncontract.do",
					root : 'result',
					totalProperty : 'totalCounts',
					remoteSort : true,
					fields : [{
								name : 'contractId',
								type : 'int'
							}, 'contractNo', 'contractor', 'projectRegional',
							'projectName', 'projectCharger',
							'projectAbbreviation', 'businessCharger',
							'linkmanAndTel', 'numOrArea', 'sumPrice', 'payWay',
							'projectTime', 'quality', 'isFullContract',
							'isDesignCost', 'designCost', 'isModelCommunity',
							'guarantee', 'constructionBackUp',
							'constructionBackUpPerson',
							'constructionBackUpFinishTime',
							'constructionLicense', 'constructionLicensePerson',
							'constructionLicenseFinishTime', 'isoverBudget',
							'quote', 'quoteloss', 'remark', 'meno','status','contractRemark','processRunId']
				});
		this.store.setDefaultSort('contractId', 'desc');
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
							 iconCls:'btn-del',
							 qtip:'删除',
							 style:'margin:0 3px 0 3px',
							 hide:!(isGranted("_ConstructioncontractDel"))
							 },
							{
								iconCls : 'btn-edit',
								qtip : '编辑',
								hide:!(isGranted("_ConstructioncontractEdit")||isGranted("_ConstructioncontractQuery")),
								style : 'margin:0 3px 0 3px'
							},
							{
								iconCls:'btn-flowView',
								qtip:'查看审批表单',
								style:'margin:0 3px 0 3px'
							}]
						});

		// 初始化ColumnModel
		var sm = new Ext.grid.CheckboxSelectionModel();
		var cm = new Ext.grid.ColumnModel({
					columns : [sm, new Ext.grid.RowNumberer(), {
								header : 'contractId',
								dataIndex : 'contractId',
								hidden : true
							}, {
								header : '合同编号',
								dataIndex : 'contractNo'
							}, {
								header : '发包人',
								dataIndex : 'contractor'
							}, {
								header : '施工区域',
								dataIndex : 'projectRegional'
							}, {
								header : '项目名称',
								dataIndex : 'projectName'
							}, {
								header : '项目实际负责人',
								dataIndex : 'projectCharger'
							}, {
								header : '项目简称',
								dataIndex : 'projectAbbreviation'
							}, {
								header : '业务主办',
								dataIndex : 'businessCharger'
							}, {
								header : '发包联系人及电话',
								dataIndex : 'linkmanAndTel'
							}, {
								header : '数量/面积',
								dataIndex : 'numOrArea'
							}, {
								header : '合同总价',
								dataIndex : 'sumPrice'
							}, {
								header : '合同说明',
								dataIndex : 'contractRemark'
							}, {
								header : '付款方式',
								dataIndex : 'payWay'
							}, {
								header : '工期要求/能否满足/奖罚',
								dataIndex : 'projectTime'
							}, {
								header : '质量/奖罚',
								dataIndex : 'quality'
							}, {
								header : '是否双包',
								dataIndex : 'isFullContract'
							}, {
								header : '是否承担设计费',
								dataIndex : 'isDesignCost'
							}, {
								header : '设计费',
								dataIndex : 'designCost'
							}, {
								header : '是否文明工地',
								dataIndex : 'isModelCommunity'
							}, {
								header : '保修期',
								dataIndex : 'guarantee'
							}, {
								header : '是否办理施工备案',
								dataIndex : 'constructionBackUp'
							}, {
								header : '办理责任人',
								dataIndex : 'constructionBackUpPerson'
							}, {
								header : '预计完成时间',
								dataIndex : 'constructionBackUpFinishTime'
							}, {
								header : '是否办理施工许可证',
								dataIndex : 'constructionLicense'
							}, {
								header : '许可证办理责任人',
								dataIndex : 'constructionLicensePerson'
							}, {
								header : '许可证预计完成时间',
								dataIndex : 'constructionLicenseFinishTime'
							}, {
								header : '是否存在（无）超资质施工的情况',
								dataIndex : 'isoverBudget'
							}, {
								header : '报价',
								dataIndex : 'quote'
							}, {
								header : '预估亏损额',
								dataIndex : 'quoteloss',
								renderer : function(value) {
									if (value != '') {
										return value + "元";
									}
								}
							}, {
								header : '其他应在签订前商谈的事项',
								dataIndex : 'remark'
							}, {
								header : '应在合同履行过程中注意的事项',
								dataIndex : 'meno'
							}, {
								header : '状态',
								dataIndex : 'status',
								renderer : function(value) {
									if (value != '') {
										if(value=='0'){
											return '可用';
										}else if(value=='1'){
											return '不可用';
										}
									}else{
										return '可用';
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
								text : '添加合同',
								xtype : 'button',
								hidden:!(isGranted("_ConstructioncontractAdd")) ,
								handler : this.createRecord
							}, {
								iconCls : 'btn-del',
								text : '删除合同',
								xtype : 'button',
								handler : this.delRecords,
								hidden:!(isGranted("_ConstructioncontractDel")) ,
								scope : this
							}]
				});

		this.gridPanel = new Ext.grid.GridPanel({
					id : 'ConstructioncontractGrid',
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
							if(isGranted('_ConstructioncontractQuery')||isGranted('_ConstructioncontractEdit')){
								new ConstructioncontractForm({
											contractId : rec.data.contractId
										}).show();
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
		if (self.searchPanel.getForm().isValid()) {// 如果合法
			var params = self.gridPanel.getStore().baseParams;
			Ext.apply(params, self.searchPanel.getForm().getValues());
			self.gridPanel.getStore().load(params);
		}
	},

	/**
	 * 添加记录
	 */
	createRecord : function() {
		new ConstructioncontractForm().show();
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
									+ '/statistics/multiDelConstructioncontract.do',
							params : {
								ids : ids
							},
							method : 'POST',
							success : function(response, options) {
								Ext.ux.Toast.msg('操作信息',
										'成功删除该合同！');
								Ext.getCmp('ConstructioncontractGrid')
										.getStore().reload();
							},
							failure : function(response, options) {
								Ext.ux.Toast.msg('操作信息', '操作出错，请联系管理员！');
							}
						});
			}
		});// end of comfirm
	},
	/**
	 * 删除多条记录
	 */
	delRecords : function() {
		var gridPanel = Ext.getCmp('ConstructioncontractGrid');
		var selectRecords = gridPanel.getSelectionModel().getSelections();
		if (selectRecords.length == 0) {
			Ext.ux.Toast.msg("信息", "请选择要删除的记录！");
			return;
		}
		var ids = Array();
		for (var i = 0; i < selectRecords.length; i++) {
			ids.push(selectRecords[i].data.contractId);
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
		new ConstructioncontractForm({
					contractId : record.data.contractId
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
				this.delByIds(record.data.contractId);
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
