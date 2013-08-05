/**
 * @author:
 * @class ProjectReceiveView
 * @extends Ext.Panel
 * @description [ProjectReceive]管理
 * @company
 * @createtime:2010-07-19
 */
ProjectReceiveView = Ext.extend(Ext.Panel, {
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
		ProjectReceiveView.superclass.constructor.call(this, {
					id : 'ProjectReceiveView',
					title : '[收款]管理',
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
							}, {
								style : 'margin-top:5px;',
								xtype : 'label',
								text : '凭证字号:'
							}, {
								name : 'Q_pzzh_S_LK',
								xtype : 'textfield'
							}, {
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
		// this.mainPanel = new Ext.FormPanel({
		// layout : 'column',
		// region : 'north',
		// bodyStyle : 'padding:6px 10px 6px 10px',
		// border : false,
		// frame : true,
		// height : 50,
		// defaults : {
		// border : false,
		// anchor : '98%,98%',
		// xtype:'label',
		// style : 'margin-left:5px;margin-top:5px;'
		// },
		// items : [{
		// text : '项目信息:'
		// }, {
		// text : '项目名称:'
		// }, {
		// id:'proNameLabel'
		// }, {
		// text : '项目编号:'
		// }, {
		// id:'proNoLabel'
		// }, {
		// text : '所属区域:'
		// }, {
		// id : 'areaLabel'
		// }, {
		// text : '项目负责人:'
		// }, {
		// id : 'proChargerLabel'
		// }, {
		// text : '收款总计:'
		// }, {
		// id : 'receiveTotal'
		// }, {
		// text : '大写:'
		// }, {
		// id : 'receiveTotalBig'
		// }]
		// });// end of the mainPanel

		// 加载数据至store
		this.store = new Ext.data.JsonStore({
					url : __ctxPath + "/statistics/listProjectReceive.do",
					root : 'result',
					totalProperty : 'totalCounts',
					// baseParams:{'Q_project.id_L_EQ':this.proId},
					remoteSort : true,
					fields : [{
								name : 'id',
								type : 'int'
							}, 'amountBig', 'amount', 'project', 'createtime',
							'createUser', 'receiveTime', 'zy', 'pzzh']
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
								hide : !isGranted("_ProjectReceiveDel")
							}, {
								iconCls : 'btn-edit',
								qtip : '编辑',
								style : 'margin:0 3px 0 3px',
								hide : !isGranted("_ProjectReceiveEdit")
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
								header : '项目名称',
								dataIndex : 'project',
								renderer : function(value) {
									return value.proName
								}
							}, {
								header : '收款时间',
								dataIndex : 'receiveTime'
							}, {
								header : '收款金额',
								dataIndex : 'amount'
							}, {
								header : '收款金额大写',
								dataIndex : 'amountBig'
							}, {
								header : '凭证字号',
								dataIndex : 'pzzh'
							}, {
								header : '摘要',
								dataIndex : 'zy'
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
								iconCls : 'btn-xls',
								text : '导入[收款]',
								xtype : 'button',
								handler : this.importRecord,
								scope : this,
								hidden : !isGranted("_ProjectReceiveImport")
							}, {
								iconCls : 'btn-add',
								text : '添加[收款]',
								xtype : 'button',
								handler : this.createRecord,
								scope : this,
								hidden : !isGranted("_ProjectReceiveAdd")
							}, {
								iconCls : 'btn-del',
								text : '删除[收款]',
								xtype : 'button',
								handler : this.delRecords,
								scope : this,
								hidden : !isGranted("_ProjectReceiveDel")
							}]
				});

		this.gridPanel = new Ext.grid.GridPanel({
					id : 'ProjectReceiveGrid',
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
					grid.getSelectionModel().each(function(rec) {
								if (isGranted("_ProjectReceiveEdit")) {
									new ProjectReceiveForm({
												id : rec.data.id
											}).show();
								}
							});
				});
		this.rowActions.on('action', this.onRowAction, this);
		this.initData();
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
	initData : function() {
		// if (this.proId != null && this.proId != 'undefined') {
		// this.mainPanel.getForm().load({
		// deferredRender : false,
		// url : __ctxPath + '/statistics/getProjectNew.do?id='+ this.proId,
		// waitMsg : '正在载入数据...',
		// success : function(form, action) {
		// var jsonData = Ext.util.JSON.decode(action.response.responseText);
		// var res = jsonData.data;
		// Ext.getCmp("proNameLabel").setText("<font
		// color='green'>"+res.proName+"</font>",false);
		// Ext.getCmp("proNoLabel").setText("<font
		// color='green'>"+res.proNo+"</font>",false);
		// Ext.getCmp("areaLabel").setText("<font
		// color='green'>"+res.area+"</font>",false);
		// var proCharger = "";
		// if(!Ext.isEmpty(res.proChargerUser)){
		// proCharger = res.proChargerUser.fullname;
		// }
		// Ext.getCmp("proChargerLabel").setText("<font
		// color='green'>"+proCharger+"</font>",false);
		// // Ext.getCmp("proChargerLabel").setText("<font
		// color='green'>"+AmountInWords(res.contractAmount)+"</font>",false);
		// var url = __ctxPath +
		// '/statistics/getReceiveByProNoProjectReceive.do?proNo='+ res.proNo;
		// var receiveTotal = ajaxSyncCall(url).data;
		// Ext.getCmp("receiveTotal").setText("<font
		// color='green'>"+receiveTotal+"</font>",false);
		// Ext.getCmp("receiveTotalBig").setText("<font
		// color='green'>"+AmountInWords(receiveTotal)+"</font>",false);
		// },
		// failure : function(form, action) {
		// }
		// });
		// }
	},
	/**
	 * 添加记录
	 */
	createRecord : function() {
		new ProjectReceiveForm({
					proId : this.proId
				}).show();
	},
	importRecord : function() {
		new ProjectReceiveImport({
					proId : this.proId
				}).show();
	},
	/**
	 * 按IDS删除记录
	 * 
	 * @param {}
	 *            ids
	 */
	delByIds : function(ids, obj) {
		Ext.Msg.confirm('信息确认', '您确认要删除所选记录吗？', function(btn) {
					if (btn == 'yes') {
						Ext.Ajax.request({
									url : __ctxPath
											+ '/statistics/multiDelProjectReceive.do',
									params : {
										ids : ids
									},
									method : 'POST',
									success : function(response, options) {
										Ext.ux.Toast.msg('操作信息',
												'成功删除该[收款]！');
										obj.initData();
										Ext.getCmp('ProjectReceiveGrid')
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
		var gridPanel = Ext.getCmp('ProjectReceiveGrid');
		var selectRecords = gridPanel.getSelectionModel().getSelections();
		if (selectRecords.length == 0) {
			Ext.ux.Toast.msg("信息", "请选择要删除的记录！");
			return;
		}
		var ids = Array();
		for (var i = 0; i < selectRecords.length; i++) {
			ids.push(selectRecords[i].data.id);
		}
		this.delByIds(ids, this);
	},

	/**
	 * 编辑记录
	 * 
	 * @param {}
	 *            record
	 */
	editRecord : function(record) {
		new ProjectReceiveForm({
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
				this.delByIds(record.data.id, this);
				break;
			case 'btn-edit' :
				this.editRecord(record);
				break;
			default :
				break;
		}
	}
});
