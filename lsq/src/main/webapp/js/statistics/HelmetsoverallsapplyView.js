/**
 * @author:
 * @class HelmetsoverallsapplyView
 * @extends Ext.Panel
 * @description 安全帽工作服管理
 * @company
 * @createtime:2010-07-19
 */
HelmetsoverallsapplyView = Ext.extend(Ext.Panel, {
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
		HelmetsoverallsapplyView.superclass.constructor.call(this, {
					id : 'HelmetsoverallsapplyView',
					title : '安全帽工作服管理',
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
					height : 90,
					defaults : {
						border : false,
						anchor : '98%,98%'
					},
					items : [{
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
								text : '施工区域名称:'
							}, {
								name : 'Q_areaName_S_LK',
								xtype : 'textfield'
							}, {
								style : 'margin-top:5px;',
								xtype : 'label',
								text : '工程名称:'
							}, {
								name : 'Q_proName_S_LK',
								xtype : 'textfield'
							}, {
								style : 'margin-top:5px;',
								xtype : 'label',
								text : '工程详细地址:'
							}, {
								name : 'Q_address_S_LK',
								xtype : 'textfield'
							}, {
								style : 'margin-top:5px;',
								xtype : 'label',
								text : '负责人姓名:'
							}, {
								name : 'Q_designChargerName_S_LK',
								xtype : 'textfield'
							}, {
								style : 'margin-top:5px;',
								xtype : 'label',
								text : '负责人电话:'
							},{
								name : 'Q_designChargerTel_S_LK',
								xtype : 'textfield'
							}]}, {
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
							items : [ {
								style : 'margin-top:5px;margin-left:100px;',
								xtype : 'label',
								text : '收货人姓名:'
							}, {
								name : 'Q_takeUserName_S_LK',
								xtype : 'textfield'
							}, {
								style : 'margin-top:5px;',
								xtype : 'label',
								text : '收货人电话号码:'
							}, {
								name : 'Q_takeUserTel_S_LK',
								xtype : 'textfield'
							}, {
								style : 'margin-top:5px;',
								xtype : 'label',
								text : '备注:'
							}, {
								name : 'Q_remark_S_LK',
								xtype : 'textfield'
							},{
								xtype : 'button',
								text : '查询',
								iconCls : 'search',
								handler : this.search.createCallback(this)
							}]}]
				});// end of the searchPanel

		// 加载数据至store
		this.store = new Ext.data.JsonStore({
					url : __ctxPath + "/statistics/listHelmetsoverallsapply.do",
					root : 'result',
					totalProperty : 'totalCounts',
					remoteSort : true,
					fields : [{
								name : 'id',
								type : 'int'
							}, 'areaName', 'proName', 'proID',
							'address', 'designChargerId', 'designChargerName',
							'designChargerTel', 'takeUserName', 'takeUserId',
							'takeUserTel', 'remark', 'redNum', 'yellowNum',
							'whiteNum', 'blueNum', 'longSleeveXL',
							'longSleeveXXL', 'longSleeveXXXL', 'shortSleeveXL',
							'shortSleeveXXL', 'shortSleeveXXXL', 'processRunId','timeCreate']
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
								hide : !isGranted("_HelmetsoverallsapplyDel")
							}, {
								iconCls : 'btn-edit',
								qtip : '编辑',
								style : 'margin:0 3px 0 3px',
								hide : !isGranted("_HelmetsoverallsapplyEdit")
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
								header : '施工区域名称',
								dataIndex : 'areaName'
							}, {
								header : '工程名称',
								dataIndex : 'proName'
							}, {
								header : '工程详细地址',
								dataIndex : 'address'
							}, {
								header : '负责人姓名',
								dataIndex : 'designChargerName'
							}, {
								header : '负责人电话',
								dataIndex : 'designChargerTel'
							}, {
								header : '收货人姓名',
								dataIndex : 'takeUserName'
							}, {
								header : '收货人电话号码',
								dataIndex : 'takeUserTel'
							}, {
								header : '红色数量',
								dataIndex : 'redNum'
							}, {
								header : '黄色数量',
								dataIndex : 'yellowNum'
							}, {
								header : '白色数量',
								dataIndex : 'whiteNum'
							}, {
								header : '蓝色数量',
								dataIndex : 'blueNum'
							}, {
								header : '长袖XL数量',
								dataIndex : 'longSleeveXL'
							}, {
								header : '长袖XXL数量',
								dataIndex : 'longSleeveXXL'
							}, {
								header : '长袖XXXL数量',
								dataIndex : 'longSleeveXXXL'
							}, {
								header : '短袖XL数量',
								dataIndex : 'shortSleeveXL'
							}, {
								header : '短袖XXL数量',
								dataIndex : 'shortSleeveXXL'
							}, {
								header : '短袖XXXL数量',
								dataIndex : 'shortSleeveXXXL'
							}, {
								header : '备注',
								dataIndex : 'remark'
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
								text : '添加[安全帽工作服]',
								xtype : 'button',
								handler : this.createRecord,
								hidden : !isGranted("_HelmetsoverallsapplyAdd")
							}, {
								iconCls : 'btn-del',
								text : '删除[安全帽工作服]',
								xtype : 'button',
								handler : this.delRecords,
								scope : this,
								hidden : !isGranted("_HelmetsoverallsapplyDel")
							}]
				});

		this.gridPanel = new Ext.grid.GridPanel({
					id : 'HelmetsoverallsapplyGrid',
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
								if (isGranted("_HelmetsoverallsapplyEdit")) {
									new HelmetsoverallsapplyForm({
												id : rec.data.id
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
		new HelmetsoverallsapplyForm().show();
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
									+ '/statistics/multiDelHelmetsoverallsapply.do',
							params : {
								ids : ids
							},
							method : 'POST',
							success : function(response, options) {
								Ext.ux.Toast.msg('操作信息', '成功删除该安全帽工作服！');
								Ext.getCmp('HelmetsoverallsapplyGrid')
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
		var gridPanel = Ext.getCmp('HelmetsoverallsapplyGrid');
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
		new HelmetsoverallsapplyForm({
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
			default :
				break;
		}
	}
});
