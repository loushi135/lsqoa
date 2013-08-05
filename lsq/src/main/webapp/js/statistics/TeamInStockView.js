/**
 * @author:
 * @class TeamInStockView
 * @extends Ext.Panel
 * @description 班组入库评审管理
 * @company
 * @createtime:2010-07-19
 */
TeamInStockView = Ext.extend(Ext.Panel, {
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
		TeamInStockView.superclass.constructor.call(this, {
					id : 'TeamInStockView',
					title : '[班组信息]管理',
					region : 'center',
					layout : 'border',
					items : [this.searchPanel, this.gridPanel]
				});
	},// end of constructor

	// 初始化组件
	initUIComponents : function() {
		// 初始化搜索条件Panel
		this.searchPanel = new Ext.FormPanel({
					layout : 'form',
					region : 'north',
					bodyStyle : 'padding:6px 10px 6px 10px',
					border : false,
					frame : true,
					height : 90,
					defaults : {
						border : false,
						anchor : '98%,98%'
					},
					items : [

					{
								xtype : "panel",
								layout : "column",
								border : false,
								unstyled : false,
								style : 'margin-top:5px;',
								defaults : {
									xtype : 'label',
									style : {
										margin : '0px 0px 5px 0px'
									}
								},
								items : [{
											style : 'margin-top:5px;',
											xtype : 'label',
											text : '请输入查询条件:'
										}, {
											style : 'margin-top:5px;',
											xtype : 'label',
											text : '单位名称:'
										}, {
											name : 'Q_suppliersName_S_LK',
											xtype : 'textfield'
										}, {
											style : 'margin-top:5px;',
											xtype : 'label',
											text : '推荐人:'
										}, {
											name : 'Q_inviteUserName_S_LK',
											xtype : 'textfield'
										}, {
											style : 'margin-top:5px;',
											xtype : 'label',
											text : '法定代表人:'
										}, {
											name : 'Q_legalRepresentative_S_LK',
											xtype : 'textfield'
										}, {
											style : 'margin-top:5px;',
											xtype : 'label',
											text : '联系人:'
										}, {
											name : 'Q_supplierContacter_S_LK',
											xtype : 'textfield'
										}]
							}, {
								xtype : "panel",
								layout : "column",
								border : false,
								unstyled : false,
								defaults : {
									xtype : 'label',
									style : {
										margin : '0px 0px 3px 0px'
									}
								},
								items : [{
											style : 'margin-top:5px;margin-left:88px;',
											xtype : 'label',
											text : '通讯地址:'
										}, {
											style : 'margin-top:5px;',
											name : 'Q_supplierAddress_S_LK',
											xtype : 'textfield'
										}, {
											style : 'margin-top:5px;',
											xtype : 'label',
											text : '联系人电话:'
										}, {
											style : 'margin-top:5px;',
											name : 'Q_supplierPhone_S_LK',
											xtype : 'textfield',
											width : 120
										}, {
											style : 'margin-top:5px;',
											xtype : 'label',
											text : '工人主要来自地区:'
										}, {
											style : 'margin-top:5px;',
											name : 'Q_mainArea_S_LK',
											xtype : 'textfield',
											width : 105
										}, {
											style : 'margin-top:5px;',
											xtype : 'button',
											text : '查询',
											iconCls : 'search',
											handler : this.search
													.createCallback(this)
										}]
							}]
				});// end of the searchPanel

		// 加载数据至store
		this.store = new Ext.data.JsonStore({
					url : __ctxPath + "/customer/listSuppliersAssess.do",
					root : 'result',
					totalProperty : 'totalCounts',
					baseParams:{'Q_type_N_EQ':1},
					remoteSort : true,
					fields : [{
								name : 'suppliersId',
								type : 'int'
							},'suppliersNo', 'suppliersName', 'inviteUserName', 'legalRepresentative',
							'supplierContacter', 'supplierAddress', 'supplierPhone',
							'registeredCapital', 'zip', 'officeTel', 'fax',
							'email', 'fixedStaffNum', 'techDebugNum', 'leaderNum',
							'validElecNum', 'validWelderNum',
							'validQualifyNum', 'peakNum', 'mainArea',
							'previousYearOutput', 'lastYearOutput', 'mainInfo',
							'recommendReason', 'processRunId',
							'applyUser', 'suppliersbank', 'bankAccount','cooperateType','project','province', 'city']
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
								hide : !isGranted("_TeamInStockDel")
							}, {
								iconCls : 'btn-edit',
								qtip : '编辑',
								style : 'margin:0 3px 0 3px',
								hide : !isGranted("_TeamInStockEdit")
							}, {
								iconCls : 'btn-flowView',
								qtip : '查看审批表单',
								style : 'margin:0 3px 0 3px'
							}]
				});

		// 初始化ColumnModel
		var sm = new Ext.grid.CheckboxSelectionModel();
		var cm = new Ext.grid.ColumnModel({
					columns : [sm, new Ext.grid.RowNumberer(), {
								header : 'suppliersId',
								dataIndex : 'suppliersId',
								hidden : true
							}, {
								header : '单位名称',
								dataIndex : 'suppliersName'
							}, {
								header : '编号',
								dataIndex : 'suppliersNo'
							}, {
								header : '推荐人',
								dataIndex : 'inviteUserName'
							}, {
								header : '法定代表人',
								dataIndex : 'legalRepresentative'
							}, {
								header : '联系人',
								dataIndex : 'supplierContacter'
							}, {
								header : '通讯地址',
								dataIndex : 'supplierAddress'
							}, {
								header : '联系人电话',
								dataIndex : 'supplierPhone'
							}, {
								header : '注册资金',
								dataIndex : 'registeredCapital'
							}, {
								header : '邮      编',
								dataIndex : 'zip'
							}, {
								header : '开户银行',
								dataIndex : 'suppliersbank'
							}, {
								header : '银行帐号',
								dataIndex : 'bankAccount'
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
								header : '办公电话',
								dataIndex : 'officeTel'
							}, {
								header : '传真',
								dataIndex : 'fax'
							}, {
								header : 'email',
								dataIndex : 'email'
							}, {
								header : '固定员工人数',
								dataIndex : 'fixedStaffNum'
							}, {
								header : '其中专业技术安装调试人员数',
								dataIndex : 'techDebugNum'
							}, {
								header : '其中班组长人数 ',
								dataIndex : 'leaderNum'
							}, {
								header : '持有效电工证工人数',
								dataIndex : 'validElecNum'
							}, {
								header : '持有效焊工证工人数',
								dataIndex : 'validWelderNum'
							}, {
								header : '持资格证书',
								dataIndex : 'validQualifyNum'
							}, {
								header : '高峰可上人员',
								dataIndex : 'peakNum'
							}, {
								header : '工人主要来自地区',
								dataIndex : 'mainArea'
							}, {
								header : '前年工程产值',
								dataIndex : 'previousYearOutput'
							}, {
								header : '去年工程产值',
								dataIndex : 'lastYearOutput'
							}, {
								header : '主要骨干姓名及联系电话擅长系统信息',
								dataIndex : 'mainInfo'
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
								header : '推荐原因',
								dataIndex : 'recommendReason'
							}, {
								header : '经办人',
								dataIndex : 'applyUser',
								renderer : function(value, metadata, record,
										rowIndex, colIndex) {
									var str = '';
									if (!Ext.isEmpty(value)) {
										str = value.fullname;
									}
									return str;
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
								text : '添加[班组]',
								xtype : 'button',
								handler : this.createRecord,
								hidden : !isGranted("_TeamInStockAdd")
							}, {
								iconCls : 'btn-del',
								text : '删除[班组]',
								xtype : 'button',
								handler : this.delRecords,
								scope : this,
								hidden : !isGranted("_TeamInStockDel")
							}]
				});

		this.gridPanel = new Ext.grid.GridPanel({
					id : 'TeamInStockGrid',
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
								plugins : [new Ext.ux.PageSizePlugin()],
								displayInfo : true,
								displayMsg : '当前页记录索引{0}-{1}， 共{2}条记录',
								emptyMsg : "当前没有记录"
							})
				});

		this.gridPanel.addListener('rowdblclick', function(grid, rowindex, e) {
					grid.getSelectionModel().each(function(rec) {
								if (isGranted("_TeamInStockEdit")) {
									new TeamInStockForm({
												suppliersId : rec.data.suppliersId
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
		new TeamInStockForm().show();
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
											+ '/customer/multiDelSuppliersAssess.do',
									params : {
										ids : ids
									},
									method : 'POST',
									success : function(response, options) {
										Ext.ux.Toast
												.msg('操作信息', '成功删除该班组！');
										Ext.getCmp('TeamInStockGrid')
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
		var gridPanel = Ext.getCmp('TeamInStockGrid');
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
		new TeamInStockForm({
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
			case 'btn-flowView' :
				AppUtil.selectProcessForm(record.data.processRunId);
				break;
			default :
				break;
		}
	}
});
